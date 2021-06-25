package stas.batura.podlodkacompose.ui.sessions

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.*
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.transform.CircleCropTransformation
import com.google.accompanist.coil.rememberCoilPainter
import stas.batura.podlodkacompose.data.rawdata.getTestSession
import stas.batura.podlodkacompose.data.room.Session
import stas.batura.podlodkacompose.data.room.SessionFav
import stas.batura.podlodkacompose.ui.theme.PodlodkaComposeTheme

@Composable
fun SessionItem(
    session: SessionFav,
    onSessClick: (Session) -> Unit,
    addToFavClick: (Session) -> Unit,
    remFromFavClick: (Session) -> Unit
    ) {
    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier.padding(16.dp),
        elevation = 8.dp,
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
                .clickable {
                    onSessClick(session.toSession())
                })
        {
            Box(modifier = Modifier) {
                SessionImage(session = session.toSession())
            }

            Box(modifier = Modifier.weight(1.0f)) {
                SessionContent(session = session.toSession())
            }

            Box(modifier = Modifier) {
                FavButton(
                    session = session,
                    addToFavClick = addToFavClick,
                    remFromFavClick = remFromFavClick
                    )
            }
        }
    }
}

@Composable
fun SessionContent(session: Session) {
    Column() {
        Text(text = "${session.speaker}", style = MaterialTheme.typography.subtitle1)
        Text(text = "${session.timeInterval}", style = MaterialTheme.typography.subtitle1)
        Text(text = "${session.description}", style = MaterialTheme.typography.body1)
    }
}

@Composable
fun SessionImage(session: Session) {
    Image(
        painter = rememberCoilPainter(
            request = session.imageUrl,
            requestBuilder = {
                transformations(CircleCropTransformation())
            }
        ),
        contentDescription = "photo",
        modifier = Modifier
            .size(80.dp)
            .padding(end = 12.dp)
    )
}

@Composable
fun FavButton(
    session: SessionFav,
    addToFavClick: (Session) -> Unit,
    remFromFavClick: (Session) -> Unit) {
    if (session.isFav) {
        Icon(
            Icons.Default.Favorite,
            "fav icon",
            modifier = Modifier.clickable {  remFromFavClick(session.toSession())})
    } else {
        Icon(
            Icons.Default.FavoriteBorder,
            "icon",
            modifier = Modifier.clickable { addToFavClick(session.toSession()) })
    }
}

@Composable
fun InfinitelyPulsingHeart() {
    // Creates an [InfiniteTransition] instance for managing child animations.
    val infiniteTransition = rememberInfiniteTransition()

    // Creates a child animation of float type as a part of the [InfiniteTransition].
    val scale by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            // Infinitely repeating a 1000ms tween animation using default easing curve.
            animation = tween(1000),
            // After each iteration of the animation (i.e. every 1000ms), the animation will
            // start again from the [initialValue] defined above.
            // This is the default [RepeatMode]. See [RepeatMode.Reverse] below for an
            // alternative.
            repeatMode = RepeatMode.Restart
        )
    )

    // Creates a Color animation as a part of the [InfiniteTransition].
    val color by infiniteTransition.animateColor(
        initialValue = Red,
        targetValue = Color(0xff800000), // Dark Red
        animationSpec = infiniteRepeatable(
            // Linearly interpolate between initialValue and targetValue every 1000ms.
            animation = tween(1000, easing = LinearEasing),
            // Once [TargetValue] is reached, starts the next iteration in reverse (i.e. from
            // TargetValue to InitialValue). Then again from InitialValue to TargetValue. This
            // [RepeatMode] ensures that the animation value is *always continuous*.
            repeatMode = RepeatMode.Reverse
        )
    )

    Box(modifier = Modifier.padding(top = 8.dp)
        .size(24.dp)
        ) {
        CircularProgressIndicator(color = color)
    }
}


