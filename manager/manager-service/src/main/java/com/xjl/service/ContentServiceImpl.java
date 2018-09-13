package com.xjl.service;

import com.xjl.entity.EasyUITreeNode;
import com.xjl.manager.service.ContentService;
import com.xjl.mapper.ContentCategoryMapper;
import com.xjl.pojo.ContentCategory;
import com.xjl.pojo.ContentCategoryExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ContentServiceImpl implements ContentService {
    @Autowired
    private ContentCategoryMapper contentCategoryMapper;

    @Override
    public List<EasyUITreeNode> nodeList(Long parentId) {
        ContentCategoryExample example = new ContentCategoryExample();
        ContentCategoryExample.Criteria criteria = example.createCriteria();
        criteria.andParentIdEqualTo(parentId);
        List<ContentCategory> categories = contentCategoryMapper.selectByExample(example);
        List<EasyUITreeNode> nodes = new ArrayList<>();
        if (categories.size()>0){
            for (ContentCategory category : categories) {
                EasyUITreeNode node = new EasyUITreeNode();
                node.setId(category.getId());
                node.setText(category.getName());
                node.setState(category.getIsParent()?"closed":"open");
                nodes.add(node);
            }
        }
        return nodes;
    }
}
