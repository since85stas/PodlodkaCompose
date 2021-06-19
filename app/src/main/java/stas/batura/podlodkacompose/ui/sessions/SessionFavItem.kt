package stas.batura.podlodkacompose.ui.sessions

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import stas.batura.podlodkacompose.data.room.Session
import stas.batura.podlodkacompose.data.room.SessionFav

@Composable
fun SessionFavItem(
    session: Session
) {
    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier.width(100.dp).padding(16.dp),
        elevation = 16.dp,
    ) {
        SessionContentFav(session = session)
    }
}

@Composable
fun SessionContentFav(session: Session) {
//    Column() {
//        Text(text = "${session.speaker}")
//        Text(text = "${session.timeInterval}")
    Text(text = "${session.description}")
//    }
}