package com.lexan.lox.source

case class SourceLocation(idx: Int)
case class SourceSpan(begin: SourceLocation, end: SourceLocation) {
  lazy val length: Int = end.idx - begin.idx

  def merge(other: SourceSpan) =
    SourceSpan(
      if(begin.idx < other.begin.idx) begin else other.begin,
      if(end.idx > other.end.idx) end else other.end)
}

case class SourceCode(source: String) extends Iterable[(Char, Int)] {

  lazy val lines: Stream[String] = source.lines.toStream

  def span(sourceSpan: SourceSpan): String = source.substring(sourceSpan.begin.idx, sourceSpan.end.idx)

  def apply(pos: Int): Char = source(pos)

  override def iterator: Iterator[(Char, Int)] = source.iterator.zipWithIndex
}