package checkeos.reglas.aritmetica

import checkeos.problemas.{NivelAdvertencia, Problema}
import checkeos.reglas.Regla
import operaciones.Resta
import programas.{Programa, Sentencia}
import refactors.Refactor
import valores.Numero

case class ReglaNoRestarCero() extends Regla("No restar cero", NivelAdvertencia()) with Refactor {
  val fn: PartialFunction[(Programa, Sentencia), Option[Problema]] = {
    case ps@(_, Resta(_, Numero(0))) => Some(new Problema(this, gravedad, ps._2))
  }

  override def refactor(s: Sentencia): Sentencia = {
    s match {
      case Resta(a, Numero(0)) => a
    }
  }
}
