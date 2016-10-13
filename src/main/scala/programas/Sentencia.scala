package programas

abstract class Sentencia {
}

abstract class SentenciaSimple extends Sentencia {

}

abstract class SentenciaCompuesta(val _sentencias: Sentencia*) extends Sentencia {
  var sentencias = _sentencias.toList

  def modificarYRetornar(ls: List[Sentencia]): SentenciaCompuesta = {
    sentencias = ls
    this
  }

  def ==(obj: SentenciaCompuesta): Boolean = {
    obj.sentencias == this.sentencias
  }
}

