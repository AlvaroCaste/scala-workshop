package exercise1

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

sealed trait Event {
  def id: UUID
  def userId: UUID
}

case class UserLogIn(id: UUID, userId: UUID) extends Event
case class AddItemToBasket(id: UUID, userId: UUID, itemId: UUID) extends Event
case class UserIntentPay(id: UUID, userId: UUID) extends Event
case class PaymentCorrect(id: UUID, userId: UUID, paymentReceipt: String) extends Event

sealed trait PaymentFailure extends Event
case class TimeoutFailure(id: UUID, userId: UUID, intentId: UUID) extends PaymentFailure
case class InsufficientFundsFailure(id: UUID, userId: UUID, intentId: UUID) extends PaymentFailure