package co.protectors.product.catalog.domain.infrastructure.repository

import cats.effect.Sync
import cats.syntax.functor._
import co.protectors.product.catalog.domain.infrastructure.repository.rows.ItemRow
import co.protectors.product.catalog.domain.infrastructure.repository.sql.ItemSQL
import co.protectors.product.catalog.domain.item.Item
import co.protectors.product.catalog.domain.item.algebras.ItemAlg
import doobie.implicits._
import doobie.util.transactor.Transactor
import henkan.convert.Syntax._

class InMemoryRepository[F[_]: Sync](xa: Transactor[F]) extends ItemAlg[F] {
  override def getAll: F[List[Item]] = ItemSQL.getAll.transact(xa).map(row => row.map(_.to[Item]()))

  override def create(item: Item): F[Item] =
    ItemSQL.insert(item.to[ItemRow]()).run.transact(xa).map(_ => item)

}
