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

INSERT INTO `blog_post` (`id`, `author_id`, `channel_id`, `comments`, `created`, `favors`, `featured`, `status`, `summary`, `tags`, `thumbnail`, `title`, `views`, `weight`) VALUES ('1', '1', '1', '0', '2019-02-14 23:18:10', '0', '0', '0', '&nbsp; &nbsp; &nbsp; &nbsp; 我喜欢出发。 &nbsp; &nbsp; &nbsp; &nbsp; 凡是到达了的地方，都属于昨天。哪怕那山再青，那水再秀，那风再温柔。太深的流连便成了一种羁绊，绊住的不仅有双脚，还有未来。...', '汪国真,我喜欢出发,散文', '/storage/thumbnails/2019/0214/14232223hnhp.jpg', '我喜欢出发', '6', '0');
INSERT INTO `blog_post` (`id`, `author_id`, `channel_id`, `comments`, `created`, `favors`, `featured`, `status`, `summary`, `tags`, `thumbnail`, `title`, `views`, `weight`) VALUES ('2', '1', '1', '0', '2019-02-14 23:25:41', '0', '0', '0', '　　我与父亲不相见已二年余了，我最不能忘记的是他的背影。那年冬天，祖母死了，父亲的差使也交卸了，正是祸不单行的日子，我从北京到徐州，打算跟着父亲奔丧回家。到徐州见着父亲，看见满院狼藉的东西，又想起祖母，不禁簌簌地流下眼泪。父亲说，“事已如此，不必...', '朱自清,背影,散文', '/storage/thumbnails/2019/0214/14232528txqe.jpg', '背影', '1', '0');

