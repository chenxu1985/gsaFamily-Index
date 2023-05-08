package cn.ac.big.bigd.webservice.model.gsa;

import java.util.List;

public class Run {
    private Integer runId;
    private String accession;
	private List<RunDataFile> runDataFiles;
	private String runUrl;
//    private Integer userId;
//    private Integer prjId;
//    private String prjAccession;
//    private String prjTitle;
//    private Integer sampleId;
//    private String sampleAccession;
//    private String sampleTitle;
//    private Integer expId;
//    private String expAccession;
//    private Integer craId;
//    private Integer crpId;
//    private Integer crsId;
//    private String libName;
//    private Integer libStrategyId;
//    private String libStrategy;
//    private String libLayout;
//    private Integer platformId;
//    private String platform;
//    private Integer selectionId;
//    private String libSelection;
//    private Integer sourceId;
//    private String libSource;
//    private RunDataType runDataType;
//    private Integer runDataTypeId;
//    private String alias;
//    private Date createTime;
//    private Date modifyTime;
//    private Integer status;
//	private Integer isReleased;
//    private Integer releaseState;
//    private Date releaseTime;
//	private Date deleteTime;
//    private List<RunDataFile> runDataFiles;
//    private ReferenceFile referenceFile;
//    private Integer referenceId;
//    private String craAcc;
//    //added by wangyanqing
//    private Integer isShared;
//	private String shareCode;
//	private Integer shareId;
//	//added by tangbx 20151116
//	private Integer isUpload;
//	private Integer isApply;
//	private Integer isIntraPublish;
//	private String statusString;
//    private String releaseStateString;
//    private Boolean isRunFileProcessing=false;
//	private String statusClass; //for html class mapping color

	public Integer getRunId() {
		return runId;
	}
	public void setRunId(Integer runId) {
		this.runId = runId;
	}
	public String getAccession() {
		return accession;
	}
	public void setAccession(String accession) {
		this.accession = accession;
	}
	public List<RunDataFile> getRunDataFiles() {
		return runDataFiles;
	}
	public void setRunDataFiles(List<RunDataFile> runDataFiles) {
		this.runDataFiles = runDataFiles;
	}

	public String getRunUrl() {
		return runUrl;
	}

