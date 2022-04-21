package com.dogancan.remote.user

import com.dogancan.model.response.UserResponseModelItem
import com.skydoves.sandwich.ApiResponse
import retrofit2.http.GET

/**
 * Created by dogancan.kilic on 4/20/2022.
 */
interface UserService {
    @GET("users")
    suspend fun getUsers(): ApiResponse<List<UserResponseModelItem>>
}
