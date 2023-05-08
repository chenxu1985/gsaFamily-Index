package cn.ac.big.bigd.webservice.controller.gsa;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;

@Controller
public class JspController {
    @RequestMapping("/index")
    public String welcome() {
        return "index";
    }
}

