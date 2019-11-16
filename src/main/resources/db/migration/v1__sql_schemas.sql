CREATE TABLE review (
  id INT AUTO_INCREMENT PRIMARY KEY,
  title VARCHAR(255) NOT NULL,
  body VARCHAR(10000) NULL,
  created TIMESTAMP DEFAULT CURRENT_TIMESTAMP NULL,
  product_id INT NOT NULL
);

CREATE TABLE product (
  id INT AUTO_INCREMENT primary key,
  name VARCHAR(255) NOT NULL,
  description VARCHAR(100) NOT NULL
);

CREATE TABLE comment (
  id INT AUTO_INCREMENT primary key,
  title VARCHAR(255) NOT NULL,
  body VARCHAR(10000) NULL,
  created TIMESTAMP DEFAULT CURRENT_TIMESTAMP NULL,
  review_id INT NOT NULL
);


ALTER TABLE review ADD CONSTRAINT review_product_id_fk FOREIGN KEY (product_id) REFERENCES product (id);

ALTER TABLE comment ADD CONSTRAINT comment_review_id_fk FOREIGN KEY (review_id) REFERENCES review (id);