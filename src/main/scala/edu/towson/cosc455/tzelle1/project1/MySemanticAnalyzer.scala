package edu.towson.cosc455.tzelle1.project1

//Created by Tzvi on 10/11/2016.
//this will implemt the language read in

import scala.collection.mutable.Stack
import java.io._
import java.awt.Desktop
import java.io.{File, IOException}

class MySemanticAnalyzer {
  var outputStack = Stack[String]()
  var parse = Stack[String]()
  var varName = new Array[String](50)
  var varMean = new Array[String](50)
  var nextToken: String = ""
  var output: String = ""
  var varCount: Int = 0

  def symantics(): Unit = {
    //preps for method
    parse = Compiler.sin.parse.reverse
    nextToken = parse.pop()

    //calls method that process the files
    lex()
  }

  //while statment to translante text
  def lex() {
    while (!parse.isEmpty) {
      if (nextToken.equalsIgnoreCase(CONSTANTS.DOCB)) {
        outputStack.push("<html>")
        nextToken = parse.pop()
      }
      else if (nextToken.equalsIgnoreCase(CONSTANTS.TITLEB)) {
        outputStack.push("<head>")
        outputStack.push("<title>")
        outputStack.push(parse.pop())
        outputStack.push("</title>")
        outputStack.push("</head>")
        parse.pop()
        nextToken = parse.pop()
      }
      else if (nextToken.equalsIgnoreCase(CONSTANTS.HEADING)) {
        outputStack.push("<h1>")
        outputStack.push(parse.pop())
        outputStack.push("</h1>")
        nextToken = parse.pop()
      }
      else if (nextToken.equalsIgnoreCase(CONSTANTS.PARAB)) {
        outputStack.push("<p>")
        nextToken = parse.pop()
      }
      else if (nextToken.equalsIgnoreCase(CONSTANTS.PARAE)) {
        outputStack.push("</p>")
        nextToken = parse.pop()
      }
      else if (nextToken.equalsIgnoreCase(CONSTANTS.BOLD)) {
        outputStack.push("<b>")
        outputStack.push(parse.pop())
        outputStack.push("</b>")
        parse.pop()
        nextToken = parse.pop()
      }
      else if (nextToken.equalsIgnoreCase(CONSTANTS.ITALICS)) {
        outputStack.push("<i>")
        outputStack.push(parse.pop())
        outputStack.push("</i>")
        parse.pop()
        nextToken = parse.pop()
      }
      else if (nextToken.equalsIgnoreCase(CONSTANTS.LISTITEM)) {
        outputStack.push("<li>")
        nextToken = parse.pop()
        if (nextToken.contains("\n") && !parse.isEmpty) {
          outputStack.push(nextToken)
        }
        else {
          lex()
        }
        outputStack.push("</li>")
        nextToken = parse.pop()
      }
      else if (nextToken.equalsIgnoreCase(CONSTANTS.NEWLINE)) {
        outputStack.push("<br>")
        nextToken = parse.pop()
      }
      else if (nextToken.equalsIgnoreCase(CONSTANTS.LINKB)) {
        val temp = parse.pop()
        parse.pop()
        parse.pop()
        nextToken = parse.pop()
        parse.pop()

        outputStack.push("<a href = \"")
        outputStack.push(nextToken)
        outputStack.push("\">")
        outputStack.push(temp)
        outputStack.push("</a> ")
        nextToken = parse.pop()
      }
      else if (nextToken.equalsIgnoreCase(CONSTANTS.IMAGEB)) {
        val temp = parse.pop()
        parse.pop()
        parse.pop()
        nextToken = parse.pop()
        parse.pop()

        outputStack.push("<img src =\"")
        outputStack.push(nextToken)
        outputStack.push("\" alt=\"")
        outputStack.push(temp)
        outputStack.push("\">")
        nextToken = parse.pop()
      }
      else if (nextToken.equalsIgnoreCase(CONSTANTS.DEFB)) {
        val name = parse.pop()
        parse.pop()
        val mean = parse.pop()
        parse.pop()

        //var and def are pased to arrays
        if (varName.contains(name)) {
          val defed = varName.indexWhere(name)
          varName(defed) = name
          varMean(defed) = mean
        }
        else {
          varName(varCount) = name
          varMean(varCount) = mean
          varCount += 1
        }

        //nothing to push so recusive pop
        nextToken = parse.pop()
      }
      else if (nextToken.equalsIgnoreCase(CONSTANTS.USEB)) {
        val name: String = parse.pop()
        parse.pop()

        //test for var
        if (varName.contains(name)) {
          val defed = varName.IndexOf(name)
          outputStack.push(varMean(defed))
        }
        else {
          println("Static Symantic Error: Varibale by that name has not been defined")
          System.exit(1)
        }
        //incremnt
        nextToken = parse.pop()
      }

      else if (nextToken.equalsIgnoreCase(CONSTANTS.DOCE)) {
        outputStack.push("</html>")
      }
      else {
        outputStack.push(nextToken)
        nextToken = parse.pop()
      }
    }

    //print output stak to file
    val output = outputStack.reverse.mkString
    val print = new PrintWriter(new File(Compiler.filename + ".html"))
    print.write(output)
    print.close

    //calls html to open
    openHTMLFileInBrowser(Compiler.filename + ".html")
    return
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
