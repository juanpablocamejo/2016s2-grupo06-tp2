package operaciones

import ejecucion.Contexto
import programas.SentenciaCompuesta
import valores.Valor

class OperacionBinaria[A <: Valor, B <: Valor, C <: Valor](val ope1: Valor, val ope2: Valor, val fn: (A, B) => (C)) extends SentenciaCompuesta(ope1, ope2) with Valor {
  override def ejecutar(contexto: Contexto = Contexto()): C = {
    fn.apply(ope1.evaluarComo[A](contexto), ope2.evaluarComo[B](contexto))
  }
}