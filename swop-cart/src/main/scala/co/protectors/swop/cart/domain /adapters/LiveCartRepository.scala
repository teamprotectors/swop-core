package co.protectors.swop.cart.domain .adapters

import co.protectors.swop.cart.domain.item.CartShop
import co.protectors.swop.cart.domain.item.algebras.CartShopAlg

final class LiveItemRepository[F[_]: CartShopAlg] private {
  def create(item: CartShop): F[CartShop] = CartShopAlg[F].sendCart(item)
}

object LiveItemRepository {
  def apply[F[_]: CartShopAlg]: LiveItemRepository[F] = new LiveItemRepository[F]
}
