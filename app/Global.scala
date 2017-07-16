import model.Player
import play.api.{Application, GlobalSettings}

object Global extends GlobalSettings{

  override def onStart(app: Application) {
    InitialData.insert()
  }
}

/**
  * Initialize test data
  */
object InitialData {

  def insert(): Unit = {
    if (Player.findAll.isEmpty) {
      Seq(
        Player("user1", "password1"),
        Player("user2", "password2"),
        Player("user3", "password3")
      ).foreach(Player.create)
    }
  }

}