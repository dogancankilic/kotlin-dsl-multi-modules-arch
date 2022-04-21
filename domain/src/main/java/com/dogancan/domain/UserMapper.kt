package com.dogancan.domain

import com.dogancan.model.response.UserResponseModelItem
import javax.inject.Inject

/**
 * Created by dogancan.kilic on 4/20/2022.
 */
class UserMapper @Inject constructor() :
    Mapper<List<UserResponseModelItem>, List<UserUiModel>> {
    override fun map(input: List<UserResponseModelItem>): List<UserUiModel> {
        return input
            .map { user ->
                UserUiModel(
                    name = user.name ?: ""
                )
            }
    }
}
