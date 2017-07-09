/*
MySQL Data Transfer
Source Host: localhost
Source Database: hr
Target Host: localhost
Target Database: hr
Date: 2017/6/28 16:33:00
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for t_contract_info
-- ----------------------------
CREATE TABLE `t_contract_info` (
  `contract_id` int(12) NOT NULL,
  `staff_id` int(12) DEFAULT NULL,
  `contract_time_start` date DEFAULT NULL,
  `contract_time` varchar(12) COLLATE utf8_bin DEFAULT NULL,
  `contract_time_end` date DEFAULT NULL,
  `contract_type` varchar(40) COLLATE utf8_bin DEFAULT NULL,
  `beiyong_5_1` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `beiyong_5_2` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`contract_id`),
  KEY `fk_staff_con` (`staff_id`),
  CONSTRAINT `fk_staff_con` FOREIGN KEY (`staff_id`) REFERENCES `t_staff_info` (`staff_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for t_dept_info
-- ----------------------------
CREATE TABLE `t_dept_info` (
  `dept_id` varchar(12) COLLATE utf8_bin NOT NULL,
  `dept_name` varchar(16) COLLATE utf8_bin DEFAULT NULL,
  `dept_memo` varchar(30) COLLATE utf8_bin DEFAULT NULL,
  `beiyong_2_1` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `beiyong_2_2` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`dept_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for t_record_info
-- ----------------------------
CREATE TABLE `t_record_info` (
  `record_id` int(12) NOT NULL AUTO_INCREMENT,
  `staff_id` int(12) DEFAULT NULL,
  `record_name` varchar(30) COLLATE utf8_bin DEFAULT NULL,
  `record_paper` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `beiyong_4_1` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `beiyong_4_2` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`record_id`),
  KEY `fk_staff_id` (`staff_id`),
  CONSTRAINT `fk_staff_id` FOREIGN KEY (`staff_id`) REFERENCES `t_staff_info` (`staff_id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for t_staff_info
-- ----------------------------
CREATE TABLE `t_staff_info` (
  `staff_id` int(11) NOT NULL AUTO_INCREMENT,
  `dept_id` varchar(12) COLLATE utf8_bin DEFAULT NULL,
  `staff_password` varchar(16) COLLATE utf8_bin DEFAULT NULL,
  `staff_name` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `staff_gender` varchar(1) COLLATE utf8_bin DEFAULT NULL,
  `staff_edu` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `in_time` date DEFAULT NULL,
  `isdel` varchar(1) COLLATE utf8_bin DEFAULT NULL,
  `beiyong_1_1` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `beiyong_1_2` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`staff_id`),
  KEY `fk_dept_info` (`dept_id`),
  CONSTRAINT `fk_dept_info` FOREIGN KEY (`dept_id`) REFERENCES `t_dept_info` (`dept_id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records 
-- ----------------------------
INSERT INTO `t_contract_info` VALUES ('10000101', '1', '2017-06-13', '1年', '2018-06-13', '劳动合同', null, null);
INSERT INTO `t_contract_info` VALUES ('10000302', '2', '2017-06-06', '3年', '2020-06-06', '劳动合同', null, null);
INSERT INTO `t_contract_info` VALUES ('10000303', '3', '2017-03-05', '3年', '2018-06-07', '委托合同', null, null);
INSERT INTO `t_contract_info` VALUES ('10000305', '5', '2017-03-05', '3年', '2018-06-07', '委托合同', null, null);
INSERT INTO `t_contract_info` VALUES ('10000317', '17', '2017-03-05', '3年', '2018-06-07', '委托合同', null, null);
INSERT INTO `t_contract_info` VALUES ('10000318', '18', '2017-03-05', '3年', '2018-06-07', '委托合同', null, null);
INSERT INTO `t_contract_info` VALUES ('10000404', '1', '2017-03-05', '4年', '2018-06-07', '委托合同', null, null);
INSERT INTO `t_dept_info` VALUES ('06000', '技术部', '技术部员工', null, null);
INSERT INTO `t_dept_info` VALUES ('06081', '财务部', '财务部经理', null, null);
INSERT INTO `t_dept_info` VALUES ('06082', '财务部', '财务部员工', null, null);
INSERT INTO `t_dept_info` VALUES ('06091', '技术部', '技术部经理', null, null);
INSERT INTO `t_dept_info` VALUES ('06092', '公关部', '公关1号', null, null);
INSERT INTO `t_dept_info` VALUES ('06097', '公关部', '公关2号', null, null);
INSERT INTO `t_dept_info` VALUES ('06100', '程序员鼓励部', '程序员鼓励师1号', null, null);
INSERT INTO `t_record_info` VALUES ('3', '3', '王瑜的档案', '由公关2号到公关1号', null, null);
INSERT INTO `t_record_info` VALUES ('4', '4', '唐小雨的档案', '由公关1号到公关2号', null, null);
INSERT INTO `t_record_info` VALUES ('15', null, null, null, null, null);
INSERT INTO `t_staff_info` VALUES ('1', '06000', '123456', '唐甜', '男', '本科生', '2016-10-28', '1', null, null);
INSERT INTO `t_staff_info` VALUES ('2', '06097', '123456', '杨永航', '男', '博士生', '2016-10-28', '0', null, null);
INSERT INTO `t_staff_info` VALUES ('3', '06091', '123456', '王瑜', '男', '博士生', '2014-01-08', '0', null, null);
INSERT INTO `t_staff_info` VALUES ('4', '06091', '123456', '唐小雨', '男', '博士生', '2016-10-28', '0', null, null);
INSERT INTO `t_staff_info` VALUES ('5', null, null, null, null, null, null, null, null, null);
INSERT INTO `t_staff_info` VALUES ('17', '06082', '123456', '唐欣', '女', '博士生', '2017-06-30', '0', null, null);
INSERT INTO `t_staff_info` VALUES ('18', '06092', '123456', '唐甜', '女', null, '2016-10-28', '0', null, null);
INSERT INTO `t_staff_info` VALUES ('19', '06100', '123456', 'ddd', '男', '博士生', '2017-06-20', '0', null, null);
INSERT INTO `t_staff_info` VALUES ('20', '06000', '123456', '廖芷薇', '女', '硕士生', '2017-06-26', '0', null, null);
INSERT INTO `t_staff_info` VALUES ('21', '06081', '123456', '蒋志碧', '女', '博士生', '2017-06-26', '0', null, null);
INSERT INTO `t_staff_info` VALUES ('22', '06000', '123456', '黄睿', '男', '本科生', '2017-06-26', '0', null, null);
INSERT INTO `t_staff_info` VALUES ('23', '06000', '123456', '涂奎', '男', '研究生', '2017-06-27', '0', null, null);
INSERT INTO `t_staff_info` VALUES ('24', '06091', '123456', '黄明才', '男', '本科生', '2017-06-25', '0', null, null);
INSERT INTO `t_staff_info` VALUES ('25', '06092', '123456', '付妍', '女', '本科生', '2017-06-24', '0', null, null);
INSERT INTO `t_staff_info` VALUES ('26', '06082', '123456', '郭锐', '男', '博士生', '2017-06-27', '0', null, null);
INSERT INTO `t_staff_info` VALUES ('27', '06081', '123456', '王铖权', '男', '博士生', '2017-06-26', '0', null, null);
