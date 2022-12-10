/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 80030
 Source Host           : localhost:3306
 Source Schema         : java16myschool

 Target Server Type    : MySQL
 Target Server Version : 80030
 File Encoding         : 65001

 Date: 10/12/2022 22:41:36
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `userName` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `userPwd` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `avatar` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `mark` int NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES (1, 'zhangsan', '4297F44B13955235245B2497399D7A93', NULL, 1);
INSERT INTO `admin` VALUES (2, 'lisi', 'E10ADC3949BA59ABBE56E057F20F883E', NULL, 1);

-- ----------------------------
-- Table structure for brand
-- ----------------------------
DROP TABLE IF EXISTS `brand`;
CREATE TABLE `brand`  (
  `brandId` int NOT NULL AUTO_INCREMENT,
  `brandName` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `goodsId` int NOT NULL,
  `brandState` int NOT NULL,
  PRIMARY KEY (`brandId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of brand
-- ----------------------------
INSERT INTO `brand` VALUES (1, '手表', 1, 1);

-- ----------------------------
-- Table structure for grade
-- ----------------------------
DROP TABLE IF EXISTS `grade`;
CREATE TABLE `grade`  (
  `gradeId` int NOT NULL AUTO_INCREMENT,
  `gradeName` varchar(10) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  PRIMARY KEY (`gradeId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of grade
-- ----------------------------
INSERT INTO `grade` VALUES (1, '一年级');
INSERT INTO `grade` VALUES (2, '二年级');
INSERT INTO `grade` VALUES (3, '三年级');
INSERT INTO `grade` VALUES (4, '四年级');
INSERT INTO `grade` VALUES (5, '五年级');
INSERT INTO `grade` VALUES (6, '六年级');
INSERT INTO `grade` VALUES (7, '初七');

-- ----------------------------
-- Table structure for person
-- ----------------------------
DROP TABLE IF EXISTS `person`;
CREATE TABLE `person`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `age` int NOT NULL,
  `phone` varchar(15) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 21 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of person
-- ----------------------------
INSERT INTO `person` VALUES (2, '唐红莲', 20, '11256675671');
INSERT INTO `person` VALUES (6, '朱少强', 20, '13656711089');
INSERT INTO `person` VALUES (7, '李强', 21, '13245168621');
INSERT INTO `person` VALUES (8, '张明', 18, '12345676793');
INSERT INTO `person` VALUES (9, '伍艳', 23, '13454554356');
INSERT INTO `person` VALUES (10, '李小霞', 20, '13564798768');
INSERT INTO `person` VALUES (12, '黄小涛', 22, '13564795632');
INSERT INTO `person` VALUES (15, '张小军', 20, '13244567899');
INSERT INTO `person` VALUES (16, '杜飞', 25, '13656788888');
INSERT INTO `person` VALUES (17, '曾水红', 20, '13656789089');
INSERT INTO `person` VALUES (18, '朱少强', 20, '13656231089');
INSERT INTO `person` VALUES (19, '杜飞', 25, '136567888888');
INSERT INTO `person` VALUES (20, '杜飞', 25, '136567888888');
INSERT INTO `person` VALUES (21, '杜飞', 25, '136567888888');

-- ----------------------------
-- Table structure for result
-- ----------------------------
DROP TABLE IF EXISTS `result`;
CREATE TABLE `result`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `studentNo` int NOT NULL,
  `subjectNo` int NOT NULL,
  `examDate` date NOT NULL,
  `studentResult` int NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK_result_student`(`studentNo`) USING BTREE,
  INDEX `FK_result_subject`(`subjectNo`) USING BTREE,
  CONSTRAINT `FK_result_student` FOREIGN KEY (`studentNo`) REFERENCES `student` (`studentNo`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `FK_result_subject` FOREIGN KEY (`subjectNo`) REFERENCES `subject` (`subjectNo`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 23 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of result
-- ----------------------------
INSERT INTO `result` VALUES (1, 10004, 3, '2021-09-01', 80);
INSERT INTO `result` VALUES (2, 10004, 4, '2021-02-04', 80);
INSERT INTO `result` VALUES (3, 10005, 3, '2021-09-01', 70);
INSERT INTO `result` VALUES (4, 10005, 4, '2021-02-04', 78);
INSERT INTO `result` VALUES (5, 10000, 3, '2021-02-03', 83);
INSERT INTO `result` VALUES (6, 10001, 3, '2021-02-03', 75);
INSERT INTO `result` VALUES (7, 10002, 3, '2021-02-03', 88);
INSERT INTO `result` VALUES (8, 10004, 5, '2020-01-01', 89);
INSERT INTO `result` VALUES (9, 10003, 5, '2020-01-01', 78);
INSERT INTO `result` VALUES (10, 10005, 5, '2020-01-01', 82);
INSERT INTO `result` VALUES (14, 10001, 5, '2020-01-02', 78);
INSERT INTO `result` VALUES (15, 10018, 6, '2020-03-03', 89);
INSERT INTO `result` VALUES (17, 10021, 6, '2020-03-03', 84);
INSERT INTO `result` VALUES (18, 10000, 6, '2020-03-03', 97);
INSERT INTO `result` VALUES (19, 10004, 7, '2021-03-06', 99);
INSERT INTO `result` VALUES (20, 10005, 7, '2021-03-06', 95);
INSERT INTO `result` VALUES (21, 10001, 7, '2021-03-06', 98);
INSERT INTO `result` VALUES (22, 10005, 7, '2021-03-06', 100);
INSERT INTO `result` VALUES (23, 10050, 5, '2022-03-18', 98);

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(10) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES (1, 'admin');
INSERT INTO `role` VALUES (2, 'user');

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student`  (
  `studentNo` int NOT NULL AUTO_INCREMENT,
  `studentName` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `loginPwd` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `sex` char(2) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT '男',
  `gradeId` int NULL DEFAULT NULL,
  `phone` varchar(11) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `address` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `bornDate` date NULL DEFAULT NULL,
  `email` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `identityCard` char(18) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `mark` int NULL DEFAULT NULL,
  PRIMARY KEY (`studentNo`) USING BTREE,
  UNIQUE INDEX `NewIndex1`(`phone`) USING BTREE,
  INDEX `FK_student_grade`(`gradeId`) USING BTREE,
  INDEX `NewIndex2`(`address`) USING BTREE,
  CONSTRAINT `FK_student_grade` FOREIGN KEY (`gradeId`) REFERENCES `grade` (`gradeId`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 10124 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES (10000, '张三悦', 'e10adc3949ba59abbe56e057f20f883e', '男', 4, '12932345129', '南方阳平江市', '2000-02-26', '5123426116@qq.com', '432627200002060391', 1);
INSERT INTO `student` VALUES (10001, '赵明远', 'e10adc3949ba59abbe56e057f20f883e', '男', 2, '13454897890', '湖南省长沙市', '2000-02-05', 'zhaoyun@126.com', '432627200001020039', 1);
INSERT INTO `student` VALUES (10002, '张涛', 'e10adc3949ba59abbe56e057f20f883e', '男', 2, '13565678902', '湖南省岳阳市', '2000-05-06', 'zhangtao@126.com', '432627221001020039', 1);
INSERT INTO `student` VALUES (10003, '周张飞', 'e10adc3949ba59abbe56e057f20f883e', '男', 2, '13565678912', '长沙市', '2000-03-05', 'zhouzhangfei@126.com', '432627200000020039', 1);
INSERT INTO `student` VALUES (10004, '张小明', 'e10adc3949ba59abbe56e057f20f883e', '男', 1, '13245672356', '长沙市', '2000-03-05', 'zhangming@126.com', '432627200001027943', 1);
INSERT INTO `student` VALUES (10005, '李云彩', 'e10adc3949ba59abbe56e057f20f883e', '女', 2, '13678909876', '长沙市岳麓区', '2000-03-05', 'liyuncai@126.com', '432627200001021782', 1);
INSERT INTO `student` VALUES (10006, '王强', 'e10adc3949ba59abbe56e057f20f883e', '男', 2, '13543789065', '衡阳市衡南县', '2001-02-04', 'wangqiang@126.com', '435345346534523433', 1);
INSERT INTO `student` VALUES (10007, '张明', '123456', '女', 2, '13656788901', '湖南长沙市', '2001-01-02', 'sdf@126.com', '432627200101020028', 1);
INSERT INTO `student` VALUES (10018, '林小平', '123456', '男', 3, '13456567890', '长沙市', '2000-05-04', 'lingxiaoping@126.com', '432627200005043800', 1);
INSERT INTO `student` VALUES (10021, '刘冰', '123456', '女', 3, '13266567890', '长沙市天心区', '2000-06-12', 'liubing@126.com', '432627200006126888', 1);
INSERT INTO `student` VALUES (10031, '杨洋', 'E10ADC3949BA59ABBE56E057F20F883E', '男', 2, '13767000966', '湖南长沙市五一路', '1999-10-06', 'yaohailong@126.com', '432627199910063288', 1);
INSERT INTO `student` VALUES (10032, '林小伟', 'E10ADC3949BA59ABBE56E057F20F883E', '男', 1, '13456567891', '长沙市天心区', '2001-12-14', 'yaohailong@126.com', '432627200112149028', 1);
INSERT INTO `student` VALUES (10033, '任宪梁', 'E10ADC3949BA59ABBE56E057F20F883E', '男', 1, '13451167890', '长沙五一广场', '2001-12-22', 'yaohailong@126.com', '432627200112221700', 1);
INSERT INTO `student` VALUES (10038, '杜海燕', 'D9729FEB74992CC3482B350163A1A010', '女', 1, '13798099870', '长沙五一广场', '2001-12-10', 'yaohailong@126.com', '432627200112100200', 1);
INSERT INTO `student` VALUES (10050, '欧文波', '123456', '男', 1, '13254565673', '长沙五一广场A12', '2020-01-02', 'ouwenbo@126.com', '432627200908101236', 1);
INSERT INTO `student` VALUES (10051, '姚海龙', '123456', '男', 2, '13565458909', '湖南省邵阳市', '2020-10-03', 'yaohailong@126.com', '432627202010030212', 1);
INSERT INTO `student` VALUES (10055, '黄涛', '123456', '男', 5, '18890453452', '湖南长沙市芙蓉区', '1999-10-12', 'huangtao@126.com', '432627199910230091', 1);
INSERT INTO `student` VALUES (10056, '张元清', '13256789098', '男', 2, '18678934245', '湖南长沙市', '2000-11-29', 'zhuyuanqing@126.com', '432627200011290012', 1);
INSERT INTO `student` VALUES (10057, '曹玉琼', '123456', '女', 3, '13878776653', '湖南湘潭市', '2000-05-03', 'caoyuqiong@126.com', '432627100005030067', 1);
INSERT INTO `student` VALUES (10059, '雷雨阳', '123456', '女', 2, '13765689090', '湖南湘潭市', '2000-01-06', 'leiyuyang@126.com', '432627200001060259', 1);
INSERT INTO `student` VALUES (10060, '杜豪', '123456', '男', 3, '13678909909', '湖南益阳市', '2000-10-13', 'duhao@126.com', '432627200010130329', 1);
INSERT INTO `student` VALUES (10061, '张明珀', '123456', '男', 2, '13766789054', '湖南衡阳市', '2000-08-16', 'duanmingbo@126.com', '432627200008160564', 1);
INSERT INTO `student` VALUES (10062, '钱江', '123456', '男', 2, '13766781109', '湖南衡阳市', '2000-11-16', 'qiangjiang@126.com', '432627200011160504', 1);
INSERT INTO `student` VALUES (10065, '张小悦', '123456', '女', 2, '13766028721', '湖南怀化市', '2000-10-26', 'sunxiaoyue@126.com', '432627200010260319', 1);
INSERT INTO `student` VALUES (10067, '张博远', '123456', '男', 2, '13766001809', '湖南张家界市', '2000-09-06', 'wuboyuan@126.com', '432627200009060502', 1);
INSERT INTO `student` VALUES (10068, '郑凯', '123456', '男', 2, '13766009921', '湖南怀化市', '2000-03-26', 'zhengkai@126.com', '432627200003260112', 1);
INSERT INTO `student` VALUES (10069, '苏芸', '123456', '女', 3, '13899115602', '长沙五一广场A12', '1999-02-05', 'suyun@126.com', '432627199992951234', 1);
INSERT INTO `student` VALUES (10070, '匡小红', '123456', '女', 3, '13546789090', '湖南长沙市五一路', '1999-03-24', 'kuangxiaohong@126.com', '432626199903240015', 1);
INSERT INTO `student` VALUES (10071, '袁爱国', '123456', '男', 2, '13255678903', '湖南邵阳', '2000-03-16', 'yuanaiguo@126.com', '432627200003161026', 1);
INSERT INTO `student` VALUES (10072, '林思思', '123456', '女', 2, '18566798021', '湖南怀化市', '2000-09-27', 'lingsisi@126.com', '432627200009272318', 1);
INSERT INTO `student` VALUES (10073, '龙鹏', '123456', '男', 3, '13255341232', '湖南永州', '2000-12-21', 'longpeng@126.com', '432627200012210012', 1);
INSERT INTO `student` VALUES (10074, '杨菊香', '123456', '女', 3, '18890212345', '湖南邵阳市', '1999-10-06', 'yangjuxiang@126.com', '432626199910060021', 1);
INSERT INTO `student` VALUES (10075, '贺全新', '123456', '男', 3, '13099238835', '湖南长沙市岳麓区', '1999-11-07', 'hequanxing@126.com', '432627199911072201', 1);
INSERT INTO `student` VALUES (10076, '唐云芳', '123456', '女', 3, '13399032569', '湖南郴州', '2000-05-02', 'tangyunfang@126.com', '432627200005020019', 1);
INSERT INTO `student` VALUES (10077, '邵敏', '123456', '女', 3, '15800956683', '湖南岳阳市', '2000-09-21', 'shaoming@126.com', '432627200009210026', 1);
INSERT INTO `student` VALUES (10080, '张振贤', '123456', '男', 2, '13544789021', '湖南长沙市', '2000-04-21', 'lizhengxian@126.com', '432627200004210291', 1);
INSERT INTO `student` VALUES (10106, '飞天小猪', 'e10adc3949ba59abbe56e057f20f883e', '男', 2, '15011111111', '湖南工业职业技术学院白鹤小区23栋', '2000-02-26', 'eniluzt@qq.com', '432627200002060391', 1);
INSERT INTO `student` VALUES (10123, 'zhangsan', '123', NULL, NULL, NULL, NULL, NULL, '172727452@qq.com', NULL, 1);
INSERT INTO `student` VALUES (10124, 'zhangsan', '123', NULL, NULL, NULL, NULL, NULL, '172727452@qq.com', NULL, 1);

-- ----------------------------
-- Table structure for subject
-- ----------------------------
DROP TABLE IF EXISTS `subject`;
CREATE TABLE `subject`  (
  `subjectNo` int NOT NULL AUTO_INCREMENT,
  `subjectName` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `classHour` int NOT NULL,
  `gradeId` int NOT NULL,
  PRIMARY KEY (`subjectNo`) USING BTREE,
  INDEX `FK_subject_grade`(`gradeId`) USING BTREE,
  CONSTRAINT `FK_subject_grade` FOREIGN KEY (`gradeId`) REFERENCES `grade` (`gradeId`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of subject
-- ----------------------------
INSERT INTO `subject` VALUES (3, 'Java基础', 52, 1);
INSERT INTO `subject` VALUES (4, 'Java高级', 60, 2);
INSERT INTO `subject` VALUES (5, 'Web前端', 68, 3);
INSERT INTO `subject` VALUES (6, 'MySql数据库', 24, 1);
INSERT INTO `subject` VALUES (7, 'JspServlet', 44, 2);
INSERT INTO `subject` VALUES (8, 'spring框架', 24, 2);
INSERT INTO `subject` VALUES (9, 'mybatis框架', 30, 2);
INSERT INTO `subject` VALUES (10, 'redis数据库', 20, 3);
INSERT INTO `subject` VALUES (11, 'SpringMvc框架', 36, 3);
INSERT INTO `subject` VALUES (12, 'SpringBoot框架', 40, 3);
INSERT INTO `subject` VALUES (13, 'Engix', 8, 3);
INSERT INTO `subject` VALUES (14, 'SpringCloud微服务', 28, 3);
INSERT INTO `subject` VALUES (15, 'Linux系统', 20, 3);

-- ----------------------------
-- Table structure for type
-- ----------------------------
DROP TABLE IF EXISTS `type`;
CREATE TABLE `type`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `other` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of type
-- ----------------------------
INSERT INTO `type` VALUES (1, '兰花类', '兰花');
INSERT INTO `type` VALUES (2, '菊花类', '菊花');
INSERT INTO `type` VALUES (3, '荷花类', '荷花');
INSERT INTO `type` VALUES (4, '玫瑰类', '玫瑰');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'admin', '$2a$10$/W/E3wLUYonNbMkf5k/9zeCBfyUzV8IjHZUEkCvC9civ1/HPzu1g2');
INSERT INTO `user` VALUES (2, 'zhangsan', '$2a$10$/W/E3wLUYonNbMkf5k/9zeCBfyUzV8IjHZUEkCvC9civ1/HPzu1g2');
INSERT INTO `user` VALUES (3, 'lisi', '$2a$10$/W/E3wLUYonNbMkf5k/9zeCBfyUzV8IjHZUEkCvC9civ1/HPzu1g2');
INSERT INTO `user` VALUES (4, 'wangwu', '$2a$10$/W/E3wLUYonNbMkf5k/9zeCBfyUzV8IjHZUEkCvC9civ1/HPzu1g2');

-- ----------------------------
-- Table structure for userrole
-- ----------------------------
DROP TABLE IF EXISTS `userrole`;
CREATE TABLE `userrole`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `uid` int NOT NULL,
  `rid` int NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of userrole
-- ----------------------------
INSERT INTO `userrole` VALUES (1, 1, 1);
INSERT INTO `userrole` VALUES (2, 1, 2);
INSERT INTO `userrole` VALUES (3, 2, 2);
INSERT INTO `userrole` VALUES (4, 3, 2);
INSERT INTO `userrole` VALUES (5, 4, 1);

SET FOREIGN_KEY_CHECKS = 1;
