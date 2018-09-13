package com.xjl.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class IndexController {
    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String index(){
        return "index";
    }

    //http://localhost:8081/item-add?_=1536738428045
    @RequestMapping(value = "/{page}",method = RequestMethod.GET)
    public String page(@PathVariable("page")String page){
        return page;
    }



}
