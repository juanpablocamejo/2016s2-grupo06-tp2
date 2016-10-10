package operaciones

import ejecucion.Contexto
import programas.Sentencia
import valores.Valor

class OperacionBinaria[A<:Valor, B<:Valor, C<:Valor](val ope1: Valor, val ope2: Valor, val fn: (A, B) => (C)) extends Sentencia{
  def ejecutar(contexto: Contexto): C = {
    fn.apply(ope1.evaluar[A](contexto), ope2.evaluar[B](contexto))
  }
}