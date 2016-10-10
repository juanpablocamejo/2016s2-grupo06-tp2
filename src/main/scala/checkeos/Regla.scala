package checkeos
import checkeos.problemas.{Advertencia, NivelAdvertencia, NivelDeGravedad, Problema}
import operaciones.{Suma}
import programas.{Programa, Sentencia}
import valores.Numero

abstract class Regla(){
  var gravedad:NivelDeGravedad = NivelAdvertencia()
  val mensaje=s"La sentencia no comple con: ${this.getClass.getName}"
  val fn:PartialFunction[(Programa, Sentencia), Option[Problema]]
  def checkear(programa: Programa,sentencia: Sentencia): Option[Problema] ={
    fn.apply((programa,sentencia))
  }
}

case class ReglaNoSumarCero extends Regla{
  fn={ case (_,Suma(a,b)) =>
    if (esSumaCero(Suma(a,b)))
      Some(Problema(mensaje,gravedad,Suma(a,b))) //revisar
    else
    None
  }

  def esSumaCero(s:Suma): Boolean ={
    s match{
      case Suma(Numero(0),_) | Suma(_,Numero(0)) => true
    }
  }
}

