package checkeos.problemas

import checkeos.reglas.Regla
import programas.Sentencia

class Problema(val regla: Regla, val gravedad: NivelDeGravedad, val sentencia: Sentencia) {
  override def toString: String = {
    s"$gravedad | $descripcion -> $sentencia"
  }

  def descripcion = regla.mensaje
}

case class Error(_regla: Regla, sent: Sentencia) extends Problema(_regla, NivelError(), sent)

case class Advertencia(_regla: Regla, sent: Sentencia) extends Problema(_regla, NivelAdvertencia(), sent)

object ProblemaConRegla {
  def unapply(arg: Problema): Option[(Regla, Sentencia)] = Some(arg.regla, arg.sentencia)
}
