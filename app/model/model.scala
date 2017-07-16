package model


case class Card(name: String, imageURL: String)

case class Zone(name: String, var cards: List[Card])

object Placement extends Enumeration {
  val Hand, Zone, Deck = Value
}