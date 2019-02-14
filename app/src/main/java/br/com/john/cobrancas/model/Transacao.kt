package br.com.john.cobrancas.model

import java.math.BigDecimal
import java.util.*

/**
 * Created by john on 18/01/2019.
 */
class Transacao(val valor: BigDecimal,
                val categoria: String,
                val tipo: Tipo,
                val data: Calendar = Calendar.getInstance())  {

}