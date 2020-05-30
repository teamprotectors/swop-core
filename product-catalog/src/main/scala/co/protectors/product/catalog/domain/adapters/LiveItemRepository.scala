package co.protectors.product.catalog.domain.adapters

import cats.effect.Effect
import co.protectors.product.catalog.domain.infrastructure.repository.InMemoryRepository
import co.protectors.product.catalog.domain.item.algebras.ItemAlg
import doobie.util.transactor.Transactor

trait Repository[F[_]] {
  def itemRepository: ItemAlg[F]
}

final class LiveItemRepository[F[_]] private (repo: ItemAlg[F]) extends Repository[F] {
  override def itemRepository: ItemAlg[F] = repo
}

object LiveItemRepository {
  def apply[F[_]: Effect](xa: Transactor[F]): LiveItemRepository[F] =
    new LiveItemRepository[F](InMemoryRepository[F](xa))

}
