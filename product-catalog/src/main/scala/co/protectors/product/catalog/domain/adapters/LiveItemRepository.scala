package co.protectors.product.catalog.domain.adapters

import co.protectors.product.catalog.domain.item.Item
import co.protectors.product.catalog.domain.item.algebras.ItemAlg

final class LiveItemRepository[F[_]: ItemAlg] private {

  def getAll: F[List[Item]]       = ItemAlg[F].getAll
  def create(item: Item): F[Item] = ItemAlg[F].create(item)
}

object LiveItemRepository {
  def apply[F[_]: ItemAlg]: LiveItemRepository[F] = new LiveItemRepository[F]

}
