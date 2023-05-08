package cn.ac.big.bigd.webservice.model.gsa;
/**
 * @author lenovo
 * @CreateDate: 2016-06-27
 * @version 1.0
 */
public class ReferenceFile {
	private Integer referenceId;
	private String referenceName;
	private String referenceMd5;
	private Integer userId;
	private Integer referenceType;
	private String database;
	private String number;
	private String url;
	
	public Integer getReferenceId() {
		return referenceId;
	}
	public String getReferenceName() {
		return referenceName;
	}
	public String getReferenceMd5() {
		return referenceMd5;
	}
	public void setReferenceId(Integer referenceId) {
		this.referenceId = referenceId;
	}
	public void setReferenceName(String referenceName) {
		this.referenceName = referenceName;
	}
	public void setReferenceMd5(String referenceMd5) {
		this.referenceMd5 = referenceMd5;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getReferenceType() {
		return referenceType;
	}
	public void setReferenceType(Integer referenceType) {
		this.referenceType = referenceType;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getDatabase() {
		return database;
	}
	public String getNumber() {
		return number;
	}
	public void setDatabase(String database) {
		this.database = database;
	}
	public void setNumber(String number) {
		this.number = number;
	}
}
