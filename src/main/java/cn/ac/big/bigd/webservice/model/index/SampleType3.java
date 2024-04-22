package cn.ac.big.bigd.webservice.model.index;

import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenx on 2024-04-02.
 */
public class SampleType3 {
    private String center;
    private String accession;
    private String sampleAcc;
    private String name;
    private String title;
    private Integer taxonId;
    private String organism;
    private String organization;
    private String releaseTime;
    private String sampleType;
//    private String used;
    private List<Use> useBy;
    private JSONObject attributes;
    private String strain;
    private String collectionDate;
    private String geographicLocation;
    private String latitudeLongitude;
    private String isolationSource;
    private String collectedBy;
    private int hostId;
    private String host;
    private String hostTissueSampled;

//    private String altitude;
//    private String biomaterialProvider;
//    private String cultureCollection;
//    private String depth;
//    private String environmentBiome;
//    private String genotype;
//    private String identifiedBy;
//    private String labHost;
//    private String matingType;
//    private String passageHistory;
//    private String sampleSize;
//    private String serotype;
//    private String serovar;
//    private String specimenVoucher;
//    private String temperature;

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

    public Integer getTaxonId() {
        return taxonId;
    }

    public void setTaxonId(Integer taxonId) {
        this.taxonId = taxonId;
    }

    public String getOrganism() {
        return organism;
    }

    public void setOrganism(String organism) {
        this.organism = organism;
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

//    public String getUsed() {
//        return used;
//    }
//
//    public void setUsed(String used) {
//        this.used = used;
//        this.setUseBy(used);
//    }

    public List<Use> getUseBy() {
        return useBy;
    }

//    public void setUseBy(String use) {
//        String[] uses = use.split(",");
//        List<Use> useList = new ArrayList<>();
//        for(String f:uses){
//            Use u = new Use();
//            u.setUse(f);
//            useList.add(u);
//        }
//        this.useBy = useList;
//    }

    public void setUseBy(List<Use> useBy) {
        this.useBy = useBy;
    }

    public JSONObject getAttributes() {
        return attributes;
    }

    public void setAttributes(JSONObject attributes) {
        this.attributes = attributes;
    }

    public String getStrain() {
        return strain;
    }

    public void setStrain(String strain) {
        this.strain = strain;
    }

    public String getCollectedBy() {
        return collectedBy;
    }

    public void setCollectedBy(String collectedBy) {
        this.collectedBy = collectedBy;
    }

    public String getCollectionDate() {
        return collectionDate;
    }

    public void setCollectionDate(String collectionDate) {
        this.collectionDate = collectionDate;
    }

    public String getGeographicLocation() {
        return geographicLocation;
    }

    public void setGeographicLocation(String geographicLocation) {
        this.geographicLocation = geographicLocation;
    }

    public String getIsolationSource() {
        return isolationSource;
    }

    public void setIsolationSource(String isolationSource) {
        this.isolationSource = isolationSource;
    }

    public String getLatitudeLongitude() {
        return latitudeLongitude;
    }

    public void setLatitudeLongitude(String latitudeLongitude) {
        this.latitudeLongitude = latitudeLongitude;
    }

    public int getHostId() {
        return hostId;
    }

    public void setHostId(int hostId) {
        this.hostId = hostId;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getHostTissueSampled() {
        return hostTissueSampled;
    }

    public void setHostTissueSampled(String hostTissueSampled) {
        this.hostTissueSampled = hostTissueSampled;
    }
}
