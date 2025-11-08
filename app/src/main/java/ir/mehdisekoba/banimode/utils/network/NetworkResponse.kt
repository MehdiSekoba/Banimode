package ir.mehdisekoba.banimode.utils.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import ir.mehdisekoba.banimode.data.model.ErrorResponse

open class NetworkResponse<T>(
    private val response: retrofit2.Response<T>
) {
    private val moshi: Moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

    open fun generateResponse(): NetworkRequest<T> {
        return when {
            response.code() == 401 -> NetworkRequest.Error("شما اجازه دسترسی به این بخش را ندارید")
            response.code() == 404 -> NetworkRequest.Error("صفحه یا منبع مورد نظر پیدا نشد")
            response.code() == 422 -> {
                var errorMessage = ""
                response.errorBody()?.string()?.let { errorJson ->
                    val errorResponse = moshi.adapter(ErrorResponse::class.java).fromJson(errorJson)
                    errorResponse?.errors?.forEach { (_, fieldError) ->
                        errorMessage = fieldError.joinToString()
                    }
                }
                NetworkRequest.Error(errorMessage)
            }
            response.code() == 500 -> NetworkRequest.Error("دوباره تلاش کنید")
            response.isSuccessful -> NetworkRequest.Success(response.body()!!)
            else -> NetworkRequest.Error(response.message())
        }
    }
}

