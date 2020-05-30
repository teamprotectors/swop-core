package co.protectors.product.catalog.domain.infrastructure.repository.sql

import co.protectors.product.catalog.domain.infrastructure.repository.rows.ItemRow
import doobie.postgres.implicits._
import doobie.implicits.toSqlInterpolator

object ItemSQL {

  def getAll: doobie.ConnectionIO[List[ItemRow]] =
    sql"""SELECT ID, NAME, CATEGORY FROM ITEM""".query[ItemRow].to[List]

  def insert(item: ItemRow): doobie.Update0 =
    sql"""INSERT INTO ITEM (ID, NAME, CATEGORY) VALUES(${item.id}, ${item.name}, ${item.category})""".update
}
