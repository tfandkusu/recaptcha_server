package com.tfandkusu.qiitagae

import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST


interface VerifyService {
    @FormUrlEncoded
    @POST("/recaptcha/api/siteverify")
    suspend fun verify(
        @Field("secret") secret: String,
        @Field("response") response: String
    ): VerifyResponse
}
