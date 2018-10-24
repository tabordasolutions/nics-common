package edu.mit.ll.nics.common.entity;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class DirectProtectionAreaTest {
    @Test
    public void getDirectProtectionAreaGroupReturnsCamelCasedValue() {
        DirectProtectionArea directProtectionArea = new DirectProtectionArea("LOCAL", "contract county", "unitid", "respondid");
        assertEquals(directProtectionArea.getDpa(), "Local DPA");
    }

    @Test
    public void getDirectProtectionAreaGroupReturnsNull() {
        DirectProtectionArea directProtectionArea = new DirectProtectionArea(null, "contract county", "unitid", "respondid");
        assertNull(directProtectionArea.getDpa());
    }

    @Test
    public void getJurisdictionReturnsContractCountyIfDPAFallsUnderContractCounty() {
        DirectProtectionArea directProtectionArea = new DirectProtectionArea("LOCAL", "contract county", "unitid", "respondid");
        assertEquals("Contract County", directProtectionArea.getJurisdiction());
    }

    @Test
    public void getJurisdictionReturnsNWCGUnitIdIfDPAIsNotContractCounty() {
        DirectProtectionArea directProtectionArea = new DirectProtectionArea("LOCAL", "Not C county", "unitid", "respondid");
        assertEquals("unitid", directProtectionArea.getJurisdiction());
    }

    @Test
    public void getJurisdictionReturnsRespondIdIfDPAIsNotContractCountyAndNWCGUnitIdIsNotAvailable() {
        DirectProtectionArea directProtectionArea = new DirectProtectionArea("LOCAL", null, "", "respondid");
        assertEquals("respondid", directProtectionArea.getJurisdiction());
    }

    @Test
    public void getJurisdictionReturnsNullIfDPAIsNotContractCountyAndNWCGUnitIDAndRespondIdAreNotAvailable() {
        DirectProtectionArea directProtectionArea = new DirectProtectionArea("LOCAL", null, null, null);
        assertNull(directProtectionArea.getJurisdiction());
    }
}
