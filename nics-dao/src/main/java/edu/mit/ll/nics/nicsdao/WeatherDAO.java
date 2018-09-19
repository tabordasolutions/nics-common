package edu.mit.ll.nics.nicsdao;

import edu.mit.ll.nics.common.entity.Weather;

public interface WeatherDAO {

    public Weather getWeatherDataFromLocation(double[] location, double rangeInKilometers);
}
