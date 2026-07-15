package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.BorderStroke
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
import androidx.compose.ui.draw.clip
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
                    modifier = Modifier.fillMaxSize(),
                    containerColor = Color(0xFFFAFAFA)
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
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

        Box(
            modifier = Modifier
                .fillMaxHeight(0.6f)
                .width(1.dp)
                .align(Alignment.Center)
                .offset(x = (-40).dp)
                .background(Color.LightGray.copy(alpha = 0.5f))
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceAround
        ) {

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Surface(
                    shape = CircleShape,
                    modifier = Modifier
                        .size(150.dp)
                        .padding(4.dp)
                        .border(1.dp, Color.LightGray.copy(alpha = 0.8f), CircleShape),
                    color = Color.White
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.asdasdasdsfsaf),
                        contentDescription = "Profile Picture",
                        modifier = Modifier
                            .padding(8.dp)
                            .clip(CircleShape),
                        contentScale = ContentScale.Crop
                    )
                }
                
                Spacer(modifier = Modifier.height(32.dp))
                
                Text(
                    text = "Jan Linux Orbeta",
                    fontSize = 36.sp,
                    fontWeight = FontWeight.Light,
                    letterSpacing = 6.sp,
                    color = Color.Black
                )
                
                Text(
                    text = "SOFTWARE ARCHITECT",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 3.sp,
                    color = Color(0xFF9E9E9E)
                )
            }


            Surface(
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .height(IntrinsicSize.Min),
                shape = RoundedCornerShape(20.dp),
                color = Color.White,
                shadowElevation = 2.dp,
                border = BorderStroke(1.dp, Color.LightGray.copy(alpha = 0.3f))
            ) {
                Column(
                    modifier = Modifier.padding(24.dp),
                    horizontalAlignment = Alignment.Start
                ) {
                    ContactRowUnique(icon = Icons.Default.Phone, text = "+63 934 224 5678")
                    Spacer(modifier = Modifier.height(16.dp))
                    ContactRowUnique(icon = Icons.Default.Share, text = "@whathtel")
                    Spacer(modifier = Modifier.height(16.dp))
                    ContactRowUnique(icon = Icons.Default.Email, text = "jorbeta04647@gmail.com")
                }
            }
        }
    }
}

@Composable
fun ContactRowUnique(icon: ImageVector, text: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = Color(0xFF424242),
            modifier = Modifier.size(18.dp)
        )
        Spacer(modifier = Modifier.width(20.dp))
        Text(
            text = text,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            color = Color(0xFF616161)
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
