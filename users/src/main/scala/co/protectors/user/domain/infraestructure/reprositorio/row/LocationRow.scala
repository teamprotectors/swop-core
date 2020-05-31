package co.protectors.user.domain.infraestructure.reprositorio.row

import java.util.UUID

case class LocationRow(
                        id:UUID,
                           adress: String,
                           country: String,
                           city: String,
                           zipCode: String
)
