# Logging Example
This project attempts to demonstrate using logback's MDC for maintaining request logging context data. This is tricky since the MDC's context is a thread and in the Play framework, callbacks throughout a requests lifetime may be serviced by more than one thread.

## Output
Here is some log output from this application after hitting the http://localhost:9000 endpoint a few times. Notice that not all log entries have the `user_id` field.

```json
{"@timestamp":"2016-01-21T00:06:39.520-08:00","@version":1,"message":"Returning index","logger_name":"application","thread_name":"play-akka.actor.default-dispatcher-4","level":"INFO","level_value":20000,"HOSTNAME":"Johns-MacBook-Pro.local","application.home":"/Users/jlynn/projects/logging-example","request_id":"c64951b4-1d14-41dd-bdd4-ad153ce5ef04","user_id":"5"}
{"@timestamp":"2016-01-21T00:06:39.520-08:00","@version":1,"message":"Request Complete","logger_name":"application","thread_name":"play-akka.actor.default-dispatcher-6","level":"INFO","level_value":20000,"HOSTNAME":"Johns-MacBook-Pro.local","application.home":"/Users/jlynn/projects/logging-example","request_id":"c64951b4-1d14-41dd-bdd4-ad153ce5ef04","duration":1,"path":"/","method":"GET","domain":"localhost","time":1453363599519,"status":200}
{"@timestamp":"2016-01-21T00:06:40.469-08:00","@version":1,"message":"Returning index","logger_name":"application","thread_name":"play-akka.actor.default-dispatcher-5","level":"INFO","level_value":20000,"HOSTNAME":"Johns-MacBook-Pro.local","application.home":"/Users/jlynn/projects/logging-example","request_id":"86f0adef-d7dd-4d91-8cfc-1e441d21b0a0","user_id":"0"}
{"@timestamp":"2016-01-21T00:06:40.469-08:00","@version":1,"message":"Request Complete","logger_name":"application","thread_name":"play-akka.actor.default-dispatcher-7","level":"INFO","level_value":20000,"HOSTNAME":"Johns-MacBook-Pro.local","application.home":"/Users/jlynn/projects/logging-example","request_id":"86f0adef-d7dd-4d91-8cfc-1e441d21b0a0","duration":1,"path":"/","method":"GET","domain":"localhost","time":1453363600468,"status":200}
```

`user_id` is set in an ActionBuilder. MDC values set in the filter (such as `request_id`) are available in logging calls made in Actions. However, MDC values set in an Action are not available to the Filter after the Action has completed. I am hoping to see `user_id` values in the "Request Complete" log entries.
