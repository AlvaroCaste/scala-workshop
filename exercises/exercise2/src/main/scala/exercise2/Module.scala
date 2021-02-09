package exercise2

object Module extends Serializable with Product {
  override def toString: String = "Module"

  override def productArity: Int = 0

  override def productPrefix: String = "Module"

  override def productElement(n: Int): Any = throw new IndexOutOfBoundsException

  override def canEqual(that: Any): Boolean = that match {
    case Module => true
    case _ => false
  }
}

