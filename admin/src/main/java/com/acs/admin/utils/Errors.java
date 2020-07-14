package com.acs.admin.utils;

import com.acs.admin.common.exception.ServiceException;
import com.acs.admin.common.exception.UserInputException;

public class Errors {

    public static UserInputException badRequest(String msg) {
        return new UserInputException(msg);
    }

    public static ServiceException internal(Throwable e) {
        return new ServiceException(e);
    }

    public static ServiceException db() {
        return new ServiceException("数据库操作异常");
    }

}
