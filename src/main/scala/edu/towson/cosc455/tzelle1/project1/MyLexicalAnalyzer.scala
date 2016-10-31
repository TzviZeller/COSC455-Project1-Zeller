package edu.towson.cosc455.tzelle1.project1

//Created by Tzvi on 10/11/2016
//lexical analyzer is "spell" checking file

import scala.collection.mutable.ArrayBuffer
import scala.collection.mutable.ListBuffer

class MyLexicalAnalyzer extends LexicalAnalyzer {
  val lookUp = new Array[String](20)
  var token = new ArrayBuffer[Char](50)
  var fileHolder: List[Char] = List()

  var file: String = "red"
  var nextChar: Char = ' '
  var tokenLength: Int = 0
  var space: Char = ' '
  var filePosition: Int = 0

  def start(file1: String): Unit = {
    InishalizeLookupArray()
    file = file1;
    fileHolder = file.toList
  }

  //called from getNext to manage new char grab
  override def addChar() = {
    if (tokenLength <= 98) {
      //check for resonible length
      if (!terminal()) {
        //if not terminal then
        tokenLength += 1
        token += nextChar

        getChar() //get next char
        addChar() //recursie call
      }
      else {
        if (!nextChar.equals('\r')) {
          token += nextChar

          val posibleToken: String = token.mkString //make array into string to check
          println(posibleToken)
          if (lookUP(posibleToken)) {
            //lookup token
            setCurrent(posibleToken) //set token in compiler
            token.clear()
          }
        }
        else
          getChar()
      }
    }
    else {
      //token was to long
      println("LEXICAL ERROR - The found lexeme is too long!")
      System.exit(1)
    }
  }

  //simply rips head of read file
  override def getChar(): Unit = {
    if (!fileHolder.isEmpty) {
      nextChar = fileHolder.head
      fileHolder = fileHolder.slice(1, fileHolder.length)
      filePosition += 1
    }
  }

  override def getNextToken(): Unit = {
    tokenLength = 0 //reset lenth property of currentToken
    nextChar=' '

    getChar()
    getNotText() //to retrive terminal chars
    addChar()
    //getChar()//heeh
    if (!nextChar.equals(' ') || !nextChar.equals('\r') || !nextChar.equals('\n') || !terminal()) //if not terminal cut string
      getChar()
    while ((nextChar != '\n') && (nextChar != ' ') && !terminal()) {
      //while current char is not terminal get next until terminal
      addChar()
      if (nextChar != ' ' || !nextChar.equals('\r') || !nextChar.equals('\n') || !terminal())
        getChar()
    }

    /*val posibleToken: String = token.mkString //takes char sting and makes it a token
    if (lookUP(posibleToken)) {
      //checks valid
      setCurrent(posibleToken) //calls set for compiler
      token.clear() //clears array}*/
    addChar()

  }

  //method  call for token validation using submethods
  override def lookUP(token: String): Boolean = {
    if (lookUp.contains(token)) {
      return true
    }
    else if (token.endsWith("]") || token.endsWith("[")|| token.endsWith(")") || token.endsWith("(") ||token.endsWith("\n")  || token.endsWith("="))
      return true
    else {
      println("Lexical Error: " + token + " is not valid")
      System.exit(1)
      return false
    }
  }

  //sets current token in compiler to nexttoken
  def setCurrent(currentToken: String): Unit = {
    Compiler.currentToken = currentToken
  }

  //loop for retreving terminal tokens/chras
  def getNotText(): Unit = {
    while (nextChar.equals(' ') || nextChar.equals('\r') || nextChar.equals('\n') || terminal()) {
      if (terminal()) {
        addChar()
      }
      getChar()
    }
  }

  //method for testing if it is a termnal token
  def terminal(): Boolean = {
    if (nextChar.equals(']') || nextChar.equals('#') || nextChar.equals('*') || nextChar.equals(lookUp(11)) || nextChar.equals('[')
      || nextChar.equals('(') || nextChar.equals(')') || nextChar.equals('+') || nextChar.equals('=') || nextChar.equals('\n')) {
      return true
    }
    else
      return false
  }

  //defines lookup
  def InishalizeLookupArray() = {
    lookUp(0) = "\\BEGIN\r\n";
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
    lookUp(19) = "\r\n";
    lookUp(19) = " ";


  }
}