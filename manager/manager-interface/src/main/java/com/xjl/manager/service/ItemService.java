package com.xjl.manager.service;

import com.xjl.entity.EasyUIDataGridResult;
import com.xjl.entity.Message;
import com.xjl.pojo.Item;

public interface ItemService {
    EasyUIDataGridResult itemList(Integer page,Integer rows);

    Message saveItem(Item item, String desc);
}
