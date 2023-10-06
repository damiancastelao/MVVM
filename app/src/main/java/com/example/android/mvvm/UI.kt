package com.example.android.mvvm

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun InterfazUsuario(miViewModel: MyViewModel) {

    // para que sea mas facil la etiqueta del log
    val TAG_LOG: String = "mensaje UI"

    // un cuadro de texto para mostrar los numeros
    Text(
        text = "Numeros: ${miViewModel.getNumero()}",
        modifier = Modifier.padding(32.dp),
        color = Color.DarkGray
    )
    // un boton para generar numeros aleatorios
    Button(
        onClick = {
            Log.d(TAG_LOG, "Dentro del onClick")
            miViewModel.crearRandom()
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

@Preview
@Composable
fun DefaultPreview() {
    MaterialTheme {
        InterfazUsuario(miViewModel = MyViewModel())
    }
}