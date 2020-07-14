package com.acs.admin.ds.dao;

public interface BaseDao<T> {

    T find(Integer id);

    int delete(Integer id);

    int insert(T t);

    int update(T t);
}
