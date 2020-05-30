package co.protectors.product.catalog.domain.item

import java.util.UUID

final case class Item(
    id: UUID,
    name: String,
    category: String
)
