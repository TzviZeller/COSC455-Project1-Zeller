package edu.towson.cosc455.tzelle1.project1

/**
  * Created by Tzvi on 10/11/2016.
  */

trait LexicalAnalyzer {
  def start() :Unit
  def addChar() : Unit
  def getChar() : Char
  def getNextToken() : Unit
  def lookup() : Boolean = {
    println("This is lookup implementation!")
  }
}