package co.protectors.swop.cart.domain .adapters

import cats.effect.Effect
import co.protectors.swop.cart.domain.infrastructure.repository.InMemoryRepository
import co.protectors.swop.cart.domain.item.algebras.CartShopAlg
import doobie.util.transactor.Transactor


trait Repository[F[_]] {
  def itemRepository: CartShopAlg[F]
}

final class LiveItemRepository[F[_]](repo: CartShopAlg[F]) extends Repository[F]  {
  override def itemRepository: CartShopAlg[F] =  repo
}

object LiveItemRepository {
  def apply[F[_]: Effect](transactor : Transactor[F]): LiveItemRepository[F] = new LiveItemRepository[F](InMemoryRepository[F](transactor))
}
