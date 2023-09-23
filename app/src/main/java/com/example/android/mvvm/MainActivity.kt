/*
 * Copyright 2023, The Android Open Source Project
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
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

// para observar LiveDatas
import androidx.activity.viewModels
import androidx.lifecycle.Observer

// compose para la UI
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    // para que sea mas facil la etiqueta del log
    private val TAG_LOG: String = "mensaje Main"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // creamos la interface de usuario con Compose
        setContent { // In here, we can call composables!
            MaterialTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    color = MaterialTheme.colorScheme.background
                ) {
                    InterfazUsuario()
                }

            }

            // TODO ViewModel
            // nomenclatura que necesita utilizar jvm 1.8
            // se configure en project structure -> Modules -> Target Compatibillity
            val miModelo by viewModels<MyViewModel>()

            // observamos cambios en livedata
            /*miModelo.livedata_numbers.observe(
                this,
                Observer(
                    // funcion que llamaremos cada vez que cambie el valor del observable
                    fun(nuevaListaRandom: MutableList<Int>) {
                        // actualizamos textView en caso de recibir datos
                        Log.d(TAG_LOG, "Recibimos nueva lista: " + nuevaListaRandom.toString())
                    }
                )
            )*/
        }
    }
}
@Composable
private fun InterfazUsuario() {
    // datos de la aplicacion, queremos observar cuando cambia
    var _numbers = remember { mutableStateOf(0) }

    // un cuadro de texto para mostrar los numeros
    Text(
        text = "Numeros: ${_numbers.value}",
        modifier = Modifier.padding(32.dp)
    )
    // un boton para generar numeros aleatorios
    Button(
        onClick = { _numbers.value = (0..10).random() },
        modifier = Modifier.padding(64.dp))
        {
            Text(text = "Generar numeros")
        }
}

@Preview
@Composable
fun DefaultPreview() {
    MaterialTheme {
        InterfazUsuario()
    }
}