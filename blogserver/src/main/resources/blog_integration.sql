/*
Navicat MySQL Data Transfer

Source Server         : myblog
Source Server Version : 50624
Source Host           : localhost:3306
Source Database       : vueblog2

Target Server Type    : MYSQL
Target Server Version : 50624
File Encoding         : 65001

Date: 2018-12-07 14:22:51
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `article`
-- ----------------------------
DROP TABLE IF EXISTS `article`;
CREATE TABLE `article` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `article_title` varchar(255) DEFAULT NULL COMMENT '文章标题',
  `article_markdown_content` text COMMENT '文章内容 Markdown格式',
  `article_html_content` text COMMENT '文章内容 html格式',
  `article_summary` text COMMENT '文章摘要',
  #`cid` int(11) DEFAULT NULL COMMENT '',
  `user_id` int(11) DEFAULT NULL COMMENT '发表用户 多对一',
  `commented` int(1) DEFAULT '0' COMMENT '文章能否被评论',
  `article_thumbnail` varchar(11) DEFAULT '' COMMENT '文章能否被评论',
  `created` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `status` int(11) DEFAULT NULL COMMENT '0表示草稿箱，1表示已发表，2表示已删除,3表示不公开',
  `pageView` int(11) DEFAULT '0' COMMENT '文章访问量',
  PRIMARY KEY (`id`),
  #KEY `cid` (`cid`),
  #KEY `user_id` (`user_id`),
  #CONSTRAINT `article_ibfk_1` FOREIGN KEY (`cid`) REFERENCES `category` (`id`),
  #CONSTRAINT `article_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=124 DEFAULT CHARSET=utf8;
#多对一是这样的，有两张表，一张表里存着另一张表内数据的id，如果有多个id相同则表示该id有多个内容
# List<Category> categories = new ArrayList<>();List<Tag> tags = new ArrayList<>();
#List<Comment> comments = new ArrayList<>();  private String postThumbnail;缩略图

-- ----------------------------
-- Table structure for `article_tags`
-- ----------------------------
DROP TABLE IF EXISTS `article_tags`;
CREATE TABLE `article_tags` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `article_id` int(11) DEFAULT NULL COMMENT '文章编号',
  `tag_name` varchar(11) DEFAULT NULL COMMENT '标签名称',
  `tid` int(11) DEFAULT NULL,
  `created` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `status` int(1) NOT NULL DEFAULT '1' COMMENT '可用状态字',
  PRIMARY KEY (`id`),
  KEY `tid` (`tid`),
  KEY `article_tags_ibfk_1` (`article_id`),
  CONSTRAINT `article_tags_ibfk_1` FOREIGN KEY (`aid`) REFERENCES `article` (`id`) ON DELETE CASCADE,
  CONSTRAINT `article_tags_ibfk_2` FOREIGN KEY (`tid`) REFERENCES `tags` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8;
# 标签路径String tagUrl;
-- ----------------------------
-- Table structure for `category`
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category` (
  `id` int(11) NOT NULL AUTO_INCREMENT ,
  `category_name` varchar(64) DEFAULT NULL COMMENT '分类名称',
  `category_desc` varchar(64) DEFAULT NULL COMMENT '分类描述',
  `created` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `status` int(1) NOT NULL DEFAULT '1' COMMENT '可用状态字',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=65 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of category
-- ----------------------------


-- ----------------------------
-- Table structure for `comments`
-- ----------------------------
DROP TABLE IF EXISTS `comments`;
CREATE TABLE `comments` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `article_id` int(11) DEFAULT NULL COMMENT '文章编号',
  `comments_content` text COMMENT '评论内容',
  `commenter_id` int(11) DEFAULT NULL COMMENT '评论人编号',
  `comments_username` varchar(10) COMMENT '评论人名称',
  `comments_content` text COMMENT '评论内容',
  `commenter_ip` varchar(20) COMMENT '评论者ip',
  `commenter_via` varchar(20) COMMENT '评论者头像',
  `parentId` int(11) DEFAULT NULL COMMENT '-1表示正常回复，其他值表示是评论的回复',
  `created` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `status` int(1) NOT NULL DEFAULT '1' COMMENT '评论状态，0：正常，1：待审核，2：回收站',  
  PRIMARY KEY (`id`),
  KEY `article_id` (`article_id`),
  KEY `comments_userid` (`comments_userid`),
  KEY `parentId` (`parentId`),
  CONSTRAINT `comments_ibfk_1` FOREIGN KEY (`article_id`) REFERENCES `article` (`id`),
  CONSTRAINT `comments_ibfk_2` FOREIGN KEY (`commenter_id`) REFERENCES `user` (`id`),
  CONSTRAINT `comments_ibfk_3` FOREIGN KEY (`parentId`) REFERENCES `comments` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
#当前评论下的所有子评论
-- ----------------------------
-- Records of comments
-- ----------------------------

-- ----------------------------
-- Table structure for `pv`
-- ----------------------------
/*#这表干嘛的？好像是浏览量
DROP TABLE IF EXISTS `pageview`;
#CREATE TABLE `pageview` (
 # `id` int(11) NOT NULL AUTO_INCREMENT,
  #`countDate` date DEFAULT NULL,
  #`pageview` int(11) DEFAULT NULL,
  #`user_id` int(11) DEFAULT NULL,
  #`created` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
 # `updated` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  #`status` int(1) NOT NULL DEFAULT '1' COMMENT '可用状态字',
  #PRIMARY KEY (`id`),
  #KEY `pageview_ibfk_1` (`user_id`),
  #CONSTRAINT `pageview_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `UserInfo` (`id`) ON DELETE CASCADE
#) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;*/

