package com.workshop.service

import cats.effect.IO
import com.workshop.converter.NewOrderConverter
import com.workshop.model.{Order, Response}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

trait NewOrderService[F[_]] {
  def newOrder(order: Order): F[Order]
}

object NewOrderService {
  def apply[F[_]](implicit F: NewOrderService[F]): NewOrderService[F] = F

  implicit def impl(implicit NOC: NewOrderConverter[Future]): NewOrderService[Future] =
    new NewOrderService[Future] {
      def newOrder(order: Order): Future[Order] =
        for {
          r <- Future.successful(Response("label"))
          newOrder <- NOC.convert(r, order)
        } yield newOrder
    }

  implicit def implIO(implicit NOC: NewOrderConverter[IO]): NewOrderService[IO] =
    new NewOrderService[IO] {
      def newOrder(order: Order): IO[Order] =
        for {
          r <- IO.pure(Response("label"))
          newOrder <- NOC.convert(r, order)
        } yield newOrder
    }
}