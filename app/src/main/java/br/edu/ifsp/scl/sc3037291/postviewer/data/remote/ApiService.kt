package br.edu.ifsp.scl.sc3037291.postviewer.data.remote

import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    //busca a lista completa de posts do endpoint GET /posts
    @GET("posts")
    suspend fun getPosts(): List<Post>

    //busca os comentários de um post específico pelo ID
    @GET("posts/{id}/comments")
    suspend fun getComments(@Path("id") postId: Int): List<ApiComment>
}
