package cn.ac.big.bigd.webservice.model.gsa;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
/**
 * @author chenx
 * @date 20181008
 */
public class SampleAttrMimsmeWater {
    private Integer attributeId;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date collectionDate;
    private String environmentBiome;
    private String environmentFeature;
    private String environmentMaterial;
    private String geographicLocation;
    private String latitudeLongitude;
    private String depth;
    private String alkalinity;
    private String alkylDiethers;
    private String altitude;
    private String aminopeptidaseActivity;
    private String ammonium;
    private String atmosphericData;
    private String bacterialProduction;
    private String bacterialRespiration;
    private String bacterialCarbonProduction;
    private String biomass;
    private String bishomohopanol;
    private String bromide;
    private String calcium;
    private String carbonNitrogenRatio;
    private String chemicalAdministration;
    private String chloride;
    private String chlorophyll;
    private String conductivity;
    private String density;
    private String dietherLipids;
    private String dissolvedCarbonDioxide;
    private String dissolvedHydrogen;
    private String dissolvedInorganicCarbon;
    private String dissolvedInorganicNitrogen;
    private String dissolvedInorganicPhosphorus;
    private String dissolvedOrganicCarbon;
    private String dissolvedOrganicNitrogen;
    private String dissolvedOxygen;
    private String downwardPar;
    private String elevation;
    private String fluorescence;
    private String glucosidaseActivity;
    private String lightIntensity;
    private String magnesium;
    private String meanFrictionVelocity;
    private String meanPeakFrictionVelocity;
    private String miscellaneousParameter;
    private String nAlkanes;
    private String nitrate;
    private String nitrite;
    private String nitrogen;
    private String organicCarbon;
    private String organicMatter;
    private String organicNitrogen;
    private String organismCount;
    private String oxygenationStatus;
    private String particulateOrganicCarbon;
    private String particulateOrganicNitrogen;
    private String perturbation;
    private String petroleumHydrocarbon;
    private String ph;
    private String phaeopigments;
    private String phosphate;
    private String phospholipidFattyAcid;
    private String photonFlux;
    private String potassium;
    private String pressure;
    private String primaryProduction;
    private String redoxPotential;
    private String salinity;
    private String sampleStorageDuration;
    private String sampleStorageLocation;
    private String sampleStorageTemperature;
    private String silicate;
    private String sodium;
    private String solubleReactivePhosphorus;
    private String sulfate;
    private String sulfide;
    private String suspendedParticulateMatter;
    private String temperature;
    private String tidalStage;
    private String totalDepthWaterColumn;
    private String totalDissolvedNitrogen;
    private String totalInorganicNitrogen;
    private String totalNitrogen;
    private String totalParticulateCarbon;
    private String totalPhosphorus;
    private String waterCurrent;
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

    public String getAlkalinity() {
        return alkalinity;
    }

    public void setAlkalinity(String alkalinity) {
        this.alkalinity = alkalinity;
    }

    public String getAlkylDiethers() {
        return alkylDiethers;
    }

    public void setAlkylDiethers(String alkylDiethers) {
        this.alkylDiethers = alkylDiethers;
    }

    public String getAltitude() {
        return altitude;
    }

    public void setAltitude(String altitude) {
        this.altitude = altitude;
    }

    public String getAminopeptidaseActivity() {
        return aminopeptidaseActivity;
    }

    public void setAminopeptidaseActivity(String aminopeptidaseActivity) {
        this.aminopeptidaseActivity = aminopeptidaseActivity;
    }

    public String getAmmonium() {
        return ammonium;
    }

    public void setAmmonium(String ammonium) {
        this.ammonium = ammonium;
    }

    public String getAtmosphericData() {
        return atmosphericData;
    }

    public void setAtmosphericData(String atmosphericData) {
        this.atmosphericData = atmosphericData;
    }

    public String getBacterialProduction() {
        return bacterialProduction;
    }

    public void setBacterialProduction(String bacterialProduction) {
        this.bacterialProduction = bacterialProduction;
    }

    public String getBacterialRespiration() {
        return bacterialRespiration;
    }

    public void setBacterialRespiration(String bacterialRespiration) {
        this.bacterialRespiration = bacterialRespiration;
    }

    public String getBacterialCarbonProduction() {
        return bacterialCarbonProduction;
    }

