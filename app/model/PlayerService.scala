package model

import org.squeryl.PrimitiveTypeMode._

object PlayerService {

  /**
    * Authenticate a User.
    */
  def authenticate(nickname: String, password: String): Option[Player] = {
    inTransaction {
      from(GameDB.players)(player => where(player.nickname === nickname and player.password === password) select player).headOption
    }
  }

  def findById(id: Long): Option[Player] = {
    inTransaction {
      GameDB.players.lookup(id)
    }
  }

  def findByUsername(nickname: String): Option[Player] = {
    inTransaction {
      from(GameDB.players)(player => where(player.nickname === nickname) select player).headOption
    }
  }

  def findAll: Seq[Player] = {
    inTransaction {
      from(GameDB.players)(player => select(player)).toSeq
    }
  }

  def create(player: Player): Player = {
    inTransaction {
      GameDB.players.insert(player)
    }
  }

}
