import checkeos.Checkeador
import checkeos.reglas.Regla
import checkeos.reglas.aritmetica.{ReglaNoMultiplicarPorUno, ReglaNoRestarCero, ReglaNoSumarCero}
import checkeos.reglas.comparacion.ReglaNoCompararLiterales
import checkeos.reglas.variables.{ReglaReferenciaAVariableNoDeclarada, ReglaVariableDeclaradaSinUso, ReglaVariableDuplicada}
import operaciones._
import org.scalatest.{FlatSpec, Matchers}
import programas.Programa
import valores.{Numero, Referencia}

class CheckeosSpec extends FlatSpec with Matchers {
  val cualquierNumero = Numero(1)

  def CheckeadorCon(reglas: Regla*): Checkeador = {
    Checkeador(reglas.toList)
  }

  "ReglaNoSumarCero" should " detectar si un operando es 0" in {
    val p = Programa(
      Suma(Numero(0), cualquierNumero),
      Suma(cualquierNumero, Numero(0)),
      Suma(Numero(1), Numero(1))
    )
    val problemas = CheckeadorCon(ReglaNoSumarCero()).checkear(p)
    problemas.size should be(2)
  }

  "ReglaNoRestarCero" should " detectar si el segundo operando es 0" in {
    val p = Programa(
      Resta(Numero(0), cualquierNumero),
      Resta(cualquierNumero, Numero(0)),
      Resta(Numero(1), Numero(1))
    )
    val problemas = CheckeadorCon(ReglaNoRestarCero()).checkear(p)
    problemas.size should be(1)
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

  "ReglaVariableDuplicada" should " detectar una variable duplicada" in {
    val s = Variable("a")
    val p = Programa(s, s)
    val res = CheckeadorCon(ReglaVariableDuplicada()).checkear(p)
    res.size should be(2)
    assert(res.head.sentencia == s)
  }

  "ReglaVariableDuplicada" should " no detectar una variable no duplicada" in {
    val p = Programa(Variable("a"))
    CheckeadorCon(ReglaVariableDuplicada()).checkear(p).size should be(0)
  }

  "ReglaReferenciaAVariableNoDeclarada" should " detectar referencia una variable no declarada" in {
    val r = Referencia("a")
    val p = Programa(r, Variable("a"))
    val res = CheckeadorCon(ReglaReferenciaAVariableNoDeclarada()).checkear(p)
    res.size should be(1)
    assert(res.head.sentencia == r)
  }

  it should "no detectar referencia a una variable declarada" in {
    val p = Programa(Referencia("a"), Variable("b"), Referencia("b"))
    CheckeadorCon(ReglaReferenciaAVariableNoDeclarada()).checkear(p).size should be(1)
  }

  it should "detectar referencia a una variable no declarada dentro de una operacion" in {
    val s = Referencia("b")
    val p = Programa(Suma(Referencia("b"), Numero(0)), Variable("b", Numero(2)))
    val res = CheckeadorCon(ReglaReferenciaAVariableNoDeclarada()).checkear(p)
    res.size should be(1)
    assert(res.head.sentencia == s)
  }

  "ReglaVariableDeclaradaSinUso" should "detectar una variable sin uso" in {
    val s = Variable("b")
    val p = Programa(Suma(Numero(4), Numero(0)), s)
    val res = CheckeadorCon(ReglaVariableDeclaradaSinUso()).checkear(p)
    res.size should be(1)
    assert(res.head.sentencia == s)
  }

  it should "no detectar una variable en uso" in {
    val s = Variable("b")
    val p = Programa(Suma(Numero(4), Numero(0)), s, Asignar(Referencia("b"), Numero(4)))
    CheckeadorCon(ReglaVariableDeclaradaSinUso()).checkear(p).size should be(0)
  }
}
