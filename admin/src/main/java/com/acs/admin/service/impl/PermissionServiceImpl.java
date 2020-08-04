package com.acs.admin.service.impl;

import com.acs.admin.common.PageCondition;
import com.acs.admin.common.PageModel;
import com.acs.admin.common.dto.PermissionDTO;
import com.acs.admin.ds.dao.SysPermissionDao;
import com.acs.admin.service.PermissionService;
import com.acs.admin.utils.Results;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private SysPermissionDao sysPermissionDao;

    @Override
    public PageModel<PermissionDTO> listAllPermission(PageCondition condition) {
        PageHelper.startPage(condition.getPageNum(), condition.getPageSize());
        PageInfo<PermissionDTO> pageInfo = new PageInfo<>(sysPermissionDao.findAll());
        return Results.pageModel(pageInfo);
    }
}
