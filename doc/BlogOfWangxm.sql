/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2019/2/12 16:16:04                           */
/*==============================================================*/

DROP DATABASE db_blog;
CREATE DATABASE IF NOT EXISTS db_blog DEFAULT CHARSET utf8mb4;

USE db_blog;
drop table if exists blog_channel;

drop table if exists blog_comment;

drop table if exists blog_favorite;

drop table if exists blog_options;

drop table if exists blog_post;

drop table if exists blog_post_attribute;

drop table if exists blog_user;

drop table if exists shiro_permission;

drop table if exists shiro_role;

drop table if exists shiro_role_permission;

drop table if exists shiro_user_role;

/*==============================================================*/
/* Table: blog_channel                                          */
/*==============================================================*/
create table blog_channel
(
   id                   int(11) not null,
   key_                 varchar(255),
   name                 varchar(255),
   status               int(11),
   primary key (id)
);

/*==============================================================*/
/* Table: blog_comment                                          */
/*==============================================================*/
create table blog_comment
(
   id                   bigint(20) not null,
   author_id            bigint(20),
   content              varchar(255),
   created              datetime,
   pid                  bigint(20),
   status               int(11),
   to_id                bigint(20),
   primary key (id)
);

/*==============================================================*/
/* Table: blog_favorite                                         */
/*==============================================================*/
create table blog_favorite
(
   id                   bigint(20) not null,
   created              datetime,
   own_id               bigint(20),
   post_id              bigint(20),
   primary key (id)
);

/*==============================================================*/
/* Table: blog_options                                          */
/*==============================================================*/
create table blog_options
(
   id                   bigint(20) not null,
   key_                 varchar(255),
   type                 varchar(255),
   value                varchar(255),
   primary key (id)
);

/*==============================================================*/
/* Table: blog_post                                             */
/*==============================================================*/
create table blog_post
(
   id                   bigint(20) not null,
   author_id            bigint(20),
   channel_id           int(11),
   comments             int(11),
   created              datetime,
   favors               int(11),
   featured             int(11),
   status               int(11),
   summary              varchar(255),
   tags                 varchar(255),
   thumbnail            varchar(255),
   title                varchar(64),
   views                int(11),
   weight               int(11),
   is_markdown          int(1),
   primary key (id)
);


/*==============================================================*/
/* Table: blog_post_attribute                                   */
/*==============================================================*/
create table blog_post_attribute
(
   id                   bigint(20) not null,
   content              longtext,
   primary key (id)
);

/*==============================================================*/
/* Table: blog_user                                             */
/*==============================================================*/
create table blog_user
(
   id                   bigint(20) not null,
   username             varchar(32),
   name                 varchar(32),
   avatar               varchar(128),
   email                varchar(64),
   password             varchar(64),
   status               int(5),
   created              datetime,
   updated              datetime,
   last_login           datetime,
   gender               int(5),
   role_id              int(11),
   comments             int(11),
   posts                int(11),
   signature            varchar(140),
   primary key (id)
);

/*==============================================================*/
/* Table: shiro_permission                                      */
/*==============================================================*/
create table shiro_permission
(
   id                   bigint(20) not null,
   description          varchar(255),
   name                 varchar(255),
   parent_id            bigint(11),
   version              int(11),
   weight               int(11),
   primary key (id)
);

/*==============================================================*/
/* Table: shiro_role                                            */
/*==============================================================*/
create table shiro_role
(
   id                   bigint(20) not null,
   description          varchar(255),
   name                 varchar(255),
   status               int(11),
   primary key (id)
);

/*==============================================================*/
/* Table: shiro_role_permission                                 */
/*==============================================================*/
create table shiro_role_permission
(
   id                   bigint(20) not null,
   permission_id        bigint(20),
   role_id              bigint(20),
   primary key (id)
);

/*==============================================================*/
/* Table: shiro_user_role                                       */
/*==============================================================*/
create table shiro_user_role
(
   id                   bigint(20) not null,
   role_id              bigint(20),
   user_id              bigint(20),
   primary key (id)
);

INSERT INTO `blog_channel` VALUES (1, 'banner', 'banner', 0);
INSERT INTO `blog_channel` VALUES (2, 'blog', '博客', 0);
INSERT INTO `blog_channel` VALUES (3, 'questions', '问答', 0);
INSERT INTO `blog_channel` VALUES (4, 'share', '分享', 0);

