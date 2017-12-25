package com.ikempf.part1.chaper1_the_essence_of_composition

object Challenges1 {

  // 1 - Implement, as best as you can, the identity function in your favorite language (or the second favorite,
  // if your favorite language happens to be Haskell).
  def identity[A]: A ⇒ A =
    a ⇒ a

  // 2 - Implement the composition function in your favorite language. It takes two functions as arguments and returns
  // a function that is their composition.
  def composition[A, B, C]: (A ⇒ B) ⇒ (B ⇒ C) ⇒ (A ⇒ C) =
    f ⇒ g ⇒ a ⇒ g(f(a))

  def composition2[A, B, C]: ((A ⇒ B), (B ⇒ C)) ⇒ (A ⇒ C) =
    (f, g) ⇒ a ⇒ g(f(a))

  def composition3[A, B, C]: ((A ⇒ B), (B ⇒ C)) ⇒ (A ⇒ C) =
    (f, g) ⇒ f.andThen(g)

  // 4 - Is the world-wide web a category in any sense? Are links morphisms?
  /**
    * Premise: We consider web-pages as objects and links as morphisms
    * Identity OK: Each web-page has a link to itself (debatable...)
    * Composition NOK: If a page A links to a page B and the page B links to a page C, there is not necessarily a
    * link going from A to C.
    *
    * Premise: We consider web-pages as objects and links as "accessibility/can go to"
    * Identity OK: Each web-page has a link to itself (debatable...)
    * Composition OK: If a page A links to a page B and the page B links to a page C, C can be access from A
    *
    */

  // 5 - Is Facebook a category, with people as objects and friendships as morphisms?
  /**
    * Identity OK: Everybody is a friend with himself
    * Composition NOK: If A is friend with B and B is friend with C, A is not necessarily a fiend with C
    *
    * Premise: We consider people as objects and "access/contactability" as morhisms
    * Identity OK: Everybody is a friend with himself
    * Composition OK: If A is friend with B and B is friend with C, A can contact C
    *
    */

  // 6 - When is a directed graph a category?
  /**
    * A directed graph is a category if all nodes are linked to themselves and for all pairs of links A -> B and
    * B -> C there exists a link A -> C
    *
    */

}
