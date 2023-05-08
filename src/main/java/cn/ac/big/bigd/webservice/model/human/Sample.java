package cn.ac.big.bigd.webservice.model.human;
import com.alibaba.fastjson.JSONObject;

import java.util.Date;

public class Sample {
    private Integer sampleId;

    private Integer studyId;

    private Integer individualId;

    private String name;

    private String accession;

    private int packageId;

    private Integer biosampleId;

    private String biosampleAccession;

    private String title;

    private String tissue;

    private String age;

    private String ageUnit;

    private String publicDescription;

    private String collectionDate;

    private String karyotype;

    private String phenotype;

    private String population;

    private Integer status;

    private Integer releaseState;

    private Date createTime;

    private Date modifyTime;

    private Date releaseTime;

    private Date archivedTime;

    private Date deleteTime;

    private JSONObject attributes;

    private JSONObject customAttributes;

    private int IsSynchronization;

    private Integer gender;

    private JSONObject iAttr;

    private String ethnicity;

    private String iAcc;


    public Integer getSampleId() {
        return sampleId;
    }

    public void setSampleId(Integer sampleId) {
        this.sampleId = sampleId;
    }

    public Integer getStudyId() {
        return studyId;
    }

    public void setStudyId(Integer studyId) {
        this.studyId = studyId;
    }

    public Integer getIndividualId() {
        return individualId;
    }

    public void setIndividualId(Integer individualId) {
        this.individualId = individualId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccession() {
        return accession;
    }

    public void setAccession(String accession) {
        this.accession = accession;
    }

    public int getPackageId() {
        return packageId;
    }

    public void setPackageId(int packageId) {
        this.packageId = packageId;
    }

    public Integer getBiosampleId() {
        return biosampleId;
    }

    public void setBiosampleId(Integer biosampleId) {
        this.biosampleId = biosampleId;
    }

    public String getBiosampleAccession() {
        return biosampleAccession;
    }

    public void setBiosampleAccession(String biosampleAccession) {
        this.biosampleAccession = biosampleAccession;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTissue() {
        return tissue;
    }

    public void setTissue(String tissue) {
        this.tissue = tissue;
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

    public String getPublicDescription() {
        return publicDescription;
    }

    public void setPublicDescription(String publicDescription) {
        this.publicDescription = publicDescription;
    }

    public String getCollectionDate() {
        return collectionDate;
    }

    public void setCollectionDate(String collectionDate) {
        this.collectionDate = collectionDate;
    }

    public String getKaryotype() {
        return karyotype;
    }

    public void setKaryotype(String karyotype) {
        this.karyotype = karyotype;
    }

    public String getPhenotype() {
        return phenotype;
    }

    public void setPhenotype(String phenotype) {
        this.phenotype = phenotype;
    }

    public String getPopulation() {
        return population;
    }

    public void setPopulation(String population) {
        this.population = population;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getReleaseState() {
        return releaseState;
    }

    public void setReleaseState(Integer releaseState) {
        this.releaseState = releaseState;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public Date getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(Date releaseTime) {
        this.releaseTime = releaseTime;
    }

    public Date getArchivedTime() {
        return archivedTime;
    }

    public void setArchivedTime(Date archivedTime) {
        this.archivedTime = archivedTime;
    }

    public Date getDeleteTime() {
        return deleteTime;
    }

    public void setDeleteTime(Date deleteTime) {
        this.deleteTime = deleteTime;
    }

    public JSONObject getAttributes() {
        return attributes;
    }

    public void setAttributes(JSONObject attributes) {
        this.attributes = attributes;
    }

    public JSONObject getCustomAttributes() {
        return customAttributes;
    }

    public void setCustomAttributes(JSONObject customAttributes) {
        this.customAttributes = customAttributes;
    }

    public int getIsSynchronization() {
        return IsSynchronization;
    }

    public void setIsSynchronization(int isSynchronization) {
        IsSynchronization = isSynchronization;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public JSONObject getiAttr() {
        return iAttr;
    }

    public void setiAttr(JSONObject iAttr) {
        this.iAttr = iAttr;
    }

    public String getEthnicity() {
        return ethnicity;
    }

    public void setEthnicity(String ethnicity) {
        this.ethnicity = ethnicity;
    }

    public String getiAcc() {
        return iAcc;
    }

    public void setiAcc(String iAcc) {
        this.iAcc = iAcc;
    }
}