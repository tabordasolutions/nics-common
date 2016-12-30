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
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import edu.mit.ll.nics.common.entity.Org;
import edu.mit.ll.nics.common.entity.SADisplayMessageEntity;
import edu.mit.ll.nics.common.entity.SADisplayPersistedEntity;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * DocumentOrg generated by hbm2java
 */
@Entity
@Table(name = "document_org", schema = "public")
public class DocumentOrg extends SADisplayMessageEntity implements SADisplayPersistedEntity {

    private String documentOrgid;
    private Org org;
    private int orgid;
    private Document document;
    private String documentid;

    public DocumentOrg() {
    }

    public DocumentOrg(String documentOrgid, Org org, Document document) {
        this.documentOrgid = documentOrgid;
        this.org = org;
        this.document = document;
    }

    @Id
    @Column(name = "document_orgid", unique = true, nullable = false)
    public String getDocumentOrgid() {
        return this.documentOrgid;
    }

    public void setDocumentOrgid(String documentOrgid) {
        this.documentOrgid = documentOrgid;
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
    @JoinColumn(name = "documentid", insertable = false, updatable = false)
    public Document getDocument() {
        return this.document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }

    @Column(name = "documentid", nullable = false)
    public String getDocumentid() {
        return documentid;
    }

    public void setDocumentid(String documentid) {
        this.documentid = documentid;
    }

    @Column(name = "orgid", nullable = false)
    public int getOrgid() {
        return orgid;
    }

    public void setOrgid(int orgid) {
        this.orgid = orgid;
    }

    @Override
    public JSONObject toJSONObject() throws JSONException {
        JSONObject json = new JSONObject();
        json.put("documentOrgid", this.documentOrgid);
        json.put("orgid", this.orgid);
        json.put("documentid", this.documentid);
        return json;
    }
}