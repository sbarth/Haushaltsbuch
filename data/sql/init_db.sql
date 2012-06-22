create schema finance_db;

use finance_db;

create table ACCOUNT (
    account_id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(100),
    description VARCHAR(1000),
    logo VARCHAR(1000),
    PRIMARY KEY (account_id)
);

create table CATEGORY (
    category_id INT NOT NULL AUTO_INCREMENT,
    parent_id INT,
    name VARCHAR(100),
    PRIMARY KEY (category_id),
    FOREIGN KEY (parent_id) REFERENCES CATEGORY(category_id)
);

create table TRANSACTION_TYPE (
	type_id INT NOT NULL AUTO_INCREMENT,
	name VARCHAR(100),
	PRIMARY KEY (type_id)
);

create table TRANSACTION(
	transaction_id INT NOT NULL AUTO_INCREMENT,
	account_id INT NOT NULL,
	category_id INT NOT NULL,
	parent_id INT,
	type_id INT,
	amount DECIMAL NOT NULL,
	description VARCHAR(1000),
	date DATETIME,
	PRIMARY KEY (transaction_id),
    FOREIGN KEY (account_id) REFERENCES ACCOUNT(account_id),
    FOREIGN KEY (category_id) REFERENCES CATEGORY(category_id),
    FOREIGN KEY (transaction_id) REFERENCES TRANSACTION(transaction_id),
    FOREIGN KEY (type_id) REFERENCES TRANSACTION_TYPE(type_id)
);