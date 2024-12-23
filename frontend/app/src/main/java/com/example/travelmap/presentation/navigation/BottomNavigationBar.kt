package com.example.travelmap.presentation.navigation

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.travelmap.R
import com.example.travelmap.ui.theme.TravelMapTheme

data class TopLevelRoute<T : Any>(val route: T, val iconRes: Int)

val TOP_LEVEL_ROUTES = listOf(
    TopLevelRoute(route = HomeScreen, iconRes = R.drawable.home),
    TopLevelRoute(route = RoutesScreen, iconRes = R.drawable.world),
    TopLevelRoute(route = ChatScreen, iconRes = R.drawable.chat),
    TopLevelRoute(route = ProfileScreen, iconRes = R.drawable.user)
)


@Composable
fun BottomNavigationBar(
    navController: NavHostController = rememberNavController()
) {

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    BottomNavigation (
        backgroundColor = Color.White
    ) {
        TOP_LEVEL_ROUTES.forEachIndexed { index, topLevelRoute ->
            BottomNavigationItem(
                selected = currentDestination?.hierarchy?.any{
                    it.hasRoute(route = topLevelRoute.route::class)
                } == true,
                onClick = { navController.navigate(topLevelRoute.route) },
                icon = { Icon(painter = painterResource(topLevelRoute.iconRes), contentDescription = null) },
                selectedContentColor = MaterialTheme.colors.primary,
                unselectedContentColor = Color(0xFFB7B7B7)
            )
        }
    }

}


@Preview
@Composable
fun BottomNavigationPreview(){
    TravelMapTheme {
        BottomNavigationBar()
    }
}