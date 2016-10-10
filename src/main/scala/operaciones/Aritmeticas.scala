package operaciones

import valores.{Numero, Valor}

case class Suma(a: Valor, b: Valor) extends OperacionBinaria[Numero,Numero,Numero](a, b,
  { (a: Numero, b: Numero) => Numero(a.valor + b.valor) })

case class Resta(a: Valor, b: Valor) extends OperacionBinaria[Numero,Numero,Numero](a, b,
  { (a, b) => Numero(a.valor - b.valor) })

case class Multiplicacion(a: Valor, b: Valor) extends OperacionBinaria[Numero,Numero,Numero](a, b,
  { (a, b) => Numero(a.valor * b.valor) })

case class Division(a: Valor, b: Valor) extends OperacionBinaria[Numero,Numero,Numero](a, b,
  { (a, b) => Numero(a.valor / b.valor) })


