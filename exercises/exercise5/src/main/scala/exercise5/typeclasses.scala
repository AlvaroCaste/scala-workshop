package exercise5

import cats.{Eq, Eval, Foldable, Functor}
import exercise3.{Empty, Node, Tree}

object typeclasses {

  /**
   * create a Functor instance for our binary tree
   */
  implicit val treeFunctor: Functor[Tree] = new Functor[Tree] {
    override def map[A, B](fa: Tree[A])(f: A => B): Tree[B] = Tree.map(fa)(f)
  }

  /**
   * create a Foldable instance for our binary tree
   */
  implicit val treeFoldable: Foldable[Tree] = new Foldable[Tree] {
    override def foldLeft[A, B](fa: Tree[A], b: B)(f: (B, A) => B): B = fa match {
      case Empty() => b
      case Node(l, a, r) => foldLeft(r, foldLeft(l, f(b, a))(f))(f)
    }

    /**
     * Out of Scope:
     * Eval provides stack-safety, otherwise 💥
     * More about: https://www.scala-exercises.org/cats/Eval
     */
    override def foldRight[A, B](fa: Tree[A], lb: Eval[B])(f: (A, Eval[B]) => Eval[B]): Eval[B] = fa match {
      case Empty() => lb
      case Node(l, a, r) => foldRight(r, foldRight(l, f(a, lb))(f))(f)
    }
  }
}
