package com.workshop.converter

import cats.effect.IO
import com.workshop.model.{Order, Response}

import scala.concurrent.Future

trait NewOrderConverter[F[_]] {
  def convert(response: Response, order: Order): F[Order]
}

object NewOrderConverter {
  def apply[F[_]](implicit F: NewOrderConverter[F]): NewOrderConverter[F] = F

  implicit val impl: NewOrderConverter[Future] =
    new NewOrderConverter[Future] {
      def convert(response: Response, order: Order): Future[Order] =
        Future.successful(order.copy(label = Option(response.label)))
    }

  implicit val implIO: NewOrderConverter[IO] =
    new NewOrderConverter[IO] {
      def convert(response: Response, order: Order): IO[Order] =
        IO.pure(order.copy(label = Option(response.label)))
    }
}