package checkeos.reglas.aritmetica

import checkeos.Regla
import checkeos.problemas.{NivelError, Problema}
import operaciones.Multiplicacion
import programas.{Programa, Sentencia}
import valores.Numero

case class ReglaNoMultiplicarPorUno() extends Regla("No multiplicar por 1", NivelError()) {
  val fn: PartialFunction[(Programa, Sentencia), Option[Problema]] = {
    case ps@((_, Multiplicacion(_, Numero(1))) | (_, Multiplicacion(Numero(1), _))) => Some(new Problema(mensaje, gravedad, ps._2))
  }
}
