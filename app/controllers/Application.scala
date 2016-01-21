package controllers

import play.api.Logger
import play.api.mvc._


object Application extends Controller {

  def index = MyAction {
    Logger.logger.info("Returning index")
    Ok("index")
  }
}
