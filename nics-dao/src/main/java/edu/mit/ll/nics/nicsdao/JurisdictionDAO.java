package edu.mit.ll.nics.nicsdao;

import com.vividsolutions.jts.geom.Coordinate;
import edu.mit.ll.nics.common.entity.Jurisdiction;

public interface JurisdictionDAO {

    public Jurisdiction getJurisdiction(Coordinate coordinatesIn4326);
}
