package com.jmj.repository.remote.source

import retrofit2.http.GET
import retrofit2.http.Path
import java.math.BigDecimal

interface RetrofitPatientSource {
    @GET("patient-sources")
    suspend fun findAll(): List<RemotePatientSource>

    @GET("patient-sources/{id}/")
    suspend fun findById(@Path("id") id: String): RemotePatientSource?
}

data class RemotePatientSource(
    val id: String,
    val source: String,
    val fee: BigDecimal,
    val tax: BigDecimal
)