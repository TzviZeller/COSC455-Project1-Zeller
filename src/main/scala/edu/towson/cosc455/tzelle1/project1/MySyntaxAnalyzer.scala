package edu.towson.cosc455.tzelle1.project1

//Created by Tzvi on 10/11/2016.

import scala.collection.mutable.Stack

class MySyntaxAnalyzer extends SyntaxAnalyzer {
  var parse = Stack[String]()
  var found: Boolean = false;

  override def gittex(): Unit = {
    docb()
    variableDefine()
    title()
    body()
    doce()
  }

  override def body(): Unit = {
    if (Compiler.currentToken.equalsIgnoreCase(CONSTANTS.PARAB)) {
      paragraph()
      body()
    }
      
    else if (Compiler.currentToken.equalsIgnoreCase(CONSTANTS.NEWLINE)) {
      newline()
      body()
    }
    else if(Compiler.currentToken.equalsIgnoreCase(CONSTANTS.DOCE))
      {

      }
    else {
      innerText()
      body()
    }
  }

  override def innerText(): Unit = {
    if (Compiler.currentToken.equalsIgnoreCase(CONSTANTS.USEB)) {
      variableUse()
      innerText()
    }
    else if (Compiler.currentToken.equalsIgnoreCase(CONSTANTS.PARAE)) {
      if(parse.contains(CONSTANTS.PARAB)){
        pare()
      }
      else {
        println("Syntax Error: \\PARAB was never defined")
        System.exit(1)
      }
    }
    else if (Compiler.currentToken.equalsIgnoreCase(CONSTANTS.HEADING)) {
      heading()
      innerText()
    }
    else if (Compiler.currentToken.equalsIgnoreCase(CONSTANTS.BOLD)) {
      bold()
      innerText()
    }
    else if (Compiler.currentToken.equalsIgnoreCase(CONSTANTS.ITALICS)) {
      italics()
      innerText()
    }
    else if (Compiler.currentToken.equalsIgnoreCase(CONSTANTS.LISTITEM)) {
      listItem()
      innerText()
    }
    else if (Compiler.currentToken.equalsIgnoreCase(CONSTANTS.IMAGEB)) {
      image()
      innerText()
    }
    else if (Compiler.currentToken.equalsIgnoreCase(CONSTANTS.LINKB)) {
      link()
      innerText()
    }
    else if (Compiler.currentToken.equalsIgnoreCase(CONSTANTS.NEWLINE)) {
      newline()
      innerText()
    }
    else if(Compiler.currentToken.equalsIgnoreCase(CONSTANTS.DOCE))
      {
        return
      }
    else
      posText()
  }

  override def innerItem(): Unit = {
    if (Compiler.currentToken.equalsIgnoreCase(CONSTANTS.USEB)) {
      variableUse()
      innerItem()
    }
    else if (Compiler.currentToken.equalsIgnoreCase(CONSTANTS.BOLD)) {
      bold()
      innerItem()
    }
    else if (Compiler.currentToken.equalsIgnoreCase(CONSTANTS.ITALICS)) {
      italics()
      innerItem()
    }
    else if (Compiler.currentToken.equalsIgnoreCase(CONSTANTS.LINKB)) {
      link()
      innerItem()
    }
    else if(Compiler.currentToken.equalsIgnoreCase(CONSTANTS.DOCE))
      {
        found = true
      }
    else if (Compiler.lex.isText(Compiler.currentToken)) {
      text()
      innerItem()
    }
    else
      found = true
  }

  override def title(): Unit = {
    titleb()
    text()
    rightBrace()
  }

  override def paragraph(): Unit = {
    parb()
    variableDefine()
    innerText()
    pare()
  }

  override def heading(): Unit = {
    headb()
    text()
  }

  override def variableDefine(): Unit = {
    if (Compiler.currentToken.equalsIgnoreCase(CONSTANTS.DEFB)) {
      defb()
      text()
      equl()
      text()
      rightBrace()
    }
  }

