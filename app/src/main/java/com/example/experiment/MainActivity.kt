package com.example.experiment

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.experiment.ui.theme.ExperimentTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            ExperimentTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ListIntersectionApp()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListIntersectionApp() {
    var list1Input by rememberSaveable { mutableStateOf("") }
    var list2Input by rememberSaveable { mutableStateOf("") }
    var list3Input by rememberSaveable { mutableStateOf("") }
    var result1 by rememberSaveable { mutableStateOf("") }
    var result2 by rememberSaveable { mutableStateOf("") }
    var result3 by rememberSaveable { mutableStateOf("") }

    Column(
        modifier = Modifier
            .padding(horizontal = 10.dp, vertical = 25.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {

        Text(
            text = "Assignment",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(top = 50.dp, bottom = 20.dp)
        )

        OutlinedTextField(
            value = list1Input,
            onValueChange = { list1Input = it },
            label = { Text("List 1 (comma-separated)") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        OutlinedTextField(
            value = list2Input,
            onValueChange = { list2Input = it },
            label = { Text("List 2 (comma-separated)") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        OutlinedTextField(
            value = list3Input,
            onValueChange = { list3Input = it },
            label = { Text("List 3 (comma-separated)") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            modifier = Modifier.width(120.dp),
            shape = RoundedCornerShape(10),
            onClick = {
                val list1 = list1Input.split(",").map { it.trim().toInt() }
                val list2 = list2Input.split(",").map { it.trim().toInt() }
                val list3 = list3Input.split(",").map { it.trim().toInt() }

                // calculate intersection
                val intersection = list1.intersect(list2.toSet()).intersect(list3.toSet())
                result1 = intersection.joinToString(", ")

                // calculate union
                val union = list1.union(list2).union(list3).toList()
                result2 = union.joinToString(", ")

                //calculate maximum value
                val max = union.maxOrNull() ?: 0
                result3 = max.toString()
            }
        ) {
            Text(
                text = "Calculate"
            )
        }

        Column(
            modifier = Modifier
                .padding(30.dp)
                .width(400.dp)
                .height(500.dp)
                .border(2.dp, Color.Magenta, RoundedCornerShape(10.dp))
                .verticalScroll(rememberScrollState())
        ) {
            // Display Intersection Result
            Row (
                modifier = Modifier.padding(15.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(15.dp)
            ) {
                Text(
                    modifier = Modifier.width(90.dp),
                    text = "Intersection:",
                    style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 15.sp)
                )
                Text(
                    text = result1,
                    style = TextStyle(fontSize = 15.sp),
                )
            }

            // Display Union Result
            Row (
                modifier = Modifier.padding(15.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(15.dp)
            ) {
                Text(
                    modifier = Modifier.width(90.dp),
                    text = "Union:",
                    style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 15.sp)
                )
                Text(
                    text = result2,
                    style = TextStyle(fontSize = 15.sp),
                )
            }

            // Display Maximum value
            Row (
                modifier = Modifier.padding(15.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(15.dp)
            ) {
                Text(
                    modifier = Modifier.width(90.dp),
                    text = "Maximum:",
                    style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 15.sp)
                )
                Text(
                    text = result3,
                    style = TextStyle(fontSize = 15.sp),
                )
            }
        }
    }
}

@Preview
@Composable
fun Preview() {
    ExperimentTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            ListIntersectionApp()
        }
    }
}