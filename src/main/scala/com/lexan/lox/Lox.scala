package com.lexan.lox

import scala.io.StdIn

object Lox {

  def runRepl(): Unit = {
    Stream.continually(StdIn.readLine("|>")).takeWhile(_ != null).foreach{ line =>

      if(line startsWith ":")
        handleCommand(line)
      else
        run(line)

    }
  }

  private def handleCommand(line: String) = {
    line.split(" ")(0) match {
      case ":q" => System.exit(0)
    }

  }

  private def run(line: String) = {
    println(line)
  }

}
