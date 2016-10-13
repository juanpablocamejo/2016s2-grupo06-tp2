package checkeos.reglas.comparacion

import checkeos.problemas.{NivelAdvertencia, Problema}
import checkeos.reglas.Regla
import ejecucion.Contexto
import operaciones._
import programas.{Programa, Sentencia}
import refactors.Refactor
import valores.Literal

case class ReglaNoCompararLiterales() extends Regla("No comparar literales", NivelAdvertencia()) with Refactor {
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
    Some(new Problema(this, gravedad, s))
  }

  def refactor(s: Sentencia): Sentencia = {
    s match {
      case s@Igual(a: Literal, b: Literal) => s.ejecutar(Contexto())
      case s@Distinto(a: Literal, b: Literal) => s.ejecutar(Contexto())
      case s@Mayor(a: Literal, b: Literal) => s.ejecutar(Contexto())
      case s@Menor(a: Literal, b: Literal) => s.ejecutar(Contexto())
      case s@MayorOIgual(a: Literal, b: Literal) => s.ejecutar(Contexto())
      case s@MenorOIgual(a: Literal, b: Literal) => s.ejecutar(Contexto())
    }
  }
}
