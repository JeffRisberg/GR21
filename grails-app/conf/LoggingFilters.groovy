import java.text.DateFormat
import java.text.SimpleDateFormat


/**
 * The <i>LoggingFilters</i> class causes recording of an Http request organized by Grails
 * into a controller and action into a LogEntry record whose content is configured by a
 * loggingInformation specification on the controller classes.
 *
 * @author Jeff Risberg, Spoorthy Ananthaiah
 * @since 10/16/10
 */
class LoggingFilters {

  def logEntryService

  def filters = {
    all(controller:'*', action:'*') {
      before = {
        Date now = new Date()
        DateFormat dateF = new SimpleDateFormat("MM/dd/yy HH:mm:ss")

        // Look up the controller via the controllerName, then fetch the logging instructions
        String firstLetter = controllerName?.substring(0, 1)?.toUpperCase()
        String restLetters = controllerName?.substring(1)
        String controllerFullName = "com.vivecoach.${firstLetter}${restLetters}Controller"
        println "---> " + dateF.format(now) + " " + controllerFullName + " " + actionName

      }
      after = {
        Date now = new Date()
        DateFormat dateF = new SimpleDateFormat("MM/dd/yy HH:mm:ss")

        String firstLetter = controllerName?.substring(0, 1)?.toUpperCase()
        String restLetters = controllerName?.substring(1)
        String controllerFullName = "com.vivecoach.${firstLetter}${restLetters}Controller"
        println "<--- " + dateF.format(now) + " " + controllerFullName + " " + actionName
      }
      afterView = {
      }
    }
  }
}
