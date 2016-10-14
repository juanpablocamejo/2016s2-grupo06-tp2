package checkeos.reglas.variables

import checkeos.problemas.{NivelAdvertencia, Problema}
import checkeos.reglas.Regla
import operaciones.Variable
import programas.{Programa, Sentencia, SentenciaCompuesta}
import valores.Referencia

case class ReglaUsoDeVariableSinAsignar() extends Regla("Uso de variable sin asignar", NivelAdvertencia()) {

  val fn: PartialFunction[(Programa, Sentencia), Option[Problema]] = {
    case (p, r: Referencia) => if (referenciaAVariableSinAsginar(r.nombre, p.sentenciasHijas)) Some(new Problema(this, gravedad, r)) else None
  }

  def referenciaAVariableSinAsginar(nombre: String, ls: List[Sentencia]): Boolean = {
    for (s <- ls) {
      s match {
        case Referencia(n) => if (n == nombre) return true
        case Variable(n, v:Valor) => if (n == nombre && v!=null) return false
        case Asignar(Referencia(n), v:Valor) => if (n == nombre) return false
        case s: SentenciaCompuesta => if (referenciaAVariableSinAsginar(nombre, s.sentenciasHijas)) return true
        case _ =>
      }
    }
    false
  }
}
