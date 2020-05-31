package co.protectors.user.domain.infraestructure.reprositorio.row

import java.util.UUID

case class UserRow(
    age: Int,
    id: String,
    occupation: String,
    userName: String,
    typeId: String,
    idLocation: UUID
)
