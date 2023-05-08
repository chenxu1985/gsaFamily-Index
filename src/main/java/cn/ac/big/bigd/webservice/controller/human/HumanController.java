package cn.ac.big.bigd.webservice.controller.human;

import cn.ac.big.bigd.webservice.mapper.gsa.*;
import cn.ac.big.bigd.webservice.mapper.human.HumanSampleMapper;
import cn.ac.big.bigd.webservice.mapper.human.StudyMapper;
import cn.ac.big.bigd.webservice.model.gsa.*;
import cn.ac.big.bigd.webservice.model.human.*;
import cn.ac.big.bigd.webservice.model.human.Sample;
import cn.ac.big.bigd.webservice.model.human.User;
import cn.ac.big.bigd.webservice.send.SendWe;
import cn.ac.big.bigd.webservice.utility.HttpRequestUtil;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * author chenxu
 */
@RestController
public class HumanController {
    @Autowired
    private HumanSampleMapper humanSampleMapper;
    @Autowired
    private StudyMapper studyMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private SubmitterMapper submitterMapper;
    @Autowired
    private SampleSubmissionMapper sampleSubmissionMapper;
    @Autowired
    private SampleMapper sampleMapper;
    @Autowired
    private SampleAttrHumanMapper sampleAttrHumanMapper;
    @Autowired
    private SampleAttrPathogenClinicalHostMapper sampleAttrPathogenClinicalHostMapper;
    @Autowired
    private SampleAttrMimsmeHumanGutMapper sampleAttrMimsmeHumanGutMapper;
    @Autowired
    private SampleAttrMetagenomeEnvironmentalMapper sampleAttrMetagenomeEnvironmentalMapper;

    private String studyStr;
    /**
     * sample同步
     * @return
     */
    @Scheduled(cron = "0 0 5 * * ?") //每天5点钟执行
    @RequestMapping("/sampleSynchronization")
    public void sampleSynchronization() {
        //在GSA中插入sample_submission,submitter,sample,sample属性表
        System.out.println("同步开始时间："+new Date());
        String subMissionIdString = this.gsaSampleSynchronization();
        System.out.println(subMissionIdString);
        if(subMissionIdString!=null&&subMissionIdString!=""){
            this.humanSampleSynchronization(subMissionIdString);
        }
        System.out.println("同步结束时间："+new Date());
    }
    @RequestMapping("/sampleSynchronizationList/{studyList}")
    public void sampleSynchronizationList(@PathVariable("studyList") String studyList) {
        if(studyList!=null){
//            if(studyList.contains(",")){
//
//
//            } else {
//                //在GSA中插入sample_submission,submitter,sample,sample属性表
//                String subMissionIdString = this.gsaSampleSynchronizationList(studyList);
//                System.out.println(subMissionIdString);
//                if(subMissionIdString!=null&&subMissionIdString!=""){
//                    this.humanSampleSynchronization(subMissionIdString);
//                }
//            }
            //在GSA中插入sample_submission,submitter,sample,sample属性表
            String[] str = studyList.split(",");
            List sList = new ArrayList();
            for(String s:str){
                sList.add(Integer.parseInt(s));
            }
            String subMissionIdString = this.gsaSampleSynchronizationList(sList);
            System.out.println(subMissionIdString);
            if(subMissionIdString!=null&&subMissionIdString!=""){
                this.humanSampleSynchronization(subMissionIdString);
            }
        }


    }
    @RequestMapping("/humanSampleSynchronization")
    @Transactional(rollbackFor = { Exception.class })
    public void humanSampleSynchronization(String subMissionIdString) {
        String result = "";
        String url="http://192.168.164.20:9998/gsub/sys/ajax/batchSynchronizationSamples";
//        String url="http://localhost:8080/gsub/sys/ajax/batchSynchronizationSamples";
        try {
            result  = HttpRequestUtil.doHttpGetResponseJson(url, null);
            net.sf.json.JSONObject jsonObject = net.sf.json.JSONObject.fromObject(result);
            int resultInt = (int) jsonObject.get("result");
            if(resultInt==0){
                SendWe.sendSampleSynchronization("ChenXu","Human样本同步，gsa分配编号报错");
            } else if(resultInt==1) {
//                String path = "/Users/laphael/Downloads/sampleList.txt";
                String path = "/webdb/testApplications/gsagroup/webApp/bigtools/files/sampleList.txt";
                File file = new File(path);
                InputStreamReader read = null;//考虑到编码格式
                read = new InputStreamReader(new FileInputStream(file),"GBK");
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt = null;
                while((lineTxt = bufferedReader.readLine()) != null){
                    String str = lineTxt;
                    String humanSampleAccession = str.split("\t")[0];
                    int bioSampleId = Integer.parseInt(str.split("\t")[1]);
                    String bioSampleAccession = str.split("\t")[2];
                    Sample humanSample = new Sample();
                    humanSample.setAccession(humanSampleAccession);
                    humanSample.setBiosampleId(bioSampleId);
                    humanSample.setBiosampleAccession(bioSampleAccession);
                    humanSample.setIsSynchronization(1);
                    this.humanSampleMapper.updateBioSampleByAccession(humanSample);
                }
                String[] studySp = this.studyStr.split(",");
                for(String id:studySp){
                    int studyId = Integer.parseInt(id);
                    this.studyMapper.updateSynchronizationByStudyId(studyId);
                }
                SendWe.sendSampleSynchronization("ChenXu","Human样本同步成功");
            }

        } catch (Exception e) {
            SendWe.sendSampleSynchronization("ChenXu","Human样本同步失败");
            e.printStackTrace();
        }
    }

