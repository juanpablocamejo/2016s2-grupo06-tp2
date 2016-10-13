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

class Literal extends SentenciaSimple with Valor {

}

case class Numero(valor: Int) extends Literal {
  def >(x: Numero): Boolean = {
    this.valor > x.valor
  }
}

case class Booleano(valor: Boolean) extends Literal {
  def >(x: Booleano): Boolean = {
    this.valor > x.valor
  }
}

case class Referencia(val nombre: String) extends SentenciaSimple with Valor
