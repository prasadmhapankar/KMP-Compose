package presentation

import kotlinproject.composeapp.generated.resources.Res
import kotlinproject.composeapp.generated.resources.app_name
import kotlinproject.composeapp.generated.resources.details_screen_name
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.StringResource

@OptIn(ExperimentalResourceApi::class)
enum class AppScreen(val title: StringResource) {
    ListScreen(title = Res.string.app_name),
    DetailScreen(title = Res.string.details_screen_name)
}