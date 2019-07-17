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
package edu.mit.ll.nics.nicsdao;

import java.util.Collections;

import com.vividsolutions.jts.geom.Coordinate;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import edu.mit.ll.nics.common.entity.Weather;
import edu.mit.ll.nics.nicsdao.mappers.WeatherRowMapper;
import edu.mit.ll.nics.nicsdao.impl.WeatherDAOImpl;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import org.mockito.Mockito;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.anyString;


public class WeatherDAOImplTest {
    private NamedParameterJdbcTemplate mockJdbcTemplate = Mockito.mock(NamedParameterJdbcTemplate.class);
    private WeatherDAOImpl weatherDAO = new WeatherDAOImpl(mockJdbcTemplate);
    private Weather weather = new Weather("objectid", "location",
        80.01, 9.22f, 27.22, 310.0, 38.85f, "OK", 8.018);

    @Test
    public void returnsWeatherInfoSuccessfully() {
        Mockito.when(mockJdbcTemplate.queryForObject(anyString(), eq(Collections.EMPTY_MAP), any(WeatherRowMapper.class))).thenReturn(weather);
        Weather result = weatherDAO.getWeatherDataFromLocation(new Coordinate(-121.098, 35.0998), 10);
        assertEquals(result, weather);
    }

    @Test
    public void returnsNullIfNoResultsFound() {
        Mockito.when(mockJdbcTemplate.queryForObject(anyString(), eq(Collections.EMPTY_MAP), any(WeatherRowMapper.class))).thenThrow(new EmptyResultDataAccessException(1));
        assertNull(weatherDAO.getWeatherDataFromLocation(new Coordinate(-121.098, 35.0998), 10));
    }

    @Test(expected = Exception.class)
    public void throwsExceptionIfFailsToFetchWeatherData() {
        Mockito.when(mockJdbcTemplate.queryForObject(anyString(), eq(Collections.EMPTY_MAP), any(WeatherRowMapper.class))).thenThrow(new Exception("Test Exception"));
        weatherDAO.getWeatherDataFromLocation(new Coordinate(-121.098, 35.0998), 10);
    }

}