INSERT INTO `blog_post_attribute` (`id`, `content`) VALUES ('1', '<!DOCTYPE html>\r\n<html>\r\n<head>\r\n</head>\r\n<body>\r\n<p><span class=\"bjh-p\">        我喜欢出发。</span></p>\r\n<p><span class=\"bjh-p\">        凡是到达了的地方，都属于昨天。哪怕那山再青，那水再秀，那风再温柔。太深的流连便成了一种羁绊，绊住的不仅有双脚，还有未来。</span></p>\r\n<p><span class=\"bjh-p\">        怎么能不喜欢出发呢？没见过大山的巍峨，真是遗憾；见了大山的巍峨没见过大海的浩瀚，仍然遗憾；见了大海的浩瀚没见过大漠的广袤，依旧遗憾；见了大漠的广袤没见过森林的神秘，还是遗憾。世界上有不绝的风景，我有不老的心情。</span></p>\r\n<p><span class=\"bjh-p\">        我自然知道，大山有坎坷，大海有浪涛，大漠有风沙，森林有猛兽。即便这样，我依然喜欢。</span></p>\r\n<p><span class=\"bjh-p\">        打破生活的平静便是另一番景致，一种属于年轻的景致。真庆幸，我还没有老。即便真老了又怎么样，不是有句话叫老当益壮吗？</span></p>\r\n<p><span class=\"bjh-p\">        于是，我还想从大山那里学习深刻，我还想从大海那里学习勇敢，我还想从大漠那里学习沉着，我还想从森林那里学习机敏。我想学着品味一种缤纷的人生。</span></p>\r\n<p><span class=\"bjh-p\">        人能走多远？这话不是要问两脚而是要问志向；人能攀多高？这事不是要问双手而是要问意志。于是，我想用青春的热血给自己树起一个高远的目标。不仅是为了争取一种光荣，更是为了追求一种境界。目标实现了，便是光荣；目标实现不了，人生也会因这一路风雨跋涉变得丰富而充实；在我看来，这就是不虚此生。</span></p>\r\n<p><span class=\"bjh-p\">        是的，我喜欢出发，愿你也喜欢。</span></p>\r\n</body>\r\n</html><br/><p class=\"copyright\">注意：本文归作者所有，未经作者允许，不得转载</p>');
INSERT INTO `blog_post_attribute` (`id`, `content`) VALUES ('2', '<!DOCTYPE html>\r\n<html>\r\n<head>\r\n</head>\r\n<body>\r\n<p>　　我与父亲不相见已二年余了，我最不能忘记的是他的背影。那年冬天，祖母死了，父亲的差使也交卸了，正是祸不单行的日子，我从北京到徐州，打算跟着父亲奔丧回家。到徐州见着父亲，看见满院狼藉的东西，又想起祖母，不禁簌簌地流下眼泪。父亲说，“事已如此，不必难过，好在天无绝人之路！”<br />　　回家变卖典质，父亲还了亏空；又借钱办了丧事。这些日子，家中光景很是惨淡，一半为了丧事，一半为了父亲赋闲。丧事完毕，父亲要到南京谋事，我也要回北京念书，我们便同行。<br />　　到南京时，有朋友约去游逛，勾留了一日；第二日上午便须渡江到浦口，下午上车北去。父亲因为事忙，本已说定不送我，叫旅馆里一个熟识的茶房陪我同去。他再三嘱咐茶房，甚是仔细。但他终于不放心，怕茶房不妥帖；颇踌躇了一会。其实我那年已二十岁，北京已来往过两三次，是没有甚么要紧的了。他踌躇了一会，终于决定还是自己送我去。我两三回劝他不必去；他只说，“不要紧，他们去不好！”<br />　　我们过了江，进了车站。我买票，他忙着照看行李。行李太多了，得向脚夫行些小费，才可过去。他便又忙着和他们讲价钱。我那时真是聪明过分，总觉他说话不大漂亮，非自己插嘴不可。但他终于讲定了价钱；就送我上车。他给我拣定了靠车门的一张椅子；我将他给我做的紫毛大衣铺好坐位。他嘱我路上小心，夜里警醒些，不要受凉。又嘱托茶房好好照应我。我心里暗笑他的迂；他们只认得钱，托他们直是白托！而且我这样大年纪的人，难道还不能料理自己么？唉，我现在想想，那时真是太聪明了！<br />　　我说道，“爸爸，你走吧。”他望车外看了看，说，“我买几个橘子去。你就在此地，不要走动。”我看那边月台的栅栏外有几个卖东西的等着顾客。走到那边月台，须穿过铁道，须跳下去又爬上去。父亲是一个胖子，走过去自然要费事些。我本来要去的，他不肯，只好让他去。我看见他戴着黑布小帽，穿着黑布大马褂，深青布棉袍，蹒跚地走到铁道边，慢慢探身下去，尚不大难。可是他穿过铁道，要爬上那边月台，就不容易了。他用两手攀着上面，两脚再向上缩；他肥胖的身子向左微倾，显出努力的样子。这时我看见他的背影，我的泪很快地流下来了。我赶紧拭干了泪，怕他看见，也怕别人看见。我再向外看时，他已抱了朱红的橘子望回走了。过铁道时，他先将橘子散放在地上，自己慢慢爬下，再抱起橘子走。到这边时，我赶紧去搀他。他和我走到车上，将橘子一股脑儿放在我的皮大衣上。于是扑扑衣上的泥土，心里很轻松似的，过一会说，“我走了；到那边来信！”我望着他走出去。他走了几步，回过头看见我，说，“进去吧，里边没人。”等他的背影混入来来往往的人里，再找不着了，我便进来坐下，我的眼泪又来了。<br />　　近几年来，父亲和我都是东奔西走，家中光景是一日不如一日。他少年出外谋生，独力支持，做了许多大事。那知老境却如此颓唐！他触目伤怀，自然情不能自已。情郁于中，自然要发之于外；家庭琐屑便往往触他之怒。他待我渐渐不同往日。但最近两年的不见，他终于忘却我的不好，只是惦记着我，惦记着我的儿子。我北来后，他写了一信给我，信中说道，“我身体平安，惟膀子疼痛利害，举箸提笔，诸多不便，大约大去之期不远矣。”我读到此处，在晶莹的泪光中，又看见那肥胖的，青布棉袍，黑布马褂的背影。唉！我不知何时再能与他相见！<br />　<br />　　1925年10月在北京。</p>\r\n</body>\r\n</html>');

