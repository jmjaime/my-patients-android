package com.jmj.repository.remote.source

import com.jmj.domain.money.Money
import com.jmj.domain.source.PatientSource
import com.jmj.domain.source.PatientSources

class RemotePatientSources(
    private val retrofitPatientSource: RetrofitPatientSource
) : PatientSources {
    override suspend fun findAll(): List<PatientSource> =
        retrofitPatientSource.findAll().map { it.toPatientSource() }

    override suspend fun findById(id: String): PatientSource? =
        retrofitPatientSource.findById(id)?.toPatientSource()
}

private fun RemotePatientSource.toPatientSource() =
    PatientSource(this.id, this.source, Money(this.fee), this.tax)