package com.homework.teach.domain.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

public class UpdateHomeworkRecordVo implements Serializable {
    @ApiModelProperty(dataType = "Integer", name = "id", value = "主键")
    private Integer id;//主键
    @ApiModelProperty(dataType = "Integer", name = "recordStatus", value = "作业上交状态(0未交,,1已交)")
    private Integer recordStatus;//作业上交状态(0未交,,1已交)
    @ApiModelProperty(dataType = "Integer", name = "level", value = "作业评价(0未评价,1优,2错误多,3未完成)")
    private Integer level;//作业评价(0未评价,1优,2错误多,3未完成)
    @ApiModelProperty(dataType = "Integer", name = "type", value = "类型(1修改上交状态,2修改评价)")
    private Integer type;//类型
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRecordStatus() {
        return recordStatus;
    }

    public void setRecordStatus(Integer recordStatus) {
        this.recordStatus = recordStatus;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "UpdateHomeworkRecordVo{" +
                "id=" + id +
                ", recordStatus=" + recordStatus +
                ", level=" + level +
                ", type=" + type +
                '}';
    }
}