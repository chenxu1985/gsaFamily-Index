package cn.ac.big.bigd.webservice.model.gsa;


import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
/**
 * @author chenx
 * @date 20181008
 */
public class SampleAttrMimsmeSoil {
    private Integer attributeId;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date collectionDate;
    private String environmentBiome;
    private String environmentFeature;
    private String environmentMaterial;
    private String geographicLocation;
    private String latitudeLongitude;
    private String depth;
    private String elevation;
    private String agrochemicalAdditions;
    private String aluminiumSaturation;
    private String aluminiumSaturationMethod;
    private String altitude;
    private String annualSeasonalPrecipitation;
    private String annualSeasonalTemperature;
    private String cropRotation;
    private String currentVegetation;
    private String currentVegetationMethod;
    private String drainageClassification;
    private String extremeEvent;
    private String extremeSalinity;
    private String faoClassification;
    private String fire;
    private String flooding;
    private String heavyMetals;
    private String heavyMetalsMethod;
    private String horizon;
    private String horizonMethod;
    private String linksAdditionalAnalysis;
    private String linkClassificationInformation;
    private String linkClimateInformation;
    private String localClassification;
    private String localClassificationMethod;
    private String microbialBiomass;
    private String microbialBiomassMethod;
    private String miscellaneousParameter;
    private String ph;
    private String phMethod;
    private String poolingDnaExtracts;
    private String previousLandUse;
    private String previousLandUseMethod;
    private String profilePosition;
    private String salinityMethod;
    private String sieving;
    private String slopeAspect;
    private String soilType;
    private String slopeGradient;
    private String soilTypeMethod;
    private String storageConditions;
    private String texture;
    private String textureMethod;
    private String totalNMethod;
    private String totalNitrogen;
    private String totalOrganicCarbonMethod;
    private String totalOrganicCarbon;
    private String waterContentSoil;
    private String waterContentSoilMethod;
    private String referenceBiomaterial;
    private String sampleCollectionDevice;
    private String sampleMaterialProcessing;
    private String sampleSize;
    private String sampleVolumeWeightDnaExtraction;
    private String sourceMaterialIdentifiers;
    private String isolationSource;
    private String description;

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

    public String getDepth() {
        return depth;
    }

    public void setDepth(String depth) {
        this.depth = depth;
    }

    public String getElevation() {
        return elevation;
    }

    public void setElevation(String elevation) {
        this.elevation = elevation;
    }

    public String getAgrochemicalAdditions() {
        return agrochemicalAdditions;
    }

    public void setAgrochemicalAdditions(String agrochemicalAdditions) {
        this.agrochemicalAdditions = agrochemicalAdditions;
    }

    public String getAluminiumSaturation() {
        return aluminiumSaturation;
    }

    public void setAluminiumSaturation(String aluminiumSaturation) {
        this.aluminiumSaturation = aluminiumSaturation;
    }

    public String getAluminiumSaturationMethod() {
        return aluminiumSaturationMethod;
    }

    public void setAluminiumSaturationMethod(String aluminiumSaturationMethod) {
        this.aluminiumSaturationMethod = aluminiumSaturationMethod;
    }

    public String getAltitude() {
        return altitude;
    }

    public void setAltitude(String altitude) {
        this.altitude = altitude;
    }

    public String getAnnualSeasonalPrecipitation() {
        return annualSeasonalPrecipitation;
    }

    public void setAnnualSeasonalPrecipitation(String annualSeasonalPrecipitation) {
        this.annualSeasonalPrecipitation = annualSeasonalPrecipitation;
    }

    public String getAnnualSeasonalTemperature() {
        return annualSeasonalTemperature;
    }

    public void setAnnualSeasonalTemperature(String annualSeasonalTemperature) {
        this.annualSeasonalTemperature = annualSeasonalTemperature;
    }

    public String getCropRotation() {
        return cropRotation;
    }

    public void setCropRotation(String cropRotation) {
        this.cropRotation = cropRotation;
    }

    public String getCurrentVegetation() {
        return currentVegetation;
    }

