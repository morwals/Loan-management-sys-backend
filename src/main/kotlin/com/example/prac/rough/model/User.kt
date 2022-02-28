package com.example.prac.rough.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document("users")
data class User(

    @Id
    var user_id:Int,

    var user_name:String,
    var full_name:String,
    var user_password:String,
    val email:String,
    var isAdmin:Int
)