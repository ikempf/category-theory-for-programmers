package com.ikempf.part1.chaper1_the_essence_of_composition

import org.scalatest.FlatSpec
import org.scalatest.Matchers
import org.scalatest.prop.Checkers
import org.scalatest.prop.PropertyChecks

class Challenges1Test extends FlatSpec with Matchers with Checkers with PropertyChecks {

  "Identity" should "return the given argument" in {
    forAll((n: Int) ⇒ Challenges1.identity(n) should equal(n))
  }

  // 3 Write a program that tries to test that your composition function respects identity.
  "Composition" should "respect identity" in {
    def f[A]: A ⇒ (A, A) = a ⇒ (a, a)

    val `f.i` = Challenges1.composition(Challenges1.identity[Int])(f)
    val `i.f` = Challenges1.composition(f[Int])(Challenges1.identity)

    forAll((n: Int) ⇒ `f.i`(n) should equal(`i.f`(n)))
  }

  it should "respect associativity" in {
    val f: Int ⇒ (Int, Int)         = n ⇒ (n, n)
    val g: ((Int, Int)) ⇒ List[Int] = n ⇒ List(n._1, n._2)
    val h: List[Int] ⇒ Set[Int]     = n ⇒ Set(n: _*)

    val `h.(g.f)` = Challenges1.composition(Challenges1.composition(f)(g))(h)
    val `(h.g).f` = Challenges1.composition(f)(Challenges1.composition(g)(h))

    forAll((n: Int) ⇒ {
      `h.(g.f)`(n) should equal(`(h.g).f`(n))
      `(h.g).f`(n) should equal(h(g(f(n))))
    })
  }

}
