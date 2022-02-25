package com.example.prac.rough.repository

import com.example.prac.rough.model.User
import org.springframework.data.mongodb.repository.MongoRepository

interface UserRepo : MongoRepository<User,Int>

