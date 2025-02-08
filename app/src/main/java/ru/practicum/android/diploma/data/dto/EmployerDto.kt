package ru.practicum.android.diploma.data.dto

import com.google.gson.annotations.SerializedName

class EmployerDto(
    val name: String?,
    @SerializedName("logo_urls")
    val logoUrls: LogoUrlsDto? = null
)
