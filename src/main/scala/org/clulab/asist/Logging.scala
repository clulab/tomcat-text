//  DialogAgent
//
//  Author:  Joseph Astier, Adarsh Pyarelal
//  Date:  2021 January
//
//
package org.clulab.asist

trait Advisory {
  def text(owner: String, str: String): String = 
    "%s: %s".format(owner,str)
}

/** Report a problem to stderr */
object Error extends Advisory {
  def apply(owner: String, str: String): Unit = 
    System.err.println(text(owner, str))
  def apply(owner: String, t: Throwable): Unit = 
    apply(owner, t.toString)
}

/** Report a str to stdout */
object Info extends Advisory {
  def apply(owner: String, str: String): Unit =
    System.out.println(text(owner, str))
}
