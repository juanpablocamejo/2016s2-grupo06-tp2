package ejecucion

import valores.Valor

case class Contexto(var referencias:Map[String,Valor]=Map()){}

