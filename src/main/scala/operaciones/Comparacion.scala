package operaciones

import valores.{Booleano, Numero, Valor}

case class Mayor(a: Valor, b: Valor) extends OperacionBinaria(a, b,
  { (a: Numero, b: Numero) => Booleano(a.valor > b.valor) }
)

case class Menor(a: Valor, b: Valor) extends OperacionBinaria(a, b,
  { (a: Numero, b: Numero) => Booleano(a.valor < b.valor) }
)

case class Igual(a: Valor, b: Valor) extends OperacionBinaria(a, b,
  { (a: Numero, b: Numero) => Booleano(a.valor == b.valor) }
)

case class Distinto(a: Valor, b: Valor) extends OperacionBinaria(a, b,
  { (a: Numero, b: Numero) => Booleano(a.valor != b.valor) }
)

case class MayorOIgual(a: Numero, b: Numero) extends OperacionBinaria(a, b,
  { (a: Numero, b: Numero) => Booleano(a.valor >= b.valor) }
)

case class MenorOIgual(a: Numero, b: Numero) extends OperacionBinaria(a, b,
  { (a: Numero, b: Numero) => Booleano(a.valor <= b.valor) }
)

