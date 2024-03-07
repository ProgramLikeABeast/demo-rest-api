LOAD DATA LOCAL INFILE 'C://ProgramData//MySQL//MySQL Server 8.0//Uploads//product.csv'
INTO TABLE data_directory.products
FIELDS TERMINATED BY ',' ENCLOSED BY '"'
LINES TERMINATED BY '\n'
IGNORE 1 ROWS
(pid, product_name, cid, product_desc, base_price, has_flavor, enable_hot, enable_cold, enable_large, avail_sugar_level, avail_ice_level, topping_blacklist, free_topping_quantity, ice_cream_container, @var)
SET tags = NULLIF(@var, "null");

SET SQL_SAFE_UPDATES = 0;
UPDATE data_directory.products
SET tags = TRIM(TRAILING '\r' FROM tags)
WHERE tags LIKE '%\r';