package com.workshop.example1

import com.workshop.example2.service.{NewOrderService, PublishEventService}
import com.workshop.model.Order

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

object Main extends App {
  for {
    newOrder <- NewOrderService[Future].newOrder(Order("id"))
    _ <- PublishEventService[Future].publish(newOrder)  } yield ()
}