package edu.towson.cosc455.tzelle1.project1

//Created by Tzvi on 10/11/2016.
//this will implemt the language read in

import scala.collection.mutable.Stack
import java.io._
import java.awt.Desktop
import java.io.{File, IOException}

class MySemanticAnalyzer {
  var outputStack = Stack[String]()
  var parse = Compiler.sin.parse.reverse
  var variables = Stack[String]()
  var nextToken: String = ""
  var output: String = ""

  def symantics(): Unit = {
    lex()
    //openHTMLFileInBrowser() //change@@@
  }

  def lex() {
    while (!parse.isEmpty) {
      nextToken = parse.pop()
      if (nextToken.equalsIgnoreCase(CONSTANTS.DOCB)) {
        outputStack.push("<html>")
      }
      else if (nextToken.equalsIgnoreCase(CONSTANTS.TITLEB)) {
        outputStack.push("<head>")
        outputStack.push("<title>")
        outputStack.push(parse.pop())
        outputStack.push("</title>")
        outputStack.push("</head>")
        parse.pop()
      }
      else if (nextToken.equalsIgnoreCase(CONSTANTS.HEADING)) {
        outputStack.push("<h1>")
        outputStack.push(parse.pop())
        outputStack.push("</h1>")
      }
      else if (nextToken.equalsIgnoreCase(CONSTANTS.PARAB)) {
        outputStack.push("<p>")
      }
      else if (nextToken.equalsIgnoreCase(CONSTANTS.PARAE)) {
        outputStack.push("</p>")
      }
      else if (nextToken.equalsIgnoreCase(CONSTANTS.BOLD)) {
        outputStack.push("<b>")
        outputStack.push(parse.pop())
        outputStack.push("</b>")
        parse.pop()
      }
      else if (nextToken.equalsIgnoreCase(CONSTANTS.ITALICS)) {
        outputStack.push("<i>")
        outputStack.push(parse.pop())
        outputStack.push("</i>")
        parse.pop()
      }
      else if (nextToken.equalsIgnoreCase(CONSTANTS.LISTITEM)) {
        outputStack.push("<li>")
        nextToken = parse.pop()
        while (nextToken != "\n") {
          lex()
        }
        outputStack.push("</li>")
      }
      else if (nextToken.equalsIgnoreCase(CONSTANTS.NEWLINE)) {
        outputStack.push("<br>")
      }
      else if (nextToken.equalsIgnoreCase(CONSTANTS.LINKB)) {
        val temp = parse.pop()
        parse.pop()
        parse.pop()
        nextToken = parse.pop()
        parse.pop()
        
        outputStack.push("< a herf = \" ")
        outputStack.push(nextToken)
        outputStack.push("\">")
        outputStack.push(temp)
        outputStack.push("</a>")
      }
      else if (nextToken.equalsIgnoreCase(CONSTANTS.IMAGEB)) {//@@@
        val temp = parse.pop()
        parse.pop()
        parse.pop()
        nextToken = parse.pop()
        parse.pop()

        outputStack.push("< a herf = \" ")
        outputStack.push(nextToken)
        outputStack.push("\">")
        outputStack.push(temp)
        outputStack.push("</a>")
        
        outputStack.push("<img src = \" > ")
      }
      else if (nextToken.equalsIgnoreCase(CONSTANTS.DEFB)) {//@@@
        //variables  nextToken
        outputStack.push("<br>")
      }
      else if (nextToken.equalsIgnoreCase(CONSTANTS.USEB)) {//@@@
        outputStack.push("<br>")//@@@ waitout
      }

      else if (nextToken.equalsIgnoreCase(CONSTANTS.DOCE)) {
        outputStack.push("</html>")///@@@ waitout
      }
      else {
        println("send help")
        System.exit(1)

        /*or strait push*/
      }
    }

    //print output stak to file
    val output = parse.reverse.toString()
    val print = new PrintWriter(new File(Compiler.filename +".html"))
    print.write(output)
    print.close

    //calls html to open
    openHTMLFileInBrowser(Compiler.filename +".html")
  }


  /* * Hack Scala/Java function to take a String filename and open in default web browswer. */
  def openHTMLFileInBrowser(htmlFileStr: String) = {
    val file: File = new File(htmlFileStr.trim)
    println(file.getAbsolutePath)
    if (!file.exists())
      sys.error("File " + htmlFileStr + " does not exist.")

    try {
      Desktop.getDesktop.browse(file.toURI)
    }
    catch {
      case ioe: IOException => sys.error("Failed to open file:  " + htmlFileStr)
      case e: Exception => sys.error("He's dead, Jim!")
    }
  }
}
