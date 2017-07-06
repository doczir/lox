package com.lexan.lox.grammar

import com.lexan.lox.grammar.diagnostics.CompileError

sealed trait Token

case class Integral(value: Long) extends Token

object LeftParen extends Token

object RightParen extends Token

object Plus extends Token

object Minus extends Token

object Slash extends Token

object Star extends Token

object EndOfFile extends Token

case class Error(error: CompileError) extends Token

