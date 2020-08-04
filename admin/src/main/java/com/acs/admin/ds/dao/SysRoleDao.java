package com.acs.admin.ds.dao;

import com.acs.admin.common.dto.PermissionDTO;
import com.acs.admin.common.dto.RoleDTO;
import com.acs.admin.ds.entity.SysRole;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysRoleDao extends BaseDao<SysRole> {

    List<RoleDTO> findAll();

    List<PermissionDTO> findRolePermissions(Integer roleId);
}
