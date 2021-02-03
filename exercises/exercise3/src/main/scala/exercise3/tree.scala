package exercise3

sealed trait Tree[A]
case class Empty[A]() extends Tree[A]
case class Node[A](l: Tree[A], a: A, r: Tree[A]) extends Tree[A]

object Tree {

  /**
   * implement the fold function
   */
  def fold[A, B](tree: Tree[A])(onEmpty: B, onNode: (B, A, B) => B): B = tree match {
    case Empty() => onEmpty
    case Node(left, a, right) => onNode(
      fold(left)(onEmpty, onNode),
      a,
      fold(right)(onEmpty, onNode)
    )
  }

  /**
   * Refactor all the functions we implemented with primitive recursion
   */
  def height[A](tree: Tree[A]): Int = fold(tree)(0, { (l: Int, _: A, r: Int) =>
    1 + (l.max(r))
  })

  def sum(tree: Tree[Int]): Int = fold(tree)(0, { (l: Int, a: Int, r: Int) =>
    l + a + r
  })

  def count[A](tree: Tree[A]): Int = fold(tree)(0, { (l: Int, _: A, r: Int) =>
    1 + l + r
  })

  /**
   * Implement the map function based on fold
   */
  def map[A, B](tree: Tree[A])(fn: A => B): Tree[B] =
    fold(tree)(Empty[B](), { (l: Tree[B], a: A, r: Tree[B]) =>
      Node[B](l, fn(a), r)
  })

}
