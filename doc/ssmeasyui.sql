/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50624
Source Host           : localhost:3306
Source Database       : ssmeasyui

Target Server Type    : MYSQL
Target Server Version : 50624
File Encoding         : 65001

Date: 2017-05-12 17:39:05
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for tb_dict
-- ----------------------------
DROP TABLE IF EXISTS `tb_dict`;
CREATE TABLE `tb_dict` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '字典主键 无逻辑意义',
  `parentId` bigint(20) unsigned zerofill DEFAULT NULL COMMENT '父id',
  `code` varchar(150) DEFAULT NULL COMMENT '字典代码',
  `name` varchar(150) DEFAULT NULL COMMENT '字典名称',
  `sequence` int(11) DEFAULT NULL COMMENT '排序',
  `status` int(2) DEFAULT '0' COMMENT '状态：0-启用；1-停用',
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_dict
-- ----------------------------

-- ----------------------------
-- Table structure for tb_menu
-- ----------------------------
DROP TABLE IF EXISTS `tb_menu`;
CREATE TABLE `tb_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `parentId` bigint(20) DEFAULT NULL,
  `name` varchar(150) DEFAULT NULL,
  `url` varchar(150) DEFAULT NULL,
  `icon` varchar(50) DEFAULT NULL,
  `sequence` int(11) DEFAULT NULL COMMENT '序列号',
  `status` int(2) DEFAULT '0' COMMENT '状态：0-启用；1-停用',
  `createTime` datetime DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_menu
-- ----------------------------
INSERT INTO `tb_menu` VALUES ('1', '0', '系统管理', '#', 'icon-sys', '9', '0', null, null);
INSERT INTO `tb_menu` VALUES ('2', '1', '角色权限', '/role/list.do', 'icon-nav', '1', '0', null, null);
INSERT INTO `tb_menu` VALUES ('3', '1', '数据词典', '/dict/list.do', 'icon-nav', '1', '0', null, null);
INSERT INTO `tb_menu` VALUES ('4', '1', '菜单管理', '/menu/list.do', 'icon-nav', '1', '0', null, null);
INSERT INTO `tb_menu` VALUES ('5', '1', '用户管理', '/user/list.do', 'icon-nav', '1', '0', null, null);

-- ----------------------------
-- Table structure for tb_role
-- ----------------------------
DROP TABLE IF EXISTS `tb_role`;
CREATE TABLE `tb_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL COMMENT '角色名',
  `menuIds` text COMMENT '权限ID',
  `status` int(2) DEFAULT '0' COMMENT '状态：0-启用；1-停用',
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  `updateTime` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='后台角色权限表';

-- ----------------------------
-- Records of tb_role
-- ----------------------------
INSERT INTO `tb_role` VALUES ('1', '管理', '2,4,5', '0', null, '2016-12-27 10:56:36');

-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `loginName` varchar(150) DEFAULT NULL COMMENT '帐号',
  `password` varchar(150) DEFAULT NULL COMMENT '密码',
  `realName` varchar(150) DEFAULT NULL COMMENT '正式名称',
  `roleIds` text COMMENT '角色ID',
  `status` int(2) DEFAULT '0' COMMENT '状态：0-启用；1-停用',
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  `updateTime` datetime DEFAULT NULL COMMENT '用户密码修改时间',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='后台用户表';

-- ----------------------------
-- Records of tb_user
-- ----------------------------
INSERT INTO `tb_user` VALUES ('1', 'admin', '6912b2d167c8e11a2fdd0105902f6904', '管理者', '1,2', '0', null, null);
INSERT INTO `tb_user` VALUES ('2', 'test', '6912b2d167c8e11a2fdd0105902f6904', '测试帐号', '2', '0', null, null);
