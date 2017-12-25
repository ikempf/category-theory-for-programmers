package com.ikempf.part1.chapter2_types_and_functions

import com.ikempf.part1.chapter2_types_and_functions.Challenges2.memoize
import org.scalatest.FlatSpec
import org.scalatest.Matchers

import scala.util.Random

class Challenges2Test extends FlatSpec with Matchers {

  "Memoize" should "only evaluate function once" in {
    def f: Int ⇒ Int = a ⇒ {
      Thread.sleep(1000)
      a
    }

    val fastF = memoize(f)

    time(Range(0, 100).map(_ ⇒ fastF(0)).toList) should be < 2000l
  }

  // 2 - Try to memoize a function from your standard library that you normally use to produce random numbers.
  // Does it work?
  /**
    * Predictably, the memoized generator does not generate random numbers
    * The memoized function's behaviour is different from the original function because the
    * former has side effects (using global state as seed) so it can't be replaced by it's seed,
    * it's not referentially transparent
    */
  it should "memoize random number generator" in {
    val memoizedRand = memoize((_: Any) ⇒ Random.nextInt)

    // Is not random anymore
    // memoizedRand("") should not equal memoizedRand("")
  }

  // 3 - Most random number generators can be initialized with a seed. Implement a function that takes a seed,
  // calls the random number generator with that seed, and returns the result. Memoize that function. Does it work?
  /**
    * Given a seed the generated sequence is always the same
    * The memoization works because the memoized function is pure
    */
  it should "memoize random number generator given a seed" in {
    val memoizedRand = memoize(new Random(_: Int).nextInt)

    memoizedRand(5) should equal(new Random(5).nextInt)
  }

  // 4 - Which of these C++ functions are pure? Try to memoize them and observe what happens when you call them
  // multiple times: memoized and not.
  // 4.1 - The factorial function from the example in the text.
  /** Factorial is pure, memoization works */
  it should "memoize factorial function" in {
    val memoizedFact = memoize(Challenges2.fact)

    memoizedFact(7) should equal(Challenges2.fact(7))
  }

  // 4.2 - std::getchar()
  // Side-effecting std:getchar can't be memoized

  // 4.3
  // bool f() {
  // std::cout << "Hello!" << std::endl;
  // return true;
  // }
  /** f is impure, the side-effecting println isn't be memoized */
  it should "memoize hello function" in {
    val memoizedHello = memoize((_: Any) => Challenges2.f())

    // Memoized function doesn't println
    memoizedHello("") should equal(Challenges2.f())
  }

  // 4.4
  // int f(int x)
  // {
  //   static int y = 0;
  //   y += x;
  //   return y;
  // }
  /** f has local mutable state but is pure, memoization works */
  it should "memoize identity function" in {
    val memoizedHello = memoize(Challenges2.f)

    memoizedHello(5) should equal(Challenges2.f(5))
  }

  def time[A](f: ⇒ A): Long = {
    val start = System.currentTimeMillis()
    f
    System.currentTimeMillis() - start
  }

}