    public void setCurrentVegetation(String currentVegetation) {
        this.currentVegetation = currentVegetation;
    }

    public String getCurrentVegetationMethod() {
        return currentVegetationMethod;
    }

    public void setCurrentVegetationMethod(String currentVegetationMethod) {
        this.currentVegetationMethod = currentVegetationMethod;
    }

    public String getDrainageClassification() {
        return drainageClassification;
    }

    public void setDrainageClassification(String drainageClassification) {
        this.drainageClassification = drainageClassification;
    }

    public String getExtremeEvent() {
        return extremeEvent;
    }

    public void setExtremeEvent(String extremeEvent) {
        this.extremeEvent = extremeEvent;
    }

    public String getExtremeSalinity() {
        return extremeSalinity;
    }

    public void setExtremeSalinity(String extremeSalinity) {
        this.extremeSalinity = extremeSalinity;
    }

    public String getFaoClassification() {
        return faoClassification;
    }

    public void setFaoClassification(String faoClassification) {
        this.faoClassification = faoClassification;
    }

    public String getFire() {
        return fire;
    }

    public void setFire(String fire) {
        this.fire = fire;
    }

    public String getFlooding() {
        return flooding;
    }

    public void setFlooding(String flooding) {
        this.flooding = flooding;
    }

    public String getHeavyMetals() {
        return heavyMetals;
    }

    public void setHeavyMetals(String heavyMetals) {
        this.heavyMetals = heavyMetals;
    }

    public String getHeavyMetalsMethod() {
        return heavyMetalsMethod;
    }

    public void setHeavyMetalsMethod(String heavyMetalsMethod) {
        this.heavyMetalsMethod = heavyMetalsMethod;
    }

    public String getHorizon() {
        return horizon;
    }

    public void setHorizon(String horizon) {
        this.horizon = horizon;
    }

    public String getHorizonMethod() {
        return horizonMethod;
    }

    public void setHorizonMethod(String horizonMethod) {
        this.horizonMethod = horizonMethod;
    }

    public String getLinksAdditionalAnalysis() {
        return linksAdditionalAnalysis;
    }

    public void setLinksAdditionalAnalysis(String linksAdditionalAnalysis) {
        this.linksAdditionalAnalysis = linksAdditionalAnalysis;
    }

    public String getLinkClassificationInformation() {
        return linkClassificationInformation;
    }

    public void setLinkClassificationInformation(String linkClassificationInformation) {
        this.linkClassificationInformation = linkClassificationInformation;
    }

    public String getLinkClimateInformation() {
        return linkClimateInformation;
    }

    public void setLinkClimateInformation(String linkClimateInformation) {
        this.linkClimateInformation = linkClimateInformation;
    }

    public String getLocalClassification() {
        return localClassification;
    }

    public void setLocalClassification(String localClassification) {
        this.localClassification = localClassification;
    }

    public String getLocalClassificationMethod() {
        return localClassificationMethod;
    }

    public void setLocalClassificationMethod(String localClassificationMethod) {
        this.localClassificationMethod = localClassificationMethod;
    }

    public String getMicrobialBiomass() {
        return microbialBiomass;
    }

    public void setMicrobialBiomass(String microbialBiomass) {
        this.microbialBiomass = microbialBiomass;
    }

    public String getMicrobialBiomassMethod() {
        return microbialBiomassMethod;
    }

    public void setMicrobialBiomassMethod(String microbialBiomassMethod) {
        this.microbialBiomassMethod = microbialBiomassMethod;
    }

    public String getMiscellaneousParameter() {
        return miscellaneousParameter;
    }

    public void setMiscellaneousParameter(String miscellaneousParameter) {
        this.miscellaneousParameter = miscellaneousParameter;
    }

    public String getPh() {
        return ph;
    }

    public void setPh(String ph) {
        this.ph = ph;
    }

    public String getPhMethod() {
        return phMethod;
    }

    public void setPhMethod(String phMethod) {
        this.phMethod = phMethod;
    }

    public String getPoolingDnaExtracts() {
        return poolingDnaExtracts;
    }

