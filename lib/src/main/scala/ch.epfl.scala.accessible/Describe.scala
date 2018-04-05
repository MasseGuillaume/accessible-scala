package ch.epfl.scala.accessible

import scala.meta._
import java.nio.file.Path

object Describe {
  // private def concat(ts: List[Tree], sep: String): String =
  //   ts.map(_.syntax).mkString(sep)

  // private def join(ts: List[Tree]): String =
  //   concat(ts, " ")

  // def render(parts: String*): String =
  //   parts.filter(_ != " ").mkString(" ")

  def apply(path: Path, offset: Offset): String =
    apply(parse(path), offset)

  def apply(tree: Tree, offset: Offset): String =
    tree match {
      // case Decl.Def(mods0, name0, tparams0, paramss0, decltpe0) => {
      //   val mods = join(mods0)
      //   val name = name0.syntax
      //   val tparams = tparams0.map(apply).mkString("type params ", " ", "")
      //   val paramss = paramss0.map(_.map(_.syntax).mkString(" ")).mkString(" ")
      //   val decltpe = apply(decltpe0)

      //   render(mods, "def", name, tparams, paramss, decltpe)
      // }

      // case Type.Tuple(args) => "tuple: " + args.map(apply).mkString(", ")
      // case Type.Name(value) => value
      // case Type.Param(mods0, name0, tparams0, tbounds0, vbounds0, cbounds0) => {
      //   val mods = join(mods0)
      //   val name = name0.syntax
      //   val tparams = 
      //   val tbounds = 
      //   val vbounds = 
      //   val cbounds = 
      // }
      // case Type.Function(params, res) => {
      //   val dParams = 
      //     if (params.nonEmpty) params.map(apply).mkString(", ")
      //     else "Unit"

      //   "function: " + dParams + " to " + apply(res)
      // }
      case e => e.structure
    }
}