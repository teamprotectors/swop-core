package co.protectors.user.domain.user.algebras

import java.util.UUID

import co.protectors.user.domain.user.Location

trait LocationAlg [F[_]]{
  def getLocation(id:UUID):F[Location]
  def createLocation(location: Location):F[Location]
}
object LocationAlg{
  def apply[F[_]:LocationAlg]: LocationAlg [F]= implicitly
}