package com.atat.deviceGroup.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.atat.common.bean.JsonResult;
import com.atat.deviceGroup.bean.TabDeviceGroup;
import com.atat.deviceGroup.service.TabDeviceGroupService;
import com.atat.util.CollectionUtil;
import com.atat.util.JsonUtil;
import com.atat.util.StringUtil;
import com.atat.util.httpClient.URLUtil;
import com.atat.util.weixinClient.CommonUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.atat.deviceGroup.bean.RelCustomerDeviceGroup;
import com.atat.deviceGroup.dao.RelCustomerDeviceGroupDao;
import com.atat.deviceGroup.service.RelCustomerDeviceGroupService;
import com.atat.freshair.service.TabDeviceFreshairService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @author whaosoft
 */
@Service
@Transactional
public class RelCustomerDeviceGroupServiceImpl implements RelCustomerDeviceGroupService {

    @Autowired
    private RelCustomerDeviceGroupDao relCustomerDeviceGroupDao;

    @Autowired
    private TabDeviceGroupService tabDeviceGroupService;

    @Autowired
    private Properties deviceService;

    @Autowired
    private TabDeviceFreshairService tabDeviceFreshairService;

    private Logger logger = LoggerFactory.getLogger(getClass());

    private String daoNamespace = RelCustomerDeviceGroupDao.class.getName();

    @Override
    public void addRelCustomerDeviceGroup(RelCustomerDeviceGroup relCustomerDeviceGroup) {
        relCustomerDeviceGroupDao.addRelCustomerDeviceGroup(relCustomerDeviceGroup);
    }

    @Override
    public void updateRelCustomerDeviceGroupById(RelCustomerDeviceGroup relCustomerDeviceGroup) {
        relCustomerDeviceGroupDao.updateRelCustomerDeviceGroupById(relCustomerDeviceGroup);
    }

    @Override
    public List<Map<String, Object>> selectRelCustomerDeviceGroupList(Map<String, Object> param) {
        return relCustomerDeviceGroupDao.selectRelCustomerDeviceGroupList(param);
    }

    @Override
    public PageInfo<Map<String, Object>> getRelCustomerDeviceGroupPageTurn(Map<String, Object> param, Integer pageNo,
            Integer pageSize) {
        pageNo = pageNo == null ? 1 : pageNo;
        pageSize = pageSize == null ? 10 : pageSize;
        PageHelper.startPage(pageNo, pageSize);
        List<Map<String, Object>> list = relCustomerDeviceGroupDao.selectRelCustomerDeviceGroupList(param);
        // 用PageInfo对结果进行包装
        PageInfo<Map<String, Object>> page = new PageInfo<Map<String, Object>>(list);
        return page;
    }

    @Override
    public Map<String, Object> getRelCustomerDeviceGroupById(Long relCustomerDeviceGroupId) {
        Map<String, Object> relCustomerDeviceGroupinfo = new HashMap<String, Object>();
        Map<String, Object> rs = new HashMap<String, Object>();
        rs.put("relCustomerDeviceGroupId", relCustomerDeviceGroupId);
        List<Map<String, Object>> relCustomerDeviceGroupList = relCustomerDeviceGroupDao.selectRelCustomerDeviceGroupList(rs);
        if ((null != relCustomerDeviceGroupList) && (relCustomerDeviceGroupList.size() > 0)) {
            relCustomerDeviceGroupinfo = relCustomerDeviceGroupList.get(0);
        }
        return relCustomerDeviceGroupinfo;
    }

    @Override
    public void delRelCustomerDeviceGroupById(Long relCustomerDeviceGroupId) {
        RelCustomerDeviceGroup relCustomerDeviceGroup = new RelCustomerDeviceGroup();
        relCustomerDeviceGroup.setIsDeleted(1);
        relCustomerDeviceGroup.setModifiedDate(new Date());
        relCustomerDeviceGroup.setRelCustomerDeviceGroupId(relCustomerDeviceGroupId);
        relCustomerDeviceGroupDao.updateRelCustomerDeviceGroupById(relCustomerDeviceGroup);
    }

    @Override
    public Map<String, Object> findDeviceByIp(String ip) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        // http请求设备服务 获取Ip下所有设备及分组信息
        String basePase = deviceService.getProperty("basePase");
        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("ip", ip);
        String url = basePase + "/deviceGroup/findDeviceByIp";
        String resultstr = "";
        try {
            resultstr = URLUtil.originalGetData(url, paramMap);
        }
        catch (Exception e) {
            return null;
        }
        JsonResult<Map<String, Object>> result = new JsonResult<Map<String, Object>>();
        result = JsonUtil.fromJson(resultstr, result.getClass());
        resultMap = result.getObj();
        // 筛选设备分组不为空的设备
        List<Map<String, String>> deviceList = (List<Map<String, String>>) resultMap.get("deviceList");
        for (Map<String, String> device : deviceList) {
            if (null != device.get("tabDeviceGroupId")
                    && StringUtil.isNotEmpty(device.get("tabDeviceGroupId").toString())) {
                deviceList.remove(device);
            }
        }
        resultMap.put("deviceList", deviceList);
        return resultMap;
    }

    @Override
    public Map<String, Object> customerAddNewGroup(Long customerId, String groupName, String address) {
        // 新建分组
        JsonResult<Object> result = new JsonResult<Object>();
        TabDeviceGroup tabDeviceGroup = new TabDeviceGroup();
        String uid = UUID.randomUUID().toString().replaceAll("-", "");
        tabDeviceGroup.setUid(uid);
        tabDeviceGroup.setAddress(address);
        tabDeviceGroup.setCreatedDate(new Date());
        tabDeviceGroupService.addTabDeviceGroup(tabDeviceGroup);
        // 依据uid查找刚创建的用户分组信息
        Map<String, Object> findGroupParam = new HashMap<String, Object>();
        findGroupParam.put("uid", uid);
        List<Map<String, Object>> groupList = tabDeviceGroupService.selectTabDeviceGroupList(findGroupParam);
        if (CollectionUtil.isNotEmpty(groupList)) {
            Map<String, Object> groupMap = groupList.get(0);
            //新建用户与分组绑定关系
            Long tabDeviceGroupId = Long.parseLong(groupMap.get("tabDeviceGroupId").toString());
            Integer isOnwer = 1;
            Integer isSendMsg = 1;
            RelCustomerDeviceGroup relCustomerDeviceGroup = new RelCustomerDeviceGroup();
            relCustomerDeviceGroup.setCustomerId(customerId);
            relCustomerDeviceGroup.setTabDeviceGroupId(tabDeviceGroupId);
            relCustomerDeviceGroup.setGroupName(groupName);
            relCustomerDeviceGroup.setIsOnwer(isOnwer);
            relCustomerDeviceGroup.setIsSendMsg(isSendMsg);
            relCustomerDeviceGroup.setCreatedDate(new Date());
            relCustomerDeviceGroupDao.addRelCustomerDeviceGroup(relCustomerDeviceGroup);
            //返回分组信息
            return groupMap;
        }
        else {
            return null;
        }
    }

    @Override public void groupBoundDevice(Long customerId, Long tabDeviceGroupId, String deviceSeriaNumberList) {

    }
}
