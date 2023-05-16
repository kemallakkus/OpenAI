package com.kemalakkus.openai.data

import com.kemalakkus.openai.API_KEY.MY_API_KEY
import com.kemalakkus.openai.model.CompletionRequest
import com.kemalakkus.openai.model.CompletionResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface OpenAiApi {
    @Headers("Authorization: Bearer $MY_API_KEY")
    @POST("v1/completions")
    suspend fun getCompletions(@Body completionResponse: CompletionRequest) : Response<CompletionResponse>
}