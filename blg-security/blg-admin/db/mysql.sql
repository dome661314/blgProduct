-- 菜单
CREATE TABLE `sys_menu` (
  `menu_id` bigint NOT NULL AUTO_INCREMENT,
  `parent_id` bigint COMMENT '父菜单ID，一级菜单为0',
  `name` varchar(50) COMMENT '菜单名称',
  `url` varchar(200) COMMENT '菜单URL',
  `perms` varchar(500) COMMENT '授权(多个用逗号分隔，如：user:list,user:create)',
  `type` int COMMENT '类型   0：目录   1：菜单   2：按钮',
  `icon` varchar(50) COMMENT '菜单图标',
  `order_num` int COMMENT '排序',
  PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='菜单管理';

-- 部门
CREATE TABLE `sys_dept` (
  `dept_id` bigint NOT NULL AUTO_INCREMENT,
  `parent_id` bigint COMMENT '上级部门ID，一级部门为0',
  `name` varchar(50) COMMENT '部门名称',
  `order_num` int COMMENT '排序',
  `del_flag` tinyint DEFAULT 0 COMMENT '是否删除  -1：已删除  0：正常',
  PRIMARY KEY (`dept_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='部门管理';

-- 系统用户
CREATE TABLE `sys_user` (
  `user_id` bigint NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL COMMENT '用户名',
  `password` varchar(100) COMMENT '密码',
  `salt` varchar(20) COMMENT '盐',
  `email` varchar(100) COMMENT '邮箱',
  `mobile` varchar(100) COMMENT '手机号',
  `status` tinyint COMMENT '状态  0：禁用   1：正常',
  `dept_id` bigint(20) COMMENT '部门ID',
  `create_time` datetime COMMENT '创建时间',
  PRIMARY KEY (`user_id`),
  UNIQUE INDEX (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统用户';

-- 角色
CREATE TABLE `sys_role` (
  `role_id` bigint NOT NULL AUTO_INCREMENT,
  `role_name` varchar(100) COMMENT '角色名称',
  `remark` varchar(100) COMMENT '备注',
  `dept_id` bigint(20) COMMENT '部门ID',
  `create_time` datetime COMMENT '创建时间',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色';

-- 用户与角色对应关系
CREATE TABLE `sys_user_role` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` bigint COMMENT '用户ID',
  `role_id` bigint COMMENT '角色ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户与角色对应关系';

-- 角色与菜单对应关系
CREATE TABLE `sys_role_menu` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `role_id` bigint COMMENT '角色ID',
  `menu_id` bigint COMMENT '菜单ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色与菜单对应关系';

-- 角色与部门对应关系
CREATE TABLE `sys_role_dept` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `role_id` bigint COMMENT '角色ID',
  `dept_id` bigint COMMENT '部门ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色与部门对应关系';


-- 系统配置信息
CREATE TABLE `sys_config` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `param_key` varchar(50) COMMENT 'key',
  `param_value` varchar(2000) COMMENT 'value',
  `status` tinyint DEFAULT 1 COMMENT '状态   0：隐藏   1：显示',
  `remark` varchar(500) COMMENT '备注',
  PRIMARY KEY (`id`),
  UNIQUE INDEX (`param_key`)
) ENGINE=`InnoDB` DEFAULT CHARACTER SET utf8 COMMENT='系统配置信息表';

-- 数据字典
CREATE TABLE `sys_dict` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL COMMENT '字典名称',
  `type` varchar(100) NOT NULL COMMENT '字典类型',
  `code` varchar(100) NOT NULL COMMENT '字典码',
  `value` varchar(1000) NOT NULL COMMENT '字典值',
  `order_num` int DEFAULT 0 COMMENT '排序',
  `remark` varchar(255) COMMENT '备注',
  `del_flag` tinyint DEFAULT 0 COMMENT '删除标记  -1：已删除  0：正常',
  PRIMARY KEY (`id`),
  UNIQUE KEY(`type`,`code`)
) ENGINE=InnoDB DEFAULT CHARACTER SET utf8 COMMENT='数据字典表';

-- 系统日志
CREATE TABLE `sys_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) COMMENT '用户名',
  `operation` varchar(50) COMMENT '用户操作',
  `method` varchar(200) COMMENT '请求方法',
  `params` varchar(5000) COMMENT '请求参数',
  `time` bigint NOT NULL COMMENT '执行时长(毫秒)',
  `ip` varchar(64) COMMENT 'IP地址',
  `create_date` datetime COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=`InnoDB` DEFAULT CHARACTER SET utf8 COMMENT='系统日志';

-- 初始数据
INSERT INTO `sys_user` (`user_id`, `username`, `password`, `salt`, `email`, `mobile`, `status`, `dept_id`, `create_time`) VALUES ('1', 'admin', 'e1153123d7d180ceeb820d577ff119876678732a68eef4e6ffc0b1f06a01f91b', 'YzcmCZNvbXocrsz9dm8e', 'root@blg.io', '13612345678', '1', '1', '2016-11-11 11:11:11');
INSERT INTO `sys_menu` (`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`) VALUES ('1', '0', '系统管理', NULL, NULL, '0', 'fa fa-cog', '0');
INSERT INTO `sys_menu` (`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`) VALUES ('2', '1', '管理员管理', 'modules/sys/user.html', NULL, '1', 'fa fa-user', '1');
INSERT INTO `sys_menu` (`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`) VALUES ('3', '1', '角色管理', 'modules/sys/role.html', NULL, '1', 'fa fa-user-secret', '2');
INSERT INTO `sys_menu` (`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`) VALUES ('4', '1', '菜单管理', 'modules/sys/menu.html', NULL, '1', 'fa fa-th-list', '3');
INSERT INTO `sys_menu` (`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`) VALUES ('5', '1', 'SQL监控', 'druid/sql.html', NULL, '1', 'fa fa-bug', '4');
INSERT INTO `sys_menu` (`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`) VALUES ('15', '2', '查看', NULL, 'sys:user:list,sys:user:info', '2', NULL, '0');
INSERT INTO `sys_menu` (`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`) VALUES ('16', '2', '新增', NULL, 'sys:user:save,sys:role:select', '2', NULL, '0');
INSERT INTO `sys_menu` (`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`) VALUES ('17', '2', '修改', NULL, 'sys:user:update,sys:role:select', '2', NULL, '0');
INSERT INTO `sys_menu` (`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`) VALUES ('18', '2', '删除', NULL, 'sys:user:delete', '2', NULL, '0');
INSERT INTO `sys_menu` (`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`) VALUES ('19', '3', '查看', NULL, 'sys:role:list,sys:role:info', '2', NULL, '0');
INSERT INTO `sys_menu` (`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`) VALUES ('20', '3', '新增', NULL, 'sys:role:save,sys:menu:perms', '2', NULL, '0');
INSERT INTO `sys_menu` (`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`) VALUES ('21', '3', '修改', NULL, 'sys:role:update,sys:menu:perms', '2', NULL, '0');
INSERT INTO `sys_menu` (`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`) VALUES ('22', '3', '删除', NULL, 'sys:role:delete', '2', NULL, '0');
INSERT INTO `sys_menu` (`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`) VALUES ('23', '4', '查看', NULL, 'sys:menu:list,sys:menu:info', '2', NULL, '0');
INSERT INTO `sys_menu` (`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`) VALUES ('24', '4', '新增', NULL, 'sys:menu:save,sys:menu:select', '2', NULL, '0');
INSERT INTO `sys_menu` (`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`) VALUES ('25', '4', '修改', NULL, 'sys:menu:update,sys:menu:select', '2', NULL, '0');
INSERT INTO `sys_menu` (`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`) VALUES ('26', '4', '删除', NULL, 'sys:menu:delete', '2', NULL, '0');
INSERT INTO `sys_menu` (`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`) VALUES ('27', '1', '参数管理', 'modules/sys/config.html', 'sys:config:list,sys:config:info,sys:config:save,sys:config:update,sys:config:delete', '1', 'fa fa-sun-o', '6');
INSERT INTO `sys_menu` (`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`) VALUES ('29', '1', '系统日志', 'modules/sys/log.html', 'sys:log:list', '1', 'fa fa-file-text-o', '7');
INSERT INTO `sys_menu` (`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`) VALUES ('31', '1', '部门管理', 'modules/sys/dept.html', NULL, '1', 'fa fa-file-code-o', '1');
INSERT INTO `sys_menu` (`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`) VALUES ('32', '31', '查看', NULL, 'sys:dept:list,sys:dept:info', '2', NULL, '0');
INSERT INTO `sys_menu` (`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`) VALUES ('33', '31', '新增', NULL, 'sys:dept:save,sys:dept:select', '2', NULL, '0');
INSERT INTO `sys_menu` (`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`) VALUES ('34', '31', '修改', NULL, 'sys:dept:update,sys:dept:select', '2', NULL, '0');
INSERT INTO `sys_menu` (`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`) VALUES ('35', '31', '删除', NULL, 'sys:dept:delete', '2', NULL, '0');

INSERT INTO `sys_menu`(`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`) VALUES (36, 1, '字典管理', 'modules/sys/dict.html', NULL, 1, 'fa fa-bookmark-o', 6);
INSERT INTO `sys_menu`(`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`) VALUES (37, 36, '查看', NULL, 'sys:dict:list,sys:dict:info', 2, NULL, 6);
INSERT INTO `sys_menu`(`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`) VALUES (38, 36, '新增', NULL, 'sys:dict:save', 2, NULL, 6);
INSERT INTO `sys_menu`(`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`) VALUES (39, 36, '修改', NULL, 'sys:dict:update', 2, NULL, 6);
INSERT INTO `sys_menu`(`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`) VALUES (40, 36, '删除', NULL, 'sys:dict:delete', 2, NULL, 6);


INSERT INTO `sys_dept` (`dept_id`, `parent_id`, `name`, `order_num`, `del_flag`) VALUES ('1', '0', '维也纳商品租赁集团', '0', '0');
INSERT INTO `sys_dept` (`dept_id`, `parent_id`, `name`, `order_num`, `del_flag`) VALUES ('2', '1', '北京分公司', '1', '0');
INSERT INTO `sys_dept` (`dept_id`, `parent_id`, `name`, `order_num`, `del_flag`) VALUES ('3', '1', '上海分公司', '2', '0');
INSERT INTO `sys_dept` (`dept_id`, `parent_id`, `name`, `order_num`, `del_flag`) VALUES ('4', '3', '商品部', '0', '0');
INSERT INTO `sys_dept` (`dept_id`, `parent_id`, `name`, `order_num`, `del_flag`) VALUES ('5', '3', '销售部', '1', '0');

INSERT INTO `sys_dict`(`id`, `name`, `type`, `code`, `value`, `order_num`, `remark`, `del_flag`) VALUES (1, '性别', 'sex', '0', '女', 0, NULL, 0);
INSERT INTO `sys_dict`(`id`, `name`, `type`, `code`, `value`, `order_num`, `remark`, `del_flag`) VALUES (2, '性别', 'sex', '1', '男', 1, NULL, 0);
INSERT INTO `sys_dict`(`id`, `name`, `type`, `code`, `value`, `order_num`, `remark`, `del_flag`) VALUES (3, '性别', 'sex', '2', '未知', 3, NULL, 0);



-- ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------
-- 云存储服务相关SQL，如果不使用该功能，则不用执行下面SQL -------------------------------------------------------------------------------------------------------------
-- ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------

-- 文件上传
CREATE TABLE `sys_oss` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `url` varchar(200) COMMENT 'URL地址',
  `create_date` datetime COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=`InnoDB` DEFAULT CHARACTER SET utf8 COMMENT='文件上传';

INSERT INTO `sys_config` (`param_key`, `param_value`, `status`, `remark`) VALUES ('CLOUD_STORAGE_CONFIG_KEY', '{\"aliyunAccessKeyId\":\"\",\"aliyunAccessKeySecret\":\"\",\"aliyunBucketName\":\"\",\"aliyunDomain\":\"\",\"aliyunEndPoint\":\"\",\"aliyunPrefix\":\"\",\"qcloudBucketName\":\"\",\"qcloudDomain\":\"\",\"qcloudPrefix\":\"\",\"qcloudSecretId\":\"\",\"qcloudSecretKey\":\"\",\"qiniuAccessKey\":\"NrgMfABZxWLo5B-YYSjoE8-AZ1EISdi1Z3ubLOeZ\",\"qiniuBucketName\":\"ios-app\",\"qiniuDomain\":\"http://7xqbwh.dl1.z0.glb.clouddn.com\",\"qiniuPrefix\":\"upload\",\"qiniuSecretKey\":\"uIwJHevMRWU0VLxFvgy0tAcOdGqasdtVlJkdy6vV\",\"type\":1}', '0', '云存储配置信息');
INSERT INTO `sys_menu` (`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`) VALUES ('30', '1', '文件上传', 'modules/oss/oss.html', 'sys:oss:all', '1', 'fa fa-file-image-o', '6');


-- ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------
-- 定时任务相关表结构，如果不使用job模块，则不用执行下面SQL -------------------------------------------------------------------------------------------------------------
-- ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------

-- 初始化菜单数据
INSERT INTO `sys_menu` (`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`) VALUES ('6', '1', '定时任务', 'modules/job/schedule.html', NULL, '1', 'fa fa-tasks', '5');
INSERT INTO `sys_menu` (`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`) VALUES ('7', '6', '查看', NULL, 'sys:schedule:list,sys:schedule:info', '2', NULL, '0');
INSERT INTO `sys_menu` (`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`) VALUES ('8', '6', '新增', NULL, 'sys:schedule:save', '2', NULL, '0');
INSERT INTO `sys_menu` (`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`) VALUES ('9', '6', '修改', NULL, 'sys:schedule:update', '2', NULL, '0');
INSERT INTO `sys_menu` (`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`) VALUES ('10', '6', '删除', NULL, 'sys:schedule:delete', '2', NULL, '0');
INSERT INTO `sys_menu` (`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`) VALUES ('11', '6', '暂停', NULL, 'sys:schedule:pause', '2', NULL, '0');
INSERT INTO `sys_menu` (`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`) VALUES ('12', '6', '恢复', NULL, 'sys:schedule:resume', '2', NULL, '0');
INSERT INTO `sys_menu` (`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`) VALUES ('13', '6', '立即执行', NULL, 'sys:schedule:run', '2', NULL, '0');
INSERT INTO `sys_menu` (`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`) VALUES ('14', '6', '日志列表', NULL, 'sys:schedule:log', '2', NULL, '0');

-- 定时任务
CREATE TABLE `schedule_job` (
  `job_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '任务id',
  `bean_name` varchar(200) DEFAULT NULL COMMENT 'spring bean名称',
  `method_name` varchar(100) DEFAULT NULL COMMENT '方法名',
  `params` varchar(2000) DEFAULT NULL COMMENT '参数',
  `cron_expression` varchar(100) DEFAULT NULL COMMENT 'cron表达式',
  `status` tinyint(4) DEFAULT NULL COMMENT '任务状态  0：正常  1：暂停',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`job_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='定时任务';

-- 定时任务日志
CREATE TABLE `schedule_job_log` (
  `log_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '任务日志id',
  `job_id` bigint(20) NOT NULL COMMENT '任务id',
  `bean_name` varchar(200) DEFAULT NULL COMMENT 'spring bean名称',
  `method_name` varchar(100) DEFAULT NULL COMMENT '方法名',
  `params` varchar(2000) DEFAULT NULL COMMENT '参数',
  `status` tinyint(4) NOT NULL COMMENT '任务状态    0：成功    1：失败',
  `error` varchar(2000) DEFAULT NULL COMMENT '失败信息',
  `times` int(11) NOT NULL COMMENT '耗时(单位：毫秒)',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`log_id`),
  KEY `job_id` (`job_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='定时任务日志';



INSERT INTO `schedule_job` (`bean_name`, `method_name`, `params`, `cron_expression`, `status`, `remark`, `create_time`) VALUES ('testTask', 'test', 'blg', '0 0/30 * * * ?', '0', '有参数测试', '2016-12-01 23:16:46');
INSERT INTO `schedule_job` (`bean_name`, `method_name`, `params`, `cron_expression`, `status`, `remark`, `create_time`) VALUES ('testTask', 'test2', NULL, '0 0/30 * * * ?', '1', '无参数测试', '2016-12-03 14:55:56');


--  quartz自带表结构
CREATE TABLE QRTZ_JOB_DETAILS(
  SCHED_NAME VARCHAR(120) NOT NULL,
  JOB_NAME VARCHAR(200) NOT NULL,
  JOB_GROUP VARCHAR(200) NOT NULL,
  DESCRIPTION VARCHAR(250) NULL,
  JOB_CLASS_NAME VARCHAR(250) NOT NULL,
  IS_DURABLE VARCHAR(1) NOT NULL,
  IS_NONCONCURRENT VARCHAR(1) NOT NULL,
  IS_UPDATE_DATA VARCHAR(1) NOT NULL,
  REQUESTS_RECOVERY VARCHAR(1) NOT NULL,
  JOB_DATA BLOB NULL,
  PRIMARY KEY (SCHED_NAME,JOB_NAME,JOB_GROUP))
  ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE QRTZ_TRIGGERS (
  SCHED_NAME VARCHAR(120) NOT NULL,
  TRIGGER_NAME VARCHAR(200) NOT NULL,
  TRIGGER_GROUP VARCHAR(200) NOT NULL,
  JOB_NAME VARCHAR(200) NOT NULL,
  JOB_GROUP VARCHAR(200) NOT NULL,
  DESCRIPTION VARCHAR(250) NULL,
  NEXT_FIRE_TIME BIGINT(13) NULL,
  PREV_FIRE_TIME BIGINT(13) NULL,
  PRIORITY INTEGER NULL,
  TRIGGER_STATE VARCHAR(16) NOT NULL,
  TRIGGER_TYPE VARCHAR(8) NOT NULL,
  START_TIME BIGINT(13) NOT NULL,
  END_TIME BIGINT(13) NULL,
  CALENDAR_NAME VARCHAR(200) NULL,
  MISFIRE_INSTR SMALLINT(2) NULL,
  JOB_DATA BLOB NULL,
  PRIMARY KEY (SCHED_NAME,TRIGGER_NAME,TRIGGER_GROUP),
  FOREIGN KEY (SCHED_NAME,JOB_NAME,JOB_GROUP)
  REFERENCES QRTZ_JOB_DETAILS(SCHED_NAME,JOB_NAME,JOB_GROUP))
  ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE QRTZ_SIMPLE_TRIGGERS (
  SCHED_NAME VARCHAR(120) NOT NULL,
  TRIGGER_NAME VARCHAR(200) NOT NULL,
  TRIGGER_GROUP VARCHAR(200) NOT NULL,
  REPEAT_COUNT BIGINT(7) NOT NULL,
  REPEAT_INTERVAL BIGINT(12) NOT NULL,
  TIMES_TRIGGERED BIGINT(10) NOT NULL,
  PRIMARY KEY (SCHED_NAME,TRIGGER_NAME,TRIGGER_GROUP),
  FOREIGN KEY (SCHED_NAME,TRIGGER_NAME,TRIGGER_GROUP)
  REFERENCES QRTZ_TRIGGERS(SCHED_NAME,TRIGGER_NAME,TRIGGER_GROUP))
  ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE QRTZ_CRON_TRIGGERS (
  SCHED_NAME VARCHAR(120) NOT NULL,
  TRIGGER_NAME VARCHAR(200) NOT NULL,
  TRIGGER_GROUP VARCHAR(200) NOT NULL,
  CRON_EXPRESSION VARCHAR(120) NOT NULL,
  TIME_ZONE_ID VARCHAR(80),
  PRIMARY KEY (SCHED_NAME,TRIGGER_NAME,TRIGGER_GROUP),
  FOREIGN KEY (SCHED_NAME,TRIGGER_NAME,TRIGGER_GROUP)
  REFERENCES QRTZ_TRIGGERS(SCHED_NAME,TRIGGER_NAME,TRIGGER_GROUP))
  ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE QRTZ_SIMPROP_TRIGGERS
(
  SCHED_NAME VARCHAR(120) NOT NULL,
  TRIGGER_NAME VARCHAR(200) NOT NULL,
  TRIGGER_GROUP VARCHAR(200) NOT NULL,
  STR_PROP_1 VARCHAR(512) NULL,
  STR_PROP_2 VARCHAR(512) NULL,
  STR_PROP_3 VARCHAR(512) NULL,
  INT_PROP_1 INT NULL,
  INT_PROP_2 INT NULL,
  LONG_PROP_1 BIGINT NULL,
  LONG_PROP_2 BIGINT NULL,
  DEC_PROP_1 NUMERIC(13,4) NULL,
  DEC_PROP_2 NUMERIC(13,4) NULL,
  BOOL_PROP_1 VARCHAR(1) NULL,
  BOOL_PROP_2 VARCHAR(1) NULL,
  PRIMARY KEY (SCHED_NAME,TRIGGER_NAME,TRIGGER_GROUP),
  FOREIGN KEY (SCHED_NAME,TRIGGER_NAME,TRIGGER_GROUP)
  REFERENCES QRTZ_TRIGGERS(SCHED_NAME,TRIGGER_NAME,TRIGGER_GROUP))
  ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE QRTZ_BLOB_TRIGGERS (
  SCHED_NAME VARCHAR(120) NOT NULL,
  TRIGGER_NAME VARCHAR(200) NOT NULL,
  TRIGGER_GROUP VARCHAR(200) NOT NULL,
  BLOB_DATA BLOB NULL,
  PRIMARY KEY (SCHED_NAME,TRIGGER_NAME,TRIGGER_GROUP),
  INDEX (SCHED_NAME,TRIGGER_NAME, TRIGGER_GROUP),
  FOREIGN KEY (SCHED_NAME,TRIGGER_NAME,TRIGGER_GROUP)
  REFERENCES QRTZ_TRIGGERS(SCHED_NAME,TRIGGER_NAME,TRIGGER_GROUP))
  ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE QRTZ_CALENDARS (
  SCHED_NAME VARCHAR(120) NOT NULL,
  CALENDAR_NAME VARCHAR(200) NOT NULL,
  CALENDAR BLOB NOT NULL,
  PRIMARY KEY (SCHED_NAME,CALENDAR_NAME))
  ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE QRTZ_PAUSED_TRIGGER_GRPS (
  SCHED_NAME VARCHAR(120) NOT NULL,
  TRIGGER_GROUP VARCHAR(200) NOT NULL,
  PRIMARY KEY (SCHED_NAME,TRIGGER_GROUP))
  ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE QRTZ_FIRED_TRIGGERS (
  SCHED_NAME VARCHAR(120) NOT NULL,
  ENTRY_ID VARCHAR(95) NOT NULL,
  TRIGGER_NAME VARCHAR(200) NOT NULL,
  TRIGGER_GROUP VARCHAR(200) NOT NULL,
  INSTANCE_NAME VARCHAR(200) NOT NULL,
  FIRED_TIME BIGINT(13) NOT NULL,
  SCHED_TIME BIGINT(13) NOT NULL,
  PRIORITY INTEGER NOT NULL,
  STATE VARCHAR(16) NOT NULL,
  JOB_NAME VARCHAR(200) NULL,
  JOB_GROUP VARCHAR(200) NULL,
  IS_NONCONCURRENT VARCHAR(1) NULL,
  REQUESTS_RECOVERY VARCHAR(1) NULL,
  PRIMARY KEY (SCHED_NAME,ENTRY_ID))
  ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE QRTZ_SCHEDULER_STATE (
  SCHED_NAME VARCHAR(120) NOT NULL,
  INSTANCE_NAME VARCHAR(200) NOT NULL,
  LAST_CHECKIN_TIME BIGINT(13) NOT NULL,
  CHECKIN_INTERVAL BIGINT(13) NOT NULL,
  PRIMARY KEY (SCHED_NAME,INSTANCE_NAME))
  ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE QRTZ_LOCKS (
  SCHED_NAME VARCHAR(120) NOT NULL,
  LOCK_NAME VARCHAR(40) NOT NULL,
  PRIMARY KEY (SCHED_NAME,LOCK_NAME))
  ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE INDEX IDX_QRTZ_J_REQ_RECOVERY ON QRTZ_JOB_DETAILS(SCHED_NAME,REQUESTS_RECOVERY);
CREATE INDEX IDX_QRTZ_J_GRP ON QRTZ_JOB_DETAILS(SCHED_NAME,JOB_GROUP);

CREATE INDEX IDX_QRTZ_T_J ON QRTZ_TRIGGERS(SCHED_NAME,JOB_NAME,JOB_GROUP);
CREATE INDEX IDX_QRTZ_T_JG ON QRTZ_TRIGGERS(SCHED_NAME,JOB_GROUP);
CREATE INDEX IDX_QRTZ_T_C ON QRTZ_TRIGGERS(SCHED_NAME,CALENDAR_NAME);
CREATE INDEX IDX_QRTZ_T_G ON QRTZ_TRIGGERS(SCHED_NAME,TRIGGER_GROUP);
CREATE INDEX IDX_QRTZ_T_STATE ON QRTZ_TRIGGERS(SCHED_NAME,TRIGGER_STATE);
CREATE INDEX IDX_QRTZ_T_N_STATE ON QRTZ_TRIGGERS(SCHED_NAME,TRIGGER_NAME,TRIGGER_GROUP,TRIGGER_STATE);
CREATE INDEX IDX_QRTZ_T_N_G_STATE ON QRTZ_TRIGGERS(SCHED_NAME,TRIGGER_GROUP,TRIGGER_STATE);
CREATE INDEX IDX_QRTZ_T_NEXT_FIRE_TIME ON QRTZ_TRIGGERS(SCHED_NAME,NEXT_FIRE_TIME);
CREATE INDEX IDX_QRTZ_T_NFT_ST ON QRTZ_TRIGGERS(SCHED_NAME,TRIGGER_STATE,NEXT_FIRE_TIME);
CREATE INDEX IDX_QRTZ_T_NFT_MISFIRE ON QRTZ_TRIGGERS(SCHED_NAME,MISFIRE_INSTR,NEXT_FIRE_TIME);
CREATE INDEX IDX_QRTZ_T_NFT_ST_MISFIRE ON QRTZ_TRIGGERS(SCHED_NAME,MISFIRE_INSTR,NEXT_FIRE_TIME,TRIGGER_STATE);
CREATE INDEX IDX_QRTZ_T_NFT_ST_MISFIRE_GRP ON QRTZ_TRIGGERS(SCHED_NAME,MISFIRE_INSTR,NEXT_FIRE_TIME,TRIGGER_GROUP,TRIGGER_STATE);

CREATE INDEX IDX_QRTZ_FT_TRIG_INST_NAME ON QRTZ_FIRED_TRIGGERS(SCHED_NAME,INSTANCE_NAME);
CREATE INDEX IDX_QRTZ_FT_INST_JOB_REQ_RCVRY ON QRTZ_FIRED_TRIGGERS(SCHED_NAME,INSTANCE_NAME,REQUESTS_RECOVERY);
CREATE INDEX IDX_QRTZ_FT_J_G ON QRTZ_FIRED_TRIGGERS(SCHED_NAME,JOB_NAME,JOB_GROUP);
CREATE INDEX IDX_QRTZ_FT_JG ON QRTZ_FIRED_TRIGGERS(SCHED_NAME,JOB_GROUP);
CREATE INDEX IDX_QRTZ_FT_T_G ON QRTZ_FIRED_TRIGGERS(SCHED_NAME,TRIGGER_NAME,TRIGGER_GROUP);
CREATE INDEX IDX_QRTZ_FT_TG ON QRTZ_FIRED_TRIGGERS(SCHED_NAME,TRIGGER_GROUP);


/*==============================================================*/
/* Table: blg_user_address                                      */
/*==============================================================*/
create table blg_user_address
(
   id                   bigint not null comment '主键',
   user_id              bigint not null comment '用户ID',
   user_name            varchar(32) comment '用户名',
   receive_name         varchar(32) comment '收货人姓名',
   receive_mobile       varchar(16) comment '收货人电话',
   address              varchar(500) comment '用户地址',
   postal_code          varchar(6) comment '邮政编码',
   province             varchar(32) comment '省份',
   city                 varchar(32) comment '城市',
   county               varchar(32) comment '县',
   create_name          varchar(64) comment '创建人',
   create_date          datetime comment '创建时间',
   update_name          varchar(64) comment '修改人',
   update_date          datetime comment '修改时间',
   is_default           varchar(8) comment '是否默认',
   primary key (id)
)ENGINE=InnoDB DEFAULT CHARACTER SET utf8 COMMENT='用户地址表';


/*==============================================================*/
/* Table: blg_prod_image                                        */
/*==============================================================*/
create table blg_prod_image
(
   id                   bigint not null comment '主键',
   prod_id              bigint comment '商品ID',
   url                  varchar(500) comment '图片路径',
   type                 varchar(2) comment '图片类型',
   create_name          varchar(64) comment '创建人',
   create_date          datetime comment '创建时间',
   primary key (id)
)ENGINE=InnoDB DEFAULT CHARACTER SET utf8 COMMENT='商品图片';

/*==============================================================*/
/* Table: blg_product                                           */
/*==============================================================*/
create table blg_product
(
   id                   bigint not null comment '主键',
   prod_code            varchar(64) not null comment '商品编码',
   prod_name            varchar(200) not null comment '商品名称',
   prod_price           decimal(16,4) comment '商品价格',
   prod_spec            varchar(20) comment '商品规格',
   prod_brand           varchar(32) comment '商品品牌',
   deposit              decimal(16,4) comment '押金',
   prod_place           varchar(500) comment '产地',
   prod_condition            varchar(32) comment '成色',
   prod_unit            varchar(2) comment '商品单位',
   prod_status          varchar(2) comment '商品状态',
   prod_num             int comment '商品数量',
   produce_date         datetime comment '生产日期',
   produce_date_str     varchar(32) comment '生产日期',
   create_name          varchar(64) comment '创建人',
   create_date          datetime comment '创建时间',
   update_name          varchar(64) comment '修改人',
   update_date          datetime comment '修改时间',
   image_path           varchar(500) comment '图片路径',
   primary key (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品信息表';


/*==============================================================*/
/* Table: blg_order                                             */
/*==============================================================*/
create table blg_order
(
   id                   bigint not null comment '主键',
   address_id           bigint comment '用户地址ID',
   user_id              bigint comment '用户ID',
   order_no             varchar(64) not null comment '订单号',
   total_price          decimal(16,4) not null comment '订单价格',
   order_date           date not null comment '下单时间',
   order_status         varchar(2) not null comment '订单状态',
   create_name          varchar(64) comment '创建人',
   create_date          datetime comment '创建时间',
   update_name          varchar(64) comment '修改人',
   update_date          datetime comment '修改时间',
   primary key (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单信息表';


/*==============================================================*/
/* Table: blg_order_item                                        */
/*==============================================================*/
create table blg_order_item
(
   id                   bigint not null comment '主键',
   order_id             bigint comment '订单ID',
   prod_id              bigint comment '商品ID',
   prod_name            varchar(64) comment '商品名称',
   prod_num             int comment '商品数量',
   prod_price           decimal(16,4) comment '商品价格',
   rent_start_date      date comment '租赁开始时间',
   rent_end_date        date comment '租赁结束时间',
   create_name          varchar(64) comment '创建人',
   create_date          datetime comment '创建时间',
   update_name          varchar(64) comment '修改人',
   update_date          datetime comment '修改时间',
   primary key (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单明细表';

INSERT INTO `blg_security`.`sys_menu`(`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`) VALUES (41, 0, '业务管理', NULL, NULL, 0, 'fa fa-thumbs-o-up', 2);
INSERT INTO `blg_security`.`sys_menu`(`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`) VALUES (42, 41, '商品信息管理', 'modules/prod/prod.html', NULL, 1, 'fa fa-shopping-bag', 1);
INSERT INTO `blg_security`.`sys_menu`(`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`) VALUES (43, 41, '订单管理', 'modules/order/order.html', NULL, 1, 'fa fa-shopping-basket', 2);
INSERT INTO `blg_security`.`sys_menu`(`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`) VALUES (44, 41, '用户地址管理', 'modules/sys/userAddress.html', NULL, 1, 'fa fa-id-card', 2);
INSERT INTO `blg_security`.`sys_menu`(`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`) VALUES (45, 42, '查看', NULL, 'blg:prod:list,blg:prod:info', 2, NULL, 0);
INSERT INTO `blg_security`.`sys_menu`(`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`) VALUES (46, 42, '修改', NULL, 'blg:prod:update', 2, NULL, 0);
INSERT INTO `blg_security`.`sys_menu`(`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`) VALUES (47, 42, '删除', NULL, 'blg:prod:delete', 2, NULL, 0);
INSERT INTO `blg_security`.`sys_menu`(`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`) VALUES (48, 42, '新增', NULL, 'blg:prod:save', 2, NULL, 0);
INSERT INTO `blg_security`.`sys_menu`(`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`) VALUES (49, 43, '查看', NULL, 'blg:order:list,blg:order:info', 2, NULL, 0);
INSERT INTO `blg_security`.`sys_menu`(`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`) VALUES (50, 43, '修改', NULL, 'blg:order:update', 2, NULL, 0);
INSERT INTO `blg_security`.`sys_menu`(`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`) VALUES (51, 44, '查看', NULL, 'sys:userAddress:list,sys:userAddress:info', 2, NULL, 0);
INSERT INTO `blg_security`.`sys_menu`(`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`) VALUES (52, 44, '修改', NULL, 'sys:userAddress:update', 2, NULL, 0);
INSERT INTO `blg_security`.`sys_menu`(`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`) VALUES (53, 44, '删除', NULL, 'sys:userAddress:delete', 2, NULL, 0);



create table blg_prod_brand
(
   id                   bigint not null comment '主键',
   brand_code           bigint comment '品牌编码',
   brand_name              bigint comment '品牌名称',
   create_name          varchar(64) comment '创建人',
   create_date          datetime comment '创建时间',
   update_name          varchar(64) comment '修改人',
   update_date          datetime comment '修改时间',
   primary key (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品品牌表';

