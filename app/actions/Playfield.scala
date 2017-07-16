package actions

import model._

import scala.util.Random

case class Playfield(
                      resources: Int,
                      owner: Player,
                      var deck: List[Card],
                      var hand: List[Card],
                      var zones: List[Zone]
                    ) {

  def shuffleDeck(): Unit = {
    if (deck.size <= 1) return
    var shuffleResult = Random.shuffle(deck)
    while (shuffleResult == deck) shuffleResult = Random.shuffle(deck)
    deck = shuffleResult
  }

  def moveCard(from: Placement.Value, to: Placement.Value): Unit = {
    from match {
      case Placement.Deck =>
      case Placement.Hand =>
      case Placement.Zone =>
    }
  }

  def moveCardFromHand(to: Placement.Value, cardIndex: Int, zoneIndex: Int): Unit = {
    to match {
      case Placement.Deck =>
        deck = deck :+ hand(cardIndex)
      case Placement.Hand =>
        throw InvalidZoneException();
      case Placement.Zone =>
        zones(zoneIndex).cards = zones(zoneIndex).cards :+ hand(cardIndex)
    }
    hand = hand.take(cardIndex) ++ hand.drop(cardIndex + 1)
  }

}
