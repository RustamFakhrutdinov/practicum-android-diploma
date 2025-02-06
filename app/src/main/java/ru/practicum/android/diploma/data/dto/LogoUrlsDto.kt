package ru.practicum.android.diploma.data.dto

import com.google.gson.annotations.SerializedName

class LogoUrlsDto(
    val original: String? = null,
    @SerializedName("90")
    val size90: String? = null,
    @SerializedName("240")
    val size240: String? = null
)
