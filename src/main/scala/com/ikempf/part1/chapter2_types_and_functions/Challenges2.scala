package com.ikempf.part1.chapter2_types_and_functions

import scala.collection.mutable

object Challenges2 {

  // 1 - Define a higher-order function (or a function object) memoize in your favorite language. This function takes
  // a pure function f as an argument and returns a function that behaves almost exactly like f, except that it only
  // calls the original function once for every argument, stores the result internally, and subsequently returns this
  // stored result every time it’s called with the same argument. You can tell the memoized function from the original
  // by watching its performance. For instance, try to memoize a function that takes a long time to evaluate. You’ll
  // have to wait for the result the first time you call it, but on subsequent calls, with the same argument, you should
  // get the result immediately.
  def memoize[A, B](f: A ⇒ B): A ⇒ B = {
    val m = mutable.Map.empty[A, B]
    a ⇒
      m.getOrElse(a, {
        val value = f(a)
        m.put(a, value)
        value
      })
  }

  // 4
  def f(): Boolean = {
    println("Hello!")
    true
  }

  def f(x: Int): Int = {
    var y = 0
    y += x
    y
  }

  def fact(n: Int): Int =
    Range(2, n).product

  // 5 - How many different functions are there from Bool to Bool? Can you implement them all?
  /**
    * Bool domain has size 2 and codomain has size 2, so there are 2^2=4 functions
    * Including bottom type 2^0=1 we have 5 morphisms
    */
  val identity: Boolean ⇒ Boolean    = b ⇒ b
  val negation: Boolean ⇒ Boolean    = b ⇒ !b
  val constTrue: Boolean ⇒ Boolean   = _ ⇒ true
  val constFalse: Boolean ⇒ Boolean  = _ ⇒ false
  val constBottom: Boolean ⇒ Boolean = _ ⇒ ???

  // 6 - Draw a picture of a category whose only objects are the types Void, () (unit), and Bool;
  // with arrows corresponding to all possible functions between these types.
  // Label the arrows with the names of the functions.
  object TypesAndFunctions {
    sealed trait OVoid
    sealed trait OUnit
    sealed trait OBool

    case object VUnit extends OUnit

    case object VTrue  extends OBool
    case object VFalse extends OBool

    val voidIdentity: OVoid ⇒ OVoid = v ⇒ v
    val absurdUnit: OVoid ⇒ OUnit   = _ ⇒ VUnit
    val absurdTrue: OVoid ⇒ OBool   = _ ⇒ VTrue
    val absurdFalse: OVoid ⇒ OBool  = _ ⇒ VFalse

    val unitIdentity: OUnit ⇒ OUnit = u ⇒ u
    val constBottom: OUnit ⇒ OVoid  = _ ⇒ ???
    val constTrue: OUnit ⇒ OBool    = _ ⇒ VTrue
    val constFalse: OUnit ⇒ OBool   = _ ⇒ VFalse

    val boolIdentity: OBool ⇒ OBool = a ⇒ a

    val negate: OBool ⇒ OBool = {
      case VTrue  ⇒ VFalse
      case VFalse ⇒ VTrue
    }
    val bConstBottom: OBool ⇒ OVoid = _ ⇒ ???
    val bConstUnit: OBool ⇒ OUnit   = _ ⇒ VUnit
    val bConstTrue: OBool ⇒ OBool   = _ ⇒ VTrue
    val bConstFalse: OBool ⇒ OBool  = _ ⇒ VFalse
  }
  /**
    * The corresponding category would have
    * Objects: Void, Unit, Bool
    * Morphisms: derived from the 14 functions above
    */

}
