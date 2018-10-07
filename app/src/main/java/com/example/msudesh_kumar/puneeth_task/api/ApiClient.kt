package com.example.msudesh_kumar.puneeth_task.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient {

    private val BASE_URL = "http://www.mocky.io/v2/"
    private var retrofit: Retrofit? = null

    fun getClient(): Retrofit? {
        if(retrofit == null)
        {
            retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
        }
        return retrofit
    }

}