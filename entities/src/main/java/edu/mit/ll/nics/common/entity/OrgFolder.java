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

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Proxy;
import org.json.JSONObject;

import edu.mit.ll.nics.common.entity.datalayer.Folder;

/**
 * OrgFolder generated by hbm2java
 */
@Entity
@Proxy(lazy=false)
@Table(name = "orgfolder")
public class OrgFolder extends SADisplayMessageEntity implements SADisplayPersistedEntity {

	private int orgfolderid;
	private Org org;
	private Folder folder;
	private int orgid;
	private String folderid;

	public OrgFolder() {
	}

	public OrgFolder(int orgfolderid, Org org) {
		this.orgfolderid = orgfolderid;
		this.org = org;
	}

	public OrgFolder(int orgfolderid, Org org,
			Folder folder) {
		this.orgfolderid = orgfolderid;
		this.org = org;
		this.folder = folder;
	}

	@Id @GeneratedValue
	@Column(name = "orgfolderid", unique = true, nullable = false)
	public int getOrgfolderid() {
		return this.orgfolderid;
	}

	public void setOrgfolderid(int orgfolderid) {
		this.orgfolderid = orgfolderid;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "orgid", insertable = false, updatable = false)
	public Org getOrg() {
		return this.org;
	}

	public void setOrg(Org org) {
		this.org = org;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "folderid", insertable = false, updatable = false)
	public Folder getFolder() {
		return this.folder;
	}

	public void setFolder(Folder folder) {
		this.folder = folder;
	}
	
	@Column(name = "orgid", nullable = false)
    public int getOrgid() {
        return this.orgid;
    }

    public void setOrgid(int orgid) {
        this.orgid = orgid;
    }
    
    @Column(name = "folderid", nullable = false)
    public String getFolderid() {
        return this.folderid;
    }

    public void setFolderid(String folderid) {
        this.folderid = folderid;
    }
    
    public JSONObject toJSONObject(){
		JSONObject obj = new JSONObject();
		try{
			obj.put("orgid", this.orgid);
			obj.put("folderid", this.folderid);
			obj.put("orgfolderid", this.orgfolderid);
		}catch(Exception e){}
		return obj;
	}

}
