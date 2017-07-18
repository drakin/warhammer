package model

import java.sql.Timestamp

import org.squeryl._


class GameDbObject extends KeyedEntity[Long] {
  val id: Long = 0L
  var timeOfLastUpdate = new Timestamp(System.currentTimeMillis)
}

object GameDB extends Schema {
  val players: Table[Player] = table[Player]("Players")

  override def drop: Unit = {
    Session.cleanupResources
    super.drop
  }
}

case class Card(name: String, imageURL: String)

case class Zone(name: String, var cards: List[Card])

object Placement extends Enumeration {
  type Placement = Value
  val Hand, Zone, Deck = Value
}

case class Player(nickname: String, password: String) extends GameDbObject with java.io.Serializable {
  def this() = this("anonymous", "")

}
