package co.protectors.user.domain.user

import java.util.UUID

final case class Location(
    id:UUID,
    adress: String,
    country: String,
    city: String,
    zipCode: String
)
