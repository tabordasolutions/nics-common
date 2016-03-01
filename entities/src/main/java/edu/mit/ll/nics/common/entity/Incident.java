/**
 * Copyright (c) 2008-2015, Massachusetts Institute of Technology (MIT)
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
package edu.mit.ll.nics.common.entity;

// Generated Oct 7, 2011 8:20:22 AM by Hibernate Tools 3.4.0.CR1
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.Proxy;
import org.hibernate.annotations.Type;

import com.vividsolutions.jts.geom.Polygon;






//import edu.mit.ll.nics.common.entity.datalayer.DatalayerIncident;
import edu.mit.ll.nics.common.entity.datalayer.DocumentIncident;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Incident generated by hbm2java
 */
@Entity
@Proxy(lazy = false)
@Table(name = "incident")
@SequenceGenerator(name = "SEQ_STORE",
sequenceName = "incident_seq",
allocationSize = 1)
public class Incident extends SADisplayMessageEntity implements SADisplayPersistedEntity {

    private Integer incidentid;
    private Integer parentincidentid;
    private Usersession usersession;
    private Integer usersessionid;
    private String incidentname;
    private double lat;
    private double lon;
    private Date created;
    private Date lastupdate;
    private boolean active;
    private String folder;
    private String description;
    private String bounds;
    private Integer workspaceid;
    private Set<CollabRoom> collabrooms = new HashSet<CollabRoom>(0);
    private Set<IncidentIncidentType> incidentIncidenttypes = new HashSet<IncidentIncidentType>(0);
    
    //	Used in nics-web to display tree structure for multi-incident-view
    @Transient
    private List<Incident> children = null;
    private Boolean leaf = true;
    
    public Incident() {
    }

    public Incident(Integer incidentid, String incidentname, double lat,
            double lon, Date created, Date lastupdate, boolean active, String folder) {
        this.incidentid = incidentid;
        this.incidentname = incidentname;
        this.lat = lat;
        this.lon = lon;
        this.created = created;
        this.active = active;
        this.folder = folder;
        this.lastupdate = lastupdate;
    }

    public Incident(Integer incidentid, Usersession usersession,
            String incidentname, double lat, double lon, Date created, Date lastupdate,
            boolean active, String folder, String bounds, Set<CollabRoom> collabrooms,
            Set<IncidentIncidentType> incidentIncidenttypes, Set<Form> forms) {
        this.incidentid = incidentid;
        this.usersession = usersession;
        this.incidentname = incidentname;
        this.lat = lat;
        this.lon = lon;
        this.created = created;
        this.active = active;
        this.folder = folder;
        this.bounds = bounds;
        this.collabrooms = collabrooms;
        this.incidentIncidenttypes = incidentIncidenttypes;
        this.lastupdate = lastupdate;
    }

    @Id
    //@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_STORE")
    @Column(name = "incidentid", unique = true, nullable = false)
    public Integer getIncidentid() {
        return this.incidentid;
    }

    public void setIncidentid(Integer incidentid) {
        this.incidentid = incidentid;
    }

    @Column(name = "parentincidentid")
    public Integer getParentincidentid() {
        return this.parentincidentid;
    }

    public void setParentincidentid(Integer parentincidentid) {
        this.parentincidentid = parentincidentid;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "usersessionid", insertable=false, updatable = false)
    public Usersession getUsersession() {
        return this.usersession;
    }

    public void setUsersession(Usersession usersession) {
        this.usersession = usersession;
    }

    @Column(name = "incidentname", nullable = false, length = 50)
    public String getIncidentname() {
        return this.incidentname;
    }

    public void setIncidentname(String incidentname) {
        this.incidentname = encodeForHTML(incidentname);
    }

