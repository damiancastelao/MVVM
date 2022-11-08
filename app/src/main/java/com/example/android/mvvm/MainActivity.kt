/*
 * Copyright 2018, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.mvvm

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
// para observar LiveDatas
import androidx.activity.viewModels
import androidx.lifecycle.Observer

class MainActivity : AppCompatActivity() {
    // para que sea mas facil la etiqueta del log
    private val TAG_LOG: String = "mensaje Main"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // cargamos layout
        setContentView(R.layout.activity_main)

        // Instanciamos el ViewModel
        // nomenclatura que necesita utilizar jvm 1.8
        // se configure en project structure -> Modules -> Target Compatibillity
        val miModelo by viewModels<MyViewModel>()

        // definimos el listener del boton
        // llama a una función del ViewModel
        // que es el encargado de manipular los datos
        val botonNuevoRandom: Button = findViewById(R.id.roll_button)
        botonNuevoRandom.setOnClickListener {
            // llama a la función del ViewModel
            miModelo.sumarRandom()
            Log.d(TAG_LOG, "Actualizo ronda")
        }

        // observamos cambios en livedata
        miModelo.livedata_numbers.observe(
            this, 
            Observer(
                // funcion que llamaremos cada vez que cambie el valor del observable
                fun(nuevaListaRandom: MutableList<Int>) {
                    // actualizamos textView en caso de recibir datos
                    var textRandom: TextView = findViewById(R.id.textRandom)
                    textRandom.text = nuevaListaRandom.toString()
                }
            )
        )


    }
}