-- ----------------------------
-- Records of pv
-- ----------------------------

-- ----------------------------
-- Table structure for `roles`
-- ----------------------------
DROP TABLE IF EXISTS `roles`;
CREATE TABLE `roles` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) DEFAULT NULL COMMENT '角色名',
  `created` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `status` int(1) NOT NULL DEFAULT '1' COMMENT '可用状态字',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of roles
-- ----------------------------
#INSERT INTO `roles` VALUES ('1', '超级管理员');
#INSERT INTO `roles` VALUES ('2', '普通用户');
#INSERT INTO `roles` VALUES ('3', '测试角色1');


-- ----------------------------
-- Table structure for `roles_user`
-- ----------------------------
DROP TABLE IF EXISTS `roles_user`;
CREATE TABLE `roles_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) DEFAULT '2' COMMENT '角色名',
  `user_id` int(11) DEFAULT NULL,
  `created` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `status` int(1) NOT NULL DEFAULT '1' COMMENT '可用状态字',
  PRIMARY KEY (`id`),
  KEY `role_id` (`role_id`),
  KEY `roles_user_ibfk_2` (`user_id`),
  CONSTRAINT `roles_user_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`),
  CONSTRAINT `roles_user_ibfk_2` FOREIGN KEY (`uid`) REFERENCES `user` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=131 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of roles_user
-- ----------------------------
#INSERT INTO `roles_user` VALUES ('8', '2', '7');
#INSERT INTO `roles_user` VALUES ('9', '1', '7');
#INSERT INTO `roles_user` VALUES ('17', '5', '7');
#INSERT INTO `roles_user` VALUES ('106', '2', '14');
#INSERT INTO `roles_user` VALUES ('108', '2', '16');
#INSERT INTO `roles_user` VALUES ('109', '2', '17');
#INSERT INTO `roles_user` VALUES ('110', '2', '18');
#INSERT INTO `roles_user` VALUES ('111', '2', '19');
#INSERT INTO `roles_user` VALUES ('112', '2', '20');
#INSERT INTO `roles_user` VALUES ('119', '2', '15');
#INSERT INTO `roles_user` VALUES ('120', '5', '15');
#INSERT INTO `roles_user` VALUES ('121', '2', '6');
#INSERT INTO `roles_user` VALUES ('123', '2', '13');
#INSERT INTO `roles_user` VALUES ('124', '3', '13');
#INSERT INTO `roles_user` VALUES ('128', '2', '10');
#INSERT INTO `roles_user` VALUES ('129', '5', '10');
#INSERT INTO `roles_user` VALUES ('130', '1', '6');

-- ----------------------------
-- Table structure for `tags`
-- ----------------------------
DROP TABLE IF EXISTS `tags`;
CREATE TABLE `tags` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tagName` varchar(32) DEFAULT NULL COMMENT '创建时间',
  `created` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `status` int(1) NOT NULL DEFAULT '1' COMMENT '可用状态字',
  PRIMARY KEY (`id`),
  UNIQUE KEY `tagName` (`tagName`)
) ENGINE=InnoDB AUTO_INCREMENT=67 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tags
-- ----------------------------
#INSERT INTO `tags` VALUES ('66', '666');
#INSERT INTO `tags` VALUES ('35', 'Ajax');
#INSERT INTO `tags` VALUES ('36', 'Dubbo');
#INSERT INTO `tags` VALUES ('40', 'git');
#INSERT INTO `tags` VALUES ('33', 'Linux');
#INSERT INTO `tags` VALUES ('45', 'mongodb');
#INSERT INTO `tags` VALUES ('42', 'spring');
#INSERT INTO `tags` VALUES ('44', 'SpringSecurity');
#INSERT INTO `tags` VALUES ('37', 'websocket');
#INSERT INTO `tags` VALUES ('34', 'Zookeeper');
#INSERT INTO `tags` VALUES ('50', '图片上传');
#INSERT INTO `tags` VALUES ('51', '图片预览');
#INSERT INTO `tags` VALUES ('41', '学习资料');
#INSERT INTO `tags` VALUES ('65', '杂谈');

