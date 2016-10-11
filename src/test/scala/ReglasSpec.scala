import checkeos.ReglaNoSumarCero
import operaciones.Suma
import org.scalatest.{FlatSpec, Matchers}
import programas.Programa
import valores.Numero

class ReglasSpec extends FlatSpec with Matchers {
  "La Regla NoSumarCeros" should " fallar con 1+0" in {
    val s = Suma(Numero(1), Numero(0))
    val p = Programa(s)
    val r = ReglaNoSumarCero().checkear(p,1)
    println(r.get)
    assert(r != None)
  }

  it should "pasar con 1+1 " in {
    val s = Suma(Numero(1), Numero(1))
    val p = Programa(s)
    assert(ReglaNoSumarCero().checkear(p,1) == None)
  }
}
