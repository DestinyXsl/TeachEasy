package com.homework.teach.domain;

public class RitCusriReport {
    private long cusNo;//客户号
    private String rightEntno;//权益实体号
    private String configTp;//权益配置方式
    private String configObj;//具体配置方式
    private String mblno;//手机号
    private String certTp;//客户证件类型
    private String certNo;//证件号
    private String rightNo;//权益编号
    private String cusriSt;//客户权益状态
    private String expDt;//权益到期日
    private String chgAcdt;//修改会计日
    private String chgDt;//修改日期
    private String chgTm;//修改时间

    public long getCusNo() {
        return cusNo;
    }

    public void setCusNo(long cusNo) {
        this.cusNo = cusNo;
    }

    public String getRightEntno() {
        return rightEntno;
    }

    public void setRightEntno(String rightEntno) {
        this.rightEntno = rightEntno;
    }

    public String getConfigTp() {
        return configTp;
    }

    public void setConfigTp(String configTp) {
        this.configTp = configTp;
    }

    public String getConfigObj() {
        return configObj;
    }

    public void setConfigObj(String configObj) {
        this.configObj = configObj;
    }

    public String getMblno() {
        return mblno;
    }

    public void setMblno(String mblno) {
        this.mblno = mblno;
    }

    public String getCertTp() {
        return certTp;
    }

    public void setCertTp(String certTp) {
        this.certTp = certTp;
    }

    public String getCertNo() {
        return certNo;
    }

    public void setCertNo(String certNo) {
        this.certNo = certNo;
    }

    public String getRightNo() {
        return rightNo;
    }

    public void setRightNo(String rightNo) {
        this.rightNo = rightNo;
    }

    public String getCusriSt() {
        return cusriSt;
    }

    public void setCusriSt(String cusriSt) {
        this.cusriSt = cusriSt;
    }

    public String getExpDt() {
        return expDt;
    }

    public void setExpDt(String expDt) {
        this.expDt = expDt;
    }

    public String getChgAcdt() {
        return chgAcdt;
    }

    public void setChgAcdt(String chgAcdt) {
        this.chgAcdt = chgAcdt;
    }

    public String getChgDt() {
        return chgDt;
    }

    public void setChgDt(String chgDt) {
        this.chgDt = chgDt;
    }

    public String getChgTm() {
        return chgTm;
    }

    public void setChgTm(String chgTm) {
        this.chgTm = chgTm;
    }

    @Override
    public String toString() {
        return "RitCusriReport{" +
                "cusNo=" + cusNo +
                ", rightEntno='" + rightEntno + '\'' +
                ", configTp='" + configTp + '\'' +
                ", configObj='" + configObj + '\'' +
                ", mblno='" + mblno + '\'' +
                ", certTp='" + certTp + '\'' +
                ", certNo='" + certNo + '\'' +
                ", rightNo='" + rightNo + '\'' +
                ", cusriSt='" + cusriSt + '\'' +
                ", expDt='" + expDt + '\'' +
                ", chgAcdt='" + chgAcdt + '\'' +
                ", chgDt='" + chgDt + '\'' +
                ", chgTm='" + chgTm + '\'' +
                '}';
    }
}
