package com.android.myou.api

import com.android.myou.Dto.DiaryRequestDto
import com.android.myou.Dto.ResponseDtoListDiaryListDto
import com.android.myou.Dto.ResponseDtoString
import com.android.myou.Dto.User
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {
    //user-controller
    @POST("/api/user")
    suspend fun signUp(
        @Body data : User
    ) : ResponseDtoString
    @GET("/api/user")
    suspend fun logIn(
        @Query("username") username : String,
        @Query("password") password : String
    ) : ResponseDtoString

//    diary-controller
    @POST("/api/diary")
    suspend fun addDiary(
        @Body data : DiaryRequestDto
    ) : ResponseDtoString
    @DELETE("/api/diary")
    suspend fun deleteDiary(
        @Query("id") id : Int
    ) : ResponseDtoString
    @GET("/api/diary/user")
    suspend fun getMyAllDiary(
        @Query("username") username : String
    ) : ResponseDtoListDiaryListDto
    @GET("/api/diary/all")
    suspend fun getAllDiary(
        @Query("username") username : String
    ) : ResponseDtoListDiaryListDto
}