package co.protectors.swop.cart.domain .infrastructure.repository

import java.util.UUID

import cats.data.OptionT
import cats.effect.Effect
import cats.instances.list._
import cats.instances.option._
import cats.syntax.apply._
import cats.syntax.flatMap._
import cats.syntax.functor._
import cats.syntax.traverse._
import co.protectors.swop.cart.domain.infrastructure.repository.rows.{CartShopRow, ItemRow, ItemWishRow}
import co.protectors.swop.cart.domain.infrastructure.repository.sql.{CartShopSQL, ItemSQL, ItemWishSQL}
import co.protectors.swop.cart.domain.item.CartShop
import co.protectors.swop.cart.domain.item.algebras.CartShopAlg
import doobie.implicits._
import doobie.util.transactor.Transactor

class InMemoryRepository [F[_]: Effect](xa: Transactor[F]) extends CartShopAlg[F] {

  override def sendCart(cart: CartShop): F[CartShop] =
    (CartShopSQL.insert(CartShopRow(cart.id, cart.idUser)).run *>
      saveItems(ItemRow(cart).toList) *>
      saveItemWish(cart.items.flatMap(ItemWishRow(_))))
      .transact(xa)
      .as(cart)


  override def getByID(id: UUID): OptionT[F, CartShop] =
    OptionT(
      CartShopSQL.getById(id).option.transact(xa).flatMap(
        mCart => mCart.traverse{
          cart =>
            ItemSQL.getByIdCart(id).transact(xa).flatMap {
              item =>
                item.flatTraverse(itemResult => ItemWishSQL.getByIdItem(itemResult.idItem))
                  .transact(xa)
                    .map( wish => CartShop(id,cart.idUser,ItemRow.toDomainList(item,wish).toList))
            }
        })
    )

  override def getAll: F[List[CartShop]] = ???

  def saveItems(items: List[ItemRow]): doobie.ConnectionIO[List[Int]] =
    items.traverse{ itemRow =>
      ItemSQL.insert(itemRow).run
    }

  def saveItemWish (items: List[ItemWishRow]): doobie.ConnectionIO[List[Int]] =
    items.traverse{ itemRow =>
      ItemWishSQL.insert(itemRow).run
    }


}

object InMemoryRepository {
  def apply[F[_] : Effect](xa: Transactor[F]): InMemoryRepository[F] = new InMemoryRepository(xa)
}


