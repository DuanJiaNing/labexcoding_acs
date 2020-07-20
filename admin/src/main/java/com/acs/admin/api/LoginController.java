package com.acs.admin.api;

import com.acs.admin.utils.Results;
import io.swagger.annotations.ApiOperation;
import lombok.Data;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @PostMapping("/login")
    @ApiOperation("登录")
    public ResponseEntity login(@RequestBody LoginRequest req) {
        // TODO
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(req.getUsername(), req.getPassword());
        try {
            subject.login(token);
            return Results.success();
        } catch (UnknownAccountException e) {
            e.printStackTrace();
            return Results.error("用户不存在");
        } catch (IncorrectCredentialsException e) {
            e.printStackTrace();
            return Results.error("密码错误");
        } catch (AuthenticationException e) {
            e.printStackTrace();
            return Results.error("登录失败");
        }
    }

    @Data
    public static class LoginRequest {
        private String username;
        private String password;
    }
}