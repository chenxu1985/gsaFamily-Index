package cn.ac.big.bigd.webservice.model.index;

import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenx on 2024-04-03.
 */
public class SampleType11 {
    private String center;
    private String accession;
    private String sampleAcc;
    private String name;
    private String title;
    private Integer taxonId;
    private String organism;
    private String organization;
    private String releaseTime;
    private String sampleType;
    private List<Use> useBy;
    private JSONObject attributes;
    private String collectionDate;
    private String environmentBiome;
    private String environmentFeature;
    private String environmentMaterial;
    private String geographicLocation;
    private String latitudeLongitude;
    private String isolationSource;
    private String depth;
    private String alkalinity;

//    private String alkylDiethers;
//    private String altitude;
//    private String aminopeptidaseActivity;
//    private String ammonium;
//    private String atmosphericData;
//    private String bacterialProduction;
//    private String bacterialRespiration;
//    private String bacterialCarbonProduction;
//    private String biomass;
//    private String bishomohopanol;
//    private String bromide;
//    private String calcium;
//    private String carbonNitrogenRatio;
//    private String chemicalAdministration;
//    private String chloride;
//    private String chlorophyll;
//    private String conductivity;
//    private String density;
//    private String dietherLipids;
//    private String dissolvedCarbonDioxide;
//    private String dissolvedHydrogen;
//    private String dissolvedInorganicCarbon;
//    private String dissolvedInorganicNitrogen;
//    private String dissolvedInorganicPhosphorus;
//    private String dissolvedOrganicCarbon;
//    private String dissolvedOrganicNitrogen;
//    private String dissolvedOxygen;
//    private String downwardPar;
//    private String elevation;
//    private String fluorescence;
//    private String glucosidaseActivity;
//    private String lightIntensity;
//    private String magnesium;
//    private String meanFrictionVelocity;
//    private String meanPeakFrictionVelocity;
//    private String miscellaneousParameter;
//    private String nAlkanes;
//    private String nitrate;
//    private String nitrite;
//    private String nitrogen;
//    private String organicCarbon;
//    private String organicMatter;
//    private String organicNitrogen;
//    private String organismCount;
//    private String oxygenationStatus;
//    private String particulateOrganicCarbon;
//    private String particulateOrganicNitrogen;
//    private String perturbation;
//    private String petroleumHydrocarbon;
//    private String ph;
//    private String phaeopigments;
//    private String phosphate;
//    private String phospholipidFattyAcid;
//    private String photonFlux;
//    private String potassium;
//    private String pressure;
//    private String primaryProduction;
//    private String redoxPotential;
//    private String salinity;
//    private String sampleStorageDuration;
//    private String sampleStorageLocation;
//    private String sampleStorageTemperature;
//    private String silicate;
//    private String sodium;
//    private String solubleReactivePhosphorus;
//    private String sulfate;
//    private String sulfide;
//    private String suspendedParticulateMatter;
//    private String temperature;
//    private String tidalStage;
//    private String totalDepthWaterColumn;
//    private String totalDissolvedNitrogen;
//    private String totalInorganicNitrogen;
//    private String totalNitrogen;
//    private String totalParticulateCarbon;
//    private String totalPhosphorus;
//    private String waterCurrent;
//    private String referenceBiomaterial;
//    private String relationshipOxygen;
//    private String sampleCollectionDevice;
//    private String sampleMaterialProcessing;
//    private String sampleSize;
//    private String sampleVolumeWeightDnaExtraction;
//    private String sourceMaterialIdentifiers;
//    private String description;
    public String getCenter() {
        return center;
    }

    public void setCenter(String center) {
        this.center = center;
    }

    public String getAccession() {
        return accession;
    }

    public void setAccession(String accession) {
        this.accession = accession;
    }

    public String getSampleAcc() {
        return sampleAcc;
    }

    public void setSampleAcc(String sampleAcc) {
        this.sampleAcc = sampleAcc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getTaxonId() {
        return taxonId;
    }

    public void setTaxonId(Integer taxonId) {
        this.taxonId = taxonId;
    }

    public String getOrganism() {
        return organism;
    }

    public void setOrganism(String organism) {
        this.organism = organism;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(String releaseTime) {
        this.releaseTime = releaseTime;
    }

    public String getSampleType() {
        return sampleType;
    }

    public void setSampleType(String sampleType) {
        this.sampleType = sampleType;
    }

//    public String getUsed() {
//        return used;
//    }
//
//    public void setUsed(String used) {
//        this.used = used;
//        this.setUseBy(used);
//    }

    public List<Use> getUseBy() {
        return useBy;
    }

//    public void setUseBy(String use) {
//        String[] uses = use.split(",");
//        List<Use> useList = new ArrayList<>();
//        for(String f:uses){
//            Use u = new Use();
//            u.setUse(f);
//            useList.add(u);
//        }
//        this.useBy = useList;
//    }

    public void setUseBy(List<Use> useBy) {
        this.useBy = useBy;
    }

    public JSONObject getAttributes() {
        return attributes;
    }

    public void setAttributes(JSONObject attributes) {
        this.attributes = attributes;
    }

    public String getGeographicLocation() {
        return geographicLocation;
    }

    public void setGeographicLocation(String geographicLocation) {
        this.geographicLocation = geographicLocation;
    }

    public String getIsolationSource() {
        return isolationSource;
    }

    public void setIsolationSource(String isolationSource) {
        this.isolationSource = isolationSource;
    }

    public String getLatitudeLongitude() {
        return latitudeLongitude;
    }

    public void setLatitudeLongitude(String latitudeLongitude) {
        this.latitudeLongitude = latitudeLongitude;
    }

    public String getCollectionDate() {
        return collectionDate;
    }

    public void setCollectionDate(String collectionDate) {
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
}
