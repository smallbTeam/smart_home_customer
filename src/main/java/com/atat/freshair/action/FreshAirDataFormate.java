/**
 * Company
 * Copyright (C) 2004-2017 All Rights Reserved.
 */
package com.atat.freshair.action;

import com.atat.util.CollectionUtil;

import java.util.*;

/**
 * @author ligw
 * @version $Id FreshAirDataFormate.java, v 0.1 2017-08-15 22:25 ligw Exp $$
 */
public class FreshAirDataFormate {

    public static List<Map<String, Object>> formateDataForEchar(List<Map<String, Object>> devicepartTimeData){
        //查找设备参数Id
        List<Map<String, Object>> categoryParameter = new ArrayList<Map<String, Object>>();
        Map<String, Object> wendu = new HashMap<String, Object>();
        Double shidu_max = Double.valueOf(-10000);
        Double shidu_min = Double.valueOf(10000);
        Double shidu_avg = Double.valueOf(0);
        Double shidu_sum = Double.valueOf(0);

        Double wendu_max = Double.valueOf(-10000);
        Double wendu_min = Double.valueOf(10000);
        Double wendu_avg = Double.valueOf(0);
        Double wendu_sum = Double.valueOf(0);

        Double pm_max = Double.valueOf(-10000);
        Double pm_min = Double.valueOf(10000);
        Double pm_avg = Double.valueOf(0);
        Double pm_sum = Double.valueOf(0);

        Double voc_max = Double.valueOf(-10000);
        Double voc_min = Double.valueOf(10000);
        Double voc_avg = Double.valueOf(0);
        Double voc_sum = Double.valueOf(0);

        Double co2_max = Double.valueOf(-10000);
        Double co2_min = Double.valueOf(10000);
        Double co2_avg = Double.valueOf(0);
        Double co2_sum = Double.valueOf(0);

        wendu.put("code","wendu");
        wendu.put("name","温度");
        wendu.put("unit","℃");
        Map<String, Object> shidu = new HashMap<String, Object>();
        shidu.put("code","shidu");
        shidu.put("name","湿度");
        shidu.put("unit","％");
        Map<String, Object> pm = new HashMap<String, Object>();
        pm.put("code","pm");
        pm.put("name","PM2.5");
        pm.put("unit","μg/m³");
        Map<String, Object> voc = new HashMap<String, Object>();
        voc.put("code","voc");
        voc.put("name","VOC");
        voc.put("unit","g/L");
        Map<String, Object> co2 = new HashMap<String, Object>();
        co2.put("code","co2");
        co2.put("name","CO2");
        co2.put("unit","ppm");
        //依次取出各变量
        List<Long> dateList = new ArrayList<Long>();
        List<Double> wendu_val_list = new ArrayList<Double>();
        List<Double> shidu_val_list = new ArrayList<Double>();
        List<Double> pm_val_list = new ArrayList<Double>();
        List<Double> voc_val_list = new ArrayList<Double>();
        List<Double> co2_val_list = new ArrayList<Double>();
        if (CollectionUtil.isNotEmpty(devicepartTimeData)){
            for (Map<String, Object> deviceData : devicepartTimeData) {
                dateList.add(Long.parseLong(deviceData.get("recordTime").toString()));
                Double wendu_val = (Double) deviceData.get("wendu");
                wendu_val_list.add(wendu_val);
                wendu_min = wendu_min>wendu_val?wendu_val:wendu_min;
                wendu_max = wendu_max>wendu_val?wendu_max:wendu_val;
                wendu_sum += wendu_val;
                Double shidu_val = (Double) deviceData.get("shidu");
                shidu_val_list.add(shidu_val);
                shidu_min = shidu_min>shidu_val?shidu_val:shidu_min;
                shidu_max = shidu_max>shidu_val?shidu_max:shidu_val;
                shidu_sum += shidu_val;
                Double pm_val = (Double) deviceData.get("pm");
                pm_val_list.add(pm_val);
                pm_min = pm_min>pm_val?pm_val:pm_min;
                pm_max = pm_max>pm_val?pm_max:pm_val;
                pm_sum += pm_val;
                Double voc_val = (Double) deviceData.get("voc");
                voc_val_list.add(voc_val);
                voc_min = voc_min>voc_val?voc_val:voc_min;
                voc_max = voc_max>voc_val?voc_max:voc_val;
                voc_sum += voc_val;
                Double co2_val = (Double) deviceData.get("co2");
                co2_val_list.add(co2_val);
                co2_min = co2_min>co2_val?co2_val:co2_min;
                co2_max = co2_max>co2_val?co2_max:co2_val;
                co2_sum += co2_val;
            }
            wendu.put("data",wendu_val_list);
            wendu_avg = wendu_sum/(wendu_val_list.size() != 0 ?wendu_val_list.size():1);
            wendu.put("recordTime",dateList);
            shidu.put("data",shidu_val_list);
            shidu_avg = shidu_sum/(shidu_val_list.size() != 0 ?shidu_val_list.size():1);
            shidu.put("recordTime",dateList);
            pm.put("data",pm_val_list);
            pm_avg = pm_sum/(pm_val_list.size() != 0 ?pm_val_list.size():1);
            pm.put("recordTime",dateList);
            voc.put("data",voc_val_list);
            voc_avg = voc_sum/(voc_val_list.size() != 0 ?voc_val_list.size():1);
            voc.put("recordTime",dateList);
            co2.put("data",co2_val_list);
            co2_avg = co2_sum/(co2_val_list.size() != 0 ?co2_val_list.size():1);
            co2.put("recordTime",dateList);
        }
        wendu.put("min",wendu_min);
        wendu.put("max",wendu_max);
        wendu.put("avg",wendu_avg);
        shidu.put("min",shidu_min);
        shidu.put("max",shidu_max);
        shidu.put("avg",shidu_avg);
        pm.put("min",pm_min);
        pm.put("max",pm_max);
        pm.put("avg",pm_avg);
        voc.put("min",voc_min);
        voc.put("max",voc_max);
        voc.put("avg",voc_avg);
        co2.put("min",co2_min);
        co2.put("max",co2_max);
        co2.put("avg",co2_avg);
        categoryParameter.add(wendu);
        categoryParameter.add(shidu);
        categoryParameter.add(pm);
        categoryParameter.add(voc);
        categoryParameter.add(co2);
        return categoryParameter;
    }
}
