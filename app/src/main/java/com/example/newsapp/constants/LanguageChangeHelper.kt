package com.example.newsapp.constants

import android.app.LocaleManager
import android.content.Context
import android.os.Build
import android.os.LocaleList
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat

class LanguageChangeHelper {

    fun changeLanguage(context : Context, selectedLanguageCode : String){

//        Android version >= 13
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
            context.getSystemService(LocaleManager::class.java).applicationLocales = LocaleList.forLanguageTags(selectedLanguageCode)
        }
//        Android version < 13
        else{
            AppCompatDelegate.setApplicationLocales(LocaleListCompat.forLanguageTags(selectedLanguageCode))
        }
    }

    fun getLanguageCode(context : Context) : String{
//        Android version >= 13
        return if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
             context.getSystemService(LocaleManager::class.java).applicationLocales[0]?.toLanguageTag()?.split("-")?.first() ?: "en"
        }
//        Android version < 13
        else{
             AppCompatDelegate.getApplicationLocales()[0]?.toLanguageTag()?.split("-")?.first() ?: "en"
        }
    }
}