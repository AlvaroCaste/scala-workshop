package com.workshop

import com.workshop.model.Order
import com.workshop.service.{NewOrderService, PublishEventService}

import scala.concurrent.ExecutionContext.Implicits.global

object Main extends App {
  for {
    newOrder <- NewOrderService.impl.newOrder(Order("id"))
    _ <- PublishEventService.impl.publish(newOrder)
  } yield ()
}