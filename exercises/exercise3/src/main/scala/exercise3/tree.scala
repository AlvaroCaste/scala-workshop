package exercise3

sealed trait Tree[A]
case class Empty[A]() extends Tree[A]
case class Node[A](l: Tree[A], a: A, r: Tree[A]) extends Tree[A]

object Tree {

  /**
   * implement the fold function
   */
  def fold[A, B](tree: Tree[A])(onEmpty: B, onNode: (B, A, B) => B): B = ???

}
