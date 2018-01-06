package com.ikempf.part1.chapter7_functors

import cats.Functor
import cats.kernel.Eq
import cats.laws.discipline.FunctorTests
import com.ikempf.part1.chapter7_functors.Challenges7.Just
import com.ikempf.part1.chapter7_functors.Challenges7.Maybe
import com.ikempf.part1.chapter7_functors.Challenges7.Nothing
import com.ikempf.part1.chapter7_functors.Challenges7.listInstance
import com.ikempf.part1.chapter7_functors.Challenges7.readerInstance
import org.scalacheck.Arbitrary
import org.scalacheck.Gen
import org.scalatest.FunSuite
import org.scalatest.Matchers
import org.typelevel.discipline.scalatest.Discipline

class Challenges7Test extends FunSuite with Matchers with Discipline {

  implicit val strGen = Gen.alphaStr

  // 1 Checking maybe functor with discipline and cats laws
  val constantMaybeFunctor: Functor[Maybe] = new Functor[Maybe] {
    override def map[A, B](fa: Maybe[A])(f: A ⇒ B): Maybe[B] = Nothing
  }
  def maybeGen[A](implicit genA: Gen[A]): Gen[Maybe[A]] = Gen.oneOf(Nothing, Just(genA.sample.get))
  implicit def maybeArbitrary[A: Gen]: Arbitrary[Maybe[A]] = Arbitrary(maybeGen)
  implicit def maybeEq[A]: Eq[Maybe[A]] = Eq.instance(_ == _)
  val maybeFunctorRules = FunctorTests(constantMaybeFunctor).functor[String, Int, Char]

  //checkAll("maybe functor with fmap = Nothing does NOT respect identity", maybeFunctorRules)

  // 2 - Checking reader functor
  implicit def readerEq[A]: Eq[Function[String, A]] = Eq.instance(_("foo") == _("foo"))

  type Why[A] = Function[String, A] // should not be needed
  val stringReaderInstance: Functor[Why] = readerInstance[String]
  val readerFunctorRules = FunctorTests(stringReaderInstance).functor[String, Int, Char]

  checkAll("reader functor should respect identity", readerFunctorRules)

  // 4 - Checking list functor
  implicit def listEq[A]: Eq[List[A]] = Eq.instance(_ == _)
  val listFunctorRules = FunctorTests(listInstance).functor[String, Int, Char]

  checkAll("list functor should respect identity", listFunctorRules)

}
