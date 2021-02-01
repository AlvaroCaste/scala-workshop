package exercise2.solutions

import org.scalacheck.{Arbitrary, Gen}
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import org.scalatestplus.scalacheck.ScalaCheckDrivenPropertyChecks

class LabelledPointTest extends AnyFlatSpec with Matchers with ScalaCheckDrivenPropertyChecks {
  // skip this. We will see it later
  implicit val arbitraryPoint: Arbitrary[LabelledPoint] = Arbitrary(for {
    label <- Gen.alphaStr
    x <- Arbitrary.arbitrary[Int]
    y <- Arbitrary.arbitrary[Int]
  } yield new LabelledPoint(label, x, y))


  "A 'case' class like LabelledPoint" should "have getters" in {
    val value = new LabelledPoint("label", 1, 2)

    value.label shouldBe "label"
    value.x shouldBe 1
    value.y shouldBe 2
  }

  it should "offer a factory method" in {
    val value = LabelledPoint("label", 1, 2)

    value.label shouldBe "label"
    value.x shouldBe 1
    value.y shouldBe 2
  }

  it should "have a nice to string method" in {
    LabelledPoint("label", 1, 2).toString shouldBe "LabelledPoint(label,1,2)"
  }

  it should "implement extensional equality" in {
    forAll { (label: String, x: Int, y: Int) =>
      LabelledPoint(label, x, y) shouldBe LabelledPoint(label, x, y)
      LabelledPoint(label, x, y) should not be LabelledPoint(label + "!", x, y)
      LabelledPoint(label, x, y) should not be LabelledPoint(label, x + 1, y)
      LabelledPoint(label, x, y) should not be LabelledPoint(label, x, y - 2)
    }
  }

  it should "implement extensional hash code" in {
    forAll { (value: LabelledPoint) =>
      val pointWithSameData = new LabelledPoint(value.label, value.x, value.y)
      value.hashCode() shouldBe pointWithSameData.hashCode()
    }
  }

  it should "be serializable" in {
    LabelledPoint("label", 1, 2) shouldBe a [Serializable]
  }

  it should "be a product type" in {
    val value = LabelledPoint("name", 5, 7)

    value.productPrefix shouldBe "LabelledPoint"
    value.productArity shouldBe 3
    value.productElement(0) shouldBe "name"
    value.productElement(1) shouldBe 5
    value.productElement(2) shouldBe 7
    value.productIterator.toList shouldBe List("name", 5, 7)
  }

  it should "have a copy method" in {
    val value = LabelledPoint("name", 5, 7)
    value.copy(label = "other") shouldBe LabelledPoint("other", 5, 7)
    value.copy(x = 1, y = 2) shouldBe LabelledPoint("name", 1, 2)
  }

  it should "have an extractor" in {
    LabelledPoint.unapply(LabelledPoint("name", 5, 7)) shouldBe Some("name", 5, 7)

    val values = Seq.tabulate(10) { index => LabelledPoint(s"point$index", index % 2, index) }
    values.collect {
      case LabelledPoint(name, 0, index) if index < 5 => name
    } shouldBe Seq("point0", "point2", "point4")
  }
}