INSERT INTO `blog_options` VALUES ('1', 'site_name', '0', 'BlogOfWangxm');
INSERT INTO `blog_options` VALUES ('3', 'site_domain', '0', 'http://eussi.com');
INSERT INTO `blog_options` VALUES ('4', 'site_keywords', '0', 'BlogOfWangxm,博客');
INSERT INTO `blog_options` VALUES ('5', 'site_description', '0', 'Blog of wangxueming');
INSERT INTO `blog_options` VALUES ('6', 'site_metas', '0', '');
INSERT INTO `blog_options` VALUES ('7', 'site_copyright', '0', 'Copyright © 2018 ICP：豫ICP备18034613号');
INSERT INTO `blog_options` VALUES ('8', 'site_icp', '0', '');
INSERT INTO `blog_options` VALUES ('11', 'qq_callback', '0', '');
INSERT INTO `blog_options` VALUES ('12', 'qq_app_id', '0', '');
INSERT INTO `blog_options` VALUES ('13', 'qq_app_key', '0', '');
INSERT INTO `blog_options` VALUES ('14', 'weibo_callback', '0', '');
INSERT INTO `blog_options` VALUES ('15', 'weibo_client_id', '0', '');
INSERT INTO `blog_options` VALUES ('16', 'weibo_client_sercret', '0', '');
INSERT INTO `blog_options` VALUES ('23', 'github_callback', '0', '');
INSERT INTO `blog_options` VALUES ('24', 'github_client_id', '0', '');
INSERT INTO `blog_options` VALUES ('25', 'github_secret_key', '0', '');

INSERT INTO `shiro_role` VALUES ('1', null, 'admin', '0');

INSERT INTO `shiro_permission` VALUES ('1', '进入后台', 'admin', '0', '0', '0');
INSERT INTO `shiro_permission` VALUES ('2', '栏目管理', 'channel:list', '0', '0', '0');
INSERT INTO `shiro_permission` VALUES ('3', '编辑栏目', 'channel:update', '2', '0', '0');
INSERT INTO `shiro_permission` VALUES ('4', '删除栏目', 'channel:delete', '2', '0', '0');
INSERT INTO `shiro_permission` VALUES ('5', '文章管理', 'post:list', '0', '0', '0');
INSERT INTO `shiro_permission` VALUES ('6', '编辑文章', 'post:update', '5', '0', '0');
INSERT INTO `shiro_permission` VALUES ('7', '删除文章', 'post:delete', '5', '0', '0');
INSERT INTO `shiro_permission` VALUES ('8', '评论管理', 'comment:list', '0', '0', '0');
INSERT INTO `shiro_permission` VALUES ('9', '删除评论', 'comment:delete', '8', '0', '0');
INSERT INTO `shiro_permission` VALUES ('10', '用户管理', 'user:list', '0', '0', '0');
INSERT INTO `shiro_permission` VALUES ('11', '用户授权', 'user:role', '10', '0', '0');
INSERT INTO `shiro_permission` VALUES ('12', '修改密码', 'user:pwd', '10', '0', '0');
INSERT INTO `shiro_permission` VALUES ('13', '激活用户', 'user:open', '10', '0', '0');
INSERT INTO `shiro_permission` VALUES ('14', '关闭用户', 'user:close', '10', '0', '0');
INSERT INTO `shiro_permission` VALUES ('15', '角色管理', 'role:list', '0', '0', '0');
INSERT INTO `shiro_permission` VALUES ('16', '修改角色', 'role:update', '15', '0', '0');
INSERT INTO `shiro_permission` VALUES ('17', '删除角色', 'role:delete', '15', '0', '0');
INSERT INTO `shiro_permission` VALUES ('18', '系统配置', 'options:index', '0', '0', '0');
INSERT INTO `shiro_permission` VALUES ('19', '修改配置', 'options:update', '18', '0', '0');

INSERT INTO `shiro_role_permission` VALUES ('1', '10', '1');
INSERT INTO `shiro_role_permission` VALUES ('2', '4', '1');
INSERT INTO `shiro_role_permission` VALUES ('3', '16', '1');
INSERT INTO `shiro_role_permission` VALUES ('4', '1', '1');
INSERT INTO `shiro_role_permission` VALUES ('5', '14', '1');
INSERT INTO `shiro_role_permission` VALUES ('6', '17', '1');
INSERT INTO `shiro_role_permission` VALUES ('7', '3', '1');
INSERT INTO `shiro_role_permission` VALUES ('8', '12', '1');
INSERT INTO `shiro_role_permission` VALUES ('9', '6', '1');
INSERT INTO `shiro_role_permission` VALUES ('10', '2', '1');
INSERT INTO `shiro_role_permission` VALUES ('11', '5', '1');
INSERT INTO `shiro_role_permission` VALUES ('12', '18', '1');
INSERT INTO `shiro_role_permission` VALUES ('13', '15', '1');
INSERT INTO `shiro_role_permission` VALUES ('14', '19', '1');
INSERT INTO `shiro_role_permission` VALUES ('15', '13', '1');
INSERT INTO `shiro_role_permission` VALUES ('16', '7', '1');
INSERT INTO `shiro_role_permission` VALUES ('17', '9', '1');
INSERT INTO `shiro_role_permission` VALUES ('18', '8', '1');
INSERT INTO `shiro_role_permission` VALUES ('19', '11', '1');

INSERT INTO `blog_user` VALUES ('1', 'admin', 'wangxm', '/dist/images/ava/default.jpg', 'example@eussi.com', '3TGCQF25BLHU9R7IQUITN0FCC5', '0', '2019-02-14 17:52:41', '2019-02-14 11:08:36', '2019-02-14 13:24:13', '0', '1', '0', '0', '');

