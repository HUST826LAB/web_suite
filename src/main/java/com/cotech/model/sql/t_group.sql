/*
Navicat MySQL Data Transfer

Source Server         : czwTest
Source Server Version : 50719
Source Host           : 103.37.156.69:5002
Source Database       : czwtest

Target Server Type    : MYSQL
Target Server Version : 50719
File Encoding         : 65001

Date: 2017-11-21 11:32:06
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_group
-- ----------------------------
DROP TABLE IF EXISTS `t_group`;
CREATE TABLE `t_group` (
  `group_id` char(40) COLLATE utf8mb4_bin NOT NULL COMMENT '主键ID',
  `name` varchar(40) COLLATE utf8mb4_bin DEFAULT NULL,
  `numSum` int(10) DEFAULT NULL,
  `score` int(10) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `owner` int(10) DEFAULT NULL,
  `creator` int(10) DEFAULT NULL,
  PRIMARY KEY (`group_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
