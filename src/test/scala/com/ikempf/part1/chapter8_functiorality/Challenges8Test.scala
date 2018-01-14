package com.ikempf.part1.chapter8_functiorality

import cats.Eq
import cats.Functor
import cats.laws.discipline.FunctorTests
import com.ikempf.part1.chapter8_functiorality.Challenges8.pairFirstInstance
import com.ikempf.part1.chapter8_functiorality.Challenges8.Pair
import com.ikempf.part1.chapter8_functiorality.Challenges8.pairSecondInstance
import org.scalacheck.Arbitrary
import org.scalacheck.Gen
import org.scalatest.FunSuite
import org.scalatest.Matchers
import org.typelevel.discipline.scalatest.Discipline

class Challenges8Test extends FunSuite with Matchers with Discipline {

  implicit val strGen = Gen.alphaStr

  // 1 Checking Pair functoriality in first argument
  def firstPairGen[A](implicit genA: Gen[A]): Gen[Pair[A, String]] = genA.map(Pair(_, ""))
  implicit def arbitraryPair[A: Gen]: Arbitrary[Pair[A, String]] = Arbitrary(firstPairGen)
  implicit def eqPair[A]: Eq[Pair[A, String]] = Eq.instance(_ == _)

  type Why[A] = Pair[A, String] // should not be needed
  private val firstInstance: Functor[Why] = pairFirstInstance
  private val firstFunctorRules = FunctorTests(firstInstance).functor[String, Int, Char]
  checkAll("pair first argument functoriality", firstFunctorRules)

  // 2 Checking Pair functoriality in first argument
  type Why2[A] = Pair[String, A] // should not be needed
  private val secondInstance: Functor[Why2] = pairSecondInstance
  private val secondFunctorRules = FunctorTests(firstInstance).functor[String, Int, Char]
  checkAll("pair second argument functoriality", secondFunctorRules)

}
