package exercise4.problems

import java.util.Random

object Exercise4 {
  val random = new Random

  val maybeString: String =
    if (random.nextBoolean) {
      "it's not null"
    } else {
      null
    }

  /**
   * reimplement this using Option
   */
  def useOption: String =
    if (maybeString == null) {
      null
    } else {
      maybeString
    }

}