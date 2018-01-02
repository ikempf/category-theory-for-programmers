package com.ikempf.part1.chapter6_simple_algebraic_data_types

import com.ikempf.part1.chapter6_simple_algebraic_data_types.Challenges6.eitherToOption
import com.ikempf.part1.chapter6_simple_algebraic_data_types.Challenges6.optionToEither
import com.ikempf.part1.chapter6_simple_algebraic_data_types.Challenges6.prodToSum
import com.ikempf.part1.chapter6_simple_algebraic_data_types.Challenges6.sumToProd
import org.scalacheck.Gen
import org.scalatest.FlatSpec
import org.scalatest.Matchers
import org.scalatest.prop.PropertyChecks

class Challenges6Test extends FlatSpec with Matchers with PropertyChecks {

  "Either () a and Option a" should "be isomorphic" in {
    forAll(Gen.option(Gen.alphaStr)) { maybe ⇒
      eitherToOption(optionToEither(maybe)) should equal(maybe)
    }
  }

  "a + a = 2 * a" should "be true for types as well" in {
    forAll(Gen.oneOf(true, false), Gen.alphaStr) { (bool, str) ⇒
      val prod = (bool, str)
      sumToProd(prodToSum(prod)) should equal(prod)
    }
  }

}
