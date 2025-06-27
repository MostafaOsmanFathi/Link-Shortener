CREATE DATABASE IF NOT EXISTS linkshortenerdb;

USE linkshortenerdb;

CREATE TABLE IF NOT EXISTS short_links (
                                           id INT AUTO_INCREMENT PRIMARY KEY,
                                           original_url VARCHAR(2048) NOT NULL,
                                           short_link_code VARCHAR(100) NOT NULL UNIQUE,
                                           short_link_url VARCHAR(2048) NOT NULL
);
