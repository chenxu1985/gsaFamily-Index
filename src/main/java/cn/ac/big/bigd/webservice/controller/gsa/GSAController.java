package cn.ac.big.bigd.webservice.controller.gsa;

import cn.ac.big.bigd.webservice.mapper.gsa.*;
import cn.ac.big.bigd.webservice.model.gsa.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * author chenxu
 */
@RestController
public class GSAController {
    @Autowired
    private GsaMapper gsaMapper;
    @Autowired
    private ProjectMapper projectMapper;
    @Autowired
    private SampleMapper sampleMapper;
    @Autowired
    private KeyMapper keyMapper;
    @Autowired
    private UserMapper userMapper;
    @RequestMapping("/getGsaRecords/{startTime}/{endTime}")
    public List<GSAForBioOneJson> getGsaRecordsByReleaseTime(@PathVariable("startTime") String startTime, @PathVariable("endTime") String endTime, HttpServletResponse httpServletResponse) {
        Map<String,String> params = new HashMap<String,String>();
        params.put("startTime",startTime);
        params.put("endTime",endTime);
        httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");
        List<GSAForBioOneJson> jsonrRecords = new ArrayList<GSAForBioOneJson>();
        List<GSAForBioOne> records = new ArrayList<GSAForBioOne>();
//        System.out.println("aaa"+startTime);
//        System.out.println("bbb"+endTime);
//        if (term.trim().startsWith("SAMC") || term.trim().startsWith("samc")) {
//            Sample sample = sampleMapper.getSampleByAccession(term);
//            if (sample != null) {
//                setAncestorAndAttribute(sample);
//                samples.add(sample);
//            }
//        } else {
//            samples = this.sampleMapper.getSamplesByTerm(term.trim());
//            if (samples != null) {
//                for (Sample sample : samples) {
//                    setAncestorAndAttribute(sample);
//                }
//            }
//        }
        records=gsaMapper.selectRecordsByReleaseDate(params);
        if(records!=null && records.size()>0){
            for(GSAForBioOne record:records){
                GSAForBioOneJson jsonRecord = new GSAForBioOneJson();
                jsonRecord.setBioProjectAccession(record.getBioProjectAccession());
                jsonRecord.setBioProjectTitle(record.getBioProjectTitle());
                jsonRecord.setBioSampleAccession(record.getBioSampleAccession());
                jsonRecord.setBioSampleTitle(record.getBioSampleTitle());
                jsonRecord.setGsaAccession(record.getGsaAccession());
                jsonRecord.setExperimentAccession(record.getExperimentAccession());
                jsonRecord.setInsertSize(record.getInsertSize());
                if(record.getLibraryLayout().equals("1"))
                    jsonRecord.setLibraryLayout("Fragement");
                else
                    jsonRecord.setLibraryLayout("Paired End");
//                (record.getLibraryLayout().equals("1"))?jsonRecord.setLibraryDesign("Fragement"):jsonRecord.setLibraryDesign("Paired End");
//                jsonRecord.setLibraryLayout(record.getLibraryLayout());
                jsonRecord.setLibrarySelection(record.getLibrarySelection());
                jsonRecord.setLibrarySource(record.getLibrarySource());
                jsonRecord.setLibraryStrategy(record.getLibraryStrategy());
                jsonRecord.setPlatform(record.getPlatform());
                if(record.getLibraryLayout().equals("2")){
                    jsonRecord.setPlannedReadLength1(record.getPlannedReadLengthMate1());
                    jsonRecord.setPlannedReadLength2(record.getPlannedReadLengthMate2());
                }else{
                    jsonRecord.setPlannedReadLength1(record.getPlannedReadLength());
                }
                jsonRecord.setReferenceAccesemblyName(record.getReferenceAccesemblyName());
                jsonRecord.setReferenceAssemblyUrl(record.getReferenceAssemblyUrl());
                jsonRecord.setReleaseTime(record.getReleaseTime());
                jsonRecord.setRunAccession(record.getRunAccession());
                jsonRecord.setTitle(record.getTitle());
                jsonRecord.setTaxonId(record.getTaxonId());
                jsonRecord.setTaxonName(record.getTaxonName());
                jsonRecord.setRunDataType(record.getRunDataType());
                if(record.getRunDataType()!=null && record.getRunDataType().equals("fastq") && record.getLibraryLayout().equals("2")){
                    if(record.getRunDataFiles().size()==2){
                        for(RunDataFile file:record.getRunDataFiles()){
                            if(file.getRunFileLabel()!=null){
                                if(file.getRunFileLabel().equals("F1")){
                                    jsonRecord.setRunFileNameF1(file.getArchivedFileName());
                                    jsonRecord.setRunFileSizeF1(file.getRunFileSize());
                                    jsonRecord.setMd5RunFileF1(file.getMd5());
                                }else if(file.getRunFileLabel().equals("R2")){
                                    jsonRecord.setRunFileNameR2(file.getArchivedFileName());
                                    jsonRecord.setRunFileSizeR2(file.getRunFileSize());
                                    jsonRecord.setMd5RunFileR2(file.getMd5());
                                }
                            }
                            else if(file.getRunFileName().contains("R1")||file.getRunFileName().contains("_1")||file.getRunFileName().contains("r1")||file.getRunFileName().contains("read1")){
                                jsonRecord.setRunFileNameF1(file.getArchivedFileName());
                                jsonRecord.setRunFileSizeF1(file.getRunFileSize());
                                jsonRecord.setMd5RunFileF1(file.getMd5());
                            }else if(file.getRunFileName().contains("R2")||file.getRunFileName().contains("_2")||file.getRunFileName().contains("r2")||file.getRunFileName().contains("read2")){
                                jsonRecord.setRunFileNameR2(file.getArchivedFileName());
                                jsonRecord.setRunFileSizeR2(file.getRunFileSize());
                                jsonRecord.setMd5RunFileR2(file.getMd5());
                            }
                        }
                    }
                }else if(record.getRunDataFiles()!=null && record.getRunDataFiles().size()>0){
                    jsonRecord.setRunFileNameF1(record.getRunDataFiles().get(0).getArchivedFileName());
                    jsonRecord.setRunFileSizeF1(record.getRunDataFiles().get(0).getRunFileSize());
                    jsonRecord.setMd5RunFileF1(record.getRunDataFiles().get(0).getMd5());
                }
                if(record.getPublications()!=null && record.getPublications().size()>0){
                    Publication publication=record.getPublications().get(0);
                    jsonRecord.setPublicationPubmedId(publication.getPubmedId());
                    jsonRecord.setPublicationArticleTitle(publication.getArticleTitle());
                    jsonRecord.setPublicationJournalTitle(publication.getJournalTitle());
                }
                jsonRecord.setSubmitterEmail(record.getSubmitterEmail());
                jsonRecord.setSubmitterAffiliation(record.getSubmitterAffiliation());
                jsonRecord.setSubmitterName(record.getSubmitterName());
                jsonrRecords.add(jsonRecord);
            }
        }
        return jsonrRecords;
    }
    @RequestMapping(value = "/releaseHuman", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String releaseHuman(@RequestBody JsonRelease jsonRelease) {
        String result="";
        int prjId = jsonRelease.getPrjId();
        List<Sample> sampleList = jsonRelease.getSampleList();
        Project pro =this.projectMapper.findProjectById(prjId);
        if(pro!=null){
            Integer releaseState = pro.getReleaseState();
            Integer status = pro.getStatus();
            if(status!=3){
                result = "project status error";
            } else {
                if(releaseState==1){
                    Project p = new Project();
                    p.setPrjId(prjId);
                    p.setReleaseTime(new Date());
                    p.setReleaseState(2);
                    this.projectMapper.releaseProject(p);
                    result = "project release success";
                } else {
                    result = "project has been released";
                    return result;
                }
            }

            if(sampleList!=null){
                for(Sample sam:sampleList){
                    Sample sample = this.sampleMapper.findSampleById(sam.getSampleId());
                    if(sample!=null){
                        if(sample.getReleaseState()==null){
                            result =sample.getSampleId()+ " release_state is null";
                            return result;
                        } else {
                            if(sample.getReleaseState()== 1) {
                                Sample s = new Sample();
                                s.setSampleId(sample.getSampleId());
                                s.setReleaseState(2);
                                this.sampleMapper.releaseSampleStatus(s);
                            }
                        }
                    }
                    result = "sample release success";
                }
            } else {
                result = "sample release error";
                return result;
            }

        } else {
            result = "project release error";
            return result;
        }
        return result;
    }
    @RequestMapping(value = "/gsaBrowse")
    public List<GsaBrowse> gsaBrowse(HttpServletResponse httpServletResponse) {
        httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");
        List<GsaBrowse> gsaBrowseList = this.gsaMapper.getGsaBrowseList();

        return gsaBrowseList;
    }
    /**
     * 获取外部数据库key
     * @param
     * @param httpServletResponse
     * @return
     */
    @RequestMapping("/getKey/{uid}/{web}")
    public ResultKey getKey(@PathVariable("uid") String uid,@PathVariable("web") String web, HttpServletResponse httpServletResponse) {
        httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");
        ResultKey resultKey = new ResultKey();
        User u = this.userMapper.selectUserByCsaId(uid);
        if(u!=null) {
            String key = this.keyMapper.getKey(web);
            resultKey.setWeb(web);
            resultKey.setKey(key);
            resultKey.setErrorM("SUCCESS");
        } else {
            resultKey.setErrorM("uid does not exist");
        }
        return resultKey;
    }
}
