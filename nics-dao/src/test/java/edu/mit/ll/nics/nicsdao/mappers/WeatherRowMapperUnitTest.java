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
