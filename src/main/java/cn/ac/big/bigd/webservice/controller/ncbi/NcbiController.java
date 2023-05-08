package cn.ac.big.bigd.webservice.controller.ncbi;

import cn.ac.big.bigd.webservice.mapper.ncbi.NcbiSampleMapper;
import cn.ac.big.bigd.webservice.model.gsa.*;
import cn.ac.big.bigd.webservice.model.ncbi.SampleCountsNcbi;
import cn.ac.big.bigd.webservice.model.ncbi.SampleMonthCountsNcbi;
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
public class NcbiController {
    @Autowired
    private NcbiSampleMapper ncbiSampleMapper;
    /**
     * 根据国家查询GSA数据库中新冠样本数量
     * @param countryName
     * @param httpServletResponse
     * @return
     */
    @RequestMapping("/getSampleCountsByCountryNameNcbiAll/{countryName}")
    public SampleMonthCountsNcbi getNcbiBricsSamples(@PathVariable("countryName") String countryName, HttpServletResponse httpServletResponse) throws ParseException {
        httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");
        TreeMap<String,Integer> mouthMap = this.getMounth();
        List<SampleCountsNcbi> sampleCounts = this.ncbiSampleMapper.getSampleCounts(countryName);
        TreeMap<String,Integer> map = new TreeMap();
        if(sampleCounts.size()>0){
            map = getMap(sampleCounts);
        }
        for(Map.Entry<String,Integer> entry:mouthMap.entrySet()){
            String key = entry.getKey();
            int counts = 0;
            if(map!=null){
                if(map.containsKey(key)){
                    counts = map.get(key);
                }
            }
            entry.setValue(counts);
        }
        SampleMonthCountsNcbi sampleMonthCounts = new SampleMonthCountsNcbi();
        sampleMonthCounts.setMonthCounts(mouthMap);
        return sampleMonthCounts;
    }
    public TreeMap getMap(List<SampleCountsNcbi> samples){
        TreeMap map = new TreeMap();
        for(SampleCountsNcbi sampleCounts:samples){
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

        while(begin.getTime()<=end.getTime()){
            result.add(tempStart.getTime());
            tempStart.add(Calendar.DAY_OF_YEAR, 1);
            begin = tempStart.getTime();
        }
        return result;
    }


}
