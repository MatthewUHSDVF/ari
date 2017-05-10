package com.matthew.wechat.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Administrator on 2017/4/20.
 */
@Controller
public class PageController {

    @RequestMapping(value = "index")
    public String getIndex(){
        return "index";
    }
}
