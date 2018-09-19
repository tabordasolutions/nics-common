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
          assertEquals(weather.getDescriptiveWindDirection(), "N");
          weather = new Weather("objectid", "POINT(-121.0909 35.009893)", 80.01, 2.0f, 10.0, 3.01f, "OK", 8.012);
          assertEquals(weather.getDescriptiveWindDirection(), "N");
          weather = new Weather("objectid", "POINT(-121.0909 35.009893)", 80.01, 2.0f, 360.0, 3.01f, "OK", 8.012);
          assertEquals(weather.getDescriptiveWindDirection(), "N");
    }

    @Test
    public void verifyDirectionDegreesForNNE() {
        Weather weather = new Weather("objectid", "POINT(-121.0909 35.009893)", 80.01, 2.0f, 12.25, 3.01f, "OK", 8.012);
        assertEquals(weather.getDescriptiveWindDirection(), "NNE");
        weather = new Weather("objectid", "POINT(-121.0909 35.009893)", 80.01, 2.0f, 12.26, 3.01f, "OK", 8.012);
        assertEquals(weather.getDescriptiveWindDirection(), "NNE");
        weather = new Weather("objectid", "POINT(-121.0909 35.009893)", 80.01, 2.0f, 20.0, 3.01f, "OK", 8.012);
        assertEquals(weather.getDescriptiveWindDirection(), "NNE");
        weather = new Weather("objectid", "POINT(-121.0909 35.009893)", 80.01, 2.0f, 22.5, 3.01f, "OK", 8.012);
        assertEquals(weather.getDescriptiveWindDirection(), "NNE");
    }

    @Test
    public void verifyDirectionDegreesForNE() {
        Weather weather = new Weather("objectid", "POINT(-121.0909 35.009893)", 80.01, 2.0f, 34.75, 3.01f, "OK", 8.012);
        assertEquals(weather.getDescriptiveWindDirection(), "NE");
        weather = new Weather("objectid", "POINT(-121.0909 35.009893)", 80.01, 2.0f, 42.5, 3.01f, "OK", 8.012);
        assertEquals(weather.getDescriptiveWindDirection(), "NE");
        weather = new Weather("objectid", "POINT(-121.0909 35.009893)", 80.01, 2.0f, 44.8, 3.01f, "OK", 8.012);
        assertEquals(weather.getDescriptiveWindDirection(), "NE");
    }

    @Test
    public void verifyCompassDirectionForSSW() {
        Weather weather = new Weather("objectid", "POINT(-121.0909 35.009893)", 80.01, 2.0f, 202.0, 3.01f, "OK", 8.012);
        assertEquals(weather.getDescriptiveWindDirection(), "SSW");
    }

    @Test
    public void testDegreesAreNormalizedBeforeCalculatingCompassDirection() {
        Weather weather = new Weather("objectid", "POINT(-121.0909 35.009893)", 80.01, 2.0f, 360.0+360, 3.01f, "OK", 8.012);
        assertEquals(weather.getDescriptiveWindDirection(), "N");
        weather = new Weather("objectid", "POINT(-121.0909 35.009893)", 80.01, 2.0f, 360.0+250, 3.01f, "OK", 8.012);
        assertEquals(weather.getDescriptiveWindDirection(), "WSW");
        weather = new Weather("objectid", "POINT(-121.0909 35.009893)", 80.01, 2.0f, 360.0+170, 3.01f, "OK", 8.012);
        assertEquals(weather.getDescriptiveWindDirection(), "S");
    }
}
