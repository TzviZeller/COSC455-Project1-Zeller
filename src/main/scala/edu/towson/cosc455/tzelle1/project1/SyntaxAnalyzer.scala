package edu.towson.cosc455.tzelle1.project1

/**
  * Created by Tzvi on 10/11/2016.
  */


trait SyntaxAnalyzer {
  def gittex() : Unit
  def title() : Unit
  def body() : Unit
  def paragraph() : Unit
  def innerText() : Unit
  def heading() : Unit
  def variableDefine() : Unit
  def variableUse() : Unit
  def bold() : Unit
  def italics() : Unit
  def listItem() : Unit
  def innerItem() : Unit
  def link() : Unit
  def image() : Unit
  def newline() : Unit
}
