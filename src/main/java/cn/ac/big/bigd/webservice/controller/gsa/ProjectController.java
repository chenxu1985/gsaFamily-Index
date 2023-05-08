package cn.ac.big.bigd.webservice.controller.gsa;

import cn.ac.big.bigd.webservice.mapper.gsa.KeyMapper;
import cn.ac.big.bigd.webservice.mapper.gsa.ProjectMapper;
import cn.ac.big.bigd.webservice.mapper.gsa.ReleaseMapper;
import cn.ac.big.bigd.webservice.mapper.gsa.UserMapper;
import cn.ac.big.bigd.webservice.mapper.human.HumanProjectMapper;
import cn.ac.big.bigd.webservice.mapper.omix.OmixProjectMapper;
import cn.ac.big.bigd.webservice.model.gsa.*;
import cn.ac.big.bigd.webservice.model.human.ProjectTaxonomyHuman;
import cn.ac.big.bigd.webservice.model.omix.ProjectTaxonomyOmix;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.*;

/**
 * author chenxu
 */
@RestController
public class ProjectController {


    @Autowired
    private ProjectMapper projectMapper;
    @Autowired
    private HumanProjectMapper humanProjectMapper;
    @Autowired
    private OmixProjectMapper omixProjectMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private KeyMapper keyMapper;
    @Autowired
    private ReleaseMapper releaseMapper;

    @RequestMapping(value = "/getProjectTaxonomy")
    public List<ProjectTaxonomy> getProjectTaxonomy(HttpServletResponse httpServletResponse) {
        httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");
        List<ProjectTaxonomy> projectTaxonomyList = new ArrayList();
        List<Project> projectList = this.getReleaseList();
        for(Project p:projectList){
            int pid= p.getPrjId();
            String acc = p.getAccession();
            ProjectTaxonomy projectTaxonomy1 = this.getTaxonomy(pid);
            ProjectTaxonomyHuman projectTaxonomy2 = this.getHumanTaxonomy(pid);
            ProjectTaxonomyOmix projectTaxonomy3 = this.getOmixTaxonomy(pid);
            if(projectTaxonomy1!=null||projectTaxonomy2!=null||projectTaxonomy3!=null){
                ProjectTaxonomy projectTaxonomy = this.getALlTaxonomy(acc,projectTaxonomy1,projectTaxonomy2,projectTaxonomy3);
                projectTaxonomyList.add(projectTaxonomy);
            }
        }
        return projectTaxonomyList;
    }

