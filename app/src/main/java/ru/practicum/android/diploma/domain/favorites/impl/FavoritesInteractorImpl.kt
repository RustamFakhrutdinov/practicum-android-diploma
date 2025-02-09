package ru.practicum.android.diploma.domain.favorites.impl

import kotlinx.coroutines.flow.Flow
import ru.practicum.android.diploma.domain.DatabaseResult
import ru.practicum.android.diploma.domain.VacancyNotFoundException
import ru.practicum.android.diploma.domain.favorites.api.FavoritesInteractor
import ru.practicum.android.diploma.domain.favorites.api.FavoritesRepository
import ru.practicum.android.diploma.domain.models.Vacancy

class FavoritesInteractorImpl(
    private val favoritesRepository: FavoritesRepository
) : FavoritesInteractor {

    override fun getFavoritesList(): Flow<DatabaseResult> {
        return favoritesRepository.getFavoritesList()
    }

    override suspend fun checkFavoriteStatus(vacancyId: Long): Boolean {
        return favoritesRepository.checkFavoriteStatus(vacancyId)
    }

    override suspend fun getVacancyByID(vacancyId: Long): Vacancy {
        return try {
            favoritesRepository.getVacancyByID(vacancyId)
        } catch (e: VacancyNotFoundException) {
            throw e
        }
    }

    override suspend fun saveVacancy(vacancy: Vacancy) {
        favoritesRepository.saveVacancy(vacancy)
    }

    override suspend fun deleteVacancy(vacancy: Vacancy) {
        favoritesRepository.deleteVacancy(vacancy)
    }

    override suspend fun removeVacancyById(vacancyId: Long) {
        favoritesRepository.removeVacancyById(vacancyId)
    }
}
