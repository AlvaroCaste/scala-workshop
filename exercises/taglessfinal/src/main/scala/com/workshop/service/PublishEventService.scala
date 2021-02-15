package com.workshop.service

import scala.concurrent.Future

trait PublishEventService[F[_]] {
  def publish[A](event: A): F[Unit]
}

object PublishEventService {
  implicit val impl: PublishEventService[Future] =
    new PublishEventService[Future] {
      def publish[A](event: A): Future[Unit] =
        Future.successful(println(s"event published: $event"))
    }
}