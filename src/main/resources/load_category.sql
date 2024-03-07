LOAD DATA LOCAL INFILE 'C://ProgramData//MySQL//MySQL Server 8.0//Uploads//category.csv'
INTO TABLE data_directory.categories
FIELDS TERMINATED BY ',' ENCLOSED BY '"'
LINES TERMINATED BY '\n'
IGNORE 1 ROWS
(cid, category, @var)
SET root_category = NULLIF(@var, "null");

SET SQL_SAFE_UPDATES = 0;
UPDATE data_directory.categories
SET root_category = TRIM(TRAILING '\r' FROM root_category)
WHERE root_category LIKE '%\r';