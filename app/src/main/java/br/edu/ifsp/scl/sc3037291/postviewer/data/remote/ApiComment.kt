package br.edu.ifsp.scl.sc3037291.postviewer.data.remote

import com.google.gson.annotations.SerializedName

data class ApiComment(
    @SerializedName("id") val id: Int,
    @SerializedName("postId") val postId: Int,
    @SerializedName("name") val name: String,
    @SerializedName("email") val email: String,
    @SerializedName("body") val body: String
)
