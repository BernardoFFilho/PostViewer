package br.edu.ifsp.scl.sc3037291.postviewer.ui.postdetail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import br.edu.ifsp.scl.sc3037291.postviewer.data.local.AppDatabase
import br.edu.ifsp.scl.sc3037291.postviewer.data.local.LocalComment
import br.edu.ifsp.scl.sc3037291.postviewer.data.remote.ApiComment
import br.edu.ifsp.scl.sc3037291.postviewer.data.repository.PostRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PostDetailViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = PostRepository(AppDatabase.getInstance(application))

    //lista de comentários carregados da API
    private val _apiComments = MutableStateFlow<List<ApiComment>>(emptyList())
    val apiComments: StateFlow<List<ApiComment>> = _apiComments


    //lista de comentários adicionados localmente pelo usuário,
    private val _localComments = MutableStateFlow<List<LocalComment>>(emptyList())

    val localComments: StateFlow<List<LocalComment>> = _localComments
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading
    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage


    fun loadData(postId: Int) {
        // carregamento da API
        viewModelScope.launch {
            _isLoading.value = true
            try {
                _apiComments.value = repository.getApiComments(postId)
            } catch (e: Exception) {
                _errorMessage.value = e.message ?: "Erro ao carregar comentários"
            } finally {
                _isLoading.value = false
            }
        }

        viewModelScope.launch {
            repository.getLocalComments(postId).collect { comments ->
                _localComments.value = comments
            }
        }
    }

    fun addLocalComment(postId: Int, body: String) {
        if (body.isBlank()) return
        viewModelScope.launch {
            repository.insertLocalComment(LocalComment(postId = postId, body = body))
        }
    }
}
