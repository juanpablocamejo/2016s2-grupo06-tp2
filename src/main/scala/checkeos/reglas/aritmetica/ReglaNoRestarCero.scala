package checkeos.reglas.aritmetica

import checkeos.Regla
import checkeos.problemas.{NivelAdvertencia, Problema}
import operaciones.Resta
import programas.{Programa, Sentencia}
import valores.Numero

case class ReglaNoRestarCero() extends Regla("No restar cero", NivelAdvertencia()) {
  val fn: PartialFunction[(Programa, Sentencia), Option[Problema]] = {
    case ps@((_, Resta(Numero(0), _)) | (_, Resta(_, Numero(0)))) => Some(new Problema(mensaje, gravedad, ps._2))
  }
}