    public void setPoolingDnaExtracts(String poolingDnaExtracts) {
        this.poolingDnaExtracts = poolingDnaExtracts;
    }

    public String getPreviousLandUse() {
        return previousLandUse;
    }

    public void setPreviousLandUse(String previousLandUse) {
        this.previousLandUse = previousLandUse;
    }

    public String getPreviousLandUseMethod() {
        return previousLandUseMethod;
    }

    public void setPreviousLandUseMethod(String previousLandUseMethod) {
        this.previousLandUseMethod = previousLandUseMethod;
    }

    public String getProfilePosition() {
        return profilePosition;
    }

    public void setProfilePosition(String profilePosition) {
        this.profilePosition = profilePosition;
    }

    public String getSalinityMethod() {
        return salinityMethod;
    }

    public void setSalinityMethod(String salinityMethod) {
        this.salinityMethod = salinityMethod;
    }

    public String getSieving() {
        return sieving;
    }

    public void setSieving(String sieving) {
        this.sieving = sieving;
    }

    public String getSlopeAspect() {
        return slopeAspect;
    }

    public void setSlopeAspect(String slopeAspect) {
        this.slopeAspect = slopeAspect;
    }

    public String getSoilType() {
        return soilType;
    }

    public void setSoilType(String soilType) {
        this.soilType = soilType;
    }

    public String getSlopeGradient() {
        return slopeGradient;
    }

    public void setSlopeGradient(String slopeGradient) {
        this.slopeGradient = slopeGradient;
    }

    public String getSoilTypeMethod() {
        return soilTypeMethod;
    }

    public void setSoilTypeMethod(String soilTypeMethod) {
        this.soilTypeMethod = soilTypeMethod;
    }

    public String getStorageConditions() {
        return storageConditions;
    }

    public void setStorageConditions(String storageConditions) {
        this.storageConditions = storageConditions;
    }

    public String getTexture() {
        return texture;
    }

    public void setTexture(String texture) {
        this.texture = texture;
    }

    public String getTextureMethod() {
        return textureMethod;
    }

    public void setTextureMethod(String textureMethod) {
        this.textureMethod = textureMethod;
    }


    public String getTotalNMethod() {
        return totalNMethod;
    }

    public void setTotalNMethod(String totalNMethod) {
        this.totalNMethod = totalNMethod;
    }

    public String getTotalNitrogen() {
        return totalNitrogen;
    }

    public void setTotalNitrogen(String totalNitrogen) {
        this.totalNitrogen = totalNitrogen;
    }

    public String getTotalOrganicCarbonMethod() {
        return totalOrganicCarbonMethod;
    }

    public void setTotalOrganicCarbonMethod(String totalOrganicCarbonMethod) {
        this.totalOrganicCarbonMethod = totalOrganicCarbonMethod;
    }

    public String getTotalOrganicCarbon() {
        return totalOrganicCarbon;
    }

    public void setTotalOrganicCarbon(String totalOrganicCarbon) {
        this.totalOrganicCarbon = totalOrganicCarbon;
    }

    public String getWaterContentSoil() {
        return waterContentSoil;
    }

    public void setWaterContentSoil(String waterContentSoil) {
        this.waterContentSoil = waterContentSoil;
    }

    public String getWaterContentSoilMethod() {
        return waterContentSoilMethod;
    }

    public void setWaterContentSoilMethod(String waterContentSoilMethod) {
        this.waterContentSoilMethod = waterContentSoilMethod;
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

    public String getSampleVolumeWeightDnaExtraction() {
        return sampleVolumeWeightDnaExtraction;
    }

    public void setSampleVolumeWeightDnaExtraction(String sampleVolumeWeightDnaExtraction) {
        this.sampleVolumeWeightDnaExtraction = sampleVolumeWeightDnaExtraction;
    }

    public String getSourceMaterialIdentifiers() {
        return sourceMaterialIdentifiers;
    }

    public void setSourceMaterialIdentifiers(String sourceMaterialIdentifiers) {
        this.sourceMaterialIdentifiers = sourceMaterialIdentifiers;
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
}
