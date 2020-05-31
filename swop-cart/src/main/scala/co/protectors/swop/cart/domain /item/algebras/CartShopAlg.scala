package co.protectors.swop.cart.domain .item.algebras

import java.util.UUID

import cats.data.OptionT
import co.protectors.swop.cart.domain.item.CartShop

trait CartShopAlg[F[_]] {
  def sendCart(cart: CartShop): F[CartShop]
  def getByID(id: UUID): OptionT[F,CartShop]
  def getAll: F[List[CartShop]]
}

object CartShopAlg {
  def apply[F[_]: CartShopAlg]: CartShopAlg[F] = implicitly
}
