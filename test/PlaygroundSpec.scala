import actions.Playfield
import model.{Card, Placement, Player, Zone}
import org.junit.runner._
import org.specs2.mutable._
import org.specs2.runner.JUnitRunner
import play.api.Logger

/**
  * Add your spec here.
  * You can mock out a whole application including requests, plugins etc.
  * For more information, consult the wiki.
  */
@RunWith(classOf[JUnitRunner])
class PlaygroundSpec extends Specification {

  val logger: Logger = Logger(this.getClass)

  "Playground" should {

    "shuffle deck with two cards" in {
      val initCardList = List(Card("name", "url2"), Card("name2", "url2"))
      val playfield = Playfield(0, Player("nick","password"), initCardList, List(), List())
      playfield.shuffleDeck()
      playfield.deck shouldNotEqual initCardList
    }
    "shuffle deck with one card" in {
      val initCardList = List(Card("name", "url2"))
      val playfield = Playfield(0, Player("nick","password"), initCardList, List(), List())
      playfield.shuffleDeck()
      playfield.deck shouldEqual initCardList
    }
    "shuffle deck with without cards" in {
      val initCardList = List()
      val playfield = Playfield(0, Player("nick","password"), initCardList, List(), List())
      playfield.shuffleDeck()
      playfield.deck shouldEqual initCardList
    }

  }

  "Cards moving" should {
    val initDeck = List(
      Card("name", "url"),
      Card("name2", "url2")
    )
    val initHand = List(
      Card("name3", "url3")
    )
    val initZoneCards = List()
    val playfield = Playfield(0,
      Player("nick","password"),
      initDeck,
      initHand,
      List(
        Zone("zone1", initZoneCards)
      )
    )
//    step {
//      logger.info("Before")
//    }

    "support moving from hand to zone" in {
      val moveCardIndex = 0
      val zoneIndex = 0
      playfield.moveCardFromHand(Placement.Zone, moveCardIndex, zoneIndex)
      initHand shouldNotEqual playfield.hand
      initHand.size - 1 shouldEqual playfield.hand.size
      initZoneCards shouldNotEqual playfield.zones(zoneIndex).cards
      initZoneCards.size + 1 shouldEqual playfield.zones(zoneIndex).cards.size
      playfield.zones(zoneIndex).cards.last == initHand(moveCardIndex)
    }
//    step {
//      logger.info("After")
//    }
  }
}
