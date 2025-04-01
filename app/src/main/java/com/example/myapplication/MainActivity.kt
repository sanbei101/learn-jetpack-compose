package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                App()
            }
        }
    }
}

object AppScreens {
    const val Home = "home"
    const val User = "user/{name}"
}

@Composable
fun App() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = AppScreens.Home) {
        composable(AppScreens.Home) {
            HomeScreen(navController)

        }
        composable(
            route = AppScreens.User,
            arguments = listOf(
                navArgument("name") {
                    type = NavType.StringType
                    defaultValue = "John Doe"
                }
            )
        ) { backStackEntry ->
            UserProfile(
                navController = navController,
                name = backStackEntry.arguments?.getString("name") ?: "John Doe",
            )
        }
    }
}

@Composable
fun HomeScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text("Welcome to Hello World App", style = MaterialTheme.typography.headlineLarge)
        Button(
            onClick = {
                navController.navigate("user/john")
            },
        ) {
            Text("Go to User")
        }
    }
}

@Composable
fun UserProfile(
    navController: NavController,
    name: String
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier.align(Alignment.Center)
        ) {
            Text("Hello $name", style = MaterialTheme.typography.headlineLarge)
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {
                    navController.popBackStack()
                }
            ) {
                Text("Go, Back")
            }
        }
    }
}