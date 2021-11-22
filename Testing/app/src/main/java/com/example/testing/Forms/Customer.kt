package com.example.testing.Forms

import android.service.autofill.OnClickAction
import android.util.Log
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.activity.OnBackPressedDispatcher
import androidx.activity.compose.BackHandler
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.testing.Screen

@Composable
fun Customer(navController: NavController){

    Box(
        modifier = Modifier
//            .clickable {
//                navController.navigate(Screen.MainScreen.route) {
//                    popUpTo(Screen.MainScreen.route) {
//                        inclusive = true
//                    }
//                }
//            }
            .fillMaxSize()
            .background(Color.White), contentAlignment = Alignment.Center
    ) {
        Column {

            Text(
                text = "In Working",
                Modifier.padding(10.dp),
                color = Color.Black

            )


        }



    }
    com.example.testing.Forms.BackHandler(onBack = { Screen.MainScreen.route }, navController = navController)
}
@Composable
public fun BackHandler(enabled: Boolean = true, onBack: () -> Unit , navController: NavController) {

    val currentOnBack by rememberUpdatedState(onBack)

    val backCallback = remember {
        object : OnBackPressedCallback(enabled) {
            override fun handleOnBackPressed() {

                navController.navigate(Screen.MainScreen.route){
                    popUpTo(Screen.MainScreen.route){
                        inclusive = true
                    }
                }


            }
        }
    }


    SideEffect {
        backCallback.isEnabled = enabled
    }
    val backDispatcher = checkNotNull(LocalOnBackPressedDispatcherOwner.current) {

    }.onBackPressedDispatcher

    val lifecycleOwner = LocalLifecycleOwner.current
    DisposableEffect(lifecycleOwner, backDispatcher) {

        backDispatcher.addCallback(lifecycleOwner, backCallback)

        onDispose {
            backCallback.remove()
        }
    }
}




