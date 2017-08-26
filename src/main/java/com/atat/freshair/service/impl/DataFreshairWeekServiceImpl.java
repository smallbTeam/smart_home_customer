package com.atat.freshair.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.atat.freshair.action.FreshAirDataFormate;
import com.atat.freshair.bean.DataFreshairWeek;
import com.atat.freshair.dao.DataFreshairWeekDao;
import com.atat.freshair.service.DataFreshairWeekService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 *
 * @author whaosoft
 *
 */
@Service
@Transactional
public class DataFreshairWeekServiceImpl implements DataFreshairWeekService {

    @Autowired
    private DataFreshairWeekDao dataFreshairWeekDao;

    private String daoNamespace = DataFreshairWeekDao.class.getName();

    @Override
    public void  addDataFreshairWeek(DataFreshairWeek dataFreshairWeek) {
        dataFreshairWeekDao.addDataFreshairWeek(dataFreshairWeek);
    }

    @Override
    public void  updateDataFreshairWeekById(DataFreshairWeek dataFreshairWeek) {
        dataFreshairWeekDao.updateDataFreshairWeekById(dataFreshairWeek);
    }

    @Override
    public List<Map<String, Object>> selectDataFreshairWeekList(Map<String, Object> param) {
        return dataFreshairWeekDao.selectDataFreshairWeekList(param);
    }

    @Override
    public PageInfo<Map<String, Object>> getDataFreshairWeekPageTurn(Map<String, Object> param, Integer pageNo, Integer pageSize) {
        pageNo = pageNo == null?1:pageNo;
        pageSize = pageSize == null?10:pageSize;
        PageHelper.startPage(pageNo, pageSize);
        List<Map<String,Object>> list = dataFreshairWeekDao.selectDataFreshairWeekList(param);
        //用PageInfo对结果进行包装
        PageInfo<Map<String,Object>> page = new PageInfo<Map<String,Object>>(list);
        return page;
    }

    @Override
    public void delDataFreshairWeekById(Long dataFreshairWeekId) {

     //DataFreshairWeek dataFreshairWeek = new DataFreshairWeek();
     //dataFreshairWeek.setIsDeleted(1);
     //dataFreshairWeek.setDataFreshairWeekId(dataFreshairWeekId);
     //dataFreshairWeekDao.updateDataFreshairWeekById(dataFreshairWeek);

        dataFreshairWeekDao.delDataFreshairWeekById(dataFreshairWeekId);
    }

    @Override public List<Map<String, Object>> getOneYearDeviceData(Long tabDeviceFreshairId) {
        List<Map<String, Object>> categoryParameter = new ArrayList<Map<String, Object>>();
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("tabDeviceFreshairId", tabDeviceFreshairId);
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.YEAR, -1);
        param.put("recordTimeStart", cal.getTime().getTime());
        List<Map<String, Object>> deviceOneMonthData = dataFreshairWeekDao.selectDataFreshairWeekList(param);
        categoryParameter = FreshAirDataFormate.formateDataForEchar(deviceOneMonthData);
        return categoryParameter;
    }
}
