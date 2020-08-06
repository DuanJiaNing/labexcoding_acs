package com.acs.admin.service;

import com.acs.admin.common.PageCondition;
import com.acs.admin.common.PageModel;
import com.acs.admin.common.dto.UserDTO;

public interface UserService {

    Integer findUserID(String username);

    PageModel<UserDTO> listAllUser(PageCondition condition);

    UserDTO create(String username, String password);

    UserDTO update(String username, Integer uid);

    void delete(Integer uid);
}
