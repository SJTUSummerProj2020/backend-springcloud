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

 Date: 19/08/2020 19:13:33
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for orderlist
-- ----------------------------
DROP TABLE IF EXISTS `c_order`;
CREATE TABLE `c_order`  (
  `order_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '订单号',
  `user_id` int(11) NOT NULL COMMENT '用户号',
  `detail_id` int(11) NOT NULL COMMENT '商品子项目号',
  `goods_id` int(11) NOT NULL COMMENT '商品号',
  `number` int(11) NOT NULL COMMENT '数目',
  `price` float NOT NULL COMMENT '价格',
  `source_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '原站票号',
  `order_status` tinyint(4) NOT NULL DEFAULT 0 COMMENT '0 创建 1 完成 -1 删除',
  `time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  PRIMARY KEY (`order_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '订单' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of orderlist
-- ----------------------------
INSERT INTO `c_order` VALUES (1, 1, 1891, 514, 1, 80, 'test12313', 0, '2020-08-19 19:11:28');

SET FOREIGN_KEY_CHECKS = 1;
