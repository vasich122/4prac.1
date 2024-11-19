package com.example.a4prac

import androidx.lifecycle.LiveData

class PostsRepository(private val postsDAO:PostsDAO ) {
    val allPosts : LiveData<List<Post>> = postsDAO.getAllPosts()
    suspend fun insert(newPosts: Post) {
        postsDAO.insertPosts(newPosts)
    }
    suspend fun update(posts: Post) {
        postsDAO.updatePosts(posts)
    }
}