package cn.ac.big.bigd.webservice.controller.gsa;

import cn.ac.big.bigd.webservice.mapper.gsa.GsaIndexMapper;
import cn.ac.big.bigd.webservice.mapper.gsa.SampleIndexMapper;
import cn.ac.big.bigd.webservice.model.index.*;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * author chenxu
 */
@RestController
public class sampleController {

    @Autowired
    private SampleIndexMapper sampleIndexMapper;
    private Map<String,String> sampleType;
    private Map<Integer,String> sexMap;
    private Map<String,String> attrMap;
    private Map<String,String> oxygenMap;
    private Map<String,String> oxygenStatusMap;
    private Map<String,String> dietMap;
    private Map<String,String> drainageMap;
    private Map<String,String> horizoneMap;
    private Map<String,String> profileMap;
    private Map<String,String> tillageMap;
    private Map<String,String> tidalStageMap;
    private Map<String,String> currentLandUseMap;

    @RequestMapping("/updateTaxon")
    public String updateTaxon() throws IOException {
        String res = "success";
        List<SampleTaxon> sampleTaxons = this.sampleIndexMapper.getTaxons();
        String path = "/data/gsagroup/index/taxon.txt";
//        String path = "/Users/laphael/Desktop/taxon.txt";
        File toFile = new File(path);
        toFile.createNewFile();
        FileWriter toWriter = new FileWriter(toFile);
        BufferedWriter toOut = new BufferedWriter(toWriter);
        for(SampleTaxon s:sampleTaxons){
            toOut.write(s.getTaxId()+"\t"+s.getOrganism()+"\n");
        }
        toOut.close();
        return res;
    }
    @RequestMapping("/getSampleIndex")
    public void getSampleIndex() throws IOException {
        String index = "biosample";
        String title = "BioSample";
        String url = "https://ngdc.cncb.ac.cn/biosample";
        String desc = "Biological Sample Library";
        String basicInfo = "The BioSample database stores and supplies descriptions and metadata about biological samples used in research and development by academia and industry. BioSample are reciprocally linked with the BioProject in which they participate, as well as with derived experimental data in BIG Data Center databases such as Genome Sequence Archive, Genome Warehouse, Gene Expression Nebulas, etc.";
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String today = format.format(new Date());
        Database database = new Database();
        database.setIndex(index);
        database.setTitle(title);
        database.setUrl(url);
        database.setDescription(desc);
        database.setBasicInfo(basicInfo);
        database.setUpdateTime(today);
        File file = new File("/data/gsagroup/index/biosampleA.bs");
//        File file = new File("/Users/laphael/Desktop/biosampleA.bs");
        String path = "/data/gsagroup/index/taxon.txt";
//        String path = "/Users/laphael/Desktop/taxon.txt";
        if(file.exists()){
            file.delete();
        }
        FileOutputStream fos;
        System.out.println("BioSample 索引开始生成 "+new Date());
        this.initMaps();
        System.out.println("读取Taxon开始 "+new Date());
        File f1 = new File(path);
        InputStreamReader read = null;//考虑到编码格式
        HashMap<Integer,String> taxMap = new HashMap<>();
        int allCount = 0;
        try {
            read = new InputStreamReader(new FileInputStream(f1),"GBK");
            BufferedReader bu = new BufferedReader(read);
            String line = null;
            while((line = bu.readLine()) != null){
                //解析文件具体路径
                String str = line;
                int taxId = Integer.parseInt(str.split("\t")[0]);
                String name = str.split("\t")[1];
                taxMap.put(taxId,name);
            }
            read.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("读取Taxon完毕 "+new Date());
        try {
            fos = new FileOutputStream(file);
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(fos));
            writer.write("DB");
            writer.write("\t");
            writer.write(JSON.toJSONString(database).replaceAll("\n", ""));
            writer.write("\n");
            System.out.println("Sample Type1 开始");
            List<SampleType1> sampleList1 = this.sampleIndexMapper.getSampleType1();
            for(SampleType1 sample1:sampleList1){
                String or = taxMap.get(sample1.getTaxonId());
                sample1.setOrganism(or);
                JSONObject attributes = new JSONObject();
                if(sample1.getIsolate()!=null&&!sample1.getIsolate().equals("")){
                    attributes.put("isolate",sample1.getIsolate());
                }
                if(sample1.getHostId()!=0){
                    attributes.put("host", taxMap.get(sample1.getHostId()));
                }
                if(sample1.getHostDisease()!=null&&!sample1.getHostDisease().equals("")){
                    attributes.put("host_disease",sample1.getHostDisease());
                }
                if(sample1.getIsolationSource()!=null&&!sample1.getIsolationSource().equals("")){
                    attributes.put("isolation_source",sample1.getIsolationSource());
                }
                if(sample1.getLatitudeLongitude()!=null&&!sample1.getLatitudeLongitude().equals("")){
                    attributes.put("latitude_longitude",sample1.getLatitudeLongitude());
                }
                if(sample1.getGeographicLocation()!=null&&!sample1.getGeographicLocation().equals("")){
                    attributes.put("geographic_location",sample1.getGeographicLocation());
                }
                if(sample1.getStrain()!=null&&!sample1.getStrain().equals("")){
                    attributes.put("strain",sample1.getStrain());
                }
                if(sample1.getCollectionDate()!=null&&!sample1.getCollectionDate().equals("")){
                    attributes.put("collection_date",sample1.getCollectionDate());
                }
                if(sample1.getHostTissueSampled()!=null&&!sample1.getHostTissueSampled().equals("")){
                    attributes.put("host_tissue_sampled",sample1.getHostTissueSampled());
                }
                if(sample1.getCollectedBy()!=null&&!sample1.getCollectedBy().equals("")){
                    attributes.put("collected_by",sample1.getCollectedBy());
                }
                if(sample1.getHostSex()!=null){
                    attributes.put("host_sex",this.sexMap.get(sample1.getHostSex()));
                }

                sample1.setAttributes(attributes);
                writer.write("SAMPLE");
                writer.write("\t");
                writer.write(JSON.toJSONString(sample1).replaceAll("\n", ""));
                writer.write("\n");
            }
            System.out.println("Sample Type1 完毕");
            System.out.println("BioSample Type1 生成索引数量："+sampleList1.size());
            allCount = allCount + sampleList1.size();
            System.out.println("Sample Type2 开始");
            List<SampleType2> sampleList2 = this.sampleIndexMapper.getSampleType2();
            for(SampleType2 sample2:sampleList2){
                String or = taxMap.get(sample2.getTaxonId());
                sample2.setOrganism(or);
                JSONObject attributes = new JSONObject();
                if(sample2.getIsolate()!=null&&!sample2.getIsolate().equals("")){
                    attributes.put("isolate",sample2.getIsolate());
                }
                if(sample2.getStrain()!=null&&!sample2.getStrain().equals("")){
                    attributes.put("strain",sample2.getStrain());
                }
                if(sample2.getCollectedBy()!=null&&!sample2.getCollectedBy().equals("")){
                    attributes.put("collected_by",sample2.getCollectedBy());
                }
                if(sample2.getCollectionDate()!=null&&!sample2.getCollectionDate().equals("")){
                    attributes.put("collection_date",sample2.getCollectionDate());
                }
                if(sample2.getGeographicLocation()!=null&&!sample2.getGeographicLocation().equals("")){
                    attributes.put("geographic_location",sample2.getGeographicLocation());
                }
                if(sample2.getIsolationSource()!=null&&!sample2.getIsolationSource().equals("")){
                    attributes.put("isolation_source",sample2.getIsolationSource());
                }
                if(sample2.getLatitudeLongitude()!=null&&!sample2.getLatitudeLongitude().equals("")){
                    attributes.put("latitude_longitude",sample2.getLatitudeLongitude());
                }
                sample2.setAttributes(attributes);
                writer.write("SAMPLE");
                writer.write("\t");
                writer.write(JSON.toJSONString(sample2).replaceAll("\n", ""));
                writer.write("\n");
            }
            System.out.println("Sample Type2 完毕");
            System.out.println("BioSample Type2 生成索引数量："+sampleList2.size());
            allCount = allCount + sampleList2.size();
            System.out.println("Sample Type3 开始");
            List<SampleType3> sampleList3 = this.sampleIndexMapper.getSampleType3();
            for(SampleType3 sample3:sampleList3){
                String or = taxMap.get(sample3.getTaxonId());
                sample3.setOrganism(or);
                JSONObject attributes = new JSONObject();
                if(sample3.getHostId()!=0){
                    attributes.put("host", taxMap.get(sample3.getHostId()));
                }
                if(sample3.getStrain()!=null&&!sample3.getStrain().equals("")){
                    attributes.put("strain",sample3.getStrain());
                }
                if(sample3.getCollectedBy()!=null&&!sample3.getCollectedBy().equals("")){
                    attributes.put("collected_by",sample3.getCollectedBy());
                }
                if(sample3.getCollectionDate()!=null&&!sample3.getCollectionDate().equals("")){
                    attributes.put("collection_date",sample3.getCollectionDate());
                }
                if(sample3.getGeographicLocation()!=null&&!sample3.getGeographicLocation().equals("")){
                    attributes.put("geographic_location",sample3.getGeographicLocation());
                }
                if(sample3.getIsolationSource()!=null&&!sample3.getIsolationSource().equals("")){
                    attributes.put("isolation_source",sample3.getIsolationSource());
                }
                if(sample3.getLatitudeLongitude()!=null&&!sample3.getLatitudeLongitude().equals("")){
                    attributes.put("latitude_longitude",sample3.getLatitudeLongitude());
                }
                if(sample3.getHostTissueSampled()!=null&&!sample3.getHostTissueSampled().equals("")){
                    attributes.put("host_tissue_sampled",sample3.getHostTissueSampled());
                }
                sample3.setAttributes(attributes);
                writer.write("SAMPLE");
                writer.write("\t");
                writer.write(JSON.toJSONString(sample3).replaceAll("\n", ""));
                writer.write("\n");
            }
            System.out.println("Sample Type3 完毕");
            System.out.println("BioSample Type3 生成索引数量："+sampleList3.size());
            allCount = allCount + sampleList3.size();
            System.out.println("Sample Type4 开始");
            List<SampleType4> sampleList4 = this.sampleIndexMapper.getSampleType4();
            for(SampleType4 sample4:sampleList4){
                String or = taxMap.get(sample4.getTaxonId());
                sample4.setOrganism(or);
                JSONObject attributes = new JSONObject();
                if(sample4.getStrain()!=null&&!sample4.getStrain().equals("")){
                    attributes.put("strain",sample4.getStrain());
                }
                if(sample4.getBreed()!=null&&!sample4.getBreed().equals("")){
                    attributes.put("breed",sample4.getBreed());
                }
                if(sample4.getAge()!=null&&!sample4.getAge().equals("")){
                    attributes.put("age",sample4.getAge());
                }
                if(sample4.getAgeUnit()!=null&&!sample4.getAgeUnit().equals("")){
                    attributes.put("age_unit",sample4.getAgeUnit());
                }
                if(sample4.getBiomaterialProvider()!=null&&!sample4.getBiomaterialProvider().equals("")){
                    attributes.put("biomaterial_provider",sample4.getBiomaterialProvider());
                }
                if(sample4.getTissue()!=null&&!sample4.getTissue().equals("")){
                    attributes.put("tissue",sample4.getTissue());
                }
                if(sample4.getCellLine()!=null&&!sample4.getCellLine().equals("")){
                    attributes.put("cell_line",sample4.getCellLine());
                }
                if(sample4.getCellType()!=null&&!sample4.getCellType().equals("")){
                    attributes.put("cell_type",sample4.getCellType());
                }
                if(sample4.getDisease()!=null&&!sample4.getDisease().equals("")){
                    attributes.put("disease",sample4.getDisease());
                }
                if(sample4.getSex()!=null){
                    attributes.put("sex",this.sexMap.get(sample4.getSex()));
                }
                if(sample4.getCollectedBy()!=null&&!sample4.getCollectedBy().equals("")){
                    attributes.put("collected_by",sample4.getCollectedBy());
                }
                if(sample4.getGeographicLocation()!=null&&!sample4.getGeographicLocation().equals("")){
                    attributes.put("geographic_location",sample4.getGeographicLocation());
                }
                if(sample4.getIsolationSource()!=null&&!sample4.getIsolationSource().equals("")){
                    attributes.put("isolation_source",sample4.getIsolationSource());
                }
                if(sample4.getLatitudeLongitude()!=null&&!sample4.getLatitudeLongitude().equals("")){
                    attributes.put("latitude_longitude",sample4.getLatitudeLongitude());
                }
                sample4.setAttributes(attributes);
                writer.write("SAMPLE");
                writer.write("\t");
                writer.write(JSON.toJSONString(sample4).replaceAll("\n", ""));
                writer.write("\n");
            }
            System.out.println("Sample Type4 完毕");
            System.out.println("BioSample Type4 生成索引数量："+sampleList4.size());
            allCount = allCount + sampleList4.size();
            System.out.println("Sample Type5 开始");
            List<SampleType5> sampleList5 = this.sampleIndexMapper.getSampleType5();
            for(SampleType5 sample5:sampleList5){
                String or = taxMap.get(sample5.getTaxonId());
                sample5.setOrganism(or);
                JSONObject attributes = new JSONObject();
                if(sample5.getIsolate()!=null&&!sample5.getIsolate().equals("")){
                    attributes.put("isolate",sample5.getIsolate());
                }
                if(sample5.getAge()!=null&&!sample5.getAge().equals("")){
                    attributes.put("age",sample5.getAge());
                }
                if(sample5.getAgeUnit()!=null&&!sample5.getAgeUnit().equals("")){
                    attributes.put("age_unit",sample5.getAgeUnit());
                }
                if(sample5.getBiomaterialProvider()!=null&&!sample5.getBiomaterialProvider().equals("")){
                    attributes.put("biomaterial_provider",sample5.getBiomaterialProvider());
                }
                if(sample5.getSex()!=null){
                    attributes.put("sex",this.sexMap.get(sample5.getSex()));
                }
                if(sample5.getTissue()!=null&&!sample5.getTissue().equals("")){
                    attributes.put("tissue",sample5.getTissue());
                }
                if(sample5.getCellLine()!=null&&!sample5.getCellLine().equals("")){
                    attributes.put("cell_line",sample5.getCellLine());
                }
                if(sample5.getCellType()!=null&&!sample5.getCellType().equals("")){
                    attributes.put("cell_type",sample5.getCellType());
                }
                if(sample5.getDisease()!=null&&!sample5.getDisease().equals("")){
                    attributes.put("disease",sample5.getDisease());
                }
                sample5.setAttributes(attributes);
                writer.write("SAMPLE");
                writer.write("\t");
                writer.write(JSON.toJSONString(sample5).replaceAll("\n", ""));
                writer.write("\n");
            }
            System.out.println("Sample Type5 完毕");
            System.out.println("BioSample Type5 生成索引数量："+sampleList5.size());
            allCount = allCount + sampleList5.size();
            System.out.println("Sample Type6 开始");
            List<SampleType6> sampleList6 = this.sampleIndexMapper.getSampleType6();
            for(SampleType6 sample6:sampleList6){
                String or = taxMap.get(sample6.getTaxonId());
                sample6.setOrganism(or);
                JSONObject attributes = new JSONObject();
                if(sample6.getCultivar()!=null&&!sample6.getCultivar().equals("")){
                    attributes.put("cultivar",sample6.getCultivar());
                }
                if(sample6.getAge()!=null&&!sample6.getAge().equals("")){
                    attributes.put("age",sample6.getAge());
                }
                if(sample6.getAgeUnit()!=null&&!sample6.getAgeUnit().equals("")){
                    attributes.put("age_unit",sample6.getAgeUnit());
                }
                if(sample6.getBiomaterialProvider()!=null&&!sample6.getBiomaterialProvider().equals("")){
                    attributes.put("biomaterial_provider",sample6.getBiomaterialProvider());
                }
                if(sample6.getSex()!=null){
                    attributes.put("sex",this.sexMap.get(sample6.getSex()));
                }
                if(sample6.getTissue()!=null&&!sample6.getTissue().equals("")){
                    attributes.put("tissue",sample6.getTissue());
                }
                if(sample6.getCellLine()!=null&&!sample6.getCellLine().equals("")){
                    attributes.put("cell_line",sample6.getCellLine());
                }
                if(sample6.getCellType()!=null&&!sample6.getCellType().equals("")){
                    attributes.put("cell_type",sample6.getCellType());
                }
                if(sample6.getDisease()!=null&&!sample6.getDisease().equals("")){
                    attributes.put("disease",sample6.getDisease());
                }
                if(sample6.getSex()!=null){
                    attributes.put("sex",this.sexMap.get(sample6.getSex()));
                }
                if(sample6.getCollectedBy()!=null&&!sample6.getCollectedBy().equals("")){
                    attributes.put("collected_by",sample6.getCollectedBy());
                }
                if(sample6.getGeographicLocation()!=null&&!sample6.getGeographicLocation().equals("")){
                    attributes.put("geographic_location",sample6.getGeographicLocation());
                }
                if(sample6.getIsolationSource()!=null&&!sample6.getIsolationSource().equals("")){
                    attributes.put("isolation_source",sample6.getIsolationSource());
                }
                if(sample6.getLatitudeLongitude()!=null&&!sample6.getLatitudeLongitude().equals("")){
                    attributes.put("latitude_longitude",sample6.getLatitudeLongitude());
                }
                if(sample6.getCollectionDate()!=null&&!sample6.getCollectionDate().equals("")){
                    attributes.put("collection_date",sample6.getCollectionDate());
                }
                sample6.setAttributes(attributes);
                writer.write("SAMPLE");
                writer.write("\t");
                writer.write(JSON.toJSONString(sample6).replaceAll("\n", ""));
                writer.write("\n");
            }
            System.out.println("Sample Type6 完毕");
            System.out.println("BioSample Type6 生成索引数量："+sampleList6.size());
            allCount = allCount + sampleList6.size();
            System.out.println("Sample Type7 开始");
            List<SampleType7> sampleList7 = this.sampleIndexMapper.getSampleType7();
            for(SampleType7 sample7:sampleList7){
                String or = taxMap.get(sample7.getTaxonId());
                sample7.setOrganism(or);
                JSONObject attributes = new JSONObject();
                if(sample7.getIsolate()!=null&&!sample7.getIsolate().equals("")){
                    attributes.put("isolate",sample7.getIsolate());
                }
                if(sample7.getStrain()!=null&&!sample7.getStrain().equals("")){
                    attributes.put("strain",sample7.getStrain());
                }
                if(sample7.getHostId()!=0){
                    attributes.put("host", taxMap.get(sample7.getHostId()));
                }
                if(sample7.getBiomaterialProvider()!=null&&!sample7.getBiomaterialProvider().equals("")){
                    attributes.put("biomaterial_provider",sample7.getBiomaterialProvider());
                }
                if(sample7.getHostTissueSampled()!=null&&!sample7.getHostTissueSampled().equals("")){
                    attributes.put("host_tissue_sampled",sample7.getHostTissueSampled());
                }
                if(sample7.getLabHost()!=null&&!sample7.getLabHost().equals("")){
                    attributes.put("lab_host",sample7.getLabHost());
                }
                if(sample7.getDisease()!=null&&!sample7.getDisease().equals("")){
                    attributes.put("disease",sample7.getDisease());
                }
                if(sample7.getCollectedBy()!=null&&!sample7.getCollectedBy().equals("")){
                    attributes.put("collected_by",sample7.getCollectedBy());
                }
                if(sample7.getGeographicLocation()!=null&&!sample7.getGeographicLocation().equals("")){
                    attributes.put("geographic_location",sample7.getGeographicLocation());
                }
                if(sample7.getIsolationSource()!=null&&!sample7.getIsolationSource().equals("")){
                    attributes.put("isolation_source",sample7.getIsolationSource());
                }
                if(sample7.getLatitudeLongitude()!=null&&!sample7.getLatitudeLongitude().equals("")){
                    attributes.put("latitude_longitude",sample7.getLatitudeLongitude());
                }
                if(sample7.getCollectionDate()!=null&&!sample7.getCollectionDate().equals("")){
                    attributes.put("collection_date",sample7.getCollectionDate());
                }
                sample7.setAttributes(attributes);
                writer.write("SAMPLE");
                writer.write("\t");
                writer.write(JSON.toJSONString(sample7).replaceAll("\n", ""));
                writer.write("\n");
            }
            System.out.println("Sample Type7 完毕");
            System.out.println("BioSample Type7 生成索引数量："+sampleList7.size());
            allCount = allCount + sampleList7.size();
            System.out.println("Sample Type8 开始");
            List<SampleType8> sampleList8 = this.sampleIndexMapper.getSampleType8();
            for(SampleType8 sample8:sampleList8){
                String or = taxMap.get(sample8.getTaxonId());
                sample8.setOrganism(or);
                JSONObject attributes = new JSONObject();
                if(sample8.getHostId()!=0){
                    attributes.put("host", taxMap.get(sample8.getHostId()));
                }
                if(sample8.getGeographicLocation()!=null&&!sample8.getGeographicLocation().equals("")){
                    attributes.put("geographic_location",sample8.getGeographicLocation());
                }
                if(sample8.getIsolationSource()!=null&&!sample8.getIsolationSource().equals("")){
                    attributes.put("isolation_source",sample8.getIsolationSource());
                }
                if(sample8.getLatitudeLongitude()!=null&&!sample8.getLatitudeLongitude().equals("")){
                    attributes.put("latitude_longitude",sample8.getLatitudeLongitude());
                }
                if(sample8.getCollectionDate()!=null&&!sample8.getCollectionDate().equals("")){
                    attributes.put("collection_date",sample8.getCollectionDate());
                }
                sample8.setAttributes(attributes);
                writer.write("SAMPLE");
                writer.write("\t");
                writer.write(JSON.toJSONString(sample8).replaceAll("\n", ""));
                writer.write("\n");
            }
            System.out.println("Sample Type8 完毕");
            System.out.println("BioSample Type8 生成索引数量："+sampleList8.size());
            allCount = allCount + sampleList8.size();
            System.out.println("Sample Type9 开始");
            List<SampleType9> sampleList9 = this.sampleIndexMapper.getSampleType9();
            for(SampleType9 sample9:sampleList9){
                String or = taxMap.get(sample9.getTaxonId());
                sample9.setOrganism(or);
                JSONObject attributes = new JSONObject();
                if(sample9.getHostId()!=0){
                    attributes.put("host", taxMap.get(sample9.getHostId()));
                }
                if(sample9.getGeographicLocation()!=null&&!sample9.getGeographicLocation().equals("")){
                    attributes.put("geographic_location",sample9.getGeographicLocation());
                }
                if(sample9.getIsolationSource()!=null&&!sample9.getIsolationSource().equals("")){
                    attributes.put("isolation_source",sample9.getIsolationSource());
                }
                if(sample9.getLatitudeLongitude()!=null&&!sample9.getLatitudeLongitude().equals("")){
                    attributes.put("latitude_longitude",sample9.getLatitudeLongitude());
                }
                if(sample9.getCollectionDate()!=null&&!sample9.getCollectionDate().equals("")){
                    attributes.put("collection_date",sample9.getCollectionDate());
                }
                if(sample9.getHostSex()!=null){
                    attributes.put("host_sex",this.sexMap.get(sample9.getHostSex()));
                }
                if(sample9.getHostTissueSampled()!=null&&!sample9.getHostTissueSampled().equals("")){
                    attributes.put("host_tissue_sampled",sample9.getHostTissueSampled());
                }
                if(sample9.getHostDisease()!=null&&!sample9.getHostDisease().equals("")){
                    attributes.put("host_disease",sample9.getHostDisease());
                }
                sample9.setAttributes(attributes);
                writer.write("SAMPLE");
                writer.write("\t");
                writer.write(JSON.toJSONString(sample9).replaceAll("\n", ""));
                writer.write("\n");
            }
            System.out.println("Sample Type9 完毕");
            System.out.println("BioSample Type9 生成索引数量："+sampleList9.size());
            allCount = allCount + sampleList9.size();
            System.out.println("Sample Type10 开始");
            List<SampleType10> sampleList10 = this.sampleIndexMapper.getSampleType10();
            for(SampleType10 sample10:sampleList10){
                String or = taxMap.get(sample10.getTaxonId());
                sample10.setOrganism(or);
                JSONObject attributes = new JSONObject();
                if(sample10.getGeographicLocation()!=null&&!sample10.getGeographicLocation().equals("")){
                    attributes.put("geographic_location",sample10.getGeographicLocation());
                }
                if(sample10.getIsolationSource()!=null&&!sample10.getIsolationSource().equals("")){
                    attributes.put("isolation_source",sample10.getIsolationSource());
                }
                if(sample10.getLatitudeLongitude()!=null&&!sample10.getLatitudeLongitude().equals("")){
                    attributes.put("latitude_longitude",sample10.getLatitudeLongitude());
                }
                if(sample10.getCollectionDate()!=null&&!sample10.getCollectionDate().equals("")){
                    attributes.put("collection_date",sample10.getCollectionDate());
                }

                if(sample10.getEnvironmentBiome()!=null&&!sample10.getEnvironmentBiome().equals("")){
                    attributes.put("env_broad_scale",sample10.getEnvironmentBiome());
                }
                if(sample10.getEnvironmentFeature()!=null&&!sample10.getEnvironmentFeature().equals("")){
                    attributes.put("env_local_scale",sample10.getEnvironmentFeature());
                }
                if(sample10.getEnvironmentMaterial()!=null&&!sample10.getEnvironmentMaterial().equals("")){
                    attributes.put("environment_medium",sample10.getEnvironmentMaterial());
                }
                if(sample10.getDepth()!=null&&!sample10.getDepth().equals("")){
                    attributes.put("depth",sample10.getDepth());
                }
                if(sample10.getElevation()!=null&&!sample10.getElevation().equals("")){
                    attributes.put("elevation",sample10.getElevation());
                }
                sample10.setAttributes(attributes);
                writer.write("SAMPLE");
                writer.write("\t");
                writer.write(JSON.toJSONString(sample10).replaceAll("\n", ""));
                writer.write("\n");
            }
            System.out.println("Sample Type10 完毕");
            System.out.println("BioSample Type10 生成索引数量："+sampleList10.size());
            allCount = allCount + sampleList10.size();
            System.out.println("Sample Type11 开始");
            List<SampleType11> sampleList11 = this.sampleIndexMapper.getSampleType11();
            for(SampleType11 sample11:sampleList11){
                String or = taxMap.get(sample11.getTaxonId());
                sample11.setOrganism(or);
                JSONObject attributes = new JSONObject();
                if(sample11.getGeographicLocation()!=null&&!sample11.getGeographicLocation().equals("")){
                    attributes.put("geographic_location",sample11.getGeographicLocation());
                }
                if(sample11.getIsolationSource()!=null&&!sample11.getIsolationSource().equals("")){
                    attributes.put("isolation_source",sample11.getIsolationSource());
                }
                if(sample11.getLatitudeLongitude()!=null&&!sample11.getLatitudeLongitude().equals("")){
                    attributes.put("latitude_longitude",sample11.getLatitudeLongitude());
                }
                if(sample11.getCollectionDate()!=null&&!sample11.getCollectionDate().equals("")){
                    attributes.put("collection_date",sample11.getCollectionDate());
                }
                if(sample11.getEnvironmentBiome()!=null&&!sample11.getEnvironmentBiome().equals("")){
                    attributes.put("env_broad_scale",sample11.getEnvironmentBiome());
                }
                if(sample11.getEnvironmentFeature()!=null&&!sample11.getEnvironmentFeature().equals("")){
                    attributes.put("env_local_scale",sample11.getEnvironmentFeature());
                }
                if(sample11.getEnvironmentMaterial()!=null&&!sample11.getEnvironmentMaterial().equals("")){
                    attributes.put("environment_medium",sample11.getEnvironmentMaterial());
                }
                if(sample11.getDepth()!=null&&!sample11.getDepth().equals("")){
                    attributes.put("depth",sample11.getDepth());
                }
                if(sample11.getAlkalinity()!=null&&!sample11.getAlkalinity().equals("")){
                    attributes.put("alkalinity",sample11.getAlkalinity());
                }
                sample11.setAttributes(attributes);
                writer.write("SAMPLE");
                writer.write("\t");
                writer.write(JSON.toJSONString(sample11).replaceAll("\n", ""));
                writer.write("\n");
            }
            System.out.println("Sample Type11 完毕");
            System.out.println("BioSample Type11 生成索引数量："+sampleList11.size());
            allCount = allCount + sampleList11.size();
            writer.close();
            System.out.println("BioSample 索引生成完毕 同步数量："+allCount+" "+new Date());
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    private void initMaps(){
        if(this.sampleType == null){
            this.sampleType = new HashMap<String, String>();
            this.sampleType.put("Human sample", "5");
            this.sampleType.put("Microbe", "3");
            this.sampleType.put("Model organism or animal sample", "4");
            this.sampleType.put("Clinical or host-associated pathogen", "1");
            this.sampleType.put("Environmental, food or other pathogen", "2");
            this.sampleType.put("Plant sample", "6");
            this.sampleType.put("Virus sample", "7");
            this.sampleType.put("Metagenome or environmental sample", "8");
            this.sampleType.put("Environmental/Metagenome Genomic Sequences MIMS.me -- human-gut", "9");
            this.sampleType.put("Environmental/Metagenome Genomic Sequences MIMS.me -- soil", "10");
            this.sampleType.put("Environmental/Metagenome Genomic Sequences MIMS.me -- water", "11");
        }
        if(this.attrMap == null){
            this.attrMap = new HashMap<String, String>();
            this.attrMap.put("1", "sample_attr_pathogen_clinical_host_associated");
            this.attrMap.put("2", "sample_attr_pathogen_environmental_food_other");
            this.attrMap.put("3", "sample_attr_microbe");
            this.attrMap.put("4", "sample_attr_model_animal");
            this.attrMap.put("5", "sample_attr_human");
            this.attrMap.put("6", "sample_attr_plant");
            this.attrMap.put("7", "sample_attr_virus");
            this.attrMap.put("8", "sample_attr_metagenome_environmental");
            this.attrMap.put("9", "sample_attr_mimsme_human_gut");
            this.attrMap.put("10", "sample_attr_mimsme_soil");
            this.attrMap.put("11", "sample_attr_mimsme_water");

        }
        if(this.sexMap == null){
            this.sexMap = new HashMap<Integer, String>();
            this.sexMap.put(1, "male");
            this.sexMap.put(2, "female");
            this.sexMap.put(3, "neuter");
            this.sexMap.put(4, "hermaphrodite");
            this.sexMap.put(5, "not determined");
            this.sexMap.put(6, "missing");
            this.sexMap.put(7, "not applicable");
            this.sexMap.put(8, "not collected");
        }
        if(this.oxygenMap == null){
            this.oxygenMap = new HashMap<String,String>();
            this.oxygenMap.put("aerobe", "1");
            this.oxygenMap.put("anaerobe", "2");
            this.oxygenMap.put("facultative", "3");
            this.oxygenMap.put("microaerophilic", "4");
            this.oxygenMap.put("microanaerobe", "5");
            this.oxygenMap.put("obligate aerobe", "6");
            this.oxygenMap.put("obligate anaerobe", "7");
        }
        if(this.oxygenStatusMap == null){
            this.oxygenStatusMap = new HashMap<String,String>();
            this.oxygenStatusMap.put("aerobe", "1");
            this.oxygenStatusMap.put("anaerobe", "2");
        }
        if(this.dietMap == null){
            this.dietMap = new HashMap<String,String>();
            this.dietMap.put("low carb", "1");
            this.dietMap.put("reduced calorie", "2");
            this.dietMap.put("vegetarian", "3");
            this.dietMap.put("other", "4");
        }
        if(this.drainageMap == null){
            this.drainageMap = new HashMap<String,String>();
            this.drainageMap.put("very poorly", "1");
            this.drainageMap.put("poorly", "2");
            this.drainageMap.put("somewhat poorly", "3");
            this.drainageMap.put("moderately well", "4");
            this.drainageMap.put("well", "5");
            this.drainageMap.put("excessively drained", "6");
        }
        if(this.horizoneMap == null){
            this.horizoneMap = new HashMap<String,String>();
            this.horizoneMap.put("O horizon", "1");
            this.horizoneMap.put("A horizon", "2");
            this.horizoneMap.put("E horizon", "3");
            this.horizoneMap.put("B horizon", "4");
            this.horizoneMap.put("C horizon", "5");
            this.horizoneMap.put("R layer", "6");
            this.horizoneMap.put("Permafrost", "7");
        }
        if(this.profileMap == null){
            this.profileMap = new HashMap<String,String>();
            this.profileMap.put("summit", "1");
            this.profileMap.put("shoulder", "2");
            this.profileMap.put("backslope", "3");
            this.profileMap.put("footslope", "4");
            this.profileMap.put("toeslope", "5");
        }
        if(this.tillageMap == null){
            this.tillageMap = new HashMap<String,String>();
            this.tillageMap.put("drill", "1");
            this.tillageMap.put("cutting disc", "2");
            this.tillageMap.put("ridge till", "3");
            this.tillageMap.put("strip tillage", "4");
            this.tillageMap.put("zonal tillage", "5");
            this.tillageMap.put("chisel", "6");
            this.tillageMap.put("tined", "7");
            this.tillageMap.put("mouldboard", "8");
            this.tillageMap.put("disc plough", "9");
        }
        if(this.tidalStageMap == null){
            this.tidalStageMap = new HashMap<String,String>();
            this.tidalStageMap.put("low", "1");
            this.tidalStageMap.put("high", "2");
        }
        if(this.currentLandUseMap ==null){
            this.currentLandUseMap = new HashMap<String,String>();
            this.currentLandUseMap.put("cities", "1");
            this.currentLandUseMap.put("farmstead", "2");
            this.currentLandUseMap.put("industrial areas", "3");
            this.currentLandUseMap.put("roads/railroads", "4");
            this.currentLandUseMap.put("rock", "5");
            this.currentLandUseMap.put("sand", "6");
            this.currentLandUseMap.put("gravel", "7");
            this.currentLandUseMap.put("mudflats", "8");
            this.currentLandUseMap.put("salt flats", "9");
            this.currentLandUseMap.put("badlands", "10");
            this.currentLandUseMap.put("permanent snow or ice", "11");
            this.currentLandUseMap.put("saline seeps", "12");
            this.currentLandUseMap.put("mines/quarries", "13");
            this.currentLandUseMap.put("oil waste areas", "14");
            this.currentLandUseMap.put("small grains", "15");
            this.currentLandUseMap.put("row crops", "16");
            this.currentLandUseMap.put("vegetable crops", "17");
            this.currentLandUseMap.put("horticultural plants (e.g. tulips)", "18");
            this.currentLandUseMap.put("marshlands (grass,sedges,rushes)", "19");
            this.currentLandUseMap.put("tundra (mosses,lichens)", "20");
            this.currentLandUseMap.put("rangeland", "21");
            this.currentLandUseMap.put("pastureland (grasslands used for livestock grazing)", "22");
            this.currentLandUseMap.put("hayland", "23");
            this.currentLandUseMap.put("meadows (grasses,alfalfa,fescue,bromegrass,timothy)", "24");
            this.currentLandUseMap.put("shrub land (e.g. mesquite,sage-brush,creosote bush,shrub oak,eucalyptus)", "25");
            this.currentLandUseMap.put("successional shrub land (tree saplings,hazels,sumacs,chokecherry,shrub dogwoods,blackberries)", "26");
            this.currentLandUseMap.put("shrub crops (blueberries,nursery ornamentals,filberts)", "27");
            this.currentLandUseMap.put("vine crops (grapes)", "28");
            this.currentLandUseMap.put("conifers (e.g. pine,spruce,fir,cypress)", "29");
            this.currentLandUseMap.put("hardwoods (e.g. oak,hickory,elm,aspen)", "30");
            this.currentLandUseMap.put("intermixed hardwood and conifers", "31");
            this.currentLandUseMap.put("tropical (e.g. mangrove,palms)", "32");
            this.currentLandUseMap.put("rainforest (evergreen forest receiving >406 cm annual rainfall)", "33");
            this.currentLandUseMap.put("swamp (permanent or semi-permanent water body dominated by woody plants)", "34");
            this.currentLandUseMap.put("crop trees (nuts,fruit,christmas trees,nursery trees)", "35");
        }
    }
}
