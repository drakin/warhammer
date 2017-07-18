import model.{GameDB, Player, PlayerService}
import org.squeryl.PrimitiveTypeMode.inTransaction
import org.squeryl.adapters.{H2Adapter, PostgreSqlAdapter}
import org.squeryl.internals.DatabaseAdapter
import org.squeryl.{Session, SessionFactory}
import play.api.db.DB
import play.api.{Application, GlobalSettings}

object Global extends GlobalSettings {

  override def onStart(app: Application) {
    startDatabaseSession(app: Application)
    inTransaction {
      GameDB.create
    }
    InitialData.insert()
  }

  def startDatabaseSession(app: Application): Unit = {
    SessionFactory.concreteFactory = app.configuration.getString("db.default.driver") match {
      case Some("org.h2.Driver") => Some(() => getSession(new H2Adapter, app))
      case Some("org.postgresql.Driver") => Some(() => getSession(new PostgreSqlAdapter, app))
      case _ => sys.error("Database driver must be either org.h2.Driver or org.postgresql.Driver")
    }

    def getSession(adapter: DatabaseAdapter, app: Application) = Session.create(DB.getConnection()(app), adapter)
  }

  /**
    * Initialize test data
    */
  object InitialData {

    def insert(): Unit = {
      if (PlayerService.findAll.isEmpty) {
        Seq(
          Player("user1", "password1"),
          Player("user2", "password2"),
          Player("user3", "password3")
        ).foreach(PlayerService.create)
      }
    }

  }

}