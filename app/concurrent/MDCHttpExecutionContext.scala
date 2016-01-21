package concurrent

import org.slf4j.MDC
import scala.concurrent.{ExecutionContextExecutor, ExecutionContext}


object MDCHttpExecutionContext {

  def fromThread(delegate: ExecutionContext): ExecutionContextExecutor =
    new MDCHttpExecutionContext(MDC.getCopyOfContextMap, delegate)

}

class MDCHttpExecutionContext(mdcContext: java.util.Map[_, _], delegate: ExecutionContext) extends ExecutionContextExecutor {

  def execute(runnable: Runnable) = delegate.execute(new Runnable {
    def run() {
      val oldMDCContext = MDC.getCopyOfContextMap
      setContextMap(mdcContext)
      try {
        runnable.run()
      } finally {
        setContextMap(oldMDCContext)
      }
    }
  })

  private[this] def setContextMap(context: java.util.Map[_, _]) {
    if (context == null) {
      MDC.clear()
    } else {
      MDC.setContextMap(context)
    }
  }

  def reportFailure(t: Throwable) = delegate.reportFailure(t)
}