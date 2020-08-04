package com.acs.admin.api;

import com.acs.admin.common.exception.ServiceException;
import com.acs.admin.common.exception.UserInputException;
import com.acs.admin.utils.Results;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler({UserInputException.class})
    @ResponseBody
    public ResponseEntity userInputException(UserInputException e) {
        log.warn(e.getMessage());
        return Results.userInputError(e.getMessage());
    }

    @ExceptionHandler({ServiceException.class})
    @ResponseBody
    public ResponseEntity serviceException(ServiceException e) {
        log.error("未知错误", e);
        return Results.error("未知错误");
    }

    @ExceptionHandler({AuthorizationException.class})
    @ResponseBody
    public ResponseEntity authorizationException(AuthorizationException e) {
        log.error("权限错误", e);
        return Results.accessDenied();
    }

    // 数据库(联合)唯一约束被违反，即试图插入重复数据
    @ExceptionHandler({DuplicateKeyException.class})
    @ResponseBody
    public ResponseEntity duplicateKeyException(DuplicateKeyException e) {
        String msg = e.getMessage();
        String mark = "SQLIntegrityConstraintViolationException";
        msg = msg.substring(msg.lastIndexOf(mark) + mark.length());
        return new ResponseEntity(msg, HttpStatus.CONFLICT);
    }
}