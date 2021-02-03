package exercise3.problems

sealed trait Tree[A]
case class Empty[A]() extends Tree[A]
case class Node[A](l: Tree[A], a: A, r: Tree[A]) extends Tree[A]

object Tree {

  /**
   * implement the fold function
   */
  def fold[A, B](tree: Tree[A])(onEmpty: B, onNode: (B, A, B) => B): B = ???

  /**
   * Refactor all the functions we implemented with primitive recursion
   */
  def height[A](tree: Tree[A]): Int = ???

  def sum(tree: Tree[Int]): Int = ???

  def count[A](tree: Tree[A]): Int = ???

  /**
   * Implement the map function based on fold
   */
  def map[A, B](tree: Tree[A])(fn: A => B): Tree[B] = ???

  /**
   * refactor toStringNodes & squared to be based on map instead of fold
   */
  def toStringNodes(tree: Tree[Int]): Tree[String] = ???

  def squared(tree: Tree[Int]): Tree[Int] = ???
}