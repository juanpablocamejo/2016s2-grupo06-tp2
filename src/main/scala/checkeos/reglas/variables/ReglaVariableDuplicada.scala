package checkeos.reglas.variables

import checkeos.problemas.{NivelAdvertencia, Problema}
import checkeos.reglas.Regla
import operaciones.Variable
import programas.{Programa, Sentencia}

case class ReglaVariableDuplicada() extends Regla("Variable duplicada", NivelAdvertencia()) {

  val fn: PartialFunction[(Programa, Sentencia), Option[Problema]] = {
    case (p, v: Variable) => if (variableDuplicada(v.nombre, p)) Some(new Problema(this, gravedad, v)) else None
  }

  def variableDuplicada(nombre: String, p: Programa): Boolean = {
    var ocurrencias = 0
    for (a <- p.sentenciasHijas) {
      a match {
        case a: Variable => if (a.nombre == nombre) ocurrencias += 1; if (ocurrencias > 1) return true
        case _ =>
      }
    }
    ocurrencias > 1
  }
}
