import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import cafe.adriel.voyager.navigator.Navigator
import di.koinViewModel
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.KoinContext
import presentation.feature_list.FeatureListViewModel
import presentation.feature_list.HomeScreen

@Composable
@Preview
fun App() {
    MaterialTheme {
        KoinContext {
            Scaffold(
                topBar = {
                    //TODO
                }
            ) {
                val listViewModel = koinViewModel<FeatureListViewModel>()
                val state = listViewModel.featureListState.collectAsState()
                Navigator(HomeScreen(state = state))
            }
        }
    }
}