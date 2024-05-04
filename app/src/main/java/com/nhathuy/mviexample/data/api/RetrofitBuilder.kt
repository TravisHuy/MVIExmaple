package com.nhathuy.mviexample.data.api

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
//tạo url điểm cuối và sử dụng các dịch vụ rest
object RetrofitBuilder {
    private val baseUrl="https://5e510330f2c0d300147c034c.mockapi.io/"

    private fun getRetrofit() = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    val  apiServer:ApiServer= getRetrofit().create(ApiServer::class.java)

}