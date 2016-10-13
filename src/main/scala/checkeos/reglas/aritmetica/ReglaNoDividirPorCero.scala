package checkeos.reglas.aritmetica

import checkeos.Regla
import checkeos.problemas.{NivelError, Problema}
import operaciones.Division
import programas.{Programa, Sentencia}
import valores.Numero

case class ReglaNoDividirPorCero() extends Regla("No dividir por cero", NivelError()) {
  val fn: PartialFunction[(Programa, Sentencia), Option[Problema]] = {
    case ps@((_, Division(_, Numero(0)))) => Some(new Problema(mensaje, gravedad, ps._2))
  }
}
