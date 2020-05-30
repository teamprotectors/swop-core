package co.protectors.product.catalog.domain.item.algebras

import co.protectors.product.catalog.domain.item.Item

trait ItemAlg[F[_]] {
  def getAll: F[List[Item]]
  def create(item: Item): F[Item]
}

object ItemAlg {

  def apply[F[_]: ItemAlg]: ItemAlg[F] = implicitly
}
