/*
 Navicat Premium Data Transfer

 Source Server         : test
 Source Server Type    : MySQL
 Source Server Version : 80019
 Source Host           : localhost:3306
 Source Schema         : ebookshare

 Target Server Type    : MySQL
 Target Server Version : 80019
 File Encoding         : 65001

 Date: 16/04/2020 23:05:46
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for book
-- ----------------------------
DROP TABLE IF EXISTS `book`;
CREATE TABLE `book`  (
  `book_id` int(0) NOT NULL AUTO_INCREMENT COMMENT '书籍id',
  `user_id` int(0) NOT NULL COMMENT '上传用户id',
  `file_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '解压书籍后对应文件夹',
  `book_category` int(0) NULL DEFAULT NULL COMMENT '分类所在 ，-1为未分类',
  `is_share` int(0) NULL DEFAULT 0 COMMENT '是否可分享，1：是，0：否 。默认为否',
  `upload_date` datetime(0) NULL DEFAULT NULL COMMENT '上传时间',
  PRIMARY KEY (`book_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 73 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------

-- ----------------------------
-- Table structure for book_comment
-- ----------------------------
DROP TABLE IF EXISTS `book_comment`;
CREATE TABLE `book_comment`  (
  `comment_id` int(0) NOT NULL AUTO_INCREMENT,
  `user_id` int(0) NOT NULL,
  `content` varchar(511) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `update_date` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  `create_date` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`comment_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of book_comment
-- ----------------------------

-- ----------------------------
-- Table structure for book_info
-- ----------------------------
DROP TABLE IF EXISTS `book_info`;
CREATE TABLE `book_info`  (
  `book_info_id` int(0) NOT NULL AUTO_INCREMENT COMMENT 'book_info表id',
  `book_id` int(0) NOT NULL COMMENT 'book表id',
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '书名',
  `introduction` varchar(511) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '简介',
  `language` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '语言',
  `publisher` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '出版社',
  `creator` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '作者',
  `ISBN` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '国际标准书号',
  `publish_date` datetime(0) NULL DEFAULT NULL COMMENT '出版时间',
  PRIMARY KEY (`book_info_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 64 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for book_mark
-- ----------------------------
DROP TABLE IF EXISTS `book_mark`;
CREATE TABLE `book_mark`  (
  `book_mark_id` int(0) NOT NULL AUTO_INCREMENT,
  `user_id` int(0) NOT NULL,
  `book_id` int(0) NOT NULL,
  `chapters` varchar(511) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `create_date` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`book_mark_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category`  (
  `category_id` int(0) NOT NULL AUTO_INCREMENT COMMENT '分类id',
  `category_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '书籍类别名',
  PRIMARY KEY (`category_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of category
-- ----------------------------
INSERT INTO `category` VALUES (1, '文学艺术');
INSERT INTO `category` VALUES (2, '名著经典');
INSERT INTO `category` VALUES (3, '青春言情');
INSERT INTO `category` VALUES (4, '推理悬疑');
INSERT INTO `category` VALUES (5, '影视原著');

-- ----------------------------
-- Table structure for read_history
-- ----------------------------
DROP TABLE IF EXISTS `read_history`;
CREATE TABLE `read_history`  (
  `read_history_id` int(0) NOT NULL AUTO_INCREMENT COMMENT '阅读记录id',
  `book_id` int(0) NOT NULL COMMENT '书籍id',
  `user_id` int(0) NOT NULL COMMENT '用户id',
  `chapter` varchar(511) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '对应章节',
  `update_date` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`read_history_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for self
-- ----------------------------
DROP TABLE IF EXISTS `self`;
CREATE TABLE `self`  (
  `self_id` int(0) NOT NULL AUTO_INCREMENT COMMENT '用户书架id',
  `user_id` int(0) NOT NULL COMMENT '用户id',
  `book_id` int(0) NOT NULL COMMENT '电子书id',
  `create_date` datetime(0) NULL DEFAULT NULL COMMENT '添加到书架时间',
  PRIMARY KEY (`self_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 20 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of self
-- ----------------------------
INSERT INTO `self` VALUES (23, 12, 34, '2020-04-16 23:02:19');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `user_id` int(0) NOT NULL AUTO_INCREMENT COMMENT '用户表uid',
  `user_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
  `user_password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户密码',
  `email` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户邮箱',
  `type` int(0) NOT NULL DEFAULT 1 COMMENT '账户类型：0，管理员；1，普通账户',
  `state` int(0) NOT NULL DEFAULT 0 COMMENT '账户状态：0，正常；1，冻结；',
  `update_date` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `create_date` datetime(0) NULL DEFAULT NULL COMMENT '用户创建日期',
  PRIMARY KEY (`user_id`) USING BTREE,
  UNIQUE INDEX `user_name`(`user_name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 24 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'admin', '123456', '315636696@qq.com', 0, 0, '2020-03-23 15:14:31', '2020-03-01 18:04:34');
SET FOREIGN_KEY_CHECKS = 1;
