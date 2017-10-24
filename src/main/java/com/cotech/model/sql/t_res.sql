/*
Navicat MySQL Data Transfer

Source Server         : root
Source Server Version : 50719
Source Host           : localhost:3306
Source Database       : web_suite

Target Server Type    : MYSQL
Target Server Version : 50719
File Encoding         : 65001

Date: 2017-10-24 16:29:45
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_res
-- ----------------------------
DROP TABLE IF EXISTS `t_res`;
CREATE TABLE `t_res` (
  `res_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `coordinate` text COLLATE utf8mb4_bin COMMENT '坐标点集',
  `ip` varchar(128) COLLATE utf8mb4_bin DEFAULT '' COMMENT 'ip地址',
  `device` varchar(40) COLLATE utf8mb4_bin DEFAULT '' COMMENT '设备名',
  `sex` tinyint(1) DEFAULT '0' COMMENT '性别：0男，1女',
  `constellation` tinyint(2) DEFAULT '0' COMMENT '星座',
  `blood` tinyint(2) DEFAULT '0' COMMENT '血型',
  `address` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '联系方式',
  `score` int(10) DEFAULT '0' COMMENT '分数',
  `gold` int(10) DEFAULT '0' COMMENT '金币',
  `group` varchar(20) COLLATE utf8mb4_bin DEFAULT '' COMMENT '组别',
  `create_time` datetime DEFAULT NULL COMMENT '录入时间',
  `referee` int(11) DEFAULT NULL COMMENT '推荐人',
  `deviation` varchar(3) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '偏离度',
  `time_len` int(8) DEFAULT NULL COMMENT '画圈所用时间',
  PRIMARY KEY (`res_id`),
  UNIQUE KEY `idx_userid` (`user_id`) USING BTREE,
  FULLTEXT KEY `idx_group` (`group`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
