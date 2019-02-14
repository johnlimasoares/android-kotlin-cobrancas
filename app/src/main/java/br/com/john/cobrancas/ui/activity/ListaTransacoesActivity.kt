package br.com.john.cobrancas.ui.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.ViewGroup
import android.widget.AdapterView
import br.com.john.cobrancas.R
import br.com.john.cobrancas.dao.TransacaoDAO
import br.com.john.cobrancas.model.Tipo
import br.com.john.cobrancas.model.Transacao
import br.com.john.cobrancas.ui.ResumoView
import br.com.john.cobrancas.ui.adapter.ListaTransacoesAdapter
import br.com.john.cobrancas.ui.dialog.AdicionaTransacaoDialog
import br.com.john.cobrancas.ui.dialog.AlteraTransacaoDialog
import kotlinx.android.synthetic.main.activity_lista_transacoes.*
import java.math.BigDecimal
import java.util.*

class ListaTransacoesActivity : AppCompatActivity() {

    private val dao = TransacaoDAO()
//    private lateinit var viewDaActivity: View
    private val viewDaActivity by lazy {
        window.decorView
    }

    private val viewGroupDaActivity by lazy {
        window.decorView as ViewGroup
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_transacoes)
        configuraResumo()
        configuraLista()
        configuraFAB()
    }

    private fun configuraFAB() {
        lista_transacoes_adiciona_receita.setOnClickListener {
            chamaDialogDeAdicao(Tipo.RECEITA)
        }
        lista_transacoes_adiciona_despesa.setOnClickListener {
            chamaDialogDeAdicao(Tipo.DESPESA)
        }
    }

    private fun chamaDialogDeAdicao(tipo: Tipo) {
        AdicionaTransacaoDialog(this, viewGroupDaActivity)
                .chama(tipo, { transacao ->
                        adiciona(transacao)
//                        adiciona(it) Pode tbm utilizar o objeto subentendido IT
                        lista_transacoes_adiciona_menu.close(true)
                })
    }

    private fun adiciona(transacao: Transacao) {
        dao.adiciona(transacao)
        atualizaTransacoes()
    }

    private fun atualizaTransacoes() {
        configuraLista()
        configuraResumo()
    }

    private fun configuraResumo() {
        val resumoView = ResumoView(this, viewDaActivity, dao.findAll())
        resumoView.atualiza()
    }

    private fun configuraLista() {
        with(lista_transacoes_listview) {
            adapter = ListaTransacoesAdapter(dao.findAll(), this@ListaTransacoesActivity)//this dentro do with corresponde ao lista_transacoes_listview neste caso
            setOnItemClickListener({ _, _, position, _ ->
                val transacao = dao.find(position)
                chamaDialogDeAlteracao(transacao, position)
            })
            setOnCreateContextMenuListener { menu, v, menuInfo ->
                menu.add(Menu.NONE, 1, Menu.NONE, "Remover")
            }
        }
    }

    override fun onContextItemSelected(item: MenuItem?): Boolean {
        val idDoMenu = item?.itemId
        if(idDoMenu == 1){
            val adapterContextMenuInfo = item.menuInfo as AdapterView.AdapterContextMenuInfo
            val position = adapterContextMenuInfo.position
            dao.remove(position)
            atualizaTransacoes()
        }
        return super.onContextItemSelected(item)
    }


    private fun chamaDialogDeAlteracao(transacao: Transacao, position: Int) {
        AlteraTransacaoDialog(this, viewGroupDaActivity)
                .chama(transacao,{ transacaoAlterada ->
                        altera(transacaoAlterada, position)
                })
    }

    private fun altera(transacao: Transacao, position: Int) {
        dao.altera(transacao, position)
        atualizaTransacoes()
    }

    private fun listaDeTransacoes(): List<Transacao> {
        return listOf<Transacao>(
                Transacao(BigDecimal(10.5), "Alimentação", Tipo.DESPESA)
                , Transacao(BigDecimal(230.5), "Combustível", Tipo.DESPESA)
                , Transacao(BigDecimal(6030.5), "PLR", Tipo.RECEITA)
                , Transacao(data = Calendar.getInstance(), tipo = Tipo.RECEITA, categoria = "PLR", valor = BigDecimal(6030.5))
        )
    }
}
