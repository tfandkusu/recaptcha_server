package com.tfandkusu.qiitagae

/**
 * リクエスト本文
 *
 * @param comment 投稿するコメント
 * @param token reCAPTCHAのresponse token
 */
data class CommentRequest(val comment: String, val token: String)
