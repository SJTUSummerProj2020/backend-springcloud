/*
 Navicat Premium Data Transfer

 Source Server         : MySQL
 Source Server Type    : MySQL
 Source Server Version : 50725
 Source Host           : localhost:3306
 Source Schema         : jupiter_cloud

 Target Server Type    : MySQL
 Target Server Version : 50725
 File Encoding         : 65001

 Date: 18/08/2020 23:06:51
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `user_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `user_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
  `nick_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户昵称',
  `password` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户密码',
  `phone` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '手机号码',
  `user_type` tinyint(4) NOT NULL DEFAULT 1 COMMENT '用户类型，-1禁用，0管理员，1普通用户',
  `buy0` int(11) NOT NULL DEFAULT 0,
  `buy1` int(11) NOT NULL DEFAULT 0,
  `buy2` int(11) NOT NULL DEFAULT 0,
  `buy3` int(11) NOT NULL DEFAULT 0,
  `buy4` int(11) NOT NULL DEFAULT 0,
  `buy5` int(11) NOT NULL DEFAULT 0,
  PRIMARY KEY (`user_id`) USING BTREE,
  UNIQUE INDEX `user_name_index`(`user_name`) USING BTREE COMMENT 'user_name的index'
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'root', 'root', 'root', '1', 0, 0, 0, 0, 0, 0, 0);
INSERT INTO `user` VALUES (2, 'user', 'user', 'user', '2', 1, 0, 0, 0, 0, 0, 0);
INSERT INTO `user` VALUES (3, 'ban', 'ban', 'ban', '3', -1, 0, 0, 0, 0, 0, 0);

SET FOREIGN_KEY_CHECKS = 1;
