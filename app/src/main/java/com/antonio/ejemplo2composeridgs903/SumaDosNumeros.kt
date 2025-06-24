package com.antonio.ejemplo2composeridgs903

import android.widget.RadioButton
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@Composable
fun SumaDosNumeros(){
    var num1 by remember()  { mutableStateOf("") }
    var num2 by remember() { mutableStateOf("")}
    var resultado by remember() { mutableStateOf("") }

    Column (modifier = Modifier
        .fillMaxSize()
        .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ){
        TextField(
            value = num1,
            onValueChange = {num1 = it},
            label = {Text("Número 1")},
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        TextField(
            value = num2,
            onValueChange = {num2 = it},
            label = {Text("Número 2")},
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        var selected = RadioButtonSingleSelection()
        Button(onClick = {
            val n1 = num1.toIntOrNull()
            val n2 = num2.toIntOrNull()

            if (n1 != null && n2 != null) {
                resultado = when (selected) {
                    "Suma" -> (n1 + n2).toString()
                    "Resta" -> (n1 - n2).toString()
                    "Multiplicacion" -> (n1 * n2).toString()
                    "Division" -> if (n2 != 0) (n1 / n2).toString() else "Error: División por cero"
                    else -> "Operación no válida"
                }
            } else {
                resultado = "Error: Ingrese números válidos"
            }
        }) {
            Text("Calcular")
        }
        Text(text = resultado)


    }
}

@Composable
fun RadioButtonSingleSelection(modifier: Modifier = Modifier): String {
    val radioOptions = listOf("Suma", "Resta", "Multiplicacion", "Division")
    val (selectedOption, onOptionSelected) = remember { mutableStateOf(radioOptions[0]) }
    // Note that Modifier.selectableGroup() is essential to ensure correct accessibility behavior
    Column(modifier.selectableGroup()) {
        radioOptions.forEach { text ->
            Row(
                Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .selectable(
                        selected = (text == selectedOption),
                        onClick = { onOptionSelected(text) },
                        role = Role.RadioButton
                    )
                    .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = (text == selectedOption),
                    onClick = null // null recommended for accessibility with screen readers
                )
                Text(
                    text = text,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(start = 16.dp)
                )
            }
        }
    }
    return selectedOption
}