package edu.mit.ll.nics.nicsdao;

import com.vividsolutions.jts.geom.Coordinate;
import edu.mit.ll.nics.common.entity.Weather;

public interface WeatherDAO {

    public Weather getWeatherDataFromLocation(Coordinate location, double rangeInKilometers);
}
