package com.homework.teach.domain;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;

public class WorkBookTcode implements Serializable {
    @ApiModelProperty(dataType = "Integer", name = "id", value = "id")
    private Integer id;//id
    @ApiModelProperty(dataType = "Integer", name = "workBookId", value = "作业本id")
    private Integer workBookId;//作业本id
    @ApiModelProperty(dataType = "Integer", name = "studentId", value = "学生id")
    private Integer studentId;//学生id
    @ApiModelProperty(dataType = "String", name = "tcode", value = "条形码")
    private String tcode;//条形码
    @ApiModelProperty(dataType = "String", name = "description", value = "作业本描述")
    private String description;//作业本描述
    @ApiModelProperty(dataType = "string", name = "createTime", value = "创建时间")
    private Date createTime;//创建时间
    @ApiModelProperty(dataType = "Integer", name = "isDelete", value = "是否删除(0否,1是)")
    private Integer isDelete;//是否删除(0否,1是)

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getWorkBookId() {
        return workBookId;
    }

    public void setWorkBookId(Integer workBookId) {
        this.workBookId = workBookId;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

}