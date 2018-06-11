# ************************************************************
# Sequel Pro SQL dump
# Versão 4541
#
# http://www.sequelpro.com/
# https://github.com/sequelpro/sequelpro
#
# Host: dds.c0muvaanwkln.sa-east-1.rds.amazonaws.com (MySQL 5.7.21-log)
# Base de Dados: dds
# Tempo de Geração: 2018-05-21 19:52:00 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Dump da tabela channel_user
# ------------------------------------------------------------

DROP TABLE IF EXISTS `channel_user`;

CREATE TABLE `channel_user` (
  `channel_id` int(10) unsigned NOT NULL,
  `user_id` int(10) unsigned NOT NULL,
  KEY `channel_user_channel_id_foreign` (`channel_id`),
  KEY `channel_user_user_id_foreign` (`user_id`),
  CONSTRAINT `channel_user_channel_id_foreign` FOREIGN KEY (`channel_id`) REFERENCES `channels` (`id`) ON DELETE CASCADE,
  CONSTRAINT `channel_user_user_id_foreign` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;



# Dump da tabela channels
# ------------------------------------------------------------

DROP TABLE IF EXISTS `channels`;

CREATE TABLE `channels` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `slack_id` varchar(10) COLLATE utf8mb4_unicode_ci NOT NULL,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `type` enum('channel','group','direct') COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `channels_slack_id_unique` (`slack_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;



# Dump da tabela messages
# ------------------------------------------------------------

DROP TABLE IF EXISTS `messages`;

CREATE TABLE `messages` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `text` Integertext COLLATE utf8mb4_unicode_ci NOT NULL,
  `hash` varchar(32) COLLATE utf8mb4_unicode_ci NOT NULL,
  `type` varchar(32) COLLATE utf8mb4_unicode_ci NOT NULL,
  `date` datetime NOT NULL,
  `channel_id` int(10) unsigned NOT NULL,
  `user_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `messages_hash_unique` (`hash`),
  KEY `messages_channel_id_foreign` (`channel_id`),
  KEY `messages_user_id_foreign` (`user_id`),
  CONSTRAINT `messages_channel_id_foreign` FOREIGN KEY (`channel_id`) REFERENCES `channels` (`id`) ON DELETE CASCADE,
  CONSTRAINT `messages_user_id_foreign` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;



# Dump da tabela migrations
# ------------------------------------------------------------

DROP TABLE IF EXISTS `migrations`;

CREATE TABLE `migrations` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `migration` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `batch` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

LOCK TABLES `migrations` WRITE;
/*!40000 ALTER TABLE `migrations` DISABLE KEYS */;

INSERT INTO `migrations` (`id`, `migration`, `batch`)
VALUES
	(1,'2018_03_19_204308_create_users_table',1),
	(2,'2018_03_20_180230_add_name_email_column',1),
	(3,'2018_03_20_185302_create_channels_table',1),
	(4,'2018_03_20_195631_create_menssages_table',1);

/*!40000 ALTER TABLE `migrations` ENABLE KEYS */;
UNLOCK TABLES;


# Dump da tabela users
# ------------------------------------------------------------

DROP TABLE IF EXISTS `users`;

CREATE TABLE `users` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `slack_id` varchar(9) COLLATE utf8mb4_unicode_ci NOT NULL,
  `slack_token` mediumtext COLLATE utf8mb4_unicode_ci,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `email` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `users_slack_id_unique` (`slack_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;

INSERT INTO `users` (`id`, `slack_id`, `slack_token`, `created_at`, `updated_at`, `name`, `email`)
VALUES
	(3,'UAD8B843V','xoxp-353216679731-353283276131-353283491331-ac6cd002fc1375610eabf41c1d91a3d2','2018-04-26 00:23:58','2018-04-26 00:23:58','Alexandre Lerario','alerario@gmail.com'),
	(4,'UAD7P0ECT','xoxp-353216679731-353261014435-353159024612-c6b61501d05f58208dd98c578d2b9100','2018-04-26 00:34:08','2018-04-26 00:34:08','Vinicius Baroni Soares','vsoares@alunos.utfpr.edu.br'),
	(5,'UAECVL9DM','xoxp-353216679731-354437689463-353389889234-b357415f0c837c63373bd469fcfc1d98','2018-04-26 00:38:21','2018-04-26 00:38:21','Amilton Junior','amilton@alunos.utfpr.edu.br'),
	(6,'UAD8ES2RZ','xoxp-353216679731-353286886883-352844407489-c767dd5017ffbfcb441cb496f9a58b09','2018-04-26 00:39:36','2018-04-26 00:39:36','Leandro Fernandes','leandro-gf@uol.com.br'),
	(7,'UADFGDQS1','xoxp-353216679731-353526466885-353289592035-a115cd982e36cd8e635132c030c671d0','2018-04-26 00:39:40','2018-04-26 00:39:40','Cesar Cardoso','cesarcardoso954@gmail.com'),
	(8,'UACQW8QKB','xoxp-353216679731-352846296657-352696027776-f1412196d671166d528b73b30740df54','2018-04-26 00:45:40','2018-04-26 00:45:40','Bruno Daguano','brunodaguano@alunos.utfpr.edu.br'),
	(9,'UAECW859V','xoxp-353216679731-354438277335-353164673716-7c26e4fffc3d3cd9a9b77badacacb62d','2018-04-26 00:49:10','2018-04-26 00:49:10','Renato Candido','renatinhu_barbosa@hotmail.com'),
	(11,'UACLCQU72','xoxp-353216679731-352692844240-354444704151-f4a1b1657f50c4536fa9d6e2b4d735cf','2018-04-26 00:56:38','2018-04-26 00:56:38','Caio Thizio','caiothizio@alunos.utfpr.edu.br'),
	(12,'UACLBUPNC','xoxp-353216679731-352691975760-354445106055-bb9f52a859073498d29ff63872f7e5bb','2018-04-26 00:57:42','2018-04-26 00:57:42','João Paulo Paes','joaopaulopaez@gmail.com'),
	(13,'UADFH0YJH','xoxp-353216679731-353527032629-354445143895-201728b04ca241298ae15643d780c6e8','2018-04-26 00:57:47','2018-04-26 00:57:47','Adolpho Nascimento','acn2002@live.com'),
	(14,'UAD8HUYLB','xoxp-353216679731-353289984691-353296445347-f83e275ec86c91f45c7297727b02fda9','2018-04-26 00:57:59','2018-04-26 00:57:59','Vitor Carnevalli','vitorcarnevalli007@hotmail.com'),
	(15,'UAD8GCSFM','xoxp-353216679731-353288434531-352700769664-929b67aef432036d284304b15d219397','2018-04-26 00:58:26','2018-04-26 00:58:26','Cíntia Nunes','cintianunessantos@gmail.com'),
	(16,'UAD560FD2','xoxp-353216679731-353176015444-354453940519-40214f182c88c7d35320a1e111c7d934','2018-04-26 01:20:29','2018-04-26 01:20:29','Nicolas Aoki','nick_aoki@hotmail.com'),
	(17,'UAD6CL04T','xoxp-353216679731-353216680163-353357000178-494014014ff1c07e4eca75dd6d9768ca','2018-04-26 01:30:45','2018-04-26 01:30:45','Fabricio Oliveira','fabricio.jhonata@gmail.com'),
	(18,'UADBLH5DG','xoxp-353216679731-353394583458-354288330758-7abc851a75bd2cdc3974353d7622e50d','2018-05-03 20:20:34','2018-05-03 20:20:34','Lucas','lucas.verdine@gmail.com');

/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;



/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
