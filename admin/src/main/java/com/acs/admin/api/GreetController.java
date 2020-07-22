package com.acs.admin.api;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api")
public class GreetController {

    @GetMapping("/greet") // 从 "/" 修改为 "/greet"
    @ApiOperation("打个招呼") // 对接口进行说明
    @RequiresPermissions("role:add")
    public String greet() {
        return "hello world";
    }
}