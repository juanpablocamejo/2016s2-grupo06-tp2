import checkeos.Checkeador
import checkeos.reglas.aritmetica.ReglaNoSumarCero
import operaciones.Suma
import programas.Programa
import valores.Numero

/**
  * Created by K-ME on 13/10/2016.
  */
object app {

  def main(args: Array[String]): Unit = {
    val p = Programa(
      Suma(Numero(1), Suma(Numero(1), Numero(0))),
      Suma(Numero(0), Numero(4)),
      Suma(Numero(0), Numero(4))
    )
    val chk = Checkeador(List(ReglaNoSumarCero()))
    chk.checkearSentencia(p, p, ReglaNoSumarCero()).foreach(println)


  }

}
