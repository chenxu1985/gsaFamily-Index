package cn.ac.big.bigd.webservice.model.gsa;

public class Publication {
    private int publicationId;
    private Project project; //foreign key
    private String pubmedId;
    private String doi;
    private String journalTitle;
    private String articleTitle;
    private String year;
    private String volume;
    private String issue;
    private String pagefrom;
    private String pageto;
    private String authorList;

    public int getPublicationId() {
        return publicationId;
    }

    public void setPublicationId(int publicationId) {
        this.publicationId = publicationId;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public String getPubmedId() {
        return pubmedId;
    }

    public void setPubmedId(String pubmedId) {
        this.pubmedId = pubmedId;
    }

    public String getDoi() {
        return doi;
    }

    public void setDoi(String doi) {
        this.doi = doi;
    }

    public String getJournalTitle() {
        return journalTitle;
    }

    public void setJournalTitle(String journalTitle) {
        this.journalTitle = journalTitle;
    }

    public String getArticleTitle() {
        return articleTitle;
    }

    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getIssue() {
        return issue;
    }

    public void setIssue(String issue) {
        this.issue = issue;
    }

    public String getPagefrom() {
        return pagefrom;
    }

    public void setPagefrom(String pagefrom) {
        this.pagefrom = pagefrom;
    }

    public String getPageto() {
        return pageto;
    }

    public void setPageto(String pageto) {
        this.pageto = pageto;
    }

    public String getAuthorList() {
        return authorList;
    }

    public void setAuthorList(String authorList) {
        this.authorList = authorList;
    }
}
