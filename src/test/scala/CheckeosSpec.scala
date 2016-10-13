import checkeos.problemas.Problema
import checkeos.reglas.aritmetica.{ReglaNoRestarCero, ReglaNoSumarCero}
import checkeos.reglas.comparacion.ReglaNoCompararExpresionesIdenticas
import checkeos.{Checkeador, Regla}
import operaciones.{Igual, Resta, Suma}
import org.scalatest.{FlatSpec, Matchers}
import programas.Programa
import valores.Numero

class CheckeosSpec extends FlatSpec with Matchers {
  def CheckeadorCon(reglas: Regla*): Checkeador = {
    Checkeador(reglas.toList)
  }

  "ReglaNoSumarCero" should " detectar si al menos un operando es cero" in {
    val p = Programa(
      Suma(Numero(0), Numero(1)),
      Suma(Numero(1), Numero(0))
    )
    val problemas = CheckeadorCon(ReglaNoSumarCero()).checkear(p)
    problemas.size should be(2)
  }

  it should "pasar con 1+1 " in {
    val p = Programa(Suma(Numero(1), Numero(1)))
    CheckeadorCon(ReglaNoSumarCero()).checkear(p) should be(List[Problema]())
  }
  "ReglaNoRestarCero" should " detectar si al menos un operando es cero" in {
    val p = Programa(
      Resta(Numero(0), Numero(1)),
      Resta(Numero(1), Numero(0))
    )
    val problemas = CheckeadorCon(ReglaNoRestarCero()).checkear(p)
    problemas.size should be(2)
  }

  it should "pasar con 1-1 " in {
    val p = Programa(Resta(Numero(1), Numero(1)))
    CheckeadorCon(ReglaNoRestarCero()).checkear(p) should be(List[Problema]())
  }

  "ReglaNoCompararExpresionesIdenticas" should "detectar con 2==2" in {
    val p = Programa(Igual(Numero(1), Numero(1)))
    CheckeadorCon(ReglaNoCompararExpresionesIdenticas()).checkear(p).size should be(1)
  }
}
