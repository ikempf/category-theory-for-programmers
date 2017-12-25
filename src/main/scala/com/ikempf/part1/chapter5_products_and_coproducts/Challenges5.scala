package com.ikempf.part1.chapter5_products_and_coproducts

object Challenges5 {

  // 1 Show that the terminal object is unique up to unique isomorphism.
  /**
    * We know by it's universal construction that the terminal object is an object with exactly one arrow coming from
    * any other object of the category.
    * Let's consider two terminal objects A and B. By the terminal object's universal construction,
    * they both must have one arrow coming from any other object, meaning, that two morphisms f: A => B and g: B => A
    * exist in the category. Since there can only be one morphism going from A to A and from B to B
    * (again, this is due to the terminal object's universal construction) we know that g.f = idA and f.g = idB.
    * This means that g is the inverse of f, this is the universal construction of an isomorphism making A and B
    * isomorphic.
    */

  // 2 What is a product of two objects in a poset? Hint: Use the universal construction.
  /**
    * The product (if it exists) of two objects A and B in a poset is the biggest object that is smaller than A and B.
    *
    * Example: Let's consider the partially ordered set containing (-2, -1, 0, 1, 2) with the relation "<".
    * Let's find the product of the objects 0 and 1.
    * The (best) product of 0 and 1 is -1 with the following arrows -1 => 0 and -1 => 1.
    * -1 is a better product than -2 since there is a morphism -2 => -1 that factorizes -2 => 0 and -2 => 1
    *
    * Example 2: Let's consider the partially ordered set containing
    * (Object, Iterable, Collection, List, ArrayList, Throwable, Exception, RuntimeException)
    * with the relation "is subtype of".
    * The product of List and ArrayList is Collection.
    * The product of Collection and ArrayList is Iterable
    * The product of Exception and List is Object
    */

  // 3 What is a coproduct of two objects in a poset?
  /**
    * The product (if it exists) of two objects in a poset is the smallest object bigger than A and B.
    *
    * Example: Let's consider the partially ordered set containing (-2, -1, 0, 1, 2) with the relation "<".
    * The coproduct of -2 and 0 is 1
    * The coproduct of -1 and 0 is 1
    */

  // 4 Implement the equivalent of Haskell Either as a generic type in your favorite language (other than Haskell).
  sealed trait EitherN[+A, +B]
  case class LeftN[+A](a: A) extends EitherN[A, Nothing]
  case class RightN[+B](b: B) extends EitherN[Nothing, B]

  // 5 Show that Either is a “better” coproduct than int equipped with two injections:
  // int i(int n) { return n; }
  // int j(bool b) { return b? 0: 1; }
  def i(n : Int): Int = n
  def j(b: Boolean): Int = if (b) 0 else 1

  /**
    * To prove that Either[Bool, Int] is a better coproduct than int as defined by universal construction we will try
    * to find a function m that factorizes i and j
    */
  def m(e: EitherN[Boolean, Int]): Int = {
    e match {
      case LeftN(l) => j(l)
      case RightN(r) => i(r)
    }
  }

  // 6 Continuing the previous problem: How would you argue that int with the two injections i and j cannot be
  // “better” than Either?
  /**
    * int with two injection i and j can not be better than Either since we can not "trace back" the original
    * domain for the overlapping values 0 and 1 (they can come either from int/i or bool/j)
    * This is why we can not factorize left and right
    */
  def mBis(in: Int): Either[Boolean, Int] =
    if (in != 0 && in != 1)
      Right(in)
    else
      ??? // Left or Right ?

  // 7 Still continuing: What about these injections?
  def i2(n: Int): Int =
    if (n < 0) n
    else n + 2

  def j2(b: Boolean): Int =
    if (b) 0
    else 1
  /**
    * We will try to factorize i2 and j2
    * Seems to be possible (if we ignore int overflow)
    */
  def m2(in: Int): Int =
    if (in == 0)
      j2(true)
    else if (in != 1)
      j2(false)
    else
      i2(in)

  // 8 Come up with an inferior candidate for a coproduct of int and bool that cannot be better than Either because
  // it allows multiple acceptable morphisms from it to Either.
  /**
    * An inferior candidate for a coproduct of int and bool would be Either[Float, Either[Boolean, Int]]
    * or (Int, Either[Boolean, Int])
    */

}
