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

  override def heading(): Unit = ???

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
      println("Error: no title")
      System.exit(1)
    }

    def docb(): Unit ={
      if (Compiler.currentToken.equalsIgnoreCase(CONSTANTS.DOCB)) {
        Compiler.lex.getNextToken()
      }
      else{
        println("Error: no begining tag")
        System.exit(1)
      }
    }

  /*DOCB        ::= ‘\BEGIN’
DOCE        ::= '\END'
TITLEB      ::= '\TITLE['
BRACKETE    ::= ‘]’
HEADING     ::= '#'
PARAB       ::= '\PARAB'
PARAE       ::= '\PARAE'
BOLD        ::= '**'
ITALICS     ::= '*'
LISTITEM    ::= '+'
NEWLINE     ::= \\'
LINKB       ::= '['
ADDRESSB    ::= '('
ADDRESSE    ::= ')'
IMAGEB      ::= '!['
DEFB        ::= '\DEF['
EQSIGN      ::= '='
USEB        ::= '\USE['*/

}
