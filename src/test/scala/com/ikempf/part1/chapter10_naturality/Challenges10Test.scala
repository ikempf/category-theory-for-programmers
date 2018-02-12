package com.ikempf.part1.chapter10_naturality

import com.ikempf.part1.chapter10_naturality.Challenges10.optToList
import org.scalacheck.Gen
import org.scalatest.FlatSpec
import org.scalatest.Matchers
import org.scalatest.prop.PropertyChecks

class Challenges10Test extends FlatSpec with Matchers with PropertyChecks {

  "Opt to List" should "satisfy naturality laws" in {
    val optGen = Gen.option(Gen.numStr)

    val f: String => Boolean = _.length % 2 == 0

    forAll(optGen) { o =>
      optToList(o.map(f)) should equal(optToList(o).map(f))
    }
  }

}
