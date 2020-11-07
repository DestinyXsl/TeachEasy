package com.homework.teach.domain;

import io.swagger.annotations.ApiModelProperty;

public class TCodeBody {
    @ApiModelProperty(dataType = "string", name = "vgdecoderesult", value = "解码的结果字符串")
    private String vgdecoderesult;
    @ApiModelProperty(dataType = "string", name = "devicenumber", value = "扫描器设备号")
    private String devicenumber;

    public String getVgdecoderesult() {
        return vgdecoderesult;
    }

    public void setVgdecoderesult(String vgdecoderesult) {
        this.vgdecoderesult = vgdecoderesult;
    }

    public String getDevicenumber() {
        return devicenumber;
    }

    public void setDevicenumber(String devicenumber) {
        this.devicenumber = devicenumber;
    }

    @Override
    public String toString() {
        return "TCodeBody{" +
                "vgdecoderesult='" + vgdecoderesult + '\'' +
                ", devicenumber='" + devicenumber + '\'' +
                '}';
    }
}
