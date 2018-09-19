package edu.mit.ll.nics.nicsdao.mappers;

import edu.mit.ll.jdbc.JoinRowMapper;
import edu.mit.ll.nics.common.entity.Weather;

import java.sql.ResultSet;
import java.sql.SQLException;

public class WeatherRowMapper extends JoinRowMapper<Weather> {

    public WeatherRowMapper() {
        super("weather");
    }

    @Override
    public Weather createRowObject(ResultSet rs, int rowNum) throws SQLException {
        Weather weather = new Weather(rs.getString("objectid"), rs.getString("location"),
        rs.getDouble("airTemperature"), rs.getFloat("windSpeed"),
        rs.getDouble("windDirection"), rs.getFloat("relativeHumidity"), rs.getString("qcStatus"), rs.getDouble("distance"));
        return weather;
    }

    @Override
    public Object getKey(ResultSet rs) throws SQLException {
        return rs.getString("objectid");
    }
}
