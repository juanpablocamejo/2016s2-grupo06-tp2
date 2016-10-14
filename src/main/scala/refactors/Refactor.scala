package refactors

import programas.{Programa, Sentencia, SentenciaCompuesta, SentenciaSimple}

trait Refactor {
  def refactor(s: Sentencia): Sentencia

  def refactorizar(p: Programa, s: Sentencia): Programa = {
    Programa(reemplazarSentencia(p.sentenciasHijas, s, refactor(s)): _*)
  }

  def reemplazarSentencia(ls: List[Sentencia], sentencia: Sentencia, reemplazo: Sentencia): List[Sentencia] = {
    ls.map {
      case s@(ss: SentenciaSimple) => if (ss == s) reemplazo else s
      case s@(sc: SentenciaCompuesta) =>
        if (sc == s) reemplazo else sc.modificarYRetornar(reemplazarSentencia(sc.sentenciasHijas, s, reemplazo))
    }
  }
}
