package com.homework.teach.domain.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

public class HomeworkRecordVo implements Serializable {
    @ApiModelProperty(dataType = "Integer", name = "id", value = "主键")
    private Integer id;//主键
    @ApiModelProperty(dataType = "Integer", name = "homeWorkId", value = "作业Id")
    private Integer homeWorkId;//作业Id
    @ApiModelProperty(dataType = "Integer", name = "studentId", value = "学生Id")
    private Integer studentId;//学生Id
    @ApiModelProperty(dataType = "string", name = "tcode", value = "条形码")
    private String tcode;//学生Id
    @ApiModelProperty(dataType = "Integer", name = "recordStatus", value = "作业上交状态(0未交,,1已交)")
    private Integer recordStatus;//作业上交状态(0未交,,1已交)
    @ApiModelProperty(dataType = "Integer", name = "level", value = "")
    private Integer level;
    @ApiModelProperty(dataType = "string", name = "accountName", value = "账号")
    private String accountName;//账号
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

    public Integer getHomeWorkId() {
        return homeWorkId;
    }

    public void setHomeWorkId(Integer homeWorkId) {
        this.homeWorkId = homeWorkId;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public String getTcode() {
        return tcode;
    }

    public void setTcode(String tcode) {
        this.tcode = tcode;
    }

    public Integer getRecordStatus() {
        return recordStatus;
    }

    public void setRecordStatus(Integer recordStatus) {
        this.recordStatus = recordStatus;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
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

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    @Override
    public String toString() {
        return "HomeworkRecordVo{" +
                "id=" + id +
                ", homeWorkId=" + homeWorkId +
                ", studentId=" + studentId +
                ", tcode='" + tcode + '\'' +
                ", recordStatus=" + recordStatus +
                ", level=" + level +
                ", accountName='" + accountName + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", status=" + status +
                '}';
    }
}