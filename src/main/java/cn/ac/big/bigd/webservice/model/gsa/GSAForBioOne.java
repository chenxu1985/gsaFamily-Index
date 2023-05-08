package cn.ac.big.bigd.webservice.model.gsa;

import java.util.List;

public class GSAForBioOne {
    private Integer runId;
    private String gsaAccession;
    private String bioProjectAccession;
    private String bioSampleAccession;
    private String experimentAccession;
    private String runAccession;
    private String title;
    private String taxonName;
    private Integer taxonId;
    private String platform;
    private String libraryDesign;
    private String libraryStrategy;
    private String librarySource;
    private String librarySelection;
    private String libraryLayout;
    private Integer insertSize;
    private Integer plannedReadLength;
    private Integer plannedReadLengthMate1;
    private Integer plannedReadLengthMate2;
//    private Integer plannedReadLength1;
//    private Integer plannedReadLength2;
    private String releaseTime;
    private String runDataType;
    private String runFileNameF1;
    private String md5RunFileF1;
    private String runFileNameR2;
    private String md5RunFileR2;
    private String referenceAccesemblyName;
    private String referenceAssemblyUrl;
    private String runFileSizeF1;
    private String runFileSizeR2;
    private String submitterEmail;
    private String submitterAffiliation;
    private String bioProjectTitle;
    private String bioSampleTitle;
//    private String publicationPubmedId;
//    private String publicationArticleTitle;
//    private String publicationJournalTitle;
    private String submitterName;

    private Submitter submitter;
    private List<RunDataFile> runDataFiles;
    private List<Publication> publications;


    public Integer getRunId() {
        return runId;
    }

    public void setRunId(Integer runId) {
        this.runId = runId;
    }

    public String getGsaAccession() {
        return gsaAccession;
    }

    public void setGsaAccession(String gsaAccession) {
        this.gsaAccession = gsaAccession;
    }

    public String getBioProjectAccession() {
        return bioProjectAccession;
    }

    public void setBioProjectAccession(String bioProjectAccession) {
        this.bioProjectAccession = bioProjectAccession;
    }

    public String getBioSampleAccession() {
        return bioSampleAccession;
    }

    public void setBioSampleAccession(String bioSampleAccession) {
        this.bioSampleAccession = bioSampleAccession;
    }

    public String getExperimentAccession() {
        return experimentAccession;
    }

    public void setExperimentAccession(String experimentAccession) {
        this.experimentAccession = experimentAccession;
    }

    public String getRunAccession() {
        return runAccession;
    }

