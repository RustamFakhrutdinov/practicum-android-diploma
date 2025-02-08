package ru.practicum.android.diploma.ui.filter.common.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.practicum.android.diploma.domain.filter.api.FilterInteractor
import ru.practicum.android.diploma.domain.models.FilterParameters

class FilterCommonViewModel(
    private val filterInteractor: FilterInteractor
) : ViewModel() {

    private var filterParameters: FilterParameters = filterInteractor.readFromFilterStorage()

    private val _filterParamLiveData = MutableLiveData<FilterParameters>()
    val filterParamLiveData: LiveData<FilterParameters> = _filterParamLiveData

    private val _applyButtonLiveData = MutableLiveData<Boolean>()
    val acceptButtonLiveData: LiveData<Boolean> = _applyButtonLiveData

    private val _resetButtonLiveData = MutableLiveData<Boolean>()
    val resetButtonLiveData: LiveData<Boolean> = _resetButtonLiveData

    init {
        _filterParamLiveData.postValue(filterParameters)
        shouldShowResetButton()
    }

    fun setCountryAndRegionParams(
        countryId: String?,
        countryName: String?,
        regionId: String?,
        regionName: String?
    ) {
        filterParameters = filterParameters.copy(
            countryId = countryId,
            countryName = countryName,
            regionId = regionId,
            regionName = regionName
        )
        _filterParamLiveData.postValue(filterParameters)
        shouldShowApplyButton()
        shouldShowResetButton()
    }

    fun setIndustryParams(industryId: String?, industryName: String?) {
        filterParameters = filterParameters.copy(industryId = industryId, industryName = industryName)
        _filterParamLiveData.postValue(filterParameters)
        shouldShowApplyButton()
        shouldShowResetButton()
    }

    fun setExpectedSalaryParam(expectedSalary: Int?) {
        if (filterParameters.expectedSalary != expectedSalary) {
            filterParameters = filterParameters.copy(expectedSalary = expectedSalary)
            _filterParamLiveData.postValue(filterParameters)
            shouldShowApplyButton()
            shouldShowResetButton()
        }
    }

    fun setIsWithoutSalaryShowed(isWithoutSalaryShowed: Boolean) {
        filterParameters = filterParameters.copy(isWithoutSalaryShowed = isWithoutSalaryShowed)
        _filterParamLiveData.postValue(filterParameters)
        shouldShowApplyButton()
        shouldShowResetButton()
    }

    fun saveFilterSettings() {
        filterInteractor.saveToFilterStorage(
            filterParameters
        )
        shouldShowApplyButton()
    }

    fun resetFilter() {
        filterParameters = FilterParameters(isWithoutSalaryShowed = false)
        _filterParamLiveData.postValue(filterParameters)
        shouldShowApplyButton()
        shouldShowResetButton()
    }

    private fun shouldShowApplyButton() {
        _applyButtonLiveData.postValue(filterInteractor.readFromFilterStorage() != filterParameters)
    }

    private fun shouldShowResetButton() {
        _resetButtonLiveData.postValue(filterParameters != FilterParameters())
    }

}
