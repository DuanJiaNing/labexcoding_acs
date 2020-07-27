package com.acs.admin.api;

import com.acs.admin.service.UserService;
import com.acs.admin.utils.Results;
import com.acs.admin.utils.Sessions;
import io.swagger.annotations.ApiOperation;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;

@RestController
public class LoginController {

    @Autowired
    private UserService userService;

    @Data
    public static class LoginRequest implements Serializable {
        private String username;
        private String password;
    }

    @GetMapping("/login.html")
    public String loginPage() {
        return "<p><h1>" +
                "模拟登录界面" +
                "</h1><h5>" +
                "有两种情况该页面被打开:" +
                "</h5><ul><li>" +
                "用户主动打开" +
                "</li><li>" +
                "无权限时自动跳转" +
                "</li></ul></p>";
    }

    @PostMapping("/api/login")
    @ApiOperation("登录")
    public ResponseEntity login(@RequestBody LoginRequest req) {
        if (StringUtils.isBlank(req.getUsername())) {
            return Results.userInputError("请输入用户名");
        }
        if (StringUtils.isBlank(req.getPassword())) {
            return Results.userInputError("请输入密码");
        }

        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()) {
            return Results.success();
        }

        UsernamePasswordToken token = new UsernamePasswordToken(req.getUsername(), req.getPassword());
        try {
            subject.login(token);

            Integer id = userService.findUserID(req.getUsername());
            Sessions.setAttribute(Sessions.KEY_UID, id);
            return Results.success();
        } catch (UnknownAccountException e) {
            return Results.userInputError("用户不存在");
        } catch (IncorrectCredentialsException e) {
            return Results.userInputError("密码错误");
        } catch (AuthenticationException e) {
            e.printStackTrace();
            return Results.error("登录失败");
        }
    }

    @PostMapping("/api/logout")
    @ApiOperation("登出")
    public ResponseEntity logout() {
        Subject subject = SecurityUtils.getSubject();
        if (!subject.isAuthenticated()) {
            return Results.error("用户未登录");
        }

        subject.logout();
        return Results.success();
    }
}