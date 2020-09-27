package com.yqkj.zysoft.common.tree;

import com.yqkj.zysoft.common.dto.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author yangchao.cool@gmail.com
 * @copyright: Copyright (c) 2020
 * @company: 扬起科技有限公司
 * @date 2020/9/27 14:10
 * @description: 
 */
public class TreeToole {
    /**
     * 通过二层循环构建树，treeNodes 树节点数据，root根节点ID
     * @param treeNodes
     * @param root
     * @param <T>
     * @return
     */
    public static <I,T extends TreeNode> List<T> bulid(List<T> treeNodes, I root) {

        List<T> trees = new ArrayList<>();

        for (T treeNode : treeNodes) {

            if (treeNode.eqParent(root)) {
                trees.add(treeNode);
            }

            for (T it : treeNodes) {
                if (it.getParentId() == treeNode.getId()) {
                    if (Objects.isNull(treeNode.getChildren())) {
                        treeNode.setChildren(new ArrayList<>());
                    }
                    treeNode.getChildren().add(it);
                }
            }
        }
        return trees;
    }

    /**
     * 通过递归循环构建树
     * @param treeNodes
     * @param root
     * @param <T>
     * @return
     */
    public static <T extends TreeNode> List<T> buildByRecursive(List<T> treeNodes, Object root) {
        List<T> trees = new ArrayList<>();
        for (T treeNode : treeNodes) {
            if (root.equals(treeNode.getParentId())) {
                trees.add(findChildren(treeNode, treeNodes));
            }
        }
        return trees;
    }
    /**
     * 递归查找子节点
     *
     * @param treeNodes
     * @return
     */
    public static <T extends TreeNode> T findChildren(T treeNode, List<T> treeNodes) {
        for (T it : treeNodes) {
            if (treeNode.getId() == it.getParentId()) {
                if (treeNode.getChildren() == null) {
                    treeNode.setChildren(new ArrayList<TreeNode>());
                }
                treeNode.getChildren().add(findChildren(it, treeNodes));
            }
        }
        return treeNode;
    }
}