    @Column(name = "lat", nullable = false, precision = 17, scale = 17)
    public double getLat() {
        return this.lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    @Column(name = "lon", nullable = false, precision = 17, scale = 17)
    public double getLon() {
        return this.lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created", nullable = false, length = 29)
    public Date getCreated() {
        return this.created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "lastupdate", length = 29)
    public Date getLastUpdate() {
        return this.lastupdate;
    }

    public void setLastUpdate(Date lastupdate) {
        this.lastupdate = lastupdate;
    }

    @Column(name = "active", nullable = false)
    public boolean getActive() {
        return this.active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Column(name = "folder", nullable = false, length = 50)
    public String getFolder() {
        return this.folder;
    }

    public void setFolder(String folder) {
        this.folder = folder;
    }
    
    @Column(name = "description", length =250)
    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "bounds", columnDefinition = "Geometry")
    @Type(type = "org.hibernate.spatial.GeometryType")
    public String getBounds() {
        return this.bounds;
    }

    public void setBounds(String bounds) {
        this.bounds = bounds;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "incident")
    public Set<CollabRoom> getCollabrooms() {
        return this.collabrooms;
    }

    public void setCollabrooms(Set<CollabRoom> collabrooms) {
        this.collabrooms = collabrooms;
    }

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "incident")
    public Set<IncidentIncidentType> getIncidentIncidenttypes() {
        return this.incidentIncidenttypes;
    }

    public void setIncidentIncidenttypes(Set<IncidentIncidentType> incidentIncidenttypes) {
        this.incidentIncidenttypes = incidentIncidenttypes;
    }

    /**
     * @return the usersession id
     */
    @Column(name = "usersessionid", nullable = false)
    public Integer getUsersessionid() {
        return this.usersessionid;
    }

    /**
     * @param room the usersessionid
     */
    public void setUsersessionid(Integer id) {
        this.usersessionid = id;
    }
    
    /**
     * @return the workspace id
     */
    @Column(name = "workspaceid", nullable = false)
    public Integer getWorkspaceid() {
        return this.workspaceid;
    }

    /**
     * @param id workspaceid
     */
    public void setWorkspaceid(Integer id) {
        this.workspaceid = id;
    }
    
    /**
	* @return the incident children
	*/
    @Transient
	public List<Incident> getChildren() {
		return this.children;
	}
	
	/**
	* @param children incident
	*/
    @Transient
	public void setChildren(List<Incident> children) {
		if(this.children == null){
			this.children = new ArrayList<Incident>();
		}
		this.children = children;
	}
	
   /**
	* @return leaf boolean value
	*/
	public Boolean getLeaf() {
		return this.leaf;
	}
	
	/**
	* @param leaf boolean value
	*/
	public void setLeaf(Boolean leaf) {
		this.leaf = leaf;
	}
    

    @Override
    public JSONObject toJSONObject() {
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        fmt.setTimeZone(TimeZone.getTimeZone("UTC")); // time

        JSONObject json = new JSONObject();
        JSONArray jarr = new JSONArray();
        JSONArray jarr2 = new JSONArray();
        try {
            json.put("usersessionid", this.usersessionid);
            json.put("incidentname", this.incidentname);
            json.put("lat", this.lat);
            json.put("lon", this.lon);
            json.put("created", fmt.format(this.created));
            json.put("active", this.active);
            json.put("folder", this.folder);
            json.put("lastupdate", (this.lastupdate == null ? this.lastupdate : fmt.format(this.lastupdate)));
            json.put("bounds", this.bounds);
            json.put("parentincidentid", this.parentincidentid);
            json.put("description", this.description);
            
            json.put("incidentid", this.incidentid);
            // loop incident types
            Iterator<IncidentIncidentType> i = this.incidentIncidenttypes.iterator();
            while (i.hasNext())
            {	// put in json array
            	IncidentIncidentType it = i.next();
            	jarr.put(it.toJSONObject());
            }
            // put array in json object
            json.put("incidenttypes", jarr);
            if(!this.children.isEmpty()){
            
	            Iterator<Incident> j = this.children.iterator();
	            while (j.hasNext())
				{	// put in json array
	            	Incident it = j.next();
	            	jarr2.put(it.toJSONObject());
				}
				// put array in json object
				json.put("children", jarr2);
				json.put("leaf",false);
            }
            else{
            	json.put("chilren","null");
            	json.put("leaf",true);
            }
            
        } catch (JSONException ex) {
            Logger.getLogger(Incident.class.getName()).log(Level.SEVERE, null, ex);
        }
        return json;
    }
}
