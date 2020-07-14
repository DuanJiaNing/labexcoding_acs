package com.acs.admin.common;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class PageModel<D> implements Serializable {

    private int pageNum;
    private int pageSize;
    private long total;
    private int pages;
    private List<D> list;
}
