package br.com.john.cobrancas.ui.adapter

import android.content.Context
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import br.com.john.cobrancas.R
import br.com.john.cobrancas.extensions.formataParaBasileiro
import br.com.john.cobrancas.extensions.formataParaBrasileiro
import br.com.john.cobrancas.extensions.limitaEmAte
import br.com.john.cobrancas.model.Tipo
import br.com.john.cobrancas.model.Transacao
import kotlinx.android.synthetic.main.transacao_item.view.*

/**
 * Created by john on 17/01/2019.
 */
class ListaTransacoesAdapter(private val transacoes: List<Transacao>,
                             private val context: Context) : BaseAdapter() {

    private val LIMITE_DA_CATEGORIA = 14

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = LayoutInflater.from(context).inflate(R.layout.transacao_item, parent, false)
        val transacao = transacoes[position]

        adicionaCor(transacao, view)
        adicionaIcone(transacao, view)
        adicionaCategoria(transacao, view)
        adicionaData(transacao, view)
        adicionaValor(transacao, view)
        return view
    }

    override fun getItem(position: Int): Transacao {
        return transacoes[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getCount(): Int {
        return transacoes.size
    }

    private fun adicionaCor(transacao: Transacao, view: View) {
        val cor = if (transacao.tipo == Tipo.RECEITA)
            R.color.receita
        else
            R.color.despesa

        view.transacao_valor.setTextColor(ContextCompat.getColor(context, cor))
    }

    private fun adicionaIcone(transacao: Transacao, view: View) {
        val icone = if (transacao.tipo == Tipo.RECEITA)
            R.drawable.icone_transacao_item_receita
        else
            R.drawable.icone_transacao_item_despesa

        view.transacao_icone.setBackgroundResource(icone)
    }

    private fun adicionaValor(transacao: Transacao, view: View) {
        view.transacao_valor.text = transacao.valor.formataParaBrasileiro()
    }

    private fun adicionaData(transacao: Transacao, view: View) {
        view.transacao_data.text = transacao.data.formataParaBasileiro()
    }

    private fun adicionaCategoria(transacao: Transacao, view: View) {
        view.transacao_categoria.text = transacao.categoria.limitaEmAte(LIMITE_DA_CATEGORIA)
    }
}