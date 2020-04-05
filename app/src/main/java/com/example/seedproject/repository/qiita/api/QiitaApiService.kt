package com.example.seedproject.repository.qiita.api

import com.example.seedproject.data.model.qiita.QiitaArticle
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://qiita.com"

private val moshi = Moshi.Builder()
  .add(KotlinJsonAdapterFactory())
  .build()

private val retrofit = Retrofit.Builder()
  .addConverterFactory(MoshiConverterFactory.create(moshi))
  .addCallAdapterFactory(CoroutineCallAdapterFactory())
  .baseUrl(BASE_URL)
  .build()

interface QiitaApiService {
  @GET("/api/v2/items")
  fun getItems(): Deferred<List<QiitaArticle>>
}

object QiitaApi {
  val retrofitService : QiitaApiService by lazy { retrofit.create(
    QiitaApiService::class.java) }
}