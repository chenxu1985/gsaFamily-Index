package cn.ac.big.bigd.webservice.controller.insdc;

import cn.ac.big.bigd.webservice.mapper.gsa.GsaIndexMapper;
import cn.ac.big.bigd.webservice.model.index.*;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * author chenxu
 */
@RestController
public class sraController {

    //基础experiment导出txt
//    select 'INSDC' as center,'Experiment' as type,e.second_accession,e.title,e.lib_name as libraryName,e.lib_design as libDesign,
//            (case when e.lib_layout=1 then 'Single' when e.lib_layout=2 then 'Paired' end) as libLayout,eptn.type_name as platform_name,ep.platform_name as platformDetail,els.source_name as source,
//    est.strategy_name as strategyDetail,elstn.type_name as strategy,ese.selection_name as selection,e.release_time,e.submission_second_accession,e.study_second_accession,e.sample_third_accession
//    ,CONCAT("https://ngdc.cncb.ac.cn/gsa/browse/insdc/",e.submission_second_accession,"/",e.second_accession) as url,e.submittedBy as organization,
//    e.lib_nominal_size as libNominalSize,e.lib_nominal_standard_deviation as libNominalStandardDeviation
//    from experiment e ,exp_platform_ncbi ep,exp_lib_source_ncbi els,exp_lib_strategy_ncbi est,exp_lib_selection_ncbi ese,exp_platform_type_ncbi eptn,exp_lib_strategy_type_ncbi elstn
//    where e.platform_id = ep.platform_id and e.source_id=els.source_id and e.strategy_id=est.strategy_id and e.selection_id=ese.selection_id and eptn.type_id=ep.platform_type and elstn.type_id=est.strategy_type_id
//      limit 0,4500000

    @Autowired
    private GsaIndexMapper gsaIndexMapper;

