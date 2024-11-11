package com.abc.businesscard

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Call
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material.icons.rounded.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.BiasAlignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.abc.businesscard.ui.theme.BusinessCardTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BusinessCardTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    BusinessCard(Modifier.padding(innerPadding).fillMaxSize())
                }
            }
        }
    }
}

@Composable
fun BusinessCard(modifier: Modifier = Modifier) {
    Box(modifier = modifier) {
        PersonInfo(
            photoId = R.drawable.android_logo,
            name = stringResource(R.string.name),
            title = stringResource(R.string.title),
            modifier = Modifier.align(BiasAlignment(horizontalBias = 0f, verticalBias = -0.3f))
        ) // setting: val Center: Alignment = BiasAlignment(0f, 0f), i.e. -0.3f is an offset of 30% up
        PersonContacts(
            stringResource(R.string.phone_number),
            stringResource(R.string.social_media),
            stringResource(R.string.email),
            modifier = Modifier.align(Alignment.BottomCenter)
        ) // You can also tweak verticalBias instead of padding
    }
}

@Composable
fun PersonInfo(photoId: Int, name: String, title: String, modifier: Modifier = Modifier) {
    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = modifier) {
        Image(
            painter = painterResource(id = photoId),
            contentDescription = "$name profile photo",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(170.dp)
                .clip(RoundedCornerShape(30.dp))
                .background(
                    Color.Black
                )
        )
        Text(text = name, fontWeight = FontWeight.Normal, fontSize = 50.sp, lineHeight = 0.9.em)
        Text(text = title, fontWeight = FontWeight.SemiBold, fontSize = 20.sp, color = MaterialTheme.colorScheme.tertiary)
    }
}

@Composable
fun PersonContacts(
    phoneNumber: String, socialMedia: String, email: String, modifier: Modifier = Modifier
) {
    Column(modifier = modifier.padding(30.dp)) {
        CustomContact(icon = Icons.Rounded.Call, contact = phoneNumber)
        CustomContact(icon = Icons.Rounded.Share, contact = socialMedia)
        CustomContact(icon = Icons.Rounded.Email, contact = email)
    }
}

@Composable
fun CustomContact(icon: ImageVector, contact: String, modifier: Modifier = Modifier) {
    Row(modifier = modifier.padding(5.dp)) {
        Icon(
            imageVector = icon,
            contentDescription = "${icon.name} icon",
            tint = Color(MaterialTheme.colorScheme.tertiary.value),
            modifier = modifier.padding(end = 15.dp)
        )
        Text(contact)
    }
}


@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_NO, showSystemUi = false)
@Composable
fun BusinessCardPreview() {
    BusinessCardTheme {
        BusinessCard(modifier = Modifier.padding(bottom = 15.dp))
    }
}


@Preview(showBackground = true)
@Composable
fun PersonInfoPreview() {
    BusinessCardTheme {
        PersonInfo(photoId = R.drawable.android_logo, name = "Deni", title = "That's ME!")
    }
}

@Preview(showBackground = true)
@Composable
fun PersonContactsPreview() {
    BusinessCardTheme {
        PersonContacts(
            "+77777777777", "@imjustaregularnormal", "thatsme@gmail.com"
        )
    }
}
