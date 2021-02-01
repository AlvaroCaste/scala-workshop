package exercise1.solutions

import java.util.UUID

/**
 * Exercise 1.1
 *
 * Let's imagine a simple event sourced application.  We want to define
 * some events that we can handle:
 *
 * - An user logs in
 * - A customer adds an item to the basket
 * - An user starts payment process
 * - Payment goes through correctly
 * - Payment process fails with timeout
 * - Payment process fails because of Insufficient funds
 *
 * Exercise1.2
 *
 * We know that all events for this system will have several fields:
 * - Event ID
 * - User ID
 *
 * Refactor your previous exercise to add those.
 */

object eventsourcedSolution1 {
  sealed trait Event

  case class UserLogIn(userId: UUID) extends Event
  case class AddItemToBasket(userId: UUID, itemId: UUID) extends Event
  case class UserIntentPay(userId: UUID) extends Event
  case class PaymentCorrect(userId: UUID, paymentReceipt: String) extends Event

  sealed trait PaymentFailure extends Event
  case class TimeoutFailure(userId: UUID, intentId: UUID) extends PaymentFailure
  case class InsufficientFundsFailure(userId: UUID, intentId: UUID) extends PaymentFailure
}