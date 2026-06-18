package br.edu.ifsp.scl.sc3037291.postviewer.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "local_comments")
data class LocalComment(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val postId: Int,
    val body: String
)
