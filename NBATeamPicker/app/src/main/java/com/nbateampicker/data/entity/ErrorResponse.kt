package com.nbateampicker.data.entity

data class ErrorResponse(
    val httpCode: String,
    val httpMessage: String,
    val moreInformation: String?,

    val errorCode: String?,
    val errorMessage: String?
)