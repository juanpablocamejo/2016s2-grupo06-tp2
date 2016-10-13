package checkeos.reglas

import checkeos.problemas.{NivelDeGravedad, Problema}
import programas.{Programa, Sentencia}

abstract class Regla(val mensaje: String, val gravedad: NivelDeGravedad) {
  val fn: PartialFunction[(Programa, Sentencia), Option[Problema]]

  def checkear(programa: Programa, sentencia: Sentencia): Option[Problema] = {
    if (fn.isDefinedAt((programa, sentencia)))
      fn.apply((programa, sentencia))
    else None
  }

}