  override def variableUse(): Unit = {
    useb()
    text()
    rightBrace()
  }

  override def bold(): Unit = {
    bold()
    posText()
    bold()
  }

  override def italics(): Unit = {
    italb()
    posText()
    italb()
  }

  override def listItem(): Unit = {
    listItemb()
    innerItem()
  }

  override def link(): Unit = {
    leftBrace()
    text()
    rightBrace()
    leftParan()
    text()
    rightParan()
  }

  override def image(): Unit = {
    imageb()
    text()
    rightBrace()
    leftParan()
    text()
    rightParan()
  }

  //general syntax defs for methods
  def text(): Unit = {
    if (Compiler.lex.isText(Compiler.currentToken)) {
      parse.push(Compiler.currentToken)
      Compiler.lex.getNextToken()
    }
    else {
      println("Syntax Error: Text was expected")
      println(Compiler.currentToken + "Was found instead")
      System.exit(1)
    }
  }

  def posText(): Unit = {
    if (Compiler.lex.isText(Compiler.currentToken)) {
      parse.push(Compiler.currentToken)
      Compiler.lex.getNextToken()
    }
  }

  override def newline(): Unit =
    if (Compiler.currentToken.equalsIgnoreCase(CONSTANTS.NEWLINE)) {
      parse.push(CONSTANTS.NEWLINE)
      Compiler.lex.getNextToken()
    }
    else {
      println("Syntax Error: \\\\ was expected")
      println(Compiler.currentToken + "Was found instead")
      System.exit(1)
    }

  def rightBrace(): Unit = {
    if (Compiler.currentToken.equalsIgnoreCase("]")) {
      parse.push("]")
      Compiler.lex.getNextToken()
    }
    else {
      println("Syntax Error: ] was expected to end the token")
      println(Compiler.currentToken + "Was found instead")
      System.exit(1)
    }
  }

  def leftBrace(): Unit = {
    if (Compiler.currentToken.equalsIgnoreCase("[")) {
      parse.push("[")
      Compiler.lex.getNextToken()
    }
    else {
      println("Syntax Error: [ was expected")
      println(Compiler.currentToken + "Was found instead")
      System.exit(1)
    }
  }

  def leftParan(): Unit = {
    if (Compiler.currentToken.equalsIgnoreCase(CONSTANTS.ADDRESSB)) {
      parse.push("(")
      Compiler.lex.getNextToken()
    }
    else {
      println("Syntax Error: ( was expected")
      println(Compiler.currentToken + "Was found instead")
      System.exit(1)
    }
  }

  def rightParan(): Unit = {
    if (Compiler.currentToken.equalsIgnoreCase(")")) {
      parse.push(")")
      Compiler.lex.getNextToken()
    }
    else {
      println("Syntax Error: ) was expected to end the token")
      println(Compiler.currentToken + "Was found instead")
      System.exit(1)
    }
  }

  def equl(): Unit = {
    if (Compiler.currentToken.equals(CONSTANTS.EQSIGN)) {
      parse.push("=")
      Compiler.lex.getNextToken()
    }
    else {
      println("Syntax Error: = was expected")
      println(Compiler.currentToken + "Was found instead")
      System.exit(1)
    }
  }

  //specific syntax defs for methods
  def docb(): Unit = {
    if (Compiler.currentToken.equalsIgnoreCase(CONSTANTS.DOCB)) {
      parse.push(CONSTANTS.DOCB)
      Compiler.lex.getNextToken()
    }
    else {
      println("Syntax Error: \\BEGIN was expected")
      println(Compiler.currentToken + "Was found instead")
      System.exit(1)
    }
  }

  def doce(): Unit = {
    if (Compiler.currentToken.equalsIgnoreCase(CONSTANTS.DOCE)) {
      parse.push(CONSTANTS.DOCE)
      Compiler.lex.getNextToken()
      if(Compiler.lex.nextChar.equals('\n'))
        return
      else{
        println("SYNTAX ERROR: items after document end statement")
        System.exit(1)
      }
    }
    else {
      println("Syntax Error: \\END was expected")
      println(Compiler.currentToken + "Was found instead")
      System.exit(1)
    }
  }

