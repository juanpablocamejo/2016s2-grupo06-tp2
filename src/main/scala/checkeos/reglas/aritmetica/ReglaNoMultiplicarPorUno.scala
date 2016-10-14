package checkeos.reglas.aritmetica

import checkeos.problemas.{NivelAdvertencia, Problema}
import checkeos.reglas.Regla
import operaciones.Multiplicacion
import programas.{Programa, Sentencia}
import refactors.Refactor
import valores.Numero

case class ReglaNoMultiplicarPorUno() extends Regla("No multiplicar por 1", NivelAdvertencia()) with Refactor {
  val fn: PartialFunction[(Programa, Sentencia), Option[Problema]] = {
    case ps@((_, Multiplicacion(_, Numero(1))) | (_, Multiplicacion(Numero(1), _))) => Some(new Problema(this, gravedad, ps._2))
  }

  override def refactor(s: Sentencia): Sentencia = {
    s match {
      case Multiplicacion(Numero(1), a) => a
      case Multiplicacion(a, Numero(1)) => a
    }
  }

}
