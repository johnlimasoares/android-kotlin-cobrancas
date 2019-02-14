package br.com.john.cobrancas.ui

import android.content.Context
import android.support.v4.content.ContextCompat
import android.view.View
import br.com.john.cobrancas.R
import br.com.john.cobrancas.extensions.formataParaBrasileiro
import br.com.john.cobrancas.model.Resumo
import br.com.john.cobrancas.model.Transacao
import kotlinx.android.synthetic.main.resumo_card.view.*
import java.math.BigDecimal

/**
 * Created by john on 21/01/2019.
 */
class ResumoView(context: Context,
                 private val view: View,
                 transacoes: List<Transacao>) {

    private val resumo: Resumo = Resumo(transacoes)
    private val corReceita = ContextCompat.getColor(context, R.color.receita)
    private val corDespesa = ContextCompat.getColor(context, R.color.despesa)

    private fun adicionaReceita() {
        val totalReceita = resumo.receita
        with(view.resumo_card_receita) {
            text = totalReceita.formataParaBrasileiro()
            setTextColor(corReceita)
        }
    }

    private fun adicionaDespesas() {
        val totalDespesa = resumo.despesa
        with(view.resumo_card_despesa) {
            text = totalDespesa.formataParaBrasileiro()
            setTextColor(corDespesa)
        }
    }

    private fun adicionaTotal() {
        val total = resumo.total()
        val corTexto = if(total >= BigDecimal.ZERO)
            corReceita
        else
            corDespesa

        view.resumo_card_total.setTextColor(corTexto)
        view.resumo_card_total.text = total.formataParaBrasileiro()
    }

    fun atualiza() {
        adicionaDespesas()
        adicionaReceita()
        adicionaTotal()
    }
}