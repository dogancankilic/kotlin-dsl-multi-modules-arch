package com.dogancan.repository.user

import com.dogancan.core.utils.Resource
import com.skydoves.sandwich.ApiResponse
import com.skydoves.sandwich.message
import com.skydoves.sandwich.suspendOnError
import com.skydoves.sandwich.suspendOnException
import com.skydoves.sandwich.suspendOnSuccess
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

/**
 * Created by dogancan.kilic on 4/20/2022.
 */
abstract class BaseRepository {

    fun <T> invoke(
        call: suspend () -> ApiResponse<T>,
    ): Flow<Resource<T>> = flow {
        emit(Resource.loading(null))
        val response = call.invoke()
        response.suspendOnSuccess {
            emit(Resource.success(this.data))
        }.suspendOnError {
            val message = statusCode.code.toString().plus(message())
            emit(Resource.error(Throwable(message), null))
        }.suspendOnException {
            emit(Resource.error(Throwable(message), null))
        }
    }.flowOn(Dispatchers.IO)

}
