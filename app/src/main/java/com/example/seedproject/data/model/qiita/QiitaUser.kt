package com.example.seedproject.data.model.qiita

import com.squareup.moshi.Json

data class QiitaUser(
  val name:     String,

  @Json(name = "profile_image_url")
  val imageUrl: String
)