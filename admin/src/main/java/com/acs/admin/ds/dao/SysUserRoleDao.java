package com.acs.admin.ds.dao;
import com.acs.admin.ds.entity.SysUserRole;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysUserRoleDao extends BaseDao<SysUserRole> {

    void deleteByUserId(Integer userId);

    int insertBatch(@Param("rps") List<SysUserRole> rps);
}
