package br.com.john.cobrancas.extensions

import java.math.BigDecimal
import java.text.DecimalFormat
import java.util.*

/**
 * Created by john on 19/01/2019.
 */

fun BigDecimal.formataParaBrasileiro(): String {
    val valorRecebido: BigDecimal = this
    val formatoBrasileiro = DecimalFormat.getCurrencyInstance(Locale("pt", "br"))
    return formatoBrasileiro.format(valorRecebido)
            .replace("R$", " R$")
            .replace("-R$", " R$ -")
}