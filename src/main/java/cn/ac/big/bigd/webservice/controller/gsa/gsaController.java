package cn.ac.big.bigd.webservice.controller.gsa;

import cn.ac.big.bigd.webservice.mapper.gsa.GsaIndexMapper;
import cn.ac.big.bigd.webservice.model.index.*;
import com.alibaba.fastjson.JSON;
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
public class gsaController {

    @Autowired
    private GsaIndexMapper gsaIndexMapper;

    @RequestMapping("/getGsaIndex")
    public void getGsaIndex() throws IOException {
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
        File file = new File("/data/gsagroup/index/gsaA.bs");
        if(file.exists()){
            file.delete();
        }
        FileOutputStream fos;
        System.out.println("GSA 索引开始生成 "+new Date());
        try {
            fos = new FileOutputStream(file);
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(fos));
            writer.write("DB");
            writer.write("\t");
            writer.write(JSON.toJSONString(database).replaceAll("\n", ""));
            writer.write("\n");
            System.out.println("Experiment");
            List<Experiment> experimentList = this.gsaIndexMapper.getExperiment();
            for(Experiment experiment:experimentList){
                writer.write("EXPERIMENT");
                writer.write("\t");
                writer.write(JSON.toJSONString(experiment).replaceAll("\n", ""));
                writer.write("\n");
            }
            System.out.println("Experiment 生成索引数量："+experimentList.size());
            System.out.println("Run");
            List<Run> runList = this.gsaIndexMapper.getRun();
            for(Run run:runList){
                writer.write("Run");
                writer.write("\t");
                writer.write(JSON.toJSONString(run).replaceAll("\n", ""));
                writer.write("\n");
            }
            System.out.println("Run 生成索引数量："+runList.size());
            System.out.println("CRA");
            List<Gsa> gsaList = this.gsaIndexMapper.getGsa();
            for(Gsa gsa:gsaList){
                writer.write("CRA");
                writer.write("\t");
                writer.write(JSON.toJSONString(gsa).replaceAll("\n", ""));
                writer.write("\n");
            }
            System.out.println("CRA 生成索引数量："+gsaList.size());
            writer.flush();
            writer.close();
            System.out.println("GSA 索引生成完毕 "+new Date());
        } catch (Exception e){
            e.printStackTrace();
        }


    }

    //撤回索引 0为失败，1为成功
    @RequestMapping("/withdrawGsqCRA/{accession}")
    public int withdrawGsqCRA(@PathVariable(value = "accession") String accession) throws IOException {
        int result = 0;
        Boolean strResult = true;
        strResult = accession.matches("^CRA\\d{6}$");
        if(strResult){
            String fileName = "/data/gsagroup/index/withdraw/CRA/"+accession+".json";
//            String fileName = "/Users/laphael/Desktop/"+accession+".json";
            File deleteJsonFile = new File(fileName);
            deleteJsonFile.createNewFile();
            FileWriter deleteJsonWriter = new FileWriter(deleteJsonFile,true);
            BufferedWriter deleteJson = new BufferedWriter(deleteJsonWriter);
            CraAccessions craAccessions = this.gsaIndexMapper.getAccessionsByCraAcc(accession);
            if(craAccessions!=null){
                String craAcc = craAccessions.getCraAcc();
//                deleteJson.write("{\"delete\": {\"_id\": \""+craAcc+"\"}}"+"\n");
                deleteJson.write("cra\t"+craAcc+"\n");
                String expAccs = craAccessions.getExpAccs();
                if(expAccs!=null){
                    String[] expSp = expAccs.split(",");
                    for(String exp:expSp){
//                        deleteJson.write("{\"delete\": {\"_id\": \""+exp+"\"}}"+"\n");
                        deleteJson.write("exp\t"+exp+"\n");
                    }
                }
                String runAccs = craAccessions.getRunAccs();
                if(runAccs!=null){
                    String[] runSp = runAccs.split(",");
                    for(String run:runSp){
//                        deleteJson.write("{\"delete\": {\"_id\": \""+run+"\"}}"+"\n");
                        deleteJson.write("run\t"+run+"\n");
                    }
                }
                deleteJson.close();
                Process p =null;
                String[] arrP = new String[]{"java","-cp","/data/gsagroup/index/gsaSearchTools.jar","IndexWithdraw",fileName};
                for(String a:arrP){
                    System.out.println(a);
                }
                try {
                    p = Runtime.getRuntime().exec(arrP);
                    BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));

                    BufferedReader stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));
                    String s;
                    System.out.println("标准输出:");
                    while ((s = stdInput.readLine()) != null) {
                        System.out.println(s);
                    }

                    System.out.println("错误输出:");
                    while ((s = stdError.readLine()) != null) {
                        System.out.println(s);
                    }
                    p.waitFor();
                } catch (InterruptedException e) {
                    return result;
                }
                p.destroy();
                result=1;
            }
        } else {
            result=0;
        }
        return result;
    }
}
