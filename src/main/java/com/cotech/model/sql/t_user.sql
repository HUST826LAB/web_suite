/*
Navicat MySQL Data Transfer

Source Server         : root
Source Server Version : 50719
Source Host           : localhost:3306
Source Database       : web_suite

Target Server Type    : MYSQL
Target Server Version : 50719
File Encoding         : 65001

Date: 2017-10-23 16:10:50
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `username` varchar(20) COLLATE utf8mb4_bin DEFAULT '' COMMENT '用户名',
  `uname` varchar(20) COLLATE utf8mb4_bin DEFAULT '' COMMENT '用户名',
  `ip` varchar(128) COLLATE utf8mb4_bin DEFAULT '' COMMENT 'ip地址',
  `device` varchar(40) COLLATE utf8mb4_bin DEFAULT '' COMMENT '设备名',
  `sex` tinyint(1) DEFAULT '0' COMMENT '性别：0男，1女',
  `constellation` tinyint(2) DEFAULT '0' COMMENT '星座',
  `blood` tinyint(2) DEFAULT '0' COMMENT '血型',
  `address` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '联系方式',
  `score` int(10) DEFAULT '0' COMMENT '分数',
  `gold` int(10) DEFAULT '0' COMMENT '金币',
  `group` varchar(20) COLLATE utf8mb4_bin DEFAULT '' COMMENT '组别',
  `star_a` tinyint(4) DEFAULT '0',
  `star_b` tinyint(4) DEFAULT '0',
  `star_c` tinyint(4) DEFAULT '0',
  `star_d` tinyint(4) DEFAULT '0',
  `star_e` tinyint(4) DEFAULT '0',
  `star_f` tinyint(4) DEFAULT '0',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `approve_time` datetime DEFAULT NULL COMMENT '审批时间',
  `status` tinyint(4) DEFAULT NULL COMMENT '权限状态',
  `approver` int(11) DEFAULT NULL COMMENT '审批人',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `gold_idx` (`gold`) USING BTREE,
  UNIQUE KEY `score_idx` (`score`) USING BTREE,
  FULLTEXT KEY `group_idx` (`group`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
