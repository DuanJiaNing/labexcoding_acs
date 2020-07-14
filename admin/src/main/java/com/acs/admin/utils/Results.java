package com.acs.admin.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.Serializable;

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

    public static <T extends Serializable> ResponseEntity<T> success(T data) {
        ResponseEntity<T> rm = new ResponseEntity<T>(data, HttpStatus.OK);
        return rm;
    }
}
