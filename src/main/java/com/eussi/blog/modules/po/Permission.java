package com.eussi.blog.modules.po;

import com.eussi.blog.base.modules.BaseDomain;

import java.io.Serializable;

/**
 * 权限值
 * @author wangxm
 *
 */public class Permission extends BaseDomain implements Serializable {
    private static final long serialVersionUID = -5979636077639378677L;

    private long id;

    private long parentId;
    
    private String name;

    private String description;

    private int weight;

    private Integer version;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getParentId() {
        return parentId;
    }

    public void setParentId(long parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

}
