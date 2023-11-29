create schema gems;
use gems;


CREATE TABLE users (
    user_id INT PRIMARY KEY AUTO_INCREMENT,
    login VARCHAR(255),
    password VARCHAR(255),
    role VARCHAR(255)
);

CREATE TABLE gems (
    gem_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255),
    opacity DOUBLE,
    price DOUBLE,
    weight DOUBLE
);

CREATE TABLE necklace (
    necklace_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255),
    totalPrice DOUBLE,
    weight DOUBLE,
    user_id INT,
    FOREIGN KEY (user_id) REFERENCES users(user_id)
);

CREATE TABLE necklace_gem (
    necklace_id INT,
    gem_id INT,
    quantity INT,
    PRIMARY KEY (necklace_id, gem_id),
    FOREIGN KEY (necklace_id) REFERENCES necklace(necklace_id),
    FOREIGN KEY (gem_id) REFERENCES gems(gem_id)
);