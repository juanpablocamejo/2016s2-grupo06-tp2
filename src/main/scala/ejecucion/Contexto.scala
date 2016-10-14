package ejecucion

import valores.{Referencia, Valor}

case class Contexto(var referencias: Map[String, Option[Valor]] = Map()) {
  def asignar(referencia: Referencia, valor: Valor) = {
    referencias.get(referencia.nombre) match {
      case None => throw new Exception("Variable no declarada")
      case Some(v) => referencias += (referencia.nombre -> Some(valor))
    }
  }

  def declarar(nombre: String, valor: Option[Valor]) = {
    referencias += (nombre -> valor)
  }

  def obtener(referencia: Referencia): Valor =
    referencias.get(referencia.nombre) match {
      case None => throw new Exception("Variable no declarada")
      case Some(None) => throw new Exception("Variable sin asignar")
      case Some(Some(v: Valor)) => v
    }
}

