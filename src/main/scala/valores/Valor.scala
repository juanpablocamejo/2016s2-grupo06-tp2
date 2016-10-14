package valores

import ejecucion.Contexto
import programas.{Sentencia, SentenciaSimple}

trait Valor extends Sentencia {
  override def ejecutar(contexto: Contexto): Valor = {
    this match {
      case r@Referencia(nombre) => contexto.obtener(r).ejecutar(contexto)
      case a: Valor => a
    }
  }

  def evaluarComo[T](contexto: Contexto): T = {
    ejecutar(contexto).asInstanceOf[T]
  }
}

class Literal extends SentenciaSimple with Valor

case class Numero(valor: Int) extends Literal

case class Booleano(valor: Boolean) extends Literal

case class Referencia(nombre: String) extends SentenciaSimple with Valor

case class Nulo() extends SentenciaSimple with Valor
