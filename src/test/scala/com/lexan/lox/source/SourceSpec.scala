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

  it should "allow merging" in {
    {
      val sp1 = SourceSpan(SourceLocation(10), SourceLocation(20))
      val sp2 = SourceSpan(SourceLocation(5), SourceLocation(30))

      val result = sp1.merge(sp2)

      result should equal(sp2)
    }
    {
      val sp1 = SourceSpan(SourceLocation(5), SourceLocation(30))
      val sp2 = SourceSpan(SourceLocation(10), SourceLocation(20))

      val result = sp1.merge(sp2)

      result should equal(sp1)
    }
  }

  "A source" should "allow indexing" in {
    forAll { str: String =>
      val src = SourceCode(str)

      str.zipWithIndex.foreach {
        case (char, idx) => src(idx) should equal(char)
      }
    }
  }

  it should "allow retrival of a span" in {
    forAll { str: String =>
      whenever(!str.isEmpty) {
        val src = SourceCode(str)

        val idx1 = (str.length * 0.1).toInt
        val idx2 = (str.length * 0.8).toInt

        val sp = SourceSpan(SourceLocation(idx1), SourceLocation(idx2))
        src.span(sp) should equal(str.substring(idx1, idx2))
      }
    }
  }
}
