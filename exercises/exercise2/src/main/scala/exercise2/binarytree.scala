package exercise2


/**
 * Implement a binary tree structure
 *
 * Binary trees are data structures in which each node has a value,
 * an two children at most.
 *
 * https://en.wikipedia.org/wiki/Binary_tree
 */
sealed trait Tree[A]
case class Empty[A]() extends Tree[A]
case class Node[A](l: Tree[A], a: A, r: Tree[A]) extends Tree[A]

object Tree {

  /**
   * Implement a function height that returns the longest height in a
   * tree
   */
  def height[A](tree: Tree[A]): Int = tree match {
    case Empty() => 0
    case Node(l, _, r) => 1 + (height(l).max(height(r)))
  }

  /**
   * Create a function that sums all the leaves in a Tree[Int]
   */
  def sum(tree: Tree[Int]): Int = tree match {
    case Empty() => 0
    case Node(l, x, r) => x + sum(l) + sum(r)
  }

  /**
   * Create a function that counts all the leaves in a tree
   */
  def count[A](tree: Tree[A]): Int =  tree match {
    case Empty() => 0
    case Node(l, _, r) => 1 + count(l) + count(r)
  }
}