-- ----------------------------
-- Table structure for `UserInfo`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(64) DEFAULT NULL,
  `nickname` varchar(64) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `enabled` tinyint(1) DEFAULT '1',
  `email` varchar(64) DEFAULT NULL,
  `userface` varchar(255) DEFAULT NULL,
      `created` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `status` int(1) NOT NULL DEFAULT '1' COMMENT '可用状态字',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of UserInfo
-- ----------------------------
#INSERT INTO `user` VALUES ('6', 'linghu', '令狐葱', '202cb962ac59075b964b07152d234b70', '1', 'linghu@qq.com', 'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1514093920326&di=44a6fa6b597d86f475c2b15fa93008dd&imgtype=0&src=http%3A%2F%2Fwww.qqzhi.com%2Fuploadpic%2F2015-01-12%2F023019564.jpg', '2017-12-08 09:30:22');
#INSERT INTO `user` VALUES ('7', 'sang', '江南一点雨', '202cb962ac59075b964b07152d234b70', '1', 'sang123@qq.com', 'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1514093920321&di=913e88c23f382933ef430024afd9128a&imgtype=0&src=http%3A%2F%2Fp.3761.com%2Fpic%2F9771429316733.jpg', '2017-12-21 13:30:29');
#INSERT INTO `user` VALUES ('10', 'qiaofeng', '乔峰', '202cb962ac59075b964b07152d234b70', '1', 'qiaofeng@qq.com', 'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1514093920321&di=913e88c23f382933ef430024afd9128a&imgtype=0&src=http%3A%2F%2Fp.3761.com%2Fpic%2F9771429316733.jpg', '2017-12-24 06:30:46');
#INSERT INTO `user` VALUES ('13', 'duanzhengchun', '段正淳', '202cb962ac59075b964b07152d234b70', '0', 'duanzhengchun@qq.com', 'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1514093920321&di=913e88c23f382933ef430024afd9128a&imgtype=0&src=http%3A%2F%2Fp.3761.com%2Fpic%2F9771429316733.jpg', '2017-12-24 06:30:46');
#INSERT INTO `user` VALUES ('14', 'chenjialuo', '陈家洛', '202cb962ac59075b964b07152d234b70', '0', 'chenjialuo@qq.com', 'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1514093920321&di=913e88c23f382933ef430024afd9128a&imgtype=0&src=http%3A%2F%2Fp.3761.com%2Fpic%2F9771429316733.jpg', '2017-12-24 06:30:46');
#INSERT INTO `user` VALUES ('15', 'yuanchengzhi', '袁承志', '202cb962ac59075b964b07152d234b70', '1', 'yuanchengzhi@qq.com', 'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1514093920321&di=913e88c23f382933ef430024afd9128a&imgtype=0&src=http%3A%2F%2Fp.3761.com%2Fpic%2F9771429316733.jpg', '2017-12-24 06:30:46');
#INSERT INTO `user` VALUES ('16', 'chuliuxiang', '楚留香', '202cb962ac59075b964b07152d234b70', '1', 'chuliuxiang@qq.com', 'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1514093920321&di=913e88c23f382933ef430024afd9128a&imgtype=0&src=http%3A%2F%2Fp.3761.com%2Fpic%2F9771429316733.jpg', '2017-12-24 06:30:46');
#INSERT INTO `user` VALUES ('17', 'baizhantang', '白展堂', '202cb962ac59075b964b07152d234b70', '0', 'baizhantang@qq.com', 'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1514093920321&di=913e88c23f382933ef430024afd9128a&imgtype=0&src=http%3A%2F%2Fp.3761.com%2Fpic%2F9771429316733.jpg', '2017-12-24 06:30:46');
#INSERT INTO `user` VALUES ('18', 'renwoxing', '任我行', '202cb962ac59075b964b07152d234b70', '1', 'renwoxing@qq.com', 'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1514093920321&di=913e88c23f382933ef430024afd9128a&imgtype=0&src=http%3A%2F%2Fp.3761.com%2Fpic%2F9771429316733.jpg', '2017-12-24 06:30:46');
#INSERT INTO `user` VALUES ('19', 'zuolengchan', '左冷禅', '202cb962ac59075b964b07152d234b70', '1', 'zuolengchan@qq.com', 'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1514093920321&di=913e88c23f382933ef430024afd9128a&imgtype=0&src=http%3A%2F%2Fp.3761.com%2Fpic%2F9771429316733.jpg', '2017-12-24 06:30:46');
#INSERT INTO `user` VALUES ('20', 'fengqingyang', '风清扬', '202cb962ac59075b964b07152d234b70', '1', 'fengqingyang@qq.com', 'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1514093920321&di=913e88c23f382933ef430024afd9128a&imgtype=0&src=http%3A%2F%2Fp.3761.com%2Fpic%2F9771429316733.jpg', '2017-12-24 06:30:46');

