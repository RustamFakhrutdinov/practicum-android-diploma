package ru.practicum.android.diploma.data.network

import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.QueryMap
import ru.practicum.android.diploma.BuildConfig
import ru.practicum.android.diploma.data.dto.AreaDto
import ru.practicum.android.diploma.data.dto.IndustryDto
import ru.practicum.android.diploma.data.dto.VacanciesResponseDto
import ru.practicum.android.diploma.data.dto.VacancyDto

interface HhApi {
    @Headers(
        TOKEN_NAME + BuildConfig.HH_ACCESS_TOKEN,
        USER_AGENT
    )
    @GET("vacancies")
    suspend fun searchVacancies(
        @Query("text") text: String?,
        @QueryMap optionsForString: Map<String, String>,
        @QueryMap optionsForInt: Map<String, Int>,
        @Query("only_with_salary") onlyWithSalary: Boolean = false,
        @Query("per_page") perPage: Int = 20
    ): VacanciesResponseDto

    @Headers(
        TOKEN_NAME + BuildConfig.HH_ACCESS_TOKEN,
        USER_AGENT
    )
    @GET("/vacancies/{vacancy_id}")
    suspend fun getVacancyDetails(@Path("vacancy_id") id: String): VacancyDto

    @Headers(
        TOKEN_NAME + BuildConfig.HH_ACCESS_TOKEN,
        USER_AGENT
    )
    @GET("/areas")
    suspend fun getAreas(): List<AreaDto>

    @Headers(
        TOKEN_NAME + BuildConfig.HH_ACCESS_TOKEN,
        USER_AGENT
    )
    @GET("/areas/{area_id}")
    suspend fun getAreaInfo(@Path("area_id") id: String): AreaDto

    @Headers(
        TOKEN_NAME + BuildConfig.HH_ACCESS_TOKEN,
        USER_AGENT
    )
    @GET("/industries")
    suspend fun getIndustries(): List<IndustryDto>

    companion object {
        const val TOKEN_NAME = "Authorization: Bearer "
        const val USER_AGENT = "HH-User-Agent: heheru (heheru2025@gmail.com)"
    }
}
