package com.nastirlex.data.net.core.model

import com.nastirlex.domain.core.model.User
import kotlinx.serialization.Serializable

@Serializable
data class UserDto(
    val birthdate: String = "01.01.2001",
    val birthplace: String = "Moscow",
    val created_at: String = "",
    val email: String = "email@mail.ru",
    val gender: String = "female",
    val id: Int = 1,
    val name: String = "name",
    val passport: PassportDto = PassportDto(
        created_at = "",
        id = 1,
        issue_date = "",
        issue_place = "",
        number = 123456,
        series = 1234,
        unitcode = "",
        updated_at = "",
        user_id = 1,
    ),
    val password: String = "password",
    val patronymic: String = "patronymic",
    val phone: String = "89991234567",
    val remember_token: String = "remember_token",
    val surname: String = "surname",
    val updated_at: String = "12.12.2012",
)

fun UserDto.toDomain(): User =
    User(
        birthdate,
        birthplace,
        created_at,
        email,
        gender,
        id,
        name,
        passport.toDomain(),
        password,
        patronymic,
        phone,
        remember_token,
        surname,
        updated_at,
    )