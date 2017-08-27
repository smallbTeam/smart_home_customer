package com.atat.freshair.service.impl;

import com.atat.common.bean.JsonResult;
import com.atat.deviceGroup.service.RelCustomerDeviceGroupService;
import com.atat.freshair.bean.TabDeviceFreshair;
import com.atat.freshair.dao.TabDeviceFreshairDao;
import com.atat.freshair.service.TabDeviceFreshairService;
import com.atat.util.CollectionUtil;
import com.atat.util.JsonUtil;
import com.atat.util.httpClient.URLUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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
    private RelCustomerDeviceGroupService relCustomerDeviceGroupService;

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
        pageNo = pageNo == null ? 1 : pageNo;
        pageSize = pageSize == null ? 10 : pageSize;
        PageHelper.startPage(pageNo, pageSize);
        List<Map<String, Object>> list = tabDeviceFreshairDao.selectTabDeviceFreshairList(param);
        // 用PageInfo对结果进行包装
        PageInfo<Map<String, Object>> page = new PageInfo<Map<String, Object>>(list);
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

    @Override
    public Map<String, Object> getFreshairNowData(String deviceSeriaNumber) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        // http请求设备服务 获取Ip下所有设备及分组信息
        String basePase = deviceService.getProperty("basePase");
        String url = basePase + "/freshair/freshairNowData/" + deviceSeriaNumber;
        String resultstr = "";
        Map<String, String> paramMap = new HashMap<>();

        Map<String, Object> getmap = new HashMap<String, Object>();
        getmap.put("wendu", 27.77);
        getmap.put("shidu", 50.25);
        getmap.put("pm", 13);
        getmap.put("co2", 2);
        getmap.put("voc", 414);
        try {
            resultstr = URLUtil.originalGetData(url, paramMap);
            JsonResult<Map<String, Object>> result = new JsonResult<Map<String, Object>>();
            result = JsonUtil.fromJson(resultstr, result.getClass());
            if (((Integer)0).equals(result.getCode())){
                resultMap = result.getObj();
                return resultMap;
            } else {
                return getmap;
                //return null;
            }
        } catch (Exception e) {
            // 测试数据
            return getmap;
            // return null;
        }
    }

    @Override
    public Integer CustomerUpdateTabDeviceFreshair(Long tabCustomerId, TabDeviceFreshair tabDeviceFreshair) {
        // 用户是否有权限修改
        Map<String, Object> getFreshairInfoMap = new HashMap<String, Object>();
        getFreshairInfoMap.put("tabDeviceFreshairId", tabDeviceFreshair.getTabDeviceFreshairId());
        List<Map<String, Object>> tabDeviceFreshairList = tabDeviceFreshairDao.selectTabDeviceFreshairList(getFreshairInfoMap);
        if ((null != tabDeviceFreshairList) && (tabDeviceFreshairList.size() > 0)) {
            Map<String, Object> tabDeviceFreshairinfo = tabDeviceFreshairList.get(0);
            // 获取groupId
            Long tabDeviceGroupId = Long.parseLong(tabDeviceFreshairinfo.get("tabDeviceGroupId").toString());
            // 检验用户是否有权限修改设备信息
            Map<String, Object> rs = new HashMap<String, Object>();
            rs.put("tabCustomerId", tabCustomerId);
            rs.put("tabDeviceGroupId", tabDeviceGroupId);
            List<Map<String, Object>> relCustomerDeviceGroupList = relCustomerDeviceGroupService.selectRelCustomerDeviceGroupList(rs);
            if (CollectionUtil.isNotEmpty(relCustomerDeviceGroupList)) {
                // 获取用户与设备所在分组关系 是否为拥有着
                Map<String, Object> relCustomerDeviceGroup = relCustomerDeviceGroupList.get(0);
                Integer isOnwer = (Integer) relCustomerDeviceGroup.get("isOnwer");
                if (((Integer) 1).equals(isOnwer)) {
                    tabDeviceFreshairDao.updateTabDeviceFreshairById(tabDeviceFreshair);
                    return 1;
                }
                else {
                    return 0;
                }
            }
            else {
                // 用户未拥有设备所在分组
                return -1;
            }
        }
        else {
            // 设备不存在
            return -1;
        }
    }
}
