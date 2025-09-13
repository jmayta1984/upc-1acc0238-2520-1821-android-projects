package pe.edu.upc.easyshop

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import pe.edu.upc.easyshop.ui.theme.AppTheme

@Composable
fun AppNav(){
    val navController = rememberNavController()

    NavHost(navController, startDestination = Route.Login.route) {
        composable(Route.Login.route) {
            Login {
               navController.navigate(Route.Main.route)
            }
        }

        composable(Route.Main.route) {
            Main()
        }

    }
}

sealed class Route(val route: String){
    object Login: Route("login")
    object Main: Route("main")
}

@Preview
@Composable
fun AppNavPreview(){
    AppTheme (dynamicColor = false){
        AppNav()
    }
}