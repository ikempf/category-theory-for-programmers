package com.ikempf.part1.chapter3_categories_great_and_small

import com.ikempf.part1.chapter3_categories_great_and_small.Challenges3.boolAndMonoid
import com.ikempf.part1.chapter3_categories_great_and_small.Challenges3.boolOrMonoid
import org.scalacheck.Gen
import org.scalatest.FlatSpec
import org.scalatest.Matchers
import org.scalatest.prop.PropertyChecks

class Challenges3Test extends FlatSpec with Matchers with PropertyChecks {

  val boolGen = Gen.oneOf(true, false)

  "BoolAndMonoid.empty" should "satisfy identity" in {
    forAll(boolGen) { b ⇒
      boolAndMonoid.empty && b should equal(b)
    }
  }

  "BoolAndMonoid.combine" should "be associative" in {
    forAll(boolGen, boolGen, boolGen) { (b1, b2, b3) ⇒
      val right = boolAndMonoid.combine(boolAndMonoid.combine(b1, b2), b3)
      val left  = boolAndMonoid.combine(b1, boolAndMonoid.combine(b2, b3))
      right should equal(left)
    }
  }

  "BoolOrMonoid.empty" should "satisfy identity" in {
    forAll(boolGen) { b ⇒
      boolOrMonoid.empty || b should equal(b)
    }
  }

  "BoolOrMonoid.combine" should "be associative" in {
    forAll(boolGen, boolGen, boolGen) { (b1, b2, b3) ⇒
      val right = boolOrMonoid.combine(boolOrMonoid.combine(b1, b2), b3)
      val left  = boolOrMonoid.combine(b1, boolOrMonoid.combine(b2, b3))
      right should equal(left)
    }
  }

}
