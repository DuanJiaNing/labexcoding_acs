package com.acs.admin.api;

import com.acs.admin.common.PageCondition;
import com.acs.admin.common.PageModel;
import com.acs.admin.common.dto.PermissionDTO;
import com.acs.admin.service.PermissionService;
import com.acs.admin.utils.Results;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    @RequiresPermissions("permission:list")
    @ApiOperation("权限列表")
    @GetMapping("/permissions")
    public ResponseEntity<PageModel<PermissionDTO>> list(@RequestParam(required = false) Integer pageSize,
                                                         @RequestParam(required = false) Integer pageNum) {
        PageCondition pc = new PageCondition(pageNum, pageSize);
        PageModel<PermissionDTO> pageModel = permissionService.listAllPermission(pc);
        return Results.success(pageModel);
    }

}
