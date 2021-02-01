trait Food
trait VegetarianFood extends Food
trait VeganFood extends VegetarianFood

trait Consumer[A] {
  def consume(value: A): String
}

def fire: Consumer[Food] =
  new Consumer[Food] {
    override def consume(value: Food): String = s"I'm fire, I consume everything, even $value"
  }

def vegetarian: Consumer[VegetarianFood] =
  new Consumer[VegetarianFood] {
    override def consume(value: VegetarianFood): String = s"I'm vegetarian, I can only food like $value"
  }

def vegan: Consumer[VeganFood] =
  new Consumer[VeganFood] {
    override def consume(value: VeganFood): String = s"I'm vegan, I don't eat anything from animals like $value"
  }

val energy: Food = new Food {
  override def toString: String = "energy"
}
val eggs: VegetarianFood = new VegetarianFood {
  override def toString: String = "eggs"
}

val carrots: VeganFood = new VeganFood {
  override def toString: String = "carrots"
}
def feedWith[F <: Food](food: F, consumers: Consumer[F]*): Unit =
  consumers.foreach(c => println(c.consume(food)))