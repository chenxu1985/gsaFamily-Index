package cn.ac.big.bigd.webservice.model.gsa;

import java.util.Date;

public class RunDataFile {
    private Integer runFileId;
    private Integer userId;
    private Integer prjId;
    private Integer sampleId;
    private Integer expId;
    private Integer runId;
    private Integer craId;
    private Integer crpId;
    private Integer crsId;
    private String runFileName;
    private String md5;
    private Date uploadTime;
    private Integer status;
    private Integer releaseState;
    private Integer runDataTypeId;
    //added by wangyanqing
    private String runFileSize;
    private String runFileSizeMB;
    private String runFileSizeGB;
    private String ftpDir;
    private String archivedFileName;
    private String archivedFileDir;
    private Date modifyTime;
    private int isSourceFileDeleted;
    private String statusString;
    private String releaseStateString;
    private String runFileLabel;// F1,R2

    public Integer getRunFileId() {
        return runFileId;
    }

    public void setRunFileId(Integer runFileId) {
        this.runFileId = runFileId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getPrjId() {
        return prjId;
    }

    public void setPrjId(Integer prjId) {
        this.prjId = prjId;
    }

    public Integer getSampleId() {
        return sampleId;
    }

    public void setSampleId(Integer sampleId) {
        this.sampleId = sampleId;
    }

    public Integer getExpId() {
        return expId;
    }

    public void setExpId(Integer expId) {
        this.expId = expId;
    }

    public Integer getRunId() {
        return runId;
    }

    public void setRunId(Integer runId) {
        this.runId = runId;
    }

    public Integer getCraId() {
        return craId;
    }

    public void setCraId(Integer craId) {
        this.craId = craId;
    }

    public Integer getCrpId() {
        return crpId;
    }

    public void setCrpId(Integer crpId) {
        this.crpId = crpId;
    }

    public Integer getCrsId() {
        return crsId;
    }

    public void setCrsId(Integer crsId) {
        this.crsId = crsId;
    }

    public String getRunFileName() {
        return runFileName;
    }

    public void setRunFileName(String runFileName) {
        this.runFileName = runFileName;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public Date getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Date uploadTime) {
        this.uploadTime = uploadTime;
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

    public Integer getRunDataTypeId() {
        return runDataTypeId;
    }

    public void setRunDataTypeId(Integer runDataTypeId) {
        this.runDataTypeId = runDataTypeId;
    }

    public String getRunFileSize() {
        return runFileSize;
    }

    public void setRunFileSize(String runFileSize) {
        this.runFileSize = runFileSize;
    }

    public String getRunFileSizeMB() {
        return runFileSizeMB;
    }

    public void setRunFileSizeMB(String runFileSizeMB) {
        this.runFileSizeMB = runFileSizeMB;
    }

    public String getRunFileSizeGB() {
        return runFileSizeGB;
    }

    public void setRunFileSizeGB(String runFileSizeGB) {
        this.runFileSizeGB = runFileSizeGB;
    }

    public String getFtpDir() {
        return ftpDir;
    }

    public void setFtpDir(String ftpDir) {
        this.ftpDir = ftpDir;
    }

    public String getArchivedFileName() {
        return archivedFileName;
    }

    public void setArchivedFileName(String archivedFileName) {
        this.archivedFileName = archivedFileName;

    }

    public String getArchivedFileDir() {
        return archivedFileDir;
    }

    public void setArchivedFileDir(String archivedFileDir) {
        this.archivedFileDir = "ftp://download.big.ac.cn/gsa"+archivedFileDir;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public int getIsSourceFileDeleted() {
        return isSourceFileDeleted;
    }

    public void setIsSourceFileDeleted(int isSourceFileDeleted) {
        this.isSourceFileDeleted = isSourceFileDeleted;
    }

    public String getStatusString() {
        return statusString;
    }

    public void setStatusString(String statusString) {
        this.statusString = statusString;
    }

    public String getReleaseStateString() {
        return releaseStateString;
    }

    public void setReleaseStateString(String releaseStateString) {
        this.releaseStateString = releaseStateString;
    }

    public String getRunFileLabel() {
        return runFileLabel;
    }

    public void setRunFileLabel(String runFileLabel) {
        this.runFileLabel = runFileLabel;
    }
}
