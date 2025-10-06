package com.paxtech.mobileapp.core.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.paxtech.mobileapp.features.authentication.presentation.splash.SplashScreen
import com.paxtech.mobileapp.features.authentication.presentation.welcome.WelcomeScreen
import com.paxtech.mobileapp.features.clientDashboard.presentation.salondetail.SalonDetailRoute

@Preview
@Composable
fun AppNav(){
    val navController = rememberNavController()
    NavHost(navController, startDestination = Route.Splash.route){

        // Pantalla de Splash
        composable(Route.Splash.route) {
            SplashScreen(
                onNavigateToWelcome = {
                    navController.navigate(Route.Welcome.route) {
                        popUpTo(Route.Splash.route) { inclusive = true }
                    }
                }
            )
        }
        
        // Pantalla de Bienvenida
        composable(Route.Welcome.route) {
            WelcomeScreen(
                onStartClick = {
                    navController.navigate(Route.Home.route) {
                        popUpTo(Route.Splash.route) { inclusive = true }
                    }
                }
            )
        }

        // Pantalla principal
        composable(Route.Home.route){
            Main(
                onClick = {id ->
                    navController.navigate("${Route.SalonDetails.route}/$id")
                }
            )
        }
        
        composable(
            route = Route.SalonDetails.routeWithArgument,
            arguments = listOf(navArgument(Route.SalonDetails.argument) {
                type = NavType.IntType
            })
        ) { backStack ->
            val id = backStack.arguments?.getInt(Route.SalonDetails.argument) ?: 0
            SalonDetailRoute(
                salonId = id,
                onBack = { navController.popBackStack() }
            )
        }
        // composable { Route. }
    }
}



sealed class Route(val route: String) {

    object Splash: Route("splash")
    object Welcome: Route("welcome")
    object Login: Route("login")
    object Register: Route("register")
    
    object Main: Route("main")
    object Home : Route("home")
    object Cart : Route("cart")
    object Profile : Route("profile")

    object Services : Route("services")

    object SalonDetails : Route("salon_detail") {
        const val routeWithArgument = "salon_detail/{id}"
        const val argument = "id"
    }
}

