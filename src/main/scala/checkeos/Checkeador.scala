package checkeos

import checkeos.problemas.Problema
import checkeos.reglas.Regla
import programas.{Programa, Sentencia, SentenciaCompuesta, SentenciaSimple}

case class Checkeador(var reglas: List[Regla]) {

  def checkear(programa: Programa): List[Problema] = {
    reglas.foldLeft(List[Problema]()) { (z, r) =>
      z ++ checkearSentencia(programa, programa.asInstanceOf[SentenciaCompuesta], r)
    }
  }

  def checkearSentencia(p: Programa, s: Sentencia, r: Regla): List[Problema] = {
    s match {
      case s: SentenciaCompuesta =>
        r.checkear(p, s).toList ++ s.sentenciasHijas.foldLeft(List[Problema]()) { (z, s) => z ++ checkearSentencia(p, s, r) }
      case s: SentenciaSimple => r.checkear(p, s).toList
    }
  }


}