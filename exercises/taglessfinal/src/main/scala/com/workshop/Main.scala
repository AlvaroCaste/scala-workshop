package com.workshop

import com.workshop.model.Order
import com.workshop.service.{NewOrderService, PublishEventService}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

object Main extends App {
  for {
    newOrder <- NewOrderService[Future].newOrder(Order("id"))
    _ <- PublishEventService[Future].publish(newOrder)
  } yield ()
}