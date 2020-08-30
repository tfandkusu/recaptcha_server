package com.tfandkusu.qiitagae

import com.google.gson.annotations.SerializedName
import java.util.*

data class VerifyResponse(
    val success: Boolean,
    val challenge_ts: Date,
    val apk_package_name: String,
    @SerializedName("error-codes")
    val error_codes: List<String>
)
