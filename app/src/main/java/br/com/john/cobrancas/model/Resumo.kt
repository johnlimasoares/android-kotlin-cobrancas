package br.com.john.cobrancas.model

import java.math.BigDecimal

/**
 * Created by john on 21/01/2019.
 */
class Resumo(private val transacoes: List<Transacao>) {

    val receita get() = somaPor(Tipo.RECEITA)

    val despesa get() = somaPor(Tipo.DESPESA)

    fun total(): BigDecimal = receita.subtract(despesa)

    private fun somaPor(tipo: Tipo): BigDecimal {
        val soma = transacoes.filter{ it.tipo == tipo}
                .sumByDouble{ transacao -> transacao.valor.toDouble() }
        return BigDecimal(soma)
    }
}