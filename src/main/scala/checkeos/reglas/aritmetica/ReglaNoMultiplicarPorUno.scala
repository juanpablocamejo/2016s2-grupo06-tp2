package checkeos.reglas.aritmetica

import checkeos.Regla
import checkeos.problemas.{NivelError, Problema}
import operaciones.Multiplicacion
import programas.{Programa, Sentencia}
import valores.Numero

case class ReglaNoMultiplicarPorUno() extends Regla("No dividir pot cero", NivelError()) {
  val fn: PartialFunction[(Programa, Sentencia), Option[Problema]] = {
    case ps@((_, Multiplicacion(_, Numero(0)))) => Some(new Problema(mensaje, gravedad, ps._2))
  }
}
