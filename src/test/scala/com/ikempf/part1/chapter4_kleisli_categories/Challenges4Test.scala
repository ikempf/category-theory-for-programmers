package com.ikempf.part1.chapter4_kleisli_categories

import com.ikempf.part1.chapter4_kleisli_categories.Challenges4.Defined
import com.ikempf.part1.chapter4_kleisli_categories.Challenges4.Partial
import com.ikempf.part1.chapter4_kleisli_categories.Challenges4.Undefined
import com.ikempf.part1.chapter4_kleisli_categories.Challenges4.safeRootReciprocal
import org.scalacheck.Gen
import org.scalatest.FlatSpec
import org.scalatest.Matchers
import org.scalatest.prop.PropertyChecks

import scala.util.Try

class Challenges4Test extends FlatSpec with Matchers with PropertyChecks {

  val genNumStr = Gen.choose(Int.MinValue, Int.MaxValue).map(_.toString)

  "Composition" should "respect identity" in {
    val f: String ⇒ Partial[Int] = {
      case s if Try(s.toInt).isSuccess ⇒ Defined(s.toInt)
      case _                           ⇒ Undefined
    }

    val `f.i` = Partial.compose(f, Partial.identity[Int])
    val `i.f` = Partial.compose(Partial.identity[String], f)

    forAll(Gen.alphaNumStr) { str ⇒
      `f.i`(str) should equal(`i.f`(str))
    }
    forAll(genNumStr) { str ⇒
      `f.i`(str) should equal(`i.f`(str))
    }
  }

  it should "respect associativity" in {
    val f: String ⇒ Partial[Int] = {
      case s if Try(s.toInt).isSuccess ⇒ Defined(s.toInt)
      case _                           ⇒ Undefined
    }
    val g: Int ⇒ Partial[Char] = {
      case i if i > -100 && i < 100 ⇒ Defined(i.toChar)
      case _                        ⇒ Undefined
    }
    val h: Char ⇒ Partial[Boolean] = {
      case i if i > 0 && i % 2 == 0 ⇒ Defined(true)
      case i if i > 0 && i % 2 == 1 ⇒ Defined(false)
      case _ ⇒ Undefined
    }

    val `f.(g.h)` = Partial.compose(f, Partial.compose(g, h))
    val `(f.g).h` = Partial.compose(Partial.compose(f, g), h)

    forAll(genNumStr) { str =>
      `f.(g.h)`(str) should equal(`(f.g).h`(str))
    }
  }

  "SafeRootReciprocal" should "compute sqrt(1/x)" in {
    safeRootReciprocal(4) should equal(Defined(0.5))
    safeRootReciprocal(0) should equal(Undefined)
    safeRootReciprocal(-1) should equal(Undefined)
  }

}