	public void setRunUrl(String runUrl) {
		this.runUrl = runUrl;
	}

//	public int getIsIntraPublish() {
//		return isIntraPublish;
//	}
//	public void setIsIntraPublish(Integer isIntraPublish) {
//		this.isIntraPublish = isIntraPublish;
//	}
//    public Integer getUserId() {
//		return userId;
//	}
//	public void setUserId(Integer userId) {
//		this.userId = userId;
//	}
//	public Integer getPrjId() {
//		return prjId;
//	}
//	public void setPrjId(Integer prjId) {
//		this.prjId = prjId;
//	}
//	public Integer getSampleId() {
//		return sampleId;
//	}
//	public void setSampleId(Integer sampleId) {
//		this.sampleId = sampleId;
//	}
//	public Integer getExpId() {
//        return expId;
//    }
//    public void setExpId(Integer expId) {
//        this.expId = expId;
//    }
//    public String getAlias() {
//        return alias;
//    }
//    public void setAlias(String alias) {
//        this.alias = alias;
//    }
//    public Date getCreateTime() {
//        return createTime;
//    }
//    public void setCreateTime(Date createTime) {
//        this.createTime = createTime;
//    }
//    public Date getModifyTime() {
//        return modifyTime;
//    }
//    public void setModifyTime(Date modifyTime) {
//        this.modifyTime = modifyTime;
//    }
//    public Integer getStatus() {
//        return status;
//    }
//    public void setStatus(Integer status) {
//        this.status = status;
//        this.setStatusString(Status.StatusString[status]);
//    }
//	public Integer getIsReleased() {
//		return isReleased;
//	}
//	public void setIsReleased(Integer isReleased) {
//		this.isReleased = isReleased;
//	}
//	public Date getReleaseTime() {
//		return releaseTime;
//	}
//	public Integer getReleaseState() {
//		return releaseState;
//	}
//	public void setReleaseState(Integer releaseState) {
//		this.releaseState = releaseState;
//		this.setReleaseStateString(Status.ReleaseStateString[releaseState]);
//	}
//	public void setReleaseTime(String releaseTime){
//		if(releaseTime != null && !"".equals(releaseTime)){
//			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//			try{
//				this.releaseTime=dateFormat.parse(releaseTime);
//			}catch(Exception e){
//				e.printStackTrace();
//			}
//		}
//	}
//	public void setReleaseTime(Date releaseTime) {
//		this.releaseTime = releaseTime;
//	}
//	public List<RunDataFile> getRunDataFiles() {
//		return runDataFiles;
//	}
//	public void setRunDataFiles(List<RunDataFile> runDataFiles) {
//		this.runDataFiles = runDataFiles;
//	}
//	public ReferenceFile getReferenceFile() {
//		return referenceFile;
//	}
//	public void setReferenceFile(ReferenceFile referenceFile) {
//		this.referenceFile = referenceFile;
//	}
//	public Integer getReferenceId() {
//		return referenceId;
//	}
//	public void setReferenceId(Integer referenceId) {
//		this.referenceId = referenceId;
//	}
//	public Integer getIsShared() {
//		return isShared;
//	}
//	public void setIsShared(Integer isShared) {
//		this.isShared = isShared;
//	}
//	public String getShareCode() {
//		return shareCode;
//	}
//	public void setShareCode(String shareCode) {
//		this.shareCode = shareCode;
//	}
//	public Integer getShareId() {
//		return shareId;
//	}
//	public void setShareId(Integer shareId) {
//		this.shareId = shareId;
//	}
//	public Integer getIsUpload() {
//		return isUpload;
//	}
//	public void setIsUpload(Integer isUpload) {
//		this.isUpload = isUpload;
//	}
//	public Integer getIsApply() {
//		return isApply;
//	}
//	public void setIsApply(Integer isApply) {
//		this.isApply = isApply;
//	}
//	public String getPrjAccession() {
//		return prjAccession;
//	}
//	public void setPrjAccession(String prjAccession) {
//		this.prjAccession = prjAccession;
//	}
//	public String getSampleAccession() {
//		return sampleAccession;
//	}
//	public void setSampleAccession(String sampleAccession) {
//		this.sampleAccession = sampleAccession;
//	}
//	public String getExpAccession() {
//		return expAccession;
//	}
//	public void setExpAccession(String expAccession) {
//		this.expAccession = expAccession;
//	}
//	public Integer getCraId() {
//		return craId;
//	}
//	public Integer getCrpId() {
//		return crpId;
//	}
//	public Integer getCrsId() {
//		return crsId;
//	}
//	public void setCraId(Integer craId) {
//		this.craId = craId;
//	}
//	public void setCrpId(Integer crpId) {
//		this.crpId = crpId;
//	}
//	public void setCrsId(Integer crsId) {
//		this.crsId = crsId;
//	}
//	public Integer getPlatformId() {
//		return platformId;
//	}
//	public void setPlatformId(Integer platformId) {
//		this.platformId = platformId;
//	}
//	public String getPlatform() {
//		return platform;
//	}
//	public void setPlatform(String platform) {
//		this.platform = platform;
//	}
//	public Integer getSelectionId() {
//		return selectionId;
//	}
//	public void setSelectionId(Integer selectionId) {
//		this.selectionId = selectionId;
//	}
//	public String getLibSelection() {
//		return libSelection;
//	}
//	public void setLibSelection(String libSelection) {
//		this.libSelection = libSelection;
//	}
//	public Integer getSourceId() {
//		return sourceId;
//	}
//	public void setSourceId(Integer sourceId) {
//		this.sourceId = sourceId;
//	}
//	public String getLibSource() {
//		return libSource;
//	}
//	public void setLibSource(String libSource) {
//		this.libSource = libSource;
//	}
//	public RunDataType getRunDataType() {
//		return runDataType;
//	}
//	public void setRunDataType(RunDataType runDataType) {
//		this.runDataType = runDataType;
//	}
//	public Integer getRunDataTypeId() {
//		return runDataTypeId;
//	}
//	public void setRunDataTypeId(Integer runDataTypeId) {
//		this.runDataTypeId = runDataTypeId;
//	}
//	public String getPrjTitle() {
//		return prjTitle;
//	}
//	public void setPrjTitle(String prjTitle) {
//		this.prjTitle = prjTitle;
//	}
//	public String getSampleTitle() {
//		return sampleTitle;
//	}
//	public void setSampleTitle(String sampleTitle) {
//		this.sampleTitle = sampleTitle;
//	}
//	public String getLibName() {
//		return libName;
//	}
//	public void setLibName(String libName) {
//		this.libName = libName;
//	}
//	public Integer getLibStrategyId() {
//		return libStrategyId;
//	}
//	public void setLibStrategyId(Integer libStrategyId) {
//		this.libStrategyId = libStrategyId;
//	}
//	public String getLibStrategy() {
//		return libStrategy;
//	}
//	public void setLibStrategy(String libStrategy) {
//		this.libStrategy = libStrategy;
//	}
//	public String getLibLayout() {
//		return libLayout;
//	}
//	public void setLibLayout(String libLayout) {
//		this.libLayout = libLayout;
//	}
//	public String getStatusString() {
//		return statusString;
//	}
//	public String getReleaseStateString() {
//		return releaseStateString;
//	}
//	public void setStatusString(String statusString) {
//
//		this.statusString = statusString;
//	}
//	public void setReleaseStateString(String releaseStateString) {
//
//		this.releaseStateString = releaseStateString;
//	}
//	public String getCraAcc() {
//		return craAcc;
//	}
//	public void setCraAcc(String craAcc) {
//		this.craAcc = craAcc;
//	}
//	public Boolean getIsRunFileProcessing() {
//		return isRunFileProcessing;
//	}
//	public void setIsRunFileProcessing(Boolean isRunFileProcessing) {
//		this.isRunFileProcessing = isRunFileProcessing;
//	}
//	public String getStatusClass() {
//		return statusClass;
//	}
//	public void setStatusClass(String statusClass) {
//		this.statusClass = statusClass;
//	}
//	public Date getDeleteTime() {
//		return deleteTime;
//	}
//	public void setDeleteTime(Date deleteTime) {
//		this.deleteTime = deleteTime;
//	}
}