package com.xjl.service;

import com.alibaba.druid.sql.visitor.functions.If;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xjl.entity.EasyUIDataGridResult;
import com.xjl.entity.EasyUITreeNode;
import com.xjl.entity.IDUtils;
import com.xjl.entity.Message;
import com.xjl.manager.service.ItemService;
import com.xjl.mapper.ItemDescMapper;
import com.xjl.mapper.ItemMapper;
import com.xjl.pojo.Item;
import com.xjl.pojo.ItemDesc;
import com.xjl.pojo.ItemExample;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.util.Date;
import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {
    @Autowired
    private ItemMapper itemMapper;
    @Autowired
    private ItemDescMapper itemDescMapper;


    @Override
    public EasyUIDataGridResult itemList(Integer page, Integer rows) {
        PageHelper.startPage(page,rows);
        ItemExample example = new ItemExample();
        List<Item> items = itemMapper.selectByExample(example);
        //获取分页信息
        PageInfo<Item> pageInfo = new PageInfo<>(items);
        EasyUIDataGridResult result = new EasyUIDataGridResult();
        result.setRows(items);
        result.setTotal((int) pageInfo.getTotal());
        return result;
    }

    @Override
    public Message saveItem(Item item,String desc) {
        //数据校验
        if(checkItem(item)){

        }
        //初始化数据
        defaultItem(item);
        int row = itemMapper.insert(item);
        System.out.println(row);
        if (row>0){
            ItemDesc itemDesc = new ItemDesc();
            itemDesc.setItemId(item.getId());
            itemDesc.setCreated(new Date());
            itemDesc.setUpdated(new Date());
            itemDesc.setItemDesc(desc);
            itemDescMapper.insert(itemDesc);
            return Message.ok(null);
        }
        return Message.failure(null);
    }

    /**
     * 初始化商品数据
     * status,created
     * @param item
     */
    private void defaultItem(Item item) {
        //'商品状态，1-正常，2-下架，3-删除'
        item.setStatus(1);
        //创建时间
        item.setCreated(new Date());
        //更新时间
        item.setUpdated(new Date());
        //获取id
        item.setId(IDUtils.genItemId());
    }

    private boolean checkItem(Item item) {
        return true;
    }


}
