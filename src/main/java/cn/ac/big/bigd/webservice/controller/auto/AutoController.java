package cn.ac.big.bigd.webservice.controller.auto;

import cn.ac.big.bigd.webservice.controller.gsa.ProjectController;
import cn.ac.big.bigd.webservice.mapper.gsa.FtpDataInfoMapper;
import cn.ac.big.bigd.webservice.mapper.gsa.GsaMapper;
import cn.ac.big.bigd.webservice.mapper.gsa.ProjectMapper;
import cn.ac.big.bigd.webservice.mapper.human.HumanProjectMapper;
import cn.ac.big.bigd.webservice.mapper.omix.OmixProjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RestController;

/**
 * author chenxu
 */
@RestController
public class AutoController {
    @Autowired
    private FtpDataInfoMapper ftpDataInfoMapper;

    @Autowired
    private GsaMapper gsaMapper;

    @Autowired
    private ProjectMapper projectMapper;
    @Autowired
    private HumanProjectMapper humanProjectMapper;
    @Autowired
    private OmixProjectMapper omixProjectMapper;

//    @Scheduled(cron = "0 47 15 * * ?") //每天6点钟执行
    public void autoProjectTaxonomy() throws Exception {
        ProjectController pro = new ProjectController();
        pro.getProjectTaxonomyFile(projectMapper,humanProjectMapper,omixProjectMapper);
    }

}
