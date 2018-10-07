package com.example.msudesh_kumar.puneeth_task.api

import retrofit2.Call
import com.example.msudesh_kumar.puneeth_task.model.Task
import retrofit2.http.GET

interface ApiInterface {

    @GET("582695f5100000560464ca40") fun getTasks() : Call<List<Task>>

}