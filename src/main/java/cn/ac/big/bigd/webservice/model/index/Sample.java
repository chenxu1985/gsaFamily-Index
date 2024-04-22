package cn.ac.big.bigd.webservice.model.index;

import com.alibaba.fastjson.JSONObject;
import java.util.List;

/**
 * Created by chenx on 2024-04-01.
 */
public class Sample {
    private String center;
    private String accession;
    private String sampleAcc;
    private String name;
    private String title;
    private String taxonId;
    private String organism;
    private JSONObject attributes;
    private String organization;
    private String releaseTime;
    private String sampleType;
    private String use;
    private List<Use> useBy;

    public String getCenter() {
        return center;
    }

    public void setCenter(String center) {
        this.center = center;
    }

    public String getAccession() {
        return accession;
    }

    public void setAccession(String accession) {
        this.accession = accession;
    }

    public String getSampleAcc() {
        return sampleAcc;
    }

    public void setSampleAcc(String sampleAcc) {
        this.sampleAcc = sampleAcc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTaxonId() {
        return taxonId;
    }

    public void setTaxonId(String taxonId) {
        this.taxonId = taxonId;
    }

    public String getOrganism() {
        return organism;
    }

    public void setOrganism(String organism) {
        this.organism = organism;
    }

    public JSONObject getAttributes() {
        return attributes;
    }

    public void setAttributes(JSONObject attributes) {
        this.attributes = attributes;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(String releaseTime) {
        this.releaseTime = releaseTime;
    }

    public String getSampleType() {
        return sampleType;
    }

    public void setSampleType(String sampleType) {
        this.sampleType = sampleType;
    }

    public List<Use> getUseBy() {
        return useBy;
    }

    public void setUseBy(List<Use> useBy) {
        this.useBy = useBy;
    }
}
