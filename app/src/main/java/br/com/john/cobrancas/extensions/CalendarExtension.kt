package br.com.john.cobrancas.extensions

import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by john on 19/01/2019.
 */

fun Calendar.formataParaBasileiro(): String {
    val format = SimpleDateFormat("dd/MM/yyyy")
    return format.format(this.time)
}