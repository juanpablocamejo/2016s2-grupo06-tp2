package checkeos.reglas.variables

import checkeos.problemas.{NivelAdvertencia, Problema}
import checkeos.reglas.Regla
import operaciones.Variable
import programas.{Programa, Sentencia, SentenciaCompuesta}
import valores.Referencia

case class ReglaReferenciaAVariableNoDeclarada() extends Regla("Variable duplicada", NivelAdvertencia()) {

  val fn: PartialFunction[(Programa, Sentencia), Option[Problema]] = {
    case (p, r: Referencia) => if (referenciaNoDeclarada(r.nombre, p.sentenciasHijas)) Some(new Problema(this, gravedad, r)) else None
  }

  def referenciaNoDeclarada(nombre: String, ls: List[Sentencia]): Boolean = {
    for (s <- ls) {
      s match {
        case Referencia(n) => if (n == nombre) return true
        case Variable(n, _) => if (n == nombre) return false
        case s: SentenciaCompuesta => if (referenciaNoDeclarada(nombre, s.sentenciasHijas)) return true
        case _ =>
      }
    }
    false
  }
}
