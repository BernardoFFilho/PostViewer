package br.edu.ifsp.scl.sc3037291.postviewer.data.remote

import com.google.gson.annotations.SerializedName

data class Post(
    @SerializedName("id") val id: Int,
    @SerializedName("userId") val userId: Int,
    @SerializedName("title") val title: String,
    @SerializedName("body") val body: String,
    @SerializedName("comments") val comments: List<ApiComment>? = null,
    var localCommentsCount: Int = 0
)
