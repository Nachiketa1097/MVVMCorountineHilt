package com.toroi.ftl.mvvmcorountinehilt.model

data class UserResponse(
    val token: String,
    val user: User
)

data class User(
    val __v: Int,
    val _id: String,
    val createdAt: String,
    val email: String,
    val password: String,
    val updatedAt: String
)