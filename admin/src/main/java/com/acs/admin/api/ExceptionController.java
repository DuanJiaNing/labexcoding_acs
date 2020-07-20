package com.acs.admin.api;

import com.acs.admin.utils.Results;
import com.acs.admin.common.exception.ServiceException;
import com.acs.admin.common.exception.UserInputException;
import lombok.extern.slf4j.Slf4j;
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
}