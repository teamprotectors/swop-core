package co.protectors.user.domain.user

final case class User(
    age: Int,
    id: String,
    occupation: String,
    userName: String,
    typeId: String,
    location: Location
)
