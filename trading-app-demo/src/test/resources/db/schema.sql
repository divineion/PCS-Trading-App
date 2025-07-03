DROP TABLE IF EXISTS BidList;
DROP TABLE IF EXISTS Trade;
DROP TABLE IF EXISTS CurvePoint;
DROP TABLE IF EXISTS Rating;
DROP TABLE IF EXISTS RuleName;
DROP TABLE IF EXISTS app_user;
DROP TABLE IF EXISTS role;

CREATE TABLE IF NOT EXISTS role (
  id INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(50) NOT NULL UNIQUE,
  PRIMARY KEY (id)
 );

CREATE TABLE IF NOT EXISTS bid_list (
  id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  account VARCHAR(30) NOT NULL,
  type VARCHAR(30) NOT NULL,
  bid_quantity INT,
  ask_quantity INT,
  bid DECIMAL(10,4),
  ask DECIMAL(10,4),
  benchmark VARCHAR(125),
  bid_list_date DATETIME,
  commentary VARCHAR(125),
  security VARCHAR(125),
  status VARCHAR(10),
  trader VARCHAR(20),
  book VARCHAR(125),
  creation_name VARCHAR(125),
  creation_date DATETIME ,
  revision_name VARCHAR(125),
  revision_date DATETIME ,
  deal_name VARCHAR(125),
  deal_type VARCHAR(125),
  source_list_id VARCHAR(125),
  side VARCHAR(20)
);

CREATE TABLE IF NOT EXISTS trade (
  id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
  account VARCHAR(30) NOT NULL,
  type VARCHAR(30) NOT NULL,
  buy_quantity INT,
  sell_quantity INT,
  buy_price DECIMAL(10,4) ,
  sell_price DECIMAL(10,4),
  trade_date DATETIME,
  security VARCHAR(125),
  status VARCHAR(10),
  trader VARCHAR(125),
  benchmark VARCHAR(125),
  book VARCHAR(125),
  creation_name VARCHAR(125),
  creation_date DATETIME ,
  revision_name VARCHAR(125),
  revision_date DATETIME ,
  deal_name VARCHAR(125),
  deal_type VARCHAR(125),
  source_list_id VARCHAR(125),
  side VARCHAR(20)
);

CREATE TABLE IF NOT EXISTS curve_point (
  id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
  curve_id INT NOT NULL,
  as_of_date DATETIME,
  term DECIMAL(3,1) NOT NULL,
  curve_value DECIMAL(3,1) NOT NULL,
  creation_date DATETIME
  );

CREATE TABLE IF NOT EXISTS rating (
  id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
  moodys_rating VARCHAR(125),
  sand_p_rating VARCHAR(125),
  fitch_rating VARCHAR(125),
  order_number INT NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS rule_name (
  id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(125),
  description VARCHAR(125),
  json VARCHAR(255),
  template VARCHAR(512),
  sql_str VARCHAR(125),
  sql_part VARCHAR(125)
);

CREATE TABLE IF NOT EXISTS app_user (
  id INT NOT NULL AUTO_INCREMENT,
  username VARCHAR(30) NOT NULL UNIQUE,
  password VARCHAR(255) NOT NULL,
  fullname VARCHAR(125) NOT NULL,
  role INT NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT `fk_user_role` 
  FOREIGN KEY (role)
  REFERENCES role (`id`)
  ON DELETE RESTRICT
  ON UPDATE CASCADE
);
