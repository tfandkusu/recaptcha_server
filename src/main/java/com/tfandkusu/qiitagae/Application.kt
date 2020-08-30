package com.tfandkusu.qiitagae

import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.features.ContentNegotiation
import io.ktor.gson.gson
import io.ktor.html.respondHtml
import io.ktor.http.HttpStatusCode
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.routing.get
import io.ktor.routing.post
import io.ktor.routing.routing
import io.netty.handler.codec.http.HttpResponse
import kotlinx.html.body
import kotlinx.html.head
import kotlinx.html.p
import kotlinx.html.title
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

fun Application.main() {
    install(ContentNegotiation) {
        gson {

        }
    }
    // シークレットキー
    val secret = System.getenv("RECAPTCHA_SECRET_KEY")
    // Androidアプリのパッケージ名
    val packageName = System.getenv("APK_PACKAGE_NAME")
    // 検証APIクライアントの作成
    // https://developers.google.com/recaptcha/docs/verify
    val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl("https://www.google.com/")
        .build()
    val service = retrofit.create(VerifyService::class.java)

    routing {
        get("/") {
            call.respondHtml {
                head {
                    title { +"reCHAPTCHA Validator" }
                }
                body {
                    p {
                        +"reCHAPTCHA Validator"
                    }
                }
            }
        }
        post("/comments") {
            // APIリクエストの取得
            val request = call.receive<CommentRequest>()
            // 検証API呼び出し
            val response = service.verify(secret, request.token)
            // 成功フラグ、Androidアプリのパッケージ名、チャレンジの時間を検証する
            val currentTime = System.currentTimeMillis()
            if (response.success &&
                response.apk_package_name == packageName &&
                response.challenge_ts.time > currentTime - 5 * 60 * 1000
            ) {
                // 検証成功
                call.respond(mapOf("result" to "ok", "comment" to request.comment))
            } else {
                // 検証失敗
                call.respond(HttpStatusCode.Forbidden)
            }
        }
    }
}
