package cn.ac.big.bigd.webservice.model.gsa;


import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * @author chenx
 * @date 20181008
 */
public class SampleAttrMimsmeHumanGutSynchronization {
    private Integer sampleId;
    private Integer attributeId;
    private Integer taxonId;
    private Integer hostOrganismId;
    private Date collectionDate;
    private String geographicLocation;
    private String latitudeLongitude;
    private String environmentBiome;
    private String environmentFeature;
    private String environmentMaterial;
    private String referenceBiomaterial;
    private String sampleCollectionDevice;
    private String sampleMaterialProcessing;
    private String sampleSize;
    private String sampleVolumeWeight;
    private String sourceMaterialIdentifiers;
    private String chemicalAdministration;
    private String gastrointestinalTractDisorder;
    private String hostAge;
    private String hostAgeUnit;
    private String ethnicity;
    private String hostMassIndex;
    private String hostProduct;
    private String hostTemperature;
    private String hostDiet;
    private String hostDisease;
    private String hostFamilyRelationship;
    private String hostGenotype;
    private String hostHeight;
    private String hostLastMeal;
    private String hostOccupation;
    private String hostPhenotype;
    private String hostPulse;
    private Integer sex;
    private String hostSubjectId;
    private String hostTissueSampled;
    private String hostTotalMass;
    private String medicationCode;
    private String liverDisorder;
    private String medicalHistoryPerformed;
    private String miscellaneousParameter;
    private String organismCount;
    private String perturbation;
    private String salinity;
    private String storageDuration;
    private String storageLocation;
    private String storageTemperature;
    private String specialDiet;
    private String temperature;
    private String isolationSource;
    private String description;
    private Integer oxygenationStatus;

    public Integer getAttributeId() {
        return attributeId;
    }

