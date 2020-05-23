/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 80018
Source Host           : localhost:3306
Source Database       : psm

Target Server Type    : MYSQL
Target Server Version : 80018
File Encoding         : 65001

Date: 2020-05-23 16:00:06
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for mission
-- ----------------------------
DROP TABLE IF EXISTS `mission`;
CREATE TABLE `mission` (
  `mid` int(11) NOT NULL AUTO_INCREMENT COMMENT '任务id',
  `title` varchar(255) DEFAULT NULL COMMENT '任务标题',
  `desc` text COMMENT '任务描述',
  `post_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '任务发布时间',
  `end_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '结束时间',
  `state` int(11) DEFAULT NULL COMMENT '任务状态【0为未接收，1为接收，2为已完成】',
  `money` double DEFAULT NULL COMMENT '酬金',
  `label1` varchar(255) DEFAULT NULL COMMENT '标签1',
  `label2` varchar(255) DEFAULT NULL COMMENT '标签2',
  `label3` varchar(255) DEFAULT NULL COMMENT '标签3',
  `comment` text COMMENT '发布任务的人对任务完成情况进行评价',
  PRIMARY KEY (`mid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of mission
-- ----------------------------

-- ----------------------------
-- Table structure for post
-- ----------------------------
DROP TABLE IF EXISTS `post`;
CREATE TABLE `post` (
  `phone` varchar(255) NOT NULL,
  `mid` int(11) NOT NULL,
  PRIMARY KEY (`phone`,`mid`),
  KEY `mid` (`mid`),
  CONSTRAINT `mid` FOREIGN KEY (`mid`) REFERENCES `mission` (`mid`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `phone` FOREIGN KEY (`phone`) REFERENCES `user` (`phone`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of post
-- ----------------------------

-- ----------------------------
-- Table structure for rece
-- ----------------------------
DROP TABLE IF EXISTS `rece`;
CREATE TABLE `rece` (
  `phone` varchar(255) NOT NULL,
  `mid` int(11) NOT NULL,
  PRIMARY KEY (`phone`,`mid`),
  KEY `_mid` (`mid`),
  CONSTRAINT `_mid` FOREIGN KEY (`mid`) REFERENCES `mission` (`mid`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `_phone` FOREIGN KEY (`phone`) REFERENCES `user` (`phone`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of rece
-- ----------------------------

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `phone` varchar(255) NOT NULL COMMENT '用户电话，也是主键',
  `pwd` varchar(255) DEFAULT NULL COMMENT '用户密码',
  `icon` varchar(255) DEFAULT NULL COMMENT '用户头像地址',
  `motto` varchar(255) DEFAULT NULL COMMENT '用户座右铭',
  `score` double DEFAULT NULL COMMENT '用户完成任务获得的积分',
  `credit` double DEFAULT NULL COMMENT '用户的信用积分',
  PRIMARY KEY (`phone`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of user
-- ----------------------------
