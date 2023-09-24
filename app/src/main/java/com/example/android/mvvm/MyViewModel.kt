package com.example.android.mvvm

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * Inicializa y modifica los datos de la app
 */
class MyViewModel() : ViewModel() {

    // para que sea mas facil la etiqueta del log
    private val TAG_LOG: String = "mensaje ViewModel"

    // este va a ser nuestro dato de aplicación
    // inicializamos a 0 la variable numero
    val datos = Datos(0)

    // definimos una MutableLiveData de tipo entero
    // para poder observar los valores cuando cambia
    val livedata_datos = MutableLiveData<Int>()

    // inicializamos variables cuando instanciamos
    init {
        Log.d(TAG_LOG, "Inicializamos livedata")
        // inicializamos el livedata para poder ser observado
        // elegimos el dato 'numero'
        livedata_datos.value = datos.numero
    }

    /**
     * Generamos un numero aleatorio
     */
    fun sumarRandom() {
        // añadimos entero random a la lista
        datos.numero = (0..10).random()
        // actualizamos el livedata, de esta manera si hay un observador
        // este recibirá la nueva lista
        livedata_datos.setValue(datos.numero)
        // la mostramos en el logcat
        Log.d(TAG_LOG, "Añadimos random al livedata:" + datos.numero)
    }
}