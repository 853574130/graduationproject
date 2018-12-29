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
  PRIMARY KEY (`id`)
  #KEY `cid` (`cid`),
  #KEY `user_id` (`user_id`),
  #CONSTRAINT `article_ibfk_1` FOREIGN KEY (`cid`) REFERENCES `category` (`id`),
  #CONSTRAINT `article_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=124 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `article_tags`;
CREATE TABLE `article_tags` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `article_id` int(11) DEFAULT NULL COMMENT '文章编号',
  `tag_name` varchar(11) DEFAULT NULL COMMENT '标签名称',
  #`tid` int(11) DEFAULT NULL,
  `created` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `status` int(1) NOT NULL DEFAULT '1' COMMENT '可用状态字',
  PRIMARY KEY (`id`),
 # KEY `tid` (`tid`),
  KEY `article_tags_ibfk_1` (`article_id`)
  #CONSTRAINT `article_tags_ibfk_1` FOREIGN KEY (`aid`) REFERENCES `article` (`id`) ON DELETE CASCADE,
  #CONSTRAINT `article_tags_ibfk_2` FOREIGN KEY (`tid`) REFERENCES `tags` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8;
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
DROP TABLE IF EXISTS `comments`;
CREATE TABLE `comments` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `article_id` int(11) DEFAULT NULL COMMENT '文章编号',
  `comments_content` text COMMENT '评论内容',
  `commenter_id` int(11) DEFAULT NULL COMMENT '评论人编号',
  `comments_username` varchar(10) COMMENT '评论人名称',
  `commenter_ip` varchar(20) COMMENT '评论者ip',
  `commenter_via` varchar(20) COMMENT '评论者头像',
  `parentId` int(11) DEFAULT NULL COMMENT '-1表示正常回复，其他值表示是评论的回复',
  `created` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `status` int(1) NOT NULL DEFAULT '1' COMMENT '评论状态，0：正常，1：待审核，2：回收站',  
  PRIMARY KEY (`id`)
  /*KEY `article_id` (`article_id`),
  KEY `commenter_id` (`commenter_id`),
  KEY `parentId` (`parentId`)*/
  #CONSTRAINT `comments_ibfk_1` FOREIGN KEY (`article_id`) REFERENCES `article` (`id`),
  #CONSTRAINT `comments_ibfk_2` FOREIGN KEY (`commenter_id`) REFERENCES `user` (`id`),
  #CONSTRAINT `comments_ibfk_3` FOREIGN KEY (`parentId`) REFERENCES `comments` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
DROP TABLE IF EXISTS `roles`;
CREATE TABLE `roles` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(32) DEFAULT NULL COMMENT '角色名',
  `created` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `status` int(1) NOT NULL DEFAULT '1' COMMENT '可用状态字',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
DROP TABLE IF EXISTS `roles_user`;
CREATE TABLE `roles_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) DEFAULT '2' COMMENT '角色id',
  `user_id` int(11) DEFAULT NULL,
  `created` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `status` int(1) NOT NULL DEFAULT '1' COMMENT '可用状态字',
  PRIMARY KEY (`id`),
  KEY `role_id` (`role_id`),
  KEY `roles_user_ibfk_2` (`user_id`)
  #CONSTRAINT `roles_user_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`),
  #CONSTRAINT `roles_user_ibfk_2` FOREIGN KEY (`uid`) REFERENCES `user` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=131 DEFAULT CHARSET=utf8;
/*DROP TABLE IF EXISTS `tags`;
CREATE TABLE `tags` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tagName` varchar(32) DEFAULT NULL COMMENT '创建时间',
  `created` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `status` int(1) NOT NULL DEFAULT '1' COMMENT '可用状态字',
  PRIMARY KEY (`id`),
  UNIQUE KEY `tagName` (`tagName`)
) ENGINE=InnoDB AUTO_INCREMENT=67 DEFAULT CHARSET=utf8;*/
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(64) DEFAULT NULL COMMENT '用户名',
  `user_account` varchar(64) DEFAULT NULL COMMENT '用户账号',
  #`nickname` varchar(64) DEFAULT NULL COMMENT '显示昵称',
  `password` varchar(255) DEFAULT NULL COMMENT '密码',
  `type` varchar(10) DEFAULT 0 COMMENT '类型，0普通账号，10管理员',
  #`enabled` tinyint(1) DEFAULT '1' COMMENT '日志标题',
  `email` varchar(64) DEFAULT NULL COMMENT '邮箱',
  `userface` varchar(255) DEFAULT NULL,
  `created` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `status` int(1) NOT NULL DEFAULT '1' COMMENT '状态，1：正常，0：禁用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;
DROP TABLE IF EXISTS `log`;
CREATE TABLE `log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `log_title` varchar(64) DEFAULT NULL COMMENT '日志标题',
  `log_content` varchar(64) DEFAULT NULL COMMENT '日志内容',
  `created` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `status` int(1) NOT NULL DEFAULT '1' COMMENT '可用状态字',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `attach`;
CREATE TABLE `attach` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `attach_name` varchar(64) DEFAULT NULL COMMENT '附件名称',
  `attach_path` varchar(64) DEFAULT NULL COMMENT '附件路径',
  `attach_suffix` varchar(64) DEFAULT NULL COMMENT '附件后缀',
  `attach_type` varchar(64) DEFAULT NULL COMMENT '附件类型',
  `attach_small_path` varchar(64) DEFAULT NULL COMMENT '附件缩略图路径',
  `attach_length` varchar(64) DEFAULT NULL COMMENT '附件大小',
  `created` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `status` int(1) NOT NULL DEFAULT '1' COMMENT '可用状态字',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;
