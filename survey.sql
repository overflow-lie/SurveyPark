/*
SQLyog Ultimate v12.08 (64 bit)
MySQL - 5.1.32-community : Database - survey
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`survey` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `survey`;

/*Table structure for table `t_admin` */

DROP TABLE IF EXISTS `t_admin`;

CREATE TABLE `t_admin` (
  `ADMIN_ID` int(11) NOT NULL AUTO_INCREMENT,
  `ADMIN_NAME` varchar(255) DEFAULT NULL,
  `ADMIN_PWD` varchar(255) DEFAULT NULL,
  `RES_CODE` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ADMIN_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=61 DEFAULT CHARSET=gb2312;

/*Data for the table `t_admin` */

/*Table structure for table `t_admin_role_inner` */

DROP TABLE IF EXISTS `t_admin_role_inner`;

CREATE TABLE `t_admin_role_inner` (
  `ADMIN_ID` int(11) NOT NULL,
  `ROLE_ID` int(11) NOT NULL,
  PRIMARY KEY (`ADMIN_ID`,`ROLE_ID`),
  KEY `FK_rh09mfo7vcvierau6sb4yo50x` (`ROLE_ID`),
  KEY `FK_ep2s0123t3dlw39lp2h3mo3tx` (`ADMIN_ID`),
  CONSTRAINT `FK_ep2s0123t3dlw39lp2h3mo3tx` FOREIGN KEY (`ADMIN_ID`) REFERENCES `t_admin` (`ADMIN_ID`),
  CONSTRAINT `FK_rh09mfo7vcvierau6sb4yo50x` FOREIGN KEY (`ROLE_ID`) REFERENCES `t_role` (`ROLE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;

/*Data for the table `t_admin_role_inner` */

/*Table structure for table `t_answer` */

DROP TABLE IF EXISTS `t_answer`;

CREATE TABLE `t_answer` (
  `ANSWER_ID` int(11) NOT NULL AUTO_INCREMENT,
  `QUESTION_ID` int(11) DEFAULT NULL,
  `SURVEY_ID` int(11) DEFAULT NULL,
  `ANSWER_TIME` datetime DEFAULT NULL,
  `UUID` varchar(255) DEFAULT NULL,
  `MAIN_ANSWERS` varchar(255) DEFAULT NULL,
  `OTHER_ANSWERS` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ANSWER_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=105 DEFAULT CHARSET=gb2312;

/*Data for the table `t_answer` */

/*Table structure for table `t_auth_res_inner` */

DROP TABLE IF EXISTS `t_auth_res_inner`;

CREATE TABLE `t_auth_res_inner` (
  `AUTH_ID` int(11) NOT NULL,
  `RES_ID` int(11) NOT NULL,
  PRIMARY KEY (`AUTH_ID`,`RES_ID`),
  KEY `FK_eppy6trpr81hnbq82afqffi1y` (`RES_ID`),
  KEY `FK_5qd9xlgb24n7lq212sv2tvf5a` (`AUTH_ID`),
  CONSTRAINT `FK_5qd9xlgb24n7lq212sv2tvf5a` FOREIGN KEY (`AUTH_ID`) REFERENCES `t_authority` (`AUTHORITY_ID`),
  CONSTRAINT `FK_eppy6trpr81hnbq82afqffi1y` FOREIGN KEY (`RES_ID`) REFERENCES `t_resource` (`RESOURCE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;

/*Data for the table `t_auth_res_inner` */

insert  into `t_auth_res_inner`(`AUTH_ID`,`RES_ID`) values (4,1),(6,1),(6,2),(6,3),(6,4),(6,5),(6,6),(6,7),(6,8),(4,9),(6,10),(4,11),(4,12),(4,13),(4,14),(4,15),(2,16),(4,16),(11,16),(2,17),(2,18),(4,19),(4,20),(11,21),(11,22),(11,23),(11,24),(2,25),(2,26),(2,27),(2,28),(2,29),(2,30),(4,31),(3,32),(4,32),(3,33),(3,34),(3,35),(4,35),(3,36),(4,36),(5,37),(12,38),(12,39),(5,40),(12,41),(12,42),(12,43),(12,45),(12,46),(12,47),(1,49),(1,50),(13,51),(8,52),(8,53),(8,54),(5,55),(8,56),(8,57),(8,58),(8,59),(9,61),(9,62),(9,63),(9,64),(9,65),(9,66),(10,67),(10,68),(10,69),(10,70),(10,71),(13,72),(13,74),(14,80),(14,81),(14,82);

/*Table structure for table `t_authority` */

DROP TABLE IF EXISTS `t_authority`;

CREATE TABLE `t_authority` (
  `AUTHORITY_ID` int(11) NOT NULL AUTO_INCREMENT,
  `AUTHORITY_NAME` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`AUTHORITY_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=gb2312;

/*Data for the table `t_authority` */

insert  into `t_authority`(`AUTHORITY_ID`,`AUTHORITY_NAME`) values (1,'Excel管理'),(2,'包裹管理'),(3,'参与调查'),(4,'调查管理'),(5,'管理员基本操作'),(6,'用户基本操作'),(8,'权限数据管理'),(9,'角色数据管理'),(10,'管理员数据管理'),(11,'问题管理'),(12,'查看调查统计数据'),(13,'资源数据管理'),(14,'日志操作');

/*Table structure for table `t_bag` */

DROP TABLE IF EXISTS `t_bag`;

CREATE TABLE `t_bag` (
  `BAG_ID` int(11) NOT NULL AUTO_INCREMENT,
  `BAG_NAME` varchar(255) DEFAULT NULL,
  `SURVEY_ID` int(11) DEFAULT NULL,
  `BAG_ORDER` int(11) DEFAULT NULL,
  PRIMARY KEY (`BAG_ID`),
  KEY `FK_m4f4nk0r5krin9qx7qpthgkau` (`SURVEY_ID`),
  CONSTRAINT `FK_m4f4nk0r5krin9qx7qpthgkau` FOREIGN KEY (`SURVEY_ID`) REFERENCES `t_survey` (`SURVEY_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=gb2312;

/*Data for the table `t_bag` */

/*Table structure for table `t_engage` */

DROP TABLE IF EXISTS `t_engage`;

CREATE TABLE `t_engage` (
  `user_id` int(11) NOT NULL,
  `survey_id` int(11) NOT NULL,
  PRIMARY KEY (`user_id`,`survey_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_engage` */

/*Table structure for table `t_log` */

DROP TABLE IF EXISTS `t_log`;

CREATE TABLE `t_log` (
  `LOG_ID` int(11) NOT NULL AUTO_INCREMENT,
  `OPERATOR` varchar(255) DEFAULT NULL,
  `OPERATE_TIME` varchar(255) DEFAULT NULL,
  `METHOD_TYPE` varchar(255) DEFAULT NULL,
  `METHOD_NAME` varchar(255) DEFAULT NULL,
  `METHOD_ARGS` varchar(6000) DEFAULT NULL,
  `METHOD_RETURN_VALUE` varchar(6000) DEFAULT NULL,
  `METHOD_RESULT_MSG` varchar(6000) DEFAULT NULL,
  PRIMARY KEY (`LOG_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_log` */

/*Table structure for table `t_log_2016_3` */

DROP TABLE IF EXISTS `t_log_2016_3`;

CREATE TABLE `t_log_2016_3` (
  `LOG_ID` int(11) NOT NULL AUTO_INCREMENT,
  `OPERATOR` varchar(255) DEFAULT NULL,
  `OPERATE_TIME` varchar(255) DEFAULT NULL,
  `METHOD_TYPE` varchar(255) DEFAULT NULL,
  `METHOD_NAME` varchar(255) DEFAULT NULL,
  `METHOD_ARGS` varchar(6000) DEFAULT NULL,
  `METHOD_RETURN_VALUE` varchar(6000) DEFAULT NULL,
  `METHOD_RESULT_MSG` varchar(6000) DEFAULT NULL,
  PRIMARY KEY (`LOG_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=177 DEFAULT CHARSET=utf8;

/*Data for the table `t_log_2016_3` */

/*Table structure for table `t_log_2016_4` */

DROP TABLE IF EXISTS `t_log_2016_4`;

CREATE TABLE `t_log_2016_4` (
  `LOG_ID` int(11) NOT NULL AUTO_INCREMENT,
  `OPERATOR` varchar(255) DEFAULT NULL,
  `OPERATE_TIME` varchar(255) DEFAULT NULL,
  `METHOD_TYPE` varchar(255) DEFAULT NULL,
  `METHOD_NAME` varchar(255) DEFAULT NULL,
  `METHOD_ARGS` varchar(6000) DEFAULT NULL,
  `METHOD_RETURN_VALUE` varchar(6000) DEFAULT NULL,
  `METHOD_RESULT_MSG` varchar(6000) DEFAULT NULL,
  PRIMARY KEY (`LOG_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_log_2016_4` */

/*Table structure for table `t_log_2016_5` */

DROP TABLE IF EXISTS `t_log_2016_5`;

CREATE TABLE `t_log_2016_5` (
  `LOG_ID` int(11) NOT NULL AUTO_INCREMENT,
  `OPERATOR` varchar(255) DEFAULT NULL,
  `OPERATE_TIME` varchar(255) DEFAULT NULL,
  `METHOD_TYPE` varchar(255) DEFAULT NULL,
  `METHOD_NAME` varchar(255) DEFAULT NULL,
  `METHOD_ARGS` varchar(6000) DEFAULT NULL,
  `METHOD_RETURN_VALUE` varchar(6000) DEFAULT NULL,
  `METHOD_RESULT_MSG` varchar(6000) DEFAULT NULL,
  PRIMARY KEY (`LOG_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_log_2016_5` */

/*Table structure for table `t_question` */

DROP TABLE IF EXISTS `t_question`;

CREATE TABLE `t_question` (
  `QUESTION_ID` int(11) NOT NULL AUTO_INCREMENT,
  `BAG_ID` int(11) DEFAULT NULL,
  `QUESTION_NAME` varchar(255) DEFAULT NULL,
  `QUESTION_TYPE` int(11) DEFAULT NULL,
  `OPTIONS` varchar(255) DEFAULT NULL,
  `BR` tinyint(1) DEFAULT NULL,
  `HAS_OTHER` tinyint(1) DEFAULT NULL,
  `OTHER_TYPE` int(11) DEFAULT NULL,
  `MATRIX_ROW_TITLES` varchar(255) DEFAULT NULL,
  `MATRIX_COL_TITLES` varchar(255) DEFAULT NULL,
  `MATRIX_OPTIONS` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`QUESTION_ID`),
  KEY `FK_jhx3c7cjtsu726sahxnsw9l03` (`BAG_ID`),
  CONSTRAINT `FK_jhx3c7cjtsu726sahxnsw9l03` FOREIGN KEY (`BAG_ID`) REFERENCES `t_bag` (`BAG_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=gb2312;

/*Data for the table `t_question` */

/*Table structure for table `t_resource` */

DROP TABLE IF EXISTS `t_resource`;

CREATE TABLE `t_resource` (
  `RESOURCE_ID` int(11) NOT NULL AUTO_INCREMENT,
  `ACTION_NAME` varchar(255) DEFAULT NULL,
  `RESOURCE_NAME` varchar(255) DEFAULT NULL,
  `RES_POS` int(11) DEFAULT NULL,
  `RES_CODE` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`RESOURCE_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=84 DEFAULT CHARSET=gb2312;

/*Data for the table `t_resource` */

insert  into `t_resource`(`RESOURCE_ID`,`ACTION_NAME`,`RESOURCE_NAME`,`RES_POS`,`RES_CODE`) values (1,'SurveyAction_top10','调查_前十',0,1),(2,'UserAction_register','用户_注册',0,2),(3,'UserAction_login','用户_登录',0,4),(4,'ToPageAction_user_myCenter','前往页面_用户_个人中心',0,8),(5,'ToPageAction_user_pay','前往页面_用户_充值',0,16),(6,'UserAction_pay','用户_充值',0,32),(7,'ToPageAction_user_vip','前往页面_用户_续费',0,64),(8,'UserAction_vip','用户_续费',0,128),(9,'ToPageAction_survey_create','前往页面_调查_创建',0,256),(10,'UserAction_logout','用户_退出登录',0,512),(11,'SurveyAction_save','调查_保存',0,1024),(12,'SurveyAction_myUncompleted','调查_我的未完成的',0,2048),(13,'SurveyAction_editSurvey','调查_编辑调查',0,4096),(14,'SurveyAction_update','调查_更新',0,8192),(15,'SurveyAction_remove','调查_删除',0,16384),(16,'SurveyAction_design','调查_设计',0,32768),(17,'ToPageAction_bag_add','前往页面_包裹_保存',0,65536),(18,'BagAction_save','包裹_保存',0,131072),(19,'SurveyAction_adjustBagOrder','调查_调整包裹顺序',0,262144),(20,'SurveyAction_updateBagOrder','调查_更新包裹顺序',0,524288),(21,'QuestionAction_toTypeChoosen','问题_前往类型选择',0,1048576),(22,'QuestionAction_toQuestionDesign','问题_前往问题设计',0,2097152),(23,'QuestionAction_saveOrUpdate','问题_保存或更新',0,4194304),(24,'QuestionAction_remove','问题_删除',0,8388608),(25,'BagAction_edit','包裹_编辑',0,16777216),(26,'BagAction_update','包裹_更新',0,33554432),(27,'BagAction_remove','包裹_删除',0,67108864),(28,'BagAction_toMoveCopyPage','包裹_前往移动复制页面',0,134217728),(29,'BagAction_moveToThisSurvey','包裹_移动前往这个调查',0,268435456),(30,'BagAction_copyToThisSurvey','包裹_复制前往这个调查',0,536870912),(31,'SurveyAction_complete','调查_完成',0,1073741824),(32,'SurveyAction_myCompletedSurveyList','调查_我的已完成的调查列表',0,2147483648),(33,'EngageAction_entry','参与_入口',0,4294967296),(34,'EngageAction_doEngage','参与_执行参与',0,8589934592),(35,'SurveyAction_myEngagedSurvey','调查_我的参与调查',0,17179869184),(36,'SurveyAction_findAllAvailableSurvey','调查_查找全部可用的调查',0,34359738368),(37,'AdminAction_toAdminMain','管理员_前往管理员主',0,68719476736),(38,'StatisticsAction_showSummary','统计_显示摘要',0,137438953472),(39,'StatisticsAction_showNormalChart','统计_显示常规图表',0,274877906944),(40,'AdminAction_login','管理员_登录',0,549755813888),(41,'StatisticsAction_showTextList','统计_显示文本列表',0,1099511627776),(42,'StatisticsAction_showNormalMatrixPage','统计_显示常规矩阵页面',0,2199023255552),(43,'StatisticsAction_showNormalMatrixChart','统计_显示常规矩阵图表',0,4398046511104),(45,'StatisticsAction_showOtherTextList','统计_显示其他文本列表',0,17592186044416),(46,'StatisticsAction_showOptionMatrixPage','统计_显示下拉列表形式的矩阵页面',0,35184372088832),(47,'StatisticsAction_showOptionMatrixChart','统计_显示下拉列表形式的矩阵图表',0,70368744177664),(49,'ExcelAction_showAllSurvey','excel_显示全部调查',0,140737488355328),(50,'ExcelAction_exportExcel','excel_导出excel',0,281474976710656),(51,'ResourceAction_showAllResources','资源_显示全部资源',0,562949953421312),(52,'AuthorityAction_showAuthorities','权限_显示权限',0,1125899906842624),(53,'AuthorityAction_toCreatePage','权限_前往创建页面',0,2251799813685248),(54,'AuthorityAction_save','权限_保存',0,4503599627370496),(55,'AdminAction_logout','管理员_退出登录',0,9007199254740992),(56,'AuthorityAction_update','权限_更新',0,18014398509481984),(57,'AuthorityAction_batchDelete','权限_批量删除',0,36028797018963968),(58,'AuthorityAction_toResMngPage','权限_前往资源管理页面',0,72057594037927936),(59,'AuthorityAction_resMng','权限_资源管理',0,144115188075855872),(60,'RoleAction_toCreatePage','角色_前往创建页面',0,288230376151711744),(61,'RoleAction_save','角色_保存',0,576460752303423488),(62,'RoleAction_showRoles','角色_显示角色',0,1152921504606846976),(63,'RoleAction_update','角色_更新',1,1),(64,'RoleAction_batchDelete','角色_批量删除',1,2),(65,'RoleAction_toAuthMngPage','角色_前往权限管理页面',1,4),(66,'RoleAction_authMng','角色_权限管理',1,8),(67,'AdminAction_generateAdmin','管理员_生成管理员',1,16),(68,'AdminAction_showAdmins','管理员_显示管理员',1,32),(69,'AdminAction_toRoleMngPage','管理员_前往角色管理页面',1,64),(70,'AdminAction_roleMng','管理员_角色管理',1,128),(71,'AdminAction_calculationCode','管理员_计算码',1,256),(72,'ResourceAction_update','资源_更新',1,512),(74,'ResourceAction_batchDelete','资源_批量删除',1,1024),(80,'LogAction_showLogs','日志_显示日志',1,2048),(81,'LogAction_toDownloadLogsPage','日志_前往下载日志页面',1,4096),(82,'LogAction_downloadLog','日志_下载日志',1,8192),(83,'EngageAction_findAllAvailableSurvey','参与_查找全部可用的调查',1,16384);

/*Table structure for table `t_role` */

DROP TABLE IF EXISTS `t_role`;

CREATE TABLE `t_role` (
  `ROLE_ID` int(11) NOT NULL AUTO_INCREMENT,
  `ROLE_NAME` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ROLE_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=gb2312;

/*Data for the table `t_role` */

insert  into `t_role`(`ROLE_ID`,`ROLE_NAME`) values (1,'权限管理员'),(2,'Excel管理员'),(3,'日志管理员'),(4,'付费登录用户'),(5,'普通登录用户');

/*Table structure for table `t_role_auth_inner` */

DROP TABLE IF EXISTS `t_role_auth_inner`;

CREATE TABLE `t_role_auth_inner` (
  `role_id` int(11) NOT NULL,
  `auth_id` int(11) NOT NULL,
  PRIMARY KEY (`role_id`,`auth_id`),
  KEY `FK_qstgqf6vqsrclayyuctlk3tcx` (`auth_id`),
  KEY `FK_dn8pj30wjslucqx93skqwj0m9` (`role_id`),
  CONSTRAINT `FK_dn8pj30wjslucqx93skqwj0m9` FOREIGN KEY (`role_id`) REFERENCES `t_role` (`ROLE_ID`),
  CONSTRAINT `FK_qstgqf6vqsrclayyuctlk3tcx` FOREIGN KEY (`auth_id`) REFERENCES `t_authority` (`AUTHORITY_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;

/*Data for the table `t_role_auth_inner` */

insert  into `t_role_auth_inner`(`role_id`,`auth_id`) values (2,1),(4,2),(4,3),(4,4),(1,5),(2,5),(3,5),(5,5),(4,6),(1,8),(1,9),(1,10),(4,11),(4,12),(1,13),(3,14);

/*Table structure for table `t_survey` */

DROP TABLE IF EXISTS `t_survey`;

CREATE TABLE `t_survey` (
  `SURVEY_ID` int(11) NOT NULL AUTO_INCREMENT,
  `TITLE` varchar(255) DEFAULT NULL,
  `LOGO_PATH` varchar(255) DEFAULT NULL,
  `COMPLETED` tinyint(1) DEFAULT NULL,
  `USER_ID` int(11) DEFAULT NULL,
  `COMPLETED_TIME` datetime DEFAULT NULL,
  PRIMARY KEY (`SURVEY_ID`),
  KEY `FK_nkbcv36cm3c4hxxlb7ylj51f0` (`USER_ID`),
  CONSTRAINT `FK_nkbcv36cm3c4hxxlb7ylj51f0` FOREIGN KEY (`USER_ID`) REFERENCES `t_user` (`USER_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=gb2312;

/*Data for the table `t_survey` */

/*Table structure for table `t_user` */

DROP TABLE IF EXISTS `t_user`;

CREATE TABLE `t_user` (
  `USER_ID` int(11) NOT NULL AUTO_INCREMENT,
  `USER_NAME` varchar(255) DEFAULT NULL,
  `USER_PWD` varchar(255) DEFAULT NULL,
  `NICK_NAME` varchar(255) DEFAULT NULL,
  `BALANCE` int(11) DEFAULT NULL,
  `PAY_STATUS` tinyint(1) DEFAULT NULL,
  `END_TIME` bigint(20) DEFAULT NULL,
  `EMAIL` varchar(255) DEFAULT NULL,
  `RES_CODE` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`USER_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=gb2312;

/*Data for the table `t_user` */

/*Table structure for table `t_user_role_inner` */

DROP TABLE IF EXISTS `t_user_role_inner`;

CREATE TABLE `t_user_role_inner` (
  `USER_ID` int(11) NOT NULL,
  `ROLE_ID` int(11) NOT NULL,
  PRIMARY KEY (`USER_ID`,`ROLE_ID`),
  KEY `FK_65b7n2spp5ktdnrajilcmha18` (`ROLE_ID`),
  KEY `FK_4n76qwi62j9vfejst7kjkay9n` (`USER_ID`),
  CONSTRAINT `FK_4n76qwi62j9vfejst7kjkay9n` FOREIGN KEY (`USER_ID`) REFERENCES `t_user` (`USER_ID`),
  CONSTRAINT `FK_65b7n2spp5ktdnrajilcmha18` FOREIGN KEY (`ROLE_ID`) REFERENCES `t_role` (`ROLE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;

/*Data for the table `t_user_role_inner` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
