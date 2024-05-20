import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import di.koinViewModel
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.KoinContext
import presentation.AppScreen
import presentation.components.AppBar
import presentation.feature_list.FeatureListScreen
import presentation.feature_list.FeatureListViewModel

@Composable
@Preview
fun App() {
    MaterialTheme {
        KoinContext {
            val navController = rememberNavController()
            // Get current back stack entry
            val backStackEntry by navController.currentBackStackEntryAsState()

            // Get the name of the current screen
            val currentScreen = AppScreen.valueOf(
                backStackEntry?.destination?.route ?: AppScreen.ListScreen.name
            )
            Scaffold(
                topBar = {
                    AppBar(
                        currentScreen = currentScreen,
                        canNavigateBack = navController.previousBackStackEntry != null,
                        navigateUp = { navController.navigateUp() }
                    )
                }
            ) { innerPadding ->
                NavHost(
                    navController = navController,
                    startDestination = AppScreen.ListScreen.name,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                ) {
                    composable(route = AppScreen.ListScreen.name) {
                        val listViewModel = koinViewModel<FeatureListViewModel>()
                        val state = listViewModel.featureListState.collectAsState()
                        FeatureListScreen(
                            state = state
                        )
                    }
                    composable(route = AppScreen.DetailScreen.name) {
                        Box(
                            modifier = Modifier.fillMaxWidth(),
                            contentAlignment = Alignment.Center,
                        ) {
                            Text(
                                text = "Text A Details",
                                style = MaterialTheme.typography.bodyLarge,
                                color = MaterialTheme.colorScheme.onPrimary,
                            )
                        }
                    }
                }
            }
        }
    }
}