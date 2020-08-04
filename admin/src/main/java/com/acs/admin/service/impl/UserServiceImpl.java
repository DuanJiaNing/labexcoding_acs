package com.acs.admin.service.impl;

import com.acs.admin.ds.dao.SysUserDao;
import com.acs.admin.ds.entity.SysUser;
import com.acs.admin.service.UserService;
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

}
