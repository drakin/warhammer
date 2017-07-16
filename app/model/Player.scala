package model

import anorm.SqlParser._
import anorm._
import play.api.db.DB
import play.api.Play.current

case class Player(nickname: String, password: String)

object Player {
  /**
    * Authenticate a User.
    */
  def authenticate(nickname: String, password: String): Option[Player] = {
    DB.withConnection { implicit connection =>
      SQL(
        """
         select * from Players where
         nickname = {nickname} and password = {password}
        """).on(
        'nickname -> nickname,
        'password -> password).as(Player.simple.singleOpt)
    }
  }

  def findById(id: String): Option[Player] = {
    DB.withConnection { implicit connection =>
      SQL("select * from Players where nickname = {nickname}").on(
        'nickname -> id).as(Player.simple.singleOpt)
    }
  }

  val simple: RowParser[Player] = {
    get[String]("Players.nickname") ~
      get[String]("Players.password") map {
      case nickname ~ password => Player(nickname, password)
    }
  }

  def findAll: Seq[Player] = {
    DB.withConnection { implicit connection =>
      SQL("select * from Players").as(Player.simple *)
    }
  }

  def create(player: Player): Player = {
    DB.withConnection { implicit connection =>
      SQL(
        """
          insert into Players values (
            {nickname}, {password}
          )
        """).on(
        'nickname -> player.nickname,
        'password -> player.password).executeUpdate()

      player

    }
  }

}
