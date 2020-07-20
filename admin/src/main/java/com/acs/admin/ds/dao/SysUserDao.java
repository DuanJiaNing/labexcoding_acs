package com.acs.admin.ds.dao;

import com.acs.admin.ds.entity.SysPermission;
import com.acs.admin.ds.entity.SysUser;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysUserDao extends BaseDao<SysUser> {

    SysUser findByUsername(String username);

    List<SysPermission> findAllPermissions(String username);
}
