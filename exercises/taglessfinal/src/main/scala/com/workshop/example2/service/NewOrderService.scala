package com.workshop.example2.service


import com.workshop.example2.converter.NewOrderConverter
import com.workshop.model.{Order, Response}

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
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
}