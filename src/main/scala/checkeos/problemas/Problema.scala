package checkeos.problemas

import programas.Sentencia

class Problema(val descripcion: String, val gravedad:NivelDeGravedad, val sentencia: Sentencia){
  override def toString: String = { s"$gravedad | $descripcion -> $sentencia"}
}

case class Error(val desc:String, val sent:Sentencia) extends Problema(desc,NivelError(),sent)
case class Advertencia(val desc:String, val sent:Sentencia) extends Problema(desc,NivelAdvertencia(),sent)


