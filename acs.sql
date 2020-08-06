CREATE DATABASE `acs` CHARACTER SET 'utf8';
USE `acs`;

CREATE TABLE `sys_permission`
(
    `id`              int(4) unsigned NOT NULL AUTO_INCREMENT,
    `insert_time`     timestamp       NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `update_time`     timestamp       NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    `permission_code` varchar(255)    NOT NULL COMMENT '权限的代码/通配符',
    `permission_name` varchar(255)    NOT NULL COMMENT '权限的中文释义',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_permission_code` (`permission_code`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT ='权限表';

INSERT INTO `sys_permission`(id, permission_code, permission_name)
VALUES (101, 'notify:list', '通知列表'),
       (102, 'notify:add', '创建通知'),
       (103, 'notify:edit', '编辑通知'),
       (104, 'notify:delete', '删除通知'),

       (301, 'user:list', '用户列表'),
       (302, 'user:add', '新增用户'),
       (303, 'user:edit', '编辑用户'),
       (304, 'user:delete', '删除用户'),

       (501, 'role:list', '角色列表'),
       (502, 'role:add', '新增角色'),
       (503, 'role:edit', '编辑角色'),
       (504, 'role:delete', '删除角色');

CREATE TABLE `sys_role`
(
    `id`          int(4) unsigned NOT NULL AUTO_INCREMENT,
    `insert_time` timestamp       NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `update_time` timestamp       NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    `role_name`   varchar(20)     NOT NULL COMMENT '角色名',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_role_name` (`role_name`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT ='角色表';

INSERT INTO `sys_role`(id, role_name)
VALUES (1, '超级管理员');

CREATE TABLE `sys_role_permission`
(
    `id`            int(4) unsigned NOT NULL AUTO_INCREMENT,
    `insert_time`   timestamp       NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `update_time`   timestamp       NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    `role_id`       int(4)          NOT NULL COMMENT '角色id',
    `permission_id` int(4)          NOT NULL COMMENT '权限id',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_role_id_permission_id` (`role_id`, `permission_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT ='角色-权限关联表';

INSERT INTO `sys_role_permission`(role_id, permission_id)
VALUES (1, 101),
       (1, 102),
       (1, 103),
       (1, 104),

       (1, 301),
       (1, 302),
       (1, 303),
       (1, 304),

       (1, 501),
       (1, 502),
       (1, 503),
       (1, 504);

CREATE TABLE `sys_user`
(
    `id`          int(4) unsigned NOT NULL AUTO_INCREMENT,
    `insert_time` timestamp       NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `update_time` timestamp       NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    `username`    varchar(255)    NOT NULL COMMENT '用户名',
    `password`    varchar(255)    NOT NULL COMMENT '密码',
    `nickname`    varchar(255)    NULL     DEFAULT NULL COMMENT '昵称',
    PRIMARY KEY (`id`),
    UNIQUE KEY `username` (`username`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 10000
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT ='用户表';

INSERT INTO `sys_user`(id, username, password, nickname)
VALUES (10001, 'admin', '123456', '超级管理员');

CREATE TABLE `sys_user_role`
(
    `id`          int(4) unsigned NOT NULL AUTO_INCREMENT,
    `insert_time` timestamp       NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `update_time` timestamp       NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    `user_id`     int(4)          NOT NULL COMMENT '用户id',
    `role_id`     int(4)          NOT NULL COMMENT '角色id',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_user_role` (`user_id`, `role_id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 10000
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT ='用户-角色关联表';

INSERT INTO `sys_user_role`(user_id, role_id)
VALUES (10001, 1);

CREATE TABLE `notify`
(
    `id`          int(4) unsigned NOT NULL AUTO_INCREMENT,
    `insert_time` timestamp       NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `update_time` timestamp       NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    `user_id`     int(4)          NOT NULL COMMENT '创建用户id',
    `content`     text            NOT NULL COMMENT '通知内容',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT ='通知表';

# ######################################################################################################
INSERT INTO `sys_permission`(id, permission_code, permission_name)
VALUES (601, 'role:permission:list', '角色权限列表'),
       (602, 'role:assign-permission', '角色权限分配'),
       (801, 'permission:list', '权限列表');

INSERT INTO `sys_role_permission`(role_id, permission_id)
VALUES (1, 601),
       (1, 602),
       (1, 801);

INSERT INTO `sys_permission`(id, permission_code, permission_name)
    VALUE (901, 'user:assign-role', '用户角色分配');