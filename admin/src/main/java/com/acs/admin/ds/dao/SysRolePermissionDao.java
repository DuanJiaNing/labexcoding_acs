package com.acs.admin.ds.dao;
import com.acs.admin.ds.entity.SysRolePermission;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysRolePermissionDao extends BaseDao<SysRolePermission> {

    void deleteByRoleId(Integer roleId);

    int insertBatch(@Param("rps") List<SysRolePermission> rps);
}
