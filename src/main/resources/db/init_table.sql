CREATE TABLE IF NOT EXISTS short_url
(
   id BIGINT (20) NOT NULL AUTO_INCREMENT,
   gmt_create datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
   gmt_modified datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
   short_path varchar (16) NOT NULL,
   real_url varchar (2048) NOT NULL,
   expire_time BIGINT (20) DEFAULT NULL,
   PRIMARY KEY (id),
   UNIQUE KEY 'uk_shortpath' (short_path),
   KEY 'idx_expiretime' (expire_time)
)
ENGINE= InnoDB DEFAULT CHARSET = utf8 COLLATE=utf8_bin;
-----