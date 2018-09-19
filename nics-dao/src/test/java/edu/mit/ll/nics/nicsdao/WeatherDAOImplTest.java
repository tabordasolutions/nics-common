package edu.mit.ll.nics.nicsdao;

import java.util.Collections;

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
        80.01, 9.22f, 310.0, 38.85f, "OK", 8.018);

    @Test
    public void returnsWeatherInfoSuccessfully() {
        Mockito.when(mockJdbcTemplate.queryForObject(anyString(), eq(Collections.EMPTY_MAP), any(WeatherRowMapper.class))).thenReturn(weather);
        Weather result = weatherDAO.getWeatherDataFromLocation(new double[] {-121.098, 35.0998}, 10);
        assertEquals(result, weather);
    }

    @Test
    public void returnsNullIfNoResultsFound() {
        Mockito.when(mockJdbcTemplate.queryForObject(anyString(), eq(Collections.EMPTY_MAP), any(WeatherRowMapper.class))).thenThrow(new EmptyResultDataAccessException(1));
        assertNull(weatherDAO.getWeatherDataFromLocation(new double[] {-121.098, 35.0998}, 10));
    }

    @Test(expected = Exception.class)
    public void throwsExceptionIfFailsToFetchWeatherData() {
        Mockito.when(mockJdbcTemplate.queryForObject(anyString(), eq(Collections.EMPTY_MAP), any(WeatherRowMapper.class))).thenThrow(new Exception("Test Exception"));
        weatherDAO.getWeatherDataFromLocation(new double[] {-121.098, 35.0998}, 10);
    }

}
