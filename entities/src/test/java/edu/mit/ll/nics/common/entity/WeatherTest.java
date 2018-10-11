package edu.mit.ll.nics.common.entity;

import edu.mit.ll.nics.common.entity.Weather;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class WeatherTest {

    @Test
    public void verifyGettersOnWeatherInstance() {
        Weather weather = new Weather("objectid", "POINT(-121.0909 35.009893)", 80.01, 2.0f, 0.0, 3.01f, "OK", 8.012);
        assertEquals(weather.getObjectId(), "objectid");
        assertEquals(weather.getAirTemperature(), (Double) 80.01);
        assertEquals(weather.getWindSpeed(), (Float) 2.0f);
        assertEquals(weather.getWindDirection(), (Double) 0.0);
        assertEquals(weather.getHumidity(), (Float) 3.01f);
        assertEquals(weather.getQcStatus(), "OK");
        assertEquals(weather.getDistance(), (Double) 8.012);
    }

    @Test
    public void verifyDirectionDegreesForNorth() {
          Weather weather = new Weather("objectid", "POINT(-121.0909 35.009893)", 80.01, 2.0f, 0.0, 3.01f, "OK", 8.012);
          assertEquals(weather.getDescriptiveWindDirection(), "North");
          weather = new Weather("objectid", "POINT(-121.0909 35.009893)", 80.01, 2.0f, 10.0, 3.01f, "OK", 8.012);
          assertEquals(weather.getDescriptiveWindDirection(), "North");
          weather = new Weather("objectid", "POINT(-121.0909 35.009893)", 80.01, 2.0f, 360.0, 3.01f, "OK", 8.012);
          assertEquals(weather.getDescriptiveWindDirection(), "North");
          weather = new Weather("objectid", "POINT(-121.0909 35.009893)", 80.01, 2.0f, 337.5, 3.01f, "OK", 8.012);
          assertEquals(weather.getDescriptiveWindDirection(), "North");
    }

    @Test
    public void verifyDirectionDegreesForNorthEast() {
        Weather weather = new Weather("objectid", "POINT(-121.0909 35.009893)", 80.01, 2.0f, 22.5, 3.01f, "OK", 8.012);
        assertEquals(weather.getDescriptiveWindDirection(), "NorthEast");
        weather = new Weather("objectid", "POINT(-121.0909 35.009893)", 80.01, 2.0f, 32.5, 3.01f, "OK", 8.012);
        assertEquals(weather.getDescriptiveWindDirection(), "NorthEast");
        weather = new Weather("objectid", "POINT(-121.0909 35.009893)", 80.01, 2.0f, 45.0, 3.01f, "OK", 8.012);
        assertEquals(weather.getDescriptiveWindDirection(), "NorthEast");
        weather = new Weather("objectid", "POINT(-121.0909 35.009893)", 80.01, 2.0f, 65.5, 3.01f, "OK", 8.012);
        assertEquals(weather.getDescriptiveWindDirection(), "NorthEast");
    }

    @Test
    public void verifyDirectionDegreesForEast() {
        Weather weather = new Weather("objectid", "POINT(-121.0909 35.009893)", 80.01, 2.0f, 68.0, 3.01f, "OK", 8.012);
        assertEquals(weather.getDescriptiveWindDirection(), "East");
        weather = new Weather("objectid", "POINT(-121.0909 35.009893)", 80.01, 2.0f, 75.0, 3.01f, "OK", 8.012);
        assertEquals(weather.getDescriptiveWindDirection(), "East");
        weather = new Weather("objectid", "POINT(-121.0909 35.009893)", 80.01, 2.0f, 90.0, 3.01f, "OK", 8.012);
        assertEquals(weather.getDescriptiveWindDirection(), "East");
    }

    @Test
    public void verifyCompassDirectionForNorthWest() {
        Weather weather = new Weather("objectid", "POINT(-121.0909 35.009893)", 80.01, 2.0f, 315.0, 3.01f, "OK", 8.012);
        assertEquals(weather.getDescriptiveWindDirection(), "NorthWest");
        weather = new Weather("objectid", "POINT(-121.0909 35.009893)", 80.01, 2.0f, 335.0, 3.01f, "OK", 8.012);
        assertEquals(weather.getDescriptiveWindDirection(), "NorthWest");
    }

    @Test
    public void testDegreesAreNormalizedBeforeCalculatingCompassDirection() {
        Weather weather = new Weather("objectid", "POINT(-121.0909 35.009893)", 80.01, 2.0f, 360.0+275.0, 3.01f, "OK", 8.012);
        assertEquals(weather.getDescriptiveWindDirection(), "West");
        weather = new Weather("objectid", "POINT(-121.0909 35.009893)", 80.01, 2.0f, 360.0+220, 3.01f, "OK", 8.012);
        assertEquals(weather.getDescriptiveWindDirection(), "SouthWest");
        weather = new Weather("objectid", "POINT(-121.0909 35.009893)", 80.01, 2.0f, 360.0+170, 3.01f, "OK", 8.012);
        assertEquals(weather.getDescriptiveWindDirection(), "South");
    }
}
