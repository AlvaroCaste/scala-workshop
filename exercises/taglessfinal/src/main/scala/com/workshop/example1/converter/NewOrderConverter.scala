package com.workshop.example1.converter

import com.workshop.model.{Order, Response}

import scala.concurrent.Future

trait NewOrderConverter[F[_]] {
  def convert(response: Response, order: Order): F[Order]
}

object NewOrderConverter {
  implicit val impl: NewOrderConverter[Future] =
    new NewOrderConverter[Future] {
      def convert(response: Response, order: Order): Future[Order] =
        Future.successful(order.copy(label = Option(response.label)))
    }
}