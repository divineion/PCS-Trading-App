CREATE TABLE IF NOT EXISTS role (
  id INT UNSIGNED NOT NULL AUTO_INCREMENT,
  name VARCHAR(50) NOT NULL,
  PRIMARY KEY (id),
  UNIQUE INDEX `name_UNIQUE` (name)
  );

CREATE TABLE IF NOT EXISTS bid_list (
  id INT UNSIGNED PRIMARY KEY NOT NULL AUTO_INCREMENT,
  account VARCHAR(30) NOT NULL,
  type VARCHAR(30) NOT NULL,
  bid_quantity INT UNSIGNED,
  ask_quantity INT UNSIGNED,
  bid DECIMAL(10,4) UNSIGNED,
  ask DECIMAL(10,4) UNSIGNED,
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
  id INT UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
  account VARCHAR(30) NOT NULL,
  type VARCHAR(30) NOT NULL,
  buy_quantity DECIMAL(10,2) UNSIGNED,
  sell_quantity DECIMAL(10,2) UNSIGNED,
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
  id INT UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
  curve_id INT NOT NULL,
  as_of_date DATETIME,
  term DECIMAL(3,1) NOT NULL,
  curve_value DECIMAL(3,1) NOT NULL,
  creation_date DATETIME,
  UNIQUE (curve_id, term)
  );

CREATE TABLE IF NOT EXISTS rating (
  id INT UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
  moodys_rating VARCHAR(125),
  sand_p_rating VARCHAR(125),
  fitch_rating VARCHAR(125),
  order_number INT NOT NULL,
  UNIQUE INDEX unique_order_number (order_number)
);

CREATE TABLE IF NOT EXISTS trading_rule (
  id INT UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(125) NOT NULL UNIQUE,
  description VARCHAR(125),
  json TEXT,
  template TEXT,
  sql_str TEXT,
  sql_part TEXT,
  UNIQUE INDEX `name_UNIQUE` (name)
);

CREATE TABLE IF NOT EXISTS app_user (
  id INT UNSIGNED NOT NULL AUTO_INCREMENT,
  username VARCHAR(30) NOT NULL,
  password VARCHAR(255) NOT NULL,
  fullname VARCHAR(125) NOT NULL,
  role INT UNSIGNED NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT `fk_user_role` 
  FOREIGN KEY (role)
  REFERENCES role (`id`)
  ON DELETE RESTRICT
  ON UPDATE CASCADE,
  UNIQUE INDEX `username_UNIQUE` (username)
);
