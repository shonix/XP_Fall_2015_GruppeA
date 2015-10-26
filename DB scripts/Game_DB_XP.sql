
-- ------------------------------------------ --
-- Create a new database called mydb
create database  if not exists mydb;
 -- use mydb for other statements
use mydb;
-- -----------------------------------------------------
-- Table user
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS user (
  ID INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(45) NOT NULL,
  password VARCHAR(45) NOT NULL,
  PRIMARY KEY (ID),
  UNIQUE INDEX name_UNIQUE (name ASC));

-- -----------------------------------------------------
-- Table gameStat
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS gameStat (
  userID INT NOT NULL,
  gameType VARCHAR(25) NOT NULL,
  win INT NOT NULL DEFAULT 0,
  loss INT NOT NULL DEFAULT 0,
  draw INT NOT NULL DEFAULT 0,
  total INT NOT NULL DEFAULT 0,
  PRIMARY KEY (userID, gameType),
  FOREIGN KEY (userID)
  REFERENCES user(ID));
-- -----------------------------------------------------
-- Table leaves
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS leaves (
  userID INT NOT NULL,
  count INT NOT NULL DEFAULT 0,
  percentage FLOAT NOT NULL DEFAULT 0,
  totalGames INT NOT NULL DEFAULT 0,
  PRIMARY KEY (userID),
  FOREIGN KEY (userID)
  REFERENCES user (ID));
