package com.jmj.repository.remote.office

import retrofit2.http.GET
import retrofit2.http.Path

interface RetrofitOffices {

    @GET("offices")
    suspend fun findAll(): List<RemoteOffice>

    @GET("offices/{id}/")
    suspend fun findById(@Path("id") id: String): RemoteOffice?
}

data class RemoteOffice(val id: String, val name: String)