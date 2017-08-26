package com.atat.admin.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.atat.admin.bean.TabAdmin;
import com.atat.admin.dao.TabAdminDao;
import com.atat.admin.service.TabAdminService;
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
public class TabAdminServiceImpl implements TabAdminService {

    @Autowired
    private TabAdminDao tabAdminDao;

    private String daoNamespace = TabAdminDao.class.getName();

    @Override
    public void  addTabAdmin(TabAdmin tabAdmin) {
        tabAdminDao.addTabAdmin(tabAdmin);
    }

    @Override
    public void  updateTabAdminById(TabAdmin tabAdmin) {
        tabAdminDao.updateTabAdminById(tabAdmin);
    }

    @Override
    public List<Map<String, Object>> selectTabAdminList(Map<String, Object> param) {
        return tabAdminDao.selectTabAdminList(param);
    }

    @Override
    public PageInfo<Map<String, Object>> getTabAdminPageTurn(Map<String, Object> param, Integer pageNo, Integer pageSize) {
        pageNo = pageNo == null?1:pageNo;
        pageSize = pageSize == null?10:pageSize;
        PageHelper.startPage(pageNo, pageSize);
        List<Map<String,Object>> list =  tabAdminDao.selectTabAdminList(param);
        //用PageInfo对结果进行包装
        PageInfo<Map<String,Object>> page = new PageInfo<Map<String,Object>>(list);
        return page;
    }

    @Override
    public Map<String, Object> getTabAdminById(Integer tabAdminId) {
        Map<String, Object> tabAdmininfo = new HashMap<String, Object>();
        Map<String, Object> rs = new HashMap<String, Object>();
        rs.put("tabAdminId", tabAdminId);
        List<Map<String, Object>> tabAdminList = tabAdminDao.selectTabAdminList(rs);
        if ((null != tabAdminList) && (tabAdminList.size() > 0)) {
            tabAdmininfo = tabAdminList.get(0);
        }
        return tabAdmininfo;
    }

    @Override
    public void delTabAdminById(Integer tabAdminId) {

     //TabAdmin tabAdmin = new TabAdmin();
     //tabAdmin.setIsDeleted(1);
     //tabAdmin.setTabAdminId(tabAdminId);
     //tabAdminDao.updateTabAdminById(tabAdmin);

        tabAdminDao.delTabAdminById(tabAdminId);

    }

}
