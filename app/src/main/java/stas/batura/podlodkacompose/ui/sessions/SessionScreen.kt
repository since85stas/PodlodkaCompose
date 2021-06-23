package stas.batura.podlodkacompose.ui.sessions

import android.widget.ImageButton
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import stas.batura.podlodkacompose.data.room.Session
import stas.batura.podlodkacompose.data.room.SessionFav
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.input.TextFieldValue

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
    remFromFavClick: (Session) -> Unit,
    onSearchClick: (String) -> Unit,
) {
    Column {

        Surface {
            var text by remember { mutableStateOf("") }
            Row() {
                OutlinedTextField(
                    value = text,
                    onValueChange = { text = it },
                    label = { Text("Label") },
                    modifier = Modifier.weight(1.0f).padding(end = 12.dp)
                )
                Box(modifier = Modifier.align(Alignment.CenterVertically)) {
                    Button(
                        onClick = { onSearchClick(text) },
                        content = { Text("Искать") },
                    )
                }
            }
        }

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

@Composable
fun SearchButton(
    onSearchClick: (String) -> Unit
) {
    Button(
        onClick = { onSearchClick("") },
        content = {Text("Искать")}
    )
}