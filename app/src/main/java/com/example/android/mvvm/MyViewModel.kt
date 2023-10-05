package com.example.android.mvvm

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

/**
 * Inicializa y modifica los datos de la app
 */
class MyViewModel() : ViewModel() {

    // para que sea mas facil la etiqueta del log
    private val TAG_LOG: String = "mensaje ViewModel"

    // este va a ser nuestra lista para la secuencia random
    // usamos mutable, ya que la queremos modificar
    var _numbers = mutableStateOf(0)


    // inicializamos variables cuando instanciamos
    init {
        Log.d(TAG_LOG, "Inicializamos ViewModel")
    }

    /**
     * crear entero random
     */
    fun crearRandom() {
        _numbers.value = (0..10).random()
        Log.d(TAG_LOG, "creamos random ${_numbers.value}")
    }

    fun getNumero(): Int {
        return _numbers.value
    }
}