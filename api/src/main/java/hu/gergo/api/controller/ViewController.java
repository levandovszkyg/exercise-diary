package hu.gergo.api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ViewController {

    @RequestMapping({ "/client/**" })
    public String index() {
        return "forward:/index.html";
    }

}