    public void getProjectTaxonomyFile(ProjectMapper projectMapper,HumanProjectMapper humanProjectMapper,OmixProjectMapper omixProjectMapper) {
        List<ProjectTaxonomy> projectTaxonomyList = new ArrayList();
        List<Project> projectList = this.getReleaseListFile(projectMapper);
        for(Project p:projectList){
            int pid= p.getPrjId();
            String acc = p.getAccession();
            ProjectTaxonomy projectTaxonomy1 = this.getTaxonomyFile(pid,projectMapper);
            ProjectTaxonomyHuman projectTaxonomy2 = this.getHumanTaxonomyFile(pid,humanProjectMapper);
            ProjectTaxonomyOmix projectTaxonomy3 = this.getOmixTaxonomyFile(pid,omixProjectMapper);
            if(projectTaxonomy1!=null||projectTaxonomy2!=null||projectTaxonomy3!=null){
                ProjectTaxonomy projectTaxonomy = this.getALlTaxonomy(acc,projectTaxonomy1,projectTaxonomy2,projectTaxonomy3);
                projectTaxonomyList.add(projectTaxonomy);
            }
        }

        try {
//            String unPath = "/Users/laphael/Desktop/projectTaxonomy.txt";
            String unPath = "/webdb/gsagroup/webApplications/bigtools/file/projectTaxonomy.txt";
            File unFile = new File(unPath);
            if(unFile.exists()){
                unFile.delete();
            }
            if(!unFile.exists()){
                FileOutputStream unFileOutTotal;
                unFileOutTotal = new FileOutputStream(unPath,false);
                BufferedWriter unWriter = new BufferedWriter(new OutputStreamWriter(unFileOutTotal));
                for(ProjectTaxonomy pt:projectTaxonomyList) {
                    String acc = pt.getAccession();
                    String tax = pt.getTaxonomy();
                    unWriter.write(acc+"\t"+tax+"\n");
                }
                unWriter.flush();
                unWriter.close();
                unFileOutTotal.close();
            } else {
                System.out.println("文件删除失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Project> getReleaseList() {
        List<Project> projectList = this.projectMapper.getReleaseList();
        return projectList;
    }
    public ProjectTaxonomy getTaxonomy(int prjId) {
        ProjectTaxonomy projectTaxonomy = this.projectMapper.getTaxonomy(prjId);
        return projectTaxonomy;
    }
    public ProjectTaxonomyHuman getHumanTaxonomy(int prjId) {
        ProjectTaxonomyHuman projectTaxonomy = this.humanProjectMapper.getHumanTaxonomy(prjId);
        return projectTaxonomy;
    }
    public ProjectTaxonomyOmix getOmixTaxonomy(int prjId) {
        ProjectTaxonomyOmix projectTaxonomy = this.omixProjectMapper.getOmixTaxonomy(prjId);
        return projectTaxonomy;
    }

    public List<Project> getReleaseListFile(ProjectMapper projectMapper) {
        List<Project> projectList = projectMapper.getReleaseList();
        return projectList;
    }
    public ProjectTaxonomy getTaxonomyFile(int prjId,ProjectMapper projectMapper) {
        ProjectTaxonomy projectTaxonomy = projectMapper.getTaxonomy(prjId);
        return projectTaxonomy;
    }
    public ProjectTaxonomyHuman getHumanTaxonomyFile(int prjId,HumanProjectMapper humanProjectMapper) {
        ProjectTaxonomyHuman projectTaxonomy = humanProjectMapper.getHumanTaxonomy(prjId);
        return projectTaxonomy;
    }
    public ProjectTaxonomyOmix getOmixTaxonomyFile(int prjId,OmixProjectMapper omixProjectMapper) {
        ProjectTaxonomyOmix projectTaxonomy = omixProjectMapper.getOmixTaxonomy(prjId);
        return projectTaxonomy;
    }
    public ProjectTaxonomy getALlTaxonomy(String acc,ProjectTaxonomy projectTaxonomy1,ProjectTaxonomyHuman projectTaxonomy2,ProjectTaxonomyOmix projectTaxonomy3){
        ProjectTaxonomy projectTaxonomy = new ProjectTaxonomy();
        String taxonomy = "";
        String taxonomyGSA = "";
        String taxonomyHuman = "";
        String taxonomyOmix = "";
        if(projectTaxonomy1!=null) {
            taxonomyGSA = projectTaxonomy1.getTaxonomy();
            taxonomy = taxonomyGSA;
        }
        if(projectTaxonomy2!=null){
            taxonomyHuman = projectTaxonomy2.getTaxonomy();
            taxonomyHuman = taxonomyHuman.replace("\"","");
            if(taxonomy!=null&&taxonomy!=""){
                taxonomy = taxonomy + "," + taxonomyHuman;
            } else {
                taxonomy = taxonomyHuman;
            }
        }
        if(projectTaxonomy3!=null){
            taxonomyOmix = projectTaxonomy3.getTaxonomy();
            if(taxonomy!=null&&taxonomy!=""){
                taxonomy = taxonomy + "," + taxonomyOmix;
            } else {
                taxonomy = taxonomyOmix;
            }
        }
        //taxonomy = taxonomyGSA + taxonomyHuman + taxonomyOmix;
        if(taxonomy.contains(",")){
            String[] sp = taxonomy.split(",");
            HashMap<String,String> spMap = new HashMap();
            String st= "";
            for(String ta:sp){
                if(!spMap.containsKey(ta)){
                    spMap.put(ta,ta);
                }
            }
            for(String key:spMap.keySet()){
                if(st!=""){
                    st = st +","+ key;
                } else {
                    st =  key;
                }

            }
            projectTaxonomy.setAccession(acc);
            projectTaxonomy.setTaxonomy(st);
        } else {
            projectTaxonomy.setAccession(acc);
            projectTaxonomy.setTaxonomy(taxonomy);
        }

        //System.out.println(taxonomy);
        return projectTaxonomy;
    }
    /**
     * 其他数据库发布project接口
     * @param
     * @param httpServletResponse
     * @return
     */
    @RequestMapping("/releaseProject/{prjId}/{webserver}/{uid}/{keyValue}")
    public Result releaseProject(@PathVariable("prjId") int prjId,@PathVariable("webserver") String webserver,@PathVariable("uid") String uid,@PathVariable("keyValue") String keyValue, HttpServletResponse httpServletResponse) {
        System.out.println("prjId: "+prjId);
        System.out.println("uid: "+uid);
        System.out.println("web: "+webserver);
        System.out.println("keyValue: "+keyValue);
        Result result = new Result();
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
                        int status = 4;
                        String database = webserver;
                        Project pro = this.projectMapper.findProjectById(prjId);
                        if(pro!=null){
                            int pStatus = pro.getStatus();
                            int pRelease = pro.getReleaseState();
                            if(pRelease == 2){
                                result.setErrorM("项目已经发布无需发布");
                                result.setStatus("Success");
                            } else {
                                if(pStatus!= 5){
                                    Project p = new Project();
                                    p.setStatus(3);
                                    p.setPrjId(prjId);
                                    p.setReleaseTime(new Date());
                                    p.setReleaseState(2);
                                    this.projectMapper.releaseProjectWeb(p);
                                    result.setStatus("Success");
                                    ReleaseWeb releaseWeb = new ReleaseWeb();
                                    releaseWeb.setUser_id(user_id);
                                    releaseWeb.setPrj_id(prjId);
                                    releaseWeb.setSample_id(0);
                                    releaseWeb.setStatus(status);
                                    releaseWeb.setWebserver(database);
                                    releaseWeb.setOption_date(new Date());
                                    this.releaseMapper.insertRelease(releaseWeb);
                                } else {
                                    result.setErrorM("项目已经删除");
                                    result.setStatus("Fail");
                                }
                            }
                        } else {
                            result.setErrorM("项目ID不存在");
                            result.setStatus("Fail");
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
        return result;
    }
}
