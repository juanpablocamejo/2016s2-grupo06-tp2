package operaciones

import ejecucion.Contexto
import programas.SentenciaCompuesta
import valores.{Nulo, Referencia, Valor}

case class Variable(val nombre: String, _valor: Valor = null) extends SentenciaCompuesta {
  var valor: Option[Valor] = Option(_valor)

  override def ejecutar(contexto: Contexto): Valor = {
    valor match {
      case Some(v) => contexto.declarar(nombre, Some(v.ejecutar(contexto)))
      case None => contexto.declarar(nombre, None)
    }
    Nulo()
  }
}

case class Asignar(referencia: Referencia, valor: Valor) extends SentenciaCompuesta {
  sentenciasHijas = valor :: referencia :: sentenciasHijas

  override def ejecutar(contexto: Contexto): Valor = {
    contexto.asignar(referencia, valor.ejecutar(contexto))
    Nulo()
  }
}
