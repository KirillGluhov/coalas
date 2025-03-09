package com.nastirlex.domain.core.model

data class User(
    val birthdate: String,
    val birthplace: String,
    val created_at: String,
    val email: String,
    val gender: String,
    val id: Int,
    val name: String,
    val passport: Passport,
    val password: String,
    val patronymic: String,
    val phone: String,
    val remember_token: String,
    val surname: String,
    val updated_at: String
)