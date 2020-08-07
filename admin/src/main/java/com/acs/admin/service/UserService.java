package com.acs.admin.service;

import com.acs.admin.common.PageCondition;
import com.acs.admin.common.PageModel;
import com.acs.admin.common.dto.UserDTO;

import java.util.List;

public interface UserService {

    Integer findUserID(String username);

    PageModel<UserDTO> listAllUser(PageCondition condition);

    UserDTO create(String username, String password);

    UserDTO update(String username, Integer uid);

    void delete(Integer uid);

    void assignRole(Integer id, List<Integer> roleIdList);
}
