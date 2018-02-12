package com.ikempf.part1.chapter10_naturality

import cats.data.Reader

object Challenges10 {

  // 1 - Define a natural transformation from the Maybe functor to the list functor.
  // Prove the naturality condition for it.
  def optToList[A](opt: Option[A]): List[A] =
    // opt.toList
    opt.map(a => List(a)).getOrElse(List.empty)

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

}
