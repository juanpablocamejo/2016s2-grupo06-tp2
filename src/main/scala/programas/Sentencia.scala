package programas

abstract class Sentencia {
}

abstract class SentenciaSimple extends Sentencia {

}

abstract class SentenciaCompuesta(val _sentencias: Sentencia*) extends Sentencia {
  val sentencias = _sentencias.toList
}