CREATE TABLE `log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `log_title` varchar(64) DEFAULT NULL COMMENT '日志标题',
  `log_content` varchar(64) DEFAULT NULL COMMENT '日志内容',
  `created` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `status` int(1) NOT NULL DEFAULT '1' COMMENT '可用状态字',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

CREATE TABLE `attach` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `attach_name` varchar(64) DEFAULT NULL COMMENT '附件名称',
  `attach_path` varchar(64) DEFAULT NULL COMMENT '附件路径',
  `attach_suffix` varchar(64) DEFAULT NULL COMMENT '附件后缀',
  `attach_type` varchar(64) DEFAULT NULL COMMENT '附件类型',
   `attach_type` varchar(64) DEFAULT NULL COMMENT '附件缩略图',
    `attach_length` varchar(64) DEFAULT NULL COMMENT '附件大小',
  `created` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `status` int(1) NOT NULL DEFAULT '1' COMMENT '可用状态字',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

-- ----------------------------
-- View structure for `pvview`
-- ----------------------------
#DROP VIEW IF EXISTS `pvview`;
#CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `pvview` AS select sum(`pv`.`pv`) AS `pv`,`pv`.`uid` AS `uid` from `pv` group by `pv`.`uid` ;

-- ----------------------------
-- View structure for `totalpvview`
-- ----------------------------
#DROP VIEW IF EXISTS `totalpvview`;
#CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `totalpvview` AS select sum(`a`.`pageView`) AS `totalPv`,`a`.`uid` AS `uid` from `article` `a` group by `a`.`uid` ;
