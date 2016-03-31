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

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Proxy;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Chat generated by hbm2java
 */
@Entity
@Proxy(lazy=false)
@Table(name = "chat")
@SequenceGenerator(
		name="SEQ_STORE",
		sequenceName="chat_seq",
		allocationSize=1
)
public class Chat extends SADisplayMessageEntity implements SADisplayPersistedEntity {

	private int chatid;
	private UserOrg userorg;
	private int userorgid;
	private CollabRoom collabroom;
	private int collabroomid;
	private Date created;
	private long seqnum;
	private String message;

	public Chat() {
	}

	public Chat(int chatid, UserOrg userorg, CollabRoom collabroom,
			Date created, long seqnum, String message) {
		this.chatid = chatid;
		this.userorg = userorg;
		this.collabroom = collabroom;
		this.created = created;
		this.seqnum = seqnum;
		this.message = message;
	}

	@Id @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_STORE")
	@Column(name = "chatid", unique = true, nullable = false)
	public int getChatid() {
		return this.chatid;
	}

	public void setChatid(int chatid) {
		this.chatid = chatid;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userorgid", insertable=false, updatable = false)
	public UserOrg getUserorg() {
		return this.userorg;
	}

	public void setUserorg(UserOrg userorg) {
		this.userorg = userorg;
	}
	
	@Column(name = "userorgid", nullable = false)
	public int getUserorgid(){
		return this.userorgid;
	}
	
	public void setUserorgid(int userorgid){
		this.userorgid = userorgid;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "collabroomid", insertable=false, updatable = false)
	public CollabRoom getCollabroom() {
		return this.collabroom;
	}

	public void setCollabroom(CollabRoom collabroom) {
		this.collabroom = collabroom;
	}
	
	@Column(name = "collabroomid", nullable = false)
	public int getCollabroomid(){
		return this.collabroomid;
	}
	
	public void setCollabroomid(int collabroomid){
		this.collabroomid = collabroomid;
	}

	@Column(name = "created", nullable = false)
	public Date getCreated() {
		return this.created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	@Column(name = "seqnum", nullable = false)
	public long getSeqnum() {
		return this.seqnum;
	}

	public void setSeqnum(long seqnum) {
		this.seqnum = seqnum;
	}

	@Column(name = "message", nullable = false)
	public String getMessage() {
		return this.message;
	}

	public void setMessage(String message) {
		this.message = encodeForHTML(message);
	}
	
	public JSONObject toJSONObject(){
		try{
			JSONObject object = new JSONObject();
			object.put("chatid", this.chatid);
			object.put("userorgid", this.userorgid);
			object.put("collabroomid", this.collabroomid);
			object.put("created", this.created);
			object.put("seqnum", this.seqnum);
			object.put("message", this.message);
			return object;
		}catch(JSONException e){}
		return null;
	}

}
