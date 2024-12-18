package com.android.myou.Dto

data class DiaryRequestDto(
    val title : String,
    val context : String,
    val username : String,
    val visibility : Boolean
)
data class DiaryListDto(
    val id : Int,
    val title : String,
    val summary : String,
    val username : String,
    val createdAt : String
)

data class ResponseDtoListDiaryListDto(
    val status : Boolean,
    val data : List<DiaryListDto>
)