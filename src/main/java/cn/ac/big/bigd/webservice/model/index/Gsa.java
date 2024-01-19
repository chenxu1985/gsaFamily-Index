package cn.ac.big.bigd.webservice.model.index;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenx on 2023-11-08.
 */
public class Gsa {
    private String center;
    private String type;
    private String accession;
    private String downloadable;
    private transient String fileType;
    private List<FileType> fileTypes;
    private String title;
    private transient String platform;
    private transient String platformDetail;
    private List<Platform> platforms;
    private String releaseTime;
    private String dataset;
    private String datasetTitle;
    private String datasetDesc;
    private String projectAcc;
    private String projectTitle;
    private String projectDesc;
    private String releaseState;
    private String url;
    private String createTime;
    private String submitter;
    private String organization;

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

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
        this.setPlatforms(this.platform,this.platformDetail);
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

    public List<FileType> getFileTypes() {
        return fileTypes;
    }

    public List<Platform> getPlatforms() {
        return platforms;
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
