package co.protectors.user.domain.infraestructure.reprositorio.row

import co.protectors.user.domain.user.Location

case class UserRow(
    age: Int,
    id: String,
    occupation: String,
    userName: String,
    typeId: String,
    location: Location
)
