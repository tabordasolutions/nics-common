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
        Weather weather = new Weather("objectid", "POINT(-121.0909 35.009893)", 80.01, 2.0f, 27.22,  0.0, 3.01f, "OK", 8.012);
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
          Weather weather = new Weather("objectid", "POINT(-121.0909 35.009893)", 80.01, 2.0f, 27.22,0.0, 3.01f, "OK", 8.012);
          assertEquals(weather.getDescriptiveWindDirection(), "North");
          weather = new Weather("objectid", "POINT(-121.0909 35.009893)", 80.01, 2.0f, 27.22, 10.0, 3.01f, "OK", 8.012);
          assertEquals(weather.getDescriptiveWindDirection(), "North");
          weather = new Weather("objectid", "POINT(-121.0909 35.009893)", 80.01, 2.0f, 27.22, 360.0, 3.01f, "OK", 8.012);
          assertEquals(weather.getDescriptiveWindDirection(), "North");
          weather = new Weather("objectid", "POINT(-121.0909 35.009893)", 80.01, 2.0f, 27.22, 337.5, 3.01f, "OK", 8.012);
          assertEquals(weather.getDescriptiveWindDirection(), "North");
    }

    @Test
    public void verifyDirectionDegreesForNorthEast() {
        Weather weather = new Weather("objectid", "POINT(-121.0909 35.009893)", 80.01, 2.0f, 27.22, 22.5, 3.01f, "OK", 8.012);
        assertEquals(weather.getDescriptiveWindDirection(), "NorthEast");
        weather = new Weather("objectid", "POINT(-121.0909 35.009893)", 80.01, 2.0f, 27.22, 32.5, 3.01f, "OK", 8.012);
        assertEquals(weather.getDescriptiveWindDirection(), "NorthEast");
        weather = new Weather("objectid", "POINT(-121.0909 35.009893)", 80.01, 2.0f, 27.22, 45.0, 3.01f, "OK", 8.012);
        assertEquals(weather.getDescriptiveWindDirection(), "NorthEast");
        weather = new Weather("objectid", "POINT(-121.0909 35.009893)", 80.01, 2.0f, 27.22, 65.5, 3.01f, "OK", 8.012);
        assertEquals(weather.getDescriptiveWindDirection(), "NorthEast");
    }

    @Test
    public void verifyDirectionDegreesForEast() {
        Weather weather = new Weather("objectid", "POINT(-121.0909 35.009893)", 80.01, 2.0f, 27.22, 68.0, 3.01f, "OK", 8.012);
        assertEquals(weather.getDescriptiveWindDirection(), "East");
        weather = new Weather("objectid", "POINT(-121.0909 35.009893)", 80.01, 2.0f, 27.22, 75.0, 3.01f, "OK", 8.012);
        assertEquals(weather.getDescriptiveWindDirection(), "East");
        weather = new Weather("objectid", "POINT(-121.0909 35.009893)", 80.01, 2.0f, 27.22, 90.0, 3.01f, "OK", 8.012);
        assertEquals(weather.getDescriptiveWindDirection(), "East");
    }

    @Test
    public void verifyCompassDirectionForNorthWest() {
        Weather weather = new Weather("objectid", "POINT(-121.0909 35.009893)", 80.01, 2.0f, 27.22, 315.0, 3.01f, "OK", 8.012);
        assertEquals(weather.getDescriptiveWindDirection(), "NorthWest");
        weather = new Weather("objectid", "POINT(-121.0909 35.009893)", 80.01, 2.0f, 27.22, 335.0, 3.01f, "OK", 8.012);
        assertEquals(weather.getDescriptiveWindDirection(), "NorthWest");
    }

    @Test
    public void testDegreesAreNormalizedBeforeCalculatingCompassDirection() {
        Weather weather = new Weather("objectid", "POINT(-121.0909 35.009893)", 80.01, 2.0f, 27.22, 360.0+275.0, 3.01f, "OK", 8.012);
        assertEquals(weather.getDescriptiveWindDirection(), "West");
        weather = new Weather("objectid", "POINT(-121.0909 35.009893)", 80.01, 2.0f, 27.22, 360.0+220, 3.01f, "OK", 8.012);
        assertEquals(weather.getDescriptiveWindDirection(), "SouthWest");
        weather = new Weather("objectid", "POINT(-121.0909 35.009893)", 80.01, 2.0f, 27.22, 360.0+170, 3.01f, "OK", 8.012);
        assertEquals(weather.getDescriptiveWindDirection(), "South");
    }

    //getDescriptiveWindDirectionAbbreviation

    @Test // N
    public void verifyAbbreviatedDirectionDegreesForNorth() {
        Weather weather = new Weather("objectid", "POINT(-121.0909 35.009893)", 80.01, 2.0f, 27.22, 0.0, 3.01f, "OK", 8.012);
        assertEquals(weather.getDescriptiveWindDirectionAbbreviation(), "N");
        weather = new Weather("objectid", "POINT(-121.0909 35.009893)", 80.01, 2.0f, 27.22,10.0, 3.01f, "OK", 8.012);
        assertEquals(weather.getDescriptiveWindDirectionAbbreviation(), "N");
        weather = new Weather("objectid", "POINT(-121.0909 35.009893)", 80.01, 2.0f, 27.22,360.0, 3.01f, "OK", 8.012);
        assertEquals(weather.getDescriptiveWindDirectionAbbreviation(), "N");
        weather = new Weather("objectid", "POINT(-121.0909 35.009893)", 80.01, 2.0f, 27.22,358.5, 3.01f, "OK", 8.012);
        assertEquals(weather.getDescriptiveWindDirectionAbbreviation(), "N");
    }

    @Test // NNE
    public void verifyAbbreviatedDirectionDegreesForNorthNorthEast() {
        Weather weather = new Weather("objectid", "POINT(-121.0909 35.009893)", 80.01, 2.0f, 27.22,16.875, 3.01f, "OK", 8.012);
        assertEquals(weather.getDescriptiveWindDirectionAbbreviation(), "NNE");
        weather = new Weather("objectid", "POINT(-121.0909 35.009893)", 80.01, 2.0f, 27.22,20.5, 3.01f, "OK", 8.012);
        assertEquals(weather.getDescriptiveWindDirectionAbbreviation(), "NNE");
        weather = new Weather("objectid", "POINT(-121.0909 35.009893)", 80.01, 2.0f, 27.22,25.5, 3.01f, "OK", 8.012);
        assertEquals(weather.getDescriptiveWindDirectionAbbreviation(), "NNE");
        weather = new Weather("objectid", "POINT(-121.0909 35.009893)", 80.01, 2.0f, 27.22,28.125, 3.01f, "OK", 8.012);
        assertEquals(weather.getDescriptiveWindDirectionAbbreviation(), "NNE");
    }

    @Test // NE
    public void verifyAbbreviatedDirectionDegreesForNorthEast() {
        Weather weather = new Weather("objectid", "POINT(-121.0909 35.009893)", 80.01, 2.0f, 27.22,39.375, 3.01f, "OK", 8.012);
        assertEquals(weather.getDescriptiveWindDirectionAbbreviation(), "NE");
        weather = new Weather("objectid", "POINT(-121.0909 35.009893)", 80.01, 2.0f, 27.22,40.5, 3.01f, "OK", 8.012);
        assertEquals(weather.getDescriptiveWindDirectionAbbreviation(), "NE");
        weather = new Weather("objectid", "POINT(-121.0909 35.009893)", 80.01, 2.0f, 27.22,45.5, 3.01f, "OK", 8.012);
        assertEquals(weather.getDescriptiveWindDirectionAbbreviation(), "NE");
        weather = new Weather("objectid", "POINT(-121.0909 35.009893)", 80.01, 2.0f, 27.22,50.625, 3.01f, "OK", 8.012);
        assertEquals(weather.getDescriptiveWindDirectionAbbreviation(), "NE");
    }

    @Test // ENE
    public void verifyAbbreviatedDirectionDegreesForEastNorthEast() {
        Weather weather = new Weather("objectid", "POINT(-121.0909 35.009893)", 80.01, 2.0f, 27.22,61.875, 3.01f, "OK", 8.012);
        assertEquals(weather.getDescriptiveWindDirectionAbbreviation(), "ENE");
        weather = new Weather("objectid", "POINT(-121.0909 35.009893)", 80.01, 2.0f, 27.22,66.9, 3.01f, "OK", 8.012);
        assertEquals(weather.getDescriptiveWindDirectionAbbreviation(), "ENE");
        weather = new Weather("objectid", "POINT(-121.0909 35.009893)", 80.01, 2.0f, 27.22,71.5, 3.01f, "OK", 8.012);
        assertEquals(weather.getDescriptiveWindDirectionAbbreviation(), "ENE");
        weather = new Weather("objectid", "POINT(-121.0909 35.009893)", 80.01, 2.0f, 27.22,73.125, 3.01f, "OK", 8.012);
        assertEquals(weather.getDescriptiveWindDirectionAbbreviation(), "ENE");
    }


    @Test // E
    public void verifyAbbreviatedDirectionDegreesForEast() {
        Weather weather = new Weather("objectid", "POINT(-121.0909 35.009893)", 80.01, 2.0f, 27.22,84.375, 3.01f, "OK", 8.012);
        assertEquals(weather.getDescriptiveWindDirectionAbbreviation(), "E");
        weather = new Weather("objectid", "POINT(-121.0909 35.009893)", 80.01, 2.0f, 27.22,86.9, 3.01f, "OK", 8.012);
        assertEquals(weather.getDescriptiveWindDirectionAbbreviation(), "E");
        weather = new Weather("objectid", "POINT(-121.0909 35.009893)", 80.01, 2.0f, 27.22,91.5, 3.01f, "OK", 8.012);
        assertEquals(weather.getDescriptiveWindDirectionAbbreviation(), "E");
        weather = new Weather("objectid", "POINT(-121.0909 35.009893)", 80.01, 2.0f, 27.22,95.625, 3.01f, "OK", 8.012);
        assertEquals(weather.getDescriptiveWindDirectionAbbreviation(), "E");
    }

    @Test // ESE
    public void verifyAbbreviatedDirectionDegreesForEastSouthEast() {
        Weather weather = new Weather("objectid", "POINT(-121.0909 35.009893)", 80.01, 2.0f, 27.22,106.875, 3.01f, "OK", 8.012);
        assertEquals(weather.getDescriptiveWindDirectionAbbreviation(), "ESE");
        weather = new Weather("objectid", "POINT(-121.0909 35.009893)", 80.01, 2.0f, 27.22,109.9, 3.01f, "OK", 8.012);
        assertEquals(weather.getDescriptiveWindDirectionAbbreviation(), "ESE");
        weather = new Weather("objectid", "POINT(-121.0909 35.009893)", 80.01, 2.0f, 27.22,112.5, 3.01f, "OK", 8.012);
        assertEquals(weather.getDescriptiveWindDirectionAbbreviation(), "ESE");
        weather = new Weather("objectid", "POINT(-121.0909 35.009893)", 80.01, 2.0f, 27.22,118.125, 3.01f, "OK", 8.012);
        assertEquals(weather.getDescriptiveWindDirectionAbbreviation(), "ESE");
    }

    @Test // SE
    public void verifyAbbreviatedDirectionDegreesForSouthEast() {
        Weather weather = new Weather("objectid", "POINT(-121.0909 35.009893)", 80.01, 2.0f, 27.22,129.375, 3.01f, "OK", 8.012);
        assertEquals(weather.getDescriptiveWindDirectionAbbreviation(), "SE");
        weather = new Weather("objectid", "POINT(-121.0909 35.009893)", 80.01, 2.0f, 27.22,132.9, 3.01f, "OK", 8.012);
        assertEquals(weather.getDescriptiveWindDirectionAbbreviation(), "SE");
        weather = new Weather("objectid", "POINT(-121.0909 35.009893)", 80.01, 2.0f, 27.22,138.5, 3.01f, "OK", 8.012);
        assertEquals(weather.getDescriptiveWindDirectionAbbreviation(), "SE");
        weather = new Weather("objectid", "POINT(-121.0909 35.009893)", 80.01, 2.0f, 27.22,140.625, 3.01f, "OK", 8.012);
        assertEquals(weather.getDescriptiveWindDirectionAbbreviation(), "SE");
    }

    @Test // SSE
    public void verifyAbbreviatedDirectionDegreesForSouthSouthEast() {
        Weather weather = new Weather("objectid", "POINT(-121.0909 35.009893)", 80.01, 2.0f, 27.22,151.875, 3.01f, "OK", 8.012);
        assertEquals(weather.getDescriptiveWindDirectionAbbreviation(), "SSE");
        weather = new Weather("objectid", "POINT(-121.0909 35.009893)", 80.01, 2.0f, 27.22,152.9, 3.01f, "OK", 8.012);
        assertEquals(weather.getDescriptiveWindDirectionAbbreviation(), "SSE");
        weather = new Weather("objectid", "POINT(-121.0909 35.009893)", 80.01, 2.0f, 27.22,158.5, 3.01f, "OK", 8.012);
        assertEquals(weather.getDescriptiveWindDirectionAbbreviation(), "SSE");
        weather = new Weather("objectid", "POINT(-121.0909 35.009893)", 80.01, 2.0f, 27.22,163.125, 3.01f, "OK", 8.012);
        assertEquals(weather.getDescriptiveWindDirectionAbbreviation(), "SSE");
    }

    @Test // S
    public void verifyAbbreviatedDirectionDegreesForSouth() {
        Weather weather = new Weather("objectid", "POINT(-121.0909 35.009893)", 80.01, 2.0f, 27.22,174.375, 3.01f, "OK", 8.012);
        assertEquals(weather.getDescriptiveWindDirectionAbbreviation(), "S");
        weather = new Weather("objectid", "POINT(-121.0909 35.009893)", 80.01, 2.0f, 27.22,176.9, 3.01f, "OK", 8.012);
        assertEquals(weather.getDescriptiveWindDirectionAbbreviation(), "S");
        weather = new Weather("objectid", "POINT(-121.0909 35.009893)", 80.01, 2.0f, 27.22,182.5, 3.01f, "OK", 8.012);
        assertEquals(weather.getDescriptiveWindDirectionAbbreviation(), "S");
        weather = new Weather("objectid", "POINT(-121.0909 35.009893)", 80.01, 2.0f, 27.22,185.625, 3.01f, "OK", 8.012);
        assertEquals(weather.getDescriptiveWindDirectionAbbreviation(), "S");
    }

    @Test // SSW
    public void verifyAbbreviatedDirectionDegreesForSouthSouthWest() {
        Weather weather = new Weather("objectid", "POINT(-121.0909 35.009893)", 80.01, 2.0f, 27.22,196.875, 3.01f, "OK", 8.012);
        assertEquals(weather.getDescriptiveWindDirectionAbbreviation(), "SSW");
        weather = new Weather("objectid", "POINT(-121.0909 35.009893)", 80.01, 2.0f, 27.22,198.9, 3.01f, "OK", 8.012);
        assertEquals(weather.getDescriptiveWindDirectionAbbreviation(), "SSW");
        weather = new Weather("objectid", "POINT(-121.0909 35.009893)", 80.01, 2.0f, 27.22,203.5, 3.01f, "OK", 8.012);
        assertEquals(weather.getDescriptiveWindDirectionAbbreviation(), "SSW");
        weather = new Weather("objectid", "POINT(-121.0909 35.009893)", 80.01, 2.0f, 27.22,208.125, 3.01f, "OK", 8.012);
        assertEquals(weather.getDescriptiveWindDirectionAbbreviation(), "SSW");
    }

    @Test // SW
    public void verifyAbbreviatedDirectionDegreesForSouthWest() {
        Weather weather = new Weather("objectid", "POINT(-121.0909 35.009893)", 80.01, 2.0f, 27.22,219.375, 3.01f, "OK", 8.012);
        assertEquals(weather.getDescriptiveWindDirectionAbbreviation(), "SW");
        weather = new Weather("objectid", "POINT(-121.0909 35.009893)", 80.01, 2.0f, 27.22,223.9, 3.01f, "OK", 8.012);
        assertEquals(weather.getDescriptiveWindDirectionAbbreviation(), "SW");
        weather = new Weather("objectid", "POINT(-121.0909 35.009893)", 80.01, 2.0f, 27.22,228.5, 3.01f, "OK", 8.012);
        assertEquals(weather.getDescriptiveWindDirectionAbbreviation(), "SW");
        weather = new Weather("objectid", "POINT(-121.0909 35.009893)", 80.01, 2.0f, 27.22,230.625, 3.01f, "OK", 8.012);
        assertEquals(weather.getDescriptiveWindDirectionAbbreviation(), "SW");
    }

    @Test // WSW
    public void verifyAbbreviatedDirectionDegreesForWestSouthWest() {
        Weather weather = new Weather("objectid", "POINT(-121.0909 35.009893)", 80.01, 2.0f, 27.22,241.875, 3.01f, "OK", 8.012);
        assertEquals(weather.getDescriptiveWindDirectionAbbreviation(), "WSW");
        weather = new Weather("objectid", "POINT(-121.0909 35.009893)", 80.01, 2.0f, 27.22,246.9, 3.01f, "OK", 8.012);
        assertEquals(weather.getDescriptiveWindDirectionAbbreviation(), "WSW");
        weather = new Weather("objectid", "POINT(-121.0909 35.009893)", 80.01, 2.0f, 27.22,250.5, 3.01f, "OK", 8.012);
        assertEquals(weather.getDescriptiveWindDirectionAbbreviation(), "WSW");
        weather = new Weather("objectid", "POINT(-121.0909 35.009893)", 80.01, 2.0f, 27.22,253.125, 3.01f, "OK", 8.012);
        assertEquals(weather.getDescriptiveWindDirectionAbbreviation(), "WSW");
    }

    @Test // W
    public void verifyAbbreviatedDirectionDegreesForWest() {
        Weather weather = new Weather("objectid", "POINT(-121.0909 35.009893)", 80.01, 2.0f, 27.22,264.375, 3.01f, "OK", 8.012);
        assertEquals(weather.getDescriptiveWindDirectionAbbreviation(), "W");
        weather = new Weather("objectid", "POINT(-121.0909 35.009893)", 80.01, 2.0f, 27.22,267.9, 3.01f, "OK", 8.012);
        assertEquals(weather.getDescriptiveWindDirectionAbbreviation(), "W");
        weather = new Weather("objectid", "POINT(-121.0909 35.009893)", 80.01, 2.0f, 27.22,270.5, 3.01f, "OK", 8.012);
        assertEquals(weather.getDescriptiveWindDirectionAbbreviation(), "W");
        weather = new Weather("objectid", "POINT(-121.0909 35.009893)", 80.01, 2.0f, 27.22,275.625, 3.01f, "OK", 8.012);
        assertEquals(weather.getDescriptiveWindDirectionAbbreviation(), "W");
    }

    @Test // WNW
    public void verifyAbbreviatedDirectionDegreesForWestNorthWest() {
        Weather weather = new Weather("objectid", "POINT(-121.0909 35.009893)", 80.01, 2.0f, 27.22,286.875, 3.01f, "OK", 8.012);
        assertEquals(weather.getDescriptiveWindDirectionAbbreviation(), "WNW");
        weather = new Weather("objectid", "POINT(-121.0909 35.009893)", 80.01, 2.0f, 27.22,291.9, 3.01f, "OK", 8.012);
        assertEquals(weather.getDescriptiveWindDirectionAbbreviation(), "WNW");
        weather = new Weather("objectid", "POINT(-121.0909 35.009893)", 80.01, 2.0f, 27.22,295.5, 3.01f, "OK", 8.012);
        assertEquals(weather.getDescriptiveWindDirectionAbbreviation(), "WNW");
        weather = new Weather("objectid", "POINT(-121.0909 35.009893)", 80.01, 2.0f, 27.22,298.125, 3.01f, "OK", 8.012);
        assertEquals(weather.getDescriptiveWindDirectionAbbreviation(), "WNW");
    }

    @Test // NW
    public void verifyAbbreviatedCompassDirectionForNorthWest() {
        Weather weather = new Weather("objectid", "POINT(-121.0909 35.009893)", 80.01, 2.0f, 27.22,309.375, 3.01f, "OK", 8.012);
        assertEquals(weather.getDescriptiveWindDirectionAbbreviation(), "NW");
        weather = new Weather("objectid", "POINT(-121.0909 35.009893)", 80.01, 2.0f, 27.22,311.9, 3.01f, "OK", 8.012);
        assertEquals(weather.getDescriptiveWindDirectionAbbreviation(), "NW");
        weather = new Weather("objectid", "POINT(-121.0909 35.009893)", 80.01, 2.0f, 27.22,317.5, 3.01f, "OK", 8.012);
        assertEquals(weather.getDescriptiveWindDirectionAbbreviation(), "NW");
        weather = new Weather("objectid", "POINT(-121.0909 35.009893)", 80.01, 2.0f, 27.22,320.625, 3.01f, "OK", 8.012);
        assertEquals(weather.getDescriptiveWindDirectionAbbreviation(), "NW");
    }

    @Test //NNW
    public void verifyAbbreviatedDirectionDegreesForNorthNorthWest() {
        Weather weather = new Weather("objectid", "POINT(-121.0909 35.009893)", 80.01, 2.0f, 27.22,331.5, 3.01f, "OK", 8.012);
        assertEquals(weather.getDescriptiveWindDirectionAbbreviation(), "NNW");
        weather = new Weather("objectid", "POINT(-121.0909 35.009893)", 80.01, 2.0f, 27.22,337.5, 3.01f, "OK", 8.012);
        assertEquals(weather.getDescriptiveWindDirectionAbbreviation(), "NNW");
        weather = new Weather("objectid", "POINT(-121.0909 35.009893)", 80.01, 2.0f, 27.22,340.7, 3.01f, "OK", 8.012);
        assertEquals(weather.getDescriptiveWindDirectionAbbreviation(), "NNW");
        weather = new Weather("objectid", "POINT(-121.0909 35.009893)", 80.01, 2.0f, 27.22,342.5, 3.01f, "OK", 8.012);
        assertEquals(weather.getDescriptiveWindDirectionAbbreviation(), "NNW");
    }

    @Test
    public void testDegreesAreNormalizedBeforeCalculatingAbbreviatedCompassDirection() {
        Weather weather = new Weather("objectid", "POINT(-121.0909 35.009893)", 80.01, 2.0f, 27.22,360.0+275.0, 3.01f, "OK", 8.012);
        assertEquals(weather.getDescriptiveWindDirectionAbbreviation(), "W");
        weather = new Weather("objectid", "POINT(-121.0909 35.009893)", 80.01, 2.0f, 27.22,360.0+220, 3.01f, "OK", 8.012);
        assertEquals(weather.getDescriptiveWindDirectionAbbreviation(), "SW");
        weather = new Weather("objectid", "POINT(-121.0909 35.009893)", 80.01, 2.0f, 27.22,360.0+170, 3.01f, "OK", 8.012);
        assertEquals(weather.getDescriptiveWindDirectionAbbreviation(), "S");
    }
}
