package filters

import java.util
import net.logstash.logback.marker.Markers._
import org.slf4j.MDC
import play.api.Logger
import play.api.mvc.{RequestHeader, Result, Filter}
import scala.concurrent.Future


object AccessLoggingFilter extends Filter {

  implicit lazy val executionContext = play.api.libs.concurrent.Execution.defaultContext

  def apply(next: (RequestHeader) => Future[Result])(request: RequestHeader): Future[Result] = {
    val start = System.currentTimeMillis
    next(request) map { result =>
      val time = System.currentTimeMillis - start
      val props = new util.HashMap[String, Any] {
        put("domain", request.domain)
        put("method", request.method)
        put("path", request.path)
        put("status", result.header.status)
        put("time", start)
        put("duration", time)
      }
      Logger.logger.info(appendEntries(props), "Request Complete")
      result
    }
  }

}
