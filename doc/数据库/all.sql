DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` CHAR(8) NOT NULL DEFAULT '' COMMENT 'ID',
  `login_name` VARCHAR(50) NOT NULL COMMENT '登陆名',
  `password` VARCHAR(50) NOT NULL COMMENT '密码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户表';

INSERT INTO  `user` VALUES ('1111', 'test', 'test');