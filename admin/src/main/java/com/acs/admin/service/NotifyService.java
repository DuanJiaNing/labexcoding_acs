package com.acs.admin.service;

import com.acs.admin.common.PageCondition;
import com.acs.admin.common.PageModel;
import com.acs.admin.common.dto.NotifyDTO;

public interface NotifyService {

    PageModel<NotifyDTO> listAllNotify(PageCondition condition);

    NotifyDTO create(Integer userId, String content);

    NotifyDTO update(Integer notifyId, String content);

    void delete(Integer notifyId);

}
