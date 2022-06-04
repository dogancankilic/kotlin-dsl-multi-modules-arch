package com.dogancan.remote.character

import com.example.responsemodel.CharacterResponseModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by dogancan.kilic on 4/20/2022.
 */
interface CharacterService {
    @GET("character")
    suspend fun getCharacters(@Query("page") page: Int): Response<CharacterResponseModel>
}
