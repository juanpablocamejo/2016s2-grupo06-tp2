package checkeos.reglas.aritmetica

import checkeos.Regla
import checkeos.problemas.{NivelAdvertencia, Problema}
import operaciones.Suma
import programas.{Programa, Sentencia}
import valores.Numero

case class ReglaNoSumarCero() extends Regla("No sumar ceros", NivelAdvertencia()) {
  val fn: PartialFunction[(Programa, Sentencia), Option[Problema]] = {
    case ps@((_, Suma(Numero(0), _)) | (_, Suma(_, Numero(0)))) => Some(new Problema(mensaje, gravedad, ps._2))
  }
}
