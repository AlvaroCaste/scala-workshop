package exercise2

class LabelledPoint(val label: String, val x: Int, val y: Int) extends Serializable with Product {

  override def toString: String = s"LabelledPoint($label,$x,$y)"

  override def equals(obj: Any): Boolean = obj match {
    case lp: LabelledPoint => label == lp.label && x == lp.x && y == lp.y
    case _ => throw new ClassCastException
  }

  override def hashCode(): Int = 31 * label.hashCode * x.hashCode * y.hashCode

  override def productArity: Int = 3

  override def productElement(n: Int): Any = n match {
    case 0 => label
    case 1 => x
    case 2 => y
    case _ => throw new IndexOutOfBoundsException(n)
  }

  override def productPrefix: String = "LabelledPoint"

  override def canEqual(that: Any): Boolean = that match {
    case LabelledPoint => true
    case _ => false
  }

  def copy(label: String = label, x: Int = x, y: Int = y): LabelledPoint = LabelledPoint(label, x, y)
}

object LabelledPoint {
  def apply(label: String, x: Int, y: Int): LabelledPoint = new LabelledPoint(label, x, y)

  def unapply(arg: LabelledPoint): Option[(String, Int, Int)] = Option(arg.label, arg.x, arg.y)
}
