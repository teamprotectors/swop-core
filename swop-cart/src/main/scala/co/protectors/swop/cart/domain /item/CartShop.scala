package co.protectors.swop.cart.domain .item


import java.util.UUID

case class CartShop (id: UUID, idUser: String ,items: List[Item])
