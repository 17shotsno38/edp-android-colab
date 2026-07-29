package com.example.lalaqweqwe

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.SettingsBrightness
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lalaqweqwe.ui.theme.LalaqweqweTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            var isDarkTheme by remember { mutableStateOf(false) }
            LalaqweqweTheme(darkTheme = isDarkTheme) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    BusinessCard(
                        isDarkTheme = isDarkTheme,
                        onToggleTheme = { isDarkTheme = !isDarkTheme }
                    )
                }
            }
        }
    }
}

@Composable
fun BusinessCard(isDarkTheme: Boolean, onToggleTheme: () -> Unit) {
    val colorScheme = MaterialTheme.colorScheme
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Card(
            modifier = Modifier.fillMaxWidth().padding(8.dp),
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
            colors = CardDefaults.cardColors(
                containerColor = colorScheme.surface,
                contentColor = colorScheme.onSurface
            ),
            border = BorderStroke(2.dp, colorScheme.primary)
        ) {
            Column(
                modifier = Modifier
                    .padding(24.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
               
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Surface(
                        modifier = Modifier.size(120.dp),
                        shape = CircleShape,
                        color = colorScheme.surface,
                        border = BorderStroke(2.dp, colorScheme.primary)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.akoni),
                            contentDescription = null,
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Crop
                        )
                    }
                    Spacer(modifier = Modifier.size(16.dp))
                    Text(
                        text = "Jan Linux Orbeta",
                        fontSize = 32.sp,
                        style = MaterialTheme.typography.headlineLarge,
                        color = colorScheme.onSurface
                    )
                    Text(
                        text = "BSIT 3-2",
                        fontSize = 18.sp,
                        color = colorScheme.primary,
                        style = MaterialTheme.typography.titleMedium
                    )
                }

                Spacer(modifier = Modifier.size(32.dp))


                Column(
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Text(
                        text = "Name",
                        fontSize = 12.sp,
                        color = colorScheme.primary,
                        style = MaterialTheme.typography.titleMedium
                    )
                    InfoRow(icon = Icons.Default.Person, text = "Jan Linux Orbeta")
                    HorizontalDivider(modifier = Modifier.padding(horizontal = 16.dp), thickness = 0.5.dp, color = colorScheme.primary.copy(alpha = 0.2f))
                    
                    Text(
                        text = "Course",
                        fontSize = 12.sp,
                        color = colorScheme.primary,
                        style = MaterialTheme.typography.titleMedium
                    )
                    InfoRow(icon = Icons.Default.School, text = "Bachelor of Science in Information Technology")
                    HorizontalDivider(modifier = Modifier.padding(horizontal = 16.dp), thickness = 0.5.dp, color = colorScheme.primary.copy(alpha = 0.2f))
                    
                    Text(
                        text = "Section",
                        fontSize = 12.sp,
                        color = colorScheme.primary,
                        style = MaterialTheme.typography.titleMedium
                    )
                    InfoRow(icon = Icons.Default.Book, text = "BSIT 3-2")
                    HorizontalDivider(modifier = Modifier.padding(horizontal = 16.dp), thickness = 0.5.dp, color = colorScheme.primary.copy(alpha = 0.2f))
                    
                    Text(
                        text = "Contact",
                        fontSize = 12.sp,
                        color = colorScheme.primary,
                        style = MaterialTheme.typography.titleMedium
                    )
                    InfoRow(icon = Icons.Default.Phone , text = "09951215215")
                    HorizontalDivider(modifier = Modifier.padding(horizontal = 16.dp), thickness = 0.5.dp, color = colorScheme.primary.copy(alpha = 0.2f))
                    
                    Text(
                        text = "E-mail",
                        fontSize = 12.sp,
                        color = colorScheme.primary,
                        style = MaterialTheme.typography.titleMedium
                    )
                    InfoRow(icon = Icons.Default.Email, text = "jorbeta04647@liceo.edu.ph")
                }

                Spacer(modifier = Modifier.size(24.dp))

                Button(
                    onClick = onToggleTheme,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Icon(
                        imageVector = Icons.Default.SettingsBrightness,
                        contentDescription = null
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = if (isDarkTheme) "Let there be Light" else "Let there be Darkness")
                }
            }
        }
    }
}

@Composable
fun InfoRow(icon: ImageVector, text: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(8.dp)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = text, 
            fontSize = 16.sp,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun BusinessCardPreview() {
    LalaqweqweTheme(darkTheme = false) {
        BusinessCard(isDarkTheme = false, onToggleTheme = {})
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun BusinessCardDarkPreview() {
    LalaqweqweTheme(darkTheme = true) {
        BusinessCard(isDarkTheme = true, onToggleTheme = {})
    }
}