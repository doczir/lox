package com.lexan.lox.grammar.diagnostics

abstract class CompileError(msg: String, cause: Throwable = null) extends Throwable(msg, cause)
class LexicalError(msg: String) extends CompileError(msg)
