package br.com.john.cobrancas.dao

import br.com.john.cobrancas.model.Transacao

/**
 * Created by john on 12/02/2019.
 */
class TransacaoDAO {

    val transacoes: List<Transacao> = Companion.transacoes
    companion object {
        private val transacoes: MutableList<Transacao> = mutableListOf()
    }

    fun adiciona(transacao: Transacao) {
        Companion.transacoes.add(transacao)
    }

    fun altera(transacao: Transacao, position: Int) {
        Companion.transacoes.set(position, transacao)
    }

    fun remove(position: Int) {
        Companion.transacoes.removeAt(position)
    }

    fun find(position: Int): Transacao {
        return Companion.transacoes.get(position)
    }

    fun findAll(): List<Transacao> {
        return Companion.transacoes
    }
}