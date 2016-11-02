CREATE DATABASE IF NOT EXISTS twitter;

CREATE USER IF NOT EXISTS 'deeptwitter'@'localhost' IDENTIFIED BY 'WayneBruce';
GRANT ALL ON twitter.* TO 'deeptwitter'@'localhost';

USE twitter;

CREATE TABLE IF NOT EXISTS tweets(
	id BIGINT,
	text VARCHAR (200),
	lang VARCHAR (5),
	favcount INT,
	rtcount INT,
	username VARCHAR (50),
	createdat BIGINT,
	PRIMARY KEY(id)
)