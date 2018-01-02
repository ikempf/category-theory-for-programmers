package com.ikempf.part1.chapter6_simple_algebraic_data_types

import cats.syntax.either._

object Challenges6 {

  // 1 - Show the isomorphism between Maybe a and Either () a.
  def optionToEither[A](opt: Option[A]): Either[Unit, A] =
    opt.map(Either.right).getOrElse(Either.left(()))

  def eitherToOption[A](either: Either[Unit, A]): Option[A] =
    either.map(Option.apply).getOrElse(Option.empty)

  // 3 - Add circ to your C++ or Java implementation. What parts of the original code did you have to touch?
  /**
    * It is necessary to extend all data types (Shape + Circle + Rectangle) to add the circ function.
    */

  // 4 - Continuing further: Add a new shape, Square, to Shape and make all the necessary updates. What code did you have
  // to touch in Haskell vs. C++ or Java? (Even if you’re not a Haskell programmer, the modifications should be
  // pretty obvious.)
  /**
    * In Java we need to create the new class with it's data and the implementation of all existing functionality
    * of the interface (area, circ).
    * In Haskell we create the new ADT (data only) and update all existing functionalities to support the rectangle
    */

  // 5 - Show that a + a = 2 * a holds for types (up to isomorphism). Remember that 2 corresponds to Bool,
  // according to our translation table.
  def sumToProd[A](sum: Either[A, A]): (Boolean, A) =
    sum.fold((false, _), (true, _))

  def prodToSum[A](prod: (Boolean, A)): Either[A, A] =
    prod match {
      case (true, a)  ⇒ Either.right(a)
      case (false, a) ⇒ Either.left(a)
    }

}
