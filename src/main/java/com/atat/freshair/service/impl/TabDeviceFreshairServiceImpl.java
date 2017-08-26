package com.atat.freshair.service.impl;

import com.atat.common.bean.JsonResult;
import com.atat.util.JsonUtil;
import com.atat.util.httpClient.URLUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.atat.freshair.bean.TabDeviceFreshair;
import com.atat.freshair.dao.TabDeviceFreshairDao;
import com.atat.freshair.service.TabDeviceFreshairService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @author whaosoft
 */
@Service
@Transactional
public class TabDeviceFreshairServiceImpl implements TabDeviceFreshairService {

    @Autowired
    private TabDeviceFreshairDao tabDeviceFreshairDao;

    @Autowired
    private Properties deviceService;

    private String daoNamespace = TabDeviceFreshairDao.class.getName();

    @Override
    public void addTabDeviceFreshair(TabDeviceFreshair tabDeviceFreshair) {
        tabDeviceFreshairDao.addTabDeviceFreshair(tabDeviceFreshair);
    }

    @Override
    public void updateTabDeviceFreshairById(TabDeviceFreshair tabDeviceFreshair) {
        tabDeviceFreshairDao.updateTabDeviceFreshairById(tabDeviceFreshair);
    }

    @Override
    public List<Map<String, Object>> selectTabDeviceFreshairList(Map<String, Object> param) {
        return tabDeviceFreshairDao.selectTabDeviceFreshairList(param);
    }

    @Override
    public PageInfo<Map<String, Object>> getTabDeviceFreshairPageTurn(Map<String, Object> param, Integer pageNo,
            Integer pageSize) {
        pageNo = pageNo == null?1:pageNo;
        pageSize = pageSize == null?10:pageSize;
        PageHelper.startPage(pageNo, pageSize);
        List<Map<String,Object>> list = tabDeviceFreshairDao.selectTabDeviceFreshairList(param);
        //用PageInfo对结果进行包装
        PageInfo<Map<String,Object>> page = new PageInfo<Map<String,Object>>(list);
        return page;
    }

    @Override
    public Map<String, Object> getTabDeviceFreshairById(Long tabDeviceFreshairId) {
        Map<String, Object> tabDeviceFreshairinfo = new HashMap<String, Object>();
        Map<String, Object> rs = new HashMap<String, Object>();
        rs.put("tabDeviceFreshairId", tabDeviceFreshairId);
        List<Map<String, Object>> tabDeviceFreshairList = tabDeviceFreshairDao.selectTabDeviceFreshairList(rs);
        if ((null != tabDeviceFreshairList) && (tabDeviceFreshairList.size() > 0)) {
            tabDeviceFreshairinfo = tabDeviceFreshairList.get(0);
        }
        return tabDeviceFreshairinfo;
    }

    @Override
    public void delTabDeviceFreshairById(Long tabDeviceFreshairId) {
         TabDeviceFreshair tabDeviceFreshair = new TabDeviceFreshair();
         tabDeviceFreshair.setIsDeleted(1);
         tabDeviceFreshair.setModifiedDate(new Date());
         tabDeviceFreshair.setTabDeviceFreshairId(tabDeviceFreshairId);
         tabDeviceFreshairDao.updateTabDeviceFreshairById(tabDeviceFreshair);
    }

    @Override
    public Map<String, Object> getTabDeviceFreshairByDeviceSeriaNumber(String deviceSeriaNumber) {
        Map<String, Object> tabDeviceFreshairinfo = new HashMap<String, Object>();
        Map<String, Object> rs = new HashMap<String, Object>();
        rs.put("deviceSeriaNumber", deviceSeriaNumber);
        List<Map<String, Object>> tabDeviceFreshairList = tabDeviceFreshairDao.selectTabDeviceFreshairList(rs);
        if ((null != tabDeviceFreshairList) && (tabDeviceFreshairList.size() > 0)) {
            tabDeviceFreshairinfo = tabDeviceFreshairList.get(0);
            return tabDeviceFreshairinfo;
        }
        else {
            return null;
        }
    }

    @Override public Map<String, Object> getFreshairNowData(String deviceSeriaNumber) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        //http请求设备服务 获取Ip下所有设备及分组信息
        String basePase = deviceService.getProperty("basePase");
        String url=basePase+"/freshair/freshairNowData/"+deviceSeriaNumber;
        String resultstr = "";
        Map<String, String> paramMap = new HashMap<>();
        try {
            resultstr = URLUtil.originalGetData(url,paramMap);
        } catch (Exception e) {
            return null;
        }
        JsonResult<Map<String,Object>> result = new JsonResult<Map<String,Object>>();
        result = JsonUtil.fromJson(resultstr,result.getClass());
        resultMap = result.getObj();
        return resultMap;
    }
}
