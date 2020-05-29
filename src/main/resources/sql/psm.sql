/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 80018
Source Host           : localhost:3306
Source Database       : psm

Target Server Type    : MYSQL
Target Server Version : 80018
File Encoding         : 65001

Date: 2020-05-29 10:06:08
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for collection
-- ----------------------------
DROP TABLE IF EXISTS `collection`;
CREATE TABLE `collection` (
  `id` varchar(255) NOT NULL,
  `mid` int(11) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of collection
-- ----------------------------
INSERT INTO `collection` VALUES ('051411f5f2874407ae45f9a82d2665ad', '3', '15387315836');

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
  `id` varchar(255) NOT NULL,
  `content` varchar(255) DEFAULT NULL,
  `from_user` varchar(255) DEFAULT NULL,
  `mid` int(11) DEFAULT NULL,
  `to_user` varchar(255) DEFAULT NULL,
  `comment_time` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of comment
-- ----------------------------
INSERT INTO `comment` VALUES ('2e107137ade84b1ba21da3a12e5ebbbf', '你好，这是第一个回复', '17371447819', '1', '15387315836', '2020-05-28 01:13:10.178000');
INSERT INTO `comment` VALUES ('6b7cd0fc14fa4c808de40bd5a47a9d08', '你好，这是第二个评论', '15387315836', '1', '17371447819', '2020-05-28 01:12:46.523000');
INSERT INTO `comment` VALUES ('8bed86b7a60a47c1a88943e19bf0aa70', '你好，这是第一个评论', '15387315836', '1', '17371447819', '2020-05-28 01:12:11.875000');

-- ----------------------------
-- Table structure for missiontable
-- ----------------------------
DROP TABLE IF EXISTS `missiontable`;
CREATE TABLE `missiontable` (
  `mid` int(11) NOT NULL AUTO_INCREMENT COMMENT '任务id',
  `title` varchar(255) DEFAULT NULL COMMENT '任务标题',
  `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci COMMENT '任务描述',
  `post_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '任务发布时间',
  `end_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '结束时间',
  `state` int(11) DEFAULT NULL COMMENT '任务状态【0为未接收，1为接收，2为已完成】',
  `money` double DEFAULT NULL COMMENT '酬金',
  `label1` varchar(255) DEFAULT NULL COMMENT '标签1',
  `label2` varchar(255) DEFAULT NULL COMMENT '标签2',
  `label3` varchar(255) DEFAULT NULL COMMENT '标签3',
  `comment` text COMMENT '发布任务的人对任务完成情况进行评价',
  PRIMARY KEY (`mid`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of missiontable
-- ----------------------------
INSERT INTO `missiontable` VALUES ('1', '互联网大赛组队', '要求：大三，能熟练开发网页后台', '2020-05-26 08:21:09', '2020-05-30 08:20:51', '0', '100', 'IT/软件', null, null, null);
INSERT INTO `missiontable` VALUES ('2', '花旗杯组队', '要求：懂银行业务，以及相关金融知识', '2020-05-26 08:22:29', '2020-05-31 08:22:32', '0', '50', '其他', null, null, null);
INSERT INTO `missiontable` VALUES ('3', '求双休日照看小猫', '要求：每日定时喂食', '2020-05-26 08:23:17', '2020-05-29 08:23:20', '0', '500', '宠物', null, null, null);
INSERT INTO `missiontable` VALUES ('4', ' 求开发学生管理系统', '要求：懂spring，jquery，jsp等网页技术', '2020-05-26 08:25:03', '2020-05-26 08:25:03', '0', '1000', 'IT/软件', null, null, null);
INSERT INTO `missiontable` VALUES ('5', '实习招聘', '朋友想来字节实习吗，我们是广告系统穿山甲团队国际化方向的，今年大力发展国外市场，很缺人哈（暑期实习结束了，不过日常实习的转正和暑期没有区别', '2020-05-26 08:26:28', '2020-06-06 08:26:32', '0', '3000', 'IT/软件', '实习', null, null);
INSERT INTO `missiontable` VALUES ('6', '青协求帮租用场地', '计算机学院青年志愿者协会开讲座，请大家帮忙租借场地', '2020-05-26 08:27:22', '2020-06-05 08:27:25', '0', '20', '社团', null, null, null);
INSERT INTO `missiontable` VALUES ('7', '家教', '高三，双休日教4小时，每天2小时', '2020-05-26 08:28:55', '2020-06-28 08:28:59', '0', '5000', '家教', null, null, null);
INSERT INTO `missiontable` VALUES ('8', '收计网往年试卷', '收软件工程计网往年试卷', '2020-05-26 08:32:51', '2020-05-26 08:32:51', '0', '10', '二手物品', 'IT/软件', null, null);

-- ----------------------------
-- Table structure for post
-- ----------------------------
DROP TABLE IF EXISTS `post`;
CREATE TABLE `post` (
  `phone` varchar(255) NOT NULL,
  `mid` int(11) NOT NULL,
  PRIMARY KEY (`phone`,`mid`),
  KEY `mid` (`mid`),
  CONSTRAINT `mid` FOREIGN KEY (`mid`) REFERENCES `missiontable` (`mid`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `phone` FOREIGN KEY (`phone`) REFERENCES `user` (`phone`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of post
-- ----------------------------
INSERT INTO `post` VALUES ('15387315836', '1');
INSERT INTO `post` VALUES ('15387315836', '2');
INSERT INTO `post` VALUES ('15387315836', '3');
INSERT INTO `post` VALUES ('17371447819', '4');
INSERT INTO `post` VALUES ('17371447819', '5');
INSERT INTO `post` VALUES ('17371447819', '6');
INSERT INTO `post` VALUES ('15387315836', '7');
INSERT INTO `post` VALUES ('15387315836', '8');

-- ----------------------------
-- Table structure for rece
-- ----------------------------
DROP TABLE IF EXISTS `rece`;
CREATE TABLE `rece` (
  `phone` varchar(255) NOT NULL,
  `mid` int(11) NOT NULL,
  PRIMARY KEY (`phone`,`mid`),
  KEY `_mid` (`mid`),
  CONSTRAINT `_mid` FOREIGN KEY (`mid`) REFERENCES `missiontable` (`mid`) ON DELETE CASCADE ON UPDATE CASCADE,
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
INSERT INTO `user` VALUES ('15387315836', '123456', '/D:/course/psm/target/classes/static/res/images/icon/15387315836', null, '0', '100');
INSERT INTO `user` VALUES ('17371447819', '123456', null, null, '0', '100');
