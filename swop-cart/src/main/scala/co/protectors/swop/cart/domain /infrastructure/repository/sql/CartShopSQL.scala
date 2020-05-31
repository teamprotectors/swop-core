package co.protectors.swop.cart.domain .infrastructure.repository.sql

import java.util.UUID

import co.protectors.swop.cart.domain.infrastructure.repository.rows._
import doobie.implicits.toSqlInterpolator
import doobie.postgres.implicits._
import doobie.util.log.LogHandler.jdkLogHandler

object CartShopSQL {
  def insert(cartShop: CartShopRow): doobie.Update0 =
    sql"""INSERT INTO CART_SHOP (idCart, idUser)
         | VALUES(${cartShop.id},${cartShop.idUser}) ON CONFLICT (idCart) DO NOTHING""".update

  def getAll: doobie.ConnectionIO[List[CartShopRow]] =
    sql"""SELECT idCart,idUser from CART_SHOP""".stripMargin.query[CartShopRow].to[List]

  def getById(id: UUID): doobie.Query0[CartShopRow] =
    sql"""SELECT idCart,idUser from CART_SHOP where idCart=$id""".queryWithLogHandler
      [CartShopRow](jdkLogHandler)

  def getByUserID(id: UUID): doobie.Query0[CartShopRow] =
    sql"""SELECT idCart,idUser from CART_SHOP where idUser=$id""".queryWithLogHandler
      [CartShopRow](jdkLogHandler)

}