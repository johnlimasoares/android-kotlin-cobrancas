package br.com.john.cobrancas.extensions

import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by john on 19/01/2019.
 */
fun String.limitaEmAte(limiteCaracteres: Int) : String {
    val primeiroCaracter = 0
    if(this.length > limiteCaracteres) {
        return "${this.substring(primeiroCaracter, limiteCaracteres)}..."
    }
    return this
}

fun String.converteParaCalendar(): Calendar {
    val formatoBrasileiro = SimpleDateFormat("dd/MM/yyyy")
    val dataConvertida: Date = formatoBrasileiro.parse(this)
    val data = Calendar.getInstance()
    data.time = dataConvertida
    return data
}