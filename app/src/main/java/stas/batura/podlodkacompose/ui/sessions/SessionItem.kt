package stas.batura.podlodkacompose.ui.sessions

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.coil.rememberCoilPainter
import stas.batura.podlodkacompose.data.room.Session
import stas.batura.podlodkacompose.ui.theme.PodlodkaComposeTheme

@Composable
fun SessionItem(session: Session, modifier: Modifier) {
    session!!.let {
        Row(verticalAlignment = Alignment.CenterVertically, modifier = modifier) {
            Image(
                painter = rememberCoilPainter(
                    request = "https://developer.android.com/images/brand/Android_Robot.png"
                ),
                contentDescription = "photo",
                modifier = Modifier.size(50.dp)
            )
            Spacer(Modifier.width(10.dp))
            Text("Item ${session.id}")
        }
    }
}

