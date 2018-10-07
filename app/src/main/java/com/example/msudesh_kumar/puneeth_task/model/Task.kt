package com.example.msudesh_kumar.puneeth_task.model

import com.google.gson.annotations.SerializedName

data class Task(
        @SerializedName("id") val id : Int,
        @SerializedName("description") val description : String,
        @SerializedName("scheduledDate") val scheduledDate : String,
        @SerializedName("status") var status : String
)