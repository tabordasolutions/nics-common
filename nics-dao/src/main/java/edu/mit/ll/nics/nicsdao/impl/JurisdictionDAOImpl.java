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
        String sra = getResponsibilityArea(coordinatesIn4326);
        DirectProtectionArea directProtectionArea = getDirectProtectionArea(coordinatesIn4326);
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
