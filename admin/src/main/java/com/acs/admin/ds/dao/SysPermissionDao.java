package com.acs.admin.ds.dao;
import com.acs.admin.common.dto.PermissionDTO;
import com.acs.admin.ds.entity.SysPermission;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysPermissionDao extends BaseDao<SysPermission> {

    int countByIds(@Param("permisIdList") List<Integer> permisIdList);

    List<PermissionDTO> findAll();
}
