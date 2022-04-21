package com.dogancan.repository.user

import com.dogancan.remote.user.UserDataSource
import javax.inject.Inject

/**
 * Created by dogancan.kilic on 4/20/2022.
 */
class UserRepository @Inject constructor(
    private val userDataSource: UserDataSource
) : BaseRepository() {

    suspend fun getUsers() = invoke {
        userDataSource.getUsers()
    }
}
