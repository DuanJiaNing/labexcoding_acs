package com.acs.admin.ds.dao;

import com.acs.admin.ds.entity.SysUser;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysUserDao extends BaseDao<SysUser> {

    SysUser findByUsername(String username);

    List<String> findAllPermissions(String username);
}
