package ru.practicum.android.diploma.domain.models

data class VacancyForSearchViewHolder(
    val vacancyId: Long,
    val titleWithCity: String?,
    val area: Area?,
    val employer: Employer?,
    val salary: String?,
    val experience: Experience?,
    val employmentForm: EmploymentForm?,
    val employment: EmploymentForm?,
    val schedule: Schedule?,
    val description: String?,
    val keySkills: List<Skill?>,
    val alternateUrl: String?,
)
