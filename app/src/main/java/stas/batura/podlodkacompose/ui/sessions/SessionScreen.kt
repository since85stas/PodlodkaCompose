package stas.batura.podlodkacompose.ui.sessions

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import stas.batura.podlodkacompose.data.room.Session

/**
 * Stateless component that is responsible for the entire screen.
 *
 * @param days (state) list of [TodoItem] to display
 */
@ExperimentalFoundationApi
@Composable
fun SessionsScreen(
    grouped:  Map<String, List<Session>>,
    onSessClick: (Session) -> Unit
) {
    Column {
        LazyColumn(
            modifier = Modifier.weight(1f),
            contentPadding = PaddingValues(top = 8.dp)
        ) {
            grouped.forEach { (initial, contactsForInitial) ->
                stickyHeader {
                    Text(initial)
                }

                items(contactsForInitial) { session ->
                    SessionItem(
                        session = session,
                        modifier = Modifier.fillParentMaxWidth(),
                        onSessClick = onSessClick
                        )
                }
            }
        }

//             For quick testing, a random item generator button
        Button(
            onClick = {  },
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
        ) {
            Text("Add random item")
        }
    }
}