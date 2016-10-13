import checkeos.problemas.{NivelAdvertencia, Problema}
import checkeos.reglas.aritmetica.ReglaNoSumarCero
import operaciones.Suma
import programas.Programa
import valores.Numero

/**
  * Created by K-ME on 13/10/2016.
  */
object app {

  def main(args: Array[String]): Unit = {
    val s1 = Suma(Numero(0), Numero(1))
    val programa = Programa(Suma(Numero(0), Numero(1)))

    val p = new Problema(ReglaNoSumarCero(), NivelAdvertencia(), Suma(Numero(0), Numero(1)))
    println(ReglaNoSumarCero().refactor(s1))
    //    p match{
    //      case ProblemaConRegla(r,s) =>
    //    }

    /*    val p = Programa(
          Suma(Numero(1), Suma(Numero(1), Numero(0))),
          Suma(Numero(0), Numero(4)),
          Suma(Numero(0), Numero(4))
        )
        val chk = Checkeador(List(ReglaNoSumarCero()))
        chk.checkearSentencia(p, p, ReglaNoSumarCero()).foreach(println)
        val r = ReglaNoSumarCero()
        val s1 = Suma(Numero(1),Numero(0))
        val s2 = Numero(1)
        val ls = List(s1,Suma(Numero(1),Numero(0)))

        println(s1 == (Suma(Numero(1),Numero(0))))
        println( r.reemplazarSentencia(ls,s1,s2))*/

  }

}
