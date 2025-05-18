package eu.tonkov.notesapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import eu.tonkov.notesapp.data.Note
import eu.tonkov.notesapp.ui.theme.NotesAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NotesAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    AppScreen()

                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun AppScreen(){
    val noteViewModel : NoteViewModel = viewModel()
    val notes = noteViewModel.notesFlow.collectAsState(initial = emptyList())
    val noteText = remember { mutableStateOf("") }

    Column (
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        TextField(onValueChange = {noteText.value = it}, value = noteText.value)
        Button(onClick = {
            noteViewModel.insert(Note(text = noteText.value))
            noteText.value = ""
        }){
            Text(text = "Save")
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            items(notes.value) { note ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                        .background(Color.LightGray.copy(alpha = 0.2f))
                        .border(1.dp, Color.Gray)
                        .padding(8.dp)
                ) {
                    Text(text = note.text)
                }
            }
        }
    }
}