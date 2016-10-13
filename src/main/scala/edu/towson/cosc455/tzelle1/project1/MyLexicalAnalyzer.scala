package edu.towson.cosc455.tzelle1.project1

/**
  * Created by Tzvi on 10/11/2016.
  */

class MyLexicalAnalyzer extends LexicalAnalyzer{

  override def addChar(): Unit = ???

  override def getChar(): Char = ???

  override def getNextToken(): Unit = {
    val c  = getChar()
  }

  override def lookup(): Boolean = ???

}
