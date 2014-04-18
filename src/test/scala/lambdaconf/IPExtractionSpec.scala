package lambdaconf

import org.specs2.mutable.Specification
import org.specs2.ScalaCheck

/**
 * TODO: Add docs
 */
class IPExtractionSpec extends Specification with ScalaCheck {
  import IPExtraction._

  "IP extraction" should {
    "match only valid octets" in {
      List("12", "53", "275", "108", "-12").collect {
        case Octet(oct) => oct
      } must containAllOf(Seq(12, 53, 108)).exactly
    }

    "match a valid IP address" in {
      "12.24.53.123" must beLike {
        case IPv4(12, 24, 53, 123) => ok
      }
    }

    "not match an invalid IP address" in {
      "123.42.12.34.55" must beLike {
        case IPv4(_, _, _, _) => ko
        case _ => ok
      }

      "123.420.12.34" must beLike {
        case IPv4(_, _, _, _) => ko
        case _ => ok
      }
    }

    "match a valid CIDR entry" in {
      "129.22.4.3/16" must beLike {
        case CIDR(IPv4(129, 22, 4, 3), 16) => ok
      }
    }

    "not match an invalid IP in CIDR entry" in {
      "1234.23.45.67/22" must beLike {
        case CIDR(IPv4(_, _, _, _), _) => ko
        case _ => ok
      }
    }

    "not match an invalid mask in CIDR entry" in {
      "123.23.45.67/42" must beLike {
        case CIDR(IPv4(_, _, _, _), _) => ko
        case _ => ok
      }
    }

    "provide a working decodeSegment" in check {
      (a: Byte, b: Byte) =>
        IPv6Prefix.decodeSegment("%02x%02x".format(a, b)) mustEqual List(a.toInt, b.toInt)
    }
  }
}
