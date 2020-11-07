package com.homework.teach.domain;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;

public class WorkBook implements Serializable {
    @ApiModelProperty(dataType = "Integer", name = "id", value = "主键")
    private Integer id;//主键
    @ApiModelProperty(dataType = "Integer", name = "gradeClassesId", value = "班级Id")
    private Integer gradeClassesId;//班级Id
    @ApiModelProperty(dataType = "Integer", name = "subjectId", value = "科目Id")
    private Integer subjectId;//科目Id
    @ApiModelProperty(dataType = "Integer", name = "adminId", value = "管理员id")
    private Integer adminId;//管理员id
    @ApiModelProperty(dataType = "String", name = "name", value = "作业本名称")
    private String name;//作业本名称
    @ApiModelProperty(dataType = "String", name = "baseTcode", value = "基础条形码")
    private String baseTcode;//基础条形码
    @ApiModelProperty(dataType = "string", name = "createTime", value = "创建时间")
    private Date createTime;//创建时间
    @ApiModelProperty(dataType = "string", name = "updateTime", value = "更新时间")
    private Date updateTime;//更新时间
    @ApiModelProperty(dataType = "Integer", name = "status", value = "状态(0无效,1有效)")
    private Integer status;//状态(0无效,1有效)

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGradeClassesId() {
        return gradeClassesId;
    }

    public void setGradeClassesId(Integer gradeClassesId) {
        this.gradeClassesId = gradeClassesId;
    }

    public Integer getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Integer subjectId) {
        this.subjectId = subjectId;
    }

    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getBaseTcode() {
        return baseTcode;
    }

    public void setBaseTcode(String baseTcode) {
        this.baseTcode = baseTcode;
    }
}