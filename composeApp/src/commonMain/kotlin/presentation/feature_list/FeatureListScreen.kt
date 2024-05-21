package presentation.feature_list

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import data.dto.getImageUrl
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource

@Composable
fun FeatureListScreen(
    modifier: Modifier = Modifier,
    state: State<EpicImageryListState>
) {
    val imageryList = state.value.list ?: emptyList()
    AnimatedContent(targetState = imageryList.isNotEmpty()) { isContentAvailable ->
        if (isContentAvailable) {
            LazyVerticalGrid(
                columns = GridCells.Adaptive(180.dp),
                modifier = modifier.fillMaxSize(),
                contentPadding = PaddingValues(8.dp)
            ) {
                items(
                    items = imageryList,
                    key = { it.identifier }
                ) { obj ->
                    Column(
                        modifier
                            .padding(8.dp)
                            .clickable {
                                //onClick()
                            }
                    ) {
                        KamelImage(
                            resource = asyncPainterResource(data = obj.getImageUrl()),
                            contentDescription = obj.caption,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .fillMaxWidth()
                                .aspectRatio(1f)
                                .background(Color.LightGray),
                        )
                        Text(
                            text = obj.date ?: "",
                            style = MaterialTheme.typography.labelMedium,
                            color = MaterialTheme.colorScheme.primary,
                        )
                        Text(
                            text = obj.caption ?: "",
                            style = MaterialTheme.typography.labelMedium,
                            color = MaterialTheme.colorScheme.primary,
                        )
                        HorizontalDivider()
                    }
                }
            }
        } else {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(
                    modifier = Modifier.fillMaxSize(0.2f)
                )
            }
        }
    }
}