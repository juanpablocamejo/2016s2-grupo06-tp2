package programas

import ejecucion.Contexto
import valores.{Nulo, Valor}

case class Programa(__sentencias: Sentencia*) extends SentenciaCompuesta(__sentencias: _*) {
  override def ejecutar(contexto: Contexto = Contexto()): Valor = {
    sentenciasHijas.foldLeft(Nulo(): Valor) {
      (z, s) => s.ejecutar(contexto)
    }
  }
}