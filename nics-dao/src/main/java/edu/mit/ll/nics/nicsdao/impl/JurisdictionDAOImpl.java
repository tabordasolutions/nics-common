package edu.mit.ll.nics.nicsdao.impl;

import com.vividsolutions.jts.geom.Coordinate;
import edu.mit.ll.nics.common.entity.DirectProtectionArea;
import edu.mit.ll.nics.common.entity.Jurisdiction;
import edu.mit.ll.nics.nicsdao.JurisdictionDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.Collections;
import java.util.Date;

public class JurisdictionDAOImpl implements JurisdictionDAO {
    private Logger log = LoggerFactory.getLogger(JurisdictionDAOImpl.class);

    private NamedParameterJdbcTemplate jdbcTemplate = null;

    public JurisdictionDAOImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Jurisdiction getJurisdiction(Coordinate coordinatesIn4326) {
        Date startTime = new Date();
        String sra = getResponsibilityArea(coordinatesIn4326);
        DirectProtectionArea directProtectionArea = getDirectProtectionArea(coordinatesIn4326);
        log.info("Time took to get jurisdiction data : " + (new Date().getTime() - startTime.getTime()));
        return (sra == null && directProtectionArea == null) ? null : new Jurisdiction(sra, directProtectionArea);
    }

    public DirectProtectionArea getDirectProtectionArea(Coordinate coordinatesIn4326) {
        String query = String.format("select dpa_group, agreements, nwcg_unit_id, respond_id from  direct_protection_areas where\n" +
                " ST_Intersects(st_geomfromtext('POINT(%.10f %.10f)', 4326), wkb_geometry)", coordinatesIn4326.x, coordinatesIn4326.y);
        DirectProtectionArea directProtectionArea = null;
        try{
            directProtectionArea = (DirectProtectionArea) jdbcTemplate.queryForObject(query, Collections.EMPTY_MAP, new BeanPropertyRowMapper(DirectProtectionArea.class));
        } catch (EmptyResultDataAccessException e) {
            log.error(String.format("No Direct Protection Area data found for location %s", coordinatesIn4326.toString()), e);
        }
        return directProtectionArea;
    }

    public String getResponsibilityArea(Coordinate coordinatesIn4326) {
        Date startTime = new Date();
        String query = String.format("select name from  frap_responsibility_areas where\n" +
                " ST_Intersects(st_geomfromtext('POINT(%.10f %.10f)', 4326), wkb_geometry)", coordinatesIn4326.x, coordinatesIn4326.y);
        String responsibilityArea = null;
        try{
            responsibilityArea = (String) jdbcTemplate.queryForObject(query, Collections.EMPTY_MAP, String.class);
        } catch (EmptyResultDataAccessException e) {
            log.error(String.format("No Direct Protection Area data found for location %s", coordinatesIn4326.toString()), e);
        }
        return responsibilityArea;
    }
}
