package ir.mehdisekoba.banimode.utils.network

sealed class NetworkRequest<T>(val data: T? = null, val error: String? = null) {
    class Loading<T> : NetworkRequest<T>()
    class Success<T>(data: T) : NetworkRequest<T>(data)
    class Error<T>(error: String) : NetworkRequest<T>(error = error)

}