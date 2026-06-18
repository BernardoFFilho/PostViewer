package br.edu.ifsp.scl.sc3037291.postviewer.ui.postdetail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PostDetailScreen(postId: Int, viewModel: PostDetailViewModel = viewModel()) {
    LaunchedEffect(postId) { viewModel.loadData(postId) }

    val apiComments by viewModel.apiComments.collectAsState()
    val localComments by viewModel.localComments.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val errorMessage by viewModel.errorMessage.collectAsState()

    var commentText by remember { mutableStateOf("") }

    Scaffold(
        topBar = { TopAppBar(title = { Text("Comentários") }) }
    ) { paddingValues ->
        Column(modifier = Modifier.fillMaxSize().padding(paddingValues)) {
            //lista de comentários ou indicador de estado
            if (isLoading) {
                Box(
                    modifier = Modifier.weight(1f).fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            } else if (errorMessage != null) {
                Box(
                    modifier = Modifier.weight(1f).fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = errorMessage!!, color = MaterialTheme.colorScheme.error)
                }
            } else {
                LazyColumn(modifier = Modifier.weight(1f)) {
                    if (apiComments.isNotEmpty()) {
                        item {
                            Text(
                                "Da API",
                                style = MaterialTheme.typography.titleSmall,
                                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                            )
                        }
                        items(apiComments) { comment ->
                            CommentItem(authorName = comment.name, body = comment.body)
                        }
                    }
                    if (localComments.isNotEmpty()) {
                        item {
                            Text(
                                "Meus Comentários",
                                style = MaterialTheme.typography.titleSmall,
                                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                            )
                        }
                        items(localComments) { comment ->
                            CommentItem(authorName = "Você", body = comment.body)
                        }
                    }
                }
            }

            //campo de texto e botão
            Row(modifier = Modifier.fillMaxWidth().padding(8.dp)) {
                OutlinedTextField(
                    value = commentText,
                    onValueChange = { commentText = it },
                    placeholder = { Text("Adicionar comentário...") },
                    label = { Text("Novo comentário") },
                    modifier = Modifier.weight(1f)
                )
                Button(
                    onClick = {
                        viewModel.addLocalComment(postId, commentText)
                        commentText = "" // limpa o campo após enviar
                    },
                    enabled = commentText.isNotBlank(),
                    modifier = Modifier.padding(start = 8.dp)
                ) {
                    Text("Enviar")
                }
            }
        }
    }
}


//exibe um item de comentário.
@Composable
fun CommentItem(authorName: String, body: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 4.dp)
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text(text = authorName, style = MaterialTheme.typography.labelMedium)
            Text(text = body, style = MaterialTheme.typography.bodyMedium)
        }
    }
}
