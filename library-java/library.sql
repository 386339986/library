-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- 主机： localhost
-- 生成日期： 2020-10-25 14:24:57
-- 服务器版本： 8.0.12
-- PHP 版本： 7.3.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- 数据库： `library`
--

-- --------------------------------------------------------

--
-- 表的结构 `persistent_logins`
--

CREATE TABLE `persistent_logins` (
  `series` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'id',
  `username` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '登陆账号',
  `token` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'cookie令牌',
  `last_used` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='persistent_logins表，用户实现记住我功能' ROW_FORMAT=COMPACT;

-- --------------------------------------------------------

--
-- 表的结构 `room`
--

CREATE TABLE `room` (
  `id` int(64) NOT NULL COMMENT '自习室id',
  `school_id` int(64) NOT NULL COMMENT '学校id',
  `campus` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '自习室所在校区',
  `name` varchar(64) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '自习室名称',
  `status` int(32) NOT NULL DEFAULT '1' COMMENT '状态：1 启用 2 待用 3 维护',
  `available` int(32) DEFAULT NULL COMMENT '自习室剩余可用数量',
  `count` int(32) DEFAULT NULL COMMENT '自习室总座位数量',
  `seats` json DEFAULT NULL COMMENT '自习室座位和状态',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='自习室';

--
-- 转存表中的数据 `room`
--

INSERT INTO `room` (`id`, `school_id`, `campus`, `name`, `status`, `available`, `count`, `seats`, `create_time`, `update_time`) VALUES
(1, 1, '卫津路校区', '101', 1, 5, 6, '{\"seats\": [[3, 2, 3, 0, 4], [3, 2, 3, 0, 3], [0, 0, 0, 1, 0]], \"roomSize\": \"{\\\"col\\\":5,\\\"row\\\":3}\", \"seatNumber\": \"{\\\"r0\\\":{\\\"c4\\\":\\\"105\\\",\\\"c0\\\":\\\"101\\\",\\\"c2\\\":\\\"103\\\"},\\\"r1\\\":{\\\"c4\\\":\\\"106\\\",\\\"c0\\\":\\\"102\\\",\\\"c2\\\":\\\"104\\\"}}\"}', '2020-10-18 15:55:26', '2020-10-22 10:13:24'),
(2, 1, '卫津路校区', '102', 1, 11, 12, '{\"seats\": [[3, 2, 3, 0, 3], [3, 2, 3, 0, 3], [0, 0, 0, 0, 0], [3, 2, 4, 0, 3], [3, 2, 3, 0, 3], [0, 0, 0, 1, 0]], \"roomSize\": \"{\\\"col\\\":5,\\\"row\\\":6}\", \"seatNumber\": \"{\\\"r3\\\":{\\\"c4\\\":\\\"111\\\",\\\"c0\\\":\\\"107\\\",\\\"c2\\\":\\\"109\\\"},\\\"r4\\\":{\\\"c4\\\":\\\"112\\\",\\\"c0\\\":\\\"108\\\",\\\"c2\\\":\\\"110\\\"},\\\"r0\\\":{\\\"c4\\\":\\\"105\\\",\\\"c0\\\":\\\"101\\\",\\\"c2\\\":\\\"103\\\"},\\\"r1\\\":{\\\"c4\\\":\\\"106\\\",\\\"c0\\\":\\\"102\\\",\\\"c2\\\":\\\"104\\\"}}\"}', '2020-10-18 15:55:26', '2020-10-25 06:20:14'),
(3, 1, '北洋园校区', '304', 1, 40, 60, NULL, '2020-10-18 15:55:26', '2020-10-18 15:55:26'),
(4, 1, '北洋园校区', '103', 1, 12, 24, NULL, '2020-10-18 15:55:26', '2020-10-18 15:55:26'),
(5, 1, '北洋园校区', '405', 1, 400, 600, NULL, '2020-10-18 15:55:26', '2020-10-18 15:55:26');

-- --------------------------------------------------------

--
-- 表的结构 `school`
--

CREATE TABLE `school` (
  `id` int(64) NOT NULL COMMENT '学校id',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '学校名称',
  `province` varchar(32) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '学校所在省份',
  `city` varchar(32) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '学校所在市',
  `status` int(32) NOT NULL DEFAULT '1' COMMENT '状态：1 启用 2 停用',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='学校信息';

--
-- 转存表中的数据 `school`
--

INSERT INTO `school` (`id`, `name`, `province`, `city`, `status`, `create_time`, `update_time`) VALUES
(1, '天津大学', '天津市', '天津市', 1, '2020-10-18 12:40:39', '2020-10-18 12:42:07'),
(2, '南开大学', '天津市', '天津市', 1, '2020-10-18 12:43:12', '2020-10-18 12:43:12'),
(3, '北京大学', '北京市', '北京市', 1, '2020-10-18 12:43:12', '2020-10-18 12:43:12'),
(4, '清华大学', '北京市', '北京市', 1, '2020-10-18 12:43:12', '2020-10-18 12:43:12'),
(5, '测试学校', '测试省', '测试市', 1, '2020-10-18 12:43:12', '2020-10-18 12:43:12');

-- --------------------------------------------------------

--
-- 表的结构 `seat`
--

CREATE TABLE `seat` (
  `id` int(64) NOT NULL COMMENT '座位id',
  `student_id` int(64) NOT NULL COMMENT '学生id',
  `school_id` int(64) NOT NULL COMMENT '学校id',
  `room_id` int(64) NOT NULL COMMENT '自习室id',
  `seat_row` int(11) NOT NULL COMMENT '座位所在行',
  `name` varchar(255) COLLATE utf8_unicode_ci NOT NULL COMMENT '座位名称',
  `seat_col` int(11) NOT NULL COMMENT '座位所在列',
  `status` int(16) NOT NULL COMMENT '状态：1 退座 2 预约 3 正常使用 4 暂离',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '座位预约时间',
  `rest_time` int(11) NOT NULL DEFAULT '15' COMMENT '签到时长(分钟)',
  `use_time` timestamp NULL DEFAULT NULL COMMENT '入座时间',
  `end_time` timestamp NULL DEFAULT NULL COMMENT '座位释放时间',
  `temp_time` timestamp NULL DEFAULT NULL COMMENT '暂离开始时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- 转存表中的数据 `seat`
--

INSERT INTO `seat` (`id`, `student_id`, `school_id`, `room_id`, `seat_row`, `name`, `seat_col`, `status`, `create_time`, `rest_time`, `use_time`, `end_time`, `temp_time`, `update_time`) VALUES
(8, 1, 1, 1, 0, '卫津路校区101室101号', 0, 1, '2020-10-20 08:28:42', 15, '2020-10-20 08:47:11', '2020-10-20 09:30:59', NULL, '2020-10-20 12:38:56'),
(9, 1, 1, 1, 0, '卫津路校区101室101号', 0, 1, '2020-10-20 09:33:55', 15, '2020-10-20 11:18:21', '2020-10-20 11:20:53', NULL, '2020-10-20 12:38:59'),
(10, 1, 1, 1, 0, '卫津路校区101室102号', 2, 1, '2020-10-20 11:21:07', 15, '2020-10-20 11:22:18', '2020-10-20 14:21:26', NULL, '2020-10-21 13:02:58'),
(13, 1, 1, 1, 1, '卫津路校区101室104号', 2, 1, '2020-10-21 13:13:26', 15, '2020-10-21 13:13:39', '2020-10-21 13:14:35', NULL, '2020-10-21 13:14:35'),
(15, 1, 1, 1, 1, '卫津路校区101室106号', 4, 1, '2020-10-21 13:32:43', 15, '2020-10-21 13:34:30', '2020-10-21 13:34:45', NULL, '2020-10-21 13:34:44'),
(18, 1, 1, 1, 0, '卫津路校区101室103号', 2, 1, '2020-10-21 13:53:40', 15, '2020-10-21 13:53:44', '2020-10-21 14:16:59', '2020-10-21 13:57:54', '2020-10-22 09:40:47'),
(21, 2, 1, 1, 0, '卫津路校区101室105号', 4, 3, '2020-10-22 09:39:27', 15, '2020-10-22 09:39:31', NULL, NULL, '2020-10-22 09:39:30'),
(22, 1, 1, 1, 0, '卫津路校区101室101号', 0, 1, '2020-10-22 10:13:00', 15, '2020-10-22 10:13:11', '2020-10-22 10:13:24', '2020-10-22 10:13:18', '2020-10-22 10:13:24'),
(23, 1, 1, 2, 3, '卫津路校区102室109号', 2, 2, '2020-10-25 06:20:14', 15, NULL, NULL, NULL, '2020-10-25 06:20:14');

-- --------------------------------------------------------

--
-- 表的结构 `student`
--

CREATE TABLE `student` (
  `id` int(64) NOT NULL COMMENT '学生id',
  `name` varchar(64) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '学生姓名',
  `number` varchar(64) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '学生学号',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '密码',
  `phone` varchar(32) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '手机号',
  `status` int(32) NOT NULL DEFAULT '1' COMMENT '状态：1 正常 2 停用 3 黑名单',
  `violation_time` int(11) NOT NULL DEFAULT '0' COMMENT '违规次数',
  `blacklist_time` timestamp NULL DEFAULT NULL COMMENT '拉入黑名单时间',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '数据创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `school_id` int(64) NOT NULL COMMENT '学校id'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='学生信息';

--
-- 转存表中的数据 `student`
--

INSERT INTO `student` (`id`, `name`, `number`, `password`, `phone`, `status`, `violation_time`, `blacklist_time`, `create_time`, `update_time`, `school_id`) VALUES
(1, '测试学生1', '2019229010', '21232F297A57A5A743894A0E4A801FC3', '13565495631', 1, 2, NULL, '2020-10-16 07:55:52', '2020-10-22 09:16:58', 1),
(2, '吕鹏浩', '2019229034', '21232F297A57A5A743894A0E4A801FC3', '15626439719', 1, 0, NULL, '2020-10-22 09:36:11', '2020-10-22 09:36:11', 1);

-- --------------------------------------------------------

--
-- 表的结构 `sys_authority`
--

CREATE TABLE `sys_authority` (
  `authority_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '权限id',
  `authority_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '权限名称，ROLE_开头，全大写',
  `authority_remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '权限描述',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  `authority_content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '权限内容，可访问的url，多个时用,隔开'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统权限表' ROW_FORMAT=COMPACT;

--
-- 转存表中的数据 `sys_authority`
--

INSERT INTO `sys_authority` (`authority_id`, `authority_name`, `authority_remark`, `create_time`, `update_time`, `authority_content`) VALUES
('3fb1c570496d4c09ab99b8d31b06ccc', 'ROLE_USER', '普通用户', '2019-09-10 10:08:58', '2019-09-10 10:08:58', ''),
('3fb1c570496d4c09ab99b8d31b06xxx', 'ROLE_SA', '超级管理员', '2019-09-10 10:08:58', '2019-09-10 10:08:58', '/sys/**,/logging'),
('3fb1c570496d4c09ab99b8d31b06zzz', 'ROLE_ADMIN', '管理员', '2019-09-10 10:08:58', '2019-09-10 10:08:58', '/sys/**');

-- --------------------------------------------------------

--
-- 表的结构 `sys_menu`
--

CREATE TABLE `sys_menu` (
  `menu_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '菜单id',
  `menu_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '菜单名称',
  `menu_path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '菜单路径',
  `menu_parent_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '上级id',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '修改时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统菜单表' ROW_FORMAT=COMPACT;

--
-- 转存表中的数据 `sys_menu`
--

INSERT INTO `sys_menu` (`menu_id`, `menu_name`, `menu_path`, `menu_parent_id`, `create_time`, `update_time`) VALUES
('35cb950cebb04bb18bb1d8b742a02xaa', '权限管理', '/sys/sysAuthority/authority', '35cb950cebb04bb18bb1d8b742a02xxx', '2019-09-10 10:08:58', '2019-09-10 10:08:58'),
('35cb950cebb04bb18bb1d8b742a02xcc', '菜单管理', '/sys/sysMenu/menu', '35cb950cebb04bb18bb1d8b742a02xxx', '2019-09-10 10:08:58', '2019-09-10 10:08:58'),
('35cb950cebb04bb18bb1d8b742a02xxx', '系统管理', '/sys', '', '2019-09-10 10:08:58', '2019-09-10 10:08:58'),
('35cb950cebb04bb18bb1d8b742a02xzz', '用户管理', '/sys/sysUser/user', '35cb950cebb04bb18bb1d8b742a02xxx', '2019-09-10 10:08:58', '2019-09-10 10:08:58'),
('74315e162f524a4d88aa931f02416f26', '实时监控', '/monitor', '35cb950cebb04bb18bb1d8b742a02xxx', '2020-06-10 15:07:07', '2020-06-10 15:07:07'),
('914aa22c78af4327822061f3eada4067', '实时日志', '/logging', '35cb950cebb04bb18bb1d8b742a02xxx', '2019-09-11 11:19:52', '2019-09-11 11:19:52'),
('bcf17dc0ce304f0ba02d64ce21ddb4f9', '系统设置', '/sys/sysSetting/setting', '35cb950cebb04bb18bb1d8b742a02xxx', '2019-09-17 10:46:11', '2019-09-17 10:46:11');

-- --------------------------------------------------------

--
-- 表的结构 `sys_setting`
--

CREATE TABLE `sys_setting` (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '表id',
  `sys_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '系统名称',
  `sys_logo` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '系统logo图标',
  `sys_bottom_text` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '系统底部信息',
  `sys_notice_text` longtext CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '系统公告',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `user_init_password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '用户管理：初始、重置密码',
  `sys_color` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '系统颜色',
  `sys_api_encrypt` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT 'API加密 Y/N'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统设置表' ROW_FORMAT=COMPACT;

--
-- 转存表中的数据 `sys_setting`
--

INSERT INTO `sys_setting` (`id`, `sys_name`, `sys_logo`, `sys_bottom_text`, `sys_notice_text`, `create_time`, `update_time`, `user_init_password`, `sys_color`, `sys_api_encrypt`) VALUES
('1', '图书馆座位预约管理系统', 'https://avatar.gitee.com/uploads/0/5137900_huanzi-qch.png!avatar100?1562729811', '© 2019 - 2020  图书馆座位预约管理系统', '<h1 style=\"white-space: normal; text-align: center;\"><span style=\"color: rgb(255, 0, 0);\">通知</span></h1><p style=\"white-space: normal;\"><font color=\"#ff0000\">无</font></p><p><br/></p>', '2019-09-17 10:15:38', '2020-10-22 15:18:41', '123456', 'rgba(54, 123, 183,  0.73)', 'Y');

-- --------------------------------------------------------

--
-- 表的结构 `sys_shortcut_menu`
--

CREATE TABLE `sys_shortcut_menu` (
  `shortcut_menu_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户快捷菜单id',
  `shortcut_menu_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户快捷菜单名称',
  `shortcut_menu_path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户快捷菜单路径',
  `user_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户id',
  `shortcut_menu_parent_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '上级id',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '修改时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户快捷菜单表' ROW_FORMAT=COMPACT;

--
-- 转存表中的数据 `sys_shortcut_menu`
--

INSERT INTO `sys_shortcut_menu` (`shortcut_menu_id`, `shortcut_menu_name`, `shortcut_menu_path`, `user_id`, `shortcut_menu_parent_id`, `create_time`, `update_time`) VALUES
('104370a3fa7948bab156afd4a5f2a730', '个性化菜单', '', '1', '', '2019-09-12 18:35:13', '2019-09-12 18:35:13'),
('72d94b41b9994038bd2f2135a1de28d8', '快捷菜单', '', 'b5ac62e154964151a19c565346bb354a', '', '2019-09-17 14:36:28', '2019-09-17 14:36:28'),
('88353f04ad5d47b182c984bfbb1693cc', 'ggg', '/xxx', 'b5ac62e154964151a19c565346bb354a', '72d94b41b9994038bd2f2135a1de28d8', '2019-09-17 14:36:50', '2019-09-17 14:36:50'),
('cf78ced9ce7b480c85812540d1936145', '百度', 'https://www.baidu.com', '1', '104370a3fa7948bab156afd4a5f2a730', '2019-09-12 18:35:39', '2019-09-12 18:35:39');

-- --------------------------------------------------------

--
-- 表的结构 `sys_user`
--

CREATE TABLE `sys_user` (
  `user_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户id',
  `login_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '登录名',
  `user_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名称',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '登录密码',
  `valid` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '软删除标识，Y/N',
  `limited_ip` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '限制允许登录的IP集合',
  `expired_time` datetime DEFAULT NULL COMMENT '账号失效时间，超过时间将不能登录系统',
  `last_change_pwd_time` datetime NOT NULL COMMENT '最近修改密码时间，超出时间间隔，提示用户修改密码',
  `limit_multi_login` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '是否允许账号同一个时刻多人在线，Y/N',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '修改时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统用户表' ROW_FORMAT=COMPACT;

--
-- 转存表中的数据 `sys_user`
--

INSERT INTO `sys_user` (`user_id`, `login_name`, `user_name`, `password`, `valid`, `limited_ip`, `expired_time`, `last_change_pwd_time`, `limit_multi_login`, `create_time`, `update_time`) VALUES
('1', 'sa', '超级管理员', 'E10ADC3949BA59ABBE56E057F20F883E', 'Y', '', NULL, '2019-09-17 12:00:36', 'Y', '2019-07-19 16:36:03', '2020-10-22 15:18:59'),
('2', 'admin', '管理员', 'E10ADC3949BA59ABBE56E057F20F883E', 'Y', '', NULL, '2019-09-17 12:00:36', 'N', '2019-07-19 16:36:03', '2019-09-12 16:14:28');

-- --------------------------------------------------------

--
-- 表的结构 `sys_user_authority`
--

CREATE TABLE `sys_user_authority` (
  `user_authority_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户权限表id',
  `user_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户id',
  `authority_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '权限id',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '修改时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户权限表' ROW_FORMAT=COMPACT;

--
-- 转存表中的数据 `sys_user_authority`
--

INSERT INTO `sys_user_authority` (`user_authority_id`, `user_id`, `authority_id`, `create_time`, `update_time`) VALUES
('0dc1b156ed544c0986823e9cd818da08', '2', '3fb1c570496d4c09ab99b8d31b06ccc', '2019-09-12 16:14:28', '2019-09-12 16:14:28'),
('90c18739f3ad41ae8010f6c2b7eeaac5', '3fb1c570496d4c09ab99b8d31b0671cf', '3fb1c570496d4c09ab99b8d31b06ccc', '2019-09-17 12:09:47', '2019-09-17 12:09:47'),
('9ca34956ceae4af0a74f4931344e9d1b', '1', '3fb1c570496d4c09ab99b8d31b06ccc', '2019-09-17 12:00:37', '2019-09-17 12:00:37'),
('a414567aaae54b42b8344da02795cb91', '2', '3fb1c570496d4c09ab99b8d31b06zzz', '2019-09-12 16:14:28', '2019-09-12 16:14:28'),
('b34f7092406c46189fee2690d9f6e493', '1', '3fb1c570496d4c09ab99b8d31b06xxx', '2019-09-17 12:00:37', '2019-09-17 12:00:37'),
('de60e5bbbacf4c739e44a60130d0f534', 'b5ac62e154964151a19c565346bb354a', '3fb1c570496d4c09ab99b8d31b06ccc', '2019-09-17 14:28:58', '2019-09-17 14:28:58'),
('f6514b57d1524afd8dfa7cb2c3ca6a11', '1', '3fb1c570496d4c09ab99b8d31b06zzz', '2019-09-17 12:00:37', '2019-09-17 12:00:37');

-- --------------------------------------------------------

--
-- 表的结构 `sys_user_menu`
--

CREATE TABLE `sys_user_menu` (
  `user_menu_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户菜单表id',
  `user_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户id',
  `menu_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '菜单id',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '修改时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户菜单表' ROW_FORMAT=COMPACT;

--
-- 转存表中的数据 `sys_user_menu`
--

INSERT INTO `sys_user_menu` (`user_menu_id`, `user_id`, `menu_id`, `create_time`, `update_time`) VALUES
('1337996a0aba460bbf0b82db9a1da207', '1', '35cb950cebb04bb18bb1d8b742a02xxx', '2020-06-10 15:07:23', '2020-06-10 15:07:23'),
('3232782f25ec44b09438ab9805b85f83', '2', '35cb950cebb04bb18bb1d8b742a02xcc', '2019-09-12 16:14:28', '2019-09-12 16:14:28'),
('39d6a157972e400fabcd753d3766efc9', '1', '35cb950cebb04bb18bb1d8b742a02xaa', '2020-06-10 15:07:23', '2020-06-10 15:07:23'),
('3b0a160e8c624b2f86918afcd5107704', '1', '35cb950cebb04bb18bb1d8b742a02xcc', '2020-06-10 15:07:23', '2020-06-10 15:07:23'),
('57791437b9774d8abf74562a49c55a1a', '2', '35cb950cebb04bb18bb1d8b742a02xxx', '2019-09-12 16:14:28', '2019-09-12 16:14:28'),
('6afadafdc36c426182853761bf68d870', '1', '74315e162f524a4d88aa931f02416f26', '2020-06-10 15:07:23', '2020-06-10 15:07:23'),
('81f4999dde514e0ea43acfc70bfd35a8', '1', '914aa22c78af4327822061f3eada4067', '2020-06-10 15:07:23', '2020-06-10 15:07:23'),
('9f8ccddc9fa84e0b9ff74128d20e9024', '2', '35cb950cebb04bb18bb1d8b742a02xaa', '2019-09-12 16:14:28', '2019-09-12 16:14:28'),
('c4220e4602fd4f2ca70da046466c6b45', '2', '35cb950cebb04bb18bb1d8b742a02xzz', '2019-09-12 16:14:28', '2019-09-12 16:14:28'),
('cdf8f786c658437ba77eb7d7fdd6b9cb', '1', '35cb950cebb04bb18bb1d8b742a02xzz', '2020-06-10 15:07:23', '2020-06-10 15:07:23'),
('d646090ba4114c85b0a2fc2c9082a188', '1', 'bcf17dc0ce304f0ba02d64ce21ddb4f9', '2020-06-10 15:07:23', '2020-06-10 15:07:23');

-- --------------------------------------------------------

--
-- 表的结构 `user`
--

CREATE TABLE `user` (
  `id` int(64) NOT NULL,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '姓名',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '密码',
  `login_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '登录用户名',
  `phone` varchar(32) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '手机号',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间'
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- 表的结构 `violation`
--

CREATE TABLE `violation` (
  `id` int(64) NOT NULL,
  `student_id` int(11) NOT NULL COMMENT '学生Id',
  `reason` varchar(255) COLLATE utf8_unicode_ci NOT NULL COMMENT '违规原因',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '违规时间',
  `seat_id` int(64) NOT NULL COMMENT '座位Id',
  `update_time` timestamp NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间'
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- 转储表的索引
--

--
-- 表的索引 `persistent_logins`
--
ALTER TABLE `persistent_logins`
  ADD PRIMARY KEY (`series`);

--
-- 表的索引 `room`
--
ALTER TABLE `room`
  ADD PRIMARY KEY (`id`);

--
-- 表的索引 `school`
--
ALTER TABLE `school`
  ADD PRIMARY KEY (`id`);

--
-- 表的索引 `seat`
--
ALTER TABLE `seat`
  ADD PRIMARY KEY (`id`);

--
-- 表的索引 `student`
--
ALTER TABLE `student`
  ADD PRIMARY KEY (`id`);

--
-- 表的索引 `sys_authority`
--
ALTER TABLE `sys_authority`
  ADD PRIMARY KEY (`authority_id`) USING BTREE;

--
-- 表的索引 `sys_menu`
--
ALTER TABLE `sys_menu`
  ADD PRIMARY KEY (`menu_id`) USING BTREE;

--
-- 表的索引 `sys_setting`
--
ALTER TABLE `sys_setting`
  ADD PRIMARY KEY (`id`) USING BTREE;

--
-- 表的索引 `sys_shortcut_menu`
--
ALTER TABLE `sys_shortcut_menu`
  ADD PRIMARY KEY (`shortcut_menu_id`) USING BTREE;

--
-- 表的索引 `sys_user`
--
ALTER TABLE `sys_user`
  ADD PRIMARY KEY (`user_id`) USING BTREE;

--
-- 表的索引 `sys_user_authority`
--
ALTER TABLE `sys_user_authority`
  ADD PRIMARY KEY (`user_authority_id`) USING BTREE;

--
-- 表的索引 `sys_user_menu`
--
ALTER TABLE `sys_user_menu`
  ADD PRIMARY KEY (`user_menu_id`) USING BTREE;

--
-- 表的索引 `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- 表的索引 `violation`
--
ALTER TABLE `violation`
  ADD PRIMARY KEY (`id`);

--
-- 在导出的表使用AUTO_INCREMENT
--

--
-- 使用表AUTO_INCREMENT `room`
--
ALTER TABLE `room`
  MODIFY `id` int(64) NOT NULL AUTO_INCREMENT COMMENT '自习室id', AUTO_INCREMENT=6;

--
-- 使用表AUTO_INCREMENT `school`
--
ALTER TABLE `school`
  MODIFY `id` int(64) NOT NULL AUTO_INCREMENT COMMENT '学校id', AUTO_INCREMENT=6;

--
-- 使用表AUTO_INCREMENT `seat`
--
ALTER TABLE `seat`
  MODIFY `id` int(64) NOT NULL AUTO_INCREMENT COMMENT '座位id', AUTO_INCREMENT=24;

--
-- 使用表AUTO_INCREMENT `student`
--
ALTER TABLE `student`
  MODIFY `id` int(64) NOT NULL AUTO_INCREMENT COMMENT '学生id', AUTO_INCREMENT=3;

--
-- 使用表AUTO_INCREMENT `user`
--
ALTER TABLE `user`
  MODIFY `id` int(64) NOT NULL AUTO_INCREMENT;

--
-- 使用表AUTO_INCREMENT `violation`
--
ALTER TABLE `violation`
  MODIFY `id` int(64) NOT NULL AUTO_INCREMENT;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
