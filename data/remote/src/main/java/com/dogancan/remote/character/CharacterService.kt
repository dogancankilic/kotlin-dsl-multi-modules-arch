package com.dogancan.remote.character

import com.dogancan.responsemodel.CharacterResponseModel
import com.dogancan.responsemodel.ResultsItem
import com.skydoves.sandwich.ApiResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by dogancan.kilic on 4/20/2022.
 */
interface CharacterService {
    @GET("character")
    suspend fun getCharacters(@Query("page") page: Int): Response<CharacterResponseModel>

    @GET("character/{id}")
    suspend fun getCharacter(@Path("id") id: Int): ApiResponse<ResultsItem>
}
