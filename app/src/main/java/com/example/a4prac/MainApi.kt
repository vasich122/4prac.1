package com.example.a4prac

import retrofit2.http.GET
import retrofit2.http.Path

interface MainApi {
    @GET("posts/{id}")
    suspend fun getPostById(@Path("id") id: Int): Post

}