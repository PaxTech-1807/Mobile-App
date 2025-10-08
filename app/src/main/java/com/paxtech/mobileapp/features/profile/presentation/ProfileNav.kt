package com.paxtech.mobileapp.features.profile.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

private sealed class ProfileDestination(val route: String) {
    object Home : ProfileDestination("profile_home")
    object ProfileDetails : ProfileDestination("profile_details")
    object Favorites : ProfileDestination("profile_favorites")
    object Language : ProfileDestination("profile_language")
    object Settings : ProfileDestination("profile_settings")
}

private data class ProfileNavActions(
    val openProfileDetails: () -> Unit,
    val openFavorites: () -> Unit,
    val openLanguage: () -> Unit,
    val openSettings: () -> Unit,
    val goBack: () -> Unit,
)

@Preview(showBackground = true)
@Composable
private fun ProfileHomeScreenPreview() {
    ProfileNavHost(navController = rememberNavController())
}

@Composable
fun ProfileNav(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    ProfileNavHost(navController = navController, modifier = modifier)
}

@Composable
internal fun ProfileNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    val actions = remember(navController) {
        ProfileNavActions(
            openProfileDetails = { navController.navigateSingleTopTo(ProfileDestination.ProfileDetails.route) },
            openFavorites = { navController.navigateSingleTopTo(ProfileDestination.Favorites.route) },
            openLanguage = { navController.navigateSingleTopTo(ProfileDestination.Language.route) },
            openSettings = { navController.navigateSingleTopTo(ProfileDestination.Settings.route) },
            goBack = {
                if (!navController.popBackStack()) {
                    navController.navigateSingleTopTo(ProfileDestination.Home.route)
                }
            }
        )
    }

    NavHost(
        navController = navController,
        startDestination = ProfileDestination.Home.route,
        modifier = modifier
    ) {
        composable(ProfileDestination.Home.route) {
            ProfileHomeScreen(
                profile = ProfileInformation.Sample,
                onNavigateToProfile = actions.openProfileDetails,
                onNavigateToFavorites = actions.openFavorites,
                onNavigateToLanguage = actions.openLanguage,
                onNavigateToSettings = actions.openSettings
            )
        }
        composable(ProfileDestination.ProfileDetails.route) {
            ProfileDetailScreen(
                profile = ProfileInformation.Sample,
                onBack = actions.goBack
            )
        }
        composable(ProfileDestination.Favorites.route) {
            FavoritesScreen(onBack = actions.goBack)
        }
        composable(ProfileDestination.Language.route) {
            LanguageScreen(onBack = actions.goBack)
        }
        composable(ProfileDestination.Settings.route) {
            SettingsScreen(onBack = actions.goBack)
        }
    }
}

private fun NavController.navigateSingleTopTo(route: String) {
    navigate(route) {
        launchSingleTop = true
        restoreState = true
    }
}