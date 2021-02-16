package com.workshop.example2

import com.workshop.model.Order
import com.workshop.example2.service.{NewOrderService, PublishEventService}

import scala.concurrent.ExecutionContext.Implicits.global

object Main extends App {
  for {
    newOrder <- NewOrderService.impl.newOrder(Order("id"))
    _ <- PublishEventService.impl.publish(newOrder)
  } yield ()
}