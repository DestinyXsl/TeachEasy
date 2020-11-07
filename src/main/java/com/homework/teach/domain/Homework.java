package com.homework.teach.domain;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;

public class Homework implements Serializable {
    @ApiModelProperty(dataType = "Integer", name = "id", value = "主键")
    private Integer id;//主键
    @ApiModelProperty(dataType = "Integer", name = "gradeClassesId", value = "班级id")
    private Integer gradeClassesId;//班级id
    @ApiModelProperty(dataType = "Integer", name = "subjectId", value = "科目id")
    private Integer subjectId;//科目id
    @ApiModelProperty(dataType = "Integer", name = "adminId", value = "管理员id")
    private Integer adminId;//管理员id
    @ApiModelProperty(dataType = "Integer", name = "workBookId", value = "作业本id")
    private Integer workBookId;//作业本id
    @ApiModelProperty(dataType = "String", name = "content", value = "作业内容")
    private String content;//作业内容
    @ApiModelProperty(dataType = "Integer", name = "homeworkStatus", value = "作业状态(1进行中,2完成)")
    private Integer homeworkStatus;//作业状态(1进行中,2完成)
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

    public Integer getWorkBookId() {
        return workBookId;
    }

    public void setWorkBookId(Integer workBookId) {
        this.workBookId = workBookId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getHomeworkStatus() {
        return homeworkStatus;
    }

    public void setHomeworkStatus(Integer homeworkStatus) {
        this.homeworkStatus = homeworkStatus;
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

}