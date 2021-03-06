package com.ikempf.part1.chapter8_functiorality

import cats.Bifunctor
import cats.Functor
import cats.arrow.Profunctor

import scala.language.higherKinds

object Challenges8 {

  // 1 - Show that the data type:
  // data Pair a b = Pair a b
  // is a bifunctor. For additional credit implement all three methods of Bifunctor and use equational reasoning to
  // show that these definitions are compatible with the default implementations whenever they can be applied.
  /**
    * For Pair to be a bifunctor we have to prove that the type constructor is functorial in both of it's arguments
    *
    * Let's show that Pair a String is functorial in a
    * Identity law, fmap id (Pair a "") == id (Pair a "")
    * fmap id (Pair a "")
    * Definition of fmap
    * = Pair (id a) ""
    * Definition of id
    * = Pair a ""
    * Definition of id
    * = id (Pair a "")
    *
    * Composition law, fmap (g . f) (Pair a "") == (fmap g . fmap f) (Pair a "")
    * fmap (g . f) Pair(a, "")
    * Definition of fmap
    * = Pair ((g . f) a) ""
    * Definition of fmap
    * = fmap g (Pair (f a) "")
    * Definition of fmap
    * = fmap g (fmap f (Pair a ""))
    * Definition of composition
    * = (fmap g . fmap f) (Pair(a, "")
    *
    * Pair is functorial in its first argument
    *
    * Let's show that Pair String b is functorial in b
    * Identity law, fmap id (Pair "" b) = id (Pair "" b)
    * fmap id (Pair "" b)
    * Definition of fmap
    * = Pair "" (id b)
    * Definition of id
    * = Pair "" b
    * Definition of id
    * = id (Pair "" b)
    *
    * Composition law, fmap (g . f) (Pair "" b) = (fmap g . fmap f) (Pair "" b)
    * fmap (g . f) (Pair "" b)
    * Definition of fmap
    * = Pair "" ((g . f) b)
    * Definition of fmap
    * = fmap g (Pair "" (f b))
    * Definition of fmap
    * = fmap g (fmap f (Pair "" b))
    * Definition of composition
    * = (fmap g) . (fmap f) (Pair " b)
    *
    * Pair is functorial in it's second argument.
    * Pair is thus a bifunctor.
    */
  case class Pair[A, B](a: A, b: B)

  val pairFirstInstance: Functor[Pair[?, String]] =
    new Functor[Pair[?, String]] {
      override def map[A, B](fa: Pair[A, String])(f: A ⇒ B): Pair[B, String] =
        fa.copy(a = f(fa.a))
    }

  val pairSecondInstance: Functor[Pair[String, ?]] =
    new Functor[Pair[String, ?]] {
      override def map[A, B](fa: Pair[String, A])(f: A ⇒ B): Pair[String, B] =
        fa.copy(b = f(fa.b))
    }

  // 2 -  Show the isomorphism between the standard definition of Maybe and this desugaring:
  // type Maybe' a = Either (Const () a) (Identity a)
  // Hint: Define two mappings between the two implementations.
  // For additional credit, show that they are the inverse of each other using equational reasoning.

  // To better illustrate the isomorphism, let's define a "real" Identity
  // type constructor (instead of using cats Id type alias)
  case class Identity[A](a: A)
  case class Const[A, B](a: A)

  def optionToEither[A](opt: Option[A]): Either[Const[Unit, A], Identity[A]] =
    opt
      .map(a ⇒ Right(Identity(a)))
      .getOrElse(Left(Const(())))

  def eitherToOption[A](either: Either[Unit, A]): Option[A] =
    either
      .map(Some.apply)
      .getOrElse(None)

  /**
    * Let's prove the isomorphism using equational reasoning
    * Either (Const () a) (Identity a)
    * Definition of Identity (fmap-wise)
    * = Either (Const () a) a
    * Definition of Const (fmap-wise)
    * = Either () a
    * Canonical form of coproducts (in haskell)
    * = () | a
    */

