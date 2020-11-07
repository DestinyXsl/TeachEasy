package com.homework.teach.util;

import com.homework.teach.domain.RitCusriReport;

public class InsertUtil {
    public static void main(String[] args) {
        RitCusriReport rcr = new RitCusriReport();
        rcr.setCusNo(0L);
        rcr.setRightEntno("");
        rcr.setConfigTp("");
        rcr.setConfigObj("");
        rcr.setMblno("");
        rcr.setCertTp("");
        rcr.setCertNo("");
        rcr.setRightNo("");
        rcr.setCusriSt("");
        rcr.setExpDt("");
        rcr.setChgAcdt("");
        rcr.setChgDt("");
        rcr.setChgTm("");
        //insert into rit_cusri_report (cus_no,right_entno,config_tp,config_obj,mblno,cert_tp,cert_no,right_no,cusri_st,exp_dt,chg_acde,chg_dt,chg_tm)
        // values
        // (100000,1100000000000000,'CUSTOMRIGHT_FLG','CUSTOMRIGHT_CARDTYPE','13412345634','card','445738123485769879',);


    }
}
