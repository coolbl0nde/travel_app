package com.example.travelmap.presentation.navigation

import android.util.Log
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.travelmap.R
import com.example.travelmap.ui.theme.TravelMapTheme

@Composable
fun BottomNavigationBar(
    navController: NavHostController = rememberNavController()
) {

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val TOP_LEVEL_ROUTES = listOf(
        TopLevelRoute(route = HomeScreen, icon = painterResource(id = R.drawable.home)),
        TopLevelRoute(route = RoutesScreen, icon = painterResource(id = R.drawable.world)),
        TopLevelRoute(route = ChatScreen, icon = painterResource(id = R.drawable.chat)),
        TopLevelRoute(route = ProfileScreen, icon = painterResource(id = R.drawable.user))
    )

    BottomNavigation (
        backgroundColor = Color.White
    ) {
        TOP_LEVEL_ROUTES.forEach{ topLevelRoute ->
            BottomNavigationItem(
                selected = currentRoute.toString() == topLevelRoute.route.toString().split('@')[0],
                onClick = { navController.navigate(topLevelRoute.route) },
                icon = { Icon(painter = topLevelRoute.icon, contentDescription = null) },
                selectedContentColor = MaterialTheme.colors.primary,
                unselectedContentColor = Color(0xFFB7B7B7)
            )
        }
    }

}

data class TopLevelRoute<T : Any>(val route: T, val icon: Painter)


@Preview
@Composable
fun BottomNavigationPreview(){
    TravelMapTheme {
        BottomNavigationBar()
    }
}