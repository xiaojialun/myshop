package com.xjl.manager.service;

import com.xjl.entity.EasyUITreeNode;

import java.util.List;

public interface ContentService {
    List<EasyUITreeNode> nodeList(Long parentId);
}
