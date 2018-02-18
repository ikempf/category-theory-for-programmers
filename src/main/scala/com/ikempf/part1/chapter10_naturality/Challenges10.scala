package com.ikempf.part1.chapter10_naturality

import cats.data.Reader
import cats.syntax.either._

object Challenges10 {

  // 1 - Define a natural transformation from the Maybe functor to the list functor.
  // Prove the naturality condition for it.
  def optToList[A](opt: Option[A]): List[A] =
    // opt.toList
    opt.fold(List.empty[A])(a => List(a))

  // 2 - Define at least two different natural transformations between Reader () and the list functor.
  // How many different lists of () are there?
  /**
    * There are infinitely many natural transformations between Reader[Unit, B] and List[B]
    */
  def readToList1[B](r: Reader[Unit, B]): List[B] =
    List.empty

  def readToList2[B](r: Reader[Unit, B]): List[B] =
    List(r.run(()))

  def readToList3[B](r: Reader[Unit, B]): List[B] =
    List(r.run(()), r.run(()))

  // 3 - Continue the previous exercise with Reader Bool and Maybe.
  def readToOpt1[B](r: Reader[Boolean, B]): Option[B] =
    None

  def readToOpt2[B](r: Reader[Boolean, B]): Option[B] =
    Some(r.run(false))

  def readToOpt3[B](r: Reader[Boolean, B]): Option[B] =
    Some(r.run(true))

  // 4. Show that horizontal composition of natural transformation satisfies the naturality condition (hint: use components).
  // It’s a good exercise in diagram chasing.
  /**
    * For three functors F, G and H and two natural transformations a :: F -> G and b :: G -> H, let's prove that b . a
    * respects that naturality condition.
    *
    * Let's try with functors Option => Either => List
    */
  def optToEither[A](opt: Option[A]): Either[Unit, A] =
    opt.map(Either.right).getOrElse(Either.left(()))

  def eitherToList[A](either: Either[Unit, A]): List[A] =
    either.fold(_ => List(), a => List(a))

  def optToEitherToList[A](opt: Option[A]): List[A] =
    eitherToList(optToEither(opt))

}
