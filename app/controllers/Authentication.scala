package controllers

import play.api._
import play.api.mvc._
import play.api.data._
import play.api.data.Forms._
import views._
import model.Player

object Authentication extends Controller{

  val loginForm = Form(
    tuple(
      "nickname" -> text,
      "password" -> text
    ) verifying ("Invalid username or password", result => result match {
      case (nickname, password) => Player.authenticate(nickname, password).isDefined
    })
  )

  def login() = Action { implicit request =>
    Ok(html.login(loginForm))
  }

  /**
    * Handle login form submission.
    */
  def authenticate = Action { implicit request =>
    loginForm.bindFromRequest.fold(
      formWithErrors => BadRequest(html.login(formWithErrors)),
      user => Redirect(routes.Restricted.index()).withSession("username" -> user._1)
    )
  }

  /**
    * Logout and clean the session.
    */
  def logout = Action {
    Redirect(routes.Authentication.login()).withNewSession.flashing(
      "success" -> "You've been logged out"
    )
  }
}