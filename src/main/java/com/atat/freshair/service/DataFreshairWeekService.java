package com.atat.freshair.service;

import com.github.pagehelper.PageInfo;
import com.atat.freshair.bean.DataFreshairWeek;

import java.util.List;
import java.util.Map;

/**
 *
 * @author whaosoft
 *
 */
public interface DataFreshairWeekService {

    /**
     * 添加 空气监测设备每周数据表
     * @param dataFreshairWeek
     */
    public void  addDataFreshairWeek(DataFreshairWeek dataFreshairWeek);

    /**
     * 依据主键更新 空气监测设备每周数据表
     * @param dataFreshairWeek
     */
    public void  updateDataFreshairWeekById(DataFreshairWeek dataFreshairWeek);

    /**
     * 依据条件查找 空气监测设备每周数据表 列表
     * @param param
     * @return
     */
    public List<Map<String, Object>> selectDataFreshairWeekList(Map<String, Object> param);

    /**
     * 依据条件查找分页 空气监测设备每周数据表 列表
     * @param param
     * @param pageNo
     * @param pageSize
     * @return
     */
    public PageInfo<Map<String, Object>> getDataFreshairWeekPageTurn(Map<String, Object> param, Integer pageNo, Integer pageSize);

    /**
     * 依据Id删除 空气监测设备每周数据表 记录
     * @param dataFreshairWeekId
     */
    public void delDataFreshairWeekById(Long dataFreshairWeekId);

    /**
     * 获取设备  一年内 周节点数据
     * @param tabDeviceFreshairId
     * @return
     */
    public List<Map<String, Object>> getOneYearDeviceData(Long tabDeviceFreshairId);
}
