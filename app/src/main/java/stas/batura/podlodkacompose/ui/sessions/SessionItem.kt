package stas.batura.podlodkacompose.ui.sessions

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.transform.CircleCropTransformation
import com.google.accompanist.coil.rememberCoilPainter
import stas.batura.podlodkacompose.data.out.SessionDay
import stas.batura.podlodkacompose.data.rawdata.getTestSession
import stas.batura.podlodkacompose.data.room.Session
import stas.batura.podlodkacompose.ui.theme.PodlodkaComposeTheme

@Composable
fun SessionItem(
    session: Session,
    onSessClick: (Session) -> Unit,
    addToFavClick: (Session) -> Unit
    ) {
    Card(
        shape = RoundedCornerShape(8.dp),
//        modifier = Modifier.padding(16.dp),
        elevation = 16.dp,
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
            .clickable {
                onSessClick(session)
            })
        {
            SessionImage(session = session)

//            SessionContent(session = session)

//            FavButton(session = session, addToFavClick = addToFavClick)
        }
    }
}

@Composable
fun SessionContent(session: Session) {
//    Column() {
//        Text(text = "${session.speaker}")
//        Text(text = "${session.timeInterval}")
        Text(text = "${session.description}")
//    }
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
            .size(80.dp).fillMaxSize()
//            .clip(CircleShape)
    )
}

@Composable
fun FavButton(session: Session, addToFavClick: (Session) -> Unit) {
    Button(onClick = { addToFavClick(session) }) {
        Text(text = "fav")
    }
}

@Preview
@Composable
fun ItemPreview() {
    val sess = remember{ getTestSession()}
    SessionItem(session = sess, onSessClick = { /*TODO*/ }) {

    }
}

//@ExperimentalFoundationApi
//@Composable
//fun DayItem(day: SessionDay, modifier: Modifier) {
//
//        LazyColumn(
//            contentPadding = PaddingValues(top = 8.dp)
//        ) {
//            stickyHeader { Text(text = day.day, modifier.padding(bottom = 12.dp)) }
//            items(day.sessions) { session ->
//                SessionItem(session = session, modifier = Modifier.fillParentMaxWidth())
//            }
//        }
//}

