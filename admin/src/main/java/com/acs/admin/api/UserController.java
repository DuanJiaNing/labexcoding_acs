package com.acs.admin.api;

import com.acs.admin.common.PageCondition;
import com.acs.admin.common.PageModel;
import com.acs.admin.common.dto.UserDTO;
import com.acs.admin.service.UserService;
import com.acs.admin.utils.Results;
import io.swagger.annotations.ApiOperation;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    @RequiresPermissions("user:assign-role")
    @ApiOperation("用户角色分配")
    @PutMapping("/user/{id}/role")
    public ResponseEntity assignRole(@RequestBody List<Integer> roleIdList, @PathVariable Integer id) {
        userService.assignRole(id, roleIdList);
        return Results.success();
    }

    @RequiresPermissions("user:list")
    @ApiOperation("用户列表")
    @GetMapping("/users")
    public ResponseEntity<PageModel<UserDTO>> list(@RequestParam(required = false) Integer pageSize,
                                                   @RequestParam(required = false) Integer pageNum) {
        PageCondition pc = new PageCondition(pageNum, pageSize);
        PageModel<UserDTO> pageModel = userService.listAllUser(pc);
        return Results.success(pageModel);
    }

    @Data
    public static class AddUserRequest implements Serializable {
        private String username;
        private String password;
    }

    @RequiresPermissions("user:add")
    @ApiOperation("创建用户")
    @PostMapping("/user")
    public ResponseEntity<UserDTO> add(@RequestBody AddUserRequest req) {
        if (StringUtils.isBlank(req.getUsername())) {
            return Results.userInputError("用户名不能为空");
        }
        if (StringUtils.isBlank(req.getPassword())) {
            return Results.userInputError("用户密码不能为空");
        }

        UserDTO dto = userService.create(req.getUsername(), req.getPassword());
        return Results.success(dto);
    }

    @RequiresPermissions("user:edit")
    @ApiOperation("编辑用户")
    @PutMapping("/user/{id}")
    public ResponseEntity<UserDTO> edit(@RequestBody UserDTO dto, @PathVariable Integer id) {
        if (StringUtils.isBlank(dto.getUsername())) {
            return Results.userInputError("用户名不能为空");
        }

        dto = userService.update(dto.getUsername(), id);
        return Results.success(dto);
    }

    @RequiresPermissions("user:delete")
    @ApiOperation("删除用户")
    @DeleteMapping("/user/{id}")
    public ResponseEntity delete(@PathVariable Integer id) {
        userService.delete(id);
        return Results.success();
    }
}
