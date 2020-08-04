package com.acs.admin.service.impl;

import com.acs.admin.common.PageCondition;
import com.acs.admin.common.PageModel;
import com.acs.admin.common.dto.PermissionDTO;
import com.acs.admin.common.dto.RoleDTO;
import com.acs.admin.common.exception.ServiceException;
import com.acs.admin.ds.dao.SysPermissionDao;
import com.acs.admin.ds.dao.SysRoleDao;
import com.acs.admin.ds.dao.SysRolePermissionDao;
import com.acs.admin.ds.entity.SysRole;
import com.acs.admin.ds.entity.SysRolePermission;
import com.acs.admin.service.RoleService;
import com.acs.admin.utils.DataConverter;
import com.acs.admin.utils.Errors;
import com.acs.admin.utils.Results;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private SysRoleDao sysRoleDao;

    @Autowired
    private SysPermissionDao sysPermissionDao;

    @Autowired
    private SysRolePermissionDao sysRolePermissionDao;

    @Override
    public PageModel<RoleDTO> listAllRole(PageCondition condition) {
        PageHelper.startPage(condition.getPageNum(), condition.getPageSize());
        PageInfo<RoleDTO> pageInfo = new PageInfo<>(sysRoleDao.findAll());
        return Results.pageModel(pageInfo);
    }

    @Transactional
    @Override
    public RoleDTO create(String roleName) {
        SysRole sr = new SysRole();
        sr.setRoleName(roleName);
        if (1 != sysRoleDao.insert(sr)) {
            throw new ServiceException("无法新增角色记录到数据库");
        }

        return DataConverter.map(sr, RoleDTO.class);
    }

    @Transactional
    @Override
    public RoleDTO update(String roleName, Integer roleId) {
        SysRole sr = new SysRole();
        sr.setRoleName(roleName);
        sr.setId(roleId);
        sysRoleDao.update(sr);

        sr = sysRoleDao.find(roleId);
        return DataConverter.map(sr, RoleDTO.class);
    }

    @Transactional
    @Override
    public void delete(Integer roleId) {
        if (1 != sysRoleDao.delete(roleId)) {
            throw new ServiceException("在数据库中删除角色记录失败");
        }
    }

    @Override
    public PageModel<PermissionDTO> listRolePermissions(Integer roleId, PageCondition condition) {
        PageHelper.startPage(condition.getPageNum(), condition.getPageSize());
        PageInfo<PermissionDTO> pageInfo = new PageInfo<>(sysRoleDao.findRolePermissions(roleId));
        return Results.pageModel(pageInfo);
    }

    @Transactional
    @Override
    public void assignPermissions(Integer roleId, List<Integer> permisIdList) {
        if (sysRoleDao.find(roleId) == null) {
            throw Errors.badRequest("角色不存在");
        }
        if (!CollectionUtils.isEmpty(permisIdList)) {
            int count = sysPermissionDao.countByIds(permisIdList);
            if (count != permisIdList.size()) {
                throw Errors.badRequest("权限不存在");
            }
        }
        sysRolePermissionDao.deleteByRoleId(roleId);
        if (!CollectionUtils.isEmpty(permisIdList)) {
            List<SysRolePermission> rps = permisIdList.stream().map(p -> {
                SysRolePermission rp = new SysRolePermission();
                rp.setRoleId(roleId);
                rp.setPermissionId(p);
                return rp;
            }).collect(Collectors.toList());
            if (sysRolePermissionDao.insertBatch(rps) != permisIdList.size()) {
                throw Errors.db();
            }
        }
    }
}
