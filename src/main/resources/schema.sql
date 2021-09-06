

DROP TABLE IF EXISTS Url_storage;

CREATE TABLE Url_storage (
  id int AUTO_INCREMENT  PRIMARY KEY,
  urlEntity VARCHAR(200) unique,
  url VARCHAR(200),
  accessed_count int DEFAULT NULL,
  created_date timestamp DEFAULT NULL,
  updated_date timestamp DEFAULT NULL
);
