package br.edu.ifsp.scl.sc3037291.postviewer.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface LocalCommentDao {

    @Query("SELECT * FROM local_comments WHERE postId = :postId")
    fun getCommentsForPost(postId: Int): Flow<List<LocalComment>>

    @Insert
    suspend fun insertComment(comment: LocalComment)
}
