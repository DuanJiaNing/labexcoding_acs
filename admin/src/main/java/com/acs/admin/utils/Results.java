package com.acs.admin.utils;

import com.acs.admin.common.PageModel;
import com.github.pagehelper.PageInfo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.util.ArrayList;

public class Results {

    public static ResponseEntity success() {
        ResponseEntity rm = new ResponseEntity(HttpStatus.OK);
        return rm;
    }

    public static ResponseEntity userInputError(String msg) {
        ResponseEntity rm = new ResponseEntity(msg, HttpStatus.BAD_REQUEST);
        return rm;
    }

    public static ResponseEntity error(String msg) {
        ResponseEntity rm = new ResponseEntity(msg, HttpStatus.INTERNAL_SERVER_ERROR);
        return rm;
    }

    public static ResponseEntity accessDenied() {
        ResponseEntity rm = new ResponseEntity("access denied", HttpStatus.FORBIDDEN);
        return rm;
    }

    public static <T extends Serializable> PageModel<T> pageModel(PageInfo<T> page) {
        if (page == null) {
            return new PageModel<>();
        }

        PageModel<T> pm = new PageModel<>();
        pm.setPageNum(page.getPageNum());
        pm.setPages(page.getPages());
        pm.setPageSize(page.getPageSize());
        pm.setTotal(page.getTotal());
        pm.setList(CollectionUtils.isEmpty(page.getList()) ? new ArrayList<>() : page.getList());

        return pm;
    }

    public static <T extends Serializable> ResponseEntity<T> success(T data) {
        ResponseEntity<T> rm = new ResponseEntity<T>(data, HttpStatus.OK);
        return rm;
    }
}
