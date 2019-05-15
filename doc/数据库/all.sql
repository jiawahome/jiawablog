DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` CHAR(32) NOT NULL DEFAULT '' COMMENT 'ID',
  `login_name` VARCHAR(50) NOT NULL COMMENT '登陆名',
  `password` VARCHAR(50) NOT NULL COMMENT '密码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户表';

INSERT INTO  `user` VALUES ('1111', 'test', 'fb469d7ef430b0baf0cab6c436e70375');

DROP TABLE IF EXISTS `category`;
CREATE TABLE `category` (
  `id` CHAR(32) NOT NULL DEFAULT '' COMMENT 'ID',
  `name` VARCHAR(50) NOT NULL COMMENT '分类名称：JAVA；后端技术；前端技术',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='分类表';

DROP TABLE IF EXISTS `article`;
CREATE TABLE `article` (
  `id` CHAR(32) NOT NULL DEFAULT '' COMMENT 'ID',
  `title` VARCHAR(50) NOT NULL COMMENT '标题',
  `category_id` CHAR(32) COMMENT '类别：category.id',
  `summary` VARCHAR(2000) COMMENT '概述',
  `at` DATETIME NOT NULL COMMENT '文章时间',
  `status` CHAR(1) COMMENT '状态：P publish；D draft',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='文章表';

DROP TABLE IF EXISTS `content`;
CREATE TABLE `content` (
  `id` CHAR(32) NOT NULL DEFAULT '' COMMENT '文章ID',
  `content` MEDIUMTEXT NOT NULL COMMENT '文章内容',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='文章内容';