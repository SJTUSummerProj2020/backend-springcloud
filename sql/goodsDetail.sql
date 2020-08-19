DROP TABLE IF EXISTS `goodsdetail`;
CREATE TABLE `goodsdetail`  (
  `detail_id` int(11) NOT NULL AUTO_INCREMENT,
  `goods_id` int(11) NULL DEFAULT NULL,
  `price` float NOT NULL,
  `surplus` int(11) NOT NULL,
  `time` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `ticket_type` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`detail_id`) USING BTREE,
  INDEX `FK_Reference_3`(`goods_id`) USING BTREE,
  CONSTRAINT `FK_Reference_3` FOREIGN KEY (`goods_id`) REFERENCES `goods` (`goods_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of goodsdetail
-- ----------------------------
INSERT INTO `goodsdetail` VALUES (1, 1, 188, 1, '2020-08-07 星期五 20:00', '预售票（188.00）');
INSERT INTO `goodsdetail` VALUES (2, 2, 80, 1, '2020-07-09 周四 21:00', '80元（预售票）');
INSERT INTO `goodsdetail` VALUES (3, 3, 100, 1, '2020-07-10 周五 21:00', '100元（预售票）');
INSERT INTO `goodsdetail` VALUES (4, 4, 100, 1, '2020-07-11 周六 21:00', '100元（预售票）');