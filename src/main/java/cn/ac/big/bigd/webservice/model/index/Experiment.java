package cn.ac.big.bigd.webservice.model.index;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenx on 2023-11-08.
 */
public class Experiment {
    private String center;
    private String type;
    private String accession;
    private String downloadable;
    private transient String fileType;
    private List<FileType> fileTypes;
    private String title;
    private String libraryName;
    private String libDesign;
    private String libLayout;
    private transient String platform;
    private transient String platformDetail;
    private List<Platform> platforms;
    private String source;
    private String strategy;
    private String strategyDetail;
    private String selection;
    private String insertSize;
    private String releaseTime;
    private String dataset;
    private String datasetTitle;
    private String datasetDesc;
    private String projectAcc;
    private String projectTitle;
    private String projectDesc;
    private String sampleAcc;
    private String sampleName;
    private String sampleTitle;
    private String sampleDesc;
    private String sampleType;
    private String taxId;
    private String organism;
    private String properties;
    private String releaseState;
    private String url;
    private String createTime;
    private String submitter;
    private String organization;
    private String plannedReadLengthMate1;
    private String plannedReadLengthMate2;
    private String libNominalSize;
    private String libNominalStandardDeviation;
    private String plannedReadLength;
    private String plannedNumberOfCycles;

    public String getCenter() {
        return center;
    }

