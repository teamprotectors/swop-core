package co.protectors.swop.cart.domain .item.algebras

import co.protectors.swop.cart.domain.item.CartShop

trait CartShopAlg[F[_]] {
  def sendCart(cart: CartShop): F[CartShop]
}

object CartShopAlg {
  def apply[F[_]: CartShopAlg]: CartShopAlg[F] = implicitly
}
