package ru.practicum.android.diploma.data.dto

import com.google.gson.annotations.SerializedName

class AreaDto(
    val id: String,
    val name: String? = null,
    @SerializedName("parent_id")
    val parentId: String? = null,
    val areas: List<AreaDto>
) : Response()
