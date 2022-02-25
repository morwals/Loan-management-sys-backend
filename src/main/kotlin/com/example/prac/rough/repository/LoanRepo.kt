package com.example.prac.rough.repository

import com.example.prac.rough.model.LoanApplication
import org.springframework.data.mongodb.repository.MongoRepository

interface LoanRepo : MongoRepository<LoanApplication, Int>