package cn.ac.big.bigd.webservice.controller.gsa;

import cn.ac.big.bigd.webservice.mapper.gsa.*;
import cn.ac.big.bigd.webservice.model.gsa.*;
import cn.ac.big.bigd.webservice.utility.SpringContextUtil;
import cn.ac.big.bigd.webservice.utility.StringTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author chenxu
 */
@RestController
public class SampleController {
    @Autowired
    private SampleMapper sampleMapper;
    @Autowired
    private TaxonMapper taxonMapper;
    @Autowired
    private SampleAttrMapper<?> sampleAttrMapper;
    @Autowired
    private ProjectMapper projectMapper;
    @Autowired
    private ExperimentMapper experimentMapper;
    @Autowired
    private RunMapper runMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private KeyMapper keyMapper;
    @Autowired
    private SubmitterMapper submitterMapper;
    @Autowired
    private GsaMapper gsaMapper;
    @Autowired
    private ReviewMapper reviewMapper;
    @Autowired
    private SampleSubmissionMapper sampleSubmissionMapper;
    @Autowired
    private ReleaseMapper releaseMapper;
    @RequestMapping("/getSample/{accession}")
    public Sample getSample(@PathVariable("accession") String accession, HttpServletResponse httpServletResponse) {
        httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");
        Sample sample = sampleMapper.getSampleByAccession(accession);
        if (sample != null) {
            setAncestorAndAttribute(sample);
            setExperimentAndRun(sample);
        }
        return sample;
    }

    @GetMapping("/checkSample/{accession}/{uuid}")
    public int checkSample(@PathVariable(value = "accession") String accession,@PathVariable(value = "uuid") String uuid, HttpServletResponse httpServletResponse) {
        httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");
        Sample sam = new Sample();
        sam.setCasUserId(uuid);
        sam.setAccession(accession);
        Sample sample = sampleMapper.getSampleByUuid(sam);
        int result = 0;
        if (sample != null) {
            result=1;
        }
        return result;
    }

    @GetMapping("/checkProject/{accession}/{uuid}")
    public int checkProject(@PathVariable(value = "accession") String accession,@PathVariable(value = "uuid") String uuid, HttpServletResponse httpServletResponse) {
        httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");
        Project pro = new Project();
        pro.setCasUserId(uuid);
        pro.setAccession(accession);
        Project project = projectMapper.getProjectByUuid(pro);
        int result = 0;
        if (project != null) {
            result=1;
        }
        return result;
    }

    //用正则表达式表示这些字符不能被截断。{fileName:[a-zA-Z0-9\\.-_]+}
    @RequestMapping("/getSamples/{email:.+}")
    public List<Sample> getSamples(@PathVariable("email") String email, HttpServletResponse httpServletResponse) {
        httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");
        int userId ;
        try {
            userId = projectMapper.selectUserId(email);
        }catch (Exception e){
            return null;
        }
        List<Sample> samples = sampleMapper.listAllSamplesByUserId(userId);
        if (samples != null) {
            for (Sample sample : samples) {
                setAncestorAndAttribute(sample);
            }
        }
        return samples;
    }

    @RequestMapping("/getSamples/term={term}")
    public List<Sample> getSmaplesByTerm(@PathVariable("term") String term, HttpServletResponse httpServletResponse) {
        httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");
        List<Sample> samples = new ArrayList<>();
        if (term.trim().startsWith("SAMC") || term.trim().startsWith("samc")) {
            Sample sample = sampleMapper.getSampleByAccession(term);
            if (sample != null) {
                setAncestorAndAttribute(sample);
                samples.add(sample);
            }
        } else {
            samples = this.sampleMapper.getSamplesByTerm(term.trim());
            if (samples != null) {
                for (Sample sample : samples) {
                    setAncestorAndAttribute(sample);
                }
            }
        }
        return samples;
    }

    @RequestMapping("/getSamplesTerm/term={term}")
    public List<Sample> getSamplesByTerm1(@PathVariable("term") String term, HttpServletResponse httpServletResponse) {
        httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");
        List<Integer> listLeafId = new ArrayList();
        List<Sample> samples = new ArrayList<>();
        List<Integer> listTermId = this.taxonMapper.listTaxonIdsByScientificName(term.trim());
        if (listTermId != null && !listTermId.isEmpty()) {
            getLeafNode(listTermId, listLeafId);
        }
        if (!listLeafId.isEmpty()) {
            for (int id : listLeafId) {
                List<Sample> sams = this.sampleMapper.getSamplesByTaxId(id);
                if (sams != null) {
                    samples.addAll(sams);
                }
            }
        }
        return samples;
    }

