package cn.ac.big.bigd.webservice.mapper.gsa;

import cn.ac.big.bigd.webservice.model.index.CraAccessions;
import cn.ac.big.bigd.webservice.model.index.Experiment;
import cn.ac.big.bigd.webservice.model.index.Gsa;
import cn.ac.big.bigd.webservice.model.index.Run;

import java.util.List;

public interface GsaIndexMapper {
    List<Experiment> getExperiment();
    List<Gsa> getGsa();
    List<Run> getRun();
    CraAccessions getAccessionsByCraAcc(String acc);
}
