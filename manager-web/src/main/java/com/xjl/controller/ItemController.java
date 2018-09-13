package com.xjl.controller;

import com.xjl.entity.EasyUIDataGridResult;
import com.xjl.entity.Message;
import com.xjl.manager.service.ItemService;
import com.xjl.pojo.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ItemController {
    @Autowired
    private ItemService itemService;

    //http://localhost:8081/item/list?page=1&rows=30
    @RequestMapping(value = "/item/list",method = RequestMethod.GET)
    @ResponseBody
    public EasyUIDataGridResult itemList(Integer page,Integer rows){
        return itemService.itemList(page,rows);
    }

    @RequestMapping(value = "/item/save",method = RequestMethod.POST)
    @ResponseBody
    public Message save(Item item,String desc){
        return itemService.saveItem(item,desc);
    }
}
