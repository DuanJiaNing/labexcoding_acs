package com.acs.admin.api;

import com.acs.admin.common.PageCondition;
import com.acs.admin.common.PageModel;
import com.acs.admin.common.dto.PermissionDTO;
import com.acs.admin.common.dto.RoleDTO;
import com.acs.admin.service.RoleService;
import com.acs.admin.utils.Results;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @RequiresPermissions("role:assign-permission")
    @ApiOperation("分配角色权限")
    @PutMapping("/role/{id}/permission")
    public ResponseEntity assignPermissions(@RequestBody List<Integer> permisIdList, @PathVariable Integer id) {
        roleService.assignPermissions(id, permisIdList);
        return Results.success();
    }

    @RequiresPermissions("role:permission:list")
    @ApiOperation("角色权限列表")
    @GetMapping("/role/{id}/permissions")
    public ResponseEntity<PageModel<PermissionDTO>> listPermissions(@PathVariable Integer id,
                                                                    @RequestParam(required = false) Integer pageSize,
                                                                    @RequestParam(required = false) Integer pageNum) {
        PageCondition pc = new PageCondition(pageNum, pageSize);
        PageModel<PermissionDTO> pageModel = roleService.listRolePermissions(id, pc);
        return Results.success(pageModel);
    }

    @RequiresPermissions("role:list")
    @ApiOperation("角色列表")
    @GetMapping("/roles")
    public ResponseEntity<PageModel<RoleDTO>> list(@RequestParam(required = false) Integer pageSize,
                                                   @RequestParam(required = false) Integer pageNum) {
        PageCondition pc = new PageCondition(pageNum, pageSize);
        PageModel<RoleDTO> pageModel = roleService.listAllRole(pc);
        return Results.success(pageModel);
    }

    @RequiresPermissions("role:add")
    @ApiOperation("创建角色")
    @PostMapping("/role")
    public ResponseEntity<RoleDTO> add(@RequestBody RoleDTO dto) {
        if (StringUtils.isBlank(dto.getRoleName())) {
            return Results.userInputError("角色名不能为空");
        }

        dto = roleService.create(dto.getRoleName());
        return Results.success(dto);
    }

    @RequiresPermissions("role:edit")
    @ApiOperation("编辑角色")
    @PutMapping("/role/{id}")
    public ResponseEntity<RoleDTO> edit(@RequestBody RoleDTO dto, @PathVariable Integer id) {
        if (StringUtils.isBlank(dto.getRoleName())) {
            return Results.userInputError("角色名不能为空");
        }

        dto = roleService.update(dto.getRoleName(), id);
        return Results.success(dto);
    }

    @RequiresPermissions("role:delete")
    @ApiOperation("删除角色")
    @DeleteMapping("/role/{id}")
    public ResponseEntity delete(@PathVariable Integer id) {
        roleService.delete(id);
        return Results.success();
    }
}
