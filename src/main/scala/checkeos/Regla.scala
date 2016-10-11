package checkeos

import checkeos.problemas.{NivelAdvertencia, NivelDeGravedad, Problema}
import operaciones.Suma
import programas.{Programa, Sentencia}
import valores.Numero

abstract class Regla(val mensaje:String, val gravedad:NivelDeGravedad) {
  val fn: PartialFunction[(Programa,Sentencia, Int), Option[Problema]]
  def checkear(programa: Programa, posicion: Int): Option[Problema] = {
    fn.apply((programa, programa.sentencias(posicion-1), posicion-1))
  }
}

case class ReglaNoSumarCero() extends Regla("No sumar ceros", NivelAdvertencia()) {
  val fn: PartialFunction[(Programa, Sentencia, Int), Option[Problema]] = {
    case (_, sentencia:Sentencia, i:Int) =>
      if (esSumaCero(sentencia))
        Some(new Problema(mensaje, gravedad, sentencia))
      else None
  }

  def esSumaCero(s: Sentencia): Boolean = {
    s match {
      case Suma(Numero(0), _) | Suma(_, Numero(0)) => true
      case _ => false
    }
  }
}

