package edu.towson.cosc455.tzelle1.project1

//Created by Tzvi on 10/11/2016
//lexical analyzer is "spell" checking file

class MyLexicalAnalyzer extends LexicalAnalyzer {
  val lookUp = new Array[String](17)
  var token = new Array[Char](50);

  var nextChar: Char;
  var tokenLength: Int = 0
  var position: Int = 0
  var space: Char = ' '

  def start(file: String): Unit = {
    InishalizeLookupArray()
    position = 0

    getChar(file)
    getNextToken()
  }

  override def addChar() = {
    if (tokenLength <= 98) {
      tokenLength += 1
      //token += nextChar
    }
    if (nextChar != space) {
      while (nextChar != space) {
        getChar()
      }
    }
    tokenLength = 0
    addChar()
  }

  override def getChar(file: String) = {
    if (position < file.length()) {
      nextChar = file.charAt(position)
      position += 1
    }
    else {
      nextChar = '\n'
    }
  }

  override def getNextToken(): Unit = {
    val c = getChar()
  }

  override def lookup(token: String): Boolean = {
    if (!lookUp.contains(token)) {
      println("Lexical Error: " + token + "is not valid")
      return false
    }
    return true
  }

  def setCurrent(currentTokrn: String): Unit = {
    Compiler.currentToken = currentTokrn
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
    lookUp(12) = "\\";
    lookUp(13) = "[";
    lookUp(14) = "(";
    lookUp(15) = ")";
    lookUp(16) = "=";
    lookUp(17) = "![";
  }
}