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

// Generated Oct 7, 2011 8:20:22 AM by Hibernate Tools 3.4.0.CR1

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
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

import org.hibernate.annotations.Proxy;
import org.json.JSONException;
import org.json.JSONObject;

import edu.mit.ll.nics.common.entity.datalayer.Datalayer;
import edu.mit.ll.nics.common.entity.datalayer.Datalayersource;
import edu.mit.ll.nics.common.entity.datalayer.Document;

/**
 * Usersession generated by hbm2java
 */
@Entity
@Proxy(lazy=false)
@Table(name = "usersession")
@SequenceGenerator(
	name="SEQ_STORE",
	sequenceName="user_session_seq",
	allocationSize=1
)
public class Usersession extends SADisplayMessageEntity implements SADisplayPersistedEntity, java.io.Serializable {

	private int usersessionid;
	private int userorgid;
	private String sessionid;
	private Date loggedin;
	private Date loggedout;

	public Usersession() {
	}

	public Usersession(int usersessionid, UserOrg userorg, String sessionid,
			Date loggedin) {
		this.usersessionid = usersessionid;
		this.sessionid = sessionid;
		this.loggedin = loggedin;
	}

	public Usersession(int usersessionid, UserOrg userorg, String sessionid,
			Date loggedin, Date loggedout, Set<CollabRoom> collabrooms, Set<Feature> features,
			Set<CurrentUserSession> currentusersessions, Set<Chat> chats, 
			Set<Log> logs, Set<Incident> incidents, Set<Form> forms) {
		this.usersessionid = usersessionid;
		this.sessionid = sessionid;
		this.loggedin = loggedin;
		this.loggedout = loggedout;
	}

	@Id
	@Column(name = "usersessionid", unique = true, nullable = false)
	public int getUsersessionid() {
		return this.usersessionid;
	}

	public void setUsersessionid(int usersessionid) {
		this.usersessionid = usersessionid;
	}
	
	@Column(name = "userorgid", nullable = false)
	public int getUserorgid(){
		return this.userorgid;
	}
	
	public void setUserorgid(int userorgid){
		this.userorgid = userorgid;
	}

	@Column(name = "sessionid", nullable = false, length = 128)
	public String getSessionid() {
		return this.sessionid;
	}

	public void setSessionid(String sessionid) {
		this.sessionid = sessionid;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "loggedin", nullable = false, length = 29)
	public Date getLoggedin() {
		return this.loggedin;
	}

	public void setLoggedin(Date loggedin) {
		this.loggedin = loggedin;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "loggedout", length = 29)
	public Date getLoggedout() {
		return this.loggedout;
	}

	public void setLoggedout(Date loggedout) {
		this.loggedout = loggedout;
	}

	@Override
	public JSONObject toJSONObject() throws JSONException {
		JSONObject object = new JSONObject();
		object.put("usersessionid", this.usersessionid);
		object.put("userorgid", this.userorgid);
		object.put("sessionid", this.sessionid);
		object.put("loggedin", this.loggedin.toString());
		return object;
	}

}
