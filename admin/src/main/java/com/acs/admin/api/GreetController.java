package com.acs.admin.api;

import com.acs.admin.utils.Results;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetController {

    @GetMapping("/greet")
    @ApiOperation("打个招呼")
    public ResponseEntity greet() {
        return Results.success("hello world");
    }

}
