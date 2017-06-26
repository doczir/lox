package com.lexan.lox.source

case class SourceLocation(idx: Int)
case class SourceSpan(begin: SourceLocation, end: SourceLocation) {
  lazy val length: Int = end.idx - begin.idx
}

case class Source(source: String) {

  def span(sourceSpan: SourceSpan): String = source.substring(sourceSpan.begin.idx, sourceSpan.end.idx)

  def apply(pos: Int): Char = source(pos)

}