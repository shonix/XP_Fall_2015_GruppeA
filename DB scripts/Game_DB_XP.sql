
-- ------------------------------------------ --
-- Create a new database called mydb
create database  if not exists mydb;
-- Use mydb for other statements
use mydb;
-- Create a user and password to database
grant usage on *.* to sqluser@localhost identified by 'sqluserpw'; 
 -- Give user permission to create, read, update and delete
grant all privileges on mydb.* to sqluser@localhost; 
-- -----------------------------------------------------
-- Table user
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS user (
  ID INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(18) NOT NULL,
  password VARCHAR(20) NOT NULL,
  IP VARCHAR(15) NOT NULL,
  PRIMARY KEY (ID),
  UNIQUE INDEX name_UNIQUE (name ASC));
-- -----------------------------------------------------
-- Table status
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS status (
  userID INT NOT NULL,
  active BOOLEAN NOT NULL DEFAULT true,
  ban BOOLEAN NOT NULL DEFAUlt false,
  PRIMARY KEY (userID),
  FOREIGN KEY (userID)
  REFERENCES user (ID));
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

