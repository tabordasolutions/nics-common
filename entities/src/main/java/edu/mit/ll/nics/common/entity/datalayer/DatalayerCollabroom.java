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
import edu.mit.ll.nics.common.entity.CollabRoom;
import edu.mit.ll.nics.common.entity.SADisplayMessageEntity;
import edu.mit.ll.nics.common.entity.SADisplayPersistedEntity;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * DatalayerCollabroom generated by hbm2java
 */
@Entity
@Table(name = "datalayer_collabroom", schema = "public")
public class DatalayerCollabroom extends SADisplayMessageEntity implements SADisplayPersistedEntity {

    private String datalayerCollabroomid;
    private Datalayer datalayer;
    private String datalayerid;
    private CollabRoom collabroom;
    private int collabroomid;

    public DatalayerCollabroom() {
    }

    public DatalayerCollabroom(String datalayerCollabroomid, Datalayer datalayer,
            CollabRoom collabroom) {
        this.datalayerCollabroomid = datalayerCollabroomid;
        this.datalayer = datalayer;
        this.collabroom = collabroom;
    }

    @Id
    @Column(name = "datalayer_collabroomid", unique = true, nullable = false)
    public String getDatalayerCollabroomid() {
        return this.datalayerCollabroomid;
    }

    public void setDatalayerCollabroomid(String datalayerCollabroomid) {
        this.datalayerCollabroomid = datalayerCollabroomid;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "datalayerid", insertable = false, updatable = false)
    public Datalayer getDatalayer() {
        return this.datalayer;
    }

    public void setDatalayer(Datalayer datalayer) {
        this.datalayer = datalayer;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "collabroomid", insertable = false, updatable = false)
    public CollabRoom getCollabroom() {
        return this.collabroom;
    }

    public void setCollabroom(CollabRoom collabroom) {
        this.collabroom = collabroom;
    }

    @Column(name = "collabroomid", nullable = false)
    public int getCollabroomid() {
        return collabroomid;
    }

    public void setCollabroomid(int collabroomid) {
        this.collabroomid = collabroomid;
    }

    @Column(name = "datalayerid", nullable = false)
    public String getDatalayerid() {
        return datalayerid;
    }

    public void setDatalayerid(String datalayerid) {
        this.datalayerid = datalayerid;
    }

    @Override
    public JSONObject toJSONObject() throws JSONException {
        JSONObject json = new JSONObject();
        json.put("datalayerCollabroomid",this.datalayerCollabroomid);
        json.put("datalayerid",this.datalayerid);
        json.put("collabroomid",this.collabroomid);
        return json;
    }
}
