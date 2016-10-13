package valores

import ejecucion.Contexto
import programas.{Sentencia, SentenciaSimple}

trait Valor extends Sentencia {
  def evaluar[T](contexto: Contexto): T = {
    this match {
      case Referencia(nombre) => contexto.referencias(nombre).evaluar[T](contexto)
      case a: Valor => a.asInstanceOf[T]
    }
  }
}

case class Numero(valor: Int) extends SentenciaSimple with Valor

case class Booleano(valor: Boolean) extends SentenciaSimple with Valor

case class Referencia(nombre: String) extends SentenciaSimple with Valor
