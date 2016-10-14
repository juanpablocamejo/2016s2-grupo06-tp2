package ejecucion

import programas.Programa
import valores.Valor

case class Ejecutador(var contexto: Contexto = Contexto()) {
  def ejecutar(p: Programa): Valor = {
    p.ejecutar(contexto)
  }
}
