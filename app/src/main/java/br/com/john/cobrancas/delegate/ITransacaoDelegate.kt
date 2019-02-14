package br.com.john.cobrancas.delegate

import br.com.john.cobrancas.model.Transacao

/**
 * Created by john on 30/01/2019.
 */

//Substituido por higher order functions
interface ITransacaoDelegate {
    fun delegate(transacao: Transacao)
}