  def titleb(): Unit = {
    if (Compiler.currentToken.equalsIgnoreCase(CONSTANTS.TITLEB)) {
      parse.push(CONSTANTS.TITLEB)
      Compiler.lex.getNextToken()
    }
    else {
      println("Syntax Error: \\TITLE[ was expected")
      println(Compiler.currentToken + "Was found instead")
      System.exit(1)
    }
  }

  def headb(): Unit = {
    if (Compiler.currentToken.equalsIgnoreCase(CONSTANTS.HEADING)) {
      parse.push(CONSTANTS.HEADING)
      Compiler.lex.getNextToken()
    }
    else {
      println("Syntax Error: # was expected")
      println(Compiler.currentToken + "Was found instead")
      System.exit(1)
    }
  }

  def parb(): Unit = {
    if (Compiler.currentToken.equalsIgnoreCase(CONSTANTS.PARAB)) {
      parse.push(CONSTANTS.PARAB)
      Compiler.lex.getNextToken()
    }
    else {
      println("Syntax Error: \\PARAB was expected")
      println(Compiler.currentToken + "Was found instead")
      System.exit(1)
    }
  }

  def pare(): Unit = {
    if (Compiler.currentToken.equalsIgnoreCase(CONSTANTS.PARAE)) {
      parse.push(CONSTANTS.PARAE)
      Compiler.lex.getNextToken()
    }
    else {
      println("Syntax Error: \\PARAE was expected")
      println(Compiler.currentToken + "Was found instead")
      System.exit(1)
    }
  }

  def boldb(): Unit = {
    if (Compiler.currentToken.equalsIgnoreCase(CONSTANTS.BOLD)) {
      parse.push(CONSTANTS.BOLD)
      Compiler.lex.getNextToken()
    }
    else {
      println("Syntax Error: ** was expected")
      println(Compiler.currentToken + "Was found instead")
      System.exit(1)
    }
  }

  def italb(): Unit = {
    if (Compiler.currentToken.equalsIgnoreCase(CONSTANTS.ITALICS)) {
      parse.push(CONSTANTS.ITALICS)
      Compiler.lex.getNextToken()
    }
    else {
      println("Syntax Error: * was expected")
      println(Compiler.currentToken + "Was found instead")
      System.exit(1)
    }
  }

  def listItemb(): Unit = {
    if (Compiler.currentToken.equalsIgnoreCase(CONSTANTS.LISTITEM)) {
      parse.push(CONSTANTS.LISTITEM)
      Compiler.lex.getNextToken()
    }
    else {
      println("Syntax Error: + was expected")
      println(Compiler.currentToken + "Was found instead")
      System.exit(1)
    }
  }

  def imageb(): Unit = {
    if (Compiler.currentToken.equalsIgnoreCase(CONSTANTS.IMAGEB)) {
      parse.push(CONSTANTS.IMAGEB)
      Compiler.lex.getNextToken()
    }
    else {
      println("Syntax Error: ![ was expected")
      println(Compiler.currentToken + "Was found instead")
      System.exit(1)
    }
  }

  def useb(): Unit = {
    if (Compiler.currentToken.equalsIgnoreCase(CONSTANTS.USEB)) {
      parse.push(CONSTANTS.USEB)
      Compiler.lex.getNextToken()
    }
    else {
      println("Syntax Error: \\USE[ was expected")
      println(Compiler.currentToken + "Was found instead")
      System.exit(1)
    }
  }

  def defb(): Unit = {
    if (Compiler.currentToken.equalsIgnoreCase(CONSTANTS.DEFB)) {
      parse.push(CONSTANTS.DEFB)
      Compiler.lex.getNextToken()
    }
    else {
      println("Syntax Error: \\DEF[ was expected")
      println(Compiler.currentToken + "Was found instead")
      System.exit(1)
    }
  }
}