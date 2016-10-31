package edu.towson.cosc455.tzelle1.project1

import scala.util

//Created by Tzvi on 10/11/2016.

class MySyntaxAnalyzer extends SyntaxAnalyzer {

  override def gittex(): Unit = ???

  override def title(): Unit = {
    if (Compiler.currentToken.equalsIgnoreCase(CONSTANTS.TITLEB)) {
      //add parse
      Compiler.lex.getNextToken()
    }
    else {
      println("Error: no title")
      System.exit(1)
    }
  }

  override def body(): Unit = ???

  //case statmen

  override def paragraph(): Unit = ???

  //case statmen

  override def innerText(): Unit = ???

  //case statmen

  override def heading(): Unit = {
    if (Compiler.currentToken.equalsIgnoreCase(CONSTANTS.HEADING)) {
      Compiler.lex.getNextToken()
    }
    else{
      println("Error: no begining tag")
      System.exit(1)
    }
  }

  override def variableDefine(): Unit = ???

  override def variableUse(): Unit = ???

  override def bold(): Unit = ???

  override def italics(): Unit = ???

  override def listItem(): Unit = ???

  override def innerItem(): Unit = ???

  override def link(): Unit = ???

  override def image(): Unit = ???

  override def newline(): Unit =
    if (Compiler.currentToken.equalsIgnoreCase(CONSTANTS.NEWLINE)) {
      //add parse
      Compiler.lex.getNextToken()
    }
    else {
      println("Error: no title")///
      System.exit(1)
    }

  //syntax defs form methods
  def docb(): Unit = {
    if (Compiler.currentToken.equalsIgnoreCase(CONSTANTS.DOCB)) {
      Compiler.lex.getNextToken()
    }
    else {
      println("Error: no begining tag")///
      System.exit(1)
    }
  }

  def doce(): Unit = {
    if (Compiler.currentToken.equalsIgnoreCase(CONSTANTS.DOCE)) {
      Compiler.lex.getNextToken()
    }
    else {
      println("Error: no begining tag")///
      System.exit(1)
    }
  }

  def titleb(): Unit = {
    if (Compiler.currentToken.equalsIgnoreCase(CONSTANTS.TITLEB)) {
      Compiler.lex.getNextToken()
    }
    else {
      println("Error: no begining tag")///
      System.exit(1)
    }
  }

  def headb(): Unit ={
    if (Compiler.currentToken.equalsIgnoreCase(CONSTANTS.HEADING)) {
      Compiler.lex.getNextToken()
    }
    else{
      println("Error:  tag") ///
      System.exit(1)
    }
  }

  def parb(): Unit ={
    if (Compiler.currentToken.equalsIgnoreCase(CONSTANTS.PARAB)) {
      Compiler.lex.getNextToken()
    }
    else{
      println("Error: \\PARAB is missing")///
      System.exit(1)
    }
  }

  def pare(): Unit ={
    if (Compiler.currentToken.equalsIgnoreCase(CONSTANTS.PARAE)) {
      Compiler.lex.getNextToken()
    }
    else{
      println("Error: \\PARAB is missing")///
      System.exit(1)
    }
  }

  def boldb(): Unit ={
    if (Compiler.currentToken.equalsIgnoreCase(CONSTANTS.BOLD)) {
      Compiler.lex.getNextToken()
    }
    else{
      println("Error: \\PARAB is missing")///
      System.exit(1)
    }
  }

  def italb(): Unit ={
    if (Compiler.currentToken.equalsIgnoreCase(CONSTANTS.ITALICS)) {
      Compiler.lex.getNextToken()
    }
    else{
      println("Error: \\PARAB is missing")///
      System.exit(1)
    }
  }

  def listItemb(): Unit ={
    if (Compiler.currentToken.equalsIgnoreCase(CONSTANTS.LISTITEM)) {
      Compiler.lex.getNextToken()
    }
    else{
      println("Error: \\PARAB is missing")///
      System.exit(1)
    }
  }

  def imageb(): Unit ={
    if (Compiler.currentToken.equalsIgnoreCase(CONSTANTS.IMAGEB)) {
      Compiler.lex.getNextToken()
    }
    else{
      println("Error: \\PARAB is missing")///
      System.exit(1)
    }
  }

  def useb(): Unit ={
    if (Compiler.currentToken.equalsIgnoreCase(CONSTANTS.USEB)) {
      Compiler.lex.getNextToken()
    }
    else{
      println("Error: \\PARAB is missing")///
      System.exit(1)
    }
  }

  def defb(): Unit ={
    if (Compiler.currentToken.equalsIgnoreCase(CONSTANTS.DEFB)) {
      Compiler.lex.getNextToken()
    }
    else{
      println("Error: \\PARAB is missing")///
      System.exit(1)
    }
  }

  def useb(): Unit ={
    if (Compiler.currentToken.equalsIgnoreCase(CONSTANTS.USEB)) {
      Compiler.lex.getNextToken()
    }
    else{
      println("Error: \\PARAB is missing")///
      System.exit(1)
    }
  }
  /*
BRACKETE    ::= ‘]’
NEWLINE     ::= \\'
LINKB       ::= '['
ADDRESSB    ::= '('
ADDRESSE    ::= ')'
EQSIGN      ::= '='
REQTEXT
text
*/

}
