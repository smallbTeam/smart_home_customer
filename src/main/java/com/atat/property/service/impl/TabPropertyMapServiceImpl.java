package com.atat.property.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.atat.property.bean.TabPropertyMap;
import com.atat.property.dao.TabPropertyMapDao;
import com.atat.property.service.TabPropertyMapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 *
 * @author whaosoft
 *
 */
@Service
@Transactional
public class TabPropertyMapServiceImpl implements TabPropertyMapService {

    @Autowired
    private TabPropertyMapDao tabPropertyMapDao;

    private String daoNamespace = TabPropertyMapDao.class.getName();

    @Override
    public void  addTabPropertyMap(TabPropertyMap tabPropertyMap) {
        tabPropertyMapDao.addTabPropertyMap(tabPropertyMap);
    }

    @Override
    public void  updateTabPropertyMapById(TabPropertyMap tabPropertyMap) {
        tabPropertyMapDao.updateTabPropertyMapById(tabPropertyMap);
    }

    @Override
    public List<Map<String, Object>> selectTabPropertyMapList(Map<String, Object> param) {
        return tabPropertyMapDao.selectTabPropertyMapList(param);
    }

    @Override
    public PageInfo<Map<String, Object>> getTabPropertyMapPageTurn(Map<String, Object> param, Integer pageNo, Integer pageSize) {
        pageNo = pageNo == null?1:pageNo;
        pageSize = pageSize == null?10:pageSize;
        PageHelper.startPage(pageNo, pageSize);
        List<Map<String,Object>> list =  tabPropertyMapDao.selectTabPropertyMapList(param);
        //用PageInfo对结果进行包装
        PageInfo<Map<String,Object>> page = new PageInfo<Map<String,Object>>(list);
        return page;
    }

    @Override
    public Map<String, Object> getTabPropertyMapById(String tabPropertyMapId) {
        Map<String, Object> tabPropertyMapinfo = new HashMap<String, Object>();
        Map<String, Object> rs = new HashMap<String, Object>();
        rs.put("tabPropertyMapId", tabPropertyMapId);
        List<Map<String, Object>> tabPropertyMapList = tabPropertyMapDao.selectTabPropertyMapList(rs);
        if ((null != tabPropertyMapList) && (tabPropertyMapList.size() > 0)) {
            tabPropertyMapinfo = tabPropertyMapList.get(0);
        }
        return tabPropertyMapinfo;
    }

    @Override
    public void delTabPropertyMapById(String tabPropertyMapId) {

     //TabPropertyMap tabPropertyMap = new TabPropertyMap();
     //tabPropertyMap.setIsDeleted(1);
     //tabPropertyMap.setTabPropertyMapId(tabPropertyMapId);
     //tabPropertyMapDao.updateTabPropertyMapById(tabPropertyMap);

        tabPropertyMapDao.delTabPropertyMapById(tabPropertyMapId);

    }

}
