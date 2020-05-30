package co.protectors.user.domain.infraestructure.reprositorio.sql

import co.protectors.user.domain.infraestructure.reprositorio.row.UserRow
import doobie.postgres.implicits._
import doobie.implicits.toSqlInterpolator

object UserSQL {

  def get(id: String): doobie.Query0[UserRow] =
    sql"""SELECT AGE, ID, OCCUPATION, USERNAME, TYPEID, LOCATIONADRESS, LOCATIONCOUNTRY, LOCATIONCITY LOCATIONZIPCODE FROM USERSTORE WHERE ID = id """.query[UserRow]

  def insert (user:UserRow): doobie.Update0 =
   sql"""INSERT INTO USERSTORE(AGE, ID, OCCUPATION, USERNAME, TYPEID, LOCATIONADRESS, LOCATIONCOUNTRY, LOCATIONCITY LOCATIONZIPCODE)
         |VALUES (${user.age},${user.id},${user.occupation},${user.userName},${user.typeId}, ${user.location.adress}, ${user.location.country}, ${user.location.city}, ${user.location.zipCode} )""".update
}
