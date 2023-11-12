package com.example.bmiapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bmiapp.ui.theme.BmiAppTheme
import com.example.bmiapp.ui.theme.OrangeBmi

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BmiAppTheme {
                BmiApp()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Metrics(bmiFieldState: Double, onNameChange: (Double) -> Unit, modifier: Modifier = Modifier) {

    var heightFieldState by remember {
        mutableStateOf("")
    }
    var weightFieldState by remember {
        mutableStateOf("")
    }

    Column() {
        Row(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = "HEIGHT",
                modifier = modifier
                    .align(alignment = Alignment.CenterVertically)
                    .weight(2f)
                    .padding(8.dp)

            )
            TextField(
                modifier = modifier
                    .align(alignment = Alignment.CenterVertically)
                    .weight(1f),

                value = heightFieldState,

                onValueChange = {
                    heightFieldState = it
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )

            Text(
                text = "CM",
                modifier = modifier
                    .align(alignment = Alignment.CenterVertically)
                    .padding(8.dp)
            )
        }

        Spacer(modifier = Modifier.padding(vertical = 16.dp))

        Row(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = "WEIGHT",
                modifier = modifier
                    .align(alignment = Alignment.CenterVertically)
                    .weight(2f)
                    .padding(8.dp)

            )
            TextField(
                modifier = modifier
                    .align(alignment = Alignment.CenterVertically)
                    .weight(1f),

                value = weightFieldState,

                onValueChange = {
                    weightFieldState = it
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )

            Text(
                text = "KG",
                modifier = modifier
                    .align(alignment = Alignment.CenterVertically)
                    .padding(8.dp)
            )
        }

        Spacer(modifier = Modifier.padding(vertical = 16.dp))

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp),
            colors = ButtonDefaults.buttonColors(containerColor = OrangeBmi),
            shape = RoundedCornerShape(10),
            onClick = {

                var bmi = weightFieldState.toDouble() /
                        ((heightFieldState.toDouble() / 100) * (heightFieldState.toDouble() / 100))

                val solution = String.format("%.1f", bmi)

                onNameChange(bmi)

            }
        )


        {
            Text(
                text = "Calculate",
                color = Color.Black,
                fontSize = 30.sp,
                modifier = Modifier
            )
        }
    }
}

@Composable
fun BmiView(bmiFieldState: Double) {

    var bmiConclusion by remember {
        mutableStateOf("")
    }

    bmiConclusion = if (bmiFieldState > 30.0) {
        "Obese"
    } else if (bmiFieldState > 25.0) {
        "Overweight"
    } else if (bmiFieldState > 19.0) {
        "Normal"
    } else {
        "Underweight"
    }

    val solution = String.format("%.1f", bmiFieldState)

    Column(
        modifier = Modifier
            .background(color = OrangeBmi)
            .fillMaxWidth()
    )

    {
        Text(
            text = "BMI",
            fontSize = 30.sp,
            modifier = Modifier
                .align(alignment = Alignment.CenterHorizontally)
                .padding(top = 16.dp)
        )
        Text(

            text = solution,
            fontSize = 132.sp,
            modifier = Modifier
                .align(alignment = Alignment.CenterHorizontally)
                .padding(2.dp)
        )

        Text(
            text = bmiConclusion,
            fontSize = 30.sp,
            modifier = Modifier
                .align(alignment = Alignment.CenterHorizontally)
                .padding(bottom = 16.dp)
        )


    }

}

@Composable
fun BmiApp() {

    var bmiFieldState by remember {
        mutableStateOf(0.0)
    }

    Column() {
        BmiView(bmiFieldState)
        Spacer(modifier = Modifier.padding(vertical = 16.dp))
        Metrics(bmiFieldState = bmiFieldState, onNameChange = { bmiFieldState = it })
    }


}

@Preview(showBackground = true)
@Composable
fun BmiAppPreview() {
    BmiAppTheme {
        BmiApp()
    }
}