    public void setBacterialCarbonProduction(String bacterialCarbonProduction) {
        this.bacterialCarbonProduction = bacterialCarbonProduction;
    }

    public String getBiomass() {
        return biomass;
    }

    public void setBiomass(String biomass) {
        this.biomass = biomass;
    }

    public String getBishomohopanol() {
        return bishomohopanol;
    }

    public void setBishomohopanol(String bishomohopanol) {
        this.bishomohopanol = bishomohopanol;
    }

    public String getBromide() {
        return bromide;
    }

    public void setBromide(String bromide) {
        this.bromide = bromide;
    }

    public String getCalcium() {
        return calcium;
    }

    public void setCalcium(String calcium) {
        this.calcium = calcium;
    }

    public String getCarbonNitrogenRatio() {
        return carbonNitrogenRatio;
    }

    public void setCarbonNitrogenRatio(String carbonNitrogenRatio) {
        this.carbonNitrogenRatio = carbonNitrogenRatio;
    }

    public String getChemicalAdministration() {
        return chemicalAdministration;
    }

    public void setChemicalAdministration(String chemicalAdministration) {
        this.chemicalAdministration = chemicalAdministration;
    }

    public String getChloride() {
        return chloride;
    }

    public void setChloride(String chloride) {
        this.chloride = chloride;
    }

    public String getChlorophyll() {
        return chlorophyll;
    }

    public void setChlorophyll(String chlorophyll) {
        this.chlorophyll = chlorophyll;
    }

    public String getConductivity() {
        return conductivity;
    }

    public void setConductivity(String conductivity) {
        this.conductivity = conductivity;
    }

    public String getDensity() {
        return density;
    }

    public void setDensity(String density) {
        this.density = density;
    }

    public String getDietherLipids() {
        return dietherLipids;
    }

    public void setDietherLipids(String dietherLipids) {
        this.dietherLipids = dietherLipids;
    }

    public String getDissolvedCarbonDioxide() {
        return dissolvedCarbonDioxide;
    }

    public void setDissolvedCarbonDioxide(String dissolvedCarbonDioxide) {
        this.dissolvedCarbonDioxide = dissolvedCarbonDioxide;
    }

    public String getDissolvedHydrogen() {
        return dissolvedHydrogen;
    }

    public void setDissolvedHydrogen(String dissolvedHydrogen) {
        this.dissolvedHydrogen = dissolvedHydrogen;
    }

    public String getDissolvedInorganicCarbon() {
        return dissolvedInorganicCarbon;
    }

    public void setDissolvedInorganicCarbon(String dissolvedInorganicCarbon) {
        this.dissolvedInorganicCarbon = dissolvedInorganicCarbon;
    }

    public String getDissolvedInorganicNitrogen() {
        return dissolvedInorganicNitrogen;
    }

    public void setDissolvedInorganicNitrogen(String dissolvedInorganicNitrogen) {
        this.dissolvedInorganicNitrogen = dissolvedInorganicNitrogen;
    }

    public String getDissolvedInorganicPhosphorus() {
        return dissolvedInorganicPhosphorus;
    }

    public void setDissolvedInorganicPhosphorus(String dissolvedInorganicPhosphorus) {
        this.dissolvedInorganicPhosphorus = dissolvedInorganicPhosphorus;
    }

    public String getDissolvedOrganicCarbon() {
        return dissolvedOrganicCarbon;
    }

    public void setDissolvedOrganicCarbon(String dissolvedOrganicCarbon) {
        this.dissolvedOrganicCarbon = dissolvedOrganicCarbon;
    }

    public String getDissolvedOrganicNitrogen() {
        return dissolvedOrganicNitrogen;
    }

    public void setDissolvedOrganicNitrogen(String dissolvedOrganicNitrogen) {
        this.dissolvedOrganicNitrogen = dissolvedOrganicNitrogen;
    }

    public String getDissolvedOxygen() {
        return dissolvedOxygen;
    }

    public void setDissolvedOxygen(String dissolvedOxygen) {
        this.dissolvedOxygen = dissolvedOxygen;
    }

    public String getDownwardPar() {
        return downwardPar;
    }

    public void setDownwardPar(String downwardPar) {
        this.downwardPar = downwardPar;
    }

    public String getElevation() {
        return elevation;
    }

    public void setElevation(String elevation) {
        this.elevation = elevation;
    }

    public String getFluorescence() {
        return fluorescence;
    }

