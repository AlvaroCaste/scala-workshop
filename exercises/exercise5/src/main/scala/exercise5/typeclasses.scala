package exercise5

import cats.{Eval, Foldable, Functor, Monad, Monoid}
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

  /**
   * create a Monad instance for Maybe
   */
  sealed trait Maybe[A]
  case class Nothing[A]() extends Maybe[A]
  case class Just[A](a: A) extends Maybe[A]

  implicit val maybeMonad: Monad[Maybe] = new Monad[Maybe] {
    override def pure[A](x: A): Maybe[A] = Just(x)

    override def flatMap[A, B](fa: Maybe[A])(f: A => Maybe[B]): Maybe[B] = fa match {
      case Nothing() => Nothing[B]()
      case Just(a) => f(a)
    }

    /**
     * This method was not originally part of the Monad typeclass,
     * although is added for performance
     * More about: https://typelevel.org/cats/faq.html#tailrecm
     */
    override def tailRecM[A, B](a: A)(f: A => Maybe[Either[A, B]]): Maybe[B] = f(a) match {
      case Nothing() => Nothing[B]()
      case Just(Right(b)) => Just(b)
      case Just(Left(a1)) => tailRecM(a1)(f)
    }

    /**
     * Parametrize the following functions to work on typeclasses, not
     * directly on trees.
     */
    def sum[F[_]: Foldable](f: F[Int]): Int =
      Foldable[F].foldLeft(f, 0)(_ + _)

    def count[F[_]: Foldable, A](f: F[A]): Int =
      Foldable[F].foldLeft(f, 0)((acc, _) => acc + 1)

    def toStringNodes[F[_]: Functor](f: F[Int]): F[String] =
      Functor[F].map(f)(_.toString)

    def squared[F[_]: Functor](f: F[Int]): F[Int] =
      Functor[F].map(f)(x => x * x)
  }

  /**
   * Abstract this function further
   */
  def sumX[F[_], A](f: F[A])(implicit F: Foldable[F], A: Monoid[A]): A =
    F.foldLeft(f, A.empty)((x, y) => A.combine(x, y))
}
