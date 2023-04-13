package com.kareemdev.core.data

import com.kareemdev.core.data.source.remote.network.ApiResponse
import kotlinx.coroutines.flow.*

abstract class NetworkBoundResource<ResultType, RequestType> {
    private var result: Flow<Resource<ResultType>> = flow {
        emit(Resource.Loading())
//        val dbSource = loadFromDB().first()
        when(val apiResponse = createCall().first()){
            is ApiResponse.Success ->{
                val data = saveCallResult(apiResponse.data)
                emitAll(data.map { Resource.Success(it) })
            }
            is ApiResponse.Error ->{
                onFetchFailed()
                emit(Resource.Error(apiResponse.errorMessage))
            }
            is ApiResponse.Empty ->{
                val data = loadFromDB()
                emitAll(data.map { Resource.Success(it) })
            }
        }
        /*if(shouldFetch(dbSource)){
            emit(Resource.Loading())
            when(val apiResponse = createCall().first()){
                is ApiResponse.Success -> {
                    saveCallResult(apiResponse.data)
                    emitAll(loadFromDB().map{ Resource.Success(it)})
                }
                is ApiResponse.Empty -> {
                    emitAll(loadFromDB().map { Resource.Success(it) })
                }
                is ApiResponse.Error -> {
                    onFetchFailed()
                    emit(Resource.Error(apiResponse.errorMessage))
                }
            }
        }else{
            emitAll(loadFromDB().map { Resource.Success(it) })
        }*/
    }

    protected open fun onFetchFailed(){}

//    protected abstract suspend fun saveCallResult(data: RequestType)
    protected abstract suspend fun saveCallResult(data: RequestType):Flow<ResultType>

    protected abstract suspend fun createCall(): Flow<ApiResponse<RequestType>>

//    protected abstract fun shouldFetch(data: ResultType?): Boolean

    protected abstract fun loadFromDB(): Flow<ResultType>

    fun asFlow(): Flow<Resource<ResultType>> = result
}