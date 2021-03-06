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
package edu.mit.ll.nics.common.entity;

// Generated Aug 19, 2013 3:24:55 PM by Hibernate Tools 4.0.0

import java.util.HashSet;
import java.util.Set;

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

import org.hibernate.annotations.Proxy;
import org.json.JSONException;
import org.json.JSONObject;


/**
 * Unit generated by hbm2java
 */
@Entity
@Proxy(lazy=false)
@Table(name = "unit", schema = "public")
@SequenceGenerator(
	name="SEQ_STORE",
	sequenceName="unit_seq",
	allocationSize=1
)
public class Unit extends SADisplayMessageEntity implements SADisplayPersistedEntity {

	private long id;
	private Incident incident;
	private int incidentid;
	private CollabRoom collabroom;
	private int collabroomid;
	private String unitname;
	private Set<Assignment> assignments = new HashSet<Assignment>(0);

	public Unit() {
	}

	public Unit(long id, Incident incident, CollabRoom collabroom,
			String unitname) {
		this.id = id;
		this.incident = incident;
		this.collabroom = collabroom;
		this.unitname = unitname;
	}

	public Unit(long id, Incident incident, CollabRoom collabroom,
			String unitname, Set<Assignment> assignments) {
		this.id = id;
		this.incident = incident;
		this.collabroom = collabroom;
		this.unitname = unitname;
		this.assignments = assignments;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_STORE")
	@Column(name = "id", unique = true, nullable = false)
	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "incident_id", insertable=false, updatable=false)
	public Incident getIncident() {
		return this.incident;
	}

	public void setIncident(Incident incident) {
		this.incident = incident;
		if(this.incident != null) {
			this.setIncidentid(this.incident.getIncidentid());
		}
	}
	
	@Column(name = "incident_id", nullable = false)
	public int getIncidentid(){
		return this.incidentid;
	}
	
	public void setIncidentid(int incidentid){
		this.incidentid = incidentid;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "collabroom_id", insertable=false, updatable=false)
	public CollabRoom getCollabroom() {
		return this.collabroom;
	}

	public void setCollabroom(CollabRoom collabroom) {
		this.collabroom = collabroom;
		if(this.collabroom != null) {
			this.setCollabroomid(this.collabroom.getCollabRoomId());
		}
	}
	
	@Column(name = "collabroom_id", nullable = false)
	public int getCollabroomid() {
		return this.collabroomid;
	}
	
	public void setCollabroomid(int collabroomid) {
		this.collabroomid = collabroomid;
	}

	@Column(name = "unitname", nullable = false)
	public String getUnitname() {
		return this.unitname;
	}

	public void setUnitname(String unitname) {
		this.unitname = unitname;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "unit")
	public Set<Assignment> getAssignments() {
		return this.assignments;
	}

	public void setAssignments(Set<Assignment> assignments) {
		this.assignments = assignments;
	}

	@Override
	public JSONObject toJSONObject() {
		try {
			String collabRoomName = "";
			String incidentName = "";
			String incidentNumber = "";
			if (this.getCollabroom() != null) {
				collabRoomName = this.getCollabroom().getName();
			}
			if (this.getIncident() != null) {
				incidentName = this.getIncident().getIncidentname();
				incidentNumber = this.getIncident().getIncidentnumber();
			}
			JSONObject object = new JSONObject();
			object.put("id", this.id);
			object.put("incidentId", this.incidentid);
			object.put("incidentName", incidentName);
			object.put("incidentNumber", incidentNumber);
			object.put("collabroomId", this.collabroomid);
			object.put("collabroomName", collabRoomName);
			object.put("unitName", this.unitname);
			
			return object;
		} catch (JSONException e) {}
		
		return null;
	}

}
