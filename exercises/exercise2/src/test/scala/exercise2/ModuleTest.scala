package exercise2

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class ModuleTest extends AnyFlatSpec with Matchers {
  "Module" should "have a nice string conversion" in {
    exercise2.Module.toString shouldBe "Module"
  }

  it should "be serializable" in {
    Module shouldBe a [Serializable]
  }

  it should "be a product" in {
    fail("Uncomment and fix")
    //    Module.productPrefix shouldBe "Module"
    //    Module.productArity shouldBe 0
    //    Module.productIterator shouldBe 'empty
  }
}
