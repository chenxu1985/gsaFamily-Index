package cn.ac.big.bigd.webservice.model.index;

import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenx on 2024-04-02.
 */
public class SampleType4 {
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
    private List<Use> useBy;
    private JSONObject attributes;
    private String breed;
    private String strain;
    private String age;
    private String ageUnit;
    private String biomaterialProvider;
    private Integer sex;
    private String tissue;
    private String cellLine;
    private String cellType;
    private String collectedBy;
    private String disease;
    private String geographicLocation;
    private String isolationSource;
    private String latitudeLongitude;

//    private String birthDate;
//    private String birthLocation;
//    private String breedHistory;
//    private String breedMethod;
//    private String cellSubtype;
//    private String cultureCollection;
//    private String deathDate;
//    private String devStage;
//    private String diseaseStage;
//    private String genotype;
//    private String growthProtocol;
//    private String healthState;
//    private String phenotype;
//    private String type;
//    private String specimenVoucher;
//    private String storageConditions;
//    private String studBookNumber;
//    private String treatment;

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

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getAgeUnit() {
        return ageUnit;
    }

    public void setAgeUnit(String ageUnit) {
        this.ageUnit = ageUnit;
    }

    public String getBiomaterialProvider() {
        return biomaterialProvider;
    }

    public void setBiomaterialProvider(String biomaterialProvider) {
        this.biomaterialProvider = biomaterialProvider;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getTissue() {
        return tissue;
    }

    public void setTissue(String tissue) {
        this.tissue = tissue;
    }

    public String getCellLine() {
        return cellLine;
    }

    public void setCellLine(String cellLine) {
        this.cellLine = cellLine;
    }

    public String getCellType() {
        return cellType;
    }

    public void setCellType(String cellType) {
        this.cellType = cellType;
    }

    public String getDisease() {
        return disease;
    }

    public void setDisease(String disease) {
        this.disease = disease;
    }
}
