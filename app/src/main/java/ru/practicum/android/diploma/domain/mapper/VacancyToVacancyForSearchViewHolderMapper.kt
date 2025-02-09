package ru.practicum.android.diploma.domain.mapper

import android.content.Context
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.domain.models.Vacancy
import ru.practicum.android.diploma.domain.models.VacancyForSearchViewHolder
import ru.practicum.android.diploma.util.Mapper

class VacancyToVacancyForSearchViewHolderMapper(private val context: Context) :
    Mapper<Vacancy, VacancyForSearchViewHolder> {
    override fun map(from: Vacancy) = VacancyForSearchViewHolder(
        vacancyId = from.vacancyId,
        titleWithCity = getTitleWithCity(from.name, from.address?.city),
        area = from.area,
        employer = from.employer,
        salary = createSalaryInterval(
            from.salary?.from,
            from.salary?.to,
            from
                .salary?.currency
        ),
        experience = from.experience,
        employmentForm = from.employmentForm,
        employment = from.employment,
        schedule = from.schedule,
        description = from.description,
        keySkills = from.keySkills,
        alternateUrl = from.alternateUrl
    )

    private fun getTitleWithCity(name: String?, city: String?): String? {
        return if (city == null) {
            name
        } else {
            "$name, $city"
        }
    }

    private fun createSalaryInterval(from: Int?, to: Int?, currency: String?): String {
        val currencyInSymbol = when (currency) {
            "AZN" -> context.getString(R.string.AZN)
            "BYR" -> context.getString(R.string.BYR)
            "EUR" -> context.getString(R.string.EUR)
            "GEL" -> context.getString(R.string.GEL)
            "KGS" -> context.getString(R.string.KGS)
            "KZT" -> context.getString(R.string.KZT)
            "RUR" -> context.getString(R.string.RUR)
            "UAH" -> context.getString(R.string.UAH)
            "USD" -> context.getString(R.string.USD)
            "UZS" -> context.getString(R.string.UZS)
            else -> currency
        }
        return when {
            from != null && to == null -> "от ${formattedSalary(from)} $currencyInSymbol"
            from == null && to != null -> "до ${formattedSalary(to)} $currencyInSymbol"
            from != null && to != null -> "от ${formattedSalary(from)} до ${formattedSalary(to)} $currencyInSymbol"
            else -> context.getString(R.string.salary_not_specified)
        }
    }

    private fun formattedSalary(value: Int): String {
        return "%,d".format(value).replace(",", " ")
    }
}
