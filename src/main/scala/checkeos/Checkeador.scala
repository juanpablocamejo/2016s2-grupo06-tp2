package checkeos

import checkeos.problemas.Problema
import programas.{Programa, Sentencia}

class Checkeador(var reglas: List[Regla]) {

  def checkear(programa: Programa): List[Problema] = {
    var problemas: List[Problema] = List()
    reglas.foreach{ r =>
      programa.sentencias.foreach { s:Sentencia =>
        r.checkear(programa, s) match {
          case Some(a) => problemas = a :: problemas
          case None =>
        }
      }
    }
    problemas
  }
}