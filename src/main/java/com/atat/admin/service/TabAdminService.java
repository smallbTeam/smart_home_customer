package com.atat.admin.service;

import com.github.pagehelper.PageInfo;
import com.atat.admin.bean.TabAdmin;

import java.util.List;
import java.util.Map;

/**
 *
 * @author whaosoft
 *
 */
public interface TabAdminService {

    /**
     * 添加 管理员表
     * @param tabAdmin
     */
    public void  addTabAdmin(TabAdmin tabAdmin);

    /**
     * 依据主键更新 管理员表
     * @param tabAdmin
     */
    public void  updateTabAdminById(TabAdmin tabAdmin);

    /**
     * 依据条件查找 管理员表 列表
     * @param param
     * @return
     */
    public List<Map<String, Object>> selectTabAdminList(Map<String, Object> param);

    /**
     * 依据条件查找分页 管理员表 列表
     * @param param
     * @param pageNo
     * @param pageSize
     * @return
     */
    public PageInfo<Map<String, Object>> getTabAdminPageTurn(Map<String, Object> param, Integer pageNo, Integer pageSize);

    /**
     * 依据Id查找 管理员表 详情
     * @param tabAdminId
     * @return
     */
    public Map<String, Object> getTabAdminById(Integer tabAdminId);

    /**
     * 依据Id删除 管理员表 记录
     * @param tabAdminId
     */
    public void delTabAdminById(Integer tabAdminId);
}
