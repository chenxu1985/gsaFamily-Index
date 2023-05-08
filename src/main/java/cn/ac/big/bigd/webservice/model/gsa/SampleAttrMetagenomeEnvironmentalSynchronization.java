package cn.ac.big.bigd.webservice.model.gsa;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * @author chenx
 * @date 20181008
 */
public class SampleAttrMetagenomeEnvironmentalSynchronization {
    private Integer sampleId;
    private Integer attributeId;
    private Integer taxonId;
    private Integer hostOrganismId;
    private String isolationSource;
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

    public Integer getSampleId() {
        return sampleId;
    }

    public void setSampleId(Integer sampleId) {
        this.sampleId = sampleId;
    }

    public Integer getTaxonId() {
        return taxonId;
    }

    public void setTaxonId(Integer taxonId) {
        this.taxonId = taxonId;
    }

    public Integer getHostOrganismId() {
        return hostOrganismId;
    }

    public void setHostOrganismId(Integer hostOrganismId) {
        this.hostOrganismId = hostOrganismId;
    }
}
