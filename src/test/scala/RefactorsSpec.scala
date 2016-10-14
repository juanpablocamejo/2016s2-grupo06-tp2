import checkeos.reglas.aritmetica.{ReglaNoRestarCero, ReglaNoSumarCero}
import operaciones.{Resta, Suma}
import org.scalatest.{FlatSpec, Matchers}
import programas.Programa
import refactors.Refactorizador
import valores.Numero

class RefactorsSpec extends FlatSpec with Matchers {

  "Un Refactor con ReglaNoSumarCero" should "convertir 1+0 en 1" in {
    val refactorizador = Refactorizador(ReglaNoSumarCero())
    assert(refactorizador.refactorizar(Programa(Suma(Numero(0), Numero(1)))) == Programa(Numero(1)))
  }
  it should "convertir (1+0)+0 en 1" in {
    val refactorizador = Refactorizador(ReglaNoSumarCero())
    val p = Programa(Suma(Suma(Numero(1), Numero(0)), Numero(0)))
    assert(refactorizador.refactorizar(p) == Programa(Numero(1)))
  }

  "Un Refactor con ReglaNoRestarCero" should "convertir 1-0 en 1" in {
    val refactorizador = Refactorizador(ReglaNoRestarCero())
    assert(refactorizador.refactorizar(Programa(Resta(Numero(1), Numero(0)))) == Programa(Numero(1)))
  }
  
  "Un Refactor con ReglaNoCompararLiterales" should "convertir 1>0 en true" in {
    val refactorizador = Refactorizador(ReglaNoCompararLiterales())
    assert(refactorizador.refactorizar(Programa(Mayor(Numero(1), Numero(0)))) == Programa(Booleano(true)))
  }
  
  it should "convertir 1==1 en true" in {
    val refactorizador = Refactorizador(ReglaNoCompararLiterales())
    assert(refactorizador.refactorizar(Programa(Igual(Numero(1), Numero(1)))) == Programa(Booleano(true)))
  }
  
  it should "convertir 1==2 en false" in {
    val refactorizador = Refactorizador(ReglaNoCompararLiterales())
    assert(refactorizador.refactorizar(Programa(Igual(Numero(1), Numero(2)))) == Programa(Booleano(false)))
  }
  it should "convertir true==true en true" in {
    val refactorizador = Refactorizador(ReglaNoCompararLiterales())
    assert(refactorizador.refactorizar(Programa(Igual(Booleano(true), Booleano(true)))) == Programa(Booleano(true)))
  }
}
