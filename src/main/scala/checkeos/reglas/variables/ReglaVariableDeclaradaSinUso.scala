package checkeos.reglas.variables

import checkeos.problemas.{NivelAdvertencia, Problema}
import checkeos.reglas.Regla
import operaciones.Variable
import programas.{Programa, Sentencia, SentenciaCompuesta}
import valores.Referencia

case class ReglaVariableDeclaradaSinUso() extends Regla("Variable duplicada", NivelAdvertencia()) {

  val fn: PartialFunction[(Programa, Sentencia), Option[Problema]] = {
    case (p, v: Variable) => if (variableSinUso(v.nombre, p.sentenciasHijas)) Some(new Problema(this, gravedad, v)) else None
  }

  def variableSinUso(nombre: String, ls: List[Sentencia]): Boolean = {
    for (s <- ls) {
      s match {
        case r: Referencia => if (r.nombre == nombre) return false
        case s: SentenciaCompuesta => if (!variableSinUso(nombre, s.sentenciasHijas)) return false
        case _ =>
      }
    }
    true
  }
}
