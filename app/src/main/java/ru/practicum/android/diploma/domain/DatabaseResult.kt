package ru.practicum.android.diploma.domain

import ru.practicum.android.diploma.domain.models.VacancyForSearchViewHolder

sealed class DatabaseResult {
    data class Success(val vacancies: List<VacancyForSearchViewHolder>) : DatabaseResult()
    data class Error(val message: String) : DatabaseResult()
    data object Empty : DatabaseResult()
}
