package ru.practicum.android.diploma.util.diffutils

import androidx.recyclerview.widget.DiffUtil
import ru.practicum.android.diploma.domain.models.Vacancy
import ru.practicum.android.diploma.domain.models.VacancyForSearchViewHolder

class VacancyDiffCallback : DiffUtil.ItemCallback<VacancyForSearchViewHolder>() {
//    override fun areItemsTheSame(oldItem: Vacancy, newItem: Vacancy): Boolean {
//        return oldItem.vacancyId == newItem.vacancyId
//    }
//
//    override fun areContentsTheSame(oldItem: Vacancy, newItem: Vacancy): Boolean {
//        return oldItem == newItem
//    }

    override fun areItemsTheSame(oldItem: VacancyForSearchViewHolder, newItem: VacancyForSearchViewHolder): Boolean {
        return oldItem.vacancyId == newItem.vacancyId
    }

    override fun areContentsTheSame(oldItem: VacancyForSearchViewHolder, newItem: VacancyForSearchViewHolder): Boolean {
        return oldItem == newItem
    }
}
