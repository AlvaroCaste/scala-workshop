package com.workshop.service

import com.workshop.model.{Order, Response}
import com.workshop.converter.NewOrderConverter

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

trait NewOrderService[F[_]] {
  def newOrder(order: Order): F[Order]
}

object NewOrderService {
  implicit def impl(implicit NOC: NewOrderConverter[Future]): NewOrderService[Future] =
    new NewOrderService[Future] {
      def newOrder(order: Order): Future[Order] =
        for {
          r <- Future.successful(Response("label"))
          newOrder <- NOC.convert(r, order)
        } yield newOrder
    }
}