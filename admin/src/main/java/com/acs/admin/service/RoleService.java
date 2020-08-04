package com.acs.admin.service;

import com.acs.admin.common.PageCondition;
import com.acs.admin.common.PageModel;
import com.acs.admin.common.dto.PermissionDTO;
import com.acs.admin.common.dto.RoleDTO;

import java.util.List;

public interface RoleService {

    PageModel<RoleDTO> listAllRole(PageCondition condition);

    RoleDTO create(String roleName);

    RoleDTO update(String roleName, Integer roleId);

    void delete(Integer roleId);

    PageModel<PermissionDTO> listRolePermissions(Integer roleId, PageCondition condition);

    void assignPermissions(Integer roleId, List<Integer> permisIdList);
}
