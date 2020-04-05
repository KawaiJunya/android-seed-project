package com.example.seedproject.repository.qiita.api

import android.util.Log
import com.example.seedproject.data.model.qiita.QiitaArticle
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import okhttp3.HttpUrl
import okhttp3.HttpUrl.Companion.toHttpUrlOrNull
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

interface QiitaApi {
  @GET("/api/v2/items")
  fun getItems(): Deferred<List<QiitaArticle>>

  companion object {
    private const val BASE_URL = "https://qiita.com"
    private val moshi = Moshi.Builder()
      .add(KotlinJsonAdapterFactory())
      .build()
    fun create(): QiitaApi = create(BASE_URL.toHttpUrlOrNull()!!)
    fun create(httpUrl: HttpUrl): QiitaApi {
      val logger = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BASIC
      }
      val client = OkHttpClient.Builder()
        .addInterceptor(logger)
        .build()
      return Retrofit.Builder()
        .baseUrl(httpUrl)
        .client(client)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()
        .create(QiitaApi::class.java)
    }
  }
}