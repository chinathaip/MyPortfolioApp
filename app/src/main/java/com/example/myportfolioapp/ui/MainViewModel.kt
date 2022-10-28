package com.example.myportfolioapp.ui

import androidx.lifecycle.*
import com.example.myportfolioapp.domain.GetMyInfoUseCase
import com.example.myportfolioapp.ui.model.InfoGroupToMyInfoModelMapper
import com.example.myportfolioapp.ui.model.MyInfoModel
import com.example.myportfolioapp.whenNotNullAndEmpty
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(
    private val getMyInfoUseCase: GetMyInfoUseCase
) : ViewModel() {

    private val _listOfInfo = MutableLiveData<List<MyInfoModel>>()
    val listOfInfo: LiveData<List<MyInfoModel>> = _listOfInfo

    private val _selectTab = MutableLiveData<Int>()
    val selectTab: LiveData<Int> = _selectTab

    val tabGroupName = _listOfInfo.map { list ->
        list.filterIsInstance<MyInfoModel.Section>().map { it.sectionName }
    }

    var tabGroupPosition = mapOf<String, Int>()

    init {
        getMyInfo()
    }

    fun handleTabSelection(groupName: String, isScrollDown: Boolean) {
        _selectTab.value =
            if (isScrollDown) tabGroupPosition[groupName] else tabGroupPosition[groupName]?.minus(1)
    }

    private fun getMyInfo() {
        viewModelScope.launch {
            val response = withContext(Dispatchers.IO) {
                getMyInfoUseCase()
            }
            response.whenNotNullAndEmpty {
                _listOfInfo.value = InfoGroupToMyInfoModelMapper.map(it)
            }
            locateGroupPosition()
        }
    }

    private fun locateGroupPosition() {
        tabGroupPosition =
            tabGroupName.value?.mapIndexed { index, groupName -> groupName to index }?.toMap()
                ?: mapOf()
    }
}