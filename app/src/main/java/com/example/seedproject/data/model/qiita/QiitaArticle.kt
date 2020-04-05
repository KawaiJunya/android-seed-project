package com.example.seedproject.data.model.qiita

import com.squareup.moshi.Json

data class QiitaArticle(
  val title:      String,
  val url:        String,
  val user:       QiitaUser,

  @Json(name = "likes_count")
  val likeCount:  Int
)