package com.yqkj.zysoft.common.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author yangchao.cool@gmail.com
 * @copyright: Copyright (c) 2020
 * @company: 成都市知用科技有限公司
 * @date 13:31
 * @description:
 */
public abstract class TreeNode<T> implements Serializable {
    /**
     * ID
     */
    private T id;
    /**
     * 父级ID
     */
    private T parentId;
    /**
     *
     */
    private String  key;
    /**
     * 标题
     */
    private String  title;
    /**
     * 值
     */
    private String value;
    /**
     * 描述信息
     */
    private String describe;
    /**
     * 是否子节点
     */
    private Boolean isLeaf;
    /**
     * 下级
     */
    private List<TreeNode> children;
    /**
     * 是否可以选择
     */
    private Boolean checkable = Boolean.TRUE;
    /**
     * 优先级
     */
    private Integer priority;

    public TreeNode() {
    }

    public TreeNode(T id, T parentId, String key, String title, String value, String describe, Boolean isLeaf, List<TreeNode> chidren, Boolean checkable) {
        this.id = id;
        this.parentId = parentId;
        this.key = key;
        this.title = title;
        this.value = value;
        this.describe = describe;
        this.isLeaf = isLeaf;
        this.children = chidren;
        this.checkable = checkable;
        if(!Objects.isNull(this.children) && this.children.size() >0){
            this.isLeaf =false;
        }else {
            this.isLeaf=true;
        }
    }

    public TreeNode(T id, T parentId, String key, String title, String value, Boolean isLeaf, List<TreeNode> chidren) {
        this.id = id;
        this.parentId = parentId;
        this.key = key;
        this.title = title;
        this.value = value;
        this.isLeaf = isLeaf;
        this.children = chidren;
        if(!Objects.isNull(this.children) && this.children.size() >0){
            this.isLeaf =false;
        }else {
            this.isLeaf=true;
        }
    }

    public TreeNode(T id, T parentId, String key, String title, String value, Boolean isLeaf , String describe) {
        this.id = id;
        this.parentId = parentId;
        this.key = key;
        this.title = title;
        this.value = value;
        this.isLeaf = isLeaf;
        this.describe=describe;
    }

    public Integer getPriority() {
        return Objects.isNull(this.priority)?255: this.priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Boolean getCheckable() {
        return checkable;
    }

    public void setCheckable(Boolean checkable) {
        this.checkable = checkable;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public T getId() {
        return id;
    }

    public void setId(T id) {
        this.id = id;
    }

    public T getParentId() {
        return parentId;
    }

    public void setParentId(T parentId) {
        this.parentId = parentId;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public  Boolean getIsLeaf(){
        if(!Objects.isNull(this.children) && this.children.size() >0){
            this.isLeaf =false;
        }
        return  this.isLeaf;
    }

    public void setIsLeaf(Boolean isLeaf) {
        this.isLeaf=isLeaf;
    }

    public List<TreeNode> getChildren() {
        return children;
    }

    public void setChildren(List<TreeNode> children) {
        this.children = children;
    }
    /**
     *验证父级ID是否相等
     * @param parent
     * @return
     */
    public abstract Boolean eqParent(T  parent);
    /**
     * ID 是否相同
     * @param id
     * @return
     */
    public  abstract  Boolean eqId(T id);
}
