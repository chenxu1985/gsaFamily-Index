package cn.ac.big.bigd.webservice.model.index;

import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenx on 2024-04-03.
 */
public class SampleType10 {
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
//    private String used;
    private List<Use> useBy;
    private JSONObject attributes;
    private String collectionDate;
    private String environmentBiome;
    private String environmentFeature;
    private String environmentMaterial;
    private String geographicLocation;
    private String latitudeLongitude;
    private String depth;
    private String elevation;
    private String isolationSource;

//    private String agrochemicalAdditions;
//    private String aluminiumSaturation;
//    private String aluminiumSaturationMethod;
//    private String altitude;
//    private String annualSeasonalPrecipitation;
//    private String annualSeasonalTemperature;
//    private String cropRotation;
//    private String currentLandUse;
//    private String currentVegetation;
//    private String currentVegetationMethod;
//    private String drainageClassification;
//    private String extremeEvent;
//    private String extremeSalinity;
//    private String faoClassification;
//    private String fire;
//    private String flooding;
//    private String heavyMetals;
//    private String heavyMetalsMethod;
//    private String horizon;
//    private String horizonMethod;
//    private String linksAdditionalAnalysis;
//    private String linkClassificationInformation;
//    private String linkClimateInformation;
//    private String localClassification;
//    private String localClassificationMethod;
//    private String microbialBiomass;
//    private String microbialBiomassMethod;
//    private String miscellaneousParameter;
//    private String ph;
//    private String phMethod;
//    private String poolingDnaExtracts;
//    private String previousLandUse;
//    private String previousLandUseMethod;
//    private String profilePosition;
//    private String salinityMethod;
//    private String sieving;
//    private String slopeAspect;
//    private String soilType;
//    private String slopeGradient;
//    private String soilTypeMethod;
//    private String storageConditions;
//    private String texture;
//    private String textureMethod;
//    private String tillage;
//    private String totalNMethod;
//    private String totalNitrogen;
//    private String totalOrganicCarbonMethod;
//    private String totalOrganicCarbon;
//    private String waterContentSoil;
//    private String waterContentSoilMethod;
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

    public String getElevation() {
        return elevation;
    }

    public void setElevation(String elevation) {
        this.elevation = elevation;
    }
}
