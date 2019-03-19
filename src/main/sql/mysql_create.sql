CREATE DATABASE sample_website;
USE sample_website;

CREATE TABLE message (
  id BIGINT NOT NULL AUTO_INCREMENT,
  nickname VARCHAR(20) NOT NULL,
	content VARCHAR(20) NOT NULL,
	PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8;

CREATE TABLE user (
  id BIGINT NOT NULL AUTO_INCREMENT,
  username VARCHAR(20) NOT NULL UNIQUE,
	pwd VARCHAR(20) NOT NULL,
	PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8;

SET NAMES gbk;
INSERT INTO message(nickname, content)
VALUES
  ("李白", "床前明月光"),
  ("贺知章", "不知细叶谁裁出，二月春风似剪刀"),
  ("杜甫", "八月秋高风怒号，卷我屋上三重茅"),
  ("苏轼", "明月几时有，把酒问青天");

INSERT INTO user(username, pwd)
VALUES
  ("123", "123"),
  ("admin", "123");