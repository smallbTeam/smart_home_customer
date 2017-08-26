package com.atat.deviceGroup.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.atat.deviceGroup.bean.TabDeviceGroup;
import com.atat.deviceGroup.dao.TabDeviceGroupDao;
import com.atat.deviceGroup.service.TabDeviceGroupService;
import com.atat.freshair.service.TabDeviceFreshairService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author whaosoft
 */
@Service
@Transactional
public class TabDeviceGroupServiceImpl implements TabDeviceGroupService {

    @Autowired
    private TabDeviceGroupDao tabDeviceGroupDao;

    @Autowired
    private TabDeviceFreshairService tabDeviceFreshairService;

    private String daoNamespace = TabDeviceGroupDao.class.getName();

    @Override
    public void addTabDeviceGroup(TabDeviceGroup tabDeviceGroup) {
        tabDeviceGroupDao.addTabDeviceGroup(tabDeviceGroup);
    }

    @Override
    public void updateTabDeviceGroupById(TabDeviceGroup tabDeviceGroup) {
        tabDeviceGroupDao.updateTabDeviceGroupById(tabDeviceGroup);
    }

    @Override
    public List<Map<String, Object>> selectTabDeviceGroupList(Map<String, Object> param) {
        return tabDeviceGroupDao.selectTabDeviceGroupList(param);
    }

    @Override
    public PageInfo<Map<String, Object>> getTabDeviceGroupPageTurn(Map<String, Object> param, Integer pageNo,
            Integer pageSize) {
        pageNo = pageNo == null?1:pageNo;
        pageSize = pageSize == null?10:pageSize;
        PageHelper.startPage(pageNo, pageSize);
        List<Map<String,Object>> list = tabDeviceGroupDao.selectTabDeviceGroupList(param);
        //用PageInfo对结果进行包装
        PageInfo<Map<String,Object>> page = new PageInfo<Map<String,Object>>(list);
        return page;
    }

    @Override
    public Map<String, Object> getTabDeviceGroupById(Long tabDeviceGroupId) {
        Map<String, Object> groupinfo = new HashMap<String, Object>();
        //获取分组信息
        Map<String, Object> tabDeviceGroupinfo = new HashMap<String, Object>();
        Map<String, Object> rs = new HashMap<String, Object>();
        rs.put("tabDeviceGroupId", tabDeviceGroupId);
        List<Map<String, Object>> tabDeviceGroupList = tabDeviceGroupDao.selectTabDeviceGroupList(rs);
        if ((null != tabDeviceGroupList) && (tabDeviceGroupList.size() > 0)) {
            tabDeviceGroupinfo = tabDeviceGroupList.get(0);
        }
        groupinfo.put("tabDeviceGroupinfo",tabDeviceGroupinfo);
        //在空气检测设备下查找组设备
        List<Map<String, Object>> groupFreshairList = tabDeviceFreshairService.selectTabDeviceFreshairList(rs);
        groupinfo.put("groupFreshairList",groupFreshairList);
        return groupinfo;
    }

    @Override
    public void delTabDeviceGroupById(Long tabDeviceGroupId) {
        TabDeviceGroup tabDeviceGroup = new TabDeviceGroup();
        tabDeviceGroup.setModifiedDate(new Date());
        tabDeviceGroup.setIsDeleted(1);
        tabDeviceGroup.setTabDeviceGroupId(tabDeviceGroupId);
        tabDeviceGroupDao.updateTabDeviceGroupById(tabDeviceGroup);
    }
}
