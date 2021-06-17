package stas.batura.podlodkacompose.ui.sessions

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.coil.rememberCoilPainter
import stas.batura.podlodkacompose.data.out.SessionDay
import stas.batura.podlodkacompose.data.room.Session
import stas.batura.podlodkacompose.ui.theme.PodlodkaComposeTheme

@Composable
fun SessionItem(session: Session, modifier: Modifier) {
        Row(verticalAlignment = Alignment.CenterVertically, modifier = modifier) {
            Image(
                painter = rememberCoilPainter(
                    request = session.imageUrl
                ),
                contentDescription = "photo",
                modifier = Modifier.size(50.dp)
            )
            Spacer(Modifier.width(10.dp))
            Text("Item ${session.id}")
        }
}

@ExperimentalFoundationApi
@Composable
fun DayItem(day: SessionDay, modifier: Modifier) {

        LazyColumn(
            contentPadding = PaddingValues(top = 8.dp)
        ) {
            stickyHeader { Text(text = day.day, modifier.padding(bottom = 12.dp)) }
            items(day.sessions) { session ->
                SessionItem(session = session, modifier = Modifier.fillParentMaxWidth())
            }
        }
}

