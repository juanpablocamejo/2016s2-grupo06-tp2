package checkeos.reglas.comparacion

import checkeos.Regla
import checkeos.problemas.{NivelAdvertencia, Problema}
import operaciones._
import programas.{Programa, Sentencia}
import valores.Literal

case class ReglaNoCompararLiterales() extends Regla("No comparar literales", NivelAdvertencia()) {
  val fn: PartialFunction[(Programa, Sentencia), Option[Problema]] = {
    case ps@(_, op: Comparacion) => ps._2 match {
      case Igual(a: Literal, b: Literal) => errorDetectado(ps._2)
      case Distinto(a: Literal, b: Literal) => errorDetectado(ps._2)
      case Mayor(a: Literal, b: Literal) => errorDetectado(ps._2)
      case Menor(a: Literal, b: Literal) => errorDetectado(ps._2)
      case MayorOIgual(a: Literal, b: Literal) => errorDetectado(ps._2)
      case MenorOIgual(a: Literal, b: Literal) => errorDetectado(ps._2)
    }
  }

  def errorDetectado(s: Sentencia): Option[Problema] = {
    Some(new Problema(mensaje, gravedad, s))
  }
}
