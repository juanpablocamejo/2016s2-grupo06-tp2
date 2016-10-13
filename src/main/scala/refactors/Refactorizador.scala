package refactors

import checkeos.Checkeador
import checkeos.problemas.ProblemaConRegla
import checkeos.reglas.Regla
import programas.Programa

case class Refactorizador(_reglas: Regla with Refactor*) {
  val checkeador = Checkeador(_reglas.toList)

  def refactorizar(programa: Programa): Programa = {
    checkeador.checkear(programa).foldLeft(programa: Programa) { (z, problema) => problema match {
      case ProblemaConRegla(r: Regla with Refactor, s) => r.refactorizar(z, s)
      case _ => z
    }
    }
  }
}
