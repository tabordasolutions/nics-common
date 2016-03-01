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
package edu.mit.ll.nics.common.entity.datalayer;

// Generated Nov 30, 2011 2:53:26 PM by Hibernate Tools 3.4.0.CR1
import edu.mit.ll.nics.common.entity.SADisplayMessageEntity;
import edu.mit.ll.nics.common.entity.SADisplayPersistedEntity;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import edu.mit.ll.nics.common.entity.Usersession;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Datalayer generated by hbm2java
 */
@Entity
@Table(name = "datalayer", schema = "public")
public class Datalayer extends SADisplayMessageEntity implements SADisplayPersistedEntity {

    private String datalayerid;
    private Usersession usersession;
    private int usersessionid;
    private Datalayersource datalayersource;
    private String datalayersourceid;
    private boolean baselayer;
    private String displayname;    
    private Date created;
    private String legend;
    private Set<DatalayerCollabroom> datalayerCollabrooms = new HashSet<DatalayerCollabroom>(0);
    private Set<Datalayerfolder> datalayerfolders = new HashSet<Datalayerfolder>(0);
    

    public Datalayer() {
    }

    public Datalayer(String datalayerid, Usersession usersession,
            Datalayersource datalayersource, Folder folder, boolean baselayer,
            String displayname, Date created, String legend) {
        this.datalayerid = datalayerid;
        this.usersession = usersession;
        this.datalayersource = datalayersource;
        this.baselayer = baselayer;
        this.displayname = displayname;
        this.created = created;
        this.legend = legend;
    }

    public Datalayer(String datalayerid, Usersession usersession,
            Datalayersource datalayersource, Folder folder, boolean baselayer,
            String displayname, Date created,
            Set<DatalayerCollabroom> datalayerCollabrooms, String legend) {
        this.datalayerid = datalayerid;
        this.usersession = usersession;
        this.datalayersource = datalayersource;
        this.baselayer = baselayer;
        this.displayname = displayname;
        this.created = created;
        this.datalayerCollabrooms = datalayerCollabrooms;
        this.legend = legend;
    }

    @Id
    @Column(name = "datalayerid", unique = true, nullable = false)
    public String getDatalayerid() {
        return this.datalayerid;
    }

    public void setDatalayerid(String datalayerid) {
        this.datalayerid = datalayerid;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usersessionid", insertable = false, updatable = false)
    public Usersession getUsersession() {
        return this.usersession;
    }

    public void setUsersession(Usersession usersession) {
        this.usersession = usersession;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "datalayersourceid", insertable = false, updatable = false)
    public Datalayersource getDatalayersource() {
        return this.datalayersource;
    }

    public void setDatalayersource(Datalayersource datalayersource) {
        this.datalayersource = datalayersource;
    }

    @Column(name = "baselayer", nullable = false)
    public boolean getBaselayer() {
        return this.baselayer;
    }

    public void setBaselayer(boolean baselayer) {
        this.baselayer = baselayer;
    }

    @Column(name = "displayname", nullable = false, length = 256)
    public String getDisplayname() {
        return this.displayname;
    }

    public void setDisplayname(String displayname) {
        this.displayname = encodeForHTML(displayname);
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created", nullable = false, length = 29)
    public Date getCreated() {
        return this.created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "datalayer")
    public Set<DatalayerCollabroom> getDatalayerCollabrooms() {
        return this.datalayerCollabrooms;
    }

    public void setDatalayerCollabrooms(
            Set<DatalayerCollabroom> datalayerCollabrooms) {
        this.datalayerCollabrooms = datalayerCollabrooms;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "datalayer")
    public Set<Datalayerfolder> getDatalayerfolders() {
        return this.datalayerfolders;
    }

    public void setDatalayerfolders(
            Set<Datalayerfolder> datalayerfolders) {
        this.datalayerfolders = datalayerfolders;
    }

    @Column(name = "datalayersourceid", nullable = false)
    public String getDatalayersourceid() {
        return datalayersourceid;
    }

    public void setDatalayersourceid(String datalayersourceid) {
        this.datalayersourceid = datalayersourceid;
    }

    @Column(name = "usersessionid", nullable = false)
    public int getUsersessionid() {
        return usersessionid;
    }

    public void setUsersessionid(int usersessionid) {
        this.usersessionid = usersessionid;
    }
    
    @Column(name = "legend", length = 256)
    public String getLegend() {
        return legend;
    }

    public void setLegend(String legend) {
        this.legend = legend;
    }

    @Override
    public JSONObject toJSONObject() {
        JSONObject json = new JSONObject();
        try {
            json.put("datalayerid",this.datalayerid);
            json.put("created", this.created);
            json.put("displayname", this.displayname);
            json.put("baselayer", this.baselayer);
            json.put("datalayersourceid", this.datalayersourceid);
            json.put("usersessionid", this.usersessionid);
            json.put("legend", this.legend);
        } catch (JSONException ex) {
            Logger.getLogger(Document.class.getName()).log(Level.SEVERE, null, ex);
        }
        return json;
    }
}
