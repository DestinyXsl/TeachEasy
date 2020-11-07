package com.homework.teach.domain;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;

public class Student implements Serializable {
    @ApiModelProperty(dataType = "Integer", name = "id", value = "主键")
    private Integer id;//主键
    @ApiModelProperty(dataType = "Integer", name = "gradeClassesId", value = "所在班级id")
    private Integer gradeClassesId;//所在班级id
    @ApiModelProperty(dataType = "String", name = "name", value = "姓名")
    private String name;//姓名
    @ApiModelProperty(dataType = "Integer", name = "sex", value = "性别")
    private Integer sex;//性别
    @ApiModelProperty(dataType = "String", name = "telephone", value = "家长联系电话")
    private String telephone;//家长联系电话
    @ApiModelProperty(dataType = "Integer", name = "seatNumber", value = "座号")
    private Integer seatNumber;//座号
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Integer getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(Integer seatNumber) {
        this.seatNumber = seatNumber;
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