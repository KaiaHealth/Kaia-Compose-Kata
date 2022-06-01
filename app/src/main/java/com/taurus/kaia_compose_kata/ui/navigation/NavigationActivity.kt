package com.taurus.kaia_compose_kata.ui.navigation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.taurus.kaia_compose_kata.ui.navigation.basic.BasicNavGraph
import com.taurus.kaia_compose_kata.ui.theme.KaiaComposeKataTheme

class NavigationActivity : ComponentActivity() {

    private lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KaiaComposeKataTheme() {
                navController = rememberNavController()
                BasicNavGraph(navController = navController)
            }
        }
    }
}
