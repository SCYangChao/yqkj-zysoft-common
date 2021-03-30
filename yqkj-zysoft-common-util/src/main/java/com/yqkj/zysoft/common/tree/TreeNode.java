package com.yqkj.zysoft.common.tree;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * @ClassName TreeNode
 * @Description  树实体
 * @Author yangchao.cool@gmail.com
 * @Date 2021/2/10 14:15
 * @param  <Id> 树的ID
 * @param  <PId> 树的上级ID 如果为空则顶级树
 * @Version 1.0
 **/
public class TreeNode <Id, PId> implements Serializable {
    /**
     * ID编码
     */
    private Id id;
    /**
     * 上级编码
     */
    private PId pId;
    /**
     * 标题/名称
     */
    private String name;
    /**
     * 下级节点数据
     */
    private List<TreeNode> children;
    /**
     * 排序
     */
    private Integer sort;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @param id 属于的ID
     * @return true 等于上级ID 否则 不是
     */
    public  Boolean eqPId(Id id) {
        if (Objects.isNull(pId) && Objects.isNull(id)) {
            return Boolean.TRUE;
        }
        if (Objects.isNull(id)) {
            return Boolean.FALSE;
        }
        if (id.equals(pId)) {
            return Boolean.TRUE;
        }
        return  Boolean.FALSE;
    }

    public Id getId() {
        return id;
    }

    public void setId(Id id) {
        this.id = id;
    }

    /**
     * @return 获取上级ID
     */
    public PId getpId() {
        return pId;
    }
    /**
     * 设置上级ID
     * @param pId 上级ID
     */
    public void setpId(PId pId) {
        this.pId = pId;
    }

    public List<TreeNode> getChildren() {
        return children;
    }

    public void setChildren(List<TreeNode> children) {
        this.children = children;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }
}