    public void setAttributeId(Integer attributeId) {
        this.attributeId = attributeId;
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

    public String getEnvironmentBiome() {
        return environmentBiome;
    }

    public void setEnvironmentBiome(String environmentBiome) {
        this.environmentBiome = environmentBiome;
    }

    public String getEnvironmentFeature() {
        return environmentFeature;
    }

    public void setEnvironmentFeature(String environmentFeature) {
        this.environmentFeature = environmentFeature;
    }

    public String getEnvironmentMaterial() {
        return environmentMaterial;
    }

    public void setEnvironmentMaterial(String environmentMaterial) {
        this.environmentMaterial = environmentMaterial;
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

    public String getSampleVolumeWeight() {
        return sampleVolumeWeight;
    }

    public void setSampleVolumeWeight(String sampleVolumeWeight) {
        this.sampleVolumeWeight = sampleVolumeWeight;
    }

    public String getSourceMaterialIdentifiers() {
        return sourceMaterialIdentifiers;
    }

    public void setSourceMaterialIdentifiers(String sourceMaterialIdentifiers) {
        this.sourceMaterialIdentifiers = sourceMaterialIdentifiers;
    }

    public String getChemicalAdministration() {
        return chemicalAdministration;
    }

    public void setChemicalAdministration(String chemicalAdministration) {
        this.chemicalAdministration = chemicalAdministration;
    }

    public String getEthnicity() {
        return ethnicity;
    }

    public void setEthnicity(String ethnicity) {
        this.ethnicity = ethnicity;
    }

    public String getGastrointestinalTractDisorder() {
        return gastrointestinalTractDisorder;
    }

    public void setGastrointestinalTractDisorder(String gastrointestinalTractDisorder) {
        this.gastrointestinalTractDisorder = gastrointestinalTractDisorder;
    }

    public String getHostAge() {
        return hostAge;
    }

    public void setHostAge(String hostAge) {
        this.hostAge = hostAge;
    }

    public String getHostMassIndex() {
        return hostMassIndex;
    }

    public void setHostMassIndex(String hostMassIndex) {
        this.hostMassIndex = hostMassIndex;
    }

    public String getHostProduct() {
        return hostProduct;
    }

    public void setHostProduct(String hostProduct) {
        this.hostProduct = hostProduct;
    }

    public String getHostTemperature() {
        return hostTemperature;
    }

    public void setHostTemperature(String hostTemperature) {
        this.hostTemperature = hostTemperature;
    }

    public String getHostDiet() {
        return hostDiet;
    }

    public void setHostDiet(String hostDiet) {
        this.hostDiet = hostDiet;
    }

    public String getHostDisease() {
        return hostDisease;
    }

    public void setHostDisease(String hostDisease) {
        this.hostDisease = hostDisease;
    }

    public String getHostFamilyRelationship() {
        return hostFamilyRelationship;
    }

    public void setHostFamilyRelationship(String hostFamilyRelationship) {
        this.hostFamilyRelationship = hostFamilyRelationship;
    }

    public String getHostGenotype() {
        return hostGenotype;
    }

    public void setHostGenotype(String hostGenotype) {
        this.hostGenotype = hostGenotype;
    }

    public String getHostHeight() {
        return hostHeight;
    }

    public void setHostHeight(String hostHeight) {
        this.hostHeight = hostHeight;
    }

    public String getHostLastMeal() {
        return hostLastMeal;
    }

    public void setHostLastMeal(String hostLastMeal) {
        this.hostLastMeal = hostLastMeal;
    }

    public String getHostOccupation() {
        return hostOccupation;
    }

    public void setHostOccupation(String hostOccupation) {
        this.hostOccupation = hostOccupation;
    }

    public String getHostPhenotype() {
        return hostPhenotype;
    }

    public void setHostPhenotype(String hostPhenotype) {
        this.hostPhenotype = hostPhenotype;
    }

    public String getHostPulse() {
        return hostPulse;
    }

    public void setHostPulse(String hostPulse) {
        this.hostPulse = hostPulse;
    }


    public String getHostSubjectId() {
        return hostSubjectId;
    }

    public void setHostSubjectId(String hostSubjectId) {
        this.hostSubjectId = hostSubjectId;
    }

    public String getHostTissueSampled() {
        return hostTissueSampled;
    }

    public void setHostTissueSampled(String hostTissueSampled) {
        this.hostTissueSampled = hostTissueSampled;
    }

    public String getHostTotalMass() {
        return hostTotalMass;
    }

    public void setHostTotalMass(String hostTotalMass) {
        this.hostTotalMass = hostTotalMass;
    }

    public String getMedicationCode() {
        return medicationCode;
    }

    public void setMedicationCode(String medicationCode) {
        this.medicationCode = medicationCode;
    }

    public String getLiverDisorder() {
        return liverDisorder;
    }

    public void setLiverDisorder(String liverDisorder) {
        this.liverDisorder = liverDisorder;
    }

    public String getMedicalHistoryPerformed() {
        return medicalHistoryPerformed;
    }

    public void setMedicalHistoryPerformed(String medicalHistoryPerformed) {
        this.medicalHistoryPerformed = medicalHistoryPerformed;
    }

    public String getMiscellaneousParameter() {
        return miscellaneousParameter;
    }

    public void setMiscellaneousParameter(String miscellaneousParameter) {
        this.miscellaneousParameter = miscellaneousParameter;
    }

    public String getOrganismCount() {
        return organismCount;
    }

    public void setOrganismCount(String organismCount) {
        this.organismCount = organismCount;
    }


    public String getPerturbation() {
        return perturbation;
    }

    public void setPerturbation(String perturbation) {
        this.perturbation = perturbation;
    }

    public String getSalinity() {
        return salinity;
    }

    public void setSalinity(String salinity) {
        this.salinity = salinity;
    }

    public String getStorageDuration() {
        return storageDuration;
    }

    public void setStorageDuration(String storageDuration) {
        this.storageDuration = storageDuration;
    }

    public String getStorageLocation() {
        return storageLocation;
    }

    public void setStorageLocation(String storageLocation) {
        this.storageLocation = storageLocation;
    }

    public String getStorageTemperature() {
        return storageTemperature;
    }

    public void setStorageTemperature(String storageTemperature) {
        this.storageTemperature = storageTemperature;
    }

    public String getSpecialDiet() {
        return specialDiet;
    }

    public void setSpecialDiet(String specialDiet) {
        this.specialDiet = specialDiet;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getIsolationSource() {
        return isolationSource;
    }

    public void setIsolationSource(String isolationSource) {
        this.isolationSource = isolationSource;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
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

    public String getHostAgeUnit() {
        return hostAgeUnit;
    }

    public void setHostAgeUnit(String hostAgeUnit) {
        this.hostAgeUnit = hostAgeUnit;
    }

    public Integer getOxygenationStatus() {
        return oxygenationStatus;
    }

    public void setOxygenationStatus(Integer oxygenationStatus) {
        this.oxygenationStatus = oxygenationStatus;
    }
}
