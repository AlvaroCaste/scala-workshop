package com.workshop.example2.service

import scala.concurrent.Future

trait PublishEventService[F[_]] {
  def publish[A](event: A): F[Unit]
}

object PublishEventService {
  def apply[F[_]](implicit F: PublishEventService[F]): PublishEventService[F] = F

  implicit val impl: PublishEventService[Future] =
    new PublishEventService[Future] {
      def publish[A](event: A): Future[Unit] =
        Future.successful(println(s"event published: $event"))
    }
}