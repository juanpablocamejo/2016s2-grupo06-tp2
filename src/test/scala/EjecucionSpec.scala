import ejecucion.Ejecutador
import operaciones._
import org.scalatest.{FlatSpec, Matchers}
import programas.Programa
import valores.{Nulo, Numero, Referencia, Valor}

class EjecucionSpec extends FlatSpec with Matchers {
  val n4= Numero(4)
  val n3= Numero(3)

  "Ejecutador" should "ejecutar 3+4 y devolver 7" in {
    val p = Programa(Suma(n3, n4))
    val r: Valor = Ejecutador().ejecutar(p)
    r should be (Numero(7))
  }

  it should "ejecutar Variable('var') y devolver Nulo" in {
    val p = Programa(Variable("var"))
    val r: Valor = Ejecutador().ejecutar(p)
    r should be(Nulo())
  }

  it should "ejecutar Variable('var',9-1) y devolver Nulo" in {
    val p = Programa(Variable("var", Resta(Numero(9), Numero(1))))
    val r: Valor = Ejecutador().ejecutar(p)
    r should be(Nulo())
  }

  it should "ejecutar (9-1)-2 y devolver 6" in {
    val p = Programa(Resta(Resta(Numero(9), Numero(1)), Numero(2)))
    val r: Valor = Ejecutador().ejecutar(p)
    r should be(Numero(6))
  }

  it should "ejecutar 9/3 y devolver 3" in {
    val p = Programa(Division(Numero(9), Numero(3)))
    val r: Valor = Ejecutador().ejecutar(p)
    r should be(Numero(3))
  }

  it should "ejecutar 2*4 y devolver 8" in {
    val p = Programa(Multiplicacion(Numero(2), Numero(4)))
    val r: Valor = Ejecutador().ejecutar(p)
    r should be(Numero(8))
  }

  it should "ejecutar var=1; -7+var; y devolver 2" in {
    val p = Programa(Variable("var", Numero(1)), Suma(Numero(-7), Referencia("var")))
    val r: Valor = Ejecutador().ejecutar(p)
    r should be(Numero(-6))
  }

}

