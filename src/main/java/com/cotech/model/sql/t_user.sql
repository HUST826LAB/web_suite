/*
Navicat MySQL Data Transfer

Source Server         : czwTest
Source Server Version : 50719
Source Host           : 103.37.156.69:5002
Source Database       : czwtest

Target Server Type    : MYSQL
Target Server Version : 50719
File Encoding         : 65001

Date: 2017-11-21 11:32:21
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `username` varchar(20) COLLATE utf8mb4_bin DEFAULT '' COMMENT '用户名',
  `password` varchar(40) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '密码行',
  `uname` varchar(20) COLLATE utf8mb4_bin DEFAULT '' COMMENT '用户名',
  `ip` varchar(128) COLLATE utf8mb4_bin DEFAULT '' COMMENT 'ip地址',
  `device` text COLLATE utf8mb4_bin COMMENT '设备名',
  `sex` tinyint(1) DEFAULT '0' COMMENT '性别：0男，1女',
  `constellation` tinyint(2) DEFAULT '0' COMMENT '星座',
  `blood` tinyint(2) DEFAULT '0' COMMENT '血型',
  `address` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '联系方式',
  `score` int(10) DEFAULT '0' COMMENT '分数',
  `gold` int(10) DEFAULT '0' COMMENT '金币',
  `group` int(11) DEFAULT '0' COMMENT '组别',
  `star_a` tinyint(4) DEFAULT '0',
  `star_b` tinyint(4) DEFAULT '0',
  `star_c` tinyint(4) DEFAULT '0',
  `star_d` tinyint(4) DEFAULT '0',
  `star_e` tinyint(4) DEFAULT '0',
  `star_f` tinyint(4) DEFAULT '0',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `approve_time` datetime DEFAULT NULL COMMENT '审批时间',
  `status` tinyint(4) DEFAULT '0' COMMENT '权限状态',
  `approver` int(11) DEFAULT NULL COMMENT '审批人',
  `location_id` varchar(33) COLLATE utf8mb4_bin DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `idx_location` (`location_id`) USING HASH,
  KEY `idx_gold` (`gold`) USING BTREE,
  KEY `idx_score` (`score`) USING BTREE,
  KEY `idx_group_id` (`group`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
