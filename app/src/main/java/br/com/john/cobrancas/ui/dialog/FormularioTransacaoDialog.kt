package br.com.john.cobrancas.ui.dialog

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import br.com.john.cobrancas.R
import br.com.john.cobrancas.extensions.converteParaCalendar
import br.com.john.cobrancas.extensions.formataParaBasileiro
import br.com.john.cobrancas.model.Tipo
import br.com.john.cobrancas.model.Transacao
import kotlinx.android.synthetic.main.form_transacao.view.*
import java.math.BigDecimal
import java.util.*

/**
 * Created by john on 07/02/2019.
 */

/*Open permite que a classe seje subscrita*/
abstract open class FormularioTransacaoDialog(private val context: Context,
                                     private val viewGroup: ViewGroup) {
    val viewDialog = criaLayout()
    protected val campoValor = viewDialog.form_transacao_valor
    protected val campoCategoria = viewDialog.form_transacao_categoria
    protected val campoData = viewDialog.form_transacao_data
    abstract protected val tituloBotaoPositivo : String

    public fun chama(tipo: Tipo, delegate: (transacao: Transacao) -> Unit) {
        configuraCampoData()
        configuraCampoCategoria(tipo)
        configuraFormulario(tipo, delegate)
    }

    private fun configuraFormulario(tipo: Tipo, delegate: (transacao: Transacao) -> Unit) {

        val titulo = tituloPor(tipo)
        AlertDialog.Builder(context)
                .setTitle(titulo)
                .setView(viewDialog)
                .setNegativeButton("Cancelar", null)
                .setPositiveButton(tituloBotaoPositivo, { _, _ ->
                    val valorEmTexto = campoValor.text.toString()
                    val dataEmTexto = campoData.text.toString()
                    val categoriaEmTexto = campoCategoria.selectedItem.toString()
                    val valor = converteCampoValor(valorEmTexto)
                    val data = dataEmTexto.converteParaCalendar()
                    val transacaoCriada = Transacao(tipo = Tipo.RECEITA, valor = valor, data = data, categoria = categoriaEmTexto)
                    delegate(transacaoCriada)
                })
                .show()
    }

    private fun converteCampoValor(valorEmTexto: String): BigDecimal {
        return try {
            BigDecimal(valorEmTexto)
        } catch (exception: NumberFormatException) {
            Toast.makeText(context, "Falha na conversão de valor", Toast.LENGTH_LONG).show()
            BigDecimal.ZERO
        }
    }

    private fun configuraCampoCategoria(tipo: Tipo) {
        val categorias = categoriaPor(tipo)
        val adapter = ArrayAdapter
                .createFromResource(context, categorias,
                        android.R.layout.simple_spinner_dropdown_item)
        viewDialog.form_transacao_categoria.adapter = adapter
    }

    private fun configuraCampoData() {
        val hoje = Calendar.getInstance()
        campoData.setText(hoje.formataParaBasileiro())

        val ano = hoje.get(Calendar.YEAR)
        val mes = hoje.get(Calendar.MONTH)
        val dia = hoje.get(Calendar.DAY_OF_MONTH)

        campoData.setOnClickListener {
            DatePickerDialog(context,
                    DatePickerDialog.OnDateSetListener { _, ano, mes, dia ->
                        val dataSelecionada = Calendar.getInstance()
                        dataSelecionada.set(ano, mes, dia)
                        campoData.setText(dataSelecionada.formataParaBasileiro())
                    }
                    , ano, mes, dia)//data que aparece selecionada
                    .show()
        }
    }

    private fun criaLayout(): View {
        return LayoutInflater
                .from(context)
                .inflate(R.layout.form_transacao, viewGroup, false)
    }

    protected fun categoriaPor(tipo: Tipo): Int {
        return if (tipo == Tipo.RECEITA)
            R.array.categorias_de_receita
        else
            R.array.categorias_de_despesa
    }

    abstract protected fun tituloPor(tipo: Tipo): Int
}