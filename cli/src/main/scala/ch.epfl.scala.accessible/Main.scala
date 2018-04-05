package ch.epfl.scala.accessible

import espeak.Espeak
import scala.io.StdIn.readLine
import java.nio.file.Paths
import java.io.InterruptedIOException
import scala.util.control.NonFatal

import scala.meta.parsers.ParseException

class A {
  
}

object Main {

  def main(args: Array[String]): Unit = {
    System.loadLibrary("scala-espeak0")
    val espeak = new Espeak

    val file = "(.*)"
    val pos = "(\\d+)"

    val summary     = s"summary $file".r
    val summaryAt   = s"summary-at $pos $file".r
    val describe    = s"describe $pos $file".r
    val breadcrumbs = s"breadcrumbs $pos $file".r

    println("running")
    espeak.synthesize("running")

    var running = true
    while (running) {
      try {
        val line = readLine()
        println(line)
        espeak.stop()

        val output = 
          line match {
            case summary(file) =>
              Summary(Paths.get(file))
            case summaryAt(pos, file) =>
              Summary(Paths.get(file), Offset(pos))
            case describe(pos, file) =>
              Describe(Paths.get(file), Offset(pos))
            case breadcrumbs(pos, file) => 
              Breadcrumbs(Paths.get(file), Offset(pos))
            case null =>
              running = false
              "closing."
            case e =>
              s"unknown command: $e"
          }

        espeak.synthesize(output)
      } catch {
        case e: InterruptedIOException => {
          espeak.synthesize("InterruptedIOException")
          running = false
        }
        case pe: ParseException => {
          espeak.synthesize("Cannot parse")
        }
        case NonFatal(e) => {
          err(e)
          // espeak.synthesize(e.getClass.toString)
        }
      }
    }
  }

  def err(t: Throwable): Unit = {
    import java.io.{File, PrintStream}
    val file = new File("test.err")
    val ps = new PrintStream(file)
    t.printStackTrace(ps)
    ps.close()
  }
}
