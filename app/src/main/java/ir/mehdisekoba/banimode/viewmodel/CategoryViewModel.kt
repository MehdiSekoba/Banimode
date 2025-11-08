package ir.mehdisekoba.banimode.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.mehdisekoba.banimode.data.model.categoury.ResponseCategory
import ir.mehdisekoba.banimode.data.repository.CategoryRepository
import ir.mehdisekoba.banimode.utils.network.NetworkRequest
import ir.mehdisekoba.banimode.utils.network.NetworkResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(private val repository: CategoryRepository) : ViewModel() {

    private val _categoryData =
        MutableStateFlow<NetworkRequest<ResponseCategory>>(NetworkRequest.Loading())
    val categoryData: StateFlow<NetworkRequest<ResponseCategory>> = _categoryData


    fun callDetailApi() = viewModelScope.launch {
        _categoryData.value = NetworkRequest.Loading()
        val response = repository.getMegaMenuData()
        _categoryData.value = NetworkResponse(response).generateResponse()
    }
}