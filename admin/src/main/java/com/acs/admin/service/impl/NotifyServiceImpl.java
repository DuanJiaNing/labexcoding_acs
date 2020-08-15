package com.acs.admin.service.impl;

import com.acs.admin.common.PageCondition;
import com.acs.admin.common.PageModel;
import com.acs.admin.common.dto.NotifyDTO;
import com.acs.admin.common.exception.ServiceException;
import com.acs.admin.ds.dao.NotifyDao;
import com.acs.admin.ds.dao.SysUserDao;
import com.acs.admin.ds.entity.Notify;
import com.acs.admin.service.NotifyService;
import com.acs.admin.utils.Errors;
import com.acs.admin.utils.Results;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class NotifyServiceImpl implements NotifyService {

    @Autowired
    private NotifyDao notifyDao;

    @Autowired
    private SysUserDao sysUserDao;

    @Override
    public PageModel<NotifyDTO> listAllNotify(PageCondition condition) {
        PageHelper.startPage(condition.getPageNum(), condition.getPageSize());
        PageInfo<NotifyDTO> pageInfo = new PageInfo<>(notifyDao.findAll());
        return Results.pageModel(pageInfo);
    }

    @Transactional
    @Override
    public NotifyDTO create(Integer userId, String content) {
        if (sysUserDao.find(userId) == null) {
            throw Errors.badRequest("用户不存在");
        }

        Notify sr = new Notify();
        sr.setUserId(userId);
        sr.setContent(content);
        if (1 != notifyDao.insert(sr)) {
            throw new ServiceException("无法新增通知记录到数据库");
        }

        return notifyDao.findById(sr.getId());
    }

    @Transactional
    @Override
    public NotifyDTO update(Integer notifyId, String content) {
        Notify sr = new Notify();
        sr.setContent(content);
        sr.setId(notifyId);
        notifyDao.update(sr);

        return notifyDao.findById(sr.getId());
    }

    @Transactional
    @Override
    public void delete(Integer notifyId) {
        if (1 != notifyDao.delete(notifyId)) {
            throw new ServiceException("在数据库中删除角色记录失败");
        }
    }
}