    @RequestMapping("/gsaSampleSynchronization")
    @Transactional(rollbackFor = { Exception.class })
    public String gsaSampleSynchronization() {
        String subMissionIdString= "suc";
        List<Sample> samples = new ArrayList();
        List<Study> studyList = this.studyMapper.getUnSynchronizationStudys();
        try {
            if(studyList!=null&&studyList.size()>0){
                //String outPath = "/Users/laphael/Downloads/subMissionId.txt";
                String outPath = "/webdb/testApplications/gsagroup/webApp/bigtools/files/subMissionId.txt";
                File toFile = new File(outPath);
                if(toFile.exists()){
                    toFile.delete();
                }
                toFile.createNewFile();
                FileWriter toWriter = new FileWriter(toFile);
                BufferedWriter toOut = new BufferedWriter(toWriter);
                for(Study study:studyList){
                    if(studyStr!=null){
                        studyStr = studyStr + "," + study.getStudyId();
                    } else {
                        studyStr = study.getStudyId() +"";
                    }
                    System.out.println(study.getStudyId());
                    //获取gsa中user
                    String casId = study.getUser().getCasUserId();
                    cn.ac.big.bigd.webservice.model.gsa.User gsaUser = this.userMapper.selectUserByCsaId(casId);
                    int studyId = study.getStudyId();
                    int packageId = study.getPackageId();
                    int gsaUserId = gsaUser.getUserId();
                    //创建submitter
                    int submitterId = study.getSubmitterId();
                    int prjId = study.getPrjId();
                    if(packageId!=4){
                        samples = this.humanSampleMapper.getUnSynchronizationSamples(studyId);
                        if(samples!=null){
                            //插入Submitter
                            cn.ac.big.bigd.webservice.model.gsa.Submitter submitter = this.saveSubmitter(submitterId,study);
                            //创建 sample_submisson表格
                            String subMissionId = this.saveSampleSubmission(gsaUserId,submitter,prjId,study);
                            //分类型插入
                            //插入human
                            if(packageId==1||packageId==2){
                                for(Sample sample:samples ){
                                    if(packageId==1){
                                        this.saveSampleP1(sample,gsaUserId,prjId,submitter,study,subMissionId);
                                    } else if(packageId==2){
                                        this.saveSampleP2(sample,gsaUserId,prjId,submitter,study,subMissionId);
                                    }
                                }
                                //插入病原体
                            } else if(packageId==3) {
                                for(Sample sample:samples ){
                                    this.saveSampleP3(sample,gsaUserId,prjId,submitter,study,subMissionId);
                                }
                            }
                            toOut.write(subMissionId+"\n");
                        }
                    } else if(packageId==4){
                        List<Sample> sampleGut = this.humanSampleMapper.getUnSynchronizationMetagenomeSamplesGut(studyId);
                        List<Sample> sampleMetagenome = this.humanSampleMapper.getUnSynchronizationMetagenomeSamplesNoGut(studyId);
                        //分类型插入
                        //插入宏基因组
                        this.saveSampleP4(sampleGut,sampleMetagenome,gsaUserId,prjId,study,submitterId,toOut);
                    }

                }
                toOut.close();
            }
        } catch (Exception e) {
            subMissionIdString = "";
            SendWe.sendSampleSynchronization("ChenXu","Human样本同步失败");
            e.printStackTrace();
        }
        return subMissionIdString;
    }
    @Transactional(rollbackFor = { Exception.class })
    public String gsaSampleSynchronizationList(List studyIdList) {
        String subMissionIdString= "suc";
        List<Sample> samples = new ArrayList();
        List<Study> studyList = this.studyMapper.getUnSynchronizationStudysList(studyIdList);
        try {
            if(studyList!=null){
//                String outPath = "/Users/laphael/Downloads/subMissionId.txt";
                String outPath = "/webdb/testApplications/gsagroup/webApp/bigtools/files/subMissionId.txt";
                File toFile = new File(outPath);
                if(toFile.exists()){
                    toFile.delete();
                }
                toFile.createNewFile();
                FileWriter toWriter = new FileWriter(toFile);
                BufferedWriter toOut = new BufferedWriter(toWriter);
                for(Study study:studyList){
                    if(studyStr!=null){
                        studyStr = studyStr + "," + study.getStudyId();
                    } else {
                        studyStr = study.getStudyId() +"";
                    }
                    System.out.println(study.getStudyId());
                    //获取gsa中user
                    String casId = study.getUser().getCasUserId();
                    cn.ac.big.bigd.webservice.model.gsa.User gsaUser = this.userMapper.selectUserByCsaId(casId);
                    int studyId = study.getStudyId();
                    int packageId = study.getPackageId();
                    int gsaUserId = gsaUser.getUserId();
                    //创建submitter
                    int submitterId = study.getSubmitterId();
                    int prjId = study.getPrjId();
                    if(packageId!=4){
                        samples = this.humanSampleMapper.getUnSynchronizationSamples(studyId);
                        if(samples!=null){
                            //插入Submitter
                            cn.ac.big.bigd.webservice.model.gsa.Submitter submitter = this.saveSubmitter(submitterId,study);
                            //创建 sample_submisson表格
                            String subMissionId = this.saveSampleSubmission(gsaUserId,submitter,prjId,study);
                            //分类型插入
                            //插入human
                            if(packageId==1||packageId==2){
                                for(Sample sample:samples ){
                                    if(packageId==1){
                                        this.saveSampleP1(sample,gsaUserId,prjId,submitter,study,subMissionId);
                                    } else if(packageId==2){
                                        this.saveSampleP2(sample,gsaUserId,prjId,submitter,study,subMissionId);
                                    }
                                }
                                //插入病原体
                            } else if(packageId==3) {
                                for(Sample sample:samples ){
                                    this.saveSampleP3(sample,gsaUserId,prjId,submitter,study,subMissionId);
                                }
                            }
                            toOut.write(subMissionId+"\n");
                        }
                    } else if(packageId==4){
                        List<Sample> sampleGut = this.humanSampleMapper.getUnSynchronizationMetagenomeSamplesGut(studyId);
                        List<Sample> sampleMetagenome = this.humanSampleMapper.getUnSynchronizationMetagenomeSamplesNoGut(studyId);
                        //分类型插入
                        //插入宏基因组
                        this.saveSampleP4(sampleGut,sampleMetagenome,gsaUserId,prjId,study,submitterId,toOut);
                    }

                }
                toOut.close();
            }
        } catch (Exception e) {
            subMissionIdString = "";
            SendWe.sendSampleSynchronization("ChenXu","Human样本同步失败");
            e.printStackTrace();
        }
        return subMissionIdString;
    }
    private cn.ac.big.bigd.webservice.model.gsa.Submitter saveSubmitter(int submitterId,Study study){
        cn.ac.big.bigd.webservice.model.gsa.Submitter submitter = new cn.ac.big.bigd.webservice.model.gsa.Submitter();
        if(submitterId==0){
            User user = study.getUser();
            cn.ac.big.bigd.webservice.model.gsa.Country country = new cn.ac.big.bigd.webservice.model.gsa.Country();
            country.setCountryId(user.getCountry().getCountryId());
            submitter.setCountry(country);
            submitter.setFirstName(user.getFirstName());
            submitter.setMiddleName(user.getMiddleName());
            submitter.setLastName(user.getLastName());
            submitter.setEmail(user.getEmail());
            submitter.setEmailSecondary("");
            submitter.setOrganization(user.getOrganization());
            submitter.setSubmitOrganizationUrl("");
            submitter.setDepartment(user.getDepartment());
            submitter.setPhone("");
            submitter.setFax("");
            submitter.setStreet(user.getStreet());
            submitter.setState("");
            submitter.setCity(user.getCity());
            submitter.setPostalCode(user.getPostalCode());
        } else {
            cn.ac.big.bigd.webservice.model.gsa.Country country = new cn.ac.big.bigd.webservice.model.gsa.Country();
            country.setCountryId(study.getSubmitter().getCountry().getCountryId());
            submitter.setCountry(country);
            submitter.setFirstName(study.getSubmitter().getFirstName());
            submitter.setMiddleName(study.getSubmitter().getMiddleName());
            submitter.setLastName(study.getSubmitter().getLastName());
            submitter.setEmail(study.getSubmitter().getEmail());
            submitter.setEmailSecondary("");
            submitter.setOrganization(study.getSubmitter().getOrganization());
            submitter.setSubmitOrganizationUrl("");
            submitter.setDepartment(study.getSubmitter().getDepartment());
            submitter.setPhone("");
            submitter.setFax("");
            submitter.setStreet(study.getSubmitter().getStreet());
            submitter.setState(study.getSubmitter().getState());
            submitter.setCity(study.getSubmitter().getCity());
            submitter.setPostalCode(study.getSubmitter().getPostalCode());
        }
        int sub = this.submitterMapper.insertHumanSubmitter(submitter);
        return submitter;

    }
    private String saveSampleSubmission(int gsaUserId, cn.ac.big.bigd.webservice.model.gsa.Submitter submitter, int prjId, Study study){
        SampleSubmission sampleSubmission = new SampleSubmission();
        sampleSubmission.setSamState(0);
        sampleSubmission.setUserId(gsaUserId);
        sampleSubmission.setSubmitterId(submitter.getSubmitterId());
        sampleSubmission.setPrjId(prjId);
        sampleSubmission.setSubmissionType(1);
        sampleSubmission.setStatus(3);
        sampleSubmission.setReleaseState(study.getReleaseState());
        sampleSubmission.setCreateTime(new Date());
        sampleSubmission.setReleaseTime(study.getReleaseTime());
        sampleSubmission.setSamSubmissionId("0");
        sampleSubmission.setIsControlled(1);
        this.sampleSubmissionMapper.saveSampleSubmission(sampleSubmission);
        int subId = sampleSubmission.getSubmissionId();
        String subMissionId;
        if((subId+"").length()>6) {
            subMissionId = "subSAM" +   (subId+"");
        } else {
            String prefix="000000";
            prefix = prefix.substring(0,6 - (subId+"").length());
            subMissionId = "subSAM" + prefix + (subId+"");
        }
        SampleSubmission newSampleSubmission = new SampleSubmission();
        newSampleSubmission.setSubmissionId(subId);
        newSampleSubmission.setSamSubmissionId(subMissionId);
        this.sampleSubmissionMapper.updateSampleSubmissionBySubmissionId(newSampleSubmission);
        return subMissionId;
    }
    private void saveSampleP1(Sample sample, int gsaUserId, int prjId, cn.ac.big.bigd.webservice.model.gsa.Submitter submitter, Study study, String subMissionId){
        String name = sample.getName();
        String desc = sample.getPublicDescription();
        String title = sample.getTitle();
        String age = sample.getAge();
        String ageUnit = sample.getAgeUnit();
        String karyotype = sample.getKaryotype();
        String phenotype = sample.getPhenotype();
        String population = sample.getPopulation();
        String collectionDate = sample.getCollectionDate();
        Date releaseTime = sample.getReleaseTime();
        int sex = sample.getGender();
        JSONObject sampleAttr = sample.getAttributes();
        JSONObject sampleCusAttr = sample.getCustomAttributes();
        String biomaterial_provider = (String)sampleAttr.get("biomaterial_provider");
        String tissue = sample.getTissue();
        String culture_collection = (String)sampleAttr.get("culture_collection");
        JSONObject indAttr = sample.getiAttr();
        String disease_name = (String)indAttr.get("disease_name");
        String disease_stage = (String)indAttr.get("disease_stage");
        String ethnicity = sample.getEthnicity();
        String race = (String)indAttr.get("race");
        String treatment = (String)indAttr.get("treatment");
        int taxonId = 9606;
        int control = study.getIsControlledAccess();
        cn.ac.big.bigd.webservice.model.gsa.SampleSynchronization sampleGsa = new cn.ac.big.bigd.webservice.model.gsa.SampleSynchronization();
        sampleGsa.setIsControlledAccess(control);
        sampleGsa.setUserId(gsaUserId);
        sampleGsa.setPrjId(prjId);
        sampleGsa.setSubmitterId(submitter.getSubmitterId());
        sampleGsa.setName(name);
        sampleGsa.setTaxonId(taxonId);
        sampleGsa.setSampleTypeId(5);
        sampleGsa.setIsControlled(1);
        sampleGsa.setTitle(title);
        sampleGsa.setReleaseTime(releaseTime);
        sampleGsa.setPublicDescription(desc);
        sampleGsa.setCreateTime(new Date());
        sampleGsa.setStatus(3);
        sampleGsa.setReleaseState(sample.getReleaseState());
        sampleGsa.setIsReleaseImmediately(study.getIsReleaseImmediately());
        sampleGsa.setSubmissionId(subMissionId);
        sampleGsa.setSubmissionType(1);
        sampleGsa.setSubmissionStatus(3);
        sampleGsa.setBatchOption(0);
        sampleGsa.setIsSynchronization(1);
        sampleGsa.setSynchronizationAccession(sample.getAccession());
        sampleGsa.setSynchronizationTime(new Date());
        JSONObject gsaSampleAttr = new JSONObject();
        gsaSampleAttr.put("collection_date",collectionDate);
        if(sampleCusAttr!=null){
            for(Map.Entry<String, Object> map:sampleCusAttr.entrySet()){
                String key = map.getKey();
                String value = (String) map.getValue();
                gsaSampleAttr.put(key,value);
            }
        }
        sampleGsa.setAttributes(gsaSampleAttr);
        sampleGsa.setOtherDb("GSA-Human");
        sampleGsa.setAccessionInOtherDb(sample.getAccession());
        sampleGsa.setUrlInOtherDb("https://ngdc.cncb.ac.cn/gsa-human/browse/sampleDetail/"+sample.getAccession());
        this.sampleMapper.saveSampleByHuman(sampleGsa);
        int sampleId = sampleGsa.getSampleId();
        SampleAttrHumanSynchronization sampleAttrHumanSynchronization = new SampleAttrHumanSynchronization();
        sampleAttrHumanSynchronization.setTaxonId(9606);
        sampleAttrHumanSynchronization.setSampleId(sampleId);
        sampleAttrHumanSynchronization.setIsolate("not collected");
        sampleAttrHumanSynchronization.setAge(age);
        sampleAttrHumanSynchronization.setAgeUnit(ageUnit);
        sampleAttrHumanSynchronization.setBiomaterialProvider(biomaterial_provider);
        sampleAttrHumanSynchronization.setSex(sex);
        sampleAttrHumanSynchronization.setTissue(tissue);
        sampleAttrHumanSynchronization.setCultureCollection(culture_collection);
        sampleAttrHumanSynchronization.setDisease(disease_name);
        sampleAttrHumanSynchronization.setDiseaseStage(disease_stage);
        sampleAttrHumanSynchronization.setEthnicity(ethnicity);
        sampleAttrHumanSynchronization.setKaryotype(karyotype);
        sampleAttrHumanSynchronization.setPhenotype(phenotype);
        sampleAttrHumanSynchronization.setPopulation(population);
        sampleAttrHumanSynchronization.setRace(race);
        sampleAttrHumanSynchronization.setTreatment(treatment);
        this.sampleAttrHumanMapper.saveSampleAttrHumanByHuman(sampleAttrHumanSynchronization);
    }
    private void saveSampleP2(Sample sample, int gsaUserId, int prjId, cn.ac.big.bigd.webservice.model.gsa.Submitter submitter, Study study, String subMissionId){
        String name = sample.getName();
        String desc = sample.getPublicDescription();
        String title = sample.getTitle();
        String karyotype = sample.getKaryotype();
        String phenotype = sample.getPhenotype();
        String collectionDate = sample.getCollectionDate();
        Date releaseTime = sample.getReleaseTime();
        int sex = sample.getGender();
        JSONObject sampleAttr = sample.getAttributes();
        JSONObject sampleCusAttr = sample.getCustomAttributes();
        String biomaterial_provider = (String)sampleAttr.get("biomaterial_provider");
        String tissue = sample.getTissue();
        String culture_collection = (String)sampleAttr.get("culture_collection");
        String ethnicity = sample.getEthnicity();
        int taxonId = 9606;
        int control = study.getIsControlledAccess();
        cn.ac.big.bigd.webservice.model.gsa.SampleSynchronization sampleGsa = new cn.ac.big.bigd.webservice.model.gsa.SampleSynchronization();
        sampleGsa.setIsControlledAccess(control);
        sampleGsa.setUserId(gsaUserId);
        sampleGsa.setPrjId(prjId);
        sampleGsa.setSubmitterId(submitter.getSubmitterId());
        sampleGsa.setName(name);
        sampleGsa.setTaxonId(taxonId);
        sampleGsa.setSampleTypeId(5);
        sampleGsa.setIsControlled(1);
        sampleGsa.setTitle(title);
        sampleGsa.setReleaseTime(releaseTime);
        sampleGsa.setPublicDescription(desc);
        sampleGsa.setCreateTime(new Date());
        sampleGsa.setStatus(3);
        sampleGsa.setReleaseState(sample.getReleaseState());
        sampleGsa.setIsReleaseImmediately(study.getIsReleaseImmediately());
        sampleGsa.setSubmissionId(subMissionId);
        sampleGsa.setSubmissionType(1);
        sampleGsa.setSubmissionStatus(3);
        sampleGsa.setBatchOption(0);
        sampleGsa.setIsSynchronization(1);
        sampleGsa.setSynchronizationAccession(sample.getAccession());
        sampleGsa.setSynchronizationTime(new Date());
        JSONObject gsaSampleAttr = new JSONObject();
        gsaSampleAttr.put("collection_date",collectionDate);
        if(sampleCusAttr!=null){
            for(Map.Entry<String, Object> map:sampleCusAttr.entrySet()){
                String key = map.getKey();
                String value = (String) map.getValue();
                gsaSampleAttr.put(key,value);
            }
        }
        sampleGsa.setAttributes(gsaSampleAttr);
        sampleGsa.setOtherDb("GSA-Human");
        sampleGsa.setAccessionInOtherDb(sample.getAccession());
        sampleGsa.setUrlInOtherDb("https://ngdc.cncb.ac.cn/gsa-human/browse/sampleDetail/"+sample.getAccession());
        this.sampleMapper.saveSampleByHuman(sampleGsa);
        int sampleId = sampleGsa.getSampleId();
        SampleAttrHumanSynchronization sampleAttrHumanSynchronization = new SampleAttrHumanSynchronization();
        sampleAttrHumanSynchronization.setTaxonId(9606);
        sampleAttrHumanSynchronization.setSampleId(sampleId);
        sampleAttrHumanSynchronization.setIsolate("not collected");
        sampleAttrHumanSynchronization.setBiomaterialProvider(biomaterial_provider);
        sampleAttrHumanSynchronization.setSex(sex);
        sampleAttrHumanSynchronization.setTissue(tissue);
        sampleAttrHumanSynchronization.setCultureCollection(culture_collection);
        sampleAttrHumanSynchronization.setEthnicity(ethnicity);
        sampleAttrHumanSynchronization.setKaryotype(karyotype);
        sampleAttrHumanSynchronization.setPhenotype(phenotype);
        this.sampleAttrHumanMapper.saveSampleAttrHumanByHuman(sampleAttrHumanSynchronization);
    }
    private void saveSampleP3(Sample sample, int gsaUserId, int prjId, cn.ac.big.bigd.webservice.model.gsa.Submitter submitter, Study study, String subMissionId) throws ParseException {
        String name = sample.getName();
        String desc = sample.getPublicDescription();
        String title = sample.getTitle();
        JSONObject sampleAttr = sample.getAttributes();
        JSONObject sampleCusAttr = sample.getCustomAttributes();
        JSONObject indAttr = sample.getiAttr();
        int taxonId = Integer.parseInt(sampleAttr.get("taxon_id").toString());
        String isolate = (String) sampleAttr.get("isolate");
        String strain = (String) sampleAttr.get("strain");
        String collectedBy = (String) sampleAttr.get("collected_by");
        String collectionDate = sample.getCollectionDate();
        String geographicLocation = (String) sampleAttr.get("geographic_location");
        int host = 9606;
        String hostDisease = (String)indAttr.get("disease_name");
        String isolationSource = (String) sampleAttr.get("isolation_source");
        String latitudeLongitude = (String) sampleAttr.get("latitude_longitude");
        String cultureCollection = (String) sampleAttr.get("culture_collection");
        String genotype = (String) sampleAttr.get("genotype");
        String hostAge = (String) sampleAttr.get("host_age");
        String hostAgeUnit = (String) sampleAttr.get("host_age_unit");
        String hostDescription = (String)indAttr.get("description");
        String hostDiseaseOutcome = (String)indAttr.get("disease_outcome");
        String hostDiseaseStage = (String)indAttr.get("disease_stage");
        String hostHealthState = (String)indAttr.get("health_state");
        int hostSex = sample.getGender();
        String hostSubjectId = (String)sample.getiAcc();
        String hostTissueSampled = (String) sampleAttr.get("host_tissue_sampled");
        String passageHistory = (String) sampleAttr.get("passage_history");
        String pathotype = (String) sampleAttr.get("pathotype");
        String serotype = (String) sampleAttr.get("serotype");
        String serovar = (String) sampleAttr.get("serovar");
        String specimenVoucher = (String) sampleAttr.get("specimen_voucher");
        String subgroup = (String) sampleAttr.get("subgroup");
        String subtype = (String) sampleAttr.get("subtype");
        Date releaseTime = sample.getReleaseTime();
        //System.out.println(hostSubjectId);
        int control = study.getIsControlledAccess();
        cn.ac.big.bigd.webservice.model.gsa.SampleSynchronization sampleGsa = new cn.ac.big.bigd.webservice.model.gsa.SampleSynchronization();
        sampleGsa.setIsControlledAccess(control);
        sampleGsa.setUserId(gsaUserId);
        sampleGsa.setPrjId(prjId);
        sampleGsa.setSubmitterId(submitter.getSubmitterId());
        sampleGsa.setName(name);
        sampleGsa.setTaxonId(taxonId);
        sampleGsa.setSampleTypeId(1);
        sampleGsa.setIsControlled(1);
        sampleGsa.setTitle(title);
        sampleGsa.setReleaseTime(releaseTime);
        sampleGsa.setPublicDescription(desc);
        sampleGsa.setCreateTime(new Date());
        sampleGsa.setStatus(3);
        sampleGsa.setReleaseState(sample.getReleaseState());
        sampleGsa.setIsReleaseImmediately(study.getIsReleaseImmediately());
        sampleGsa.setSubmissionId(subMissionId);
        sampleGsa.setSubmissionType(1);
        sampleGsa.setSubmissionStatus(3);
        sampleGsa.setBatchOption(0);
        sampleGsa.setIsSynchronization(1);
        sampleGsa.setSynchronizationAccession(sample.getAccession());
        sampleGsa.setSynchronizationTime(new Date());
        JSONObject gsaSampleAttr = new JSONObject();
        if(sampleCusAttr!=null){
            for(Map.Entry<String, Object> map:sampleCusAttr.entrySet()){
                String key = map.getKey();
                String value = (String) map.getValue();
                gsaSampleAttr.put(key,value);
            }
        }
        sampleGsa.setAttributes(gsaSampleAttr);
        sampleGsa.setOtherDb("GSA-Human");
        sampleGsa.setAccessionInOtherDb(sample.getAccession());
        sampleGsa.setUrlInOtherDb("https://ngdc.cncb.ac.cn/gsa-human/browse/sampleDetail/"+sample.getAccession());
        this.sampleMapper.saveSampleByHuman(sampleGsa);
        int sampleId = sampleGsa.getSampleId();
        SampleAttrPathogenClinicalHostSynchronization sampleAttrPathogenClinicalHostSynchronization = new SampleAttrPathogenClinicalHostSynchronization();
        sampleAttrPathogenClinicalHostSynchronization.setSampleId(sampleId);
        sampleAttrPathogenClinicalHostSynchronization.setCollectedBy(collectedBy);
        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
        sampleAttrPathogenClinicalHostSynchronization.setCollectionDate(date.parse(collectionDate));
        sampleAttrPathogenClinicalHostSynchronization.setGeographicLocation(geographicLocation);
        sampleAttrPathogenClinicalHostSynchronization.setTaxonId(taxonId);
        sampleAttrPathogenClinicalHostSynchronization.setHostTaxonId(host);
        sampleAttrPathogenClinicalHostSynchronization.setIsolationSource(isolationSource);
        sampleAttrPathogenClinicalHostSynchronization.setLatitudeLongitude(latitudeLongitude);
        sampleAttrPathogenClinicalHostSynchronization.setHostSex(hostSex);
        sampleAttrPathogenClinicalHostSynchronization.setHostSubjectId(hostSubjectId);

        sampleAttrPathogenClinicalHostSynchronization.setIsolate(isolate);
        sampleAttrPathogenClinicalHostSynchronization.setStrain(strain);
        sampleAttrPathogenClinicalHostSynchronization.setHostDisease(hostDisease);
        sampleAttrPathogenClinicalHostSynchronization.setCultureCollection(cultureCollection);
        sampleAttrPathogenClinicalHostSynchronization.setGenotype(genotype);
        sampleAttrPathogenClinicalHostSynchronization.setHostAge(hostAge);
        sampleAttrPathogenClinicalHostSynchronization.setHostAgeUnit(hostAgeUnit);
        sampleAttrPathogenClinicalHostSynchronization.setHostDescription(hostDescription);
        sampleAttrPathogenClinicalHostSynchronization.setHostDiseaseOutcome(hostDiseaseOutcome);
        sampleAttrPathogenClinicalHostSynchronization.setHostDiseaseStage(hostDiseaseStage);
        sampleAttrPathogenClinicalHostSynchronization.setHostHealthState(hostHealthState);
        sampleAttrPathogenClinicalHostSynchronization.setHostTissueSampled(hostTissueSampled);
        sampleAttrPathogenClinicalHostSynchronization.setPassageHistory(passageHistory);
        sampleAttrPathogenClinicalHostSynchronization.setPathotype(pathotype);
        sampleAttrPathogenClinicalHostSynchronization.setSerotype(serotype);
        sampleAttrPathogenClinicalHostSynchronization.setSerovar(serovar);
        sampleAttrPathogenClinicalHostSynchronization.setSpecimenVoucher(specimenVoucher);
        sampleAttrPathogenClinicalHostSynchronization.setSubgroup(subgroup);
        sampleAttrPathogenClinicalHostSynchronization.setSubtype(subtype);
        this.sampleAttrPathogenClinicalHostMapper.saveSampleAttrPathogenClinicalHostByHuman(sampleAttrPathogenClinicalHostSynchronization);
    }
    private void saveSampleP4(List<Sample> sampleGut,List<Sample> sampleMetagenome, int gsaUserId, int prjId, Study study,int submitterId,BufferedWriter toOut) throws ParseException, IOException {
        if(sampleGut!=null){
            //插入Submitter
            cn.ac.big.bigd.webservice.model.gsa.Submitter submitter = this.saveSubmitter(submitterId,study);
            //创建 sample_submisson表格
            String subMissionId = this.saveSampleSubmission(gsaUserId,submitter,prjId,study);
            for(Sample sample:sampleGut){
                String name = sample.getName();
                String desc = sample.getPublicDescription();
                String title = sample.getTitle();
                JSONObject sampleAttr = sample.getAttributes();
                JSONObject sampleCusAttr = sample.getCustomAttributes();
                JSONObject indAttr = sample.getiAttr();
                int taxonId = Integer.parseInt(sampleAttr.get("taxon_id").toString());
                String environment = (String) sampleAttr.get("environment_type");
                int host = Integer.parseInt(sampleAttr.get("taxonHost_id").toString());
                String collectionDate = sample.getCollectionDate();
                String geographicLocation = (String) sampleAttr.get("geographic_location");
                String latitudeLongitude = (String) sampleAttr.get("latitude_longitude");
                String envBroadScale = (String) sampleAttr.get("env_broad_scale");
                String envLocalScale = (String) sampleAttr.get("env_local_scale");
                String environmentMedium = (String) sampleAttr.get("environment_medium");
                String chemicalAdministration = (String) sampleAttr.get("chemical_administration");
                String gastrointestinalTractDisorder = (String) sampleAttr.get("gastrointestinal_tract_disorder");
                String hostAge = (String) sampleAttr.get("host_age");
                String hostAgeUnit = (String) sampleAttr.get("host_age_unit");
                String ethnicity = sample.getEthnicity();
                String hostBodyProduct = (String) sampleAttr.get("host_body_product");
                String hostBodyTemperature = (String) sampleAttr.get("host_body_temperature");
                String hostDiet = (String) sampleAttr.get("host_diet");
                String hostDisease = (String)indAttr.get("disease_name");
                String hostGenotype = (String)sampleAttr.get("host_genotype");
                String hostHeight = (String)indAttr.get("height");
                String hostPhenotype = (String) sampleAttr.get("host_phenotype");
                int hostSex = sample.getGender();
                String hostSubjectId = (String)sample.getiAcc();
                String hostTissueSampled = (String) sampleAttr.get("host_tissue_sampled");
                String isolationSource = (String) sampleAttr.get("isolation_source");
                String liverDisorder = (String) sampleAttr.get("liver_disorder");
                String medicalHistoryPerformed = (String) sampleAttr.get("medical_history_performed");
                String organismCount = (String) sampleAttr.get("organism_count");
                int oxygenationStatus = 0;
                if(sampleAttr.get("oxygenation_status")!=null){
                    oxygenationStatus = Integer.parseInt(sampleAttr.get("oxygenation_status").toString());
                }
                Date releaseTime = sample.getReleaseTime();
                int control = study.getIsControlledAccess();
                cn.ac.big.bigd.webservice.model.gsa.SampleSynchronization sampleGsa = new cn.ac.big.bigd.webservice.model.gsa.SampleSynchronization();
                sampleGsa.setIsControlledAccess(control);
                sampleGsa.setUserId(gsaUserId);
                sampleGsa.setPrjId(prjId);
                sampleGsa.setSubmitterId(submitter.getSubmitterId());
                sampleGsa.setName(name);
                sampleGsa.setTaxonId(taxonId);
                sampleGsa.setSampleTypeId(9);
                sampleGsa.setIsControlled(1);
                sampleGsa.setTitle(title);
                sampleGsa.setReleaseTime(releaseTime);
                sampleGsa.setPublicDescription(desc);
                sampleGsa.setCreateTime(new Date());
                sampleGsa.setStatus(3);
                sampleGsa.setReleaseState(sample.getReleaseState());
                sampleGsa.setIsReleaseImmediately(study.getIsReleaseImmediately());
                sampleGsa.setSubmissionId(subMissionId);
                sampleGsa.setSubmissionType(1);
                sampleGsa.setSubmissionStatus(3);
                sampleGsa.setBatchOption(0);
                sampleGsa.setIsSynchronization(1);
                sampleGsa.setSynchronizationAccession(sample.getAccession());
                sampleGsa.setSynchronizationTime(new Date());
                JSONObject gsaSampleAttr = new JSONObject();
                gsaSampleAttr.put("environment_type",environment);
                if(sampleCusAttr!=null){
                    for(Map.Entry<String, Object> map:sampleCusAttr.entrySet()){
                        String key = map.getKey();
                        String value = (String) map.getValue();
                        gsaSampleAttr.put(key,value);
                    }
                }
            sampleGsa.setAttributes(gsaSampleAttr);
            sampleGsa.setOtherDb("GSA-Human");
            sampleGsa.setAccessionInOtherDb(sample.getAccession());
            sampleGsa.setUrlInOtherDb("https://ngdc.cncb.ac.cn/gsa-human/browse/sampleDetail/"+sample.getAccession());
            this.sampleMapper.saveSampleByHuman(sampleGsa);
            int sampleId = sampleGsa.getSampleId();
            SampleAttrMimsmeHumanGutSynchronization sampleAttrMimsmeHumanGutSynchronization = new SampleAttrMimsmeHumanGutSynchronization();
            sampleAttrMimsmeHumanGutSynchronization.setSampleId(sampleId);
            sampleAttrMimsmeHumanGutSynchronization.setTaxonId(taxonId);
            sampleAttrMimsmeHumanGutSynchronization.setHostOrganismId(host);
            SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
            sampleAttrMimsmeHumanGutSynchronization.setCollectionDate(date.parse(collectionDate));
            sampleAttrMimsmeHumanGutSynchronization.setGeographicLocation(geographicLocation);
            sampleAttrMimsmeHumanGutSynchronization.setLatitudeLongitude(latitudeLongitude);
            sampleAttrMimsmeHumanGutSynchronization.setEnvironmentBiome(envBroadScale);
            sampleAttrMimsmeHumanGutSynchronization.setEnvironmentFeature(envLocalScale);
            sampleAttrMimsmeHumanGutSynchronization.setEnvironmentMaterial(environmentMedium);
            sampleAttrMimsmeHumanGutSynchronization.setChemicalAdministration(chemicalAdministration);
            sampleAttrMimsmeHumanGutSynchronization.setGastrointestinalTractDisorder(gastrointestinalTractDisorder);
            sampleAttrMimsmeHumanGutSynchronization.setHostAge(hostAge);
            sampleAttrMimsmeHumanGutSynchronization.setHostAgeUnit(hostAgeUnit);
            sampleAttrMimsmeHumanGutSynchronization.setEthnicity(ethnicity);
            sampleAttrMimsmeHumanGutSynchronization.setHostProduct(hostBodyProduct);
            sampleAttrMimsmeHumanGutSynchronization.setHostTemperature(hostBodyTemperature);
            sampleAttrMimsmeHumanGutSynchronization.setHostDiet(hostDiet);
            sampleAttrMimsmeHumanGutSynchronization.setHostDisease(hostDisease);
            sampleAttrMimsmeHumanGutSynchronization.setHostGenotype(hostGenotype);
            sampleAttrMimsmeHumanGutSynchronization.setHostHeight(hostHeight);
            sampleAttrMimsmeHumanGutSynchronization.setHostPhenotype(hostPhenotype);
            sampleAttrMimsmeHumanGutSynchronization.setSex(hostSex);
            sampleAttrMimsmeHumanGutSynchronization.setHostSubjectId(hostSubjectId);
            sampleAttrMimsmeHumanGutSynchronization.setHostTissueSampled(hostTissueSampled);
            sampleAttrMimsmeHumanGutSynchronization.setIsolationSource(isolationSource);
            sampleAttrMimsmeHumanGutSynchronization.setLiverDisorder(liverDisorder);
            sampleAttrMimsmeHumanGutSynchronization.setMedicalHistoryPerformed(medicalHistoryPerformed);
            sampleAttrMimsmeHumanGutSynchronization.setOrganismCount(organismCount);
            if(oxygenationStatus!=0){
                sampleAttrMimsmeHumanGutSynchronization.setOxygenationStatus(oxygenationStatus);
            }
            this.sampleAttrMimsmeHumanGutMapper.saveSampleAttrMimsmeHumanGutByHuman(sampleAttrMimsmeHumanGutSynchronization);
            }
            toOut.write(subMissionId+"\n");
        }
        if(sampleMetagenome!=null){
            //插入Submitter
            cn.ac.big.bigd.webservice.model.gsa.Submitter submitter = this.saveSubmitter(submitterId,study);
            //创建 sample_submisson表格
            String subMissionId = this.saveSampleSubmission(gsaUserId,submitter,prjId,study);
            for(Sample sample:sampleMetagenome){
                String name = sample.getName();
                String desc = sample.getPublicDescription();
                String title = sample.getTitle();
                JSONObject sampleAttr = sample.getAttributes();
                JSONObject sampleCusAttr = sample.getCustomAttributes();
                JSONObject indAttr = sample.getiAttr();
                int taxonId = Integer.parseInt(sampleAttr.get("taxon_id").toString());
                int host = Integer.parseInt(sampleAttr.get("taxonHost_id").toString());
                String isolationSource = (String) sampleAttr.get("isolation_source");
                String collectionDate = sample.getCollectionDate();
                String geographicLocation = (String) sampleAttr.get("geographic_location");
                String latitudeLongitude = (String) sampleAttr.get("latitude_longitude");

                String environment = (String) sampleAttr.get("environment_type");
                String envBroadScale = (String) sampleAttr.get("env_broad_scale");
                String envLocalScale = (String) sampleAttr.get("env_local_scale");
                String environmentMedium = (String) sampleAttr.get("environment_medium");

                String chemicalAdministration = (String) sampleAttr.get("chemical_administration");
                String gastrointestinalTractDisorder = (String) sampleAttr.get("gastrointestinal_tract_disorder");
                String hostAge = (String) sampleAttr.get("host_age");
                String hostAgeUnit = (String) sampleAttr.get("host_age_unit");
                String hostBodyProduct = (String) sampleAttr.get("host_body_product");
                String hostBodyTemperature = (String) sampleAttr.get("host_body_temperature");
                String hostDiet = (String) sampleAttr.get("host_diet");
                String hostGenotype = (String)sampleAttr.get("host_genotype");
                String hostPhenotype = (String) sampleAttr.get("host_phenotype");
                String hostTissueSampled = (String) sampleAttr.get("host_tissue_sampled");
                String liverDisorder = (String) sampleAttr.get("liver_disorder");
                String medicalHistoryPerformed = (String) sampleAttr.get("medical_history_performed");
                String organismCount = (String) sampleAttr.get("organism_count");
                int oxygenationStatus = 0;
                if(sampleAttr.get("oxygenation_status")!=null){
                    oxygenationStatus = Integer.parseInt(sampleAttr.get("oxygenation_status").toString());
                }
                String noseMouthTeethThroatDisorder = (String) sampleAttr.get("nose_mouth_teeth_throat_disorder");
                String pulmonaryDisorder = (String) sampleAttr.get("pulmonary_disorder");
                String dermatologyDisorder = (String) sampleAttr.get("dermatology_disorder");
                String gynecologicDisorder = (String) sampleAttr.get("gynecologic_disorder");
                String urogenitDisorder = (String) sampleAttr.get("urogenit_disorder");

                int hostSex = sample.getGender();
                String hostSubjectId = (String)sample.getiAcc();

                String ethnicity = sample.getEthnicity();
                String hostDisease = (String)indAttr.get("disease_name");
                String hostHeight = (String)indAttr.get("height");
                String province = (String)indAttr.get("province");
                String occupation = (String)indAttr.get("occupation");
                String race = (String)indAttr.get("race");

                Date releaseTime = sample.getReleaseTime();

                cn.ac.big.bigd.webservice.model.gsa.SampleSynchronization sampleGsa = new cn.ac.big.bigd.webservice.model.gsa.SampleSynchronization();
                sampleGsa.setUserId(gsaUserId);
                sampleGsa.setPrjId(prjId);
                sampleGsa.setSubmitterId(submitter.getSubmitterId());
                sampleGsa.setName(name);
                sampleGsa.setTaxonId(taxonId);
                sampleGsa.setSampleTypeId(8);
                sampleGsa.setIsControlled(1);
                sampleGsa.setTitle(title);
                sampleGsa.setReleaseTime(releaseTime);
                sampleGsa.setPublicDescription(desc);
                sampleGsa.setCreateTime(new Date());
                sampleGsa.setStatus(3);
                sampleGsa.setReleaseState(sample.getReleaseState());
                sampleGsa.setIsReleaseImmediately(study.getIsReleaseImmediately());
                sampleGsa.setSubmissionId(subMissionId);
                sampleGsa.setSubmissionType(1);
                sampleGsa.setSubmissionStatus(3);
                sampleGsa.setBatchOption(0);
                sampleGsa.setIsSynchronization(1);
                sampleGsa.setSynchronizationAccession(sample.getAccession());
                sampleGsa.setSynchronizationTime(new Date());
                JSONObject gsaSampleAttr = new JSONObject();
                gsaSampleAttr.put("environment_type",environment);
                gsaSampleAttr.put("env_broad_scale",envBroadScale);
                gsaSampleAttr.put("env_local_scale",envLocalScale);
                gsaSampleAttr.put("environment_medium",environmentMedium);
                gsaSampleAttr.put("host_sex",hostSex);
                gsaSampleAttr.put("host_subject_id",hostSubjectId);
                if(chemicalAdministration!=null){
                    gsaSampleAttr.put("chemical_administration",chemicalAdministration);
                }
                if(gastrointestinalTractDisorder!=null){
                    gsaSampleAttr.put("gastrointestinal_tract_disorder",gastrointestinalTractDisorder);
                }

                if(hostAge!=null){
                    gsaSampleAttr.put("host_age",hostAge);
                }
                if(hostAgeUnit!=null){
                    gsaSampleAttr.put("host_age_unit",hostAgeUnit);
                }
                if(hostBodyProduct!=null){
                    gsaSampleAttr.put("host_body_product",hostBodyProduct);
                }
                if(hostBodyTemperature!=null){
                    gsaSampleAttr.put("host_body_temperature",hostBodyTemperature);
                }
                if(hostDiet!=null){
                    gsaSampleAttr.put("host_diet",hostDiet);
                }
                if(hostGenotype!=null){
                    gsaSampleAttr.put("host_genotype",hostGenotype);
                }
                if(hostPhenotype!=null){
                    gsaSampleAttr.put("host_phenotype",hostPhenotype);
                }
                if(hostTissueSampled!=null){
                    gsaSampleAttr.put("host_tissue_sampled",hostTissueSampled);
                }
                if(liverDisorder!=null){
                    gsaSampleAttr.put("liver_disorder",liverDisorder);
                }
                if(medicalHistoryPerformed!=null){
                    gsaSampleAttr.put("medical_history_performed",medicalHistoryPerformed);
                }
                if(organismCount!=null){
                    gsaSampleAttr.put("organism_count",organismCount);
                }
                if(oxygenationStatus!=0){
                    gsaSampleAttr.put("oxygenation_status",oxygenationStatus);
                }
                if(noseMouthTeethThroatDisorder!=null){
                    gsaSampleAttr.put("nose_mouth_teeth_throat_disorder",noseMouthTeethThroatDisorder);
                }
                if(pulmonaryDisorder!=null){
                    gsaSampleAttr.put("pulmonary_disorder",pulmonaryDisorder);
                }
                if(dermatologyDisorder!=null){
                    gsaSampleAttr.put("dermatology_disorder",dermatologyDisorder);
                }
                if(gynecologicDisorder!=null){
                    gsaSampleAttr.put("gynecologic_disorder",gynecologicDisorder);
                }
                if(urogenitDisorder!=null){
                    gsaSampleAttr.put("urogenit_disorder",urogenitDisorder);
                }
                if(ethnicity!=null){
                    gsaSampleAttr.put("ethnicity",ethnicity);
                }
                if(hostDisease!=null){
                    gsaSampleAttr.put("host_disease",hostDisease);
                }
                if(hostHeight!=null){
                    gsaSampleAttr.put("host_height",hostHeight);
                }
                if(province!=null){
                    gsaSampleAttr.put("province",province);
                }
                if(occupation!=null){
                    gsaSampleAttr.put("occupation",occupation);
                }
                if(race!=null){
                    gsaSampleAttr.put("race",race);
                }

                if(sampleCusAttr!=null){
                    for(Map.Entry<String, Object> map:sampleCusAttr.entrySet()){
                        String key = map.getKey();
                        String value = (String) map.getValue();
                        gsaSampleAttr.put(key,value);
                    }
                }
                sampleGsa.setAttributes(gsaSampleAttr);
                this.sampleMapper.saveSampleByHuman(sampleGsa);
                int sampleId = sampleGsa.getSampleId();
                SampleAttrMetagenomeEnvironmentalSynchronization sampleAttrMetagenomeEnvironmentalSynchronization = new SampleAttrMetagenomeEnvironmentalSynchronization();
                sampleAttrMetagenomeEnvironmentalSynchronization.setSampleId(sampleId);
                sampleAttrMetagenomeEnvironmentalSynchronization.setTaxonId(taxonId);
                sampleAttrMetagenomeEnvironmentalSynchronization.setHostOrganismId(host);
                sampleAttrMetagenomeEnvironmentalSynchronization.setIsolationSource(isolationSource);
                SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
                sampleAttrMetagenomeEnvironmentalSynchronization.setCollectionDate(date.parse(collectionDate));
                sampleAttrMetagenomeEnvironmentalSynchronization.setGeographicLocation(geographicLocation);
                sampleAttrMetagenomeEnvironmentalSynchronization.setLatitudeLongitude(latitudeLongitude);
                this.sampleAttrMetagenomeEnvironmentalMapper.saveSampleAttrMetagenomeEnvironmentalByHuman(sampleAttrMetagenomeEnvironmentalSynchronization);
            }
            toOut.write(subMissionId+"\n");
        }
    }
    public String getStudyStr() {
        return studyStr;
    }

    public void setStudyStr(String studyStr) {
        this.studyStr = studyStr;
    }
}
