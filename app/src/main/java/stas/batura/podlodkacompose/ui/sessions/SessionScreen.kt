package stas.batura.podlodkacompose.ui.sessions

import android.graphics.Paint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import stas.batura.podlodkacompose.data.room.Session
import stas.batura.podlodkacompose.data.room.SessionFav

/**
 * Stateless component that is responsible for the entire screen.
 *
 * @param days (state) list of [TodoItem] to display
 */
@ExperimentalFoundationApi
@Composable
fun SessionsScreen(
    favSess: List<Session>,
    grouped:  Map<String, List<SessionFav>>,
    onSessClick: (Session) -> Unit,
    addToFavClick: (Session) -> Unit,
    remFromFavClick: (Session) -> Unit
) {
    Column {
        Surface {
            Text(
                modifier = Modifier.padding(12.dp),
                text = "Избранное",
                style = MaterialTheme.typography.subtitle2
            )
        }
        if (favSess.size >0 ) {
            Box(modifier = Modifier.height(170.dp)) {
                LazyRow(modifier = Modifier.fillMaxHeight()) {
                    items(favSess) { session ->
                        SessionFavItem(session = session)
                    }
                }
            }
        }

        Surface {
            Text(
                modifier = Modifier.padding(12.dp),
                text = "Сессии",
                style = MaterialTheme.typography.subtitle2)
        }

        Box(modifier = Modifier.weight(5.0f)) {
            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
//            contentPadding = PaddingValues(top = 8.dp)
            ) {
                grouped.forEach { (day, sessions) ->
                    stickyHeader {
                        Surface {
                            Text(
                                modifier = Modifier.padding(12.dp),
                                text = day,
                                style = MaterialTheme.typography.body1)
                        }
                    }

                    items(sessions) { session ->
                        SessionItem(
                            session = session,
                            onSessClick = onSessClick,
                            addToFavClick = addToFavClick,
                            remFromFavClick = remFromFavClick
                        )
                    }
                }
            }
        }

    }
}