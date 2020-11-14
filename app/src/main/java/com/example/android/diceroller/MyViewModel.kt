package com.example.android.diceroller

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MyViewModel() : ViewModel() {

    // definimos la ronda actual
    val ronda = MutableLiveData<Int>()
    val msjBoton = MutableLiveData<String>()

    // inicializamos variables cuando instanciamos
    init {
        ronda.value = 1
        msjBoton.value ="Start"
    }

    /**
     * añadimos uno a la var ronda
     */
    fun sumarRonda() {
        // añadimos uno a la ronda
        // tenemos que chequear si es null
        // lo podemos hacer con un 'if'
        ronda.value = ronda.value?.plus(1)
    }

    /**
     * cambiamos mensaje con coroutinas
     */
    fun salidaLog() {
        CoroutineScope(Dispatchers.Main).launch {
            suspendFun("Start")
            // esperamos dos segundos y cambiamos el mensaje
            delay(2000)
            suspendFun("Stop")
        }
    }

    /**
     * funcion auxiliar que es llamada desde la coroutina
     */
    private fun suspendFun(msg: String) {
        msjBoton.value = msg
        Log.d("mensajeCorutina", msg)
    }

}