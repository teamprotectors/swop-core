package co.protectors.product.catalog.domain.infrastructure.repository.rows

import java.util.UUID

case class ItemRow(
    id: UUID,
    name: String,
    category: String
)
