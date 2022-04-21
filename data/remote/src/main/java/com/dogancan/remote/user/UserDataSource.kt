package com.dogancan.remote.user

import javax.inject.Inject

/**
 * Created by dogancan.kilic on 4/20/2022.
 */
class UserDataSource @Inject constructor(
    private val userService: UserService
) {
    suspend fun getUsers() = userService.getUsers()
}
