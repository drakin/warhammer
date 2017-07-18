package controllers

import model.PlayerService
import play.api.mvc.Controller
import views.html

object Restricted extends Controller with Secured {

  /**
    * Display restricted area only if user is logged in.
    */
  def index = IsAuthenticated { username =>
    _ =>
      PlayerService.findByUsername(username) match {
        case Some(x) => Ok(html.restricted(x))
        case None => Forbidden
      }
  }

}