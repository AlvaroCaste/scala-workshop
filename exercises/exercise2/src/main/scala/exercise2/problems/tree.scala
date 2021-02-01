package exercise2.problems


/**
 * Implement a binary tree structure
 *
 * Binary trees are data structures in which each node has a value,
 * an two children at most.
 *
 * https://en.wikipedia.org/wiki/Binary_tree
 */

trait Tree[A]

object Tree {

  /**
   * Implement a function height that returns the longest height in a
   * tree
   */
  def height[A](tree: Tree[A]): Int = ???

  /**
   * Create a function that sums all the leaves in a Tree[Int]
   */
  def sum(tree: Tree[Int]): Int = ???

  /**
   * Create a function that counts all the leaves in a tree
   */
  def count[A](tree: Tree[A]): Int = ???

  /**
   * Create a function that transforms each element in a tree into it's
   * string representation
   */
  def toStringNodes(tree: Tree[Int]): Tree[String] = ???

  /**
   * Create a function that squares all elements in an Int tree
   */
  def squared(tree: Tree[Int]): Tree[Int] = ???
}
