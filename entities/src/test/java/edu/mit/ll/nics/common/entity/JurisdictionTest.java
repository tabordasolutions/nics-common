package edu.mit.ll.nics.common.entity;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class JurisdictionTest {
    private String sra = "sra";
    private DirectProtectionArea directProtectionArea = new DirectProtectionArea("dpa", "contract county", "unitid", "respondid");
    private Jurisdiction jurisdiction = new Jurisdiction(sra, directProtectionArea);

    @Test
    public void getSRAReturnsResponsibilityArea() {
        assertEquals(jurisdiction.getSra(), sra);
    }

    @Test
    public void getDPAReturnsDirectProtectionAreaGroup() {
        assertEquals(jurisdiction.getDpa(), directProtectionArea.getDpa());
    }

    @Test
    public void getDPAReturnsNullGivenDirectProtectionAreaInstanceIsNull() {
        Jurisdiction jurisdiction = new Jurisdiction(sra, null);
        assertNull(jurisdiction.getDpa());
    }

    @Test
    public void getJurisdictionReturnsDirectProtectionAreaJurisdiction() {
        assertEquals("Contract County", jurisdiction.getJurisdiction());
    }

    @Test
    public void getJurisdictionAreaReturnsNullWhenDirectProtectionAreaIsNotAvailable() {
        Jurisdiction jurisdiction = new Jurisdiction(sra, null);
        assertNull(jurisdiction.getJurisdiction());
    }
}