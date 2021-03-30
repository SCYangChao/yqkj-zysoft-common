package com.yqkj.zysoft.common.tree;

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
public final class TreeToole {

    private  TreeToole() {

    }
    /**
     * 通过二层循环构建树，treeNodes 树节点数据，root根节点ID
     * @param treeNodes 数据List
     * @param root 顶级数据节点Id
     * @param <T> 树对象
     * @param <I> 节点Id
     * @return 返回构建好的树
     */
    public static <I, T extends TreeNode> List<T> bulid(List<T> treeNodes, I root) {
        List<T> trees = new ArrayList<>();
        for (T treeNode : treeNodes) {
            if (treeNode.eqPId(root)) {
                trees.add(treeNode);
            }
            for (T it : treeNodes) {
                if (it.getpId() == treeNode.getId()) {
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
     * @param treeNodes 数据列表数据
     * @param root 顶级树ID
     * @param <T> 树数据
     * @return 构建树
     */
    public static <T extends TreeNode> List<T> buildByRecursive(List<T> treeNodes, Object root) {
        List<T> trees = new ArrayList<>();
        for (T treeNode : treeNodes) {
            if (root.equals(treeNode.getpId())) {
                trees.add(findChildren(treeNode, treeNodes));
            }
        }
        return trees;
    }
    /**
     * 递归查找子节点
     * @param treeNodes 树集合
     * @param  treeNode 树上级类
     * @param  <T> 树数据对象
     * @return 子类
     */
    public static <T extends TreeNode> T findChildren(T treeNode, List<T> treeNodes) {
        for (T it : treeNodes) {
            if (treeNode.getId() == it.getpId()) {
                if (treeNode.getChildren() == null) {
                    treeNode.setChildren(new ArrayList<TreeNode>());
                }
                treeNode.getChildren().add(findChildren(it, treeNodes));
            }
        }
        return treeNode;
    }
}
