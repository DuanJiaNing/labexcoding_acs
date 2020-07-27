package com.acs.admin.common;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class PageCondition implements Serializable {

    public static final int DEFAULT_PAGE_SIZE = 5;
    public static final int DEFAULT_PAGE_NUM = 1;

    private int pageNum;
    private int pageSize;

    public PageCondition(Integer pageNum, Integer pageSize) {
        if (pageNum == null || pageNum <= 0) {
            pageNum = DEFAULT_PAGE_NUM;
        }
        this.pageNum = pageNum;

        if (pageSize == null || pageSize <= 0) {
            pageSize = DEFAULT_PAGE_SIZE;
        }
        this.pageSize = pageSize;
    }
}