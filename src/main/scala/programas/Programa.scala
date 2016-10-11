package programas

class ProgramaBase(var sentencias:List[Sentencia])

case class Programa(_sentencias:Sentencia*) extends ProgramaBase(_sentencias.toList)
