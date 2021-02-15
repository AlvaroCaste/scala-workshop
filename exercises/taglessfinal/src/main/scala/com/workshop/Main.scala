package com.workshop

import cats.effect.IO
import com.workshop.model.Order
import com.workshop.service.{NewOrderService, PublishEventService}

object Main extends App {
  (for {
    newOrder <- NewOrderService[IO].newOrder(Order("id"))
    _ <- PublishEventService[IO].publish(newOrder)
  } yield ()).unsafeRunSync()
}