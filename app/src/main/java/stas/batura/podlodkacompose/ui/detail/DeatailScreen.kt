package stas.batura.podlodkacompose.ui.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.unit.dp
import com.google.accompanist.coil.rememberCoilPainter
import stas.batura.podlodkacompose.data.room.Session

@Composable
fun DetailScreen(session: Session) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Image(
            painter = rememberCoilPainter(
                request = session.imageUrl
            ),
            contentDescription = "photo",
            modifier = Modifier
                .width(200.dp).height(200.dp).scale(1.0f).clip(
                    CircleShape)
        )
        Divider()
        Text(text = session.speaker)
        Text(text = "${session.date}, ${session.timeInterval}")
        Text(text = session.description)
    }
}
