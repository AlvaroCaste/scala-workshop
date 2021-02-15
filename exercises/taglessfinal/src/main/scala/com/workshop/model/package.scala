package com.workshop

package object model {
  case class Order(id: String, label: Option[String] = None)
  case class Response(label: String)
}
