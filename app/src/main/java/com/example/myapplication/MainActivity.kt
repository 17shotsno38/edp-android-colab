package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    BusinessCard(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun BusinessCard(modifier: Modifier = Modifier) {
    // Vibrant maximalist gradient
    val mainGradient = Brush.verticalGradient(
        colors = listOf(
            Color(0xFFFF00CC), // Hot Pink
            Color(0xFF3333FF), // Neon Blue
            Color(0xFF00FFCC)  // Cyan
        )
    )

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(mainGradient)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Header / Identity Section
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 40.dp)
                    .background(
                        Color.Black.copy(alpha = 0.3f),
                        shape = RoundedCornerShape(32.dp)
                    )
                    .border(3.dp, Color.White, RoundedCornerShape(32.dp))
                    .padding(24.dp)
            ) {
                Surface(
                    shape = CircleShape,
                    modifier = Modifier
                        .size(160.dp)
                        .border(6.dp, Color(0xFF00FFCC), CircleShape),
                    color = Color.White
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ellefanningwhathtehelly),
                        contentDescription = "Profile Picture",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                }
                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    text = "ELLA FANNING",
                    fontSize = 44.sp,
                    fontWeight = FontWeight.Black,
                    color = Color.White,
                    letterSpacing = 2.sp,
                    lineHeight = 44.sp
                )
                Text(
                    text = "SENIOR SOFTWARE ARCHITECT",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = Color(0xFFFFDE03), // Bright Yellow
                    letterSpacing = 2.sp
                )
            }

            // Contact Details Section
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 40.dp)
                    .background(
                        Color.White,
                        shape = RoundedCornerShape(32.dp)
                    )
                    .border(4.dp, Color(0xFFFF00CC), RoundedCornerShape(32.dp))
                    .padding(20.dp)
            ) {
                ContactRowMax(icon = Icons.Default.Phone, text = "+63 934-224-5678", color = Color(0xFF3333FF))
                HorizontalDivider(color = Color.LightGray, thickness = 1.dp, modifier = Modifier.padding(horizontal = 8.dp))
                ContactRowMax(icon = Icons.Default.Share, text = "@efanning", color = Color(0xFFFF00CC))
                HorizontalDivider(color = Color.LightGray, thickness = 1.dp, modifier = Modifier.padding(horizontal = 8.dp))
                ContactRowMax(icon = Icons.Default.Email, text = "reallyreal@gmail.com", color = Color(0xFF00BFA5))
            }
        }
    }
}

@Composable
fun ContactRowMax(icon: ImageVector, text: String, color: Color) {
    Row(
        modifier = Modifier
            .padding(vertical = 12.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(45.dp)
                .background(color, RoundedCornerShape(12.dp)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.size(24.dp)
            )
        }
        Spacer(modifier = Modifier.width(20.dp))
        Text(
            text = text,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
    }
}

@Preview(showBackground = true)
@Composable
fun BusinessCardPreview() {
    MyApplicationTheme {
        BusinessCard()
    }
}
