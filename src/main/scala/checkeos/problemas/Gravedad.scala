package checkeos.problemas

abstract class NivelDeGravedad
case class NivelAdvertencia() extends NivelDeGravedad {
  override def toString: String = "Advertencia"
}
case class NivelError() extends NivelDeGravedad{
  override def toString: String = "Error"
}
