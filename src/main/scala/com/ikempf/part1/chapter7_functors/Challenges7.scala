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
    * - For Nothing, is fmap(identity)(Nothing) = identity(Nothing) ?
    * fmap(identity)(Nothing)
    * Definition of fmap
    * = Nothing
    * Definition of identity
    * = identity(Nothing)
    *
    * - For Just, is fmap(identity)(Just(a)) = identity(a) ?
    * fmap(identity)(Just(a))
    * Definition of fmap
    * = Nothing, this is not equal to identity(Just(a))
    *
    * This definition of fmap violates functor laws
    */

  // 2 - Prove functor laws for the reader functor. Hint: it’s really simple.
  def readerInstance[R]: Functor[Function[R, ?]] = new Functor[Function[R, ?]] {
    override def map[A, B](r: R ⇒ A)(f: A ⇒ B): R ⇒ B = f.compose(r)
  }
  /**
    * Identity law, is fmap(identity)(r) = identity(r) ?
    * fmap(identity)(r)
    * Definition of fmap
    * = identity.compose(r)
    * Definition of identity
    * = r
    * Definition of identity
    * = identity(r)
    *
    * Composition law, is fmap(f.compose(g))(r)) = fmap(f)(fmap(g)(r)) ?
    * fmap(f.compose(g))(r))
    * Definition of fmap
    * = f.compose(g).compose(r)
    * Composition is associative
    * = f.compose(g.compose(r))
    * Definition of fmap
    * = fmap(f)(g.compose(r))
    * Definition of fmap
    * = fmap(f)(fmap(g)(r))
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
    * For Nil, is fmap(identity)(Nil) = identity(Nil) ?
    * fmap(identity)(Nil)
    * Definition if fmap
    * = Nil
    * Definition of identity
    * = identity(Nil)
    *
    * For ::, is fmap(identity)(l) = identity(l) ?
    * fmap(identity)(l)
    * Definition of fmap
    * = identity(h) :: fmap(identity)(t)
    * Definition of identity
    * = h :: fmap(identity)(t)
    * By induction
    * = l
    * Definition of identity
    * = identity(l)
    *
    * Composition law
    * For Nil, is fmap(f.compose(g))(Nil) = fmap(f)(fmap(g)(Nil)) ?
    * fmap(f.compose(g))(Nil)
    * Definition of fmap
    * = Nil
    * Definition of fmap
    * = fmap(f)(Nil)
    * Definition of fmap
    * = fmap(f)(fmap(g)(Nil))
    *
    * For ::, is fmap(f.compose(g))(l) = fmap(f)(fmap(g)(l)) ?
    * fmap(f.compose(g))(l)
    * Definition of fmap
    * = f.compose(g)(h) :: fmap(f.compose(g))(t)
    * Definition of fmap + induction
    * = fmap(f)(g(h) :: fmap(g)(t)))
    * Definition of fmap + induction
    * = fmap(f)(fmap(g)(l))
    *
    * Easier understood starting from fmap(f)(fmap(g)(l))
    * fmap(f)(fmap(g)(l))
    * Definition of fmap
    * = fmap(f)(g(h) :: fmap(g)(t)))
    * Definition of fmap + induction
    * = f.compose(g)(h) :: fmap(f.compose(g))(t)
    * Definition of fmap + induction
    * =  fmap(f.compose(g))(l)
    */

}
