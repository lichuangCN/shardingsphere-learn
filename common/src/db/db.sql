CREATE DATABASE IF NOT EXISTS `sdb` default charset utf8 COLLATE utf8_general_ci;

CREATE TABLE `user`  (
     `id` bigint(64) NOT NULL,
     `username` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
     `create_date` datetime NULL DEFAULT NULL COMMENT '创建时间',
     PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '人员表' ROW_FORMAT = Compact;

CREATE TABLE `city`  (
     `id` bigint(64) AUTO_INCREMENT NOT NULL,
     `city_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
     PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1  CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '城市表' ROW_FORMAT = Compact;