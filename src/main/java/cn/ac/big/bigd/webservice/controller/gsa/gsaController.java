package cn.ac.big.bigd.webservice.controller.gsa;

import cn.ac.big.bigd.webservice.mapper.gsa.GsaIndexMapper;
import cn.ac.big.bigd.webservice.model.index.Database;
import cn.ac.big.bigd.webservice.model.index.Experiment;
import cn.ac.big.bigd.webservice.model.index.Gsa;
import cn.ac.big.bigd.webservice.model.index.Run;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
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
}
