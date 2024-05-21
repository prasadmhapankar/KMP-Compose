package presentation.feature_details

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import data.dto.EpicImagery
import data.dto.getImageUrl
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import kotlinproject.composeapp.generated.resources.Res
import kotlinproject.composeapp.generated.resources.caption
import kotlinproject.composeapp.generated.resources.date
import kotlinproject.composeapp.generated.resources.version
import org.jetbrains.compose.resources.stringResource


class DetailsScreen(private val epicImagery: EpicImagery) : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.current

        AnimatedContent(true, label = "EpicImagery")
        {
            if (it) {
                EpicImageryDetails({ navigator?.pop() }, epicImagery)
            }
        }
    }

    @Composable
    fun EpicImageryDetails(
        onBackClick: () -> Unit,
        epicImagery: EpicImagery,
        modifier: Modifier = Modifier,
    ) {
        Scaffold(
            topBar = {
                // Back Button
            },
            modifier = modifier,
        ) { paddingValues ->
            Column(
                Modifier
                    .verticalScroll(rememberScrollState())
                    .padding(paddingValues)
            ) {
                KamelImage(
                    resource = asyncPainterResource(data = epicImagery.getImageUrl()),
                    contentDescription = epicImagery.caption,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1f)
                        .background(Color.LightGray),
                )

                SelectionContainer {
                    Column(Modifier.padding(12.dp)) {
                        Text(epicImagery.identifier, style = MaterialTheme.typography.bodyLarge)
                        Spacer(Modifier.height(6.dp))
                        LabeledInfo(
                            stringResource(Res.string.version),
                            data = epicImagery.version ?: "..."
                        )
                        LabeledInfo(
                            stringResource(Res.string.caption),
                            data = epicImagery.caption ?: "..."
                        )
                        LabeledInfo(stringResource(Res.string.date), data = epicImagery.date ?: "..")


                    }
                }
            }
        }
    }


    @Composable
    private fun LabeledInfo(
        label: String,
        data: String,
        modifier: Modifier = Modifier,
    ) {
        Column(modifier.padding(vertical = 4.dp)) {
            Spacer(Modifier.height(6.dp))
            Text(
                buildAnnotatedString {
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                        append("$label: ")
                    }
                    append(data)
                }
            )
        }
    }

}

