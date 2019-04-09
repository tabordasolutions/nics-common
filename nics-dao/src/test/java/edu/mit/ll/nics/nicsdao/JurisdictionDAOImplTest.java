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
package edu.mit.ll.nics.nicsdao;

import com.vividsolutions.jts.geom.Coordinate;
import edu.mit.ll.nics.common.entity.DirectProtectionArea;
import edu.mit.ll.nics.common.entity.Jurisdiction;
import edu.mit.ll.nics.nicsdao.impl.JurisdictionDAOImpl;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.Collections;

import org.junit.After;
import org.junit.Test;
import org.mockito.Mockito;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class JurisdictionDAOImplTest {
    private NamedParameterJdbcTemplate jdbcTemplate = mock(NamedParameterJdbcTemplate.class);
    private Coordinate coordinatesIn4326 =  new Coordinate(-121.0099887, 36.089787);
    private DirectProtectionArea directProtectionArea = new DirectProtectionArea("Federal", "agreements", "nwcg unit id", "respond id");
    private String sra = "SRA";
    private JurisdictionDAOImpl jurisdictionDAOImpl = new JurisdictionDAOImpl(jdbcTemplate);

    private String directProtectionAreaQuery = String.format("select dpa_group, agreements, nwcg_unit_id, respond_id from  direct_protection_areas where\n" +
            " ST_Intersects(st_geomfromtext('POINT(%.10f %.10f)', 4326), wkb_geometry)", coordinatesIn4326.x, coordinatesIn4326.y);
    private String responsibilityAreaQuery = String.format("select name from  frap_responsibility_areas where\n" +
            " ST_Intersects(st_geomfromtext('POINT(%.10f %.10f)', 4326), wkb_geometry)", coordinatesIn4326.x, coordinatesIn4326.y);

    @Test
    public void getResponsibilityAreaReturnsResponsibilityAreaGivenValidLocation() throws Exception {
        when(jdbcTemplate.queryForObject(responsibilityAreaQuery, Collections.EMPTY_MAP, String.class)).thenReturn(sra);
        String returnedSRA = jurisdictionDAOImpl.getResponsibilityArea(coordinatesIn4326);
        assertEquals(returnedSRA, sra);
    }

    @Test(expected = Exception.class)
    public void getResponsibilityAreaThrowsExceptionWhenFailsToGetDataFromDB() throws Exception {
        Exception exception = new Exception("Test Exception");
        when(jdbcTemplate.queryForObject(responsibilityAreaQuery, Collections.EMPTY_MAP, String.class)).thenThrow(exception);
        jurisdictionDAOImpl.getResponsibilityArea(coordinatesIn4326);
    }

    @Test
    public void getResponsibilityAreaReturnsEmptyStringWhenNoDataIsFound() throws Exception {
        when(jdbcTemplate.queryForObject(eq(responsibilityAreaQuery), eq(Collections.EMPTY_MAP), any(BeanPropertyRowMapper.class))).thenThrow(new EmptyResultDataAccessException(1));
        String responsibilityArea = jurisdictionDAOImpl.getResponsibilityArea(coordinatesIn4326);
        assertNull(responsibilityArea);
    }

    @Test
    public void getDirectProtectionAreaReturnsValidDirectProtectionAreaDetailsGivenValidLocation() throws Exception {
        when(jdbcTemplate.queryForObject(eq(directProtectionAreaQuery), eq(Collections.EMPTY_MAP), any(BeanPropertyRowMapper.class))).thenReturn(directProtectionArea);
        DirectProtectionArea returnedDPA = jurisdictionDAOImpl.getDirectProtectionArea(coordinatesIn4326);
        assertEquals(returnedDPA, directProtectionArea);
    }

    @Test
    public void getDirectionAreaReturnsNullWhenNoResultsAreFound() throws Exception {
        when(jdbcTemplate.queryForObject(eq(responsibilityAreaQuery), eq(Collections.EMPTY_MAP), any(BeanPropertyRowMapper.class))).thenThrow(new EmptyResultDataAccessException(1));
        assertNull(jurisdictionDAOImpl.getResponsibilityArea(coordinatesIn4326));
    }

    @Test(expected = Exception.class)
    public void getDirectionAreaThrowsExceptionWhenFailsToGetDataFromDB() throws Exception {
        Exception exception = new Exception("Test Exception");
        when(jdbcTemplate.queryForObject(directProtectionAreaQuery, eq(Collections.EMPTY_MAP), any(BeanPropertyRowMapper.class))).thenThrow(exception);
        jurisdictionDAOImpl.getDirectProtectionArea(coordinatesIn4326);
    }

    @Test
    public void getJurisdictionReturnsJurisdictionInstanceGivenValidLocation() throws Exception {
        when(jdbcTemplate.queryForObject(eq(responsibilityAreaQuery), eq(Collections.EMPTY_MAP), eq(String.class))).thenReturn(sra);
        when(jdbcTemplate.queryForObject(eq(directProtectionAreaQuery), eq(Collections.EMPTY_MAP), any(BeanPropertyRowMapper.class))).thenReturn(directProtectionArea);
        Jurisdiction jurisdiction  = jurisdictionDAOImpl.getJurisdiction(coordinatesIn4326);
        assertEquals(jurisdiction.getSra(), sra);
        assertEquals(jurisdiction.getDpa(), directProtectionArea.getDpa());
        assertEquals(jurisdiction.getJurisdiction(), directProtectionArea.getJurisdiction());
    }

    @Test
    public void getJurisdictionReturnsNullWhenResponsibilityAreaAndDirectProtectionAreaDetailsAreNotFound() throws Exception {
        when(jdbcTemplate.queryForObject(eq(responsibilityAreaQuery), eq(Collections.EMPTY_MAP), any(BeanPropertyRowMapper.class))).thenThrow(new EmptyResultDataAccessException(1));
        when(jdbcTemplate.queryForObject(eq(responsibilityAreaQuery), eq(Collections.EMPTY_MAP), any(BeanPropertyRowMapper.class))).thenThrow(new EmptyResultDataAccessException(1));
        assertNull(jurisdictionDAOImpl.getJurisdiction(coordinatesIn4326));
    }

    @Test
    public void getJurisdictionThrowsExceptionWhenFailsToGetDataFromDB() throws Exception {
        Exception exception = new Exception("Test Exception");
        when(jdbcTemplate.queryForObject(responsibilityAreaQuery, Collections.EMPTY_MAP, String.class)).thenThrow(exception);
        jurisdictionDAOImpl.getJurisdiction(coordinatesIn4326);
    }

    @After
    public void tearDown() {
        Mockito.reset(jdbcTemplate);
    }
}
