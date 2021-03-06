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
package edu.mit.ll.nics.common.entity.datalayer;

// Generated Nov 30, 2011 2:53:26 PM by Hibernate Tools 3.4.0.CR1
//import edu.mit.ll.nics.common.entity.EntityEncoder;
import edu.mit.ll.nics.common.entity.SADisplayMessageEntity;
import edu.mit.ll.nics.common.entity.SADisplayPersistedEntity;

import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Datasource generated by hbm2java
 */
@Entity
@Table(name = "datasource", schema = "public")
public class Datasource extends SADisplayMessageEntity implements SADisplayPersistedEntity {

    private String datasourceid;
    private Datasourcetype datasourcetype;
    private Integer datasourcetypeid;
    private String internalurl;
    private String externalurl;
    private String displayname;
    private String username;
    private String password;
    private boolean secure = false;
    private Set<Document> documents = new HashSet<Document>(0);
    private Set<Datalayersource> datalayersources = new HashSet<Datalayersource>(
            0);

    public Datasource() {
    }

    public Datasource(String datasourceid, Datasourcetype datasourcetype,
            String internalurl, String externalurl, Set<Document> documents,
            Set<Datalayersource> datalayersources, String displayname) {
        this.datasourceid = datasourceid;
        this.datasourcetype = datasourcetype;
        this.internalurl = internalurl;
        this.externalurl = externalurl;
        this.documents = documents;
        this.displayname = displayname;
        this.datalayersources = datalayersources;
    }

    @Id
    @Column(name = "datasourceid", unique = true, nullable = false)
    public String getDatasourceid() {
        return this.datasourceid;
    }

    public void setDatasourceid(String datasourceid) {
        this.datasourceid = datasourceid;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "datasourcetypeid", insertable = false, updatable = false)
    public Datasourcetype getDatasourcetype() {
        return this.datasourcetype;
    }

    public void setDatasourcetype(Datasourcetype datasourcetype) {
        this.datasourcetype = datasourcetype;
    }

    @Column(name = "internalurl", nullable = false, length = 256)
    public String getInternalurl() {
        return this.internalurl;
    }

    public void setInternalurl(String internalurl) {
        //this.internalurl = EntityEncoder.validateURL(internalurl);
    	this.internalurl = internalurl;
    }

    @Column(name = "externalurl", length = 256)
    public String getExternalurl() {
        return this.externalurl;
    }

    public void setExternalurl(String externalurl) {
       //this.externalurl = EntityEncoder.validateURL(externalurl);
    	this.externalurl = externalurl;
    }
    
    @Column(name = "displayname", length = 256)
    public String getDisplayname() {
        return this.displayname;
    }

    public void setDisplayname(String displayname) {
        this.displayname = encodeForHTML(displayname);
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "datasource")
    public Set<Document> getDocuments() {
        return this.documents;
    }

    public void setDocuments(Set<Document> documents) {
        this.documents = documents;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "datasource")
    public Set<Datalayersource> getDatalayersources() {
        return this.datalayersources;
    }

    public void setDatalayersources(Set<Datalayersource> datalayersources) {
        this.datalayersources = datalayersources;
    }

    @Column(name = "datasourcetypeid", nullable = false)
    public Integer getDatasourcetypeid() {
        return datasourcetypeid;
    }

    public void setDatasourcetypeid(Integer datasourcetypeid) {
        this.datasourcetypeid = datasourcetypeid;
    }
    
    public void setUsername(String username){
    	this.username = username;
    }
    
    public String getUsername(){
    	return this.username;
    }
    
    public void setPassword(String password){
    	this.password = password;
    }
    
    public String getPassword(){
    	return this.password;
    }
    
    public boolean getSecure(){
    	return this.secure;
    }
    
    public void setSecure(boolean isSecure){
    	this.secure = isSecure;
    }

    @Override
    public JSONObject toJSONObject() {
        JSONObject json = new JSONObject();
        try {
            json.put("datasourceid", this.datasourceid);
            json.put("internalurl", this.internalurl);
            json.put("externalurl", this.externalurl);
            json.put("datasourcetypeid", this.datasourcetypeid);
        } catch (JSONException ex) {
            Logger.getLogger(Document.class.getName()).log(Level.SEVERE, null, ex);
        }
        return json;
    }
}
