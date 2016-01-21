import filters.AccessLoggingFilter
import play.api.GlobalSettings
import play.api.mvc.WithFilters

object Global extends WithFilters(AccessLoggingFilter) with GlobalSettings