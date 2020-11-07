package com.homework.teach.domain;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;

public class GradeClasses implements Serializable {
    @ApiModelProperty(dataType = "Integer", name = "id", value = "主键id")
    private Integer id;//主键id
    @ApiModelProperty(dataType = "Integer", name = "gradeId", value = "年级id")
    private Integer gradeId;//年级id
    @ApiModelProperty(dataType = "string", name = "classes", value = "班")
    private String classes;//班
    @ApiModelProperty(dataType = "string", name = "createTime", value = "创建时间")
    private Date createTime;//创建时间
    @ApiModelProperty(dataType = "string", name = "updateTime", value = "更新时间")
    private Date updateTime;//更新时间
    @ApiModelProperty(dataType = "Integer", name = "isDelete", value = "是否删除（0否，1是）")
    private Integer status;//是否删除（0否，1是）

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGradeId() {
        return gradeId;
    }

    public void setGradeId(Integer gradeId) {
        this.gradeId = gradeId;
    }

    public String getClasses() {
        return classes;
    }

    public void setClasses(String classes) {
        this.classes = classes;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

}