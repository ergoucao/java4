/*
SQLyog Ultimate v12.08 (64 bit)
MySQL - 5.7.28-log : Database - myfirstproject
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`myfirstproject` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `myfirstproject`;

/*Table structure for table `t_student` */

DROP TABLE IF EXISTS `t_student`;

CREATE TABLE `t_student` (
  `sid` int(10) NOT NULL AUTO_INCREMENT,
  `studentid` varchar(15) NOT NULL,
  `studentname` varchar(100) NOT NULL,
  PRIMARY KEY (`sid`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8;

/*Data for the table `t_student` */

insert  into `t_student`(`sid`,`studentid`,`studentname`) values (1,'22180117','曹鑫'),(2,'221801112','曹鑫'),(3,'221801113','曹鑫'),(4,'22180116','曹鑫'),(5,'221801117','曹鑫'),(6,'2218011115','曹鑫'),(12,'221801115','曹鑫'),(13,'22180112','曹鑫'),(14,'221801120','曹鑫'),(15,'1','曹鑫'),(16,'2','曹鑫'),(17,'3','曹鑫'),(18,'4','曹鑫'),(19,'5','曹鑫'),(20,'6','曹鑫'),(21,'7','曹鑫'),(22,'9','曹鑫'),(23,'10','曹鑫'),(24,'10','曹鑫'),(25,'11','曹鑫'),(26,'13','曹鑫');

/*Table structure for table `t_test` */

DROP TABLE IF EXISTS `t_test`;

CREATE TABLE `t_test` (
  `tid` int(3) NOT NULL AUTO_INCREMENT,
  `tname` varchar(100) NOT NULL,
  `tcontent` varchar(5000) NOT NULL,
  `tDeadline` datetime NOT NULL,
  `test` varchar(50) NOT NULL,
  `state` int(10) NOT NULL,
  `period` int(11) DEFAULT NULL,
  PRIMARY KEY (`tid`,`test`)
) ENGINE=InnoDB AUTO_INCREMENT=88 DEFAULT CHARSET=utf8;

/*Data for the table `t_test` */

insert  into `t_test`(`tid`,`tname`,`tcontent`,`tDeadline`,`test`,`state`,`period`) values (1,'java4','前端说本次任务很简单','2020-02-11 14:34:30','3',0,1),(2,'project1','后端说本次任务很难','2020-02-11 14:34:30','4',0,1),(3,'ajax3','前端说本次任务很简单','2020-02-11 14:34:30','5',0,1),(4,'java4','后端说本次任务很难','2020-02-11 14:34:30','6',0,1),(5,'project1','后端说本次任务很难','2020-02-11 14:34:30','7',0,1),(6,'ajax3','前端说本次任务很简单','2020-02-11 14:34:30','8',0,1),(7,'java4','后端说本次任务很难','2020-02-11 14:34:30','9',0,1),(8,'ajax3','后端说本次任务很难','2020-02-11 14:34:30','10',0,1),(9,'java4','前端说本次任务很简单','2020-02-11 14:34:30','11',0,1),(10,'ajax3','前端说本次任务很简单','2020-02-11 14:34:30','12',0,1),(11,'project1','后端说本次任务很难','2020-02-11 14:34:30','13',0,1),(48,'java4565','本次任务很简单easy源自408','2020-02-15 18:25:31','15',0,4),(49,'java4','本次任务很简单','2020-02-11 14:34:30','16',0,1);

/*Table structure for table `t_user` */

DROP TABLE IF EXISTS `t_user`;

CREATE TABLE `t_user` (
  `uid` varchar(10) NOT NULL,
  `uname` varchar(100) NOT NULL,
  `pwd` varchar(100) NOT NULL,
  `sex` char(2) NOT NULL,
  `age` varchar(4) DEFAULT NULL,
  `birth` date DEFAULT NULL,
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_user` */

insert  into `t_user`(`uid`,`uname`,`pwd`,`sex`,`age`,`birth`) values ('1','张三','123','1','18','2000-10-10'),('2','李四','456','1','19','1999-10-10'),('3','曹鑫','caoxin','1','20','2002-02-20');

/*Table structure for table `t_work` */

DROP TABLE IF EXISTS `t_work`;

CREATE TABLE `t_work` (
  `wid` int(5) unsigned NOT NULL AUTO_INCREMENT,
  `filepath` varchar(1000) NOT NULL,
  `sid` int(10) NOT NULL,
  `test` int(2) DEFAULT NULL,
  `subTime` datetime DEFAULT NULL,
  `pass` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`wid`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8;

/*Data for the table `t_work` */

insert  into `t_work`(`wid`,`filepath`,`sid`,`test`,`subTime`,`pass`) values (1,'E:\\java idea\\webDemo09_fileDownload\\out\\artifacts\\webDemo09_fileDownload_Web_exploded\\WEB-INF\\upload\\221801112_曹鑫_18软工_表_数据结构 表2.2 poly解题报告. (1).pptx',1,1,'2020-02-11 11:10:51',-1),(17,'E:\\java idea\\webDemo09_fileDownload\\out\\artifacts\\webDemo09_fileDownload_Web_exploded\\WEB-INF\\upload\\221801112_曹鑫_18软工_表_数据结构 表2.2 poly解题报告. (1).pptx',15,15,'2020-02-15 19:27:12',-1),(19,'E:\\java idea\\webDemo09_fileDownload\\out\\artifacts\\webDemo09_fileDownload_Web_exploded\\WEB-INF\\upload\\221801129_许鹏宇_18软工_树_数据结构 树6.38 营救行动2解题报告..pptx',15,15,'2020-02-15 19:27:12',-1),(20,'E:\\java idea\\webDemo09_fileDownload\\out\\artifacts\\webDemo09_fileDownload_Web_exploded\\WEB-INF\\upload\\221801208_吴晗杰_18软工_图_数据结构 图10.8 bus解题报告. (1).pptx',17,15,'2020-02-15 19:27:12',-1),(21,'E:\\java idea\\webDemo09_fileDownload\\out\\artifacts\\webDemo09_fileDownload_Web_exploded\\WEB-INF\\upload\\221801228_王振南_18软工_绪论_数据结构 绪论 1.11 我喜欢 解题报告..pptx',18,15,'2020-02-15 19:27:12',-1),(22,'E:\\java idea\\webDemo09_fileDownload\\out\\artifacts\\webDemo09_fileDownload_Web_exploded\\WEB-INF\\upload\\AMap_adcode_citycode.xlsx.zip',19,15,'2020-02-15 19:27:12',-1),(23,'E:\\java idea\\webDemo09_fileDownload\\out\\artifacts\\webDemo09_fileDownload_Web_exploded\\WEB-INF\\upload\\Shadowsocks-2.5.8.zip',20,15,'2020-02-15 19:27:12',-1),(24,'E:\\java idea\\webDemo09_fileDownload\\out\\artifacts\\webDemo09_fileDownload_Web_exploded\\WEB-INF\\upload\\apache-maven-3.6.3-bin (1).zip',21,15,'2020-02-15 19:27:12',-1),(25,'E:\\java idea\\webDemo09_fileDownload\\out\\artifacts\\webDemo09_fileDownload_Web_exploded\\WEB-INF\\upload\\apache-tomcat-v9.0.27.zip',21,15,'2020-02-15 19:27:12',-1),(27,'E:\\java idea\\webDemo09_fileDownload\\out\\artifacts\\webDemo09_fileDownload_Web_exploded\\WEB-INF\\upload\\英语作业1。.mp3',23,15,'2020-02-15 19:27:12',-1),(28,'E:\\java idea\\webDemo09_fileDownload\\out\\artifacts\\webDemo09_fileDownload_Web_exploded\\WEB-INF\\upload\\英语作业1。.mp3',23,15,'2020-02-15 19:27:12',-1),(29,'E:\\java idea\\webDemo09_fileDownload\\out\\artifacts\\webDemo09_fileDownload_Web_exploded\\WEB-INF\\upload\\eclipse-java-2019-06-R-win32-x86_64.zip',25,15,'2020-02-15 19:27:12',-1),(30,'E:\\java idea\\webDemo09_fileDownload\\out\\artifacts\\webDemo09_fileDownload_Web_exploded\\WEB-INF\\upload\\eclipse-java-2019-06-R-win32-x86_64.zip',25,15,'2020-02-15 19:27:12',-1),(33,'E:\\java idea\\webDemo09_fileDownload\\out\\artifacts\\webDemo09_fileDownload_Web_exploded\\WEB-INF\\upload\\JSS-master.zip',3,15,'2020-02-16 10:14:49',-1),(34,'E:\\java idea\\webDemo09_fileDownload\\out\\artifacts\\webDemo09_fileDownload_Web_exploded\\WEB-INF\\upload\\eclipse-java-2019-06-R-win32-x86_64.zip',3,16,'2020-02-16 10:14:49',-1);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
