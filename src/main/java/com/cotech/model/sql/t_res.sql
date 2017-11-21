/*
Navicat MySQL Data Transfer

Source Server         : czwTest
Source Server Version : 50719
Source Host           : 103.37.156.69:5002
Source Database       : czwtest

Target Server Type    : MYSQL
Target Server Version : 50719
File Encoding         : 65001

Date: 2017-11-21 11:32:15
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_res
-- ----------------------------
DROP TABLE IF EXISTS `t_res`;
CREATE TABLE `t_res` (
  `res_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `cookie_id` char(40) COLLATE utf8mb4_bin DEFAULT NULL,
  `coordinate` text COLLATE utf8mb4_bin COMMENT '坐标点集',
  `ip` varchar(128) COLLATE utf8mb4_bin DEFAULT '' COMMENT 'ip地址',
  `device` text COLLATE utf8mb4_bin COMMENT '设备名',
  `sex` tinyint(1) DEFAULT '0' COMMENT '性别：0男，1女',
  `constellation` tinyint(2) DEFAULT '0' COMMENT '星座',
  `blood` tinyint(2) DEFAULT '0' COMMENT '血型',
  `address` varchar(200) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '联系方式',
  `score` int(10) NOT NULL DEFAULT '0' COMMENT '分数',
  `gold` int(10) DEFAULT '0' COMMENT '金币',
  `group` int(11) DEFAULT '0' COMMENT '组别',
  `create_time` datetime DEFAULT NULL COMMENT '录入时间',
  `referee` int(11) DEFAULT NULL COMMENT '推荐人',
  `deviation` varchar(7) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '偏离度',
  `time_len` int(8) DEFAULT NULL COMMENT '画圈所用时间',
  `location_id` varchar(33) COLLATE utf8mb4_bin DEFAULT NULL,
  PRIMARY KEY (`res_id`),
  UNIQUE KEY `idx_location` (`location_id`) USING HASH,
  KEY `idx_userid` (`user_id`) USING BTREE,
  KEY `idx_group` (`group`),
  KEY `idx_cookie_id` (`cookie_id`) USING HASH
) ENGINE=InnoDB AUTO_INCREMENT=1241 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
