package ru.practicum.android.diploma.ui.search.fragment

import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.databinding.ItemVacancyBinding
import ru.practicum.android.diploma.domain.models.Vacancy
import ru.practicum.android.diploma.domain.models.VacancyForSearchViewHolder
import ru.practicum.android.diploma.util.format.dpToPx

private const val CORNER_RADIUS = 12

class VacancyViewHolder(
    private val binding: ItemVacancyBinding,
    private val onClick: (vacancyId: Long) -> Unit,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(model: VacancyForSearchViewHolder) {
        binding.textViewVacancyTitle.text = model.titleWithCity
        binding.textViewVacancyEmployer.text = model.employer?.name
        binding.textViewVacancySalary.text = model.salary
        val image: ImageView = binding.imageViewVacancyLogo

        Glide.with(itemView)
            .load(model.employer?.logoUrls?.size240)
            .placeholder(R.drawable.ic_droid_48)
            .error(R.drawable.ic_droid_48)
            .transform(CenterCrop(), RoundedCorners(itemView.context.dpToPx(CORNER_RADIUS)))
            .into(image)

        binding.root.setOnClickListener { _ -> onClick(model.vacancyId) }
    }
}
