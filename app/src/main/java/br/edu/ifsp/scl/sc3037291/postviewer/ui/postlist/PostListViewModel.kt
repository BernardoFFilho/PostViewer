package br.edu.ifsp.scl.sc3037291.postviewer.ui.postlist

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import br.edu.ifsp.scl.sc3037291.postviewer.data.local.AppDatabase
import br.edu.ifsp.scl.sc3037291.postviewer.data.remote.Post
import br.edu.ifsp.scl.sc3037291.postviewer.data.repository.PostRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

sealed class PostListUiState {
    object Loading : PostListUiState()
    data class Success(val posts: List<Post>) : PostListUiState()
    data class Error(val message: String) : PostListUiState()
}

class PostListViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = PostRepository(AppDatabase.getInstance(application))

    private val _uiState = MutableStateFlow<PostListUiState>(PostListUiState.Loading)
    val uiState: StateFlow<PostListUiState> = _uiState

    init {
        loadPosts()
    }


    //carrega os posts da API e atualiza a UI
    private fun loadPosts() {
        viewModelScope.launch {
            _uiState.value = PostListUiState.Loading
            try {
                val posts = repository.getPosts()
                _uiState.value = PostListUiState.Success(posts)
            } catch (e: Exception) {
                _uiState.value = PostListUiState.Error(e.message ?: "Erro ao carregar posts")
            }
        }
    }
}
