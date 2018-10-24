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
