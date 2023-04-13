package com.kareemdev.core.data

import com.kareemdev.core.data.source.remote.network.ApiResponse
import kotlinx.coroutines.flow.*

abstract class RemoteSource<ResultType, RequestType> {
    private var result: Flow<Resource<ResultType>> = flow {
        emit(Resource.Loading())
        when (val apiResponse = createCall().first()) {
            is ApiResponse.Success -> {
                val data = convertCallResult(apiResponse.data)
                emitAll(data.map { Resource.Success(it) })
            }
            is ApiResponse.Empty -> {
                val data = emptyResult()
                emitAll(data.map { Resource.Success(it) })
            }
            is ApiResponse.Error -> {
                emit(Resource.Error<ResultType>(apiResponse.errorMessage))
            }

        }
    }

    protected abstract fun emptyResult(): Flow<ResultType>

    protected abstract fun convertCallResult(data: RequestType): Flow<ResultType>

    protected abstract suspend fun createCall(): Flow<ApiResponse<RequestType>>

    fun asFLow(): Flow<Resource<ResultType>> = result
}