trait Entertainment
trait Music extends Entertainment
trait Metal extends Music

sealed trait CList[A]
case class Empty[A]() extends CList[A]
case class Elem[A](h: A, t: CList[A] = Empty[A]()) extends CList[A]

val cinema: Entertainment = new Entertainment {
  override def toString: String = "cinema"
}
val classicMusic: Music = new Music {
  override def toString: String = "classic music"
}
val deathMetal: Metal = new Metal {
  override def toString: String = "death metal music"
}