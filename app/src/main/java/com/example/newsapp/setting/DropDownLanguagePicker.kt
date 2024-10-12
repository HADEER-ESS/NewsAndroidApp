package com.example.newsapp.setting

import android.content.Context
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.app.AppCompatDialog
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.os.LocaleListCompat
import com.example.newsapp.R
import com.example.newsapp.constants.Language
import com.example.newsapp.constants.LanguageChangeHelper
import com.example.newsapp.ui.theme.Black_Main
import com.example.newsapp.ui.theme.Green_Card
import com.example.newsapp.ui.theme.Red_card
import com.example.newsapp.ui.theme.White_Main

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropDownLanguagePicker(languages : List<Language>, context: Context){
    val langaugeChangeHelper by lazy {
        LanguageChangeHelper()
    }

    val local = langaugeChangeHelper.getLanguageCode(context)
    println("local is $local")
//    val english = stringResource(id = R.string.english_lang)
//    var changedLanguage : LocaleListCompat

    var isexpand by remember { mutableStateOf(false) }
    var selectedLang by remember {
        if(local == "en"){
            mutableStateOf(languages[0])
        }
        else{
            mutableStateOf(languages[1])
        }
    }

    ExposedDropdownMenuBox(
        expanded = isexpand,
        onExpandedChange = { isexpand = !isexpand},
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Transparent)
            .padding(15.dp, 10.dp)
    ) {
        TextField(
            value = selectedLang.name,
            onValueChange = {},
            readOnly = true,
            textStyle = TextStyle(color = Green_Card, fontSize = 14.sp, fontWeight = FontWeight.Bold),
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = isexpand) },
            modifier = Modifier
                .menuAnchor()
                .fillMaxWidth()
                .border(1.dp, Green_Card, RectangleShape),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = White_Main
            )
        )
        ExposedDropdownMenu(
            expanded = isexpand,
            onDismissRequest = { isexpand = false },
            modifier = Modifier
                .fillMaxWidth()
                .background(White_Main)
        ){
            languages.forEach { language ->
                DropdownMenuItem(
                    text = {
                        Text(
                            text = language.name,
                            fontSize = 12.sp,
                            color = if(language==selectedLang) Green_Card else Black_Main
                        )
                           },
                    onClick = {
                        selectedLang = language
                        isexpand = !isexpand
                        langaugeChangeHelper.changeLanguage(context, language.code)
                              },
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(White_Main)
                )
            }
        }


    }
}



@Preview
@Composable
fun DropDownLanguagePickerPreview(){
    val allLanguages = listOf(
        Language("en" , "English"),
        Language("ar" , "Arabic")
    )
    val context = LocalContext.current
    DropDownLanguagePicker(allLanguages, context)
}