    public void setFluorescence(String fluorescence) {
        this.fluorescence = fluorescence;
    }

    public String getGlucosidaseActivity() {
        return glucosidaseActivity;
    }

    public void setGlucosidaseActivity(String glucosidaseActivity) {
        this.glucosidaseActivity = glucosidaseActivity;
    }

    public String getLightIntensity() {
        return lightIntensity;
    }

    public void setLightIntensity(String lightIntensity) {
        this.lightIntensity = lightIntensity;
    }

    public String getMagnesium() {
        return magnesium;
    }

    public void setMagnesium(String magnesium) {
        this.magnesium = magnesium;
    }

    public String getMeanFrictionVelocity() {
        return meanFrictionVelocity;
    }

    public void setMeanFrictionVelocity(String meanFrictionVelocity) {
        this.meanFrictionVelocity = meanFrictionVelocity;
    }

    public String getMeanPeakFrictionVelocity() {
        return meanPeakFrictionVelocity;
    }

    public void setMeanPeakFrictionVelocity(String meanPeakFrictionVelocity) {
        this.meanPeakFrictionVelocity = meanPeakFrictionVelocity;
    }

    public String getMiscellaneousParameter() {
        return miscellaneousParameter;
    }

    public void setMiscellaneousParameter(String miscellaneousParameter) {
        this.miscellaneousParameter = miscellaneousParameter;
    }

    public String getnAlkanes() {
        return nAlkanes;
    }

    public void setnAlkanes(String nAlkanes) {
        this.nAlkanes = nAlkanes;
    }

    public String getNitrate() {
        return nitrate;
    }

    public void setNitrate(String nitrate) {
        this.nitrate = nitrate;
    }

    public String getNitrite() {
        return nitrite;
    }

    public void setNitrite(String nitrite) {
        this.nitrite = nitrite;
    }

    public String getNitrogen() {
        return nitrogen;
    }

    public void setNitrogen(String nitrogen) {
        this.nitrogen = nitrogen;
    }

    public String getOrganicCarbon() {
        return organicCarbon;
    }

    public void setOrganicCarbon(String organicCarbon) {
        this.organicCarbon = organicCarbon;
    }

    public String getOrganicMatter() {
        return organicMatter;
    }

    public void setOrganicMatter(String organicMatter) {
        this.organicMatter = organicMatter;
    }

    public String getOrganicNitrogen() {
        return organicNitrogen;
    }

    public void setOrganicNitrogen(String organicNitrogen) {
        this.organicNitrogen = organicNitrogen;
    }

    public String getOrganismCount() {
        return organismCount;
    }

    public void setOrganismCount(String organismCount) {
        this.organismCount = organismCount;
    }

    public String getOxygenationStatus() {
        return oxygenationStatus;
    }

    public void setOxygenationStatus(String oxygenationStatus) {
        this.oxygenationStatus = oxygenationStatus;
    }

    public String getParticulateOrganicCarbon() {
        return particulateOrganicCarbon;
    }

    public void setParticulateOrganicCarbon(String particulateOrganicCarbon) {
        this.particulateOrganicCarbon = particulateOrganicCarbon;
    }

    public String getParticulateOrganicNitrogen() {
        return particulateOrganicNitrogen;
    }

    public void setParticulateOrganicNitrogen(String particulateOrganicNitrogen) {
        this.particulateOrganicNitrogen = particulateOrganicNitrogen;
    }

    public String getPerturbation() {
        return perturbation;
    }

    public void setPerturbation(String perturbation) {
        this.perturbation = perturbation;
    }

    public String getPetroleumHydrocarbon() {
        return petroleumHydrocarbon;
    }

    public void setPetroleumHydrocarbon(String petroleumHydrocarbon) {
        this.petroleumHydrocarbon = petroleumHydrocarbon;
    }

    public String getPh() {
        return ph;
    }

    public void setPh(String ph) {
        this.ph = ph;
    }

    public String getPhaeopigments() {
        return phaeopigments;
    }

    public void setPhaeopigments(String phaeopigments) {
        this.phaeopigments = phaeopigments;
    }

    public String getPhosphate() {
        return phosphate;
    }

    public void setPhosphate(String phosphate) {
        this.phosphate = phosphate;
    }

    public String getPhospholipidFattyAcid() {
        return phospholipidFattyAcid;
    }

