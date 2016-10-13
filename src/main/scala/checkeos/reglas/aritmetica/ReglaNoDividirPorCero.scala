package checkeos.reglas.aritmetica

import checkeos.problemas.{NivelError, Problema}
import checkeos.reglas.Regla
import operaciones.Division
import programas.{Programa, Sentencia}
import valores.Numero


case class ReglaNoDividirPorCero() extends Regla("No dividir por cero", NivelError()) {
  val fn: PartialFunction[(Programa, Sentencia), Option[Problema]] = {
    case ps@((_, Division(_, Numero(0)))) => Some(new Problema(this, gravedad, ps._2))
  }
}
