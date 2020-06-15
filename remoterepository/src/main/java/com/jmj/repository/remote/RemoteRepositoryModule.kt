package com.jmj.repository.remote

import com.jmj.domain.office.Offices
import com.jmj.domain.source.PatientSources
import com.jmj.repository.remote.office.RemoteOffices
import com.jmj.repository.remote.office.RetrofitOffices
import com.jmj.repository.remote.security.AuthenticationInterceptor
import com.jmj.repository.remote.source.RemotePatientSources
import com.jmj.repository.remote.source.RetrofitPatientSource
import com.squareup.moshi.FromJson
import com.squareup.moshi.Moshi
import com.squareup.moshi.ToJson
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.Credentials
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.math.BigDecimal


val BASE_URL = "http://10.0.2.2:8080/api/v1/my-patients/1/"
private val username = "user"
private val password = "password"

val remoteRepositoryModule = module {
    single { moshi() }
    single { httpClient() }
    single { retrofit(baseURL = BASE_URL, moshi = get(), httpClient = get()) }
    single { retrofitOffices(retrofit = get()) }
    single { retrofitPatientSources(retrofit = get()) }
    single { RemoteOffices(retrofitOffices = get()) as Offices }
    single { RemotePatientSources(retrofitPatientSource = get()) as PatientSources}
}

private fun httpClient(): OkHttpClient {
    val authToken = Credentials.basic(username, password)
    val interceptor = AuthenticationInterceptor(authToken)
    return OkHttpClient.Builder().addInterceptor(interceptor).build()
}

private fun retrofit(baseURL: String, moshi: Moshi, httpClient: OkHttpClient): Retrofit =
    Retrofit.Builder()
        .addConverterFactory(ScalarsConverterFactory.create())
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .client(httpClient)
        .baseUrl(baseURL)
        .build()

private fun moshi(): Moshi = Moshi.Builder()
    .add(BigDecimalAdapter)
    .add(KotlinJsonAdapterFactory())
    .build()

private fun retrofitOffices(retrofit: Retrofit) = retrofit.create(RetrofitOffices::class.java)

private fun retrofitPatientSources(retrofit: Retrofit) = retrofit.create(RetrofitPatientSource::class.java)

object BigDecimalAdapter {
    @FromJson
    fun fromJson(string: String) = BigDecimal(string)

    @ToJson
    fun toJson(value: BigDecimal) = value.toString()
}
