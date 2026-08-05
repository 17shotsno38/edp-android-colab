package com.example.labact6

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.labact6.ui.theme.Labact6Theme

// 1950s Diner Colors
val DinerPink = Color(0xFFFFC1CC)
val DinerTeal = Color(0xFF4DB6AC)
val DinerCream = Color(0xFFFFFDD0)
val DinerRed = Color(0xFFD32F2F)
val DinerChrome = Color(0xFFB0BEC5)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Labact6Theme {
                DinerGroceryApp()
            }
        }
    }
}

@Composable
fun DinerGroceryApp() {
    var itemName by remember { mutableStateOf("") }
    val groceryList = remember { mutableStateListOf<String>("Eggs", "Bread", "Milk") }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = DinerCream
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Title - Neon-ish Diner Sign Style
            Surface(
                color = DinerRed,
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier
                    .padding(bottom = 24.dp)
                    .border(4.dp, DinerChrome, RoundedCornerShape(16.dp))
            ) {
                Text(
                    text = " DINER GROCERY LIST ",
                    fontSize = 32.sp,
                    fontWeight = FontWeight.ExtraBold,
                    fontFamily = FontFamily.Serif,
                    color = Color.White,
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                )
            }

            // Input Section
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedTextField(
                    value = itemName,
                    onValueChange = { itemName = it },
                    placeholder = { Text("Enter an item......", color = Color.Gray) },
                    modifier = Modifier.weight(1f),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = DinerTeal,
                        unfocusedBorderColor = DinerChrome,
                        focusedLabelColor = DinerTeal,
                        unfocusedContainerColor = Color.White,
                        focusedContainerColor = Color.White
                    ),
                    shape = RoundedCornerShape(8.dp)
                )
                Spacer(modifier = Modifier.width(12.dp))
                Button(
                    onClick = {
                        if (itemName.isNotBlank()) {
                            groceryList.add(itemName)
                            itemName = ""
                        }
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = DinerTeal),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier
                        .height(56.dp)
                        .border(2.dp, DinerChrome, RoundedCornerShape(8.dp))
                ) {
                    Text("ADD", fontWeight = FontWeight.Bold, fontSize = 18.sp)
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Total Count Display (Part B challenge) - Styled with DinerPink
            Surface(
                color = DinerPink,
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .border(2.dp, DinerChrome, RoundedCornerShape(8.dp))
            ) {
                Text(
                    text = "Total items: ${groceryList.size}",
                    modifier = Modifier.padding(12.dp),
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    fontFamily = FontFamily.Monospace,
                    color = Color.DarkGray
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            
            Spacer(modifier = Modifier.height(16.dp))

            // List Section
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(groceryList) { item ->
                    DinerItemRow(item, onDelete = { groceryList.remove(item) })
                }
            }
            
            // Retro Checkerboard Bottom Decoration (Optional)
            Row(modifier = Modifier.fillMaxWidth().height(20.dp)) {
                repeat(10) { index ->
                    Box(modifier = Modifier.weight(1f).fillMaxHeight().background(if(index % 2 == 0) DinerRed else Color.White))
                }
            }
        }
    }
}

@Composable
fun DinerItemRow(name: String, onDelete: () -> Unit) {
    Surface(
        color = Color.White,
        modifier = Modifier.fillMaxWidth().border(1.dp, DinerChrome, RoundedCornerShape(4.dp)),
        shadowElevation = 2.dp
    ) {
        Row(
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = name,
                fontSize = 22.sp,
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.SemiBold,
                color = Color.Black
            )
            
            // Retro delete button [ x ]
            TextButton(
                onClick = onDelete,
                contentPadding = PaddingValues(0.dp)
            ) {
                Text(
                    text = "[ x ]",
                    fontSize = 20.sp,
                    fontFamily = FontFamily.Monospace,
                    color = DinerRed,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DinerPreview() {
    Labact6Theme {
        DinerGroceryApp()
    }
}
