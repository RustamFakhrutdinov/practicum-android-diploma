package ru.practicum.android.diploma.data.dto

import com.google.gson.annotations.SerializedName

class VacancyDto(
    @SerializedName("id")
    val vacancyId: Long,
    val name: String? = null,
    val area: VacancyAreaDto? = null,
    val employer: EmployerDto? = null,
    val salary: SalaryDto? = null,
    val experience: ExperienceDto? = null,
    @SerializedName("employment_form")
    val employmentForm: EmploymentFormDto? = null,
    val employment: EmploymentFormDto? = null, // deprecated
    val schedule: ScheduleDto? = null,
    val description: String? = null,
    @SerializedName("key_skills")
    val keySkills: List<SkillDto?>,
    @SerializedName("alternate_url")
    val alternateUrl: String? = null,
    val address: AddressDto? = null
) : Response()
