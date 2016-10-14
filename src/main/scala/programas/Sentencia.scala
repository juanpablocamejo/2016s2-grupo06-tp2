package programas

import ejecucion.Contexto
import valores.Valor

abstract class Sentencia {
  def ejecutar(contexto: Contexto): Valor
}

abstract class SentenciaSimple extends Sentencia {

}

abstract class SentenciaCompuesta(val _sentencias: Sentencia*) extends Sentencia {
  var sentenciasHijas = _sentencias.toList

  def modificarYRetornar(ls: List[Sentencia]): SentenciaCompuesta = {
    sentenciasHijas = ls
    this
  }
}

