/**
 * Copyright (c) 2008-2016, Massachusetts Institute of Technology (MIT)
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

// Generated Sep 30, 2011 1:24:44 PM by Hibernate Tools 3.4.0.CR1

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Proxy;
import org.json.JSONObject;


/**
 * Org generated by hbm2java
 */
@Entity
@Proxy(lazy=false)
@Table(name = "org")
@SequenceGenerator(
		name="SEQ_STORE",
		sequenceName="org_seq",
		allocationSize=1
	)
public class Org extends SADisplayMessageEntity implements SADisplayPersistedEntity {

	private int orgId;
	private String name;
	private String county;
	private String state;
	private String timezone;
	private String prefix;
	private String distribution;
	private double defaultlatitude;
	private double defaultlongitude;
	private Integer parentorgid;
	private String country;
	private Date created;
	private Set<UserOrg> userorgs = new HashSet<UserOrg>(0);
	private Set<OrgOrgType> orgTypes = new HashSet<OrgOrgType>(0);

	public Org() {
	}

	public Org(int orgId, String name, String prefix, double defaultlatitude,
			double defaultlongitude, Date created) {
		this.orgId = orgId;
		this.name = name;
		this.prefix = prefix;
		this.defaultlatitude = defaultlatitude;
		this.defaultlongitude = defaultlongitude;
		this.created = created;
	}

	public Org(int orgId, String name, String county, String state,
			String timezone, String prefix, String distribution,
			double defaultlatitude, double defaultlongitude,
			Integer parentorgid, String country, Date created, Set userorgs) {
		this.orgId = orgId;
		this.name = name;
		this.county = county;
		this.state = state;
		this.timezone = timezone;
		this.prefix = prefix;
		this.distribution = distribution;
		this.defaultlatitude = defaultlatitude;
		this.defaultlongitude = defaultlongitude;
		this.parentorgid = parentorgid;
		this.country = country;
		this.created = created;
		this.userorgs = userorgs;
	}

	@Id @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_STORE")
	@Column(name = "orgid", unique = true, nullable = false)
	public int getOrgId() {
		return this.orgId;
	}

	public void setOrgId(int orgId) {
		this.orgId = orgId;
	}

	@Column(name = "name", nullable = false, length = 50)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = encodeForHTML(name);
	}

	@Column(name = "county", length = 35)
	public String getCounty() {
		return this.county;
	}

	public void setCounty(String county) {
		this.county = encodeForHTML(county);
	}

	@Column(name = "state", length = 35)
	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = encodeForHTML(state);
	}

	@Column(name = "timezone", length = 100)
	public String getTimezone() {
		return this.timezone;
	}

	public void setTimezone(String timezone) {
		this.timezone = timezone;
	}

	@Column(name = "prefix", nullable = false, length = 20)
	public String getPrefix() {
		return this.prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = encodeForHTML(prefix);
	}

	@Column(name = "distribution", length = 300)
	public String getDistribution() {
		return this.distribution;
	}

	public void setDistribution(String distribution) {
		/*StringBuffer validEmails = new StringBuffer();
		if(distribution != null && !StringUtils.isEmpty(distribution)){
			String [] emails = distribution.split(",");
			for(int i=0; i<emails.length; i++){
				if(EntityEncoder.validateEmailAddress(emails[i])){
					if(!StringUtils.isEmpty(validEmails.toString())){
						validEmails.append(",");
					}
					validEmails.append(emails[i]);
				}
			}
			this.distribution = validEmails.toString();
		}else{
			this.distribution = distribution;
		}*/
		this.distribution = distribution;
	}

	@Column(name = "defaultlatitude", nullable = false, precision = 17, scale = 17)
	public double getDefaultlatitude() {
		return this.defaultlatitude;
	}

	public void setDefaultlatitude(double defaultlatitude) {
		this.defaultlatitude = defaultlatitude;
	}

	@Column(name = "defaultlongitude", nullable = false, precision = 17, scale = 17)
	public double getDefaultlongitude() {
		return this.defaultlongitude;
	}

	public void setDefaultlongitude(double defaultlongitude) {
		this.defaultlongitude = defaultlongitude;
	}

	@Column(name = "parentorgid")
	public Integer getParentorgid() {
		return this.parentorgid;
	}

	public void setParentorgid(Integer parentorgid) {
		this.parentorgid = parentorgid;
	}

	@Column(name = "country", length = 30)
	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = encodeForHTML(country);
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created", nullable = false, length = 29)
	public Date getCreated() {
		return this.created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "org")
	public Set<UserOrg> getUserorgs() {
		return this.userorgs;
	}

	public void setUserorgs(Set<UserOrg> userorgs) {
		this.userorgs = userorgs;
	}
	
	public Set<OrgOrgType> getOrgTypes() {
		return this.orgTypes;
	}

	public void setOrgTypes(Set<OrgOrgType> orgTypes) {
		this.orgTypes = orgTypes;
	}
	
	public JSONObject toJSONObject(){
		JSONObject obj = new JSONObject();
		try{
			obj.put("orgId", this.orgId);
			obj.put("name", this.name);
			obj.put("county", this.county);
			obj.put("state", this.state);
			obj.put("timezone", this.timezone);
			obj.put("prefix", this.prefix);
			obj.put("distribution", this.distribution);
			obj.put("defaultlatitude", this.defaultlatitude);
			obj.put("defaultlongitude", this.defaultlongitude);
			obj.put("parentorgid", this.parentorgid);
			obj.put("country", this.country);
			obj.put("created", this.created);
		}catch(Exception e){}
		return obj;
	}

}
