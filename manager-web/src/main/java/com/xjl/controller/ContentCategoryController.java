package com.xjl.controller;

import com.xjl.entity.EasyUITreeNode;
import com.xjl.manager.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class ContentCategoryController {
    @Autowired
    private ContentService contentService;

//    请求 URL: http://www.myshop.com:8081/item/cat/list
    @RequestMapping(value = "/item/cat/list",method = RequestMethod.GET)
    @ResponseBody
    public List<EasyUITreeNode> nodes(@RequestParam(defaultValue = "0",name = "id") Long parentId){
        return contentService.nodeList(parentId);
    }
}
