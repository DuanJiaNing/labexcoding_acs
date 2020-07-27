package com.acs.admin.ds.dao;
import com.acs.admin.ds.entity.SysRole;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysRoleDao extends BaseDao<SysRole> {

    List<SysRole> findAll();
}
