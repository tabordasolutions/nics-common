package edu.mit.ll.nics.nicsdao.impl;

import edu.mit.ll.nics.common.entity.Weather;
import edu.mit.ll.nics.nicsdao.PostGISDataSource;
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
    public Weather getWeatherDataFromLocation(double[] location, double searchRangeInKiloMeters) throws DataAccessException {
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

    private String getWeatherDataSQL(double[] location, double searchRangeInKiloMeters) {
       String givenLocationGeography = String.format("st_geogfromtext('SRID=4326;POINT(%.20f %.20f)')", location[0], location[1]);
       String distanceFunctionSQL = String.format("st_distance(location::geography, %s)/1000", givenLocationGeography);
       double searchRangeInMeters = searchRangeInKiloMeters * 1000;
       String selectClause = "SELECT objectid as objectId, st_astext(location) as location, " +
        "air_temperature as airTemperature, wind_speed as windSpeed, " +
                "wind_direction as windDirection, relative_humidity as relativeHumidity, " +
                "qc_status as qcStatus, " + distanceFunctionSQL + " as distance ";
       String whereClause = String.format(" WHERE qc_status in ('OK', 'WARNING') and st_dwithin(location::geography, %s, %.6f) ", givenLocationGeography, searchRangeInMeters);
       String orderByClause = "ORDER BY " + distanceFunctionSQL;
       String limitClause = "LIMIT 1";
       return String.format(selectClause + "\n" +
                 " FROM " + WEATHER_DB_VIEW + "\n" +
                 whereClause +
                 orderByClause + " \n" +
                 limitClause, searchRangeInMeters);
    }
}
