package com.example.flickr_example.network

import com.example.flickr_example.network.jsontokotlin.SearchProperties
import com.example.flickr_example.network.jsontokotlin.InfoProperties
import retrofit2.Retrofit

import retrofit2.http.GET

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.Query


private const val BASE_URL = "https://www.flickr.com/services/rest/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface  ApiService {
    @GET("?method=flickr.photos.search&format=json&nojsoncallback=1")
    suspend fun search(@Query("api_key") apiKey: String, @Query("text") query: String): SearchProperties

    @GET("?method=flickr.photos.getInfo&format=json&nojsoncallback=1")
    suspend fun info(@Query("api_key") apiKey: String, @Query("photo_id") id: String): InfoProperties
}

object ObjApi {
    val retrofitService : ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}