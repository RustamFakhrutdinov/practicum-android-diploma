package ru.practicum.android.diploma.data.db

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import ru.practicum.android.diploma.data.converters.VacanciesConverter
import ru.practicum.android.diploma.data.db.entity.VacancyEntity
import ru.practicum.android.diploma.domain.DatabaseResult
import ru.practicum.android.diploma.domain.VacancyNotFoundException
import ru.practicum.android.diploma.domain.favorites.api.FavoritesRepository
import ru.practicum.android.diploma.domain.mapper.VacancyToVacancyForSearchViewHolderMapper
import ru.practicum.android.diploma.domain.models.Vacancy
import ru.practicum.android.diploma.domain.models.VacancyForSearchViewHolder
import java.io.IOException

class FavoritesRepositoryImpl(
    private val appDatabase: AppDatabase,
    private val converter: VacanciesConverter,
    private val vacancyMapper: VacancyToVacancyForSearchViewHolderMapper
) : FavoritesRepository {

    override fun getFavoritesList(): Flow<DatabaseResult> = try {
        appDatabase.vacancyDao().getFavoritesList().map { entities ->
            if (entities.isEmpty()) {
                DatabaseResult.Empty
            } else {
                val vacancies = entities.map { converter.convertFromShortEntity(it) }
                DatabaseResult.Success(convertFromVacancy(vacancies))
            }
        }
    } catch (e: IOException) {
        flowOf(DatabaseResult.Error("Ошибка базы данных: ${e.message}"))
    }.flowOn(Dispatchers.IO)

    override suspend fun getVacancyByID(vacancyId: Long): Vacancy {
        return try {
            val entity = appDatabase.vacancyDao().getFavoriteById(vacancyId)
            converter.convertFromDBtoVacancy(entity)
        } catch (e: IllegalArgumentException) {
            throw VacancyNotFoundException("Ошибка преобразования вакансии с id $vacancyId", e)
        }
    }

    override suspend fun checkFavoriteStatus(vacancyId: Long): Boolean {
        return getFavouriteByIdRequest(vacancyId) != null
    }

    private suspend fun getFavouriteByIdRequest(vacancyId: Long): VacancyEntity? {
        return withContext(Dispatchers.IO) {
            try {
                appDatabase.vacancyDao().getFavoriteById(vacancyId)
            } catch (e: IOException) {
                Log.i("DB", e.toString())
                null
            }
        }
    }

    override suspend fun saveVacancy(vacancy: Vacancy) {
        appDatabase.vacancyDao().addToFavorites(convertToDB(vacancy))
    }

    override suspend fun deleteVacancy(vacancy: Vacancy) {
        appDatabase.vacancyDao().removeFromFavorites(convertToDB(vacancy))
    }

    override suspend fun removeVacancyById(vacancyId: Long) {
        appDatabase.vacancyDao().removeById(vacancyId)
    }

    private fun convertToDB(vacancy: Vacancy) = converter.convertFromVacancyToDB(vacancy)

    private fun convertFromVacancy(vacancies: List<Vacancy>): List<VacancyForSearchViewHolder> {
        return vacancies.map { vacancy -> vacancyMapper.map(vacancy)
        }
    }
}
