package cn.ac.big.bigd.webservice.controller.xml;

import cn.ac.big.bigd.webservice.mapper.ncbi.NcbiSampleMapper;
import cn.ac.big.bigd.webservice.model.ncbi.SampleCountsNcbi;
import cn.ac.big.bigd.webservice.model.ncbi.SampleMonthCountsNcbi;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author chenxu
 */
@RestController
public class XmlController {
    @Autowired
    private NcbiSampleMapper ncbiSampleMapper;
    /**
     * xml解析String
     *
     * @param httpServletResponse
     * @return
     */
    @RequestMapping("/xmlToString")
    public String getXmlToString(HttpServletResponse httpServletResponse) throws ParseException {
        httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");
        String xml = "";
        SAXReader reader = new SAXReader();
        try {
            Document document = reader.read(new File("/webdb/gsagroup/webApplications/bigtools/submission.xml"));
            xml = document.asXML();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
//        File file = new File("/Users/laphael/Downloads/submission.xml");
//        InputStreamReader read = null;//考虑到编码格式
//        try {
//
//            read = new InputStreamReader(new FileInputStream(file),"GBK");
//            BufferedReader reader = new BufferedReader(read);
//            String txt = null;
//            while((txt = reader.readLine()) != null){
//                String str =  txt;
//                xml = xml + str;
//            }
//            read.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        return xml;
    }


}
