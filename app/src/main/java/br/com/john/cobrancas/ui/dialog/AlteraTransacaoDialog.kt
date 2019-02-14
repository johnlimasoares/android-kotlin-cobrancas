package br.com.john.cobrancas.ui.dialog

import android.content.Context
import android.view.ViewGroup
import br.com.john.cobrancas.extensions.formataParaBasileiro
import br.com.john.cobrancas.model.Tipo
import br.com.john.cobrancas.model.Transacao

/**
 * Created by john on 23/01/2019.
 */
class AlteraTransacaoDialog(private val context: Context,
                            viewGroup: ViewGroup) : FormularioTransacaoDialog(context, viewGroup) {
    override val tituloBotaoPositivo: String get() = "Alterar"

    override fun tituloPor(tipo: Tipo): Int {
        return if (tipo == Tipo.RECEITA)
            br.com.john.cobrancas.R.string.altera_receita
        else
            br.com.john.cobrancas.R.string.altera_despesa
    }

    public fun chama(transacao: Transacao, delegate: (transacao: Transacao)-> Unit) {
        val tipo = transacao.tipo
        super.chama(tipo, delegate);
        inicializaCampos(transacao)
    }

    private fun inicializaCampos(transacao: Transacao) {
        campoValor.setText(transacao.valor.toString())
        campoData.setText(transacao.data.formataParaBasileiro())
        val categoriasRetornadas = context.resources.getStringArray(categoriaPor(transacao.tipo))
        val posicaoCategoria = categoriasRetornadas.indexOf(transacao.categoria)
        campoCategoria.setSelection(posicaoCategoria, true)
    }

}