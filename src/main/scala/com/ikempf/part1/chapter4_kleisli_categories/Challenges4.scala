package com.ikempf.part1.chapter4_kleisli_categories

object Challenges4 {

  // 1
  sealed trait Partial[+A]
  case class Defined[A](a: A) extends Partial[A]
  case object Undefined       extends Partial[Nothing]

  object Partial {
    def identity[A](a: A): Partial[A] = Defined(a)

    def compose[A, B, C](f: A ⇒ Partial[B], g: B ⇒ Partial[C]): A ⇒ Partial[C] =
      a ⇒
        f(a) match {
          case Defined(b) ⇒ g(b)
          case Undefined  ⇒ Undefined
      }
  }

  // 2
  def safeReciprocal(a: Double): Partial[Double] =
    if (a != 0)
      Defined(1 / a)
    else
      Undefined

  def safeRoot(a: Int): Partial[Double] =
    if (a > 0)
      Defined(Math.sqrt(a))
    else
      Undefined

  // 3
  val safeRootReciprocal: Int => Partial[Double] =
    Partial.compose(safeRoot, safeReciprocal)

}
