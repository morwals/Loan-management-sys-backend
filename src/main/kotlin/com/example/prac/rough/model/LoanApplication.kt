package com.example.prac.rough.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document("usersDetails")

data class LoanApplication(
    @Id
    var customer_id:Int,
    var user_income:Int,
    val cibil_score:Int,
    var user_assets:String,
    val loan_purpose:String,
    val loan_amount:Int,
    val mortage:Int,
    val duration:Int,
    val isAdmin:Int

)

