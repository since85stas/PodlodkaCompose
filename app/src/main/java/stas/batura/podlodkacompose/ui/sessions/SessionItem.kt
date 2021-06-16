package stas.batura.podlodkacompose.ui.sessions

import androidx.compose.foundation.layout.Row
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.tooling.preview.Preview
import stas.batura.podlodkacompose.data.room.Session
import stas.batura.podlodkacompose.ui.theme.PodlodkaComposeTheme

@Composable
fun SessionItem(session: Session, modifier: Modifier) {
    Row(modifier = modifier) {
        Text(text = "start")
        Text(text = "end")
    }
}

