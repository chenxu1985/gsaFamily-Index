package cn.ac.big.bigd.webservice.model.gsa;

import java.util.Date;
import java.util.List;

public class FairDetail {
    public String accession;
    public Date releaseTime;
    public String title;
    public String description;
    public String url;
    public String userName;
    public String email;
    public String org;
    public String fileNumber;
    public String fileSize;
    public List<Fund> fund;
    public int prjId;

    public String getAccession() {
        return accession;
    }

    public void setAccession(String accession) {
        this.accession = accession;
    }

    public Date getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(Date releaseTime) {
        this.releaseTime = releaseTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getOrg() {
        return org;
    }

    public void setOrg(String org) {
        this.org = org;
    }

    public String getFileNumber() {
        return fileNumber;
    }

    public void setFileNumber(String fileNumber) {
        this.fileNumber = fileNumber;
    }

    public String getFileSize() {
        return fileSize;
    }

    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }

    public List<Fund> getFund() {
        return fund;
    }

    public void setFund(List<Fund> fund) {
        this.fund = fund;
    }

    public int getPrjId() {
        return prjId;
    }

    public void setPrjId(int prjId) {
        this.prjId = prjId;
    }
}
