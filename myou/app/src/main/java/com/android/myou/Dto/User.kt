package com.android.myou.Dto

data class User(
    val name : String,
    val username : String,
    val password : String
)

data class ResponseDtoString(
    val status : Boolean,
    val data : String
)