    /**
     * 根据taxId判断物种是否为冠状病毒(Coronavirus)
     * @param taxId
     * @param httpServletResponse
     * @return
     */
    @RequestMapping("/taxon/isCoronavirus/{taxId}")
    public boolean isCoronavirus(@PathVariable("taxId") int taxId, HttpServletResponse httpServletResponse) {
        httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");
//        Taxon2 genus = null;
        Taxon2 genus =  this.taxonMapper.getGenusById(taxId);
        if(genus!=null){
            int gTaxId = genus.getTaxonId();
            if(gTaxId==693996 || gTaxId==694002 || gTaxId==694013)
                return true;
            else return false;
        }
        return false;
    }

    /**
     * 根据标签查找sample列表
     * @param tag
     * @param httpServletResponse
     * @return
     */
    @RequestMapping("/getSamplesByTag/tag={tag}")
    public List<Sample> getSamplesByTag(@PathVariable("tag") String tag, HttpServletResponse httpServletResponse) {
        httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");
        List<Sample> samples = this.sampleMapper.getSamplesByTag(tag);
        if(samples!=null && samples.size()>0){
            for (Sample sample : samples) {
                setAncestor(sample);
            }
        }
        return samples;
    }

    private void getLeafNode(List<Integer> listId, List<Integer> listLeaf) {
        for (int id : listId) {
            List<Integer> listParentId = this.taxonMapper.getTaxonIdByParentId(id);
            if (listParentId != null && !listParentId.isEmpty()) {
                getLeafNode(listParentId, listLeaf);
            } else {
                listLeaf.add(id);
            }
        }
    }
    //用正则表达式表示这些字符不能被截断。{fileName:[a-zA-Z0-9\\.-_]+}
    @RequestMapping("/getProjects/{email:.+}")
    public List<Project> getProjectsByUserId(@PathVariable("email") String email, HttpServletResponse httpServletResponse) {
        httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");
        int userId ;
        try {
            userId = projectMapper.selectUserId(email);
        }catch (Exception e){
            return null;
        }
        return this.projectMapper.listProjectsByUserId(userId);
    }

    @RequestMapping("/getSamplesByPrjAcc/{accession}")
    public List<Sample> getSamplesByPrjAcc(@PathVariable("accession") String accession, HttpServletResponse httpServletResponse) {
        httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");
        return this.sampleMapper.listSamplesByPrjAcc(accession);
    }

    @RequestMapping("/updateReleaseState/{sampleId}")
    public int updateReleaseState(@PathVariable("sampleId") int sampleId, HttpServletResponse httpServletResponse) {
        httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");
        try {
            this.sampleMapper.updateReleaseStateById(sampleId);
            int prjId = this.sampleMapper.getPrjIdBySampleId(sampleId);
            this.projectMapper.updateReleaseStateById(prjId);
            return 1;
        } catch (Exception e) {
            return 0;
        }
    }

    private SampleAttrMapper<?> getAttributeMapper(String attributeType) {
        return  (SampleAttrMapper<?>) SpringContextUtil.getBean(attributeType + "Mapper");
    }

