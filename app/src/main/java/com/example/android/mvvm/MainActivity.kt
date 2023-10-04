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
import androidx.compose.foundation.Image
import androidx.lifecycle.Observer
// para observar lod caambios del ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel


// compose para la UI
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

// para que sea mas facil la etiqueta del log
private val TAG_LOG: String = "mensaje Main"

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // creamos la interface de usuario con Compose
        setContent { // In here, we can call composables!
            MaterialTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    color = MaterialTheme.colorScheme.background
                ) {
                    Log.d(TAG_LOG,"llamamos a la IU")
                    InterfazUsuario()
                }
            }
        }
    }
}

/**
 * Interfaz de usuario
 * Recibe el ViewModel para poder observar los valores
 * Utiliza funciones del ViewModel en los eventos
 *
 * @param viewModel objeto encargado de los datos de la app
 */
@Composable
private fun InterfazUsuario(viewModel: MyViewModel = viewModel()) {
    // datos de la aplicacion, queremos observar cuando cambia
    var _numbers = remember { mutableStateOf(0) }

    // un cuadro de texto para mostrar los numeros
    Text(
        text = "Numeros: ${_numbers.value}",
        modifier = Modifier.padding(32.dp),
        color = Color.DarkGray
    )
    // un boton para generar numeros aleatorios
    Button(
        onClick = {
            _numbers.value = (0..10).random()
            Log.d(TAG_LOG, "Dentro del onClick")
        },
        modifier = Modifier.padding(64.dp))
        {
            Image(
                painter = painterResource(id = R.drawable.baseline_face_24),
                contentDescription = "Generar numeros",
                Modifier.padding(8.dp)
            )
            Text(text = "Generar numeros")
        }
}

/**
 * Widget que cambia cuando cambia 'numero'
 *
 * @param number entero que queremos mostrar
 */
@Composable
private fun ShowData(number: Int?) {
    // un cuadro de texto para mostrar los numeros
    Text(
        text = "Numeros: ${number}",
        modifier = Modifier.padding(32.dp)
    )
}

/**
 * Funcion de prueba
 * Comprobamos que usa el mismo objeto ViewModel
 */
@Composable
private fun Prueba(viewModel: MyViewModel = viewModel()) {
    // datos de la aplicacion, queremos observar cuando cambia
    val _number = viewModel.livedata_datos.observeAsState()
    Log.d("mensaje Prueba", "¿Mismo ViewModel?= ${_number.value}, ¡si!")

}
@Preview
@Composable
fun DefaultPreview() {
    MaterialTheme {
        InterfazUsuario()
    }
}