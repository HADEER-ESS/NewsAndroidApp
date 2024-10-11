package com.example.newsapp.drawer

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.newsapp.R
import com.example.newsapp.constants.Routes
import com.example.newsapp.ui.theme.Black_Main
import com.example.newsapp.ui.theme.Green_Card
import com.example.newsapp.ui.theme.White_Main
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun DrawerItemSheet(navController: NavController, drawerState : DrawerState, scope : CoroutineScope){
    var selectedItem by remember {
        mutableStateOf(Routes().HOME_ROUTES)
    }
    ModalDrawerSheet {
        Box(
            modifier = Modifier
                .background(Green_Card, RectangleShape)
                .fillMaxWidth()
                .fillMaxHeight(0.15f),
            contentAlignment = Alignment.Center
        ){
            Text(
                text = "News App!",
                color = White_Main,
                textAlign = TextAlign.Center,
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .fillMaxWidth()
            )
        }

        NavigationDrawerItem(
            label = {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ){
                    Image(
                        painter = painterResource(id = R.drawable.categories_icv),
                        contentDescription = "Category Icon"
                    )
                    Text(
                        text = "Categories",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Black_Main,
                        modifier = Modifier.padding(start = 10.dp)
                    )
            }
                    },
            selected = selectedItem == Routes().HOME_ROUTES,
            onClick = {
                selectedItem = Routes().HOME_ROUTES
                navController.navigate(Routes().HOME_ROUTES){
                    popUpTo(Routes().HOME_ROUTES){
                        inclusive = true
                    }
                    launchSingleTop = true
                    restoreState = true
                }
                scope.launch {
                    drawerState.close()
                }
            },
            modifier = Modifier
                .padding(6.dp)
                .background(
                    if (selectedItem == Routes().HOME_ROUTES) Green_Card else White_Main
                ),
            colors = NavigationDrawerItemDefaults.colors(
                selectedContainerColor = Green_Card,
                unselectedContainerColor = White_Main
            )
        )

        NavigationDrawerItem(
            label = {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ){
                    Image(
                        painter = painterResource(id = R.drawable.setting_icv),
                        contentDescription = "Setting Icon"
                    )
                    Text(
                        text = "Settings",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Black_Main,
                        modifier = Modifier.padding(start = 10.dp)
                    )
                }
            },
            selected = selectedItem == Routes().SETTING_ROUTES,
            onClick = {
                selectedItem = Routes().SETTING_ROUTES
                navController.navigate(Routes().SETTING_ROUTES){
                    launchSingleTop = true
                    restoreState = true
                }
                scope.launch {
                    drawerState.close()
                }
            },
            modifier = Modifier
                .padding(6.dp)
                .background(
                    if (selectedItem == Routes().SETTING_ROUTES) Green_Card else White_Main
                ),
            colors = NavigationDrawerItemDefaults.colors(
                selectedContainerColor = Green_Card,
                unselectedContainerColor = White_Main
            )
        )
    }
}

@Preview
@Composable
fun DrawerItemSheetPreview(){
    val navController = NavController(LocalContext.current)
//    DrawerItemSheet(navController, state : DrawerState.Closed(), CoroutineScope( ))
}