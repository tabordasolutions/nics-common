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
