package com.acs.admin.utils;

import org.apache.shiro.SecurityUtils;

public class Sessions {

    public static final Object KEY_UID = new Object();

    public static Integer getUID() {
        Object uid = SecurityUtils.getSubject().getSession().getAttribute(KEY_UID);
        return ((Integer) uid);
    }

    public static void setAttribute(Object key, Object value) {
        SecurityUtils.getSubject().getSession().setAttribute(key, value);
    }
}
