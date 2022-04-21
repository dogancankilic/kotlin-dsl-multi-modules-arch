package com.dogancan.domain

import com.dogancan.core.utils.Resource
import com.dogancan.repository.user.UserRepository
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * Created by dogancan.kilic on 4/20/2022.
 */
class GetUserUseCase @Inject constructor(
    private val dataSource: UserRepository,
    private val userMapper: UserMapper

) {

    suspend fun invoke() = dataSource.getUsers().map { response ->

        when (response?.status) {
            Resource.Status.SUCCESS -> {
                val uiModel = response.data?.let { userList ->
                    userMapper.map(userList)
                }
                Resource.success(uiModel)

            }
            Resource.Status.ERROR -> {
                Resource.error(Throwable(response.message), null)

            }

            else -> {
                Resource.loading(null)
            }
        }
    }
}
