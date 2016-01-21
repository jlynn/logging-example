package controllers

import org.slf4j.MDC
import play.api.mvc.{Result, Action, Request, ActionBuilder}
import scala.concurrent.{Future, ExecutionContext}
import scala.util.Random


object MyAction extends ActionBuilder[Request] {
  def invokeBlock[A](request: Request[A], block: (Request[A]) => Future[Result]) = {
    MDC.put("user_id", Random.nextInt(10).toString)
    block(request)
  }
  override def executionContext: ExecutionContext = concurrent.Execution.defaultContext
}