    public void setCenter(String center) {
        this.center = center;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAccession() {
        return accession;
    }

    public void setAccession(String accession) {
        this.accession = accession;
    }

    public String getDownloadable() {
        return downloadable;
    }

    public void setDownloadable(String downloadable) {
        this.downloadable = downloadable;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType.toLowerCase();
        this.setFileTypes(this.fileType);
    }
    public String getPlatformDetail() {
        return platformDetail;
    }

    public void setPlatformDetail(String platformDetail) {
        this.platformDetail = platformDetail;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLibraryName() {
        return libraryName;
    }

    public void setLibraryName(String libraryName) {
        this.libraryName = libraryName;
    }

    public String getLibDesign() {
        return libDesign;
    }

    public void setLibDesign(String libDesign) {
        this.libDesign = libDesign;
    }

    public String getLibLayout() {
        return libLayout;
    }

    public void setLibLayout(String libLayout) {
        this.libLayout = libLayout;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
        this.setPlatforms(this.platform,this.platformDetail);
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getStrategy() {
        return strategy;
    }

    public void setStrategy(String strategy) {
        this.strategy = strategy;
    }

    public String getSelection() {
        return selection;
    }

    public void setSelection(String selection) {
        this.selection = selection;
    }

    public String getInsertSize() {
        return insertSize;
    }

    public void setInsertSize(String insertSize) {
        this.insertSize = insertSize;
    }

    public String getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(String releaseTime) {
        this.releaseTime = releaseTime;
    }

    public String getDataset() {
        return dataset;
    }

    public void setDataset(String dataset) {
        this.dataset = dataset;
    }

    public String getProjectAcc() {
        return projectAcc;
    }

    public void setProjectAcc(String projectAcc) {
        this.projectAcc = projectAcc;
    }

    public String getProjectTitle() {
        return projectTitle;
    }

    public void setProjectTitle(String projectTitle) {
        this.projectTitle = projectTitle;
    }

    public String getProjectDesc() {
        return projectDesc;
    }

    public void setProjectDesc(String projectDesc) {
        this.projectDesc = projectDesc;
    }

    public String getSampleAcc() {
        return sampleAcc;
    }

    public void setSampleAcc(String sampleAcc) {
        this.sampleAcc = sampleAcc;
    }

    public String getSampleName() {
        return sampleName;
    }

    public void setSampleName(String sampleName) {
        this.sampleName = sampleName;
    }

    public String getSampleTitle() {
        return sampleTitle;
    }

    public void setSampleTitle(String sampleTitle) {
        this.sampleTitle = sampleTitle;
    }

    public String getSampleDesc() {
        return sampleDesc;
    }

    public void setSampleDesc(String sampleDesc) {
        this.sampleDesc = sampleDesc;
    }

    public String getSampleType() {
        return sampleType;
    }

    public void setSampleType(String sampleType) {
        this.sampleType = sampleType;
    }

    public String getTaxId() {
        return taxId;
    }

    public void setTaxId(String taxId) {
        this.taxId = taxId;
    }

    public String getOrganism() {
        return organism;
    }

    public void setOrganism(String organism) {
        this.organism = organism;
    }

    public String getProperties() {
        return properties;
    }

    public void setProperties(String properties) {
        this.properties = properties;
    }

    public String getReleaseState() {
        return releaseState;
    }

    public void setReleaseState(String releaseState) {
        this.releaseState = releaseState;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getSubmitter() {
        return submitter;
    }

    public void setSubmitter(String submitter) {
        this.submitter = submitter;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getPlannedReadLengthMate1() {
        return plannedReadLengthMate1;
    }

    public void setPlannedReadLengthMate1(String plannedReadLengthMate1) {
        this.plannedReadLengthMate1 = plannedReadLengthMate1;
    }

    public String getPlannedReadLengthMate2() {
        return plannedReadLengthMate2;
    }

    public void setPlannedReadLengthMate2(String plannedReadLengthMate2) {
        this.plannedReadLengthMate2 = plannedReadLengthMate2;
    }

    public String getLibNominalSize() {
        return libNominalSize;
    }

    public void setLibNominalSize(String libNominalSize) {
        this.libNominalSize = libNominalSize;
    }

    public String getLibNominalStandardDeviation() {
        return libNominalStandardDeviation;
    }

    public void setLibNominalStandardDeviation(String libNominalStandardDeviation) {
        this.libNominalStandardDeviation = libNominalStandardDeviation;
    }

    public String getPlannedReadLength() {
        return plannedReadLength;
    }

    public void setPlannedReadLength(String plannedReadLength) {
        this.plannedReadLength = plannedReadLength;
    }

    public String getPlannedNumberOfCycles() {
        return plannedNumberOfCycles;
    }

    public void setPlannedNumberOfCycles(String plannedNumberOfCycles) {
        this.plannedNumberOfCycles = plannedNumberOfCycles;
    }

    public void setPlatforms(String platforms,String platformDetails) {
        String[] plats = platforms.split(",");
        String[] pds = platformDetails.split(",");
        List<Platform> platformList = new ArrayList<>();
        for(int i=0;i<plats.length;i++){
            String p = plats[i];
            String pd = pds[i];
            Platform pf = new Platform();
            pf.setPlatform(p);
            pf.setPlatformDetail(pd);
            platformList.add(pf);
        }
        this.platforms = platformList;
    }

    public void setFileTypes(String fileTypes) {
        String[] files = fileTypes.split(",");
        List<FileType> fileTypeList = new ArrayList<>();
        for(String f:files){
            FileType ft = new FileType();
            ft.setFileType(f);
            fileTypeList.add(ft);
        }
        this.fileTypes = fileTypeList;
    }

    public List<FileType> getFileTypes() {
        return fileTypes;
    }

    public void setFileTypes(List<FileType> fileTypes) {
        this.fileTypes = fileTypes;
    }

    public List<Platform> getPlatforms() {
        return platforms;
    }

    public void setPlatforms(List<Platform> platforms) {
        this.platforms = platforms;
    }


    public String getStrategyDetail() {
        return strategyDetail;
    }

    public void setStrategyDetail(String strategyDetail) {
        this.strategyDetail = strategyDetail;
    }

    public String getDatasetTitle() {
        return datasetTitle;
    }

    public void setDatasetTitle(String datasetTitle) {
        this.datasetTitle = datasetTitle;
    }

    public String getDatasetDesc() {
        return datasetDesc;
    }

    public void setDatasetDesc(String datasetDesc) {
        this.datasetDesc = datasetDesc;
    }
}
