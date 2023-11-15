package cn.ac.big.bigd.webservice.model.index;

/**
 * Created by chenx on 2023-11-08.
 */
public class Run {
    private String runAcc;
    private String type;
    private String expAcc;
    private String downloadPath;
    private String url;
    private String fileName;
    private String fileSize;

    public String getRunAcc() {
        return runAcc;
    }

    public void setRunAcc(String runAcc) {
        this.runAcc = runAcc;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getExpAcc() {
        return expAcc;
    }

    public void setExpAcc(String expAcc) {
        this.expAcc = expAcc;
    }

    public String getDownloadPath() {
        return downloadPath;
    }

    public void setDownloadPath(String downloadPath) {
        this.downloadPath = downloadPath;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileSize() {
        return fileSize;
    }

    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }
}
