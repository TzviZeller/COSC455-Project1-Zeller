package edu.towson.cosc455.tzelle1.project1

//Created by Tzvi on 10/11/2016
//lexical analyzer is "spell" checking file

import scala.collection.mutable.ArrayBuffer


class MyLexicalAnalyzer extends LexicalAnalyzer {
  val lookUp = new Array[String](17)
  var token = new ArrayBuffer[Char](50);

  var file: String = "red"
  var nextChar: Char = ' '
  var tokenLength: Int = 0
  var space: Char = ' '

  def start(file1: String): Unit = {
    InishalizeLookupArray()
    file = file1;
    getChar()//???
  }

  override def addChar() = {
    if (tokenLength <= 98) {
      if (nextChar != "\n" && !terminal()) {
        tokenLength += 1
        token += nextChar

        getChar()
        addChar()

      }
      else {
        if (terminal()) {
          token += nextChar
        }
        val posibleToken: String = token.mkString
        if (lookUP(posibleToken)) {
          setCurrent(posibleToken)
          token.clear()
        }
      }
    }
    else {
      println("LEXICAL ERROR - The found lexeme is too long!")
      System.exit(1)
    }
  }

  override def getChar(): Unit = {
    nextChar = file.head
    token.remove(0)
  }

  override def getNextToken(): Unit = {
    tokenLength = 0

    getNextToken()
    addChar()
    if (nextChar != ' ' || nextChar != "\n" || !terminal())
      getChar()

    while ((nextChar != '\n') && (nextChar != ' ') && !terminal()) {
      addChar()
      if (nextChar != ' ' || nextChar != "\n" || !terminal())
        getChar()
    }

    val posibleToken: String = token.mkString
    if (lookUP(posibleToken)) {
      setCurrent(posibleToken)
      token.clear()
    }
  }

  override def lookUP(token: String): Boolean = {
    if (!lookUp.contains(token)) {
      println("Lexical Error: " + token + "is not valid")
      System.exit(1)
    }
    return true
  }

  def setCurrent(currentToken: String): Unit = {
    Compiler.currentToken = currentToken
  }

  def notText(): Unit = {
    while (nextChar != ' ' || nextChar != "\n" || terminal()) {
      getChar()
    }
  }

  def terminal(): Boolean = {
    if (nextChar == lookUp(3) || nextChar == lookUp(4) || nextChar == lookUp(10) || nextChar == lookUp(11) || nextChar == lookUp(13)
      || nextChar == lookUp(14) || nextChar == lookUp(15) || nextChar == lookUp(16) || nextChar == '!' || nextChar == '\\') {
      return true
    }
    else
      return false
  }

  def InishalizeLookupArray() = {
    val lookUp = new Array[String](17)
    lookUp(0) = "\\BEGIN";
    lookUp(1) = "\\END";
    lookUp(2) = "\\TITLE[";
    lookUp(3) = "]";
    lookUp(4) = "#";
    lookUp(5) = "\\PARAB";
    lookUp(6) = "\\PARAE";
    lookUp(7) = "\\DEF[";
    lookUp(8) = "\\USE[";
    lookUp(9) = "**";
    lookUp(10) = "*";
    lookUp(11) = "+";
    lookUp(12) = "\\\\";
    lookUp(13) = "[";
    lookUp(14) = "(";
    lookUp(15) = ")";
    lookUp(16) = "=";
    lookUp(17) = "![";
  }
}