    public void setPhospholipidFattyAcid(String phospholipidFattyAcid) {
        this.phospholipidFattyAcid = phospholipidFattyAcid;
    }

    public String getPhotonFlux() {
        return photonFlux;
    }

    public void setPhotonFlux(String photonFlux) {
        this.photonFlux = photonFlux;
    }

    public String getPotassium() {
        return potassium;
    }

    public void setPotassium(String potassium) {
        this.potassium = potassium;
    }

    public String getPressure() {
        return pressure;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }

    public String getPrimaryProduction() {
        return primaryProduction;
    }

    public void setPrimaryProduction(String primaryProduction) {
        this.primaryProduction = primaryProduction;
    }

    public String getRedoxPotential() {
        return redoxPotential;
    }

    public void setRedoxPotential(String redoxPotential) {
        this.redoxPotential = redoxPotential;
    }

    public String getSalinity() {
        return salinity;
    }

    public void setSalinity(String salinity) {
        this.salinity = salinity;
    }

    public String getSampleStorageDuration() {
        return sampleStorageDuration;
    }

    public void setSampleStorageDuration(String sampleStorageDuration) {
        this.sampleStorageDuration = sampleStorageDuration;
    }

    public String getSampleStorageLocation() {
        return sampleStorageLocation;
    }

    public void setSampleStorageLocation(String sampleStorageLocation) {
        this.sampleStorageLocation = sampleStorageLocation;
    }

    public String getSampleStorageTemperature() {
        return sampleStorageTemperature;
    }

    public void setSampleStorageTemperature(String sampleStorageTemperature) {
        this.sampleStorageTemperature = sampleStorageTemperature;
    }

    public String getSilicate() {
        return silicate;
    }

    public void setSilicate(String silicate) {
        this.silicate = silicate;
    }

    public String getSodium() {
        return sodium;
    }

    public void setSodium(String sodium) {
        this.sodium = sodium;
    }

    public String getSolubleReactivePhosphorus() {
        return solubleReactivePhosphorus;
    }

    public void setSolubleReactivePhosphorus(String solubleReactivePhosphorus) {
        this.solubleReactivePhosphorus = solubleReactivePhosphorus;
    }

    public String getSulfate() {
        return sulfate;
    }

    public void setSulfate(String sulfate) {
        this.sulfate = sulfate;
    }

    public String getSulfide() {
        return sulfide;
    }

    public void setSulfide(String sulfide) {
        this.sulfide = sulfide;
    }

    public String getSuspendedParticulateMatter() {
        return suspendedParticulateMatter;
    }

    public void setSuspendedParticulateMatter(String suspendedParticulateMatter) {
        this.suspendedParticulateMatter = suspendedParticulateMatter;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getTidalStage() {
        return tidalStage;
    }

    public void setTidalStage(String tidalStage) {
        this.tidalStage = tidalStage;
    }

    public String getTotalDepthWaterColumn() {
        return totalDepthWaterColumn;
    }

    public void setTotalDepthWaterColumn(String totalDepthWaterColumn) {
        this.totalDepthWaterColumn = totalDepthWaterColumn;
    }

    public String getTotalDissolvedNitrogen() {
        return totalDissolvedNitrogen;
    }

    public void setTotalDissolvedNitrogen(String totalDissolvedNitrogen) {
        this.totalDissolvedNitrogen = totalDissolvedNitrogen;
    }

    public String getTotalInorganicNitrogen() {
        return totalInorganicNitrogen;
    }

    public void setTotalInorganicNitrogen(String totalInorganicNitrogen) {
        this.totalInorganicNitrogen = totalInorganicNitrogen;
    }

    public String getTotalNitrogen() {
        return totalNitrogen;
    }

    public void setTotalNitrogen(String totalNitrogen) {
        this.totalNitrogen = totalNitrogen;
    }

    public String getTotalParticulateCarbon() {
        return totalParticulateCarbon;
    }

    public void setTotalParticulateCarbon(String totalParticulateCarbon) {
        this.totalParticulateCarbon = totalParticulateCarbon;
    }

    public String getTotalPhosphorus() {
        return totalPhosphorus;
    }

    public void setTotalPhosphorus(String totalPhosphorus) {
        this.totalPhosphorus = totalPhosphorus;
    }

    public String getWaterCurrent() {
        return waterCurrent;
    }

    public void setWaterCurrent(String waterCurrent) {
        this.waterCurrent = waterCurrent;
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
