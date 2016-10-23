package edu.towson.cosc455.tzelle1.project1

/**
  * Created by Tzvi on 10/11/2016.
  */

class MyLexicalAnalyzer extends LexicalAnalyzer {

  override def start(): Unit = {
  InishalizeLookupArray()



  }

  override def addChar(): Unit = ???

  override def getChar(): Char = ???

  override def getNextToken(): Unit = {
    val c = getChar()
  }

  override def lookup(token: String): Boolean = {
    if(!lookUp.contains(token)){
      println("Lexical Error: " + token +"is not valid")
      return fasle
    }
    return true
  }

  def InishalizeLookupArray() = {
    val lookUp = new Array[String](17)
    lookUp(0)="\\BEGIN";
    lookUp(1)="\\END";
    lookUp(2)="\\TITLE[";
    lookUp(3)="]";
    lookUp(4)="#";
    lookUp(5)="\\PARAB";
    lookUp(6)="\\PARAE";
    lookUp(7)="\\DEF[";
    lookUp(8)="\\USE[";
    lookUp(9)="**";
    lookUp(10)="*";
    lookUp(11)="+";
    lookUp(12)="\\";
    lookUp(13)="[";
    lookUp(14)="(";
    lookUp(15)=")";
    lookUp(16)="=";
    lookUp(17)="![";
  }
}