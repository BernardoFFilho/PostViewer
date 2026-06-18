package br.edu.ifsp.scl.sc3037291.postviewer.data.repository

import br.edu.ifsp.scl.sc3037291.postviewer.data.local.AppDatabase
import br.edu.ifsp.scl.sc3037291.postviewer.data.local.LocalComment
import br.edu.ifsp.scl.sc3037291.postviewer.data.remote.ApiComment
import br.edu.ifsp.scl.sc3037291.postviewer.data.remote.Post
import br.edu.ifsp.scl.sc3037291.postviewer.data.remote.RetrofitInstance
import kotlinx.coroutines.flow.Flow

class PostRepository(private val database: AppDatabase) {


    //busca a lista completa de posts da API
    suspend fun getPosts(): List<Post> {
        return RetrofitInstance.api.getPosts()
    }

     //busca os comentários de um post específico na API
     suspend fun getApiComments(postId: Int): List<ApiComment> {
        return RetrofitInstance.api.getComments(postId)
    }

    //retorna um Flow reativo dos comentários locais de um post
    fun getLocalComments(postId: Int): Flow<List<LocalComment>> {
        return database.localCommentDao().getCommentsForPost(postId)
    }

    //salva um novo comentário local no banco de dados Room
    suspend fun insertLocalComment(comment: LocalComment) {
        database.localCommentDao().insertComment(comment)
    }
}
