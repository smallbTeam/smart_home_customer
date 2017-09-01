package com.atat.freshair.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.atat.freshair.action.FreshAirDataFormate;
import com.atat.freshair.bean.DataFreshairNow;
import com.atat.freshair.dao.DataFreshairHourDao;
import com.atat.freshair.dao.DataFreshairNowDao;
import com.atat.freshair.service.DataFreshairNowService;
import com.atat.util.CollectionUtil;
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
public class DataFreshairNowServiceImpl implements DataFreshairNowService {

    @Autowired
    private DataFreshairNowDao dataFreshairNowDao;

    @Autowired
    private DataFreshairHourDao dataFreshairHourDao;

    private String daoNamespace = DataFreshairNowDao.class.getName();

    @Override
    public void  addDataFreshairNow(DataFreshairNow dataFreshairNow) {
        dataFreshairNowDao.addDataFreshairNow(dataFreshairNow);
    }

    @Override
    public void  updateDataFreshairNowById(DataFreshairNow dataFreshairNow) {
        dataFreshairNowDao.updateDataFreshairNowById(dataFreshairNow);
    }

    @Override
    public List<Map<String, Object>> selectDataFreshairNowList(Map<String, Object> param) {
        return dataFreshairNowDao.selectDataFreshairNowList(param);
    }

    @Override
    public PageInfo<Map<String, Object>> getDataFreshairNowPageTurn(Map<String, Object> param, Integer pageNo, Integer pageSize) {
        pageNo = pageNo == null?1:pageNo;
        pageSize = pageSize == null?10:pageSize;
        PageHelper.startPage(pageNo, pageSize);
        List<Map<String,Object>> list = dataFreshairNowDao.selectDataFreshairNowList(param);
        //用PageInfo对结果进行包装
        PageInfo<Map<String,Object>> page = new PageInfo<Map<String,Object>>(list);
        return page;
    }

    @Override
    public void delDataFreshairNowById(Long dataFreshairNowId) {

     //DataFreshairNow dataFreshairNow = new DataFreshairNow();
     //dataFreshairNow.setIsDeleted(1);
     //dataFreshairNow.setDataFreshairNowId(dataFreshairNowId);
     //dataFreshairNowDao.updateDataFreshairNowById(dataFreshairNow);

        dataFreshairNowDao.delDataFreshairNowById(dataFreshairNowId);

    }

    @Override
    public void timingFormateForThreeHour() {
        //分别计算前六小时到前三小时内的平均值
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.HOUR, -6);
        List<Map<String, Object>> deviceDataList = new ArrayList<Map<String, Object>>();
        for (int i=0;i<3;i++){
            Long recordTimeEnd = cal.getTime().getTime();
            cal.add(Calendar.HOUR, 1);
            Long recordTimeStart = cal.getTime().getTime();
            Map<String, Object> param_oneHour = new HashMap<String, Object>();
            param_oneHour.put("recordTimeStart",recordTimeStart);
            param_oneHour.put("recordTimeEnd",recordTimeEnd);
            deviceDataList.addAll(dataFreshairNowDao.timingNowAverageData(param_oneHour));
        }
        //存入小时表
        if (CollectionUtil.isNotEmpty(deviceDataList)){
            dataFreshairHourDao.addDataHourList(deviceDataList);
        }
        //移除now表之前的数据
        //cal.add(Calendar.HOUR, 3);
        dataFreshairNowDao.delDataNowByEndTime(cal.getTime().getTime());
    }

    @Override public Map<String, Object> getThreeHourDeviceData(Long tabDeviceFreshairId,String code) {
        Map<String, Object> categoryParameter = new HashMap<String, Object>();
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("tabDeviceFreshairId", tabDeviceFreshairId);
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.HOUR, -3);
        param.put("recordTimeStart", cal.getTime().getTime());
        List<Map<String, Object>> deviceThreeHourData = dataFreshairNowDao.selectDataFreshairNowList(param);
        categoryParameter = FreshAirDataFormate.formateDataForEchar(deviceThreeHourData,code);
        return categoryParameter;
    }
}
