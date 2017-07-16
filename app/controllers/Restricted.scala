package controllers

import model.Player
import play.api.mvc.Controller
import views.html

object Restricted extends Controller with Secured {

  /**
    * Display restricted area only if user is logged in.
    */
  def index = IsAuthenticated { username =>
    _ =>
      Player.findById(username).map { user =>
        Ok(
          html.restricted(user)
        )
      }.getOrElse(Forbidden)
  }

}