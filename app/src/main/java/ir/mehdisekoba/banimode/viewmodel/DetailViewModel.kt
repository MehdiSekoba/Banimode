package ir.mehdisekoba.banimode.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.mehdisekoba.banimode.data.model.details.ResponseComments
import ir.mehdisekoba.banimode.data.model.details.ResponseDetail
import ir.mehdisekoba.banimode.data.model.details.ResponseRelated
import ir.mehdisekoba.banimode.data.repository.DetailRepository
import ir.mehdisekoba.banimode.utils.network.NetworkRequest
import ir.mehdisekoba.banimode.utils.network.NetworkResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val repository: DetailRepository) : ViewModel() {

    private val _detailData =
        MutableStateFlow<NetworkRequest<ResponseDetail>>(NetworkRequest.Loading())
    val detailData: StateFlow<NetworkRequest<ResponseDetail>> = _detailData


    fun callDetailApi(id: String) = viewModelScope.launch {
        _detailData.value = NetworkRequest.Loading()
        val response = repository.getDetailProduct(id = id)
        _detailData.value = NetworkResponse(response).generateResponse()
    }

    private val _commentData =
        MutableStateFlow<NetworkRequest<ResponseComments>>(NetworkRequest.Loading())
    val commentsData: StateFlow<NetworkRequest<ResponseComments>> = _commentData
    fun callCommentsProducts(id: String) = viewModelScope.launch {
        _commentData.value = NetworkRequest.Loading()
        val response = repository.getCommentsProduct(id)
        _commentData.value = NetworkResponse(response).generateResponse()
    }

    private val _similarProduct =
        MutableStateFlow<NetworkRequest<ResponseRelated>>(NetworkRequest.Loading())
    val similarProduct: StateFlow<NetworkRequest<ResponseRelated>> = _similarProduct

    fun callSimilarProducts(categories: Int) = viewModelScope.launch {

        _similarProduct.value = NetworkRequest.Loading()
        val response = repository.getSimilarProduct(categories)
        _similarProduct.value = NetworkResponse(response).generateResponse()
    }


}