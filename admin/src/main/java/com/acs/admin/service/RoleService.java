package com.acs.admin.service;

import com.acs.admin.common.PageCondition;
import com.acs.admin.common.PageModel;
import com.acs.admin.ds.dao.SysRoleDao;
import com.acs.admin.ds.entity.SysRole;
import com.acs.admin.utils.Results;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    @Autowired
    private SysRoleDao sysRoleDao;

    public PageModel<SysRole> listAllRole(PageCondition condition) {
        PageHelper.startPage(condition.getPageNum(), condition.getPageSize());
        PageInfo<SysRole> pageInfo = new PageInfo<>(sysRoleDao.findAll());
        return Results.pageModel(pageInfo);
    }

}
