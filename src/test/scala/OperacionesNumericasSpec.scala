import ejecucion.Contexto
import operaciones.{Resta, Suma}
import org.scalatest.{FlatSpec, Matchers}
import valores.{Numero,Referencia}

class OperacionesNumericasSpec extends FlatSpec with Matchers {
  val n4= Numero(4)
  val n3= Numero(3)

  "Ejecutar Suma(Numero(3),Numero(4))"  should "dar Numero(7)" in {
    val contexto:Contexto=Contexto()
    val r:Numero = Suma(n3,n4).ejecutar(contexto)
    r should be (Numero(7))
  }

  "Ejecutar Resta(Numero(3),Numero(4))"  should "dar un Numero(-1)" in {
    val contexto:Contexto=Contexto()
    contexto.referencias += ("variableConN3" -> Numero(3))
    val r:Numero = Resta(Referencia("variableConN3"),n4).ejecutar(contexto)
    r should be (Numero(-1))
  }

}

