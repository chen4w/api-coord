/*
 Navicat Premium Data Transfer

 Source Server         : 192.168.1.111
 Source Server Type    : MySQL
 Source Server Version : 50734
 Source Host           : 192.168.1.111:3306
 Source Schema         : coord

 Target Server Type    : MySQL
 Target Server Version : 50734
 File Encoding         : 65001

 Date: 22/10/2021 10:27:53
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for header
-- ----------------------------
DROP TABLE IF EXISTS `header`;
CREATE TABLE `header` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `cid` varchar(255) DEFAULT NULL,
  `e_from` varchar(255) DEFAULT NULL,
  `e_to` varchar(255) DEFAULT NULL,
  `method` varchar(255) DEFAULT NULL,
  `b_req` bit(1) DEFAULT NULL,
  `b_end` bit(1) DEFAULT NULL,
  `seq` int(11) DEFAULT NULL,
  `tm_create` bigint(20) DEFAULT NULL,
  `callback_url` varchar(255) DEFAULT NULL,
  `callback_method` varchar(255) DEFAULT NULL,
  `valid_str` varchar(255) DEFAULT NULL,
  `sign_data` varchar(255) DEFAULT NULL,
  `data` varchar(1000) DEFAULT NULL,
  `state` int(255) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

SET FOREIGN_KEY_CHECKS = 1;
