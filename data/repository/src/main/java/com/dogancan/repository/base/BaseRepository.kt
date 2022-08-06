package com.dogancan.repository.base

import com.skydoves.sandwich.ApiResponse
import com.skydoves.sandwich.message
import com.skydoves.sandwich.suspendOnError
import com.skydoves.sandwich.suspendOnException
import com.skydoves.sandwich.suspendOnSuccess
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Created by dogancan.kilic on 4/20/2022.
 */
abstract class BaseRepository {

    fun <T> invoke(
        call: suspend () -> ApiResponse<T>,
    ): Flow<Result<T>> = flow {
        val response = call.invoke()
        response.suspendOnSuccess {
            emit(Result.success(this.data))
        }.suspendOnError {
            val message = statusCode.code.toString().plus(message())
            emit(Result.failure(Throwable(message)))
        }.suspendOnException {
            emit(Result.failure(Throwable(message)))
        }
    }
}
