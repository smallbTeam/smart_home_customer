package com.atat.freshair.service;

import com.github.pagehelper.PageInfo;
import com.atat.freshair.bean.DataFreshairDay;

import java.util.List;
import java.util.Map;

/**
 *
 * @author whaosoft
 *
 */
public interface DataFreshairDayService {

    /**
     * 添加 空气监测设备每日数据表
     * @param dataFreshairDay
     */
    public void  addDataFreshairDay(DataFreshairDay dataFreshairDay);

    /**
     * 依据主键更新 空气监测设备每日数据表
     * @param dataFreshairDay
     */
    public void  updateDataFreshairDayById(DataFreshairDay dataFreshairDay);

    /**
     * 依据条件查找 空气监测设备每日数据表 列表
     * @param param
     * @return
     */
    public List<Map<String, Object>> selectDataFreshairDayList(Map<String, Object> param);

    /**
     * 依据条件查找分页 空气监测设备每日数据表 列表
     * @param param
     * @param pageNo
     * @param pageSize
     * @return
     */
    public PageInfo<Map<String, Object>> getDataFreshairDayPageTurn(Map<String, Object> param, Integer pageNo, Integer pageSize);


    /**
     * 依据Id删除 空气监测设备每日数据表 记录
     * @param dataFreshairDayId
     */
    public void delDataFreshairDayById(Long dataFreshairDayId);

    /**
     * 空气监测设备 每周日表向周表导入一次数据
     */
    public void timingFormateForOneWeek();

    /**
     * 获取空气检测设备 天记录节点 一月的数据
     * @param tabDeviceFreshairId
     * @return
     */
    public Map<String, Object> getOneMounthDeviceData(Long tabDeviceFreshairId,String code);
}
