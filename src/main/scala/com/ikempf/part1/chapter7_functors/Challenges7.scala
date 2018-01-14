package com.ikempf.part1.chapter7_functors

import cats.Functor

object Challenges7 {

  sealed trait Maybe[+A]
  case object Nothing      extends Maybe[Nothing]
  case class Just[A](a: A) extends Maybe[A]

  // 1 - Can we turn the Maybe type constructor into a functor by defining:
  // fmap _ _ = Nothing
  // which ignores both of its arguments? (Hint: Check the functor laws.)
  /**
    * Let's check the functor laws (ensuring the functor preserves structure between the categories it maps)
    * Identity law
    * - For Nothing, is fmap id Nothing = id Nothing ?
    * fmap id Nothing
    * Definition of fmap
    * = Nothing
    * Definition of id
    * = id Nothing
    *
    * - For Just, is fmap id (Just a) = id a ?
    * fmap id Just a
    * Definition of fmap
    * = Nothing, this is not equal to id (Just a)
    *
    * This definition of fmap violates functor laws
    */

  // 2 - Prove functor laws for the reader functor. Hint: it’s really simple.
  def readerInstance[R]: Functor[Function[R, ?]] = new Functor[Function[R, ?]] {
    override def map[A, B](r: R ⇒ A)(f: A ⇒ B): R ⇒ B = f.compose(r)
  }
  /**
    * Identity law, is fmap id r = id r ?
    * fmap id r
    * Definition of fmap
    * = id . r
    * Definition of id
    * = r
    * Definition of id
    * = id r
    *
    * Composition law, is fmap (f . g) r = (fmap f) . (fmap g) . r ?
    * fmap (f . g) r
    * Definition of fmap
    * = f . g . r
    * Definition of fmap
    * = fmap f (g . r)
    * Definition of fmap
    * = fmap f (fmap g r)
    * Definition of composition
    * = (fmap f . fmap g) r
    */

  // 3 - Implement the reader functor in your second favorite language (the first being Haskell, of course).
  /** already implemented in 2# for property testing */

  // 4 - Prove the functor laws for the list functor. Assume that the laws are true for the tail part of the list
  // you’re applying it to (in other words, use induction).
  val listInstance = new Functor[List] {
    override def map[A, B](l: List[A])(f: A ⇒ B): List[B] =
      l match {
        case Nil    ⇒ Nil
        case h :: t ⇒ f(h) +: map(t)(f)
      }
  }
  /**
    * Identity law
    * For Nil, is fmap id Nil = id Nil ?
    * fmap id Nil
    * Definition if fmap
    * = Nil
    * Definition of id
    * = id Nil
    *
    * For ::, is fmap id l = id l ?
    * fmap id l
    * Definition of fmap
    * = id h :: fmap id t
    * Definition of id
    * = h :: fmap id t
    * By induction
    * = l
    * Definition of id
    * = id l
    *
    * Composition law
    * For Nil, is fmap (f . g) Nil = fmap f (fmap g Nil) ?
    * fmap (f. g) Nil
    * Definition of fmap
    * = Nil
    * Definition of fmap
    * = fmap f Nil
    * Definition of fmap
    * = fmap f (fmap g Nil)
    *
    * For ::, is fmap (f . g) l = fmap f (fmap g l) ?
    * fmap (f . g) l
    * Definition of fmap
    * = (f . g) h :: fmap (f . g) t
    * Definition of fmap + induction
    * = fmap f (g h :: fmap g t)
    * Definition of fmap + induction
    * = fmap f (fmap g l)
    *
    * Easier understood starting from fmap f (fmap g l)
    * fmap f (fmap g l)
    * Definition of fmap
    * = fmap f (g h :: fmap g t)
    * Definition of fmap + induction
    * = (f . g) h :: fmap (f . g) t
    * Definition of fmap + induction
    * =  fmap (f . g) l
    *
    */

}
