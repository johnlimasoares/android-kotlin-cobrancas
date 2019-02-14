package br.com.john.cobrancas.ui.dialog

import android.content.Context
import android.view.ViewGroup
import br.com.john.cobrancas.R
import br.com.john.cobrancas.model.Tipo

/**
 * Created by john on 23/01/2019.
 */
class AdicionaTransacaoDialog(context: Context,
                              viewGroup: ViewGroup) : FormularioTransacaoDialog(context, viewGroup) {
    override val tituloBotaoPositivo: String get() = "Adicionar"

    override fun tituloPor(tipo: Tipo): Int {
            return if (tipo == Tipo.RECEITA)
                R.string.adiciona_receita
            else
                R.string.adiciona_despesa
    }
}