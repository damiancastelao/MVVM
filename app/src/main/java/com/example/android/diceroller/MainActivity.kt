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

package com.example.android.diceroller

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.util.*

// para no usar findViewById
import kotlinx.android.synthetic.main.activity_main.*

import androidx.activity.viewModels

class MainActivity : AppCompatActivity() {
    // contenedor de imagen
    // modificando el main
    lateinit var diceImage: ImageView


    val duration = Toast.LENGTH_LONG



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // cargamos layout
        setContentView(R.layout.activity_main)

        // Instanciamos el ViewModel
        // nomenclatura que necesita utilizar jvm 1.8
        // se configure en project structure -> Modules -> Target Compatibillity
        val miModelo by viewModels<MyViewModel>()

        val text = getString(R.string.saludo)
        val toast = Toast.makeText(applicationContext, text, duration)

        val botonTirar: Button = findViewById(R.id.roll_button)
        botonTirar.setOnClickListener {
            // cambia la imagen
            rollDice()
            // llama a las corutinas
            MisCorutinas.salidaLog()
            // añado una ronda en el ViewModel
            miModelo.sumarRonda()
            // actualizao texto
            textRonda.text = miModelo.ronda.value.toString()
            Log.d("mensajeCorutina", "Actualizo ronda")
        }

        diceImage = findViewById(R.id.dice_image)
    }

    private fun rollDice() {
        val randomInt = Random().nextInt(6) + 1
        val drawableResource = when (randomInt) {
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            else -> R.drawable.dice_6
        }

        diceImage.setImageResource(drawableResource)
    }

}
