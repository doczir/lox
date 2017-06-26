package com.lexan.lox.source

import org.scalatest.prop.PropertyChecks
import org.scalatest.{FlatSpec, Matchers}

class SourceSpec extends FlatSpec with Matchers with PropertyChecks {
  "A source span" should "calculate it's length properly" in {
    forAll { (idx1: Int, idx2: Int) =>
      whenever(idx1 >= 0 && idx2 >= 0) {
        val start = if(idx1 < idx2) idx1 else idx2
        val end = if(idx1 < idx2) idx2 else idx1

        val sp = SourceSpan(SourceLocation(start), SourceLocation(end))
        sp.length should equal(end - start)

      }
    }
  }

  "A source" should "allow indexing" in {
    forAll { str: String =>
      val src = Source(str)

      str.zipWithIndex.foreach {
        case (char, idx) => src(idx) should equal(char)
      }
    }
  }

  it should "allow retrival of a span" in {
    forAll { str: String =>
      whenever(!str.isEmpty) {
        val src = Source(str)

        val idx1 = (str.length * 0.1).toInt
        val idx2 = (str.length * 0.8).toInt

        val sp = SourceSpan(SourceLocation(idx1), SourceLocation(idx2))
        src.span(sp) should equal(str.substring(idx1, idx2))
      }
    }
  }
}
