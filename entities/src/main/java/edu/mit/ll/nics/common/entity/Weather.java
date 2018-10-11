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
