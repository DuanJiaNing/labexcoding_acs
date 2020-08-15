package com.acs.admin.ds.dao;

import com.acs.admin.common.dto.NotifyDTO;
import com.acs.admin.ds.entity.Notify;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotifyDao extends BaseDao<Notify> {

    List<NotifyDTO> findAll();

    NotifyDTO findById(Integer id);
}
