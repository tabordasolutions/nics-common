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
package edu.mit.ll.nics.common.entity.datalayer;

// Generated Nov 30, 2011 2:53:26 PM by Hibernate Tools 3.4.0.CR1
import edu.mit.ll.nics.common.entity.OrgFolder;
import edu.mit.ll.nics.common.entity.SADisplayMessageEntity;
import edu.mit.ll.nics.common.entity.SADisplayPersistedEntity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
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
 * Folder generated by hbm2java
 */
@Entity
@Table(name = "folder", schema = "public")
public class Folder extends SADisplayMessageEntity implements SADisplayPersistedEntity {

	private String folderid;
    private String foldername;
    private String parentfolderid;
    private Folder parentfolder;
    private int workspaceid;
    private int index;
    private Set<Document> documents = new HashSet<Document>(0);
    private Set<Folder> children = new HashSet<Folder>(0);
    private Set<OrgFolder> orgfolders = new HashSet<OrgFolder>(0);
    private Set<Datalayerfolder> datalayerfolders = new HashSet<Datalayerfolder>(0);

    public Folder() {
    }

    public Folder(String folderid, String foldername, String parentfolderid) {
        this.folderid = folderid;
        this.foldername = foldername;
        this.parentfolderid = parentfolderid;
    }

    public Folder(String folderid, String foldername, String parentfolderid,
            Set<Document> documents, int index) {
        this.folderid = folderid;
        this.foldername = foldername;
        this.parentfolderid = parentfolderid;
        this.documents = documents;
        this.index = index;
    }

    @Id
    @Column(name = "folderid", unique = true, nullable = false)
    public String getFolderid() {
        return this.folderid;
    }

    public void setFolderid(String folderid) {
        this.folderid = folderid;
    }

    @Column(name = "foldername", nullable = false, length = 256)
    public String getFoldername() {
        return this.foldername;
    }

    public void setFoldername(String foldername) {
        this.foldername = encodeForHTML(foldername);
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parentfolderid", insertable = false, updatable = false)
    public Folder getParentfolder() {
        return this.parentfolder;
    }

    public void setParentfolder(Folder parentfolder) {
        this.parentfolder = parentfolder;
    }

    @Column(name = "parentfolderid")
    public String getParentfolderid() {
        return this.parentfolderid;
    }

    public void setParentfolderid(String parentfolderid) {
        this.parentfolderid = parentfolderid;
    }
    
    /**
     * @return the workspace id
     */
    @Column(name = "workspaceid")
    public int getWorkspaceid() {
        return this.workspaceid;
    }

    /**
     * @param room the usersessionid
     */
    public void setWorkspaceid(int id) {
        this.workspaceid = id;
    }

    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.REMOVE})
    @JoinColumn(name = "parentfolderid")
    public Set<Folder> getChildren() {
        return this.children;
    }

    public void setChildren(Set<Folder> children) {
        this.children = children;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "folder", cascade = {CascadeType.REMOVE})
    public Set<Document> getDocuments() {
        return this.documents;
    }

    public void setDocuments(Set<Document> documents) {
        this.documents = documents;
    }
    
    @Column(name = "index", nullable = false)
    public int getIndex() {
        return this.index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
    
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "folder", cascade = {CascadeType.REMOVE})
    public Set<OrgFolder> getOrgfolders() {
        return this.orgfolders;
    }

    public void setOrgfolders(Set<OrgFolder> orgfolders) {
        this.orgfolders = orgfolders;
    }
    
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "folder", cascade = {CascadeType.REMOVE})
    public Set<Datalayerfolder> getDatalayerfolders() {
        return this.datalayerfolders;
    }

    public void setDatalayerfolders(Set<Datalayerfolder> datalayerfolders) {
        this.datalayerfolders = datalayerfolders;
    }

    @Override
    public JSONObject toJSONObject() throws JSONException {
        JSONObject json = new JSONObject();
        json.put("folderid", this.folderid);
        json.put("foldername", this.foldername);
        json.put("parentfolderid", this.parentfolderid);
        json.put("index", this.index);
        json.put("workspaceid", this.workspaceid);
        return json;
    }
}
