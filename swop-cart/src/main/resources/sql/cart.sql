CREATE TABLE IF NOT EXISTS CART_SHOP (idCart UUID, idUser varchar (100), PRIMARY KEY (idCart));

CREATE TABLE IF NOT EXISTS ITEM_CART (
  idItem UUID,
  name  varchar (100),
  quantity  Int,
  isInterchangeable boolean ,
  requestDate TIMESTAMP,
  showDate TIMESTAMP,
  idCart UUID
);

CREATE TABLE IF NOT EXISTS ITEM_WISH (
  idItemWish UUID,
  name  varchar (100),
  category  varchar (100),
  itemId UUID
);
