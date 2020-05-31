package co.protectors.swop.cart.domain .item


import java.util.UUID

case class CartShop (id: UUID, idUser: UUID ,items: List[Item])
