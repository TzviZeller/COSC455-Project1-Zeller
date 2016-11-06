package edu.towson.cosc455.tzelle1.project1

//Created by Tzvi on 10/11/2016
//lexical analyzer is "spell" checking file

import scala.collection.mutable.ArrayBuffer

class helpmegod extends LexicalAnalyzer {
  //value decleration
  val lookUp = new Array[String](20)
  var token = new ArrayBuffer[Char](50)
  var fileHolder: Array[Char] = Array()
  var nextChar: Char = ' '
  var filePosition: Int = -1

  //starter method that primes getnexttoken
  def start(file1: String): Unit = {
    InishalizeLookupArray()
    fileHolder = file1.toCharArray
  }

  //grabs next character
  override def getChar(): Unit = {
    if (filePosition < fileHolder.length) {
      filePosition += 1
      nextChar = fileHolder.charAt(filePosition)
    }
  }

  //called from getNext to manage new char grab
  override def addChar() {
      token += nextChar
    
  }

  //method is main driver that recusevly pulls text into tokens
  override def getNextToken(): Unit = {
    getChar()
    getNotText()

    if (nextChar.equals('+') || nextChar.equals('=') || nextChar.equals('#') || nextChar.equals('(') || nextChar.equals(')') || nextChar.equals(']')) {
      addChar()
      pakage()
    }
    else if (nextChar.equals('\\')) {
      addChar()
      getChar()
      while (!nextChar.equals('[') || nextChar != '\n') {
        if (nextChar.equals('\r'))
          getChar()
        else {
          addChar()
          getChar()
        }
      }
      if (nextChar.equals('[')) {
        addChar()
      }
      pakage()
    }

    else if (nextChar.equals('*')) {
      addChar()
      getChar()
      if (nextChar.equals('*')) {
        addChar()
        getChar()
        pakage()
      }
      else {
        filePosition -= 1
        pakage()
      }
    }
    else if (nextChar.equals('!')) {
      addChar()
      getChar()
      if (nextChar.equals('['))
        addChar()
      pakage()
    }

    else {
      addChar()
      getChar()
      while (CONSTANTS.TOKENS.contains(nextChar)) {
        addChar()
        getChar()
      }
    }
  }

  //method  call for token validation using submethods
  override def lookUP(posibleToken: String): Boolean = {
    val posibleToken = token.toString()
    if (lookUp.contains(posibleToken)) {
      return true
    }
    else {
      println("Lexical Error: " + posibleToken + " is not valid")
      System.exit(1)
      return false
    }
  }

  //pakage string
  def pakage(): Unit = {
    val posibleToken: String = token.mkString //make array into string to check
    println(posibleToken)
    if (lookUp.contains(posibleToken)) {
      setCurrent(posibleToken) //set token in compiler
      token.clear()
    }
  }

  //sets current token in compiler to nexttoken
  def setCurrent(currentToken: String): Unit = {
    Compiler.currentToken = currentToken
  }

  //loop for retreving terminal tokens/chras
  def getNotText(): Unit = {
    while (nextChar.equals(' ') || nextChar.equals('\r') || nextChar.equals('\n') || nextChar.equals('\t')) {
      getChar()
    }
  }

  //defines lookup
  def InishalizeLookupArray() = {
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
    lookUp(18) = "]";
  }
}