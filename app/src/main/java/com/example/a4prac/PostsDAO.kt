package com.example.a4prac

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface PostsDAO {
    @Insert
    fun insertPosts(Newpost: Post)
    @Query("SELECT * FROM posts")
    fun getAllPosts(): LiveData<List<Post>>
    @Update
    fun updatePosts(post: Post)
}