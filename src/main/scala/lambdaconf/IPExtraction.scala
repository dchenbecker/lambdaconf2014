package lambdaconf

import scala.util.Try

/**
 * In this exercise we'll be writing extractors for IP address matching. To simplify things, we can decompose
 * the extraction into the individual octets (0-255) and then the IPv4 octet quad. We can then further provide a
 * matcher for CIDR (ip/mask bits) entries.
 */
object IPExtraction {
  object Octet {
    // Lest this Pattern Matching hammer is making you see everything as a nail, note that this is probably more easily
    // implemented without patterns
    def unapply(s: String): Option[Int] = ???
  }

  // A nicety so that the CIDR match can return something that's bindable as a case class rather than a String
  case class IPv4(a: Int, b: Int, c: Int, d: Int)

  object IPv4 {
    /*
     This can be implemented as a pattern match leveraging your Octet extractor above. Remember, String.split(Char)
     returns an Array.
     */
    def unapply(s: String): Option[(Int, Int, Int, Int)] = ???
  }

  object IPv6Prefix {
    /*
      IPv6 is slightly different because people normally use a condensed representation of
      0102:0300::0004 where the "::" signifies that it can be replaced with zero octets (so you don't
      have to write them all out) and the 4-character segments are hexadecimal. In other words, that address
      is really 0102:0300:0000:0000:0000:0000:0000:0004. For this exercise, we want to return all of the byte
      values for the prefix (up to the ::). A helper function, decodeSegment, has been provided to return a List[Int]
      from a segment so that you can leverage flatMap.

      Fun trivia: IPv4 also support omitting intermediate zeroes, so 127.1 == 127.0.0.1 (try it!)
     */
    def unapplySeq(s: String): Option[List[Int]] = ???

    // The toByte.toInt after parse is to ensure that sign is extended properly
    def decodeSegment(s: String) = s.grouped(2).map(Integer.parseInt(_, 16).toByte.toInt).toList
  }

  object CIDR {
    /**
     * For CIDR parsing, we have a format of ip/mask and require validation that the mask is an integer between 0 and
     * 32, inclusive. Utilize the matchers you've defined above in addition to any new logic needed for validation.
     */
    def unapply(s: String): Option[(IPv4, Int)] = ???
  }
}
