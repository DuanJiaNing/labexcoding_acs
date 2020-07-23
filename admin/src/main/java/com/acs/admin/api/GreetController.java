package com.acs.admin.api;

import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class GreetController {

    @GetMapping("/greet") // 从 "/" 修改为 "/greet"
    @ApiOperation("打个招呼") // 对接口进行说明
    public String greet() {
        return "hello world";
    }
}