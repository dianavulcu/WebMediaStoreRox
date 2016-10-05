CREATE TABLE cart_items (
  id int(11) NOT NULL AUTO_INCREMENT,
  shoping_cart_id int(11) NOT NULL,
  media_id int(11) NOT NULL,
  quantity int(11) NOT NULL,
  PRIMARY KEY (id)
);