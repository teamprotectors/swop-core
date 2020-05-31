package co.protectors.user.domain.infraestructure.reprositorio.sql

import java.util.UUID

import co.protectors.user.domain.infraestructure.reprositorio.row.LocationRow
import doobie.implicits.toSqlInterpolator
import doobie.postgres.implicits._

object LocationSQL {

  def get(id: UUID): doobie.Query0[LocationRow] =
    sql"""SELECT ID, ADRESS, COUNTRY, CITY, ZIPCODE FROM LOCATION WHERE ID = $id """.query[LocationRow]

  def insert (location:LocationRow): doobie.Update0 =
   sql"""INSERT INTO LOCATION( ID, ADRESS, COUNTRY, CITY, ZIPCODE)
         VALUES (${location.id}, ${location.adress}, ${location.country}, ${location.city}, ${location.zipCode} )""".update
}
