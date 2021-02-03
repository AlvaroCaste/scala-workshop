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