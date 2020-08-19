DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods`  (
  `goods_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `start_time` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `end_time` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `address` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `website` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `goods_type` tinyint(4) NOT NULL,
  `image` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `view_counter` int(255) NOT NULL DEFAULT 0,
  `buy_counter` int(255) NOT NULL DEFAULT 0,
  PRIMARY KEY (`goods_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

INSERT INTO `goods` VALUES (1, '【杭州】「初夏人生」嘻哈派对', '2020-08-07', '2020-08-07', '场馆：杭州市 | 大麦66LiveHouse杭州', 'https://detail.damai.cn/item.htm?id=621634938771&clicktitle=%E3%80%8C%E5%88%9D%E5%A4%8F%E4%BA%BA%E7%94%9F%E3%80%8D%E5%98%BB%E5%93%88%E6%B4%BE%E5%AF%B9', 0, '//img.alicdn.com/bao/uploaded/i3/2251059038/O1CN01RNXESo2GdSFYachKn_!!2-item_pic.png_q60.jpg_.webp', 127, 0);
INSERT INTO `goods` VALUES (2, '【上海】皇后乐队致敬音乐会 2020 全新乐章', '2020-07-09', '2020-07-11', '场馆：上海市 | 珍珠剧场The Pearl', 'https://detail.damai.cn/item.htm?id=621046144219&clicktitle=%E7%9A%87%E5%90%8E%E4%B9%90%E9%98%9F%E8%87%B4%E6%95%AC%E9%9F%B3%E4%B9%90%E4%BC%9A%202020%20%E5%85%A8%E6%96%B0%E4%B9%90%E7%AB%A0', 0, '//img.alicdn.com/bao/uploaded/i1/2251059038/O1CN019kybVQ2GdSFbIQCPL_!!0-item_pic.jpg_q60.jpg_.webp', 26, 4);
INSERT INTO `goods` VALUES (3, '【上海】爆笑脱口秀演出-喜剧联盒国', '2020-07-10', '2020-07-18', '场馆：上海市 | 生活实验室小剧场', 'https://detail.damai.cn/item.htm?id=619943654186&clicktitle=%E7%88%86%E7%AC%91%E8%84%B1%E5%8F%A3%E7%A7%80%E6%BC%94%E5%87%BA-%E5%96%9C%E5%89%A7%E8%81%94%E7%9B%92%E5%9B%BD', 0, '//img.alicdn.com/bao/uploaded/https://img.alicdn.com/imgextra/i2/2251059038/O1CN019OYN192GdSFNbmN3f_!!2251059038.png_q60.jpg_.webp', 6, 3);
INSERT INTO `goods` VALUES (4, '【上海】7.10 | 海底捞乐 + 热血高校= All Night Party', '2020-07-10', '2020-07-10', '场馆：上海市 | 上海 LOFAS', 'https://detail.damai.cn/item.htm?id=622245178425&clicktitle=7.10%20%7C%20%E6%B5%B7%E5%BA%95%E6%8D%9E%E4%B9%90%20%2B%20%E7%83%AD%E8%A1%80%E9%AB%98%E6%A0%A1%3D%20All%20Night%20Party', 0, '//img.alicdn.com/bao/uploaded/https://img.alicdn.com/imgextra/i3/2251059038/O1CN01YyGHSD2GdSFh6kZml_!!2251059038.png_q60.jpg_.webp', 1, 0);