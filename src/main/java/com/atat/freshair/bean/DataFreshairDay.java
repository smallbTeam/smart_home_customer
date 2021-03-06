package com.atat.freshair.bean;

/**
 * 空气监测设备每日数据表
 * @author wuhaosoft
 * @version $Id DataFreshairDay.java, 2017-08-11 17:34:10 wuhaosoft Exp
 *
 */
public class DataFreshairDay {

    //采集时间
    private Long  dataFreshairDayId;

    //设备Id
    private Long tabDeviceFreshairId;

    //温度
    private Double wendu;

    //湿度
    private Double shidu;

    //pm2.5
    private Double pm;

    //voc
    private Double voc;

    //CO2
    private Double co2;

    public DataFreshairDay(){}

    public DataFreshairDay(
        Long  dataFreshairDayId,
        Long tabDeviceFreshairId,
        Double wendu,
        Double shidu,
        Double pm,
        Double voc,
        Double co2
    ) {
        this.tabDeviceFreshairId = tabDeviceFreshairId;
        this.wendu = wendu;
        this.shidu = shidu;
        this.pm = pm;
        this.voc = voc;
        this.co2 = co2;
    }

    public Long  getDataFreshairDayId(){
        return dataFreshairDayId;
    }

    public void setDataFreshairDayId(Long  dataFreshairDayId) {
        this.dataFreshairDayId = dataFreshairDayId;
    }


    public Long getTabDeviceFreshairId() {
        return tabDeviceFreshairId;
    }

    public void setTabDeviceFreshairId(Long  tabDeviceFreshairId) {
        this.tabDeviceFreshairId = tabDeviceFreshairId;
    }

    public Double getWendu() {
        return wendu;
    }

    public void setWendu(Double  wendu) {
        this.wendu = wendu;
    }

    public Double getShidu() {
        return shidu;
    }

    public void setShidu(Double  shidu) {
        this.shidu = shidu;
    }

    public Double getPm() {
        return pm;
    }

    public void setPm(Double  pm) {
        this.pm = pm;
    }

    public Double getVoc() {
        return voc;
    }

    public void setVoc(Double  voc) {
        this.voc = voc;
    }

    public Double getCo2() {
        return co2;
    }

    public void setCo2(Double  co2) {
        this.co2 = co2;
    }

}



