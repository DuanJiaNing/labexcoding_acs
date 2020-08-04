package com.acs.admin.service;

import com.acs.admin.common.PageCondition;
import com.acs.admin.common.PageModel;
import com.acs.admin.common.dto.PermissionDTO;

public interface PermissionService {

    PageModel<PermissionDTO> listAllPermission(PageCondition condition);
}
