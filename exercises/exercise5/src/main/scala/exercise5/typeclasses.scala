package exercise5

import cats.Functor
import exercise3.Tree

object typeclasses {

  /**
   * create a Functor instance for our binary tree
   */
  implicit val treeFunctor: Functor[Tree] = ???
}
