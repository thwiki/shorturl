CREATE TABLE IF NOT EXISTS short_url
(
   id BIGINT (20) NOT NULL AUTO_INCREMENT,
   gmt_create datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
   gmt_modified datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP,
   short_path varchar (16) NOT NULL,
   real_url varchar (2048) NOT NULL,
   PRIMARY KEY (id),
   UNIQUE KEY (short_path)
)
ENGINE= InnoDB DEFAULT CHARSET = utf8 COLLATE=utf8_bin;