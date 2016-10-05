CREATE TABLE shopping_carts (
  id int(11) NOT NULL AUTO_INCREMENT,
  user_id int(11) NOT NULL,
  shopping_cart_date DATE,
  PRIMARY KEY (id)
);