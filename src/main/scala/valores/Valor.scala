package valores

import ejecucion.Contexto

abstract class Valor {
  def evaluar[T](contexto: Contexto): T = {
    this match {
      case Referencia(nombre) => contexto.referencias(nombre).evaluar[T](contexto)
      case a => a.asInstanceOf[T]
    }
  }
}

case class Numero(val valor: Int) extends Valor

case class Booleano(val valor: Boolean) extends Valor

case class Referencia(val nombre: String) extends Valor
