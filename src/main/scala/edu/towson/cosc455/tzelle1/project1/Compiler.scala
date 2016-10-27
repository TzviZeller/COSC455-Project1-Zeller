package edu.towson.cosc455.tzelle1.project1

// Created by Tzvi on 10/11/2016.
// Compiler Project for Gittext

import scala.io.Source

object Compiler{
  val lex = new MyLexicalAnalyzer
  val sin = new MySyntaxAnalyzer

  var currentToken: String = ""
  var fileContents: String = ""

  def main(args: Array[String]) = {
    //checks for filetype and packs content into a string
    checkFile(args)
    readFile(args(0))

    //print(fileContents)

    //pass fileString to
    lex.start(fileContents)

    while(!lex.file.isEmpty) {
      //pass fileString to
      //lex.getNextToken()

      //checks current token for syntax
      //sin.gittex()
    }
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