    @RequestMapping("/getSraIndex")
    public void getSraIndex() throws IOException {
        String index = "gsaTest";
        String title = "GSA";
        String url = "https://ngdc.cncb.ac.cn/gsa";
        String desc = "Genome Sequence Archive";
        String basicInfo = "The Genome Sequence Archive (GSA) is a data repository for collecting, archiving, managing and sharing raw sequence data, which is the first repository of the genome sequence data with international journal recognition in China.";
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String today = format.format(new Date());
        Database database = new Database();
        database.setIndex(index);
        database.setTitle(title);
        database.setUrl(url);
        database.setDescription(desc);
        database.setBasicInfo(basicInfo);
        database.setUpdateTime(today);
        File file = new File("/data/gsagroup/index/insdc.bs");
        if(file.exists()){
            file.delete();
        }
        FileOutputStream fos;
        String code = "ngdcgsa2023";
        System.out.println("INSDC 索引开始生成 "+new Date());
        System.out.println("开始加载Project信息 "+new Date());
        //        select s.second_accession as studyAcc,prj_second_accession as proAcc,p.title as projectTitle,s.title as studyTitle from study s,project p
//        where s.prj_second_accession is not null and s.prj_second_accession!="" and s.prj_second_accession=p.second_accession
        //project.txt
        String projectPath = "/data/gsagroup/index/project.txt";
        File projectFile = new File(projectPath);
        InputStreamReader projectRead = null;//考虑到编码格式
        projectRead = new InputStreamReader(new FileInputStream(projectFile),"GBK");
        BufferedReader projectReader = new BufferedReader(projectRead);
        String projectTxt = null;
        HashMap projectMap = new HashMap();
        while((projectTxt = projectReader.readLine()) != null){
            String str =  projectTxt;
            String studyAcc = str.split("\t")[0];
            String proAcc = str.split("\t")[1];
            projectMap.put(studyAcc,proAcc);
        }
        projectRead.close();
        System.out.println("Project信息加载完毕 "+new Date());

        System.out.println("可下载实验编号加载开始 "+new Date());
        //select experiment_second_accession from run where is_local=1 group by experiment_second_accession
        String downloadPath = "/data/gsagroup/index/download.txt";
        File downloadFile = new File(downloadPath);
        InputStreamReader downloadRead = null;//考虑到编码格式
        downloadRead = new InputStreamReader(new FileInputStream(downloadFile),"GBK");
        BufferedReader downloadReader = new BufferedReader(downloadRead);
        String downloadTxt = null;
        HashMap downloadMap = new HashMap();
        while((downloadTxt = downloadReader.readLine()) != null){
            String str =  downloadTxt;
            downloadMap.put(str,str);
        }
        downloadRead.close();
        System.out.println("可下载实验编号加载完毕 "+new Date());

        System.out.println("sample编号转换加载开始 "+new Date());
        String sampleAccPath = "/data/gsagroup/index/sample_second_third.txt";
        File sampleAccFile = new File(sampleAccPath);
        InputStreamReader sampleAccRead = null;//考虑到编码格式
        sampleAccRead = new InputStreamReader(new FileInputStream(sampleAccFile),"GBK");
        BufferedReader sampleAccReader = new BufferedReader(sampleAccRead);
        String sampleAccTxt = null;
        HashMap sampleAccMap = new HashMap();
        while((sampleAccTxt = sampleAccReader.readLine()) != null){
            String str =  sampleAccTxt;
            String third = str.split("\t")[2];
            String second = str.split("\t")[1];
            sampleAccMap.put(third,second);
        }
        sampleAccRead.close();
        System.out.println("sample编号转换加载完毕 "+new Date());

        System.out.println("sample信息加载开始 "+new Date());
        //select second_accession,name,title,public_description,model,taxon_id,taxon_name from sample limit 0,5000000
        //由于存在\t的情况选用解析xml
        HashMap sampleMapNew = new HashMap();
        String samplePath1 = "/data/gsagroup/index/sample1.xml";
        String samplePath2 = "/data/gsagroup/index/sample2.xml";
        String samplePath3 = "/data/gsagroup/index/sample3.xml";
        String samplePath4 = "/data/gsagroup/index/sample4.xml";
        String samplePath5 = "/data/gsagroup/index/sample5.xml";
        String samplePath6 = "/data/gsagroup/index/sample6.xml";
        String samplePath7 = "/data/gsagroup/index/sample7.xml";
        String samplePath8 = "/data/gsagroup/index/sample8.xml";

        HashMap sampleMap1 = sampleXml(samplePath1,sampleMapNew,code);
        HashMap sampleMap2 = sampleXml(samplePath2,sampleMap1,code);
        HashMap sampleMap3 = sampleXml(samplePath3,sampleMap2,code);
        HashMap sampleMap4 = sampleXml(samplePath4,sampleMap3,code);
        HashMap sampleMap5 = sampleXml(samplePath5,sampleMap4,code);
        HashMap sampleMap6 = sampleXml(samplePath6,sampleMap5,code);
        HashMap sampleMap7 = sampleXml(samplePath7,sampleMap6,code);
        HashMap sampleMap = sampleXml(samplePath8,sampleMap7,code);
        System.out.println(sampleMap.size());
        System.out.println("sample信息加载完毕 "+new Date());
        System.out.println("rdf加载开始 "+new Date());
//        select rdf.run_second_accession,GROUP_CONCAT(DISTINCT rdf.run_file_name) as fileName,GROUP_CONCAT(DISTINCT rdf.run_file_size) as fileSize,
//        GROUP_CONCAT((case when rdf.`storage`="gsapub2" then CONCAT("ftp://download2.cncb.ac.cn/INSDC",rdf.file_path)
//        when rdf.`storage`="gsainsdc2" then CONCAT("ftp://download2.cncb.ac.cn/INSDC2",rdf.file_path) when rdf.`storage`="gsainsdc3" then CONCAT("ftp://download2.cncb.ac.cn/INSDC3",rdf.file_path) end)) as downPath
//        from run_data_file rdf
//        group by rdf.run_second_accession
        String rdfPath = "/data/gsagroup/index/rdf.txt";
        File rdfFile = new File(rdfPath);
        InputStreamReader rdfRead = null;//考虑到编码格式
        rdfRead = new InputStreamReader(new FileInputStream(rdfFile),"GBK");
        BufferedReader rdfReader = new BufferedReader(rdfRead);
        String rdfTxt = null;
        HashMap rdfMap = new HashMap();
        while((rdfTxt = rdfReader.readLine()) != null){
            String str =  rdfTxt;
            String acc = str.split("\t")[0];
            String fileName = str.split("\t")[1];
            String fileSize = str.split("\t")[2];
            String downPath = str.split("\t")[3];
            String con = fileName+code+fileSize+code+downPath;
            rdfMap.put(acc,con);
        }
        rdfRead.close();
        System.out.println("rdf加载完毕 "+new Date());
        System.out.println("experiment索引生成开始 "+new Date());
        try {
            fos = new FileOutputStream(file);
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(fos));
            writer.write("DB");
            writer.write("\t");
            writer.write(JSON.toJSONString(database).replaceAll("\n", ""));
            writer.write("\n");

            //由于内容格式问题txt并不适用，选择xml
            String expPath1 = "/data/gsagroup/index/exp1.xml";
            String expPath2 = "/data/gsagroup/index/exp2.xml";
            String expPath3 = "/data/gsagroup/index/exp3.xml";
            String expPath4 = "/data/gsagroup/index/exp4.xml";
            String expPath5 = "/data/gsagroup/index/exp5.xml";
            String expPath6 = "/data/gsagroup/index/exp6.xml";
            String expPath7 = "/data/gsagroup/index/exp7.xml";
            int expCountNew = 0;
            int expCount1 = expXml(expPath1,downloadMap,sampleAccMap,sampleMap,expCountNew,writer,code,projectMap);
            int expCount2 = expXml(expPath2,downloadMap,sampleAccMap,sampleMap,expCount1,writer,code,projectMap);
            int expCount3 = expXml(expPath3,downloadMap,sampleAccMap,sampleMap,expCount2,writer,code,projectMap);
            int expCount4 = expXml(expPath4,downloadMap,sampleAccMap,sampleMap,expCount3,writer,code,projectMap);
            int expCount5 = expXml(expPath5,downloadMap,sampleAccMap,sampleMap,expCount4,writer,code,projectMap);
            int expCount6 = expXml(expPath6,downloadMap,sampleAccMap,sampleMap,expCount5,writer,code,projectMap);
            int expCount = expXml(expPath7,downloadMap,sampleAccMap,sampleMap,expCount6,writer,code,projectMap);
            System.out.println("experiment生成索引数量："+expCount);
            System.out.println("experiment索引生成完毕 "+new Date());
            System.out.println("Run索引生成开始："+expCount);
//            select 'Run' as type,second_accession as runAcc,experiment_second_accession as expAcc
//             ,CONCAT("https://ngdc.cncb.ac.cn/gsa/browse/insdc/",submission_second_accession,"/",second_accession) as url
//            from run
            String runPath = "/data/gsagroup/index/run.txt";
            File runFile = new File(runPath);
            InputStreamReader runRead = null;//考虑到编码格式
            runRead = new InputStreamReader(new FileInputStream(runFile),"GBK");
            BufferedReader runReader = new BufferedReader(runRead);
            String runTxt = null;
            int runCount = 0;
            Run run = new Run();
            while((runTxt = runReader.readLine()) != null){
                String str =  runTxt;
                writer.write("Run");
                writer.write("\t");
                String runType = str.split("\t")[0];
                String runAcc = str.split("\t")[1];
                String expAcc = str.split("\t")[2];
                String runUrl = str.split("\t")[3];
                String fName = "";
                String fSize = "";
                String dPath = "";
                if(rdfMap.containsKey(runAcc)){
                    String con = (String) rdfMap.get(runAcc);
                    fName = con.split(code)[0];
                    fSize = con.split(code)[1];
                    dPath = con.split(code)[2];
                }
                run.setRunAcc(runAcc);
                run.setType(runType);
                run.setExpAcc(expAcc);
                run.setDownloadPath(dPath);
                run.setUrl(runUrl);
                run.setFileName(fName);
                run.setFileSize(fSize);
                writer.write(JSON.toJSONString(run).replaceAll("\n", ""));
                writer.write("\n");
                runCount++;
            }
            System.out.println("Run 生成索引数量："+runCount);
            System.out.println("Run索引生成完毕 "+new Date());
            writer.flush();
            writer.close();
            System.out.println("Insdc 索引生成完毕 "+new Date());
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public HashMap sampleXml(String samplePath,HashMap sampleMap,String code){
        File sampleFile = new File(samplePath);
        DocumentBuilderFactory xmlFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder xmlBuilder = null;
        try {
            xmlBuilder = xmlFactory.newDocumentBuilder();
            Document xmlDoc = xmlBuilder.parse(sampleFile);
            NodeList sample = xmlDoc.getElementsByTagName("RECORD");
            for (int i = 0; i < sample.getLength(); i++) {
                Element packE = (Element) sample.item(i);
                NodeList accN = packE.getElementsByTagName("second_accession");
                Element accE = (Element) accN.item(0);
                NodeList nameN = packE.getElementsByTagName("name");
                Element nameE = (Element) nameN.item(0);
                NodeList sampleTitleN = packE.getElementsByTagName("title");
                Element sampleTitleE = (Element) sampleTitleN.item(0);
                NodeList sampleDescN = packE.getElementsByTagName("public_description");
                Element sampleDescE = (Element) sampleDescN.item(0);
                NodeList typeN = packE.getElementsByTagName("model");
                Element typeE = (Element) typeN.item(0);
                NodeList idN = packE.getElementsByTagName("taxon_id");
                Element idE = (Element) idN.item(0);
                NodeList orgN = packE.getElementsByTagName("taxon_name");
                Element orgE = (Element) orgN.item(0);
                String acc =accE.getFirstChild().getNodeValue();
                String name = "";
                if (nameE.getFirstChild() != null) {
                    name =nameE.getFirstChild().getNodeValue()  ;
                }
                String sampleTitle = "";
                if (sampleTitleE.getFirstChild() != null) {
                    sampleTitle =sampleTitleE.getFirstChild().getNodeValue();
                }
                String sampleDesc = "";
                if (sampleDescE.getFirstChild() != null) {
                    sampleDesc =sampleDescE.getFirstChild().getNodeValue();
                }
                String type = "";
                if (typeE.getFirstChild() != null) {
                    type =typeE.getFirstChild().getNodeValue();
                }
                String id =idE.getFirstChild().getNodeValue();
                String org =orgE.getFirstChild().getNodeValue();
                String content = name+code+sampleTitle+code+sampleDesc+code+type+code+id+code+org;
                sampleMap.put(acc,content);
            }
        }catch(Exception e){
            System.out.println(sampleFile+":加载异常");
            e.printStackTrace();
        }
        xmlBuilder.reset();
        return sampleMap;
    }

    public int expXml(String expPath,HashMap downloadMap,HashMap sampleAccMap,HashMap sampleMap,int expCount,BufferedWriter writer,String code,HashMap projectMap){
        System.out.println(expPath+"开始生成");
        File expFile = new File(expPath);
        DocumentBuilderFactory xmlFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder xmlBuilder = null;
        try {
            xmlBuilder = xmlFactory.newDocumentBuilder();
            Document xmlDoc = xmlBuilder.parse(expFile);
            NodeList exp = xmlDoc.getElementsByTagName("RECORD");
            Experiment expObj = new Experiment();
            for (int i = 0; i < exp.getLength(); i++) {
                Element packE = (Element) exp.item(i);
                //中心
                NodeList centerN = packE.getElementsByTagName("center");
                Element centerE = (Element) centerN.item(0);
                //数据类型
                NodeList typeN = packE.getElementsByTagName("type");
                Element typeE = (Element) typeN.item(0);
                //实验编号
                NodeList accN = packE.getElementsByTagName("second_accession");
                Element accE = (Element) accN.item(0);
                //实验标题
                NodeList titleN = packE.getElementsByTagName("title");
                Element titleE = (Element) titleN.item(0);
                //建库名称
                NodeList libraryNameN = packE.getElementsByTagName("libraryName");
                Element libraryNameE = (Element) libraryNameN.item(0);
                //实验设计
                NodeList libDesignN = packE.getElementsByTagName("libDesign");
                Element libDesignE = (Element) libDesignN.item(0);
                //layout
                NodeList libLayoutN = packE.getElementsByTagName("libLayout");
                Element libLayoutE = (Element) libLayoutN.item(0);
                NodeList platformN = packE.getElementsByTagName("platform_name");
                Element platformE = (Element) platformN.item(0);

                NodeList platformND = packE.getElementsByTagName("platformDetail");
                Element platformED = (Element) platformND.item(0);

                NodeList sourceN = packE.getElementsByTagName("source");
                Element sourceE = (Element) sourceN.item(0);
                NodeList strategyN = packE.getElementsByTagName("strategy");
                Element strategyE = (Element) strategyN.item(0);
                NodeList strategyND = packE.getElementsByTagName("strategyDetail");
                Element strategyED = (Element) strategyND.item(0);
                NodeList selectionN = packE.getElementsByTagName("selection");
                Element selectionE = (Element) selectionN.item(0);
                NodeList timeN = packE.getElementsByTagName("release_time");
                Element timeE = (Element) timeN.item(0);
                NodeList submissionN = packE.getElementsByTagName("submission_second_accession");
                Element submissionE = (Element) submissionN.item(0);
                NodeList studyN = packE.getElementsByTagName("study_second_accession");
                Element studyE = (Element) studyN.item(0);
                NodeList sampleN = packE.getElementsByTagName("sample_third_accession");
                Element sampleE = (Element) sampleN.item(0);
                NodeList urlN = packE.getElementsByTagName("url");
                Element urlE = (Element) urlN.item(0);
                NodeList orgN = packE.getElementsByTagName("organization");
                Element orgE = (Element) orgN.item(0);
                NodeList libNominalSizeN = packE.getElementsByTagName("libNominalSize");
                Element libNominalSizeE = (Element) libNominalSizeN.item(0);
                NodeList nsdN = packE.getElementsByTagName("libNominalStandardDeviation");
                Element nsdE = (Element) nsdN.item(0);

                writer.write("EXPERIMENT");
                writer.write("\t");
                String center =centerE.getFirstChild().getNodeValue();
                String type = typeE.getFirstChild().getNodeValue();
                String expAcc = accE.getFirstChild().getNodeValue();
                String down = "0";
                if(downloadMap.containsKey(expAcc)){
                    down = "1";
                }
                String fType = "sra";
                List<FileType> fileTypes = new ArrayList<>();
                FileType fileType = new FileType();
                fileType.setFileType(fType);
                fileTypes.add(fileType);
                String expTitle = "";
                if (titleE.getFirstChild() != null) {
                    expTitle =titleE.getFirstChild().getNodeValue()  ;
                }
                String libraryName = "";
                if (libraryNameE.getFirstChild() != null) {
                    libraryName =libraryNameE.getFirstChild().getNodeValue()  ;
                }
                String libDesign = "";
                if (libDesignE.getFirstChild() != null) {
                    libDesign =libDesignE.getFirstChild().getNodeValue()  ;
                }
                String libLayout = libLayoutE.getFirstChild().getNodeValue();
                String platform = platformE.getFirstChild().getNodeValue();
                String platformD = platformED.getFirstChild().getNodeValue();
                String source = sourceE.getFirstChild().getNodeValue();
                String strategy = strategyE.getFirstChild().getNodeValue();
                String strategyD = strategyED.getFirstChild().getNodeValue();

                String selection = selectionE.getFirstChild().getNodeValue();

                String releaseTime = "";
                if (timeE.getFirstChild() != null) {
                    releaseTime =timeE.getFirstChild().getNodeValue()  ;
                }
                String dataset = "";
                if (submissionE.getFirstChild() != null) {
                    dataset =submissionE.getFirstChild().getNodeValue()  ;
                }
                String studyAcc = "";
                if (studyE.getFirstChild() != null) {
                    studyAcc =studyE.getFirstChild().getNodeValue()  ;
                }
                String proAcc = "";
                if(projectMap.containsKey(studyAcc)){
                    proAcc = (String) projectMap.get(studyAcc);
                }

                String sampleAcc = "";
                if (sampleE.getFirstChild() != null) {
                    sampleAcc =sampleE.getFirstChild().getNodeValue()  ;
                }

                String sname = "";
                String stitle = "";
                String sdec = "";
                String stype = "";
                String sid = "";
                String sorg = "";
                if(sampleAccMap.containsKey(sampleAcc)){
                    String secondAcc = (String) sampleAccMap.get(sampleAcc);
                    if(sampleMap.containsKey(secondAcc)){
                        String con = (String) sampleMap.get(secondAcc);
                        sname = con.split(code)[0];
                        stitle = con.split(code)[1];
                        sdec = con.split(code)[2];
                        stype = con.split(code)[3];
                        sid = con.split(code)[4];
                        sorg = con.split(code)[5];
                    }
                }

                String expUrl = urlE.getFirstChild().getNodeValue();
                String organization = "";
                if (orgE.getFirstChild() != null) {
                    organization =orgE.getFirstChild().getNodeValue()  ;
                }
                String libNominalSize = "";
                if (libNominalSizeE.getFirstChild() != null) {
                    libNominalSize =libNominalSizeE.getFirstChild().getNodeValue()  ;
                }
                String libNominalStandardDeviation = "";
                if (nsdE.getFirstChild() != null) {
                    libNominalStandardDeviation =nsdE.getFirstChild().getNodeValue()  ;
                }
                expObj.setCenter(center);
                expObj.setType(type);
                expObj.setAccession(expAcc);
                expObj.setDownloadable(down);
                expObj.setFileTypes(fileTypes);
                expObj.setTitle(expTitle);
                expObj.setLibraryName(libraryName);
                expObj.setLibDesign(libDesign);
                expObj.setLibLayout(libLayout);
                List<Platform> platforms = new ArrayList<>();
                Platform platform1 = new Platform();
                platform1.setPlatform(platform);
                platform1.setPlatformDetail(platformD);
                platforms.add(platform1);
                expObj.setPlatforms(platforms);
                expObj.setSource(source);
                expObj.setStrategy(strategy);
                expObj.setStrategyDetail(strategyD);
                expObj.setSelection(selection);
                expObj.setReleaseTime(releaseTime);
                expObj.setDataset(dataset);
                expObj.setStudyAcc(studyAcc);
                expObj.setProjectAcc(proAcc);
                expObj.setSampleAcc(sampleAcc);
                expObj.setSampleName(sname);
                expObj.setSampleTitle(stitle);
                expObj.setSampleDesc(sdec);
                expObj.setSampleType(stype);
                expObj.setTaxId(sid);
                expObj.setOrganism(sorg);
                expObj.setUrl(expUrl);
                expObj.setOrganization(organization);
                expObj.setLibNominalSize(libNominalSize);
                expObj.setLibNominalStandardDeviation(libNominalStandardDeviation);
                writer.write(JSON.toJSONString(expObj).replaceAll("\n", ""));
                writer.write("\n");
                expCount++;
            }
        }catch(Exception e){
            System.out.println(expFile+":加载异常");
            e.printStackTrace();
        }
        xmlBuilder.reset();
        return expCount;
    }
}
