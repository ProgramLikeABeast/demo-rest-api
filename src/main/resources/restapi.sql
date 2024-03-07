DROP DATABASE IF EXISTS `data_directory`;
CREATE DATABASE `data_directory`;
USE `data_directory`;

DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS products;
DROP TABLE IF EXISTS categories;
DROP TABLE IF EXISTS product_instances;
DROP TABLE IF EXISTS orders;
DROP TABLE IF EXISTS user_avatars;
DROP TABLE IF EXISTS small_images;
DROP TABLE IF EXISTS large_images;
DROP TABLE IF EXISTS payment_methods;
DROP TABLE IF EXISTS addresses;

CREATE TABLE users (
	uid INT NOT NULL AUTO_INCREMENT,
    phone VARCHAR(15) UNIQUE,
    username VARCHAR(50),
    pwd VARCHAR(50),
    email VARCHAR(50),
    momo_stamp INT,
    UNIQUE(username, email),
    PRIMARY KEY (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

CREATE TABLE categories (
	cid INT NOT NULL AUTO_INCREMENT,
    category VARCHAR(50) NOT NULL,
    root_category VARCHAR(50),
    UNIQUE(category),
    PRIMARY KEY (`cid`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

CREATE TABLE products (
	pid INT AUTO_INCREMENT,
    product_name VARCHAR(50),
    cid INT,
    product_desc VARCHAR(100),
    base_price FLOAT,
    has_flavor BOOL,
    enable_hot BOOL,
    enable_cold BOOL,
    enable_large BOOL,
    avail_sugar_level VARCHAR(30),
    avail_ice_level VARCHAR(30),
    topping_blacklist VARCHAR(100),
    free_topping_quantity INT,
    ice_cream_container VARCHAR(15),
    tags VARCHAR(50),
    PRIMARY KEY (`pid`),
    FOREIGN KEY (cid) REFERENCES categories(cid)
		ON DELETE SET NULL
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;

CREATE TABLE orders (
	oid INT AUTO_INCREMENT,
    uid INT,
    total_price FLOAT,
    discount_amount FLOAT,
    status_code INT,
    ts TIMESTAMP,
    PRIMARY KEY(`oid`),
    UNIQUE(ts, uid)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

CREATE TABLE product_instances (
	piid INT AUTO_INCREMENT,
    oid INT,
    pid INT,
    ts TIMESTAMP,
    detail VARCHAR(150),
    quantity INT,
    PRIMARY KEY (`piid`),
    FOREIGN KEY (oid) REFERENCES orders(oid)
		ON DELETE CASCADE,
    FOREIGN KEY (pid) REFERENCES products(pid)
		ON DELETE SET NULL
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

CREATE TABLE small_images (
	siid INT AUTO_INCREMENT,
    pid INT,
    image_name VARCHAR(50),
    content_type VARCHAR(30),
    image_data BLOB,
    PRIMARY KEY (`siid`),
    FOREIGN KEY (pid) REFERENCES products(pid)
		ON DELETE SET NULL
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

CREATE TABLE user_avatar (
	uaid INT AUTO_INCREMENT,
    uid INT,
    avatar_name VARCHAR(50),
    content_type VARCHAR(30),
    avatar_data BLOB,
    PRIMARY KEY (`uaid`),
    FOREIGN KEY (uid) REFERENCES users(uid)
		ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

CREATE TABLE large_images (
	liid INT AUTO_INCREMENT,
    pid INT,
    image_name VARCHAR(50),
    content_type VARCHAR(30),
    image_data MEDIUMBLOB,
    PRIMARY KEY (`liid`),
    FOREIGN KEY (pid) REFERENCES products(pid)
		ON DELETE SET NULL
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

CREATE TABLE payment_methods (
	pmid INT AUTO_INCREMENT,
    uid INT,
    card_num DECIMAL(16),
    name_on_card VARCHAR(50),
    exp_date DATE,
    PRIMARY KEY(`pmid`),
    FOREIGN KEY (uid) REFERENCES users(uid)
		ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;
