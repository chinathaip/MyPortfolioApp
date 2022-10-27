package com.example.myportfolioapp.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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

    init {
        getMyInfo()
    }

    private fun getMyInfo() {
        viewModelScope.launch {
            val response = withContext(Dispatchers.IO) {
                getMyInfoUseCase()
            }
            response.whenNotNullAndEmpty {
                _listOfInfo.value = InfoGroupToMyInfoModelMapper.map(it)
            }
        }
    }
}