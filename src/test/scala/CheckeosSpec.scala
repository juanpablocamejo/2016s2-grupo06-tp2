import checkeos.problemas.Problema
import checkeos.reglas.aritmetica.{ReglaNoMultiplicarPorUno, ReglaNoRestarCero, ReglaNoSumarCero}
import checkeos.reglas.comparacion.ReglaNoCompararLiterales
import checkeos.{Checkeador, Regla}
import operaciones._
import org.scalatest.{FlatSpec, Matchers}
import programas.Programa
import valores.Numero

class CheckeosSpec extends FlatSpec with Matchers {
  val cualquierNumero = Numero(1)

  def CheckeadorCon(reglas: Regla*): Checkeador = {
    Checkeador(reglas.toList)
  }

  "ReglaNoSumarCero" should " detectar si un operando es 0" in {
    val p = Programa(
      Suma(Numero(0), cualquierNumero),
      Suma(cualquierNumero, Numero(0))
    )
    val problemas = CheckeadorCon(ReglaNoSumarCero()).checkear(p)
    problemas.size should be(2)
  }

  it should "pasar con 1+1 " in {
    val p = Programa(Suma(Numero(1), Numero(1)))
    CheckeadorCon(ReglaNoSumarCero()).checkear(p) should be(List[Problema]())
  }
  "ReglaNoRestarCero" should " detectar si un operando es 0" in {
    val p = Programa(
      Resta(Numero(0), cualquierNumero),
      Resta(cualquierNumero, Numero(0))
    )
    val problemas = CheckeadorCon(ReglaNoRestarCero()).checkear(p)
    problemas.size should be(2)
  }
  "ReglaNoMultiplicarPorUno" should " detectar si un operando es 1" in {
    val p = Programa(
      Multiplicacion(Numero(1), cualquierNumero),
      Multiplicacion(cualquierNumero, Numero(1)),
      Multiplicacion(Numero(2), Numero(2))
    )
    val problemas = CheckeadorCon(ReglaNoMultiplicarPorUno()).checkear(p)
    problemas.size should be(2)
  }

  it should "pasar con 1-1 " in {
    val p = Programa(Resta(Numero(1), Numero(1)))
    CheckeadorCon(ReglaNoRestarCero()).checkear(p) should be(List[Problema]())
  }

  "ReglaNoCompararLiterales" should "detectar 1 == 1" in {
    val p = Programa(Igual(Numero(1), Numero(1)))
    CheckeadorCon(ReglaNoCompararLiterales()).checkear(p).size should be(1)
  }
  it should "detectar 1 != 1" in {
    val p = Programa(Distinto(Numero(1), Numero(1)))
    CheckeadorCon(ReglaNoCompararLiterales()).checkear(p).size should be(1)
  }
  it should "detectar 2 > 1" in {
    val p = Programa(Mayor(Numero(2), Numero(1)))
    CheckeadorCon(ReglaNoCompararLiterales()).checkear(p).size should be(1)
  }
  it should "detectar 2 >= 1" in {
    val p = Programa(MayorOIgual(Numero(2), Numero(1)))
    CheckeadorCon(ReglaNoCompararLiterales()).checkear(p).size should be(1)
  }
  it should "detectar 1 < 3" in {
    val p = Programa(Menor(Numero(1), Numero(3)))
    CheckeadorCon(ReglaNoCompararLiterales()).checkear(p).size should be(1)
  }
  it should "detectar 1 <= 3" in {
    val p = Programa(MenorOIgual(Numero(1), Numero(3)))
    CheckeadorCon(ReglaNoCompararLiterales()).checkear(p).size should be(1)
  }

}
