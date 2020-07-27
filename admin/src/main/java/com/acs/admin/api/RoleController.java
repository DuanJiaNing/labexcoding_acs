package com.acs.admin.api;

import com.acs.admin.common.PageCondition;
import com.acs.admin.common.PageModel;
import com.acs.admin.ds.entity.SysRole;
import com.acs.admin.service.RoleService;
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
public class RoleController {

    @Autowired
    private RoleService roleService;

    @RequiresPermissions("role:list")
    @ApiOperation("角色列表")
    @GetMapping("/roles")
    public ResponseEntity<PageModel<SysRole>> list(@RequestParam Integer pageSize, @RequestParam Integer pageNum) {
        PageCondition pc = new PageCondition(pageNum, pageSize);
        PageModel<SysRole> pageModel = roleService.listAllRole(pc);
        return Results.success(pageModel);
    }

}
