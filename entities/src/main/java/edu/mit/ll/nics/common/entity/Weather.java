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

import org.apache.commons.lang.builder.ToStringBuilder;

public class Weather {
    private String objectId;
    private String location;
    private Double airTemperature;
    private Float windSpeed;
    private Double windDirection;
    private Float humidity;
    private String qcStatus;
    private Double distance;
    private static final String[] COMPASS_DIRECTIONS = {"North", "NorthEast", "East", "SouthEast", "South", "SouthWest", "West", "NorthWest", "North"};
    private static final double DEGREES_PER_COMPASS_DIRECTION = 45;
    private static final int TOTAL_DEGREES = 360;

    public Weather(String objectId, String location, Double airTemperature,
            Float windSpeed, Double windDirection, Float humidity,
            String qcStatus, Double distance) {
        this.objectId = objectId;
        this.location = location;
        this.airTemperature = airTemperature;
        this.windSpeed = windSpeed;
        this.windDirection = windDirection;
        this.humidity = humidity;
        this.qcStatus = qcStatus;
        this.distance = distance;
    }

    public String getObjectId() {
        return objectId;
    }

    public String getLocation() {
        return location;
    }

    public Double getAirTemperature() {
        return airTemperature;
    }

    public Float getWindSpeed() {
        return windSpeed;
    }

    public Double getWindDirection() {
        return windDirection;
    }

    public String getDescriptiveWindDirection() {
        if(this.getWindDirection() == null) {
            return "Not Available";
        }
        double normalizedDirectionInDegrees = this.getWindDirection() % TOTAL_DEGREES;
        long compassDirectionIndex = Math.round(normalizedDirectionInDegrees/DEGREES_PER_COMPASS_DIRECTION);
        return COMPASS_DIRECTIONS[(int) compassDirectionIndex];
    }

    public Float getHumidity() {
        return humidity;
    }

    public String getQcStatus() {
        return qcStatus;
    }

    public Double getDistance() {
        return distance;
    }

    public Double getDistanceInMiles() {
        return distance * 0.621371;
    }

    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
