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
package edu.mit.ll.nics.nicsdao.mappers;

import java.sql.ResultSet;

import edu.mit.ll.nics.common.entity.Weather;
import edu.mit.ll.nics.nicsdao.mappers.WeatherRowMapper;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class WeatherRowMapperUnitTest {
    private WeatherRowMapper weatherRowMapper = new WeatherRowMapper();
    private ResultSet resultSet = mock(ResultSet.class);
    Weather weather = new Weather("objectid", "POINT(-121.0909 35.009893)", 80.01, 2.0f, 310.0, 3.01f, "OK", 8.012);

    @Test
    public void createsWeatherInstanceFromResultset() throws Exception {
        when(resultSet.getString("objectid")).thenReturn(weather.getObjectId());
        when(resultSet.getString("location")).thenReturn(weather.getLocation());
        when(resultSet.getDouble("airTemperature")).thenReturn(weather.getAirTemperature());
        when(resultSet.getFloat("windSpeed")).thenReturn(weather.getWindSpeed());
        when(resultSet.getDouble("windDirection")).thenReturn(weather.getWindDirection());
        when(resultSet.getFloat("relativeHumidity")).thenReturn(weather.getHumidity());
        when(resultSet.getString("qcStatus")).thenReturn(weather.getQcStatus());
        when(resultSet.getDouble("distance")).thenReturn(weather.getDistance());
    }
}
