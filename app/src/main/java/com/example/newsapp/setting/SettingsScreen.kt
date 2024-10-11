package com.example.newsapp.setting

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.newsapp.R
import com.example.newsapp.ui.theme.Black_Main

@Composable
fun SettingsScreenView(navController: NavController, modifier: Modifier = Modifier){
    val languages = listOf(
        stringResource(id = R.string.english_lang),
        stringResource(id = R.string.arabic_lang)
    )
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(0.dp, 26.dp)
            .paint(painterResource(id = R.drawable.app_pattern), contentScale = ContentScale.Crop)
    ){
        Text(
            text = stringResource(id = R.string.language_title),
            color = Black_Main,
            fontSize = 16.sp,
            textAlign = TextAlign.Start,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(8.dp)
        )
        DropDownLanguagePicker(languages)
    }

}

@Preview(backgroundColor = 0xFFFFFFFF, device = "spec:parent=pixel_5", showSystemUi = true,
    showBackground = true
)
@Composable
fun SettingsScreenViewPreview(){
    val navController = NavController(LocalContext.current)
    SettingsScreenView(navController)
}