    /**
     * 为样本设置lineage
     * @param sample
     */
    private void setAncestor(Sample sample) {
        Taxon taxon = this.taxonMapper.selectTaxonById(sample.getTaxonId());
        if(taxon!=null){
            List<Taxon2> ancestors = this.taxonMapper.selectAncestorsById(sample.getTaxonId());
            taxon.setAncestors(ancestors);
        }
        sample.setTaxon(taxon);
    }
    private void setAncestorAndAttribute(Sample sample) {
        Taxon taxon = sample.getTaxon();
        List<Taxon2> ancestors = this.taxonMapper.selectAncestorsById(taxon.getTaxonId());
        taxon.setAncestors(ancestors);
        String sampleAttr = StringTools.joinString(sample.getSampleType().getAttributeTable());
        sampleAttrMapper = getAttributeMapper(sampleAttr);
        Object attribute = this.sampleAttrMapper.findBySampleId(sample.getSampleId());
        sample.setSampleAttribute(attribute);
    }
    private void setExperimentAndRun(Sample sample) {
        int sample_id = sample.getSampleId();
        List<Experiment> expList = this.experimentMapper.selectExperimentBySampleId(sample_id);
        if(expList.size()>0) {
            for(Experiment exp:expList){
                int exp_id = exp.getExpId();
                List<Run> runList = this.runMapper.selectRunByExpId(exp_id);
                if(runList.size()>0) {
                    exp.setRuns(runList);
                }
            }
            sample.setExperimentList(expList);
        }
    }
    @RequestMapping(value = "/synchronizationSample", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String synchronizationSample(@RequestBody JsonSample jsonSample) {
        String result = "";
        User user = jsonSample.getUser();
        String email = user.getEmail();
        User newUser = this.userMapper.selectUserByEmail(email);
        int userId =0;
        Submitter sub = new Submitter();
        if(newUser==null){
            int insertU = this.userMapper.insertHumanUser(user);
            userId = user.getUserId();
            sub.setCountry(user.getCountry());
            sub.setFirstName(user.getFirstName());
            sub.setMiddleName(user.getMiddleName());
            sub.setLastName(user.getLastName());
            sub.setEmail(user.getEmail());
            sub.setOrganization(user.getOrganization());
            sub.setDepartment(user.getDepartment());
            sub.setPhone(user.getPhone());
            sub.setFax(user.getFax());
            sub.setStreet(user.getStreet());
            sub.setState(user.getState());
            sub.setPostalCode(user.getPostalCode());
        } else {
            userId = newUser.getUserId();
            sub.setCountry(newUser.getCountry());
            sub.setFirstName(newUser.getFirstName());
            sub.setMiddleName(newUser.getMiddleName());
            sub.setLastName(newUser.getLastName());
            sub.setEmail(newUser.getEmail());
            sub.setOrganization(newUser.getOrganization());
            sub.setDepartment(newUser.getDepartment());
            sub.setPhone(newUser.getPhone());
            sub.setFax(newUser.getFax());
            sub.setStreet(newUser.getStreet());
            sub.setState(newUser.getState());
            sub.setPostalCode(newUser.getPostalCode());
        }
        int submitter = this.submitterMapper.insertHumanSubmitter(sub);
        int submitterId = sub.getSubmitterId();
        return "1";
    }

    @RequestMapping("/getCra/{accession}")
    public List<Cra> getCra(@PathVariable("accession") String accession, HttpServletResponse httpServletResponse) {
        List<Cra> craList = new ArrayList<>() ;
        Sample sample = this.sampleMapper.getSampleIdByAccession(accession);
        if(sample!=null){
            int sampleId = sample.getSampleId();
            craList = this.gsaMapper.getCraListBySampleId(sampleId);
            if(craList!=null){
                for(Cra cra: craList){
                    Map<String,Integer> params = new HashMap<String,Integer>();
                    params.put("sampleId",sampleId);
                    params.put("craId",cra.getCraId());
                    List<Experiment> experimentList = this.experimentMapper.selectExperimentBySampleIdAndCraId(params);
                    if(experimentList!=null){
                        for(Experiment experiment:experimentList){
                            List<Run> runs = this.runMapper.selectRunUrlByExpId(experiment.getExpId());
                            if(runs!=null){
                                experiment.setRuns(runs);
                            }
                        }
                        cra.setExperimentList(experimentList);
                    }
                }
            }
        }
        return craList;
    }

    /**
     * 根据国家查询GSA数据库中新冠样本数量
     * @param countryName
     * @param httpServletResponse
     * @return
     */
    @RequestMapping("/getSampleCountsByCountryName/{countryName}")
    public SampleMonthCounts isTaxonomy(@PathVariable("countryName") String countryName, HttpServletResponse httpServletResponse) throws ParseException {
        httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");
        TreeMap<String,Integer> mouthMap = this.getMounth();
        List<SampleCounts> sampleCounts1 = this.sampleMapper.getSampleCountsType1(countryName);
        List<SampleCounts> sampleCounts7 = this.sampleMapper.getSampleCountsType7(countryName);
        List<SampleCounts> sampleCounts8 = this.sampleMapper.getSampleCountsType8(countryName);
        TreeMap<String,Integer> map1 = new TreeMap();
        TreeMap<String,Integer> map7 = new TreeMap();
        TreeMap<String,Integer> map8 = new TreeMap();
        if(sampleCounts1.size()>0){
            map1 = getMap(sampleCounts1);
        }
        if(sampleCounts7.size()>0){
            map7 = getMap(sampleCounts7);
        }
        if(sampleCounts8.size()>0){
            map8 = getMap(sampleCounts8);
        }
        for(Map.Entry<String,Integer> entry:mouthMap.entrySet()){
            String key = entry.getKey();
            int counts = 0;
            int count1 = 0;
            int count7 = 0;
            int count8 = 0;
            if(map1!=null){
                if(map1.containsKey(key)){
                    count1 = map1.get(key);
                }
            }
            if(map7!=null){
                if(map7.containsKey(key)){
                    count7 = map7.get(key);
                }
            }
            if(map8!=null){
                if(map8.containsKey(key)){
                    count8 = map8.get(key);
                }
            }
            counts = count1 + count7 + count8;
            entry.setValue(counts);
        }
        SampleMonthCounts sampleMonthCounts = new SampleMonthCounts();
        sampleMonthCounts.setMonthCounts(mouthMap);
        return sampleMonthCounts;
    }
    /**
     * 根据国家查询GSA数据库中新冠样本数量
     * @param countryName
     * @param httpServletResponse
     * @return
     */
    @RequestMapping("/getSampleCountsByCountryNameAll/{countryName}")
    public SampleMonthCounts isTaxonomyAll(@PathVariable("countryName") String countryName, HttpServletResponse httpServletResponse) throws ParseException {
        httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");
        TreeMap<String,Integer> mouthMap = this.getMounth();
        List<SampleCounts> sampleCounts1 = this.sampleMapper.getSampleCountsTypeAll1(countryName);
        List<SampleCounts> sampleCounts7 = this.sampleMapper.getSampleCountsTypeAll7(countryName);
        List<SampleCounts> sampleCounts8 = this.sampleMapper.getSampleCountsTypeAll8(countryName);
        TreeMap<String,Integer> map1 = new TreeMap();
        TreeMap<String,Integer> map7 = new TreeMap();
        TreeMap<String,Integer> map8 = new TreeMap();
        if(sampleCounts1.size()>0){
            map1 = getMap(sampleCounts1);
        }
        if(sampleCounts7.size()>0){
            map7 = getMap(sampleCounts7);
        }
        if(sampleCounts8.size()>0){
            map8 = getMap(sampleCounts8);
        }
        for(Map.Entry<String,Integer> entry:mouthMap.entrySet()){
            String key = entry.getKey();
            int counts = 0;
            int count1 = 0;
            int count7 = 0;
            int count8 = 0;
            if(map1!=null){
                if(map1.containsKey(key)){
                    count1 = map1.get(key);
                }
            }
            if(map7!=null){
                if(map7.containsKey(key)){
                    count7 = map7.get(key);
                }
            }
            if(map8!=null){
                if(map8.containsKey(key)){
                    count8 = map8.get(key);
                }
            }
            counts = count1 + count7 + count8;
            entry.setValue(counts);
        }
        SampleMonthCounts sampleMonthCounts = new SampleMonthCounts();
        sampleMonthCounts.setMonthCounts(mouthMap);
        return sampleMonthCounts;
    }
    public TreeMap getMap(List<SampleCounts> samples){
        TreeMap map = new TreeMap();
        for(SampleCounts sampleCounts:samples){
            map.put(sampleCounts.getMonth(),sampleCounts.getCounts());
        }
        return map;
    }
    public TreeMap getMounth() throws ParseException {
        TreeMap<String,Integer> mouthMap = new TreeMap();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        Date begin = sdf.parse("2019-12");
        Date end = new Date();
        Calendar min = Calendar.getInstance();
        Calendar max = Calendar.getInstance();

        min.setTime(begin);
        min.set(min.get(Calendar.YEAR), min.get(Calendar.MONTH), 1);

        max.setTime(end);
        max.set(max.get(Calendar.YEAR), max.get(Calendar.MONTH), 2);

        Calendar curr = min;
        while (curr.before(max)) {
            mouthMap.put(sdf.format(curr.getTime()),0);
            curr.add(Calendar.MONTH, 1);
        }
        return mouthMap;
    }

    private List<Date> getBetweenDates(Date begin, Date end) {
        List<Date> result = new ArrayList<Date>();
        Calendar tempStart = Calendar.getInstance();
        tempStart.setTime(begin);
            /* Calendar tempEnd = Calendar.getInstance();
            tempStart.add(Calendar.DAY_OF_YEAR, 1);
            tempEnd.setTime(end);
            while (tempStart.before(tempEnd)) {
                result.add(tempStart.getTime());
                tempStart.add(Calendar.DAY_OF_YEAR, 1);
            }*/
        while(begin.getTime()<=end.getTime()){
            result.add(tempStart.getTime());
            tempStart.add(Calendar.DAY_OF_YEAR, 1);
            begin = tempStart.getTime();
        }
        return result;
    }

    /**
     * 根据物种字符串判断是否存在错误物种，以逗号隔开
     * @param taxon_txt
     * @param httpServletResponse
     * @return
     */
    @RequestMapping("/taxon/isTaxon/{taxon_txt}")
    public String isTaxon(@PathVariable("taxon_txt") String taxon_txt, HttpServletResponse httpServletResponse) {
        httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");
        String[] splitTaxon = taxon_txt.split(",");
        String result= "";

        for(String str:splitTaxon){
           int count =  this.sampleMapper.getTaxonCount(str);
           if(count==0){
               if(result!=""){
                   result = result +","+str;
               } else {
                   result = str;
               }
           }
        }
        if(result==""){
            result = "suss";
        }
        return result;
    }

    /**
     * 根据物种名称查询物种id
     * @param taxon_txt
     * @param httpServletResponse
     * @return
     */
    @RequestMapping("/taxon/getTaxonId/{taxon_txt}")
    public String getTaxonId(@PathVariable("taxon_txt") String taxon_txt, HttpServletResponse httpServletResponse) {
        httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");
        String result= this.sampleMapper.getTaxonId(taxon_txt)+"";
        return result;
    }
    @RequestMapping("/getProject/{accession}")
    public Project getProjectByAccession(@PathVariable("accession") String accession, HttpServletResponse httpServletResponse) {
        httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");
        return this.projectMapper.findProjectByAccession(accession);
    }
    /**
     * 根据物种名称查询物种树
     * @param taxName
     * @param httpServletResponse
     * @return
     */
    @RequestMapping("/getTaxonNucBank/{sci_name}")
    public TaxonNucBank getTaxonNucBank(@PathVariable("sci_name") String taxName, HttpServletResponse httpServletResponse) {
        httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");
        int taxId= this.sampleMapper.getTaxonId(taxName);
        List<Taxon3> ancestors = this.taxonMapper.selectAncestors3ById(taxId);
        Taxon3 taxon3 = this.taxonMapper.selectTaxon3ById(taxId);
        ancestors.add(taxon3);
        Collections.reverse(ancestors);
        TaxonNucBank taxonNucBank = new TaxonNucBank();
        taxonNucBank.setLinage(ancestors);
        return taxonNucBank;
    }

    /**
     * 其他数据库审核sample接口
     * @param
     * @param httpServletResponse
     * @return
     */
    @RequestMapping("/reviewSample/{sampleAccession}/{webserver}/{uid}/{keyValue}")
    public Result reviewSample(@PathVariable("sampleAccession") String sampleAccession,@PathVariable("webserver") String webserver,@PathVariable("uid") String uid,@PathVariable("keyValue") String keyValue, HttpServletResponse httpServletResponse) {
        System.out.println("sampleAccession: "+sampleAccession);
        System.out.println("uid: "+uid);
        System.out.println("web: "+webserver);
        System.out.println("keyValue: "+keyValue);
        Result result = new Result();
        if(!sampleAccession.contains("SAMC")) {
            result.setErrorM("样本编号错误");
            result.setStatus("Fail");
        } else {
            User u = userMapper.selectUserByCsaId(uid);
            if(u!=null) {
                if(keyValue!=null&&!"".equals(keyValue)) {
                    String strKey =keyMapper.getKey(webserver);
                    if(strKey==null){
                        result.setErrorM("数据库参数错误");
                        result.setStatus("Fail");
                    } else {
                        if(keyValue.equals(strKey)) {
                            int user_id = u.getUserId();
                            int status = 1;
                            String database = webserver;
                            Sample sample = sampleMapper.getSampleByAccession(sampleAccession);
                            int samId = sample.getSampleId();
                            int prjId = sample.getPrjId();
                            int samStatus = sample.getStatus();
                            if(samStatus==5) {
                                result.setErrorM("样本已删除");
                                result.setStatus("Fail");
                            } else if(samStatus!=3)  {
                                SampleReview newS = new SampleReview();
                                newS.setStatus(3);
                                newS.setReleaseState(1);
                                newS.setModifyTime(new Date());
                                newS.setSampleId(samId);
                                this.sampleMapper.reviewSample(newS);
                                String subId =  this.sampleSubmissionMapper.selectSubmissionIdBySampleId(samId);
                                SampleSubmission sampleSubmission =this.sampleSubmissionMapper.selectSubmissionBySubId(subId);
                                String st = sampleSubmission.getsStatus();
                                int ss = sampleSubmission.getStatus();
                                if(ss!=3){
                                    if(st.equals("3")){
                                        int subm = this.sampleSubmissionMapper.updateSubmissionBySubId(subId);
                                    }
                                }
                                ReviewWeb reviewWeb = new ReviewWeb();
                                reviewWeb.setUser_id(user_id);
                                reviewWeb.setPrj_id(prjId);
                                reviewWeb.setSample_id(samId);
                                reviewWeb.setStatus(status);
                                reviewWeb.setWebserver(database);
                                reviewWeb.setOption_date(new Date());
                                this.reviewMapper.insertReview(reviewWeb);
                                result.setStatus("Success");
                            } else {
                                result.setErrorM("已经审核过");
                                result.setStatus("Success");
                            }

                        } else {
                            result.setErrorM("Key错误");
                            result.setStatus("Fail");
                        }
                    }
                } else {
                    result.setErrorM("key为空");
                    result.setStatus("Fail");
                }
            }else {
                result.setErrorM("uid错误");
                result.setStatus("Fail");
            }
        }
        return result;
    }

    /**
     * 其他数据库发布sample接口
     * @param
     * @param httpServletResponse
     * @return
     */
    @RequestMapping("/releaseSample/{sampleAccession}/{webserver}/{uid}/{keyValue}")
    public Result releaseSample(@PathVariable("sampleAccession") String sampleAccession,@PathVariable("webserver") String webserver,@PathVariable("uid") String uid,@PathVariable("keyValue") String keyValue, HttpServletResponse httpServletResponse) {
        System.out.println("sampleAccession: "+sampleAccession);
        System.out.println("uid: "+uid);
        System.out.println("web: "+webserver);
        System.out.println("keyValue: "+keyValue);
        Result result = new Result();
        if(!sampleAccession.contains("SAMC")) {
            result.setErrorM("样本编号错误");
            result.setStatus("Fail");
        } else {
            User u = userMapper.selectUserByCsaId(uid);
            if(u!=null) {
                if(keyValue!=null&&!"".equals(keyValue)) {
                    String strKey =keyMapper.getKey(webserver);
                    if(strKey==null){
                        result.setErrorM("数据库参数错误");
                        result.setStatus("Fail");
                    } else {
                        if(keyValue.equals(strKey)) {
                            int user_id = u.getUserId();
                            int status = 1;
                            String database = webserver;
                            Sample sample = sampleMapper.getSampleByAccession(sampleAccession);
                            int samId = sample.getSampleId();
                            int prjId = sample.getPrjId();
                            int samStatus = sample.getStatus();
                            int samState = sample.getReleaseState();
                            Project project = this.projectMapper.findProjectById(prjId);
                            int prjState = project.getReleaseState();
                            int prjStatus = project.getStatus();
                            String msg="";
                            if(prjStatus!=5&&prjState != 2){
                                Project newP = new Project();
                                newP.setPrjId(prjId);
                                newP.setStatus(3);
                                newP.setReleaseTime(new Date());
                                newP.setReleaseState(2);
                                this.projectMapper.releaseProjectWeb(newP);
                            } else {
                                status = 2;
                                msg = "项目已经发布";
                            }
                            if(samStatus!=5&&samState!=2) {
                                Sample newS = new Sample();
                                newS.setStatus(3);
                                newS.setReleaseState(2);
                                newS.setSampleId(samId);
                                this.sampleMapper.releaseSampleStatus(newS);
                                result.setStatus("Success");
                            } else {
                                status = 3;
                                result.setStatus("Success");
                                msg = msg + " 样本已经发布";
                                result.setErrorM(msg);
                            }
                            ReleaseWeb releaseWeb = new ReleaseWeb();
                            releaseWeb.setUser_id(user_id);
                            releaseWeb.setPrj_id(prjId);
                            releaseWeb.setSample_id(samId);
                            releaseWeb.setStatus(status);
                            releaseWeb.setWebserver(database);
                            releaseWeb.setOption_date(new Date());
                            this.releaseMapper.insertRelease(releaseWeb);
                        } else {
                            result.setErrorM("Key错误");
                            result.setStatus("Fail");
                        }
                    }
                } else {
                    result.setErrorM("key为空");
                    result.setStatus("Fail");
                }
            }else {
                result.setErrorM("uid错误");
                result.setStatus("Fail");
            }
        }
        return result;
    }
}
