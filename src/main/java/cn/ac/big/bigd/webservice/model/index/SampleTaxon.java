package cn.ac.big.bigd.webservice.model.index;

import com.alibaba.fastjson.JSONObject;

import java.util.List;

/**
 * Created by chenx on 2024-04-02.
 */
public class SampleTaxon {
    private int taxId;
    private String organism;

    public int getTaxId() {
        return taxId;
    }

    public void setTaxId(int taxId) {
        this.taxId = taxId;
    }

    public String getOrganism() {
        return organism;
    }

    public void setOrganism(String organism) {
        this.organism = organism;
    }
}
