package exercise5

import cats.{Foldable, Functor}
import exercise3.Tree

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
  implicit val treeFoldable: Foldable[Tree] = ???
}