  // 3 - Let’s try another data structure. I call it a PreList because it’s a precursor to a List. It replaces
  // recursion with a type parameter b.
  // data PreList a b = Nil | Cons a b
  // You could recover our earlier definition of a List by recursively applying PreList to itself (we’ll see how
  // it’s done when we talk about fixed points).
  // Show that PreList is an instance of Bifunctor.
  /**
    * To show that PreList is a bifunctor, let's show that it is functorial in both arguments
    *
    * Cons a b is isomorphic with Pair a b and we know that Pair is a bifunctor
    *
    * Identity law for Nil, fmap id Nil = id Nil
    * fmap id Nil
    * Definition of fmap
    * = id Nil
    *
    * Composition law, fmap (g . f) Nil = (fmap g . fmap f) Nil
    * fmap (g . f) Nil
    * Definition of fmap
    * = (g . f) Nil
    * Definition of fmap
    * = fmap g (f Nil)
    * Definition of fmap
    * = fmap f (fmap f Nil)
    * Definition of composition
    * = (fmap f . fmap g) Nil
    *
    * PreList is the coproduct of two functorial type constructors (Nil is a functor, Cons is a bifunctor)
    * Thus PreList is a bifunctor
    *
    */

  //4 - Show that the following data types define bifunctors in a and b
  // data K2 c a b = K2 c
  // data Fst a b = Fst a
  // data Snd a b = Snd b
  // For additional credit, check your solutions agains Conor McBride’s paper
  // Clowns to the Left of me, Jokers to the Right.
  /**
    * Let's show that K2 defines a bifunctor in a and b
    *
    * Since a and b are both ignored, we will test the laws for both arguments at the same time
    * Identity law, fmap id (K2 c) = id (K2 c)
    * fmap id (K2 a)
    * Definition of fmap
    * = K2 (id a)
    * Definition of id
    * = K2 id
    * Definition of id
    * = id (K2 id)
    *
    * Composition law, fmap (g . f) (K2 c) = (fmap g . fmap f) (K2 c)
    * fmap (g . f) (K2 c)
    * Definition of fmap
    * = K2 ((g . f) c)
    * Definition of fmap
    * = fmap g (K2 (f c))
    * Definition of fmap
    * = fmap g (fmap f (K2 c))
    * Definition of composition
    * = (fmap g . fmap f) (K2 c)
    *
    * K2 is a bifunctor
    *
    *
    * Fst and Snd are both isomorphic to the Const functor which defines a bifunctor.
    *
    */

  // 5 - Define a bifunctor in a language other than Haskell.
  // Implement bimap for a generic pair in that language.
  trait MyBiFunctor[F[_, _]] {
    def lmap[A, B, C](f: A => C)(fab: F[A, B]): F[C, B] = bimap(f, identity[B])(fab)
    def rmap[A, B, C](f: B => C)(fab: F[A, B]): F[A, C] = bimap(identity[A], f)(fab)
    def bimap[A, B, C, D](f: A => C, g: B => D)(fab: F[A, B]): F[C, D] =
      (lmap[A, B, C](f) _).andThen(rmap(g))(fab)
  }
  new MyBiFunctor[Pair] {
    override def bimap[A, B, C, D](f: A => C, g: B => D)(fab: Pair[A, B]): Pair[C, D] =
      fab.copy(a = f(fab.a), b = g(fab.b))
  }

  // 6 - Should std::map be considered a bifunctor or a profunctor in the two template arguments Key and T?
  // How would you redesign this data type to make it so?
  /**
    * Map[A, B] defines a bifunctor in arguments A and B but no Profunctor.
    */
  new Bifunctor[Map] {
    override def bimap[A, B, C, D](fab: Map[A, B])(f: A => C, g: B => D): Map[C, D] =
      fab.map {
        case (k, v) => f(k) -> g(v)
      }
  }
  new Profunctor[Map] {
    override def dimap[A, B, C, D](fab: Map[A, B])(f: C => A)(g: B => D): Map[C, D] = ???
  }

  /** Example profunctor */
  new Profunctor[Function1] {
    override def dimap[A, B, C, D](fab: A => B)(f: C => A)(g: B => D): C => D =
      f.andThen(fab).andThen(g)
  }

}
