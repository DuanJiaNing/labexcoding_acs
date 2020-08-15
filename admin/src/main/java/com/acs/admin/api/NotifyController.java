package com.acs.admin.api;

import com.acs.admin.common.PageCondition;
import com.acs.admin.common.PageModel;
import com.acs.admin.common.dto.NotifyDTO;
import com.acs.admin.service.NotifyService;
import com.acs.admin.service.UserService;
import com.acs.admin.utils.Results;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class NotifyController {

    @Autowired
    private NotifyService notifyService;

    @Autowired
    private UserService userService;

    @RequiresPermissions("notify:list")
    @ApiOperation("通知列表")
    @GetMapping("/notifies")
    public ResponseEntity<PageModel<NotifyDTO>> list(@RequestParam(required = false) Integer pageSize,
                                                     @RequestParam(required = false) Integer pageNum) {
        PageCondition pc = new PageCondition(pageNum, pageSize);
        PageModel<NotifyDTO> pageModel = notifyService.listAllNotify(pc);
        return Results.success(pageModel);
    }


    @RequiresPermissions("notify:add")
    @ApiOperation("创建通知")
    @PostMapping("/notify")
    public ResponseEntity<NotifyDTO> add(@RequestBody String content) {
        if (StringUtils.isBlank(content)) {
            return Results.userInputError("通知内容不能为空");
        }

        String username = (String) SecurityUtils.getSubject().getPrincipal();
        Integer userID = userService.findUserID(username);
        NotifyDTO dto = notifyService.create(userID, content);
        return Results.success(dto);
    }

    @RequiresPermissions("notify:edit")
    @ApiOperation("编辑通知")
    @PutMapping("/notify/{id}")
    public ResponseEntity<NotifyDTO> edit(@RequestBody String content, @PathVariable Integer id) {
        if (StringUtils.isBlank(content)) {
            return Results.userInputError("通知内容不能为空");
        }

        return Results.success(notifyService.update(id, content));
    }

    @RequiresPermissions("notify:delete")
    @ApiOperation("删除通知")
    @DeleteMapping("/notify/{id}")
    public ResponseEntity delete(@PathVariable Integer id) {
        notifyService.delete(id);
        return Results.success();
    }
}
