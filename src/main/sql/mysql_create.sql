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

-- item
CREATE TABLE item (
  id BIGINT NOT NULL AUTO_INCREMENT,
  name VARCHAR(20) NOT NULL,
	price DECIMAL(8, 2) NOT NULL, -- -- 最高消费999999.99
	quantity DECIMAL(6, 0) NOT NULL, -- 数量上限999999
	PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8;

-- cart_item
CREATE TABLE cart_item (
  id BIGINT NOT NULL AUTO_INCREMENT,
  user_id BIGINT NOT NULL,
  item_id BIGINT NOT NULL,
  quantity DECIMAL(4, 0), -- 数量上限999999
  PRIMARY KEY (id),
  FOREIGN KEY(item_id) REFERENCES item(id),
  FOREIGN KEY(user_id) REFERENCES user(id)
) ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8;

SET NAMES gbk;
INSERT INTO message(nickname, content)
VALUES
  ("李白", "床前明月光"),
  ("贺知章", "不知细叶谁裁出，二月春风似剪刀"),
  ("杜甫", "八月秋高风怒号，卷我屋上三重茅"),
  ("苏轼", "明月几时有，把酒问青天");

INSERT INTO item(name, price, quantity)
VALUES
  ("test_item01", 12.5, 9999),
  ("test_item02", 33, 998);

INSERT INTO cart_item(user_id, item_id, quantity)
VALUES
  (1000, 1000, 12),
  (1000, 1001, 2);