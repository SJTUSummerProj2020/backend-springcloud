DROP TABLE IF EXISTS `auction`;
CREATE TABLE `auction`  (
  `auction_id` int(11) NOT NULL AUTO_INCREMENT,
  `detail_id` int(11) NOT NULL,
  `goods_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `starting_price` float NOT NULL DEFAULT 0,
  `adding_price` float NOT NULL DEFAULT 0,
  `best_offer` float NOT NULL DEFAULT 0,
  `start_time` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `duration` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `goods` int(11) NULL DEFAULT NULL,
  `goods_detail` int(11) NULL DEFAULT NULL,
  `user` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`auction_id`) USING BTREE,
  INDEX `auction_goods`(`goods_id`) USING BTREE,
  INDEX `auction_goodsdetail`(`detail_id`) USING BTREE,
  INDEX `auction_user`(`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of auction
-- ----------------------------
INSERT INTO `auction` VALUES (1, 1890, 513, 1, 160, 10, 190, '2020-07-20 10:00:00', '36000000', NULL, NULL, NULL);