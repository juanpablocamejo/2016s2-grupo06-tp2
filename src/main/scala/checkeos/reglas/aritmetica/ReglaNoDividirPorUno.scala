package checkeos.reglas.aritmetica

import checkeos.problemas.{NivelAdvertencia, Problema}
import checkeos.reglas.Regla
import operaciones.Division
import programas.{Programa, Sentencia}
import refactors.Refactor
import valores.Numero

case class ReglaNoDividirPorUno() extends Regla("No dividir por 1", NivelAdvertencia()) with Refactor {
  val fn: PartialFunction[(Programa, Sentencia), Option[Problema]] = {
    case ps@((_, Division(_, Numero(1)))) => Some(new Problema(this, gravedad, ps._2))
  }

  def refactor(s: Sentencia): Sentencia = {
    s match {
      case Division(a, Numero(1)) => a
      case Division(Numero(1), a) => a
    }
  }
}
