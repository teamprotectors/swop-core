package co.protectors.swop.cart.domain .item

abstract class errors(error : String) extends Exception(error)

case class CartNotFound(error: String) extends errors(error)
