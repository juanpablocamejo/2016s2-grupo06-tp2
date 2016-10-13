package checkeos.reglas.aritmetica

import checkeos.problemas.{NivelAdvertencia, Problema}
import checkeos.reglas.Regla
import operaciones.Suma
import programas.{Programa, Sentencia}
import refactors.Refactor
import valores.Numero

case class ReglaNoSumarCero() extends Regla("No sumar cero", NivelAdvertencia()) with Refactor {
  val fn: PartialFunction[(Programa, Sentencia), Option[Problema]] = {
    case ps@((_, Suma(Numero(0), _)) | (_, Suma(_, Numero(0)))) => Some(new Problema(this, gravedad, ps._2))
  }

  override def refactor(s: Sentencia): Sentencia = {
    s match {
      case Suma(Numero(0), a) => a
      case Suma(a, Numero(0)) => a
    }
  }
}
