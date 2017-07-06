package com.lexan.lox.grammar

import com.lexan.lox.grammar.diagnostics.LexicalError
import com.lexan.lox.source.SourceCode

class Lexer(val source: SourceCode) {


  private val code = source.source

  private var start = 0
  private var current = 0

  def tokenize: Stream[Token] = Stream.continually {
    skipWhitespace()

    if(!isAtEnd) scanToken()
    else EndOfFile
  }

  private def scanToken(): Token = {
    start = current
    val c = advance()
    c match {
      case '(' => LeftParen
      case ')' => RightParen
      case '+' => Plus
      case '/' => Slash
      case '*' => Star
      case '-' =>
        if (peek().isDigit) scanNumber()
        else Minus
      case _ => {
        if (c.isDigit) scanNumber()
        else scanError("Unexpected token: %s")
      }

    }
  }

  private def scanNumber(): Token = {
    while (peek().isDigit)
      advance()

    Integral(currentTokenString.toLong)
  }

  private def scanError(msg: String): Token = {
    while (!peek().isWhitespace && peek() != Lexer.EOF)
      advance()

    val error = new LexicalError(msg.format(currentTokenString))
    Error(error)
  }

  private def skipWhitespace() = {
    while (peek().isWhitespace)
      advance()
  }

  private def isAtEnd = current >= code.length

  private def advance(): Char = {
    current += 1
    if (current - 1 >= code.length) Lexer.EOF
    else code(current - 1)
  }

  private def peek(): Char =
    if (current >= code.length) Lexer.EOF
    else code(current - 1)

  private def currentTokenString = code.substring(start, current)

}

object Lexer {
  private val EOF = '\0'
}
