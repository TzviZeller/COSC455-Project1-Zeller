package edu.towson.cosc455.tzelle1.project1

/**
  * Created by Tzvi on 10/11/2016.
  */
object CONSTANTS {
  val DOCB      : String = "\\BEGIN\r\n"
  val DOCE      : String = "\\END"
  val TITLEB    : String = "\t\\TITLE["
  val BRACKETE  : String = "]"
  val HEADING   : String = "#"
  val PARAB     : String = "\t\\PARAB"
  val PARAE     : String = "\t\\PARAE"
  val BOLD      : String = "**"
  val ITALICS   : String = "*"
  val LISTITEM  : String = "+"
  val NEWLINE   : String = "\t\\\\"
  val LINKB     : String = "["
  val ADDRESSB  : String = "("
  val ADDRESSE  : String = ")"
  val IMAGEB    : String = "!["
  val DEFB      : String = "\t\\DEF"
  val EQSIGN    : String = "="
  val USEB      : String = "\t\\USE["
  val REQTEXT   : String = (('a' to 'z') ++ ('A' to 'Z') ++ ('0' to '9')).toString()
  val TOKENS    : String = ('\\', '#', '*','[','!', '+','*' ).toString()
  //val TEXT  : String =  ????



}
