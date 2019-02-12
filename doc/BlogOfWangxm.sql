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