    public void setRunAccession(String runAccession) {
        this.runAccession = runAccession;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTaxonName() {
        return taxonName;
    }

    public void setTaxonName(String taxonName) {
        this.taxonName = taxonName;
    }

    public Integer getTaxonId() {
        return taxonId;
    }

    public void setTaxonId(Integer taxonId) {
        this.taxonId = taxonId;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getLibraryDesign() {
        return libraryDesign;
    }

    public void setLibraryDesign(String libraryDesign) {
        this.libraryDesign = libraryDesign;
    }

    public String getLibraryStrategy() {
        return libraryStrategy;
    }

    public void setLibraryStrategy(String libraryStrategy) {
        this.libraryStrategy = libraryStrategy;
    }

    public String getLibrarySource() {
        return librarySource;
    }

    public void setLibrarySource(String librarySource) {
        this.librarySource = librarySource;
    }

    public String getLibrarySelection() {
        return librarySelection;
    }

    public void setLibrarySelection(String librarySelection) {
        this.librarySelection = librarySelection;
    }

    public String getLibraryLayout() {
        return libraryLayout;
    }

    public void setLibraryLayout(String libraryLayout) {
        this.libraryLayout = libraryLayout;
    }

    public Integer getInsertSize() {
        return insertSize;
    }

    public void setInsertSize(Integer insertSize) {
        this.insertSize = insertSize;
    }

    public Integer getPlannedReadLength() {
        return plannedReadLength;
    }

    public void setPlannedReadLength(Integer plannedReadLength) {
        this.plannedReadLength = plannedReadLength;
    }

    public Integer getPlannedReadLengthMate1() {
        return plannedReadLengthMate1;
    }

    public void setPlannedReadLengthMate1(Integer plannedReadLengthMate1) {
        this.plannedReadLengthMate1 = plannedReadLengthMate1;
    }

    public Integer getPlannedReadLengthMate2() {
        return plannedReadLengthMate2;
    }

    public void setPlannedReadLengthMate2(Integer plannedReadLengthMate2) {
        this.plannedReadLengthMate2 = plannedReadLengthMate2;
    }
//    public Integer getPlannedReadLength1() {
//        return plannedReadLength1;
//    }
//
//    public void setPlannedReadLength1(Integer plannedReadLength1) {
//        this.plannedReadLength1 = plannedReadLength1;
//    }
//
//    public Integer getPlannedReadLength2() {
//        return plannedReadLength2;
//    }
//
//    public void setPlannedReadLength2(Integer plannedReadLength2) {
//        this.plannedReadLength2 = plannedReadLength2;
//    }

    public String getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(String releaseTime) {
        this.releaseTime = releaseTime;
    }

    public String getRunDataType() {
        return runDataType;
    }

    public void setRunDataType(String runDataType) {
        this.runDataType = runDataType;
    }

    public String getRunFileNameF1() {
        return runFileNameF1;
    }

    public void setRunFileNameF1(String runFileNameF1) {
        this.runFileNameF1 = runFileNameF1;
    }

    public String getMd5RunFileF1() {
        return md5RunFileF1;
    }

    public void setMd5RunFileF1(String md5RunFileF1) {
        this.md5RunFileF1 = md5RunFileF1;
    }

    public String getRunFileNameR2() {
        return runFileNameR2;
    }

    public void setRunFileNameR2(String runFileNameR2) {
        this.runFileNameR2 = runFileNameR2;
    }

    public String getMd5RunFileR2() {
        return md5RunFileR2;
    }

    public void setMd5RunFileR2(String md5RunFileR2) {
        this.md5RunFileR2 = md5RunFileR2;
    }

    public String getReferenceAccesemblyName() {
        return referenceAccesemblyName;
    }

    public void setReferenceAccesemblyName(String referenceAccesemblyName) {
        this.referenceAccesemblyName = referenceAccesemblyName;
    }

    public String getReferenceAssemblyUrl() {
        return referenceAssemblyUrl;
    }

    public void setReferenceAssemblyUrl(String referenceAssemblyUrl) {
        this.referenceAssemblyUrl = referenceAssemblyUrl;
    }

    public String getRunFileSizeF1() {
        return runFileSizeF1;
    }

    public void setRunFileSizeF1(String runFileSizeF1) {
        this.runFileSizeF1 = runFileSizeF1;
    }

    public String getRunFileSizeR2() {
        return runFileSizeR2;
    }

    public void setRunFileSizeR2(String runFileSizeR2) {
        this.runFileSizeR2 = runFileSizeR2;
    }

    public String getSubmitterEmail() {
        return submitterEmail;
    }

    public void setSubmitterEmail(String submitterEmail) {
        this.submitterEmail = submitterEmail;
    }

    public String getSubmitterAffiliation() {
        return submitterAffiliation;
    }

    public void setSubmitterAffiliation(String submitterAffiliation) {
        this.submitterAffiliation = submitterAffiliation;
    }

    public String getBioProjectTitle() {
        return bioProjectTitle;
    }

    public void setBioProjectTitle(String bioProjectTitle) {
        this.bioProjectTitle = bioProjectTitle;
    }

    public String getBioSampleTitle() {
        return bioSampleTitle;
    }

    public void setBioSampleTitle(String bioSampleTitle) {
        this.bioSampleTitle = bioSampleTitle;
    }

//    public String getPublicationPubmedId() {
//        return publicationPubmedId;
//    }
//
//    public void setPublicationPubmedId(String publicationPubmedId) {
//        this.publicationPubmedId = publicationPubmedId;
//    }
//
//    public String getPublicationArticleTitle() {
//        return publicationArticleTitle;
//    }
//
//    public void setPublicationArticleTitle(String publicationArticleTitle) {
//        this.publicationArticleTitle = publicationArticleTitle;
//    }
//
//    public String getPublicationJournalTitle() {
//        return publicationJournalTitle;
//    }String
//
//    public void setPublicationJournalTitle(String publicationJournalTitle) {
//        this.publicationJournalTitle = publicationJournalTitle;
//    }

    public String getSubmitterName() {
        return submitterName;
    }

    public void setSubmitterName(String submitterName) {
        this.submitterName = submitterName;
    }

    public Submitter getSubmitter() {
        return submitter;
    }

    public void setSubmitter(Submitter submitter) {
        this.submitter = submitter;
    }

    public List<RunDataFile> getRunDataFiles() {
        return runDataFiles;
    }

    public void setRunDataFiles(List<RunDataFile> runDataFiles) {
        this.runDataFiles = runDataFiles;
    }

    public List<Publication> getPublications() {
        return publications;
    }

    public void setPublications(List<Publication> publications) {
        this.publications = publications;
    }
}
