/**
 * Copyright (c) 2008-2018, Massachusetts Institute of Technology (MIT)
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this
 * list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 * this list of conditions and the following disclaimer in the documentation
 * and/or other materials provided with the distribution.
 *
 * 3. Neither the name of the copyright holder nor the names of its contributors
 * may be used to endorse or promote products derived from this software without
 * specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package edu.mit.ll.nics.nicsdao.impl;

import com.vividsolutions.jts.geom.Coordinate;
import edu.mit.ll.nics.common.entity.Weather;
import edu.mit.ll.nics.nicsdao.WeatherDAO;
import edu.mit.ll.nics.nicsdao.mappers.WeatherRowMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.Collections;

public class WeatherDAOImpl implements WeatherDAO {

    private Logger log = LoggerFactory.getLogger(WeatherDAOImpl.class);

    public static final String WEATHER_DB_VIEW = "synoptics_weather";
    private NamedParameterJdbcTemplate jdbcTemplate = null;
    private WeatherRowMapper weatherRowMapper = null;

    public WeatherDAOImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.weatherRowMapper = new WeatherRowMapper();
    }

    @Override
    public Weather getWeatherDataFromLocation(Coordinate location, double searchRangeInKiloMeters) throws DataAccessException {
        Weather weather = null;
        if(searchRangeInKiloMeters <= 0) {
            log.warn("No Weather data can be found in given search range %.10f", searchRangeInKiloMeters);
            return null;
        }
        String sql = this.getWeatherDataSQL(location, searchRangeInKiloMeters);
        try {
            weather = jdbcTemplate.queryForObject(sql, Collections.EMPTY_MAP, weatherRowMapper);
        } catch (EmptyResultDataAccessException e) {
            log.error(String.format("No weather data found within %.10f km of location %s", searchRangeInKiloMeters, location.toString()), e);
            return null;
        }
        return weather;
    }

    private String getWeatherDataSQL(Coordinate location, double searchRangeInKiloMeters) {
       String givenLocationGeography = String.format("st_geogfromtext('SRID=4326;POINT(%.20f %.20f)')", location.x, location.y);
       String distanceFunctionSQL = String.format("st_distance(location::geography, %s)/1000", givenLocationGeography);
       double searchRangeInMeters = searchRangeInKiloMeters * 1000;
       String selectClause = "SELECT objectid as objectId, st_astext(location) as location, " +
        "air_temperature as airTemperature, wind_speed as windSpeed, " +
                "wind_direction as windDirection, relative_humidity as relativeHumidity, " +
                "qc_status as qcStatus, " + distanceFunctionSQL + " as distance ";
       String whereClause = String.format(" WHERE qc_status in ('OK', 'WARNING') and st_dwithin(location::geography, %s, %.6f) ", givenLocationGeography, searchRangeInMeters);
       String orderByClause = "ORDER BY " + distanceFunctionSQL;
       String limitClause = " LIMIT 1";
       return String.format(selectClause +
                 " FROM " + WEATHER_DB_VIEW +
                 whereClause +
                 orderByClause +
                 limitClause, searchRangeInMeters);
    }
}
