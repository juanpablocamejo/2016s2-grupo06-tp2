package programas

import ejecucion.Contexto
import valores.Valor

abstract class Sentencia {
  def ejecutar(contexto: Contexto): Valor
}

