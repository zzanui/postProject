SELECT * FROM notepg.article;
delete from article where id =1;

drop table article;

DELIMITER $$
CREATE PROCEDURE insertData()
BEGIN
    DECLARE i INT DEFAULT 1;
    WHILE i <= 1000 DO
    

        INSERT INTO article (id,content,nickname,timestamp,title,username,view)
        VALUES (i, CONCAT(CHAR(RAND() * 24 + 97), CHAR(RAND() * 24 + 97), CHAR(RAND() * 24 + 97),
                           CHAR(RAND() * 24 + 97), CHAR(RAND() * 24 + 97), CHAR(RAND() * 24 + 97),
                           CHAR(RAND() * 24 + 97), CHAR(RAND() * 24 + 97), CHAR(RAND() * 24 + 97), CHAR(RAND() * 24 + 97)),
        
					CONCAT(CHAR(RAND() * 24 + 97), CHAR(RAND() * 24 + 97), CHAR(RAND() * 24 + 97),
									   CHAR(RAND() * 24 + 97), CHAR(RAND() * 24 + 97), CHAR(RAND() * 24 + 97),
									   CHAR(RAND() * 24 + 97), CHAR(RAND() * 24 + 97), CHAR(RAND() * 24 + 97), CHAR(RAND() * 24 + 97)),
					FROM_UNIXTIME(FLOOR(unix_timestamp('2022-02-01 00:00:00') +
                                        (RAND() * (unix_timestamp('2022-02-28 00:00:00') -
                                                   unix_timestamp('2022-02-01 00:00:00'))))),
					CONCAT(CHAR(RAND() * 24 + 97), CHAR(RAND() * 24 + 97), CHAR(RAND() * 24 + 97),
									   CHAR(RAND() * 24 + 97), CHAR(RAND() * 24 + 97), CHAR(RAND() * 24 + 97),
									   CHAR(RAND() * 24 + 97), CHAR(RAND() * 24 + 97), CHAR(RAND() * 24 + 97), CHAR(RAND() * 24 + 97)),
                    CONCAT(CHAR(RAND() * 24 + 97), CHAR(RAND() * 24 + 97), CHAR(RAND() * 24 + 97),
									   CHAR(RAND() * 24 + 97), CHAR(RAND() * 24 + 97), CHAR(RAND() * 24 + 97),
									   CHAR(RAND() * 24 + 97), CHAR(RAND() * 24 + 97), CHAR(RAND() * 24 + 97), CHAR(RAND() * 24 + 97)),
                                       0
        );
        SET i = i + 1;
    END WHILE;
END$$
DELIMITER ;

CALL insertData();
