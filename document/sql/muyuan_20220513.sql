-- MySQL dump 10.13  Distrib 5.7.34, for el7 (x86_64)
--
-- Host: 127.0.0.1    Database: 
-- ------------------------------------------------------
-- Server version	5.7.34-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Current Database: `auth`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `auth` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci */;

USE `auth`;

--
-- Table structure for table `oauth_access_token`
--

DROP TABLE IF EXISTS `oauth_access_token`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `oauth_access_token` (
  `token_id` varchar(256) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT 'MD5加密后存储的access_token',
  `token` blob COMMENT 'access_token序列化的二进制数据格式',
  `authentication_id` varchar(256) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '主键，其值是根据当前的username(如果有),client_id与scope通过MD5加密生成的,具体实现参见DefaultAuthenticationKeyGenerator',
  `user_name` varchar(256) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `client_id` varchar(256) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `authentication` blob COMMENT '将OAuth2Authentication对象序列化后的二进制数据',
  `refresh_token` varchar(256) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT 'refresh_token的MD5加密后的数据',
  PRIMARY KEY (`authentication_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `oauth_access_token`
--

LOCK TABLES `oauth_access_token` WRITE;
/*!40000 ALTER TABLE `oauth_access_token` DISABLE KEYS */;
/*!40000 ALTER TABLE `oauth_access_token` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `oauth_client_details`
--

DROP TABLE IF EXISTS `oauth_client_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `oauth_client_details` (
  `client_id` varchar(256) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '必填，Oauth2 client_id',
  `resource_ids` varchar(256) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '可选，资源id集合，多个资源用英文逗号隔开',
  `client_secret` varchar(256) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '必填，Oauth2 client_secret',
  `scope` varchar(256) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '必填，Oauth2 权限范围，比如 read，write等可自定义',
  `authorized_grant_types` varchar(256) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '必填，Oauth2 授权类型，支持类型：authorization_code,password,refresh_token,implicit,client_credentials，多个用英文逗号隔开',
  `web_server_redirect_uri` varchar(256) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '可选，客户端的重定向URI,当grant_type为authorization_code或implicit时,此字段是需要的',
  `authorities` varchar(256) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '可选，指定客户端所拥有的Spring Security的权限值',
  `access_token_validity` int(11) DEFAULT NULL COMMENT '可选，access_token的有效时间值(单位:秒)，不填写框架(类refreshTokenValiditySeconds)默认12小时',
  `refresh_token_validity` int(11) DEFAULT NULL COMMENT '可选，refresh_token的有效时间值(单位:秒)，不填写框架(类refreshTokenValiditySeconds)默认30天',
  `additional_information` varchar(4096) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '预留字段，格式必须是json',
  `autoapprove` varchar(256) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '该字段适用于grant_type="authorization_code"的情况下，用户是否自动approve操作',
  PRIMARY KEY (`client_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `oauth_client_details`
--

LOCK TABLES `oauth_client_details` WRITE;
/*!40000 ALTER TABLE `oauth_client_details` DISABLE KEYS */;
INSERT INTO `oauth_client_details` VALUES ('WEB',NULL,'{bcrypt}$2a$10$WMYTnw5hiHNUd6EL96AkaOxbGcJ2PApNuOo3gqMSAr43dPZGTeC0q','write','image_captcha',NULL,NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `oauth_client_details` ENABLE KEYS */;
UNLOCK TABLES;


--
-- Current Database: `member`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `member` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci */;

USE `member`;

--
-- Table structure for table `t_menu`
--

DROP TABLE IF EXISTS `t_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '菜单ID',
  `name` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '菜单名称',
  `parent_id` bigint(20) DEFAULT '0' COMMENT '父菜单ID',
  `order_num` int(4) DEFAULT '0' COMMENT '显示顺序',
  `path` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '路由地址',
  `component` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '组件路径',
  `query` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '路由参数',
  `frame` int(1) DEFAULT '1' COMMENT '是否为外链（0是 1否）',
  `cache` int(1) DEFAULT '0' COMMENT '是否缓存（0缓存 1不缓存）',
  `type` char(1) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '菜单类型（M目录 C菜单 F按钮）',
  `visible` char(1) COLLATE utf8mb4_unicode_ci DEFAULT '0' COMMENT '菜单状态（0显示 1隐藏）',
  `status` char(1) COLLATE utf8mb4_unicode_ci DEFAULT '0' COMMENT '菜单状态（0正常 1停用）',
  `perms` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '权限标识',
  `icon` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT '#' COMMENT '菜单图标',
  `create_by` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '创建者',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_by` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '更新者',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `remark` varchar(500) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`id`),
  KEY `index_menu_type` (`type`)
) ENGINE=InnoDB AUTO_INCREMENT=1064 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='菜单权限表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_menu`
--

LOCK TABLES `t_menu` WRITE;
/*!40000 ALTER TABLE `t_menu` DISABLE KEYS */;
INSERT INTO `t_menu` VALUES (1,'商品',0,1,'system',NULL,'',1,0,'M','0','0','','system','1473141102158827520','2022-02-11 09:09:19','','2022-05-10 06:30:06','系统管理目录'),(2,'交易',0,2,'monitor',NULL,'',1,0,'M','0','0','','monitor','1473141102158827520','2022-02-11 09:09:19','','2022-05-10 06:30:06','系统监控目录'),(3,'店铺',0,3,'tool',NULL,'',1,0,'M','0','0','','tool','1473141102158827520','2022-02-11 09:09:19','','2022-05-10 06:30:06','系统工具目录'),(100,'商品管理',1,1,'operator','system/operator/index','',1,0,'C','0','0','system:operator:list','operator','1473141102158827520','2022-02-11 09:09:19','','2022-05-10 06:30:06','用户管理菜单'),(102,'菜单管理',1,3,'menu','system/menu/index','',1,0,'C','0','0','system:menu:list','tree-table','1473141102158827520','2022-02-11 09:09:19','','2022-05-10 06:30:06','菜单管理菜单'),(103,'部门管理',1,4,'dept','system/dept/index','',1,0,'C','0','0','system:dept:list','tree','1473141102158827520','2022-02-11 09:09:19','','2022-05-10 06:30:06','部门管理菜单'),(104,'岗位管理',1,5,'post','system/post/index','',1,0,'C','0','0','system:post:list','post','1473141102158827520','2022-02-11 09:09:19','','2022-05-10 06:30:06','岗位管理菜单'),(105,'字典管理',1,6,'dict','system/dict/index','',1,0,'C','0','0','system:dict:list','dict','1473141102158827520','2022-02-11 09:09:20','','2022-05-10 06:30:06','字典管理菜单'),(106,'参数设置',1,7,'config','system/config/index','',1,0,'C','0','0','system:config:list','edit','1473141102158827520','2022-02-11 09:09:20','','2022-05-10 06:30:06','参数设置菜单'),(107,'通知公告',1,8,'notice','system/notice/index','',1,0,'C','0','0','system:notice:list','message','1473141102158827520','2022-02-11 09:09:20','','2022-05-10 06:30:06','通知公告菜单'),(108,'日志管理',1,9,'log','','',1,0,'M','0','0','','log','1473141102158827520','2022-02-11 09:09:20','','2022-05-10 06:30:06','日志管理菜单'),(111,'Sentinel控制台',2,3,'http://localhost:8718','','',0,0,'C','0','0','monitor:sentinel:list','sentinel','1473141102158827520','2022-02-11 09:09:20','','2022-05-10 06:30:06','流量控制菜单'),(112,'Nacos控制台',2,4,'http://localhost:8848/nacos','','',0,0,'C','0','0','monitor:nacos:list','nacos','1473141102158827520','2022-02-11 09:09:20','','2022-05-10 06:30:06','服务治理菜单'),(113,'Admin控制台',2,5,'http://localhost:9100/login','','',0,0,'C','0','0','monitor:server:list','server','1473141102158827520','2022-02-11 09:09:21','','2022-05-10 06:30:06','服务监控菜单'),(500,'操作日志',108,1,'operlog','system/operlog/index','',1,0,'C','0','0','system:operlog:list','form','1473141102158827520','2022-02-11 09:09:21','','2022-05-10 06:30:06','操作日志菜单'),(501,'登录日志',108,2,'logininfor','system/logininfor/index','',1,0,'C','0','0','system:logininfor:list','logininfor','1473141102158827520','2022-02-11 09:09:21','','2022-05-10 06:30:06','登录日志菜单'),(1001,'发布商品',100,2,'product','','',1,0,'C','0','0','system:operator:query','#','1473141102158827520','2022-02-11 09:09:21','1473141102158827520','2022-05-11 06:18:18',''),(1013,'菜单查询',102,1,'','','',1,0,'F','0','0','system:menu:query','#','1473141102158827520','2022-02-11 09:09:23','','2022-05-10 06:30:06',''),(1014,'菜单新增',102,2,'','','',1,0,'F','0','0','system:menu:add','#','1473141102158827520','2022-02-11 09:09:23','','2022-05-10 06:30:06',''),(1015,'菜单修改',102,3,'','','',1,0,'F','0','0','system:menu:edit','#','1473141102158827520','2022-02-11 09:09:23','','2022-05-10 06:30:06',''),(1016,'菜单删除',102,4,'','','',1,0,'F','0','0','system:menu:remove','#','1473141102158827520','2022-02-11 09:09:23','','2022-05-10 06:30:06',''),(1017,'部门查询',103,1,'','','',1,0,'F','0','0','system:dept:query','#','1473141102158827520','2022-02-11 09:09:23','','2022-05-10 06:30:06',''),(1018,'部门新增',103,2,'','','',1,0,'F','0','0','system:dept:add','#','1473141102158827520','2022-02-11 09:09:23','','2022-05-10 06:30:06',''),(1019,'部门修改',103,3,'','','',1,0,'F','0','0','system:dept:edit','#','1473141102158827520','2022-02-11 09:09:23','','2022-05-10 06:30:06',''),(1020,'部门删除',103,4,'','','',1,0,'F','0','0','system:dept:remove','#','1473141102158827520','2022-02-11 09:09:23','','2022-05-10 06:30:06',''),(1021,'岗位查询',104,1,'','','',1,0,'F','0','0','system:post:query','#','1473141102158827520','2022-02-11 09:09:23','','2022-05-10 06:30:06',''),(1022,'岗位新增',104,2,'','','',1,0,'F','0','0','system:post:add','#','1473141102158827520','2022-02-11 09:09:24','','2022-05-10 06:30:06',''),(1023,'岗位修改',104,3,'','','',1,0,'F','0','0','system:post:edit','#','1473141102158827520','2022-02-11 09:09:24','','2022-05-10 06:30:06',''),(1024,'岗位删除',104,4,'','','',1,0,'F','0','0','system:post:remove','#','1473141102158827520','2022-02-11 09:09:24','','2022-05-10 06:30:06',''),(1025,'岗位导出',104,5,'','','',1,0,'F','0','0','system:post:export','#','1473141102158827520','2022-02-11 09:09:24','','2022-05-10 06:30:06',''),(1026,'字典查询',105,1,'#','','',1,0,'F','0','0','system:dict:query','#','1473141102158827520','2022-02-11 09:09:24','','2022-05-10 06:30:06',''),(1027,'字典新增',105,2,'#','','',1,0,'F','0','0','system:dict:add','#','1473141102158827520','2022-02-11 09:09:24','','2022-05-10 06:30:06',''),(1028,'字典修改',105,3,'#','','',1,0,'F','0','0','system:dict:edit','#','1473141102158827520','2022-02-11 09:09:24','','2022-05-10 06:30:06',''),(1029,'字典删除',105,4,'#','','',1,0,'F','0','0','system:dict:remove','#','1473141102158827520','2022-02-11 09:09:24','','2022-05-10 06:30:06',''),(1030,'字典导出',105,5,'#','','',1,0,'F','0','0','system:dict:export','#','1473141102158827520','2022-02-11 09:09:24','','2022-05-10 06:30:06',''),(1031,'参数查询',106,1,'#','','',1,0,'F','0','0','system:config:query','#','1473141102158827520','2022-02-11 09:09:24','','2022-05-10 06:30:06',''),(1032,'参数新增',106,2,'#','','',1,0,'F','0','0','system:config:add','#','1473141102158827520','2022-02-11 09:09:25','','2022-05-10 06:30:06',''),(1033,'参数修改',106,3,'#','','',1,0,'F','0','0','system:config:edit','#','1473141102158827520','2022-02-11 09:09:25','','2022-05-10 06:30:06',''),(1034,'参数删除',106,4,'#','','',1,0,'F','0','0','system:config:remove','#','1473141102158827520','2022-02-11 09:09:25','','2022-05-10 06:30:06',''),(1035,'参数导出',106,5,'#','','',1,0,'F','0','0','system:config:export','#','1473141102158827520','2022-02-11 09:09:25','','2022-05-10 06:30:06',''),(1036,'公告查询',107,1,'#','','',1,0,'F','0','0','system:notice:query','#','1473141102158827520','2022-02-11 09:09:25','','2022-05-10 06:30:06',''),(1037,'公告新增',107,2,'#','','',1,0,'F','0','0','system:notice:add','#','1473141102158827520','2022-02-11 09:09:25','','2022-05-10 06:30:06',''),(1038,'公告修改',107,3,'#','','',1,0,'F','0','0','system:notice:edit','#','1473141102158827520','2022-02-11 09:09:25','','2022-05-10 06:30:06',''),(1039,'公告删除',107,4,'#','','',1,0,'F','0','0','system:notice:remove','#','1473141102158827520','2022-02-11 09:09:25','','2022-05-10 06:30:06',''),(1040,'操作查询',500,1,'#','','',1,0,'F','0','0','system:operlog:query','#','1473141102158827520','2022-02-11 09:09:26','','2022-05-10 06:30:06',''),(1041,'操作删除',500,2,'#','','',1,0,'F','0','0','system:operlog:remove','#','1473141102158827520','2022-02-11 09:09:26','','2022-05-10 06:30:06',''),(1042,'日志导出',500,4,'#','','',1,0,'F','0','0','system:operlog:export','#','1473141102158827520','2022-02-11 09:09:26','','2022-05-10 06:30:06',''),(1043,'登录查询',501,1,'#','','',1,0,'F','0','0','system:logininfor:query','#','1473141102158827520','2022-02-11 09:09:26','','2022-05-10 06:30:06',''),(1044,'登录删除',501,2,'#','','',1,0,'F','0','0','system:logininfor:remove','#','1473141102158827520','2022-02-11 09:09:26','','2022-05-10 06:30:06',''),(1045,'日志导出',501,3,'#','','',1,0,'F','0','0','system:logininfor:export','#','1473141102158827520','2022-02-11 09:09:26','','2022-05-10 06:30:06',''),(1061,'首页',0,0,'home','home',NULL,1,0,'C','0','0','home:list','home','1473141102158827520','2022-05-10 09:13:33','','2022-05-10 09:13:32',''),(1062,'我的商品',100,1,'product',NULL,NULL,1,0,'C','0','0','product:goods:list','#','1473141102158827520','2022-05-11 06:17:22','','2022-05-11 06:17:20',''),(1063,'用户',0,4,'operator',NULL,NULL,1,0,'M','0','0',NULL,'operator','1473141102158827520','2022-05-11 06:19:54','','2022-05-11 06:19:53','');
/*!40000 ALTER TABLE `t_menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_role`
--

DROP TABLE IF EXISTS `t_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_role` (
  `id` bigint(29) NOT NULL,
  `code` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '角色名称',
  `name` varchar(128) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '角色描述',
  `status` int(1) DEFAULT '0' COMMENT '状态 0-正常 1-删除 2-禁用',
  `parent_id` bigint(20) DEFAULT NULL COMMENT '父角色ID',
  `create_by` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '创建者',
  `update_by` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '更新者',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `sort` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='角色表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_role`
--

LOCK TABLES `t_role` WRITE;
/*!40000 ALTER TABLE `t_role` DISABLE KEYS */;
INSERT INTO `t_role` VALUES (1,'SHOP_KEEPER','店主',0,NULL,'','1473141102158827520','2022-01-27 08:56:59','2022-05-11 07:05:32',1);
/*!40000 ALTER TABLE `t_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_role_menu`
--

DROP TABLE IF EXISTS `t_role_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_role_menu` (
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  `menu_id` bigint(20) NOT NULL COMMENT '菜单ID',
  PRIMARY KEY (`role_id`,`menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='角色菜单表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_role_menu`
--

LOCK TABLES `t_role_menu` WRITE;
/*!40000 ALTER TABLE `t_role_menu` DISABLE KEYS */;
INSERT INTO `t_role_menu` VALUES (1,1),(1,2),(1,3),(1,100),(1,102),(1,103),(1,104),(1,105),(1,106),(1,107),(1,108),(1,111),(1,112),(1,113),(1,500),(1,501),(1,1001),(1,1013),(1,1014),(1,1015),(1,1016),(1,1017),(1,1018),(1,1019),(1,1020),(1,1021),(1,1022),(1,1023),(1,1024),(1,1025),(1,1026),(1,1027),(1,1028),(1,1029),(1,1030),(1,1031),(1,1032),(1,1033),(1,1034),(1,1035),(1,1036),(1,1037),(1,1038),(1,1039),(1,1040),(1,1041),(1,1042),(1,1043),(1,1044),(1,1045),(1,1061),(1,1062),(1,1063);
/*!40000 ALTER TABLE `t_role_menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_sys_dept`
--

DROP TABLE IF EXISTS `t_sys_dept`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_sys_dept` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '部门id',
  `parent_id` bigint(20) DEFAULT '0' COMMENT '父部门id',
  `ancestors` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '祖级列表',
  `name` varchar(30) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '部门名称',
  `order_num` int(4) DEFAULT '0' COMMENT '显示顺序',
  `leader` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '负责人',
  `phone` varchar(11) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '联系电话',
  `email` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '邮箱',
  `status` char(1) COLLATE utf8mb4_unicode_ci DEFAULT '0' COMMENT '部门状态（0正常 1停用）',
  `del_flag` char(1) COLLATE utf8mb4_unicode_ci DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  `create_by` bigint(20) DEFAULT NULL COMMENT '创建者',
  `create_by_id` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by_id` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '更新者',
  `update_by` bigint(20) DEFAULT NULL COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=200 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='部门表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_sys_dept`
--

LOCK TABLES `t_sys_dept` WRITE;
/*!40000 ALTER TABLE `t_sys_dept` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_sys_dept` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_user`
--

DROP TABLE IF EXISTS `t_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_user` (
  `id` bigint(20) NOT NULL,
  `username` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '用户名-用于登录',
  `nick_name` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '昵称',
  `shop_no` bigint(20) DEFAULT NULL COMMENT '店铺编号',
  `password` varchar(128) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '密码',
  `salt` varchar(36) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '加密sale',
  `encrypt_key` varchar(36) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '加密串',
  `phone` varchar(11) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '手机号',
  `status` tinyint(1) DEFAULT '0' COMMENT '账号状态 0-正常 1-删除 2-锁定',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `last_sign_time` timestamp NULL DEFAULT NULL COMMENT '上吃登录时间',
  `update_by` bigint(20) DEFAULT NULL COMMENT '修改人编号',
  `create_by` bigint(20) DEFAULT NULL COMMENT '创建人编号',
  `avatar` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '头像',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='用户表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_user`
--

LOCK TABLES `t_user` WRITE;
/*!40000 ALTER TABLE `t_user` DISABLE KEYS */;
INSERT INTO `t_user` VALUES (1473141102158827520,'muyuan001','my_wDvYcRo823',0,'XaZfatnjIDAy5/nAbwxLFVOA8Yo=','56dcf99a-ca90-4ff2-b826-4e90ee5a6bd1','23d2a49b-8b49-41c7-894a-51e6c7448dcd',NULL,0,'2021-12-21 04:00:11','2021-12-21 04:00:11',NULL,0,0,NULL),(1475479873988296704,'muyuan002','my_hLfDt4c941',NULL,'xnxPBOjQS3vghVaSZ0ee3fKgDQI=','08512f9c-28c5-4440-ab79-6d8d3d28b409','1b7f18ab-4560-4632-87ca-5c127dc3dc51',NULL,0,'2021-12-27 14:53:09','2022-03-18 07:28:28',NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `t_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_user_role`
--

DROP TABLE IF EXISTS `t_user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_user_role` (
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`user_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='用户角色表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_user_role`
--

LOCK TABLES `t_user_role` WRITE;
/*!40000 ALTER TABLE `t_user_role` DISABLE KEYS */;
INSERT INTO `t_user_role` VALUES (1473141102158827520,1);
/*!40000 ALTER TABLE `t_user_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Current Database: `nacos`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `nacos` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci */;

USE `nacos`;

--
-- Table structure for table `config_info`
--

DROP TABLE IF EXISTS `config_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `config_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `data_id` varchar(255) COLLATE utf8_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `content` longtext COLLATE utf8_bin NOT NULL COMMENT 'content',
  `md5` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT 'md5',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `src_user` text COLLATE utf8_bin COMMENT 'source operator',
  `src_ip` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT 'source ip',
  `app_name` varchar(128) COLLATE utf8_bin DEFAULT NULL,
  `tenant_id` varchar(128) COLLATE utf8_bin DEFAULT '' COMMENT '租户字段',
  `c_desc` varchar(256) COLLATE utf8_bin DEFAULT NULL,
  `c_use` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `effect` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `type` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `c_schema` text COLLATE utf8_bin,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_configinfo_datagrouptenant` (`data_id`,`group_id`,`tenant_id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='config_info';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `config_info`
--

LOCK TABLES `config_info` WRITE;
/*!40000 ALTER TABLE `config_info` DISABLE KEYS */;
INSERT INTO `config_info` VALUES (1,'member-dev.properties','MUYUAN','member.jdbc.driver-class-name=com.mysql.jdbc.Driver\nmember.jdbc.url=jdbc:mysql://119.29.77.107:3306/member?characterEncoding=utf8&useSSL=false&serverTimezone=UTC&rewriteBatchedStatements=true\nmember.jdbc.password=4+1NGowdKIndl\nmember.jdbc.username=root\n\nspring.redis.host=119.29.77.107\nspring.redis.port=6379\nspring.redis.password=4+1NGoDdKIndl\n\nserver.servlet.encoding.cherset=UTF-8','a5e7c9ea6618d8e44732032b029571e5','2022-03-16 02:30:00','2022-03-16 05:55:50','nacos','113.118.115.107','','c3108468-5b07-4639-a101-3cf12feff1ba','用户系统配置','','','properties',''),(2,'auth-dev.properties','MUYUAN','auth.jdbc.username=root\nauth.jdbc.password=4+1NGowdKIndl\nauth.jdbc.driver-class-name=com.mysql.jdbc.Driver\nauth.jdbc.url=jdbc:mysql://119.29.77.107:3306/auth?serverTimezone=UTC\n\nspring.redis.host=119.29.77.107\nspring.redis.port=6379\nspring.redis.password=4+1NGoDdKIndl','3aad1343460d954128c106c1ce7e8296','2022-03-16 02:30:00','2022-04-19 14:24:40','nacos','120.229.60.236','','c3108468-5b07-4639-a101-3cf12feff1ba','用户系统配置','','','properties',''),(3,'gateway-dev.properties','MUYUAN','member.jdbc.driver-class-name=com.mysql.jdbc.Driver\nmember.jdbc.url=jdbc:mysql://119.29.77.107:3306/member?characterEncoding=utf8&useSSL=false&serverTimezone=UTC&rewriteBatchedStatements=true\nmember.jdbc.password=4+1NGowdKIndl\nmember.jdbc.username=root\n\nspring.redis.host=119.29.77.107\nspring.redis.port=6379\nspring.redis.password=4+1NGoDdKIndl\n\nspring.cloud.gateway.routes[0].id=member\nspring.cloud.gateway.routes[0].uri=lb://member\nspring.cloud.gateway.routes[0].predicates[0]=Path=/member/**\nspring.cloud.gateway.routes[0].filters[0]=StripPrefix=1\n\nspring.cloud.gateway.routes[1].id=auth\nspring.cloud.gateway.routes[1].uri=lb://auth\nspring.cloud.gateway.routes[1].predicates[0]=Path=/auth/**\nspring.cloud.gateway.routes[1].filters[0]=StripPrefix=1\n\nspring.cloud.gateway.routes[2].id=system\nspring.cloud.gateway.routes[2].uri=lb://system\nspring.cloud.gateway.routes[2].predicates[0]=Path=/system/**\nspring.cloud.gateway.routes[2].filters[0]=StripPrefix=1\n\nsecure.ignore[0]=/api/actuator/**\nsecure.ignore[1]=/api/auth/oauth/token\nsecure.ignore[2]=/api/auth/oauth/authorize\nsecure.ignore[3]=/api/auth/login/**\nsecure.ignore[4]=/doc.html\nsecure.ignore[5]=/webjars/**\nsecure.ignore[6]=/swagger-**\nsecure.ignore[7]=/favicon.ico\nsecure.ignore[8]=/api/member/v3/**\nsecure.ignore[9]=/api/system/v3/**\nsecure.ignore[10]=/api/auth/v3/**\n','4ddd8b4d84e3caaaafebbb025dcdb30b','2022-03-16 02:30:00','2022-03-17 11:07:27','nacos','113.118.112.245','','c3108468-5b07-4639-a101-3cf12feff1ba','用户系统配置','','','properties',''),(4,'system-dev.properties','MUYUAN','system.jdbc.driver-class-name=com.mysql.jdbc.Driver\nsystem.jdbc.url=jdbc:mysql://119.29.77.107:3306/system?characterEncoding=utf8&useSSL=false&serverTimezone=UTC&rewriteBatchedStatements=true\nsystem.jdbc.password=4+1NGowdKIndl\nsystem.jdbc.username=root\n\nspring.redis.host=119.29.77.107\nspring.redis.port=6379\nspring.redis.password=4+1NGoDdKIndl\n\nserver.servlet.encoding.cherset=UTF-8','07d49033fff94283b2decd3dd0dc2d53','2022-03-16 02:30:00','2022-03-16 05:57:08','nacos','113.116.30.124','','c3108468-5b07-4639-a101-3cf12feff1ba','用户系统配置','','','properties',''),(21,'member-dev.properties','MUYUAN','member.jdbc.username=root\nmember.jdbc.password=4+1NGowdKIndl\nmember.jdbc.driver-class-name=com.mysql.jdbc.Driver\nmember.jdbc.url=jdbc:mysql://119.29.77.107:3306/member?serverTimezone=UTC&autoReconnect=true&&failOverReadOnly=false\n\nspring.redis.host=119.29.77.107\nspring.redis.port=6379\nspring.redis.password=4+1NGoDdKIndl','1305f64ac51ce4ee989bc3972af1f771','2022-03-22 01:18:40','2022-04-24 07:10:27','nacos','113.118.93.215','','f650d0e5-398a-4230-9cfb-235548328981','','','','properties',''),(22,'auth-dev.properties','MUYUAN','auth.jdbc.username=root\nauth.jdbc.password=4+1NGowdKIndl\nauth.jdbc.driver-class-name=com.mysql.jdbc.Driver\nauth.jdbc.url=jdbc:mysql://119.29.77.107:3306/auth?serverTimezone=UTC&autoReconnect=true&&failOverReadOnly=false\n\nspring.redis.host=119.29.77.107\nspring.redis.port=6379\nspring.redis.password=4+1NGoDdKIndl','038998963943a87dfc2d7084d711545b','2022-03-22 01:18:40','2022-04-24 07:10:13','nacos','113.118.93.215','','f650d0e5-398a-4230-9cfb-235548328981','','','','properties',''),(23,'gateway-dev.properties','MUYUAN','auth.jdbc.username=root\nauth.jdbc.password=root@123\nauth.jdbc.driver-class-name=com.mysql.jdbc.Driver\nauth.jdbc.url=jdbc:mysql://localhost:3306/auth?serverTimezone=UTC&autoReconnect=true&&failOverReadOnly=false\n\nspring.redis.host=119.29.77.107\nspring.redis.port=6379\nspring.redis.password=4+1NGoDdKIndl\n\nsecure.ignore[0]=/api/actuator/**\nsecure.ignore[1]=/api/auth/oauth/token\nsecure.ignore[2]=/api/auth/oauth/authorize\nsecure.ignore[3]=/api/auth/login/**\nsecure.ignore[4]=/doc.html\nsecure.ignore[5]=/webjars/**\nsecure.ignore[6]=/swagger-**\nsecure.ignore[7]=/favicon.ico\nsecure.ignore[8]=/api/member/v3/**\nsecure.ignore[9]=/api/system/v3/**\nsecure.ignore[10]=/api/auth/v3/**\n\nspring.cloud.gateway.routes[0].id=member\nspring.cloud.gateway.routes[0].uri=lb://member\nspring.cloud.gateway.routes[0].predicates[0]=Path=/member/**\nspring.cloud.gateway.routes[0].filters[0]=StripPrefix=1\n\nspring.cloud.gateway.routes[1].id=auth\nspring.cloud.gateway.routes[1].uri=lb://auth\nspring.cloud.gateway.routes[1].predicates[0]=Path=/auth/**\nspring.cloud.gateway.routes[1].filters[0]=StripPrefix=1\n\nspring.cloud.gateway.routes[1].id=system\nspring.cloud.gateway.routes[1].uri=lb://system\nspring.cloud.gateway.routes[1].predicates[0]=Path=/system/**\nspring.cloud.gateway.routes[1].filters[0]=StripPrefix=1','1f1904689488c851b8d2a7b29b2324b0','2022-03-22 01:18:40','2022-04-24 07:10:41','nacos','113.118.93.215','','f650d0e5-398a-4230-9cfb-235548328981','','','','properties',''),(27,'system-dev.properties','MUYUAN','system.jdbc.username=root\nsystem.jdbc.password=4+1NGowdKIndl\nsystem.jdbc.driver-class-name=com.mysql.jdbc.Driver\nsystem.jdbc.url=jdbc:mysql://119.29.77.107:3306/system?serverTimezone=UTC&autoReconnect=true&&failOverReadOnly=false\n\nspring.redis.host=119.29.77.107\nspring.redis.port=6379\nspring.redis.password=4+1NGoDdKIndl','f559d8c04cc2fb2188c48fbc63cae46a','2022-03-22 01:18:40','2022-04-24 07:10:52','nacos','113.118.93.215','','f650d0e5-398a-4230-9cfb-235548328981','','','','properties','');
/*!40000 ALTER TABLE `config_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `config_info_aggr`
--

DROP TABLE IF EXISTS `config_info_aggr`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `config_info_aggr` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `data_id` varchar(255) COLLATE utf8_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(255) COLLATE utf8_bin NOT NULL COMMENT 'group_id',
  `datum_id` varchar(255) COLLATE utf8_bin NOT NULL COMMENT 'datum_id',
  `content` longtext COLLATE utf8_bin NOT NULL COMMENT '内容',
  `gmt_modified` datetime NOT NULL COMMENT '修改时间',
  `app_name` varchar(128) COLLATE utf8_bin DEFAULT NULL,
  `tenant_id` varchar(128) COLLATE utf8_bin DEFAULT '' COMMENT '租户字段',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_configinfoaggr_datagrouptenantdatum` (`data_id`,`group_id`,`tenant_id`,`datum_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='增加租户字段';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `config_info_aggr`
--

LOCK TABLES `config_info_aggr` WRITE;
/*!40000 ALTER TABLE `config_info_aggr` DISABLE KEYS */;
/*!40000 ALTER TABLE `config_info_aggr` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `config_info_beta`
--

DROP TABLE IF EXISTS `config_info_beta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `config_info_beta` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `data_id` varchar(255) COLLATE utf8_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(128) COLLATE utf8_bin NOT NULL COMMENT 'group_id',
  `app_name` varchar(128) COLLATE utf8_bin DEFAULT NULL COMMENT 'app_name',
  `content` longtext COLLATE utf8_bin NOT NULL COMMENT 'content',
  `beta_ips` varchar(1024) COLLATE utf8_bin DEFAULT NULL COMMENT 'betaIps',
  `md5` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT 'md5',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `src_user` text COLLATE utf8_bin COMMENT 'source operator',
  `src_ip` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT 'source ip',
  `tenant_id` varchar(128) COLLATE utf8_bin DEFAULT '' COMMENT '租户字段',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_configinfobeta_datagrouptenant` (`data_id`,`group_id`,`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='config_info_beta';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `config_info_beta`
--

LOCK TABLES `config_info_beta` WRITE;
/*!40000 ALTER TABLE `config_info_beta` DISABLE KEYS */;
/*!40000 ALTER TABLE `config_info_beta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `config_info_tag`
--

DROP TABLE IF EXISTS `config_info_tag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `config_info_tag` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `data_id` varchar(255) COLLATE utf8_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(128) COLLATE utf8_bin NOT NULL COMMENT 'group_id',
  `tenant_id` varchar(128) COLLATE utf8_bin DEFAULT '' COMMENT 'tenant_id',
  `tag_id` varchar(128) COLLATE utf8_bin NOT NULL COMMENT 'tag_id',
  `app_name` varchar(128) COLLATE utf8_bin DEFAULT NULL COMMENT 'app_name',
  `content` longtext COLLATE utf8_bin NOT NULL COMMENT 'content',
  `md5` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT 'md5',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `src_user` text COLLATE utf8_bin COMMENT 'source operator',
  `src_ip` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT 'source ip',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_configinfotag_datagrouptenanttag` (`data_id`,`group_id`,`tenant_id`,`tag_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='config_info_tag';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `config_info_tag`
--

LOCK TABLES `config_info_tag` WRITE;
/*!40000 ALTER TABLE `config_info_tag` DISABLE KEYS */;
/*!40000 ALTER TABLE `config_info_tag` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `config_tags_relation`
--

DROP TABLE IF EXISTS `config_tags_relation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `config_tags_relation` (
  `id` bigint(20) NOT NULL COMMENT 'id',
  `tag_name` varchar(128) COLLATE utf8_bin NOT NULL COMMENT 'tag_name',
  `tag_type` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT 'tag_type',
  `data_id` varchar(255) COLLATE utf8_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(128) COLLATE utf8_bin NOT NULL COMMENT 'group_id',
  `tenant_id` varchar(128) COLLATE utf8_bin DEFAULT '' COMMENT 'tenant_id',
  `nid` bigint(20) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`nid`),
  UNIQUE KEY `uk_configtagrelation_configidtag` (`id`,`tag_name`,`tag_type`),
  KEY `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='config_tag_relation';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `config_tags_relation`
--

LOCK TABLES `config_tags_relation` WRITE;
/*!40000 ALTER TABLE `config_tags_relation` DISABLE KEYS */;
/*!40000 ALTER TABLE `config_tags_relation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `group_capacity`
--

DROP TABLE IF EXISTS `group_capacity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `group_capacity` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `group_id` varchar(128) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT 'Group ID，空字符表示整个集群',
  `quota` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '配额，0表示使用默认值',
  `usage` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '使用量',
  `max_size` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '单个配置大小上限，单位为字节，0表示使用默认值',
  `max_aggr_count` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '聚合子配置最大个数，，0表示使用默认值',
  `max_aggr_size` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '单个聚合数据的子配置大小上限，单位为字节，0表示使用默认值',
  `max_history_count` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '最大变更历史数量',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_group_id` (`group_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='集群、各Group容量信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `group_capacity`
--

LOCK TABLES `group_capacity` WRITE;
/*!40000 ALTER TABLE `group_capacity` DISABLE KEYS */;
/*!40000 ALTER TABLE `group_capacity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `his_config_info`
--

DROP TABLE IF EXISTS `his_config_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `his_config_info` (
  `id` bigint(64) unsigned NOT NULL,
  `nid` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `data_id` varchar(255) COLLATE utf8_bin NOT NULL,
  `group_id` varchar(128) COLLATE utf8_bin NOT NULL,
  `app_name` varchar(128) COLLATE utf8_bin DEFAULT NULL COMMENT 'app_name',
  `content` longtext COLLATE utf8_bin NOT NULL,
  `md5` varchar(32) COLLATE utf8_bin DEFAULT NULL,
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `src_user` text COLLATE utf8_bin,
  `src_ip` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `op_type` char(10) COLLATE utf8_bin DEFAULT NULL,
  `tenant_id` varchar(128) COLLATE utf8_bin DEFAULT '' COMMENT '租户字段',
  PRIMARY KEY (`nid`),
  KEY `idx_gmt_create` (`gmt_create`),
  KEY `idx_gmt_modified` (`gmt_modified`),
  KEY `idx_did` (`data_id`)
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='多租户改造';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `his_config_info`
--

LOCK TABLES `his_config_info` WRITE;
/*!40000 ALTER TABLE `his_config_info` DISABLE KEYS */;
INSERT INTO `his_config_info` VALUES (2,40,'auth-dev.properties','MUYUAN','','auth.jdbc.driver-class-name=com.mysql.jdbc.Driver\nauth.jdbc.url=jdbc:mysql://119.29.77.107:3306/auth?characterEncoding=utf8&useSSL=false&serverTimezone=UTC&rewriteBatchedStatements=true\nauth.jdbc.password=4+1NGowdKIndl\nauth.jdbc.username=root\n\nspring.redis.host=119.29.77.107\nspring.redis.port=6379\nspring.redis.password=4+1NGoDdKIndl','5b6136744df8b8f8f3258c19bcab752a','2022-04-19 22:24:39','2022-04-19 14:24:40','nacos','120.229.60.236','U','c3108468-5b07-4639-a101-3cf12feff1ba'),(22,41,'auth-dev.properties','MUYUAN','','auth.jdbc.username=root\nauth.jdbc.password=4+1NGowdKIndl\nauth.jdbc.driver-class-name=com.mysql.jdbc.Driver\nauth.jdbc.url=jdbc:mysql://119.29.77.107:3306/auth?serverTimezone=UTC\n\nspring.redis.host=119.29.77.107\nspring.redis.port=6379\nspring.redis.password=4+1NGoDdKIndl','3aad1343460d954128c106c1ce7e8296','2022-04-24 15:10:12','2022-04-24 07:10:13','nacos','113.118.93.215','U','f650d0e5-398a-4230-9cfb-235548328981'),(21,42,'member-dev.properties','MUYUAN','','member.jdbc.username=root\nmember.jdbc.password=4+1NGowdKIndl\nmember.jdbc.driver-class-name=com.mysql.jdbc.Driver\nmember.jdbc.url=jdbc:mysql://119.29.77.107:3306/member?serverTimezone=UTC\n\nspring.redis.host=119.29.77.107\nspring.redis.port=6379\nspring.redis.password=4+1NGoDdKIndl','c89e20850eb29c27e63653a1f9d7e8a9','2022-04-24 15:10:27','2022-04-24 07:10:27','nacos','113.118.93.215','U','f650d0e5-398a-4230-9cfb-235548328981'),(23,43,'gateway-dev.properties','MUYUAN','','auth.jdbc.username=root\nauth.jdbc.password=root@123\nauth.jdbc.driver-class-name=com.mysql.jdbc.Driver\nauth.jdbc.url=jdbc:mysql://localhost:3306/auth?serverTimezone=UTC\n\nspring.redis.host=119.29.77.107\nspring.redis.port=6379\nspring.redis.password=4+1NGoDdKIndl\n\nsecure.ignore[0]=/api/actuator/**\nsecure.ignore[1]=/api/auth/oauth/token\nsecure.ignore[2]=/api/auth/oauth/authorize\nsecure.ignore[3]=/api/auth/login/**\nsecure.ignore[4]=/doc.html\nsecure.ignore[5]=/webjars/**\nsecure.ignore[6]=/swagger-**\nsecure.ignore[7]=/favicon.ico\nsecure.ignore[8]=/api/member/v3/**\nsecure.ignore[9]=/api/system/v3/**\nsecure.ignore[10]=/api/auth/v3/**\n\nspring.cloud.gateway.routes[0].id=member\nspring.cloud.gateway.routes[0].uri=lb://member\nspring.cloud.gateway.routes[0].predicates[0]=Path=/member/**\nspring.cloud.gateway.routes[0].filters[0]=StripPrefix=1\n\nspring.cloud.gateway.routes[1].id=auth\nspring.cloud.gateway.routes[1].uri=lb://auth\nspring.cloud.gateway.routes[1].predicates[0]=Path=/auth/**\nspring.cloud.gateway.routes[1].filters[0]=StripPrefix=1\n\nspring.cloud.gateway.routes[1].id=system\nspring.cloud.gateway.routes[1].uri=lb://system\nspring.cloud.gateway.routes[1].predicates[0]=Path=/system/**\nspring.cloud.gateway.routes[1].filters[0]=StripPrefix=1','d14938e8badd861e6b9320ced53521c6','2022-04-24 15:10:41','2022-04-24 07:10:41','nacos','113.118.93.215','U','f650d0e5-398a-4230-9cfb-235548328981'),(27,44,'system-dev.properties','MUYUAN','','system.jdbc.username=root\nsystem.jdbc.password=4+1NGowdKIndl\nsystem.jdbc.driver-class-name=com.mysql.jdbc.Driver\nsystem.jdbc.url=jdbc:mysql://119.29.77.107:3306/system?serverTimezone=UTC\n\nspring.redis.host=119.29.77.107\nspring.redis.port=6379\nspring.redis.password=4+1NGoDdKIndl','9d593e331913c96c4fdbda5fcb5d709d','2022-04-24 15:10:51','2022-04-24 07:10:52','nacos','113.118.93.215','U','f650d0e5-398a-4230-9cfb-235548328981');
/*!40000 ALTER TABLE `his_config_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `permissions`
--

DROP TABLE IF EXISTS `permissions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `permissions` (
  `role` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `resource` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `action` varchar(8) COLLATE utf8mb4_unicode_ci NOT NULL,
  UNIQUE KEY `uk_role_permission` (`role`,`resource`,`action`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `permissions`
--

LOCK TABLES `permissions` WRITE;
/*!40000 ALTER TABLE `permissions` DISABLE KEYS */;
/*!40000 ALTER TABLE `permissions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `roles` (
  `username` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `role` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  UNIQUE KEY `idx_user_role` (`username`,`role`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES ('nacos','ROLE_ADMIN');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tenant_capacity`
--

DROP TABLE IF EXISTS `tenant_capacity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tenant_capacity` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `tenant_id` varchar(128) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT 'Tenant ID',
  `quota` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '配额，0表示使用默认值',
  `usage` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '使用量',
  `max_size` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '单个配置大小上限，单位为字节，0表示使用默认值',
  `max_aggr_count` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '聚合子配置最大个数',
  `max_aggr_size` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '单个聚合数据的子配置大小上限，单位为字节，0表示使用默认值',
  `max_history_count` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '最大变更历史数量',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='租户容量信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tenant_capacity`
--

LOCK TABLES `tenant_capacity` WRITE;
/*!40000 ALTER TABLE `tenant_capacity` DISABLE KEYS */;
/*!40000 ALTER TABLE `tenant_capacity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tenant_info`
--

DROP TABLE IF EXISTS `tenant_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tenant_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `kp` varchar(128) COLLATE utf8_bin NOT NULL COMMENT 'kp',
  `tenant_id` varchar(128) COLLATE utf8_bin DEFAULT '' COMMENT 'tenant_id',
  `tenant_name` varchar(128) COLLATE utf8_bin DEFAULT '' COMMENT 'tenant_name',
  `tenant_desc` varchar(256) COLLATE utf8_bin DEFAULT NULL COMMENT 'tenant_desc',
  `create_source` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT 'create_source',
  `gmt_create` bigint(20) NOT NULL COMMENT '创建时间',
  `gmt_modified` bigint(20) NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_tenant_info_kptenantid` (`kp`,`tenant_id`),
  KEY `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='tenant_info';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tenant_info`
--

LOCK TABLES `tenant_info` WRITE;
/*!40000 ALTER TABLE `tenant_info` DISABLE KEYS */;
INSERT INTO `tenant_info` VALUES (1,'1','c3108468-5b07-4639-a101-3cf12feff1ba','dev','开发','nacos',1647397753346,1647397753346),(2,'1','f650d0e5-398a-4230-9cfb-235548328981','dev_cmp','公司','nacos',1647397774828,1647397774828);
/*!40000 ALTER TABLE `tenant_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `operators`
--

DROP TABLE IF EXISTS `operators`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `operators` (
  `username` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `password` varchar(500) COLLATE utf8mb4_unicode_ci NOT NULL,
  `enabled` tinyint(1) NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `operators`
--

LOCK TABLES `operators` WRITE;
/*!40000 ALTER TABLE `operators` DISABLE KEYS */;
INSERT INTO `operators` VALUES ('nacos','$2a$10$t5vfTZW7dUsucf/EMP.FYufA3lKOtyB.k6btPGh7jDzA4X0CDTgDm',1);
/*!40000 ALTER TABLE `operators` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Current Database: `product`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `product` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci */;

USE `product`;

--
-- Table structure for table `t_attribute_value`
--

DROP TABLE IF EXISTS `t_attribute_value`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_attribute_value` (
  `id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='分类可选属性值';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_attribute_value`
--

LOCK TABLES `t_attribute_value` WRITE;
/*!40000 ALTER TABLE `t_attribute_value` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_attribute_value` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_base_attribute`
--

DROP TABLE IF EXISTS `t_base_attribute`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_base_attribute` (
  `id` bigint(20) NOT NULL,
  `name` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '属性名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='基础属性表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_base_attribute`
--

LOCK TABLES `t_base_attribute` WRITE;
/*!40000 ALTER TABLE `t_base_attribute` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_base_attribute` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_brand`
--

DROP TABLE IF EXISTS `t_brand`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_brand` (
  `id` bigint(20) NOT NULL,
  `name` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '品牌名称',
  `parent_id` bigint(20) DEFAULT '0' COMMENT '父品牌IDD',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='品牌表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_brand`
--

LOCK TABLES `t_brand` WRITE;
/*!40000 ALTER TABLE `t_brand` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_brand` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_category`
--

DROP TABLE IF EXISTS `t_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_category` (
  `id` bigint(20) NOT NULL,
  `parend_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '父类ID',
  `name` varchar(62) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '分类名称',
  `level` int(11) NOT NULL COMMENT '晨级',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='商品分类';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_category`
--

LOCK TABLES `t_category` WRITE;
/*!40000 ALTER TABLE `t_category` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_category_attribute`
--

DROP TABLE IF EXISTS `t_category_attribute`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_category_attribute` (
  `id` bigint(20) NOT NULL,
  `name` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '属性名称',
  `category_id` bigint(20) NOT NULL COMMENT '商品分类ID',
  `type` int(11) NOT NULL COMMENT '属性类型 1:关键属性 2:销售属性 3:非关键属性',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='商品分类属性';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_category_attribute`
--

LOCK TABLES `t_category_attribute` WRITE;
/*!40000 ALTER TABLE `t_category_attribute` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_category_attribute` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_product`
--

DROP TABLE IF EXISTS `t_product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_product` (
  `id` bigint(20) NOT NULL,
  `name` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '产品名称',
  `description` varchar(128) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '商品描述',
  `tag` varchar(128) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '商品标签逗号隔开',
  `shop_id` bigint(20) NOT NULL COMMENT '店铺ID',
  `category_id` bigint(20) NOT NULL COMMENT '分类ID',
  `brand_id` bigint(20) NOT NULL COMMENT '品牌ID',
  `brand_name` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '品牌名称',
  `main_picture_url` varchar(128) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '主图URL',
  `status` varchar(1) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '0' COMMENT '0-未上架 1-上架 2-删除',
  `create_id` bigint(20) NOT NULL COMMENT '创建人',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='商品表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_product`
--

LOCK TABLES `t_product` WRITE;
/*!40000 ALTER TABLE `t_product` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_sku`
--

DROP TABLE IF EXISTS `t_sku`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_sku` (
  `id` bigint(20) NOT NULL,
  `product_id` bigint(29) NOT NULL COMMENT '产品ID',
  `context` varchar(256) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'sku内容 JSON格式字符串',
  `price` double NOT NULL COMMENT '售价 单位（分）',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='SKU';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_sku`
--

LOCK TABLES `t_sku` WRITE;
/*!40000 ALTER TABLE `t_sku` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_sku` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Current Database: `shop`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `shop` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci */;

USE `shop`;

--
-- Current Database: `system`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `system` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci */;

USE `system`;

--
-- Table structure for table `t_dict_data`
--

DROP TABLE IF EXISTS `t_dict_data`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_dict_data` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '字典编码',
  `sort` int(4) DEFAULT '0' COMMENT '字典排序',
  `label` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '字典标签',
  `value` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '字典键值',
  `type` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '字典类型',
  `css_class` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '样式属性（其他样式扩展）',
  `list_class` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '表格回显样式',
  `def` char(1) COLLATE utf8mb4_unicode_ci DEFAULT 'N' COMMENT '是否默认（Y是 N否）',
  `status` tinyint(1) DEFAULT '0' COMMENT '状态（0正常 1停用）',
  `create_by` bigint(20) DEFAULT NULL COMMENT '创建者',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `update_by` bigint(20) DEFAULT NULL COMMENT '更新者',
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `remark` varchar(500) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='字典数据表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_dict_data`
--

LOCK TABLES `t_dict_data` WRITE;
/*!40000 ALTER TABLE `t_dict_data` DISABLE KEYS */;
INSERT INTO `t_dict_data` VALUES (1,1,'男','0','sys_user_sex','','','0',0,1473141102158827520,'2022-03-18 06:20:13',NULL,'2022-04-01 06:42:17','性别男'),(2,2,'女','1','sys_user_sex','','','1',0,1473141102158827520,'2022-03-18 06:20:13',NULL,'2022-04-01 06:42:17','性别女'),(3,3,'未知','2','sys_user_sex','','','1',0,1473141102158827520,'2022-03-18 06:20:13',NULL,'2022-04-01 06:42:17','性别未知'),(4,1,'显示','0','sys_show_hide','','primary','0',0,1473141102158827520,'2022-03-18 06:20:14',NULL,'2022-04-01 06:42:17','显示菜单'),(5,2,'隐藏','1','sys_show_hide','','danger','1',0,1473141102158827520,'2022-03-18 06:20:14',NULL,'2022-04-01 06:42:17','隐藏菜单'),(6,1,'正常','0','sys_normal_disable','','primary','0',0,1473141102158827520,'2022-03-18 06:20:14',NULL,'2022-04-01 06:42:17','正常状态'),(7,2,'停用','1','sys_normal_disable','','danger','1',0,1473141102158827520,'2022-03-18 06:20:14',NULL,'2022-04-01 06:42:17','停用状态'),(8,1,'正常','0','sys_job_status','','primary','0',0,1473141102158827520,'2022-03-18 06:20:14',NULL,'2022-04-01 06:42:17','正常状态'),(9,2,'暂停','1','sys_job_status','','danger','1',0,1473141102158827520,'2022-03-18 06:20:14',NULL,'2022-04-01 06:42:17','停用状态'),(10,1,'默认','DEFAULT','sys_job_group','','','0',0,1473141102158827520,'2022-03-18 06:20:14',NULL,'2022-04-01 06:42:17','默认分组'),(11,2,'系统','SYSTEM','sys_job_group','','','1',0,1473141102158827520,'2022-03-18 06:20:14',NULL,'2022-04-01 06:42:17','系统分组'),(12,1,'是','Y','sys_yes_no','','primary','0',0,1473141102158827520,'2022-03-18 06:20:14',NULL,'2022-04-01 06:42:17','系统默认是'),(13,2,'否','N','sys_yes_no','','danger','1',0,1473141102158827520,'2022-03-18 06:20:15',NULL,'2022-04-01 06:42:17','系统默认否'),(14,1,'通知','1','sys_notice_type','','warning','0',0,1473141102158827520,'2022-03-18 06:20:15',NULL,'2022-04-01 06:42:17','通知'),(15,2,'公告','2','sys_notice_type','','success','1',0,1473141102158827520,'2022-03-18 06:20:15',NULL,'2022-04-01 06:42:17','公告'),(16,1,'正常','0','sys_notice_status','','primary','0',0,1473141102158827520,'2022-03-18 06:20:15',NULL,'2022-04-01 06:42:17','正常状态'),(17,2,'关闭','1','sys_notice_status','','danger','1',0,1473141102158827520,'2022-03-18 06:20:15',NULL,'2022-04-01 06:42:17','关闭状态'),(18,1,'新增','1','sys_oper_type','','info','1',0,1473141102158827520,'2022-03-18 06:20:15',NULL,'2022-04-01 06:42:17','新增操作'),(19,2,'修改','2','sys_oper_type','','info','1',0,1473141102158827520,'2022-03-18 06:20:15',NULL,'2022-04-01 06:42:17','修改操作'),(20,3,'删除','3','sys_oper_type','','danger','1',0,1473141102158827520,'2022-03-18 06:20:15',NULL,'2022-04-01 06:42:17','删除操作'),(21,4,'授权','4','sys_oper_type','','primary','1',0,1473141102158827520,'2022-03-18 06:20:15',NULL,'2022-04-01 06:42:17','授权操作'),(22,5,'导出','5','sys_oper_type','','warning','1',0,1473141102158827520,'2022-03-18 06:20:15',NULL,'2022-04-01 06:42:17','导出操作'),(23,6,'导入','6','sys_oper_type','','warning','1',0,1473141102158827520,'2022-03-18 06:20:16',NULL,'2022-04-01 06:42:17','导入操作'),(24,7,'强退','7','sys_oper_type','','danger','1',0,1473141102158827520,'2022-03-18 06:20:16',NULL,'2022-04-01 06:42:17','强退操作'),(25,8,'生成代码','8','sys_oper_type','','warning','1',0,1473141102158827520,'2022-03-18 06:20:16',NULL,'2022-04-01 06:42:17','生成操作'),(26,9,'清空数据','9','sys_oper_type','','danger','1',0,1473141102158827520,'2022-03-18 06:20:16',NULL,'2022-04-01 06:42:17','清空操作'),(27,1,'成功','0','sys_common_status','','primary','1',0,1473141102158827520,'2022-03-18 06:20:16',NULL,'2022-04-01 06:42:17','正常状态'),(28,2,'失败','1','sys_common_status','','danger','1',0,1473141102158827520,'2022-03-18 06:20:16',NULL,'2022-04-01 06:42:17','停用状态');
/*!40000 ALTER TABLE `t_dict_data` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_dict_type`
--

DROP TABLE IF EXISTS `t_dict_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_dict_type` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '字典主键',
  `name` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '字典名称',
  `type` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '字典类型',
  `status` tinyint(1) DEFAULT '0',
  `create_by` bigint(20) DEFAULT NULL COMMENT '创建者',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `update_by` bigint(20) DEFAULT NULL COMMENT '更新者',
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `remark` varchar(500) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`),
  UNIQUE KEY `dict_type` (`type`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='字典类型表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_dict_type`
--

LOCK TABLES `t_dict_type` WRITE;
/*!40000 ALTER TABLE `t_dict_type` DISABLE KEYS */;
INSERT INTO `t_dict_type` VALUES (1,'用户性别','sys_user_sex',0,1473141102158827520,'2022-03-18 06:20:12',NULL,'2022-04-01 06:45:58','用户性别列表'),(2,'菜单状态','sys_show_hide',0,1473141102158827520,'2022-03-18 06:20:12',NULL,'2022-04-01 06:45:58','菜单状态列表'),(3,'系统开关','sys_normal_disable',0,1473141102158827520,'2022-03-18 06:20:12',NULL,'2022-04-01 06:45:58','系统开关列表'),(4,'任务状态','sys_job_status',0,1473141102158827520,'2022-03-18 06:20:12',NULL,'2022-04-01 06:45:58','任务状态列表'),(5,'任务分组','sys_job_group',0,1473141102158827520,'2022-03-18 06:20:12',NULL,'2022-04-01 06:45:58','任务分组列表'),(6,'系统是否','sys_yes_no',0,1473141102158827520,'2022-03-18 06:20:13',NULL,'2022-04-01 06:45:58','系统是否列表'),(7,'通知类型','sys_notice_type',0,1473141102158827520,'2022-03-18 06:20:13',NULL,'2022-04-01 06:45:58','通知类型列表'),(8,'通知状态','sys_notice_status',0,1473141102158827520,'2022-03-18 06:20:13',NULL,'2022-04-01 06:45:58','通知状态列表'),(9,'操作类型','sys_oper_type',0,1473141102158827520,'2022-03-18 06:20:13',NULL,'2022-04-01 06:45:58','操作类型列表'),(10,'系统状态','sys_common_status',0,1473141102158827520,'2022-03-18 06:20:13',NULL,'2022-04-01 06:45:58','登录状态列表');
/*!40000 ALTER TABLE `t_dict_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_sys_dept`
--

DROP TABLE IF EXISTS `t_sys_dept`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_sys_dept` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '部门id',
  `parent_id` bigint(20) DEFAULT '0' COMMENT '父部门id',
  `ancestors` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '祖级列表',
  `name` varchar(30) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '部门名称',
  `order_num` int(4) DEFAULT '0' COMMENT '显示顺序',
  `leader` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '负责人',
  `phone` varchar(11) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '联系电话',
  `email` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '邮箱',
  `status` char(1) COLLATE utf8mb4_unicode_ci DEFAULT '0' COMMENT '部门状态（0正常 1停用）',
  `del_flag` char(1) COLLATE utf8mb4_unicode_ci DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  `create_by_id` bigint(20) DEFAULT NULL COMMENT '创建者',
  `create_by` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '更新者',
  `update_by_id` bigint(20) DEFAULT NULL COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=200 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='部门表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_sys_dept`
--

LOCK TABLES `t_sys_dept` WRITE;
/*!40000 ALTER TABLE `t_sys_dept` DISABLE KEYS */;
INSERT INTO `t_sys_dept` VALUES (100,0,'0','若依科技',0,'若依','15888888888','ry@qq.com','0','0',NULL,'admin','2022-05-13 10:19:08','',NULL,NULL),(101,100,'0,100','深圳总公司',1,'若依','15888888888','ry@qq.com','0','0',NULL,'admin','2022-05-13 10:19:08','',NULL,NULL),(102,100,'0,100','长沙分公司',2,'若依','15888888888','ry@qq.com','0','0',NULL,'admin','2022-05-13 10:19:08','',NULL,NULL),(103,101,'0,100,101','研发部门',1,'若依','15888888888','ry@qq.com','0','0',NULL,'admin','2022-05-13 10:19:08','',NULL,NULL),(104,101,'0,100,101','市场部门',2,'若依','15888888888','ry@qq.com','0','0',NULL,'admin','2022-05-13 10:19:08','',NULL,NULL),(105,101,'0,100,101','测试部门',3,'若依','15888888888','ry@qq.com','0','0',NULL,'admin','2022-05-13 10:19:08','',NULL,NULL),(106,101,'0,100,101','财务部门',4,'若依','15888888888','ry@qq.com','0','0',NULL,'admin','2022-05-13 10:19:08','',NULL,NULL),(107,101,'0,100,101','运维部门',5,'若依','15888888888','ry@qq.com','0','0',NULL,'admin','2022-05-13 10:19:08','',NULL,NULL),(108,102,'0,100,102','市场部门',1,'若依','15888888888','ry@qq.com','0','0',NULL,'admin','2022-05-13 10:19:09','',NULL,NULL),(109,102,'0,100,102','财务部门',2,'若依','15888888888','ry@qq.com','0','0',NULL,'admin','2022-05-13 10:19:09','',NULL,NULL);
/*!40000 ALTER TABLE `t_sys_dept` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_sys_menu`
--

DROP TABLE IF EXISTS `t_sys_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_sys_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` tinytext COLLATE utf8mb4_unicode_ci,
  `parent_id` bigint(20) DEFAULT NULL,
  `order_num` int(11) DEFAULT NULL,
  `path` tinytext COLLATE utf8mb4_unicode_ci,
  `component` tinytext COLLATE utf8mb4_unicode_ci,
  `frame` int(11) DEFAULT NULL,
  `cache` int(11) DEFAULT NULL,
  `type` char(1) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `visible` char(1) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `status` char(1) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `perms` tinytext COLLATE utf8mb4_unicode_ci,
  `icon` tinytext COLLATE utf8mb4_unicode_ci,
  `create_by` bigint(20) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT NULL,
  `update_by` bigint(20) DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT NULL,
  `remark` varchar(500) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `query` tinytext COLLATE utf8mb4_unicode_ci COMMENT '路由参数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1079 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='系统菜单权限表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_sys_menu`
--

LOCK TABLES `t_sys_menu` WRITE;
/*!40000 ALTER TABLE `t_sys_menu` DISABLE KEYS */;
INSERT INTO `t_sys_menu` VALUES (1,'系统管理',0,1,'system',NULL,1,0,'M','0','0','','system',1473141102158827520,'2022-04-22 06:14:13',NULL,NULL,'系统管理目录',NULL),(2,'系统监控',0,2,'monitor',NULL,1,0,'M','0','0','','monitor',1473141102158827520,'2022-04-22 06:14:22',NULL,NULL,'系统监控目录',NULL),(3,'系统工具',0,3,'tool',NULL,1,0,'M','0','0','','tool',1473141102158827520,'2022-04-22 06:14:22',NULL,NULL,'系统工具目录',NULL),(100,'用户管理',1,1,'operator','system/operator/index',1,0,'C','0','0','system:operator:list','operator',1473141102158827520,'2022-04-22 06:14:22',NULL,NULL,'用户管理菜单',NULL),(101,'角色管理',1,2,'role','system/role/index',1,0,'C','0','0','system:role:list','peoples',1473141102158827520,'2022-04-22 06:14:22',NULL,NULL,'角色管理菜单',NULL),(102,'菜单管理',1,3,'menu','system/menu/index',1,0,'C','0','0','system:menu:list','tab',1473141102158827520,'2022-04-22 06:14:22',NULL,NULL,'菜单管理菜单',NULL),(103,'部门管理',1,4,'dept','system/dept/index',1,0,'C','0','0','system:dept:list','tree',1473141102158827520,'2022-04-22 06:14:22',NULL,NULL,'部门管理菜单',NULL),(104,'岗位管理',1,5,'post','system/post/index',1,0,'C','0','0','system:post:list','post',1473141102158827520,'2022-04-22 06:14:23',NULL,NULL,'岗位管理菜单',NULL),(105,'字典管理',1,6,'dict','system/dict/index',1,0,'C','0','0','system:dict:list','dict',1473141102158827520,'2022-04-22 06:14:23',NULL,NULL,'字典管理菜单',NULL),(106,'参数设置',1,7,'config','system/config/index',1,0,'C','0','0','system:config:list','edit',1473141102158827520,'2022-04-22 06:14:23',NULL,NULL,'参数设置菜单',NULL),(107,'通知公告',1,8,'notice','system/notice/index',1,0,'C','0','0','system:notice:list','message',1473141102158827520,'2022-04-22 06:14:23',NULL,NULL,'通知公告菜单',NULL),(108,'日志管理',1,9,'log','',1,0,'M','0','0','','log',1473141102158827520,'2022-04-22 06:14:23',NULL,NULL,'日志管理菜单',NULL),(109,'在线用户',2,1,'online','monitor/online/index',1,0,'C','0','0','monitor:online:list','online',1473141102158827520,'2022-04-22 06:14:23',NULL,NULL,'在线用户菜单',NULL),(110,'定时任务',2,2,'job','monitor/job/index',1,0,'C','0','0','monitor:job:list','job',1473141102158827520,'2022-04-22 06:14:23',NULL,NULL,'定时任务菜单',NULL),(111,'数据监控',2,3,'druid','monitor/druid/index',1,0,'C','0','0','monitor:druid:list','druid',1473141102158827520,'2022-04-22 06:14:23',NULL,NULL,'数据监控菜单',NULL),(112,'服务监控',2,4,'server','monitor/server/index',1,0,'C','0','0','monitor:server:list','server',1473141102158827520,'2022-04-22 06:14:23',NULL,NULL,'服务监控菜单',NULL),(113,'缓存监控',2,5,'cache','monitor/cache/index',1,0,'C','0','0','monitor:cache:list','redis',1473141102158827520,'2022-04-22 06:14:24',NULL,NULL,'缓存监控菜单',NULL),(114,'表单构建',3,1,'build','tool/build/index',1,0,'C','0','0','tool:build:list','build',1473141102158827520,'2022-04-22 06:14:24',NULL,NULL,'表单构建菜单',NULL),(115,'代码生成',3,2,'gen','tool/gen/index',1,0,'C','0','0','tool:gen:list','code',1473141102158827520,'2022-04-22 06:14:24',NULL,NULL,'代码生成菜单',NULL),(116,'系统接口',3,3,'swagger','tool/swagger/index',1,0,'C','0','0','tool:swagger:list','swagger',1473141102158827520,'2022-04-22 06:14:24',NULL,NULL,'系统接口菜单',NULL),(500,'操作日志',108,1,'operlog','monitor/operlog/index',1,0,'C','0','0','monitor:operlog:list','form',1473141102158827520,'2022-04-22 06:14:24',NULL,NULL,'操作日志菜单',NULL),(501,'登录日志',108,2,'logininfor','monitor/logininfor/index',1,0,'C','0','0','monitor:logininfor:list','logininfor',1473141102158827520,'2022-04-22 06:14:24',NULL,NULL,'登录日志菜单',NULL),(1001,'用户查询',100,1,'','',1,0,'F','0','0','system:operator:query','#',1473141102158827520,'2022-04-22 06:14:24',NULL,NULL,'',NULL),(1002,'用户新增',100,2,'','',1,0,'F','0','0','system:operator:add','#',1473141102158827520,'2022-04-22 06:14:24',NULL,NULL,'',NULL),(1003,'用户修改',100,3,'','',1,0,'F','0','0','system:operator:edit','#',1473141102158827520,'2022-04-22 06:14:24',NULL,NULL,'',NULL),(1004,'用户删除',100,4,'','',1,0,'F','0','0','system:operator:remove','#',1473141102158827520,'2022-04-22 06:14:24',NULL,NULL,'',NULL),(1005,'用户导出',100,5,'','',1,0,'F','0','0','system:operator:export','#',1473141102158827520,'2022-04-22 06:14:25',NULL,NULL,'',NULL),(1006,'用户导入',100,6,'','',1,0,'F','0','0','system:operator:import','#',1473141102158827520,'2022-04-22 06:14:25',NULL,NULL,'',NULL),(1007,'重置密码',100,7,'','',1,0,'F','0','0','system:operator:resetPwd','#',1473141102158827520,'2022-04-22 06:14:25',NULL,NULL,'',NULL),(1008,'角色查询',101,1,'','',1,0,'F','0','0','system:role:query','#',1473141102158827520,'2022-04-22 06:14:25',NULL,NULL,'',NULL),(1009,'角色新增',101,2,'','',1,0,'F','0','0','system:role:add','#',1473141102158827520,'2022-04-22 06:14:25',NULL,NULL,'',NULL),(1010,'角色修改',101,3,'','',1,0,'F','0','0','system:role:edit','#',1473141102158827520,'2022-04-22 06:14:25',NULL,NULL,'',NULL),(1011,'角色删除',101,4,'','',1,0,'F','0','0','system:role:remove','#',1473141102158827520,'2022-04-22 06:14:25',NULL,NULL,'',NULL),(1012,'角色导出',101,5,'','',1,0,'F','0','0','system:role:export','#',1473141102158827520,'2022-04-22 06:14:25',NULL,NULL,'',NULL),(1013,'菜单查询',102,1,'','',1,0,'F','0','0','system:menu:query','#',1473141102158827520,'2022-04-22 06:14:26',NULL,NULL,'',NULL),(1014,'菜单新增',102,2,'','',1,0,'F','0','0','system:menu:add','#',1473141102158827520,'2022-04-22 06:14:26',NULL,NULL,'',NULL),(1015,'菜单修改',102,3,'','',1,0,'F','0','0','system:menu:edit','#',1473141102158827520,'2022-04-22 06:14:26',NULL,NULL,'',NULL),(1016,'菜单删除',102,4,'','',1,0,'F','0','0','system:menu:remove','#',1473141102158827520,'2022-04-22 06:14:26',NULL,NULL,'',NULL),(1017,'部门查询',103,1,'','',1,0,'F','0','0','system:dept:query','#',1473141102158827520,'2022-04-22 06:14:26',NULL,NULL,'',NULL),(1018,'部门新增',103,2,'','',1,0,'F','0','0','system:dept:add','#',1473141102158827520,'2022-04-22 06:14:26',NULL,NULL,'',NULL),(1019,'部门修改',103,3,'','',1,0,'F','0','0','system:dept:edit','#',1473141102158827520,'2022-04-22 06:14:26',NULL,NULL,'',NULL),(1020,'部门删除',103,4,'','',1,0,'F','0','0','system:dept:remove','#',1473141102158827520,'2022-04-22 06:14:26',NULL,NULL,'',NULL),(1021,'岗位查询',104,1,'','',1,0,'F','0','0','system:post:query','#',1473141102158827520,'2022-04-22 06:14:26',NULL,NULL,'',NULL),(1022,'岗位新增',104,2,'','',1,0,'F','0','0','system:post:add','#',1473141102158827520,'2022-04-22 06:14:27',NULL,NULL,'',NULL),(1023,'岗位修改',104,3,'','',1,0,'F','0','0','system:post:edit','#',1473141102158827520,'2022-04-22 06:14:27',NULL,NULL,'',NULL),(1024,'岗位删除',104,4,'','',1,0,'F','0','0','system:post:remove','#',1473141102158827520,'2022-04-22 06:14:27',NULL,NULL,'',NULL),(1025,'岗位导出',104,5,'','',1,0,'F','0','0','system:post:export','#',1473141102158827520,'2022-04-22 06:14:27',NULL,NULL,'',NULL),(1026,'字典查询',105,1,'#','',1,0,'F','0','0','system:dict:query','#',1473141102158827520,'2022-04-22 06:14:27',NULL,NULL,'',NULL),(1027,'字典新增',105,2,'#','',1,0,'F','0','0','system:dict:add','#',1473141102158827520,'2022-04-22 06:14:27',NULL,NULL,'',NULL),(1028,'字典修改',105,3,'#','',1,0,'F','0','0','system:dict:edit','#',1473141102158827520,'2022-04-22 06:14:27',NULL,NULL,'',NULL),(1029,'字典删除',105,4,'#','',1,0,'F','0','0','system:dict:remove','#',1473141102158827520,'2022-04-22 06:14:27',NULL,NULL,'',NULL),(1030,'字典导出',105,5,'#','',1,0,'F','0','0','system:dict:export','#',1473141102158827520,'2022-04-22 06:14:28',NULL,NULL,'',NULL),(1031,'参数查询',106,1,'#','',1,0,'F','0','0','system:config:query','#',1473141102158827520,'2022-04-22 06:14:28',NULL,NULL,'',NULL),(1032,'参数新增',106,2,'#','',1,0,'F','0','0','system:config:add','#',1473141102158827520,'2022-04-22 06:14:28',NULL,NULL,'',NULL),(1033,'参数修改',106,3,'#','',1,0,'F','0','0','system:config:edit','#',1473141102158827520,'2022-04-22 06:14:28',NULL,NULL,'',NULL),(1034,'参数删除',106,4,'#','',1,0,'F','0','0','system:config:remove','#',1473141102158827520,'2022-04-22 06:14:28',NULL,NULL,'',NULL),(1035,'参数导出',106,5,'#','',1,0,'F','0','0','system:config:export','#',1473141102158827520,'2022-04-22 06:14:28',NULL,NULL,'',NULL),(1036,'公告查询',107,1,'#','',1,0,'F','0','0','system:notice:query','#',1473141102158827520,'2022-04-22 06:14:28',NULL,NULL,'',NULL),(1037,'公告新增',107,2,'#','',1,0,'F','0','0','system:notice:add','#',1473141102158827520,'2022-04-22 06:14:28',NULL,NULL,'',NULL),(1038,'公告修改',107,3,'#','',1,0,'F','0','0','system:notice:edit','#',1473141102158827520,'2022-04-22 06:14:28',NULL,NULL,'',NULL),(1039,'公告删除',107,4,'#','',1,0,'F','0','0','system:notice:remove','#',1473141102158827520,'2022-04-22 06:14:29',NULL,NULL,'',NULL),(1040,'操作查询',500,1,'#','',1,0,'F','0','0','monitor:operlog:query','#',1473141102158827520,'2022-04-22 06:14:29',NULL,NULL,'',NULL),(1041,'操作删除',500,2,'#','',1,0,'F','0','0','monitor:operlog:remove','#',1473141102158827520,'2022-04-22 06:14:29',NULL,NULL,'',NULL),(1042,'日志导出',500,4,'#','',1,0,'F','0','0','monitor:operlog:export','#',1473141102158827520,'2022-04-22 06:14:29',NULL,NULL,'',NULL),(1043,'登录查询',501,1,'#','',1,0,'F','0','0','monitor:logininfor:query','#',1473141102158827520,'2022-04-22 06:14:29',NULL,NULL,'',NULL),(1044,'登录删除',501,2,'#','',1,0,'F','0','0','monitor:logininfor:remove','#',1473141102158827520,'2022-04-22 06:14:29',NULL,NULL,'',NULL),(1045,'日志导出',501,3,'#','',1,0,'F','0','0','monitor:logininfor:export','#',1473141102158827520,'2022-04-22 06:14:29',NULL,NULL,'',NULL),(1046,'在线查询',109,1,'#','',1,0,'F','0','0','monitor:online:query','#',1473141102158827520,'2022-04-22 06:14:29',NULL,NULL,'',NULL),(1047,'批量强退',109,2,'#','',1,0,'F','0','0','monitor:online:batchLogout','#',1473141102158827520,'2022-04-22 06:14:29',NULL,NULL,'',NULL),(1048,'单条强退',109,3,'#','',1,0,'F','0','0','monitor:online:forceLogout','#',1473141102158827520,'2022-04-22 06:14:30',NULL,NULL,'',NULL),(1049,'任务查询',110,1,'#','',1,0,'F','0','0','monitor:job:query','#',1473141102158827520,'2022-04-22 06:14:30',NULL,NULL,'',NULL),(1050,'任务新增',110,2,'#','',1,0,'F','0','0','monitor:job:add','#',1473141102158827520,'2022-04-22 06:14:30',NULL,NULL,'',NULL),(1051,'任务修改',110,3,'#','',1,0,'F','0','0','monitor:job:edit','#',1473141102158827520,'2022-04-22 06:14:30',NULL,NULL,'',NULL),(1052,'任务删除',110,4,'#','',1,0,'F','0','0','monitor:job:remove','#',1473141102158827520,'2022-04-22 06:14:30',NULL,NULL,'',NULL),(1053,'状态修改',110,5,'#','',1,0,'F','0','0','monitor:job:changeStatus','#',1473141102158827520,'2022-04-22 06:14:30',NULL,NULL,'',NULL),(1054,'任务导出',110,7,'#','',1,0,'F','0','0','monitor:job:export','#',1473141102158827520,'2022-04-22 06:14:30',NULL,NULL,'',NULL),(1055,'生成查询',115,1,'#','',1,0,'F','0','0','tool:gen:query','#',1473141102158827520,'2022-04-22 06:14:30',NULL,NULL,'',NULL),(1056,'生成修改',115,2,'#','',1,0,'F','0','0','tool:gen:edit','#',1473141102158827520,'2022-04-22 06:14:31',NULL,NULL,'',NULL),(1057,'生成删除',115,3,'#','',1,0,'F','0','0','tool:gen:remove','#',1473141102158827520,'2022-04-22 06:14:31',NULL,NULL,'',NULL),(1058,'导入代码',115,2,'#','',1,0,'F','0','0','tool:gen:import','#',1473141102158827520,'2022-04-22 06:14:31',NULL,NULL,'',NULL),(1059,'预览代码',115,4,'#','',1,0,'F','0','0','tool:gen:preview','#',1473141102158827520,'2022-04-22 06:14:31',NULL,NULL,'',NULL),(1060,'生成代码',115,5,'#','',1,0,'F','0','0','tool:gen:code','#',1473141102158827520,'2022-04-22 06:14:31',NULL,NULL,'',NULL),(1074,'商户系统配置',0,0,'merchants',NULL,1,0,'M','0','0',NULL,'merchants',1473141102158827520,'2022-05-10 02:38:37',1473141102158827520,'2022-05-10 03:20:26',NULL,NULL),(1075,'菜单管理',1074,1,'menu','merchants/menu/index',1,0,'C','0','0','member:menu:list','tab',1473141102158827520,'2022-05-10 03:07:51',1473141102158827520,'2022-05-10 03:50:51',NULL,NULL),(1076,'角色管理',1074,2,'merchents','merchants/role/index',1,0,'C','0','0','member:role:list','peoples',1473141102158827520,'2022-05-11 06:25:01',1473141102158827520,'2022-05-11 06:25:39',NULL,NULL),(1077,'NACOS',3,4,'http://119.29.77.107:8848/nacos/index.html',NULL,0,0,'C','0','0','nacos','services',1473141102158827520,'2022-05-11 09:16:21',NULL,NULL,NULL,NULL),(1078,'商品管理',0,4,'product',NULL,1,0,'M','0','0',NULL,'goods',1473141102158827520,'2022-05-13 01:02:42',NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `t_sys_menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_sys_oper_log`
--

DROP TABLE IF EXISTS `t_sys_oper_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_sys_oper_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '日志主键',
  `title` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '模块标题',
  `business_type` int(2) DEFAULT '0' COMMENT '业务类型（0其它 1新增 2修改 3删除）',
  `method` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '方法名称',
  `request_method` varchar(10) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '请求方式',
  `operator_type` int(1) DEFAULT '0' COMMENT '操作类别（0其它 1后台用户 2手机端用户）',
  `oper_name` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '操作人员',
  `dept_name` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '部门名称',
  `oper_url` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '请求URL',
  `oper_ip` varchar(128) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '主机地址',
  `oper_location` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '操作地点',
  `oper_param` varchar(2000) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '请求参数',
  `json_result` varchar(2000) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '返回参数',
  `status` int(1) DEFAULT '0' COMMENT '操作状态（0正常 1异常）',
  `error_msg` varchar(2000) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '错误消息',
  `oper_time` datetime DEFAULT NULL COMMENT '操作时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='操作日志记录';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_sys_oper_log`
--

LOCK TABLES `t_sys_oper_log` WRITE;
/*!40000 ALTER TABLE `t_sys_oper_log` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_sys_oper_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_sys_role`
--

DROP TABLE IF EXISTS `t_sys_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_sys_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `code` tinytext COLLATE utf8mb4_unicode_ci,
  `name` tinytext COLLATE utf8mb4_unicode_ci,
  `status` int(11) DEFAULT NULL,
  `create_by` tinytext COLLATE utf8mb4_unicode_ci,
  `update_by` tinytext COLLATE utf8mb4_unicode_ci,
  `create_time` timestamp NULL DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT NULL,
  `sort` int(11) DEFAULT NULL COMMENT '显示顺序',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='系统角色表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_sys_role`
--

LOCK TABLES `t_sys_role` WRITE;
/*!40000 ALTER TABLE `t_sys_role` DISABLE KEYS */;
INSERT INTO `t_sys_role` VALUES (1,'ADMIN','超级管理员',0,'','','2022-01-27 08:56:59','2022-01-27 08:57:25',0),(17,'DEVELOPER','开发',0,'1473141102158827520','1473141102158827520','2022-05-05 02:03:37','2022-05-09 06:50:20',1),(28,'OPERATION','商户运营人员',0,'1473141102158827520',NULL,'2022-05-13 01:09:08',NULL,3);
/*!40000 ALTER TABLE `t_sys_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_sys_role_menu`
--

DROP TABLE IF EXISTS `t_sys_role_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_sys_role_menu` (
  `role_id` bigint(20) DEFAULT NULL,
  `menu_id` bigint(20) DEFAULT NULL,
  UNIQUE KEY `t_sys_role_menu_pk` (`role_id`,`menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='系统角色菜单表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_sys_role_menu`
--

LOCK TABLES `t_sys_role_menu` WRITE;
/*!40000 ALTER TABLE `t_sys_role_menu` DISABLE KEYS */;
INSERT INTO `t_sys_role_menu` VALUES (1,1),(1,2),(1,3),(1,4),(1,100),(1,101),(1,102),(1,103),(1,104),(1,105),(1,106),(1,107),(1,108),(1,109),(1,110),(1,111),(1,112),(1,113),(1,114),(1,115),(1,116),(1,500),(1,501),(1,1000),(1,1001),(1,1002),(1,1003),(1,1004),(1,1005),(1,1006),(1,1007),(1,1008),(1,1009),(1,1010),(1,1011),(1,1012),(1,1013),(1,1014),(1,1015),(1,1016),(1,1017),(1,1018),(1,1019),(1,1020),(1,1021),(1,1022),(1,1023),(1,1024),(1,1025),(1,1026),(1,1027),(1,1028),(1,1029),(1,1030),(1,1031),(1,1032),(1,1033),(1,1034),(1,1035),(1,1036),(1,1037),(1,1038),(1,1039),(1,1040),(1,1041),(1,1042),(1,1043),(1,1044),(1,1045),(1,1046),(1,1047),(1,1048),(1,1049),(1,1050),(1,1051),(1,1052),(1,1053),(1,1054),(1,1055),(1,1056),(1,1057),(1,1058),(1,1059),(1,1060),(1,1074),(1,1075),(1,1076),(1,1077),(1,1078),(17,1),(17,100),(17,101),(17,102),(17,103),(17,104),(17,105),(17,106),(17,107),(17,108),(17,500),(17,501),(17,1001),(17,1002),(17,1003),(17,1004),(17,1005),(17,1006),(17,1007),(17,1008),(17,1009),(17,1010),(17,1011),(17,1012),(17,1013),(17,1014),(17,1015),(17,1016),(17,1017),(17,1018),(17,1019),(17,1020),(17,1021),(17,1022),(17,1023),(17,1024),(17,1025),(17,1026),(17,1027),(17,1028),(17,1029),(17,1030),(17,1031),(17,1032),(17,1033),(17,1034),(17,1035),(17,1036),(17,1037),(17,1038),(17,1039),(17,1040),(17,1041),(17,1042),(17,1043),(17,1044),(17,1045),(27,1),(27,100),(27,101),(27,102),(27,103),(27,104),(27,105),(27,106),(27,107),(27,108),(27,500),(27,501),(27,1001),(27,1002),(27,1003),(27,1004),(27,1005),(27,1006),(27,1007),(27,1008),(27,1009),(27,1010),(27,1011),(27,1012),(27,1013),(27,1014),(27,1015),(27,1016),(27,1017),(27,1018),(27,1019),(27,1020),(27,1021),(27,1022),(27,1023),(27,1024),(27,1025),(27,1026),(27,1027),(27,1028),(27,1029),(27,1030),(27,1031),(27,1032),(27,1033),(27,1034),(27,1035),(27,1036),(27,1037),(27,1038),(27,1039),(27,1040),(27,1041),(27,1042),(27,1043),(27,1044),(27,1045),(28,1078);
/*!40000 ALTER TABLE `t_sys_role_menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_sys_user`
--

DROP TABLE IF EXISTS `t_sys_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_sys_user` (
  `id` bigint(20) NOT NULL,
  `username` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '用户名-用于登录',
  `nick_name` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '昵称',
  `password` varchar(128) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '密码',
  `salt` varchar(36) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '加密sale',
  `encrypt_key` varchar(36) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '加密串',
  `phone` varchar(11) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '手机号',
  `status` tinyint(1) DEFAULT '0' COMMENT '账号状态 0-正常 1-删除 2-锁定',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `last_sign_time` timestamp NULL DEFAULT NULL COMMENT '上吃登录时间',
  `update_by` bigint(20) DEFAULT NULL COMMENT '修改人编号',
  `create_by` bigint(20) DEFAULT NULL COMMENT '创建人编号',
  `avatar` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '头像',
  `update_by_name` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '更新人名称',
  `create_by_name` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '创建人名称',
  `dept_id` bigint(20) DEFAULT NULL COMMENT '部门ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='系统用户表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_sys_user`
--

LOCK TABLES `t_sys_user` WRITE;
/*!40000 ALTER TABLE `t_sys_user` DISABLE KEYS */;
INSERT INTO `t_sys_user` VALUES (1473141102158827520,'admin001','my_wDvYcRo823','XaZfatnjIDAy5/nAbwxLFVOA8Yo=','56dcf99a-ca90-4ff2-b826-4e90ee5a6bd1','23d2a49b-8b49-41c7-894a-51e6c7448dcd',NULL,0,'2022-03-09 06:02:44','2022-03-09 06:04:23',NULL,0,0,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `t_sys_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_sys_user_role`
--

DROP TABLE IF EXISTS `t_sys_user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_sys_user_role` (
  `user_id` bigint(20) DEFAULT NULL,
  `role_id` bigint(20) DEFAULT NULL,
  UNIQUE KEY `t_sys_user_role_pk` (`user_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='系统用户角色表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_sys_user_role`
--

LOCK TABLES `t_sys_user_role` WRITE;
/*!40000 ALTER TABLE `t_sys_user_role` DISABLE KEYS */;
INSERT INTO `t_sys_user_role` VALUES (1473141102158827520,1);
/*!40000 ALTER TABLE `t_sys_user_role` ENABLE KEYS */;
UNLOCK TABLES;

/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-05-13 16:14:34
