package edu.towson.cosc455.tzelle1.project1

// Created by Tzvi on 10/11/2016.
// Compiler Project for Gittext

import scala.io.Source

object Compiler {
  //inisalise classes and vars
  val lex = new MyLexicalAnalyzer
  val sin = new MySyntaxAnalyzer
  val sam = new MySemanticAnalyzer
  var currentToken: String = ""
  var fileContents: String = ""

  //driver for compiler
  def main(args: Array[String]) = {
    //checks for filetype and packs content into a string
    checkFile(args)
    readFile(args(0))

    //formation for printout
    println("File passed in: ")
    print(fileContents)
    println()
    println()
    println("Processing File: ")
    println("-------")

    //pass fileString to
    lex.start(fileContents)

    //loops till file empty
    while (lex.filePosition < lex.filesize) { //@@@
      //gets current token
      lex.getNextToken()
      //checks current token for syntax
      //sin.gittex()
    }

    //calls samantic analyzer
    println("File has been read and checked")
    println("File is being convereted to HTML")
    //sam.symantics()
  }

  //packs file into a string
  def readFile(file: String) = {
    val source = scala.io.Source.fromFile(file)
    fileContents = try source.mkString finally source.close()
  }

  //checks file type and args
  def checkFile(args: Array[String]) = {
    if (args.length != 1) {
      println("USAGE ERROR: Wrong number of args")
      System.exit(1)
    }
    else if (!args(0).endsWith(".mkd")) {
      println("USAGE ERROR: Wrong Extension Type")
      System.exit(1)
    }
  }
}