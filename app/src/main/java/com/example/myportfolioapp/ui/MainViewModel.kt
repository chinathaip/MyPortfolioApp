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

    private val _scrollToGroup = MutableLiveData<Int>()
    val scrollToGroup: LiveData<Int> = _scrollToGroup

    val tabGroupName = _listOfInfo.map { list ->
        list.filterIsInstance<MyInfoModel.Section>().map { it.sectionName }
    }

    private var tabGroupMap = mapOf<String, Int>()
    private var tabGroupPositionMap = mapOf<String, Int>()

    init {
        getMyInfo()
    }

    fun handleTabSelection(groupName: String, isScrollDown: Boolean) {
        _selectTab.value =
            if (isScrollDown) tabGroupMap[groupName] else tabGroupMap[groupName]?.minus(1)
    }

    fun handleScrollToGroup(groupName: String) {
        _scrollToGroup.value = tabGroupPositionMap[groupName]
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
        tabGroupMap =
            tabGroupName.value.orEmpty().mapIndexed { index, groupName -> groupName to index }
                .toMap()

        tabGroupPositionMap = _listOfInfo.value.orEmpty().mapIndexed { index, data ->
            ((data as? MyInfoModel.Section)?.takeIf { it.sectionName.isNotBlank() }?.sectionName
                ?: "") to index
        }.toMap()
    }
}