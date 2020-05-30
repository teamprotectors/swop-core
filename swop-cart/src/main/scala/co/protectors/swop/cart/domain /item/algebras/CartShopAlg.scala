package co.protectors.swop.cart.domain .item.algebras

import java.util.UUID

import co.protectors.swop.cart.domain.item.CartShop

trait CartShopAlg[F[_]] {
  def sendCart(cart: CartShop): F[CartShop]
  def getByID(id: UUID): F[CartShop]
}

object CartShopAlg {
  def apply[F[_]: CartShopAlg]: CartShopAlg[F] = implicitly
}
