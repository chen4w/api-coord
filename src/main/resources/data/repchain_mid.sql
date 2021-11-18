/*
 Navicat Premium Data Transfer

 Source Server         : 192.168.1.111
 Source Server Type    : MySQL
 Source Server Version : 50734
 Source Host           : 192.168.1.111:3306
 Source Schema         : repchain_mid

 Target Server Type    : MySQL
 Target Server Version : 50734
 File Encoding         : 65001

 Date: 18/11/2021 10:01:19
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for api_definition
-- ----------------------------
DROP TABLE IF EXISTS `api_definition`;
CREATE TABLE `api_definition` (
  `id` varchar(255) NOT NULL,
  `obj` mediumtext,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for api_serv_and_ack
-- ----------------------------
DROP TABLE IF EXISTS `api_serv_and_ack`;
CREATE TABLE `api_serv_and_ack` (
  `id` varchar(255) NOT NULL,
  `obj` mediumtext,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for async_header
-- ----------------------------
DROP TABLE IF EXISTS `async_header`;
CREATE TABLE `async_header` (
  `id` varchar(255) NOT NULL,
  `obj` mediumtext,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for persistence
-- ----------------------------
DROP TABLE IF EXISTS `persistence`;
CREATE TABLE `persistence` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `cid` varchar(255) DEFAULT NULL,
  `header` mediumtext,
  `result` mediumtext,
  `send_file` mediumtext,
  `download_file` mediumtext,
  `time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `ckey` (`cid`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for persistence_result
-- ----------------------------
DROP TABLE IF EXISTS `persistence_result`;
CREATE TABLE `persistence_result` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `cid` varchar(255) DEFAULT NULL,
  `header` mediumtext,
  `result` mediumtext,
  `send_file` mediumtext,
  `download_file` mediumtext,
  `time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `ckey` (`cid`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for repchain
-- ----------------------------
DROP TABLE IF EXISTS `repchain`;
CREATE TABLE `repchain` (
  `id` varchar(20) NOT NULL,
  `obj` mediumtext,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for req_ack_proof
-- ----------------------------
DROP TABLE IF EXISTS `req_ack_proof`;
CREATE TABLE `req_ack_proof` (
  `id` varchar(255) NOT NULL,
  `obj` mediumtext,
  PRIMARY KEY (`id`),
  UNIQUE KEY `zxcasd` (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

SET FOREIGN_KEY_CHECKS = 1;
