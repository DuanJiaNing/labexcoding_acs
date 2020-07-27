package com.acs.admin.service;

import com.acs.admin.ds.dao.SysUserDao;
import com.acs.admin.ds.entity.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private SysUserDao sysUserDao;

    public Integer findUserID(String username) {
        SysUser user = sysUserDao.findByUsername(username);
        if (user == null) {
            return null;
        }

        return user.getId();
    }

}
