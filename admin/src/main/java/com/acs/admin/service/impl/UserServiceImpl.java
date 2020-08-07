package com.acs.admin.service.impl;

import com.acs.admin.common.PageCondition;
import com.acs.admin.common.PageModel;
import com.acs.admin.common.dto.UserDTO;
import com.acs.admin.common.exception.ServiceException;
import com.acs.admin.ds.dao.SysRoleDao;
import com.acs.admin.ds.dao.SysUserDao;
import com.acs.admin.ds.dao.SysUserRoleDao;
import com.acs.admin.ds.entity.SysUser;
import com.acs.admin.ds.entity.SysUserRole;
import com.acs.admin.service.UserService;
import com.acs.admin.utils.DataConverter;
import com.acs.admin.utils.Errors;
import com.acs.admin.utils.Results;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private SysUserDao sysUserDao;

    @Autowired
    private SysRoleDao sysRoleDao;

    @Autowired
    private SysUserRoleDao sysUserRoleDao;

    @Override
    public Integer findUserID(String username) {
        SysUser user = sysUserDao.findByUsername(username);
        if (user == null) {
            return null;
        }

        return user.getId();
    }

    @Override
    public PageModel<UserDTO> listAllUser(PageCondition condition) {
        PageHelper.startPage(condition.getPageNum(), condition.getPageSize());
        PageInfo<UserDTO> pageInfo = new PageInfo<>(sysUserDao.findAll());
        return Results.pageModel(pageInfo);
    }

    @Override
    public UserDTO create(String username, String password) {
        SysUser sr = new SysUser();
        sr.setUsername(username);
        sr.setPassword(password);
        if (1 != sysUserDao.insert(sr)) {
            throw new ServiceException("无法新增用户记录到数据库");
        }

        return DataConverter.map(sr, UserDTO.class);
    }

    @Override
    public UserDTO update(String username, Integer uid) {
        SysUser sr = new SysUser();
        sr.setUsername(username);
        sr.setId(uid);
        sysUserDao.update(sr);

        sr = sysUserDao.find(uid);
        return DataConverter.map(sr, UserDTO.class);
    }

    @Override
    public void delete(Integer uid) {
        if (1 != sysUserDao.delete(uid)) {
            throw new ServiceException("在数据库中删除角色记录失败");
        }
    }

    @Override
    public void assignRole(Integer userId, List<Integer> roleIdList) {
        if (sysUserDao.find(userId) == null) {
            throw Errors.badRequest("用户不存在");
        }
        if (!CollectionUtils.isEmpty(roleIdList)) {
            int count = sysRoleDao.countByIds(roleIdList);
            if (count != roleIdList.size()) {
                throw Errors.badRequest("角色不存在");
            }
        }
        sysUserRoleDao.deleteByUserId(userId);
        if (!CollectionUtils.isEmpty(roleIdList)) {
            List<SysUserRole> rps = roleIdList.stream().map(p -> {
                SysUserRole rp = new SysUserRole();
                rp.setRoleId(p);
                rp.setUserId(userId);
                return rp;
            }).collect(Collectors.toList());
            if (sysUserRoleDao.insertBatch(rps) != roleIdList.size()) {
                throw Errors.db();
            }
        }
    }

}
