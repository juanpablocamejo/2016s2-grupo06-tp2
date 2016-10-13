package checkeos.reglas.comparacion

import checkeos.Regla
import checkeos.problemas.{NivelAdvertencia, Problema}
import operaciones.Igual
import programas.{Programa, Sentencia}

case class ReglaNoCompararExpresionesIdenticas() extends Regla("No sumar ceros", NivelAdvertencia()) {
  val fn: PartialFunction[(Programa, Sentencia), Option[Problema]] = {
    case ps@(_, Igual(a, b)) =>
      if (a == b)
        Some(new Problema(mensaje, gravedad, ps._2))
      else None
  }
}
