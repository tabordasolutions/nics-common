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
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import edu.mit.ll.nics.common.entity.SADisplayMessageEntity;
import edu.mit.ll.nics.common.entity.SADisplayPersistedEntity;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * DatalayerDatalayersource generated by hbm2java
 */
@Entity
@Table(name = "datalayer_datalayersource", schema = "public")
public class DatalayerDatalayersource extends SADisplayMessageEntity implements SADisplayPersistedEntity {

    private String datalayerDatalayersourceid;
    private Datalayersource datalayersource;
    private String datalayersourceid;
    private Datalayer datalayer;
    private String datalayerid;

    public DatalayerDatalayersource() {
    }

    public DatalayerDatalayersource(String datalayerDatalayersourceid, Datalayersource datalayersource,
            Datalayer datalayer) {
        this.datalayerDatalayersourceid = datalayerDatalayersourceid;
        this.datalayersource = datalayersource;
        this.datalayer = datalayer;
    }

    @Id
    @Column(name = "datalayer_datalayersourceid", unique = true, nullable = false)
    public String getDatalayerDatalayersourceid() {
        return this.datalayerDatalayersourceid;
    }

    public void setDatalayerDatalayersourceid(String datalayerDatalayersourceid) {
        this.datalayerDatalayersourceid = datalayerDatalayersourceid;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "datalayersourceid", insertable = false, updatable = false)
    public Datalayersource getDatalayersource() {
        return this.datalayersource;
    }

    public void setDatalayersource(Datalayersource datalayersource) {
        this.datalayersource = datalayersource;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "datalayerid", insertable = false, updatable = false)
    public Datalayer getDatalayer() {
        return this.datalayer;
    }

    public void setDatalayer(Datalayer datalayer) {
        this.datalayer = datalayer;
    }

    @Column(name = "datalayerid", nullable = false)
    public String getDatalayerid() {
        return datalayerid;
    }

    public void setDatalayerid(String datalayerid) {
        this.datalayerid = datalayerid;
    }

    @Column(name = "datalayersourceid", nullable = false)
    public String getDatalayersourceid() {
        return datalayersourceid;
    }

    public void setDatalayersourceid(String datalayersourceid) {
        this.datalayersourceid = datalayersourceid;
    }

    @Override
    public JSONObject toJSONObject() throws JSONException {
        JSONObject json = new JSONObject();
        json.put("datalayerDatalayersourceid", this.datalayerDatalayersourceid);
        json.put("datalayerid", this.datalayerid);
        json.put("datalayersourceid", this.datalayersourceid);
        return json;
    }
}
