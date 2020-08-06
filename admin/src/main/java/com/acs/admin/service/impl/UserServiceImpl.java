package com.acs.admin.service.impl;

import com.acs.admin.common.PageCondition;
import com.acs.admin.common.PageModel;
import com.acs.admin.common.dto.UserDTO;
import com.acs.admin.common.exception.ServiceException;
import com.acs.admin.ds.dao.SysUserDao;
import com.acs.admin.ds.entity.SysUser;
import com.acs.admin.service.UserService;
import com.acs.admin.utils.DataConverter;
import com.acs.admin.utils.Results;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private SysUserDao sysUserDao;

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

}
