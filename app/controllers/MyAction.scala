package controllers

import play.api.mvc.{Result, Action, Request, ActionBuilder}
import scala.concurrent.{Future, ExecutionContext}


object MyAction extends ActionBuilder[Request] {
  def invokeBlock[A](request: Request[A], block: (Request[A]) => Future[Result]) = {
    block(request)
  }
  override def executionContext: ExecutionContext = concurrent.Execution.defaultContext
}