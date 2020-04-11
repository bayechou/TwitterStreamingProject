package fr.twitter.configuration

import org.apache.log4j.{ Level, Logger }
import scala.io.Source
import fr.twitter.util.Constants
import scala.util.control.Exception.Catch

object AuthenticatedUser {

  def setupLoggingLevel() = {
    Logger.getRootLogger().setLevel(Level.WARN)
  }

  def setupAuthenticateUserTwitter() = {
    val dir = System.getProperty("user.dir")
    try {
      for (line <- Source.fromFile(dir + Constants.FILE_CREDENTIALS).getLines()) {
        val items = line.split(" ")
        if (items.length == 2) {
          System.setProperty(Constants.PROP_OAUTH + items(0), items(1))
        }
      }
    } catch {
      case e: Exception => println(dir + Constants.FILE_CREDENTIALS + ": not found")
    }
  }
}
