create schema finance_db;

use finance_db;

create table ACCOUNT (
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(100),
    description VARCHAR(1000),
    logo VARCHAR(1000),
    start_amount DECIMAL,
    PRIMARY KEY (id)
);

create table CATEGORY (
    id INT NOT NULL AUTO_INCREMENT,
    parent_id INT,
    name VARCHAR(100),
    PRIMARY KEY (id)
);

create table TRANSACTION(
	id INT NOT NULL AUTO_INCREMENT,
	account_id INT NOT NULL,
	category_id INT NOT NULL,
	parent_id INT,
	amount DECIMAL,
	description VARCHAR(1000),
	date DATETIME,
  type VARCHAR(50),
	PRIMARY KEY (id),
    FOREIGN KEY (account_id) REFERENCES ACCOUNT(id),
    FOREIGN KEY (category_id) REFERENCES CATEGORY(id)
);
