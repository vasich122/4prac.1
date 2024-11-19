package com.example.a4prac

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "posts")
data class Post(
    @PrimaryKey(autoGenerate = true)
    val roomId: Int = 0, // Это поле для Room, его можно сделать автоинкрементным
    val Userid: Int,
    val id: Int,
    val title: String,
    val body: String
)
