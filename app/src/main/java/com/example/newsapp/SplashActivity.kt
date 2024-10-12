package com.example.newsapp

import android.app.LocaleConfig
import android.app.LocaleManager
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.os.Build
import android.os.Bundle
import android.os.LocaleList
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.os.LocaleListCompat
import androidx.navigation.compose.rememberNavController
import com.example.newsapp.constants.Language
import com.example.newsapp.ui.theme.NewsAppTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.Locale

class SplashActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NewsAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainSplashScreen(modifier = Modifier.padding(innerPadding))
                    navigateToMain(this@SplashActivity)
                }
            }
        }
    }
}

@Composable
private fun navigateToMain(context: ComponentActivity) {
    val coroutine = rememberCoroutineScope()
    LaunchedEffect(Unit) {
        coroutine.launch {
            delay(2000) //Two second delay
            val intent =  Intent(context, MainActivity::class.java)
            context.startActivity(intent)
            context.finish()
        }
    }
}

@Composable
fun MainSplashScreen(modifier: Modifier = Modifier) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .paint(
                painterResource(id = R.drawable.app_pattern),
                contentScale = ContentScale.Crop
            )
    ){
        Image(
            painter = painterResource(id = R.drawable.app_logo),
            contentDescription = "Application Logo",
            modifier = Modifier.fillMaxHeight(1f)
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MainSplashScreenPreview() {
    NewsAppTheme {
        MainSplashScreen()
    }
}
/*
//TODO()
1- Drawer item                                  (Done)
1*- Tab Bar screen title                        (Done)
2- Drawer functionality                         (Done)
3- Setting screen                               (Done)
4- View News Details                            (Done)
5- Navigate to full article news                (Done)
6- Get news data when enter page                (Done)
7- Search screen                                (Done)
 */