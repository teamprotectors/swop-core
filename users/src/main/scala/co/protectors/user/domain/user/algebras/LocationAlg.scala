package co.protectors.user.domain.user.algebras

import co.protectors.user.domain.user.Location

trait LocationAlg [F[_]]{
  def get():F[Location]
  def crate(location: Location):F[Location]
}
object LocationAlg{
  def apply[F[_]:LocationAlg]: LocationAlg [F]= implicitly
}