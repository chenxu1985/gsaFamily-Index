package cn.ac.big.bigd.webservice.model.gsa;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * @author chenx
 * @date 20181008
 */
public class SampleAttrMetagenomeEnvironmental {
    private Integer attributeId;
    private Taxon taxon;
    private Sample sample;
    private Taxon hostTaxon;
    private String isolationSource;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date collectionDate;
    private String geographicLocation;
    private String latitudeLongitude;
    private String referenceBiomaterial;
    private String sampleCollectionDevice;
    private String sampleMaterialProcessing;
    private String sampleSize;
    private String sourceMaterialIdentifiers;
    private String description;

    public Integer getAttributeId() {
        return attributeId;
    }

    public void setAttributeId(Integer attributeId) {
        this.attributeId = attributeId;
    }

    public Taxon getTaxon() {
        return taxon;
    }

    public void setTaxon(Taxon taxon) {
        this.taxon = taxon;
    }

    public Sample getSample() {
        return sample;
    }

    public void setSample(Sample sample) {
        this.sample = sample;
    }

    public Taxon getHostTaxon() {
        return hostTaxon;
    }

    public void setHostTaxon(Taxon hostTaxon) {
        this.hostTaxon = hostTaxon;
    }

    public String getIsolationSource() {
        return isolationSource;
    }

    public void setIsolationSource(String isolationSource) {
        this.isolationSource = isolationSource;
    }

    public Date getCollectionDate() {
        return collectionDate;
    }

    public void setCollectionDate(Date collectionDate) {
        this.collectionDate = collectionDate;
    }

    public String getGeographicLocation() {
        return geographicLocation;
    }

    public void setGeographicLocation(String geographicLocation) {
        this.geographicLocation = geographicLocation;
    }

    public String getLatitudeLongitude() {
        return latitudeLongitude;
    }

    public void setLatitudeLongitude(String latitudeLongitude) {
        this.latitudeLongitude = latitudeLongitude;
    }

    public String getReferenceBiomaterial() {
        return referenceBiomaterial;
    }

    public void setReferenceBiomaterial(String referenceBiomaterial) {
        this.referenceBiomaterial = referenceBiomaterial;
    }

    public String getSampleCollectionDevice() {
        return sampleCollectionDevice;
    }

    public void setSampleCollectionDevice(String sampleCollectionDevice) {
        this.sampleCollectionDevice = sampleCollectionDevice;
    }

    public String getSampleMaterialProcessing() {
        return sampleMaterialProcessing;
    }

    public void setSampleMaterialProcessing(String sampleMaterialProcessing) {
        this.sampleMaterialProcessing = sampleMaterialProcessing;
    }

    public String getSampleSize() {
        return sampleSize;
    }

    public void setSampleSize(String sampleSize) {
        this.sampleSize = sampleSize;
    }

    public String getSourceMaterialIdentifiers() {
        return sourceMaterialIdentifiers;
    }

    public void setSourceMaterialIdentifiers(String sourceMaterialIdentifiers) {
        this.sourceMaterialIdentifiers = sourceMaterialIdentifiers;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
