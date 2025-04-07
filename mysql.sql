-- MySQL dump 10.13  Distrib 8.0.41, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: mycsdn
-- ------------------------------------------------------
-- Server version	8.0.41

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `admin`
--

DROP TABLE IF EXISTS `admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `admin` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '管理员ID',
  `adminname` varchar(50) NOT NULL COMMENT '管理员昵称',
  `adminpassword` varchar(50) NOT NULL COMMENT '管理员密码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='管理员表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin`
--

LOCK TABLES `admin` WRITE;
/*!40000 ALTER TABLE `admin` DISABLE KEYS */;
INSERT INTO `admin` VALUES (1,'龙抽经也','a6f0e1d67127deaa341e66bde0828454');
/*!40000 ALTER TABLE `admin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `blog`
--

DROP TABLE IF EXISTS `blog`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `blog` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '博文ID',
  `userid` int DEFAULT NULL COMMENT '用户ID',
  `columnid` int DEFAULT NULL COMMENT '专栏ID',
  `title` varchar(255) NOT NULL COMMENT '博文标题',
  `content` text COMMENT '博文正文',
  `blogdate` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '博文发表时间',
  `commentnum` int DEFAULT NULL COMMENT '评论数',
  `likenum` int DEFAULT NULL COMMENT '点赞数',
  `againstnum` int DEFAULT NULL COMMENT '举报数',
  `order` int NOT NULL COMMENT '是否置顶',
  PRIMARY KEY (`id`),
  KEY `fk_blog_user_id` (`userid`),
  CONSTRAINT `fk_blog_user_id` FOREIGN KEY (`userid`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='博文表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `blog`
--

LOCK TABLES `blog` WRITE;
/*!40000 ALTER TABLE `blog` DISABLE KEYS */;
INSERT INTO `blog` VALUES (1,4,0,'小米su8长什么样','我就是小米苏8','2025-04-06 20:13:30',0,3,1,0),(2,4,1,'小米汽车','骗你的，我是黑米','2025-04-06 20:16:04',0,2,0,1),(4,3,0,'哟哟','ikonikon','2025-04-07 04:24:22',0,0,0,0);
/*!40000 ALTER TABLE `blog` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `column`
--

DROP TABLE IF EXISTS `column`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `column` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '专栏ID',
  `userid` int DEFAULT NULL COMMENT '用户ID',
  `columnname` text COMMENT '专栏名称',
  PRIMARY KEY (`id`),
  KEY `fk_column_user_id` (`userid`),
  CONSTRAINT `fk_column_user_id` FOREIGN KEY (`userid`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='专栏表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `column`
--

LOCK TABLES `column` WRITE;
/*!40000 ALTER TABLE `column` DISABLE KEYS */;
INSERT INTO `column` VALUES (1,4,'小米汽车'),(2,4,'大米汽车'),(3,4,'黑米汽车');
/*!40000 ALTER TABLE `column` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `columnandblog`
--

DROP TABLE IF EXISTS `columnandblog`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `columnandblog` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `oder` int NOT NULL COMMENT '置顶',
  `columnid` int NOT NULL COMMENT '专栏ID',
  `blogid` int NOT NULL COMMENT '博文ID',
  `columnname` varchar(255) DEFAULT NULL COMMENT '专栏名',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='专栏博文表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `columnandblog`
--

LOCK TABLES `columnandblog` WRITE;
/*!40000 ALTER TABLE `columnandblog` DISABLE KEYS */;
/*!40000 ALTER TABLE `columnandblog` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comment`
--

DROP TABLE IF EXISTS `comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comment` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '评论ID',
  `userid` int DEFAULT NULL COMMENT '用户ID',
  `blogid` int DEFAULT NULL COMMENT '博文ID',
  `content` text NOT NULL COMMENT '评论内容',
  `commentdate` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '评论时间',
  `likenum` int DEFAULT NULL COMMENT '点赞数',
  PRIMARY KEY (`id`),
  KEY `fk_comment_user_id` (`userid`),
  KEY `fk_comment_blog_id` (`blogid`),
  CONSTRAINT `fk_comment_blog_id` FOREIGN KEY (`blogid`) REFERENCES `blog` (`id`),
  CONSTRAINT `fk_comment_user_id` FOREIGN KEY (`userid`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='评论表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment`
--

LOCK TABLES `comment` WRITE;
/*!40000 ALTER TABLE `comment` DISABLE KEYS */;
INSERT INTO `comment` VALUES (1,4,2,'111','2025-04-06 23:29:54',0),(2,4,1,'','2025-04-07 03:58:02',0),(3,4,1,'666','2025-04-07 04:03:12',0),(4,4,2,'666','2025-04-07 08:43:54',0);
/*!40000 ALTER TABLE `comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `follow`
--

DROP TABLE IF EXISTS `follow`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `follow` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `followerid` int DEFAULT NULL COMMENT '点关注的人ID',
  `followedid` int DEFAULT NULL COMMENT '被关注人的ID',
  PRIMARY KEY (`id`),
  KEY `fk_follower_user_id` (`followerid`),
  KEY `fk_followed_user_id` (`followedid`),
  CONSTRAINT `fk_followed_user_id` FOREIGN KEY (`followedid`) REFERENCES `user` (`id`),
  CONSTRAINT `fk_follower_user_id` FOREIGN KEY (`followerid`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='关注表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `follow`
--

LOCK TABLES `follow` WRITE;
/*!40000 ALTER TABLE `follow` DISABLE KEYS */;
INSERT INTO `follow` VALUES (1,4,4);
/*!40000 ALTER TABLE `follow` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `likeblog`
--

DROP TABLE IF EXISTS `likeblog`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `likeblog` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `likerid` int DEFAULT NULL COMMENT '点赞人的ID',
  `likedblogid` int DEFAULT NULL COMMENT '被点赞的博文ID',
  PRIMARY KEY (`id`),
  KEY `fk_likeblog_user_id` (`likerid`),
  KEY `fk_likeblog_blog_id` (`likedblogid`),
  CONSTRAINT `fk_likeblog_blog_id` FOREIGN KEY (`likedblogid`) REFERENCES `blog` (`id`),
  CONSTRAINT `fk_likeblog_user_id` FOREIGN KEY (`likerid`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='点赞表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `likeblog`
--

LOCK TABLES `likeblog` WRITE;
/*!40000 ALTER TABLE `likeblog` DISABLE KEYS */;
INSERT INTO `likeblog` VALUES (1,4,2),(2,4,2),(3,4,1),(4,4,1),(5,4,1);
/*!40000 ALTER TABLE `likeblog` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `likecomment`
--

DROP TABLE IF EXISTS `likecomment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `likecomment` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `likerid` int DEFAULT NULL COMMENT '点赞人的ID',
  `likedcommentid` int DEFAULT NULL COMMENT '被点赞的评论ID',
  PRIMARY KEY (`id`),
  KEY `fk_likecomment_user_id` (`likerid`),
  KEY `fk_likecomment_comment_id` (`likedcommentid`),
  CONSTRAINT `fk_likecomment_comment_id` FOREIGN KEY (`likedcommentid`) REFERENCES `comment` (`id`),
  CONSTRAINT `fk_likecomment_user_id` FOREIGN KEY (`likerid`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='点赞评论表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `likecomment`
--

LOCK TABLES `likecomment` WRITE;
/*!40000 ALTER TABLE `likecomment` DISABLE KEYS */;
/*!40000 ALTER TABLE `likecomment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `name` varchar(50) DEFAULT NULL COMMENT '用户名',
  `password` varchar(32) DEFAULT NULL COMMENT '密码',
  `status` char(10) DEFAULT NULL COMMENT '状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (2,'小明','9da01921df170aea2f0464322d38479a','active'),(3,'kkluv','48e4b2a8ac16604dc9b3b2841e6d0ee7','active'),(4,'小米','1ad4b7bd52ef4a763e2180f5ea852536','active'),(5,'kitty','039236ba0482c2d3cd9d23c9e1f3fef9','active');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-04-07  9:43:05
