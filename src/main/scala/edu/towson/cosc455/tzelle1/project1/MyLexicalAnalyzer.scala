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
  var position: Int = 0
  var space: Char = ' '

  def start(file1: String): Unit = {
    InishalizeLookupArray()
    file = file1;
    position = 0
  }

  override def addChar() = {
    if (tokenLength <= 98) {
      tokenLength += 1
      token(tokenLength) = nextChar
      token(tokenLength) = 0
    }
    else {
      println("LEXICAL ERROR - The found lexeme is too long!")
      if (nextChar != space) {
        while (nextChar != space) {
          getChar()
        }
      }
      tokenLength = 0
      addChar()
    }
  }

  override def getChar(): Unit = {
      nextChar = file.head
  }

  override def getNextToken(): Unit = {
    val c = getChar()
  }

  override def lookup(token: String): Boolean = {
    if (!lookUp.contains(token)) {
      println("Lexical Error: " + token + "is not valid")
      System.exit(1)
    }
    return true
  }

  def setCurrent(currentToken: String): Unit = {
    Compiler.currentToken = currentToken
  }

  def notText(): Unit ={
    while(nextChar!=' ' || nextChar!="\n" || terminal(nextChar)){
      getChar()
    }
  }

  def terminal(char: Char): Boolean ={
    if(char==lookUp(3,4,10,11,13,14,15,16)|| char == '!' || char == '\\'){
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